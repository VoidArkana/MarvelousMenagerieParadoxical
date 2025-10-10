package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.DoedicurusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ThylacineModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ThylacineHandkerchiefLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Doedicurus;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Thylacine;

public class DoedicurusRenderer extends MobRenderer<Doedicurus, DoedicurusModel<Doedicurus>> {

    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/doedicurus.png");

    public DoedicurusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DoedicurusModel<>(pContext.bakeLayer(MMModelLayers.DOEDICURUS_LAYER)), 1.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(Doedicurus pEntity) {
        return BASE_TEXTURE;
    }
}
