package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dawn_horse.DawnHorseModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.DawnHorse;

public class DawnHorseRenderer extends MobRenderer<DawnHorse, DawnHorseModel<DawnHorse>> {

    public DawnHorseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DawnHorseModel<>(pContext.bakeLayer(MMModelLayers.DAWNHORSE_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(DawnHorse pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/dawn_horse/dawn_horse.png");
    }
}
