package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.AltarBlockEntity;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.PedestalBlockEntity;
import net.voidarkana.marvelous_menagerie.data.codec.RitualManager;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends BaseEntityBlock {

    Item[] ingredients = new Item[4];

    public AltarBlock(Properties pProperties) {
        super(pProperties);
    }

    protected static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 9, 16);

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new AltarBlockEntity(pPos, pState);
    }

    @javax.annotation.Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? createTickerHelper(pBlockEntityType, MMBlockEntities.ALTAR_ENTITY.get(), AltarBlockEntity::skullAnimationTick) : null;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (this.canBeActivated(pLevel, pPos)){
            if (!(pLevel instanceof ServerLevel)) {
                return InteractionResult.SUCCESS;
            } else {
                boolean[] itemUsed = new boolean[4];;

                boolean[] itemChecked = new boolean[4];

                EntityType<?> entitytype = EntityType.HORSE;  //this.getType(itemstack.getTag());

                for (RitualManager.RitualProcessData data : RitualManager.DATA) {
                    System.out.println("checking");
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
                                    System.out.println("Item Found");
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


                entitytype.spawn((ServerLevel) pLevel, pPos.above(2), MobSpawnType.NATURAL);
                pLevel.gameEvent(null, GameEvent.ENTITY_PLACE, pPos);

                return InteractionResult.CONSUME;
            }
        }

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
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
}
