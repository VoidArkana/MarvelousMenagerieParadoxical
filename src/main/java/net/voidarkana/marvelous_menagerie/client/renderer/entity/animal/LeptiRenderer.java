package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.DawnHorseModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.LeptiModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.DawnHorse;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Leptictidium;

public class LeptiRenderer extends MobRenderer<Leptictidium, LeptiModel<Leptictidium>> {

    public LeptiRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new LeptiModel<>(pContext.bakeLayer(MMModelLayers.LEPTI_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Leptictidium pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/leptictidium.png");
    }
}
