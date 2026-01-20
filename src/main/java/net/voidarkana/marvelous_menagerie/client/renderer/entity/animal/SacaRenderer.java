package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.arandaspis.ArandaspisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.arandaspis.BabyArandaspisModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.sacabambaspis.BabySacaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.sacabambaspis.SacaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Arandaspis;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Sacabambaspis;

public class SacaRenderer<T extends Sacabambaspis> extends MobRenderer<T, MarvelousModel<T>> {

    private final SacaModel<T> adultModel;
    private final BabySacaModel<T> babyModel;

    public SacaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SacaModel<>(pContext.bakeLayer(MMModelLayers.SACA_LAYER)), 0.5f);
        adultModel = new SacaModel<>(pContext.bakeLayer(MMModelLayers.SACA_LAYER));
        babyModel = new BabySacaModel<>(pContext.bakeLayer(MMModelLayers.BABY_SACA_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/sacabambaspis/"+(pEntity.isBaby() ? "baby_" : "")
                +"saca"+pEntity.getVariantName(pEntity.getVariant())+".png");
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
}
