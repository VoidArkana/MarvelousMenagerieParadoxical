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
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.josephoartigasia.BabyJosephoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.josephoartigasia.JosephoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.pikaia.BabyPikaiaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.pikaia.PikaiaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Josephoartigasia;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Pikaia;
import org.jetbrains.annotations.Nullable;

public class PikaiaRenderer <T extends Pikaia> extends MobRenderer<T, MarvelousModel<T>> {

    private final PikaiaModel<T> adultModel;
    private final BabyPikaiaModel<T> babyModel;

    public PikaiaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PikaiaModel<>(pContext.bakeLayer(MMModelLayers.PIKAIA_LAYER)), 0.5f);

        adultModel = new PikaiaModel<>(pContext.bakeLayer(MMModelLayers.PIKAIA_LAYER));
        babyModel = new BabyPikaiaModel<>(pContext.bakeLayer(MMModelLayers.BABY_PIKAIA_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/pikaia/pikaia" + (pEntity.isPikachu()? "chu" : "") + (pEntity.isBaby()?"_baby":"")+ ".png");
    }

    @Override
    protected void setupRotations(T pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp((float) pEntityLiving.getOutOfWaterTicks()/5, pEntityLiving.currentRoll*360/4, 0)));
    }

    @Nullable
    @Override
    protected RenderType getRenderType(T pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityTranslucent(this.getTextureLocation(pLivingEntity));
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
