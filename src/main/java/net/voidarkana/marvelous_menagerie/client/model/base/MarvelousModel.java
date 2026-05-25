package net.voidarkana.marvelous_menagerie.client.model.base;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.voidarkana.marvelous_menagerie.common.entity.base.ISittingAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import org.joml.Vector3f;

import java.util.function.Function;

public abstract class MarvelousModel<E extends Entity> extends HierarchicalModel<E> {

    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

    private final float youngScaleFactor;
    private final float bodyYOffset;

    public float sittingTicks;
    public float inWaterTicks;
    public float onGroundTicks;
    public float sprintingTicks;
    public float aggroTicks;

    public MarvelousModel(float pYoungScaleFactor, float pBodyYOffset) {
        this(pYoungScaleFactor, pBodyYOffset, RenderType::entityCutoutNoCull);
    }

    public MarvelousModel(float pYoungScaleFactor, float pBodyYOffset, Function<ResourceLocation, RenderType> pRenderType) {
        super(pRenderType);
        this.bodyYOffset = pBodyYOffset;
        this.youngScaleFactor = pYoungScaleFactor;
    }

    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {

        pPoseStack.pushPose();

        if (this.young) {
            pPoseStack.scale(this.youngScaleFactor, this.youngScaleFactor, this.youngScaleFactor);
            pPoseStack.translate(0.0F, this.bodyYOffset, 0.0F);
        }

        this.root().render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);

        pPoseStack.popPose();
    }

    protected void animateIdle(AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks, float pSpeed, float pScale) {
        pAnimationState.updateTime(pAgeInTicks, pSpeed);
        pAnimationState.ifStarted((p_233392_) -> {
            KeyframeAnimations.animate(this, pAnimationDefinition, p_233392_.getAccumulatedTime(), pScale, MarvelousModel.ANIMATION_VECTOR_CACHE);
        });
    }

    protected void animate(AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks) {
        this.animate(pAnimationState, pAnimationDefinition, pAgeInTicks, 1.0F);
    }

    protected void animateWalk(AnimationDefinition pAnimationDefinition, float pLimbSwing, float pLimbSwingAmount, float pMaxAnimationSpeed, float pAnimationScaleFactor) {
        long i = (long)(pLimbSwing * 50.0F * pMaxAnimationSpeed);
        float f = Math.min(pLimbSwingAmount * pAnimationScaleFactor, 1.0F);
        KeyframeAnimations.animate(this, pAnimationDefinition, i, f, MarvelousModel.ANIMATION_VECTOR_CACHE);
    }

    protected void animate(AnimationState pAnimationState, AnimationDefinition pAnimationDefinition, float pAgeInTicks, float pSpeed) {
        pAnimationState.updateTime(pAgeInTicks, pSpeed);
        pAnimationState.ifStarted((p_233392_) -> {
            KeyframeAnimations.animate(this, pAnimationDefinition, p_233392_.getAccumulatedTime(), 1.0F, MarvelousModel.ANIMATION_VECTOR_CACHE);
        });
    }

    protected void applyStatic(AnimationDefinition pAnimationDefinition) {
        KeyframeAnimations.animate(this, pAnimationDefinition, 0L, 1.0F, MarvelousModel.ANIMATION_VECTOR_CACHE);
    }

    @Override
    public void setupAnim(E pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        if (pEntity.onGround()){
            if (this.onGroundTicks < this.getGroundTickBase()) {
                this.onGroundTicks = this.onGroundTicks+0.1f;
            }
        }else {
            if (this.onGroundTicks > 0) {
                this.onGroundTicks = this.onGroundTicks-0.1f;
            }
        }

        if (pEntity instanceof ISittingAnimal animal){
            if (animal.isSitting()){
                if (this.sittingTicks < this.getSittingTickBase()) {
                    this.sittingTicks = this.sittingTicks+0.1f;
                }
            }else {
                if (this.sittingTicks > 0) {
                    this.sittingTicks = this.sittingTicks-0.1f;
                }
            }
        }

        if (pEntity.isInWaterOrBubble()){
            if (this.inWaterTicks < this.getInWaterTickBase()) {
                this.inWaterTicks = this.inWaterTicks+0.1f;
            }
        }else {
            if (this.inWaterTicks > 0) {
                this.inWaterTicks = this.inWaterTicks-0.1f;
            }
        }

        if (pEntity.isSprinting()){
            if (this.sprintingTicks < this.getSprintingTickBase()) {
                this.sprintingTicks = this.sprintingTicks+0.1f;
            }
        }else {
            if (this.sprintingTicks > 0) {
                this.sprintingTicks = this.sprintingTicks-0.1f;
            }
        }

        if (pEntity instanceof PathfinderMob animal){
            if (animal.isAggressive()){
                if (this.aggroTicks < this.getAggroTickBase()) {
                    this.aggroTicks = this.aggroTicks+0.1f;
                }
            }else {
                if (this.aggroTicks > 0) {
                    this.aggroTicks = this.aggroTicks-0.1f;
                }
            }
        }

    }
    public float getOnGroundMultiplier(){
        return this.onGroundTicks/this.getGroundTickBase();
    }

    public float getGroundTickBase(){
        return 5;
    }

    public float getInWaterMultiplier(){
        return this.inWaterTicks/this.getInWaterTickBase();
    }

    public float getInWaterTickBase(){
        return 5;
    }

    public float getSittingMultiplier(){
        return this.sittingTicks/this.getSittingTickBase();
    }

    public float getSittingTickBase(){
        return 5;
    }

    public float getSprintingMultiplier(){
        return this.sprintingTicks/this.getSprintingTickBase();
    }

    public float getSprintingTickBase(){
        return 5;
    }

    public float getAggroMultiplier(){
        return this.aggroTicks/this.getAggroTickBase();
    }

    public float getAggroTickBase(){
        return 5;
    }
}
