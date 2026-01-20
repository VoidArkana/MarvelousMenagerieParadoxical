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
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.diplocaulus.BabyDiplocaulusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.diplocaulus.DiplocaulusModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Diplocaulus;

public class DiplocaulusRenderer<T extends Diplocaulus> extends MobRenderer<T, MarvelousModel<T>> {

    private final DiplocaulusModel<T> adultModel;
    private final BabyDiplocaulusModel<T> babyModel;

    public DiplocaulusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DiplocaulusModel<>(pContext.bakeLayer(MMModelLayers.DIPLOCAULUS_LAYER)), 0.25f);
        adultModel = new DiplocaulusModel<>(pContext.bakeLayer(MMModelLayers.DIPLOCAULUS_LAYER));
        babyModel = new BabyDiplocaulusModel<>(pContext.bakeLayer(MMModelLayers.BABY_DIPLOCAULUS_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/diplocaulus/"+(pEntity.isBaby() ? "baby_" : "")+"diplocaulus.png");
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void setupRotations(T pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp((float) pEntityLiving.getInWaterTicks()/5, 0, pEntityLiving.currentRoll*360/4)));
    }
}
