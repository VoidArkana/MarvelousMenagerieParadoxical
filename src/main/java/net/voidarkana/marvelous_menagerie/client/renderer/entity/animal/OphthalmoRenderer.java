package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus.BabyOphthalmoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus.OphthalmoModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo.OphthalmoArmorLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo.OphthalmoPassengerLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo.OphthalmoPatternLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo.OphthalmoSaddleLayer;
import net.voidarkana.marvelous_menagerie.util.render.ICustomPlayerRidePose;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Ophthalmosaurus;
import org.jetbrains.annotations.NotNull;

public class OphthalmoRenderer extends MobRenderer<Ophthalmosaurus, MarvelousModel<Ophthalmosaurus>> implements ICustomPlayerRidePose {

    private final OphthalmoModel<Ophthalmosaurus> adultModel;
    private final BabyOphthalmoModel<Ophthalmosaurus> babyModel;

    public OphthalmoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new OphthalmoModel<>(pContext.bakeLayer(MMModelLayers.OPHTHALMO_LAYER)), 1f);

        this.adultModel = new OphthalmoModel<>(pContext.bakeLayer(MMModelLayers.OPHTHALMO_LAYER));
        this.babyModel = new BabyOphthalmoModel<>(pContext.bakeLayer(MMModelLayers.BABY_OPHTHALMO_LAYER));

        this.addLayer(new OphthalmoPatternLayer(this, pContext));
        this.addLayer(new OphthalmoSaddleLayer(this, pContext));
        this.addLayer(new OphthalmoArmorLayer(this, pContext));
        this.addLayer(new OphthalmoPassengerLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Ophthalmosaurus pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/ophthalmo/"
                + ((pEntity.isBaby() ? "baby_" : "") + "ophthalmo_" +
                ((pEntity.isAngry() && !pEntity.isBaby() && !pEntity.isTamed()) ? "angry_" : "")
                + pEntity.getColorName(pEntity.getBaseColor())) + ".png");
    }

    @Override
    public <T extends LivingEntity> void applyRiderPose(HumanoidModel<T> pHumanoidModel, @NotNull T pRider) {
        //TODO: figure out animations and head rotation
        pHumanoidModel.rightArm.xRot = this.rad(-155.0F);
        pHumanoidModel.leftArm.xRot = this.rad(-155.0F);
        pHumanoidModel.rightLeg.xRot = this.rad(5.0F);
        pHumanoidModel.rightLeg.zRot = this.rad(10.0F);
        pHumanoidModel.leftLeg.xRot = this.rad(5.0F);
        pHumanoidModel.leftLeg.zRot = this.rad(-10.0F);
        pHumanoidModel.head.xRot = this.rad( -80.0F);
        pHumanoidModel.head.yRot = Mth.clamp(pHumanoidModel.head.yRot, this.rad(-35.0F), this.rad(35.0F));

        pHumanoidModel.hat.xRot = pHumanoidModel.head.xRot;
        pHumanoidModel.hat.yRot = pHumanoidModel.head.yRot;
    }

    @Override
    public <T extends Entity> void applyRiderMatrixStack(@NotNull T pVehicle, @NotNull PoseStack pMatrixStack) {
        //this.getModel().setMatrixStack(pMatrixStack);
        pMatrixStack.translate(0.0F, 0.05F - pVehicle.getBbHeight(), 1.7F);
        pMatrixStack.mulPose(Axis.YN.rotationDegrees(180.0F));
        pMatrixStack.mulPose(Axis.XN.rotationDegrees(295.0F));
    }

    @Override
    public void render(Ophthalmosaurus pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby())
            this.model = babyModel;
        else
            this.model = adultModel;

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void setupRotations(Ophthalmosaurus pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
