package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.BorealoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.DoedicurusModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Borealopelta;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Doedicurus;

public class BorealopeltaRenderer extends MobRenderer<Borealopelta, BorealoModel<Borealopelta>> {

    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/borealopelta.png");

    public BorealopeltaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BorealoModel<>(pContext.bakeLayer(MMModelLayers.BOREALOPELTA_LAYER)), 1.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Borealopelta pEntity) {
        return BASE_TEXTURE;
    }
}
