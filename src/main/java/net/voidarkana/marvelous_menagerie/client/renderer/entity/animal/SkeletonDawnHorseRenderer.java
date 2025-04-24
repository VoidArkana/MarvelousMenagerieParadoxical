package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.SkeletonDawnHorseModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.SkeletonDawnHorse;

public class SkeletonDawnHorseRenderer extends MobRenderer<SkeletonDawnHorse, SkeletonDawnHorseModel<SkeletonDawnHorse>> {

    public SkeletonDawnHorseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SkeletonDawnHorseModel<>(pContext.bakeLayer(MMModelLayers.SKELETON_DAWNHORSE_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(SkeletonDawnHorse pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/dawn_horse/dawn_horse_skeleton.png");
    }
}
