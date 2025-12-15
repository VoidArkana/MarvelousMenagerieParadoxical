package net.voidarkana.marvelous_menagerie.client.renderer.entity.abomination;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SpiderEyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.ChudModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.MoltenModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.MoltenGlowLayer;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Chud;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Molten;

public class MoltenRenderer extends MobRenderer<Molten, MoltenModel<Molten>> {

    public MoltenRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MoltenModel<>(pContext.bakeLayer(MMModelLayers.MOLTEN_LAYER)), 1.5f);
        this.addLayer(new MoltenGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Molten pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/abomination/molten/molten.png");
    }
}
