package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.tlatolophus.BabyTlatolophusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.tlatolophus.TlatolophusModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.tlatolophus.TlatolophusAlphaLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.tlatolophus.TlatolophusDarkPatternLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.tlatolophus.TlatolophusLightPatternLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.tlatolophus.TlatolophusSaddleLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Tlatolophus;

public class TlatolophusRenderer <T extends Tlatolophus> extends MobRenderer<T, MarvelousModel<T>> {

    private final TlatolophusModel<T> adultModel;
    private final BabyTlatolophusModel<T> babyModel;

    public TlatolophusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TlatolophusModel<>(pContext.bakeLayer(MMModelLayers.TLATOLOPHUS_LAYER)), 1.85f);

        this.addLayer(new TlatolophusDarkPatternLayer<>(this, pContext));
        this.addLayer(new TlatolophusLightPatternLayer<>(this, pContext));

        this.addLayer(new TlatolophusAlphaLayer<>(this, new TlatolophusModel<>(pContext.bakeLayer(MMModelLayers.TLATOLOPHUS_ALPHA_LAYER)),
                new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/tlatolophus/tlatolophus_alpha.png"),
                (tlatolophus) -> tlatolophus.getCast().getHierarchy()/2f));

        this.addLayer(new TlatolophusSaddleLayer<>(this, pContext));

        this.adultModel = new TlatolophusModel<>(pContext.bakeLayer(MMModelLayers.TLATOLOPHUS_LAYER));
        this.babyModel = new BabyTlatolophusModel<>(pContext.bakeLayer(MMModelLayers.BABY_TLATOLOPHUS_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/tlatolophus/"
                +(entity.isBaby() ? "baby_" : "")
                + "tlatolophus.png");
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby())
            this.model = babyModel;
        else
            this.model = adultModel;

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
