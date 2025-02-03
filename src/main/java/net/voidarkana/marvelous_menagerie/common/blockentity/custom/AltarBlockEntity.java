package net.voidarkana.marvelous_menagerie.common.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.misc.Fracture;
import net.voidarkana.marvelous_menagerie.data.codec.RitualManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AltarBlockEntity extends BlockEntityBase {

    private static final TargetingConditions TARGET_FRACTURE = TargetingConditions.forNonCombat().range(6.0D);
    public int time;
    public float rot;
    public float oRot;
    public float tRot;

    public boolean hasFracture = false;

    Item[] ingredients = new Item[4];

    public AltarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MMBlockEntities.ALTAR_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        hasFracture = tag.getBoolean("hasFracture");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("hasFracture", hasFracture);
    }

    @Override
    public void onDestroyed(BlockState state, BlockPos pos) {
        System.out.println("discarding");
        if (this.getFracture(pos) != null){
            System.out.println("discarded!");
            this.getFracture(pos).discard();
        }
    }

    @Override
    public InteractionResult onActivated(BlockState state, BlockPos pos, Player player, InteractionHand hand) {
        if (this.canBeActivated(this.level, pos)){
            if (!(this.level instanceof ServerLevel)) {
                return InteractionResult.SUCCESS;
            } else {
                if (this.hasFracture && getFracture(pos)!=null){

                    EntityType<?> entitytype = MMEntities.CHUD.get();

                    for (RitualManager.RitualProcessData data : RitualManager.DATA) {
                        boolean[] itemUsed = new boolean[4];

                        boolean[] itemChecked = new boolean[4];

                        int itemCount = 0;

                        Item[] inputs = new Item[4];
                        inputs[0] = data.input1();
                        inputs[1] = data.input2();
                        inputs[2] = data.input3();
                        inputs[3] = data.input4();

                        for (int i = 0; i<4; i++){
                            for (int s = 0; s<4; s++){
                                if (!itemUsed[i] && !itemChecked[s]){
                                    if (ingredients[i] == inputs[s]) {
                                        itemUsed[i] = true;
                                        itemChecked[s] = true;
                                        itemCount++;
                                    }
                                }
                            }
                        }

                        if (itemCount == 4){
                            entitytype = data.output();
                        }
                    }

                    this.getFracture(pos).summonCreature(entitytype);
                }else {
                    Fracture fracture = new Fracture(this.level, pos.getCenter().x(), pos.getCenter().y()+2, pos.getCenter().z());
                    fracture.setIsNatural(true);
                    System.out.println(pos);
                    this.level.addFreshEntity(fracture);
                    this.hasFracture = true;
                }
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    @Nullable
    public Fracture getFracture(BlockPos pPos){
        return this.level.getNearestEntity(this.level.getEntitiesOfClass(Fracture.class,
                        new AABB(new BlockPos(pPos.getX(), pPos.getY()+2, pPos.getZ())).inflate(1)), TARGET_FRACTURE,
                null, pPos.getCenter().x(),pPos.getCenter().y()+2, pPos.getCenter().z());
    }

    boolean canBeActivated(Level pLevel, BlockPos pPos){

        int formationSize = this.formationSize(pLevel, pPos);

        int height = formationSize > 3 ? 1 : 0;
        int radius = formationSize > 3 ? formationSize-3 : formationSize;

        if (radius>0){

            BlockEntity be1 = pLevel.getBlockEntity(pPos.offset(radius, height, radius));
            BlockEntity be2 = pLevel.getBlockEntity(pPos.offset(-radius, height, radius));
            BlockEntity be3 = pLevel.getBlockEntity(pPos.offset(radius, height, -radius));
            BlockEntity be4 = pLevel.getBlockEntity(pPos.offset(-radius, height, -radius));

            if (be1 instanceof PedestalBlockEntity pedestal1 && be2 instanceof PedestalBlockEntity pedestal2
                    && be3 instanceof PedestalBlockEntity pedestal3 && be4 instanceof PedestalBlockEntity pedestal4) {

                if (!pedestal1.stack.isEmpty() && !pedestal2.stack.isEmpty()
                        && !pedestal3.stack.isEmpty() && !pedestal4.stack.isEmpty()){
                    this.ingredients[0] = pedestal1.stack.getItem();
                    this.ingredients[1] = pedestal2.stack.getItem();
                    this.ingredients[2] = pedestal3.stack.getItem();
                    this.ingredients[3] = pedestal4.stack.getItem();
                    return true;
                }

                return false;

            }
        }

        return false;
    }

    int formationSize(Level pLevel, BlockPos pPos){

        int size = 1;

        if (pLevel.getBlockState(pPos.offset(size, 0, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(size, 0, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 0, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 0, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())){
            return size;
        }
        if (pLevel.getBlockState(pPos.offset(size, 1, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(size, 1, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 1, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 1, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())){
            return 4;
        }

        size++;

        if (pLevel.getBlockState(pPos.offset(size, 0, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(size, 0, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 0, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 0, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())){
            return size;
        }
        if (pLevel.getBlockState(pPos.offset(size, 1, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(size, 1, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 1, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 1, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())){
            return 5;
        }

        size++;

        if (pLevel.getBlockState(pPos.offset(size, 0, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(size, 0, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 0, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 0, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())){
            return size;
        }
        if (pLevel.getBlockState(pPos.offset(size, 1, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(size, 1, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 1, size)).is(MMBlocks.CHRONO_PEDESTAL.get())
                && pLevel.getBlockState(pPos.offset(-size, 1, -size)).is(MMBlocks.CHRONO_PEDESTAL.get())){
            return 6;
        }

        return 0;
    }

    public static void skullAnimationTick(Level pLevel, BlockPos pPos, BlockState pState, AltarBlockEntity pBlockEntity) {
        pBlockEntity.oRot = pBlockEntity.rot;
        Player player = pLevel.getNearestPlayer((double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, 3.0D, false);
        if (player != null) {
            double d0 = player.getX() - ((double)pPos.getX() + 0.5D);
            double d1 = player.getZ() - ((double)pPos.getZ() + 0.5D);
            pBlockEntity.tRot = (float) Mth.atan2(d1, d0);
        } else {
            pBlockEntity.tRot += 0.02F;
        }

        while(pBlockEntity.rot >= (float)Math.PI) {
            pBlockEntity.rot -= ((float)Math.PI * 2F);
        }

        while(pBlockEntity.rot < -(float)Math.PI) {
            pBlockEntity.rot += ((float)Math.PI * 2F);
        }

        while(pBlockEntity.tRot >= (float)Math.PI) {
            pBlockEntity.tRot -= ((float)Math.PI * 2F);
        }

        while(pBlockEntity.tRot < -(float)Math.PI) {
            pBlockEntity.tRot += ((float)Math.PI * 2F);
        }

        float f2;
        for(f2 = pBlockEntity.tRot - pBlockEntity.rot; f2 >= (float)Math.PI; f2 -= ((float)Math.PI * 2F)) {
        }

        while(f2 < -(float)Math.PI) {
            f2 += ((float)Math.PI * 2F);
        }

        pBlockEntity.rot += f2 * 0.4F;
        ++pBlockEntity.time;
    }
}
