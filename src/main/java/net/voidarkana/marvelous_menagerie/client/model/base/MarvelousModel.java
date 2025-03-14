package net.voidarkana.marvelous_menagerie.client.model.base;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import org.joml.Vector3f;

import java.util.function.Function;

public abstract class MarvelousModel<E extends Entity> extends HierarchicalModel<E> {

    private static final Vector3f ANIMATION_VECTOR_CACHE = new Vector3f();

    private final float youngScaleFactor;
    private final float bodyYOffset;

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

        float scale = Math.max(0, Math.min(1-Math.abs(pSpeed), 1f));

        pAnimationState.updateTime(pAgeInTicks, scale);

        pAnimationState.ifStarted((p_233392_) -> {
            KeyframeAnimations.animate(this, pAnimationDefinition, p_233392_.getAccumulatedTime(), scale, ANIMATION_VECTOR_CACHE);
        });

    }

}
