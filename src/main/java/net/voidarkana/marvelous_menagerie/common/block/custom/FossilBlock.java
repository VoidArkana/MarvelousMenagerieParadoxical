package net.voidarkana.marvelous_menagerie.common.block.custom;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

import java.util.Comparator;
import java.util.HashSet;

public class FossilBlock extends Block {

    private String lootTable;
    private ItemStack item = ItemStack.EMPTY;
    private Level level;

    public FossilBlock(Properties pProperties, String pLootTable) {
        super(pProperties);
        this.lootTable = pLootTable;
    }

    public FossilBlock(Properties pProperties){
        this(pProperties, "");
    }

    public String getFossilLootTable(){
        return this.lootTable;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }


    public ResourceLocation getFossilLoottable(int successLevel, String lootTable) {
        String path = "fossil/success_"+ successLevel + "/" + lootTable;
        return new ResourceLocation(MarvelousMenagerie.MODID, path);
    }

    public void unpackLootTable(BlockPos pPos, int successLevel) {
        if (this.level != null && !this.level.isClientSide() && this.level.getServer() != null) {

            FossilBlock fossilBlock = this;

            ResourceLocation lootTable = this.getFossilLoottable(successLevel, fossilBlock.getFossilLootTable());

            LootTable loottable = this.level.getServer().getLootData().getLootTable(lootTable);

//            if (pPlayer instanceof ServerPlayer serverplayer) {
//                CriteriaTriggers.GENERATE_LOOT.trigger(serverplayer, lootTable);
//            }

            LootParams lootparams = (new LootParams.Builder((ServerLevel)this.level))
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pPos))
                    //.withLuck(pPlayer.getLuck()).withParameter(LootContextParams.THIS_ENTITY, pPlayer)
                    .create(LootContextParamSets.CHEST);

            ObjectArrayList<ItemStack> objectarraylist = loottable.getRandomItems(lootparams);
            ItemStack itemstack;
            if (objectarraylist.isEmpty()) {
                itemstack = ItemStack.EMPTY;
            } else {
                itemstack = objectarraylist.get(0);
            }
            this.item = itemstack;
        }
    }

    private void dropContent(Level pLevel, BlockPos pPos, int successLevel) {
        this.level = pLevel;

        if (!this.level.isClientSide){
            ExperienceOrb.award((ServerLevel) this.level, Vec3.atCenterOf(pPos), successLevel);

            if (this.level != null && this.level.getServer() != null) {
                this.unpackLootTable(pPos, successLevel);
                if (!this.item.isEmpty()) {
                    double d3 = Vec3.atCenterOf(pPos).x;
                    double d4 = (double) pPos.getY() + (double)(EntityType.ITEM.getHeight() / 2.0F);
                    double d5 = Vec3.atCenterOf(pPos).z;
                    ItemEntity itementity = new ItemEntity(this.level, d3, d4, d5, this.item.split(this.level.random.nextInt(21) + 10));
                    itementity.setDeltaMovement(Vec3.ZERO);
                    this.level.addFreshEntity(itementity);

                    this.item = ItemStack.EMPTY;
                }
                this.level.destroyBlock(pPos, false);
            }
        }
    }

    public void destroyOriginalWithSuccessLevel(Level pLevel, int successLevel, BlockPos pPos){
        this.level = pLevel;

        if (!this.level.isClientSide){
            this.dropContent(pLevel, pPos, successLevel);

            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = pPos.relative(direction);
                BlockState state = level.getBlockState(offsetPos);
                Block block = state.getBlock();
                if (block instanceof FossilBlock fossilBlock) {
                    level.scheduleTick(offsetPos, state.getBlock(), 4 + level.getRandom().nextInt(4));

                    if (!(offsetPos == pPos)){
                        fossilBlock.passOnWithSuccessLevel(successLevel, offsetPos, 0, this.level);
                    }
                }
            }
//            BlockPos tempAdjacent;
//            for (int x = -1; x < 2; x++){
//                for (int y = -1; y < 2; y++){
//                    for (int z = -1; z < 2; z++){
//                        tempAdjacent = new BlockPos(pPos.getX()+x, pPos.getY()+y, pPos.getZ()+z);
//
//                        Block block = this.level.getBlockState(tempAdjacent).getBlock();
//
//                        if (block instanceof FossilBlock fossilBlock && !(tempAdjacent == pPos)){
//                            fossilBlock.passOnWithSuccessLevel(successLevel, tempAdjacent, 0, this.level);
//                        }
//                    }
//                }
//            }
        }
    }

    public void passOnWithSuccessLevel(int successLevel, BlockPos pPos, int chance, Level pLevel){
        if (!pLevel.isClientSide){

            this.level = pLevel;

//            BlockPos tempAdjacent;
            this.dropContent(pLevel, pPos, successLevel);

            for (Direction direction : Direction.values()) {
                BlockPos offsetPos = pPos.relative(direction);
                BlockState state = level.getBlockState(offsetPos);
                Block block = state.getBlock();
                if (block instanceof FossilBlock fossilBlock) {
                    level.scheduleTick(offsetPos, state.getBlock(), 4 + level.getRandom().nextInt(4));

                    if (!(offsetPos == pPos) && this.level.random.nextInt(3)>chance){
                        fossilBlock.passOnWithSuccessLevel(successLevel, offsetPos, chance, pLevel);
                    }
                }
            }

//            for (int x = pPos.getX()-1; x < pPos.getX()+2; x++){
//                for (int y = pPos.getY()-1; y < pPos.getY()+2; y++){
//                    for (int z = pPos.getZ()-1; z < pPos.getZ()+2; z++){
//                        tempAdjacent = new BlockPos(x, y, z);
//
//                        Block block = this.level.getBlockState(tempAdjacent).getBlock();
//
//                        if (block instanceof FossilBlock fossilBlock && !(tempAdjacent == pPos) && this.level.random.nextInt(3)>chance){
//                            fossilBlock.passOnWithSuccessLevel(successLevel, tempAdjacent, chance, pLevel);
//                        }
//                    }
//                }
//            }
        }
    }

    //    @Override
//    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
//        if (!pLevel.isClientSide && this.level == null){
//            this.level = pLevel;
//        }
//        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
//    }
}
