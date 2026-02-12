package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus.BabyOphthalmoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus.OphthalmoModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo.OphthalmoArmorLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo.OphthalmoPassengerLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo.OphthalmoPatternLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ophthalmo.OphthalmoSaddleLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Ophthalmosaurus;

public class OphthalmoRenderer extends MobRenderer<Ophthalmosaurus, MarvelousModel<Ophthalmosaurus>> {

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
