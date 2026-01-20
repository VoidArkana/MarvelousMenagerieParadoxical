package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.anomalocaris.AnomalocarisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.anomalocaris.BabyAnomalocarisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.sacabambaspis.BabySacaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.sacabambaspis.SacaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anomalocaris;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Sacabambaspis;
import org.jetbrains.annotations.Nullable;

public class AnomalocarisRenderer<T extends Anomalocaris> extends MobRenderer<T, MarvelousModel<T>> {

    private final AnomalocarisModel<T> adultModel;
    private final BabyAnomalocarisModel<T> babyModel;

    public AnomalocarisRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AnomalocarisModel<>(pContext.bakeLayer(MMModelLayers.ANOMALOCARIS_LAYER)), 0.75f);
        adultModel = new AnomalocarisModel<>(pContext.bakeLayer(MMModelLayers.ANOMALOCARIS_LAYER));
        babyModel = new BabyAnomalocarisModel<>(pContext.bakeLayer(MMModelLayers.BABY_ANOMALOCARIS_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/anomalocaris/"+(pEntity.isBaby() ? "baby_" : "")+"anomalocaris"+pEntity.getVariantName(pEntity.getVariant())+".png");
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void setupRotations(T pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp((float) pEntityLiving.getOutOfWaterTicks()/5, pEntityLiving.currentRoll*360/4, 0)));
    }

    @Override
    protected @Nullable RenderType getRenderType(T pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityCutout(this.getTextureLocation(pLivingEntity));
    }
}
