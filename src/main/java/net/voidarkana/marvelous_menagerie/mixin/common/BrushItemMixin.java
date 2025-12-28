package net.voidarkana.marvelous_menagerie.mixin.common;

import net.minecraft.core.BlockPos;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.voidarkana.marvelous_menagerie.common.block.custom.PaleontologyTableBlock;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.PaleontologyTableBlockEntity;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrushItem.class)
public class BrushItemMixin extends Item {

    @Shadow private static final double MAX_BRUSH_DISTANCE = Math.sqrt(ServerGamePacketListenerImpl.MAX_INTERACTION_DISTANCE) - 1.0D;

    public BrushItemMixin(Properties pProperties) {
        super(pProperties);
    }

    @Inject(
            method = {"onUseTick"},
            at = @At(value = "HEAD")
    )
    private void brushPaleoTable(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration, CallbackInfo ci){
        if (pRemainingUseDuration >= 0 && pLivingEntity instanceof Player player) {
            HitResult hitresult = calculateHitResult(pLivingEntity);
            if (hitresult instanceof BlockHitResult blockhitresult) {
                if (hitresult.getType() == HitResult.Type.BLOCK) {
                    int i = this.getUseDuration(pStack) - pRemainingUseDuration + 1;
                    boolean flag = i % 10 == 5;
                    if (flag) {
                        BlockPos blockpos = blockhitresult.getBlockPos();
                        BlockState blockstate = pLevel.getBlockState(blockpos);

                        Block $$18 = blockstate.getBlock();

                        if ($$18 instanceof PaleontologyTableBlock brushableblock) {
                            BlockEntity blockentity = pLevel.getBlockEntity(blockpos);

                            if (blockentity instanceof PaleontologyTableBlockEntity) {
                                PaleontologyTableBlockEntity brushableblockentity = (PaleontologyTableBlockEntity)pLevel.getBlockEntity(blockpos);

                                SoundEvent soundevent = brushableblock.getBrushSound();

                                pLevel.playSound(player, blockpos, soundevent, SoundSource.BLOCKS);
                                if (!pLevel.isClientSide()) {
                                    boolean flag1 = brushableblockentity.brush(pLevel.getGameTime(), player, blockhitresult.getDirection());
                                    if (flag1) {
                                        EquipmentSlot equipmentslot = pStack.equals(player.getItemBySlot(EquipmentSlot.OFFHAND)) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                                        pStack.hurtAndBreak(1, pLivingEntity, (p_279044_) -> {
                                            p_279044_.broadcastBreakEvent(equipmentslot);
                                        });
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Inject(
            method = {"useOn"},
            at = @At(value = "HEAD"),
            cancellable = true)
    private void useOn(UseOnContext pContext, CallbackInfoReturnable<InteractionResult> cir){
        Player player = pContext.getPlayer();

        if (player != null){
            ItemStack offhand = player.getItemInHand(InteractionHand.OFF_HAND);

            HitResult hitresult = calculateHitResult(player);

            if (hitresult instanceof BlockHitResult blockhitresult && !player.level().isClientSide) {

                Level level = player.level();
                BlockPos blockpos = blockhitresult.getBlockPos();
                BlockState blockstate = level.getBlockState(blockpos);
                Block $$18 = blockstate.getBlock();

                if ($$18 instanceof PaleontologyTableBlock) {
                    BlockEntity blockentity = level.getBlockEntity(blockpos);

                    if (blockentity instanceof PaleontologyTableBlockEntity brushableblockentity) {
                        if (!brushableblockentity.stack.isEmpty() && offhand.is(MMTags.Items.ANIMAL_FOSSILS)){
                            cir.setReturnValue(InteractionResult.FAIL);
                        }
                    }
                }
            }
        }
    }

    private HitResult calculateHitResult(LivingEntity pEntity) {
    return ProjectileUtil.getHitResultOnViewVector(pEntity, (p_281111_) -> {
        return !p_281111_.isSpectator() && p_281111_.isPickable();
    }, MAX_BRUSH_DISTANCE);
    }
}
