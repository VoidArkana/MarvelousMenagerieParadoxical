package net.voidarkana.marvelous_menagerie.common.block.custom;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class FossilBlock extends Block {

    private ItemStack item = ItemStack.EMPTY;
    private long lootTableSeed;
    @Nullable
    protected Level level;
    public boolean hasLootPassed = false;

    public FossilBlock(Properties pProperties) {
        super(pProperties);
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (this.level == null && !pLevel.isClientSide()){
            this.level = pLevel;
        }
        return InteractionResult.PASS;
    }

    private @NotNull ResourceLocation getFossilLoottable(int successLevel) {
        String path = "fossil/success_"+ successLevel +"/"+getDescriptionId();
        return new ResourceLocation(MarvelousMenagerie.MODID, path);
    }

    public void unpackLootTable(Player pPlayer, BlockPos pPos, int successLevel) {
        if (this.level != null && !this.level.isClientSide() && this.level.getServer() != null) {

            this.lootTableSeed = pPos.asLong();
            ResourceLocation lootTable = getFossilLoottable(successLevel);

            LootTable loottable = this.level.getServer().getLootData().getLootTable(lootTable);
            if (pPlayer instanceof ServerPlayer serverplayer) {
                CriteriaTriggers.GENERATE_LOOT.trigger(serverplayer, lootTable);
            }

            LootParams lootparams = (new LootParams.Builder((ServerLevel)this.level))
                    .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pPos))
                    .withLuck(pPlayer.getLuck()).withParameter(LootContextParams.THIS_ENTITY, pPlayer)
                    .create(LootContextParamSets.CHEST);

            ObjectArrayList<ItemStack> objectarraylist = loottable.getRandomItems(lootparams, this.lootTableSeed);
            ItemStack itemstack;
            switch (objectarraylist.size()) {
                case 0:
                    itemstack = ItemStack.EMPTY;
                    break;
                case 1:
                    itemstack = objectarraylist.get(0);
                    break;
                default:
                    //LOGGER.warn("Expected max 1 loot from loot table " + this.lootTable + " got " + objectarraylist.size());
                    itemstack = objectarraylist.get(0);
            }
            this.item = itemstack;
        }
    }

    private void dropContent(Player pPlayer, BlockPos pPos, int successLevel) {

        ExperienceOrb.award((ServerLevel) this.level, Vec3.atCenterOf(pPos), successLevel);

        if (this.level != null && this.level.getServer() != null) {
            this.unpackLootTable(pPlayer, pPos, successLevel);
            if (!this.item.isEmpty()) {
//                double d0 = EntityType.ITEM.getWidth();
//                double d1 = 1.0D - d0;
//                double d2 = d0 / 2.0D;
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

    public void destroyOriginalWithSuccessLevel(Player pPlayer, int successLevel, BlockPos pPos){
        this.dropContent(pPlayer, pPos, successLevel);
        BlockPos tempAdjacent;
        for (int x = -1; x < 2; x++){
            for (int y = -1; y < 2; y++){
                for (int z = -1; z < 2; z++){
                    tempAdjacent = new BlockPos(pPos.getX()+x, pPos.getY()+y, pPos.getZ()+z);
                    BlockState block = level.getBlockState(tempAdjacent);
                    if (block.is(MMTags.Blocks.FOSSIL_BLOCKS) && !(tempAdjacent == pPos)){
                        passOnWithSuccessLevel(pPlayer, successLevel, tempAdjacent, level, 0);
                    }
                }
            }
        }
    }

    public void passOnWithSuccessLevel(Player pPlayer, int successLevel, BlockPos pPos, Level pLevel, int chance){
        this.level = pLevel;
        this.dropContent(pPlayer, pPos, successLevel);
        BlockPos tempAdjacent;
        for (int x = pPos.getX()-1; x < pPos.getX()+2; x++){
            for (int y = pPos.getY()-1; y < pPos.getY()+2; y++){
                for (int z = pPos.getZ()-1; z < pPos.getZ()+2; z++){
                    tempAdjacent = new BlockPos(x, y, z);
                    BlockState block = level.getBlockState(tempAdjacent);
                    if (block.is(MMTags.Blocks.FOSSIL_BLOCKS) && !(tempAdjacent == pPos) && pLevel.random.nextInt(3)>chance){
                        passOnWithSuccessLevel(pPlayer, successLevel, tempAdjacent, level, 0);
                    }
                }
            }
        }
    }

    //private static final Component CONTAINER_TITLE = Component.translatable("marvelous_menagerie.container.fossil_minigame");

//    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
//        return new SimpleMenuProvider((i, inv, player) -> {
//            return new FossilMinigameMenu(i, inv, ContainerLevelAccess.create(level, pos));
//        }, CONTAINER_TITLE);
//    }

}
