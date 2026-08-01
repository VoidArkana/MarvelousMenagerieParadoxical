package net.voidarkana.marvelous_menagerie.client.renderer.entity.misc;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.misc.WhispererModel;
import net.voidarkana.marvelous_menagerie.common.entity.villager.Watcher;

public class WhispererRenderer<T extends Watcher> extends MobRenderer<T, WhispererModel<T>> {

    public WhispererRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new WhispererModel<>(pContext.bakeLayer(MMModelLayers.WHISPERER_LAYER)), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/whisperer/whisperer.png");
    }
}
