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
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.diplocaulus.BabyDiplocaulusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.diplocaulus.DiplocaulusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.flubber.BabyFlubberModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.flubber.FlubberModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Diplocaulus;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Flubber;
import org.jetbrains.annotations.Nullable;

public class FlubberRenderer<T extends Flubber> extends MobRenderer<T, MarvelousModel<T>> {

    private final FlubberModel<T> adultModel;
    private final BabyFlubberModel<T> babyModel;

    public FlubberRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FlubberModel<>(pContext.bakeLayer(MMModelLayers.FLUBBER_LAYER)), 0.75f);
        adultModel = new FlubberModel<>(pContext.bakeLayer(MMModelLayers.FLUBBER_LAYER));
        babyModel = new BabyFlubberModel<>(pContext.bakeLayer(MMModelLayers.BABY_FLUBBER_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/flubber/"+(pEntity.isBaby() ? "baby_" : "")+"flubber.png");
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected void setupRotations(T pEntityLiving, PoseStack pPoseStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pPoseStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        pPoseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(pEntityLiving.getInWaterTicks()/5f, 0, pEntityLiving.currentRoll*360/4)));
    }

    @Override
    protected @Nullable RenderType getRenderType(T pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityCutout(this.getTextureLocation(pLivingEntity));
    }
}
