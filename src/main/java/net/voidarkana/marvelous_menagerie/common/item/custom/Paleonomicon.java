package net.voidarkana.marvelous_menagerie.common.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.util.MMTags;

import javax.annotation.Nullable;
import java.util.List;

public class Paleonomicon extends Item {

    boolean usedOnEntity = false;

    public Paleonomicon() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemStackIn = playerIn.getItemInHand(handIn);
        if (!this.usedOnEntity){
            if (playerIn instanceof ServerPlayer) {
                ServerPlayer serverplayerentity = (ServerPlayer) playerIn;
                CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, itemStackIn);
                serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
            }
            playerIn.swing(handIn);
            if (worldIn.isClientSide) {
                MarvelousMenagerie.PROXY.openBookGUI();
            }
        }

        this.usedOnEntity = false;

        return new InteractionResultHolder(InteractionResult.PASS, itemStackIn);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget, InteractionHand pUsedHand) {

        pPlayer.getItemInHand(pUsedHand);
        if (pPlayer instanceof ServerPlayer serverplayerentity) {
            serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
        }

        if (pPlayer.level().isClientSide && pInteractionTarget.getEncodeId() != null && pInteractionTarget.getType().is(MMTags.EntityTypes.HAS_ENTRY)) {
            this.usedOnEntity = true;
            String id = pInteractionTarget.getEncodeId().split(":")[1];
            MarvelousMenagerie.PROXY.openBookGUI("mobs/" + id + ".json");
        }

        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(Component.translatable("item.marvelous_menagerie.paleonomicon.desc").withStyle(ChatFormatting.GRAY));
    }
}
