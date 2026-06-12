package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.inostrancevia.*;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.InostranceviaCollarLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.InostranceviaEyesLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Inostrancevia;

public class InostranceviaRenderer<T extends Inostrancevia> extends MobRenderer<T, MarvelousModel<T>> {

    private final InostranceviaModel<T> adultModel;
    private final BabyInostranceviaModel<T> babyModel;

    public InostranceviaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new InostranceviaModel<>(pContext.bakeLayer(MMModelLayers.INOSTRANCEVIA_LAYER)), 0.75f);
        this.addLayer(new InostranceviaCollarLayer<>(this));
        this.addLayer(new InostranceviaEyesLayer<>(this));
        this.adultModel = new InostranceviaModel<>(pContext.bakeLayer(MMModelLayers.INOSTRANCEVIA_LAYER));
        this.babyModel = new BabyInostranceviaModel<>(pContext.bakeLayer(MMModelLayers.BABY_INOSTRANCEVIA_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/inostrancevia/"
                +(pEntity.isBaby() ? "baby_" : "")
                +(pEntity.isEvil() ? "evil_":"")
                +"inostrancevia.png");
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
