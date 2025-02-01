package net.voidarkana.marvelous_menagerie.mixin.common;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BrushItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.block.custom.PaleontologyTableBlock;
import net.voidarkana.marvelous_menagerie.common.blockentity.custom.PaleontologyTableBlockEntity;
import net.voidarkana.marvelous_menagerie.util.DustParticlesDelta;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrushItem.class)
public class BrushItemMixin extends Item {

    @Shadow private static final double MAX_BRUSH_DISTANCE = Math.sqrt(ServerGamePacketListenerImpl.MAX_INTERACTION_DISTANCE) - 1.0D;

    public BrushItemMixin(Properties pProperties) {
        super(pProperties);
    }

    @Inject(
            method = {"onUseTick"},
            cancellable = true,
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
                        HumanoidArm humanoidarm = pLivingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? player.getMainArm() : player.getMainArm().getOpposite();
                        this.spawnDustParticles(pLevel, blockhitresult, blockstate, pLivingEntity.getViewVector(0.0F), humanoidarm);
                        Block $$18 = blockstate.getBlock();
                        SoundEvent soundevent;
                        if ($$18 instanceof PaleontologyTableBlock) {
                            PaleontologyTableBlock brushableblock = (PaleontologyTableBlock)$$18;
                            soundevent = brushableblock.getBrushSound();
                        } else {
                            soundevent = SoundEvents.BRUSH_GENERIC;
                        }

                        pLevel.playSound(player, blockpos, soundevent, SoundSource.BLOCKS);
                        if (!pLevel.isClientSide()) {
                            BlockEntity blockentity = pLevel.getBlockEntity(blockpos);
                            if (blockentity instanceof PaleontologyTableBlockEntity) {
                                PaleontologyTableBlockEntity brushableblockentity = (PaleontologyTableBlockEntity)blockentity;
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
                    return;
                }
            }
        }
    }

    private HitResult calculateHitResult(LivingEntity pEntity) {
        return ProjectileUtil.getHitResultOnViewVector(pEntity, (p_281111_) -> {
            return !p_281111_.isSpectator() && p_281111_.isPickable();
        }, MAX_BRUSH_DISTANCE);
    }

    public void spawnDustParticles(Level pLevel, BlockHitResult pHitResult, BlockState pState, Vec3 pPos, HumanoidArm pArm) {
        double d0 = 3.0D;
        int i = pArm == HumanoidArm.RIGHT ? 1 : -1;
        int j = pLevel.getRandom().nextInt(7, 12);
        BlockParticleOption blockparticleoption = new BlockParticleOption(ParticleTypes.BLOCK, pState);
        Direction direction = pHitResult.getDirection();
        DustParticlesDelta brushitem$dustparticlesdelta = DustParticlesDelta.fromDirection(pPos, direction);
        Vec3 vec3 = pHitResult.getLocation();

        for(int k = 0; k < j; ++k) {
            pLevel.addParticle(blockparticleoption, vec3.x - (double)(direction == Direction.WEST ? 1.0E-6F : 0.0F), vec3.y, vec3.z - (double)(direction == Direction.NORTH ? 1.0E-6F : 0.0F), brushitem$dustparticlesdelta.xd() * (double)i * 3.0D * pLevel.getRandom().nextDouble(), 0.0D, brushitem$dustparticlesdelta.zd() * (double)i * 3.0D * pLevel.getRandom().nextDouble());
        }

    }
}
