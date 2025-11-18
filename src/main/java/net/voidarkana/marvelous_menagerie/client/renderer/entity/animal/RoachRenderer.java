package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.RoachModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Apthoroblattina;

public class RoachRenderer extends MobRenderer<Apthoroblattina, RoachModel<Apthoroblattina>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/apthoroblattina/apthoroblattina.png");
    private static final ResourceLocation BABY = new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/animal/apthoroblattina/apthoroblattina_baby.png");

    public RoachRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new RoachModel<>(pContext.bakeLayer(MMModelLayers.ROACH_LAYER)), 0.75f);
    }

    @Override
    public ResourceLocation getTextureLocation(Apthoroblattina pEntity) {
        return pEntity.isBaby() ? BABY : TEXTURE;
    }
}
