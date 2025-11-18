package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.JosephoModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.JosephoSaddleLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Josephoartigasia;

public class JosephoRenderer extends MobRenderer<Josephoartigasia, JosephoModel<Josephoartigasia>> {

    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/josepho/josepho.png");

    public JosephoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new JosephoModel<>(pContext.bakeLayer(MMModelLayers.JOSEPHO_LAYER)), 1f);
        this.addLayer(new JosephoSaddleLayer(this, pContext));
    }

    @Override
    public ResourceLocation getTextureLocation(Josephoartigasia pEntity) {
        return BASE_TEXTURE;
    }
}
