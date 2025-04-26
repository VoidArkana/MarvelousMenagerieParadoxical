package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.HallucigeniaModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Hallucigenia;

public class HallucigeniaRenderer extends MobRenderer<Hallucigenia, HallucigeniaModel<Hallucigenia>> {

    public HallucigeniaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new HallucigeniaModel<>(pContext.bakeLayer(MMModelLayers.HALLU_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Hallucigenia pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/hallucigenia.png");
    }
}
