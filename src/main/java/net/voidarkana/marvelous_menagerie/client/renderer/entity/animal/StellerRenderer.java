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
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.steller_sea_cow.BabyStellerModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.steller_sea_cow.StellerModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.StellerSeaCow;


public class StellerRenderer extends MobRenderer<StellerSeaCow, MarvelousModel<StellerSeaCow>> {

    private final StellerModel<StellerSeaCow> stellerModel;
    private final BabyStellerModel<StellerSeaCow> babyStellerModel;

    private static final ResourceLocation TEXTURE_ADULT = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/steller_sea_cow/steller_sea_cow.png");
    private static final ResourceLocation TEXTURE_BABY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/steller_sea_cow/baby_steller_sea_cow.png");

    public StellerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new StellerModel<>(pContext.bakeLayer(MMModelLayers.STELLER_LAYER)), 1.25f);

        this.stellerModel = new StellerModel<>(pContext.bakeLayer(MMModelLayers.STELLER_LAYER));
        this.babyStellerModel = new BabyStellerModel<>(pContext.bakeLayer(MMModelLayers.BABY_STELLER_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(StellerSeaCow entity) {
        return entity.isBaby() ? TEXTURE_BABY : TEXTURE_ADULT;
    }

    @Override
    public void render(StellerSeaCow pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby())
            this.model = babyStellerModel;
        else
            this.model = stellerModel;

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void setupRotations(StellerSeaCow pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(pEntityLiving.currentRoll*360/4));
    }
}
