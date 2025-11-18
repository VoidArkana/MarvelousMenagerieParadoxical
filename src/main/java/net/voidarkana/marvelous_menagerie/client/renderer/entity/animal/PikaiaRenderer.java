package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.PikaiaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Pikaia;
import org.jetbrains.annotations.Nullable;

public class PikaiaRenderer extends MobRenderer<Pikaia, PikaiaModel<Pikaia>> {

    public PikaiaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PikaiaModel<>(pContext.bakeLayer(MMModelLayers.PIKAIA_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Pikaia pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/pikaia/pikaia" + (pEntity.isPikachu()? "chu" : "") + ".png");
    }

    @Override
    protected void setupRotations(Pikaia pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }

    @Nullable
    @Override
    protected RenderType getRenderType(Pikaia pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityTranslucent(this.getTextureLocation(pLivingEntity));
    }
}
