package net.voidarkana.marvelous_menagerie.common.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;
import net.voidarkana.marvelous_menagerie.common.block.custom.FossilBlock;

public class PaleoToolkit extends Item {
    
    public PaleoToolkit(Properties pProperties) {
        super(pProperties);
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockPos blockpos = pContext.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (blockstate.getBlock() instanceof FossilBlock) {

            pContext.getPlayer().playSound(SoundEvents.SHULKER_BOX_OPEN);

            if (pContext.getPlayer() instanceof ServerPlayer) {
                ServerPlayer serverplayerentity = (ServerPlayer)pContext.getPlayer();
                //CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, itemStackIn);
                serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
            }

            if (pContext.getLevel().isClientSide) {
                MarvelousMenagerie.PROXY.openToolkitGUI(pContext.getPlayer());
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

//    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
//        ItemStack itemstack = pPlayer.getItemInHand(pHand);
//        //pPlayer.openItemGui(itemstack, pHand);
//
//        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
//    }
}
