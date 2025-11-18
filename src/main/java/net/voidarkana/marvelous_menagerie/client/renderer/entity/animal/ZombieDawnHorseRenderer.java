package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dawn_horse.ZombieDawnHorseModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ZombieDawnHorse;

public class ZombieDawnHorseRenderer extends MobRenderer<ZombieDawnHorse, ZombieDawnHorseModel<ZombieDawnHorse>> {

    public ZombieDawnHorseRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ZombieDawnHorseModel<>(pContext.bakeLayer(MMModelLayers.ZOMBIE_DAWNHORSE_LAYER)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(ZombieDawnHorse pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/dawn_horse/dawn_horse_zombie.png");
    }
}
