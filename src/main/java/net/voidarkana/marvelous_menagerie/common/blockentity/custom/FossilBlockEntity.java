package net.voidarkana.marvelous_menagerie.common.blockentity.custom;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.custom.FossilBlock;
import net.voidarkana.marvelous_menagerie.common.blockentity.MMBlockEntities;

public class FossilBlockEntity extends BlockEntity {

    private ItemStack item = ItemStack.EMPTY;
    private long lootTableSeed;

    public FossilBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MMBlockEntities.FOSSILBLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public ResourceLocation getFossilLoottable(int successLevel, String lootTable) {
        String path = "fossil/success_"+ successLevel + "/" + lootTable;
        return new ResourceLocation(MarvelousMenagerie.MODID, path);
    }

    public void unpackLootTable(Player pPlayer, BlockPos pPos, int successLevel) {
        if (this.level != null && !this.level.isClientSide() && this.level.getServer() != null) {

            this.lootTableSeed = pPos.asLong();

            FossilBlock fossilBlock = (FossilBlock) this.getBlockState().getBlock();

            ResourceLocation lootTable = this.getFossilLoottable(successLevel, fossilBlock.getFossilLootTable());

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
                    itemstack = objectarraylist.get(0);
            }
            this.item = itemstack;
        }
    }

    private void dropContent(Player pPlayer, BlockPos pPos, int successLevel) {

        if (!this.level.isClientSide){
            ExperienceOrb.award((ServerLevel) this.level, Vec3.atCenterOf(pPos), successLevel);

            if (this.level != null && this.level.getServer() != null) {
                this.unpackLootTable(pPlayer, pPos, successLevel);
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

    public void destroyOriginalWithSuccessLevel(Player pPlayer, int successLevel, BlockPos pPos){
        if (!this.level.isClientSide){
            BlockPos tempAdjacent;
            this.dropContent(pPlayer, pPos, successLevel);
            for (int x = -1; x < 2; x++){
                for (int y = -1; y < 2; y++){
                    for (int z = -1; z < 2; z++){
                        tempAdjacent = new BlockPos(pPos.getX()+x, pPos.getY()+y, pPos.getZ()+z);

                        BlockEntity block = this.level.getBlockEntity(tempAdjacent);

                        if (block instanceof FossilBlockEntity fossilBlock && !(tempAdjacent == pPos)){
                            fossilBlock.passOnWithSuccessLevel(pPlayer, successLevel, tempAdjacent, 0);
                        }
                    }
                }
            }
        }
    }

    public void passOnWithSuccessLevel(Player pPlayer, int successLevel, BlockPos pPos, int chance){
        if (!this.level.isClientSide){

            BlockPos tempAdjacent;
            this.dropContent(pPlayer, pPos, successLevel);
            for (int x = pPos.getX()-1; x < pPos.getX()+2; x++){
                for (int y = pPos.getY()-1; y < pPos.getY()+2; y++){
                    for (int z = pPos.getZ()-1; z < pPos.getZ()+2; z++){
                        tempAdjacent = new BlockPos(x, y, z);
                        BlockEntity block = level.getBlockEntity(tempAdjacent);
                        if (block instanceof FossilBlockEntity fossilBlock && !(tempAdjacent == pPos) && this.level.random.nextInt(3)>chance){
                            fossilBlock.passOnWithSuccessLevel(pPlayer, successLevel, tempAdjacent, chance+1);
                        }
                    }
                }
            }

        }
    }

}
