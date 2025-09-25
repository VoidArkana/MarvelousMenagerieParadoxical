package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.LeptiModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ThylacineModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ThylacineHandkerchiefLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Leptictidium;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Thylacine;

public class ThylacineRenderer extends MobRenderer<Thylacine, ThylacineModel<Thylacine>> {

    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/thylacine.png");
    private static final ResourceLocation METRO_TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/thylacine_metropolitan.png");

    public ThylacineRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ThylacineModel<>(pContext.bakeLayer(MMModelLayers.THYLACINE_LAYER)), 0.5f);
        this.addLayer(new ThylacineHandkerchiefLayer(this, pContext));
    }

    @Override
    public ResourceLocation getTextureLocation(Thylacine pEntity) {
        return pEntity.isMetro() ? METRO_TEXTURE : BASE_TEXTURE;
    }
}
