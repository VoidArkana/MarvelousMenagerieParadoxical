package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.hallucigenia.BabyHallucigeniaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.hallucigenia.HallucigeniaModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.HallucigeniaEyesLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.HallucigeniaPupilsLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Hallucigenia;
import org.jetbrains.annotations.Nullable;

public class HallucigeniaRenderer<T extends Hallucigenia> extends MobRenderer<T, MarvelousModel<T>> {

    private final HallucigeniaModel<T> adultModel;
    private final BabyHallucigeniaModel<T> babyModel;

    public HallucigeniaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HallucigeniaModel<>(pContext.bakeLayer(MMModelLayers.HALLU_LAYER)), 0.5f);
        this.addLayer(new HallucigeniaEyesLayer<>(this, pContext.getModelSet()));
        this.addLayer(new HallucigeniaPupilsLayer<>(this));

        adultModel = new HallucigeniaModel<>(pContext.bakeLayer(MMModelLayers.HALLU_LAYER));
        babyModel = new BabyHallucigeniaModel<>(pContext.bakeLayer(MMModelLayers.BABY_HALLU_LAYER));
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/hallucigenia/"+(pEntity.isBaby() ? "baby_" : "")+"hallucigenia_"+pEntity.getVariantName()+".png");
    }

    @Override
    protected @Nullable RenderType getRenderType(T pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityCutout(this.getTextureLocation(pLivingEntity));
    }
}
