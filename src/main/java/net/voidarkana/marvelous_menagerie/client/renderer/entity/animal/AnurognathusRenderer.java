package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.anurognathus.*;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anurognathus;

public class AnurognathusRenderer<T extends Anurognathus> extends MobRenderer<T, MarvelousModel<T>> {

    private final AnurognathusModel<T> adultModel;
    private final BabyAnurognathusModel<T> babyModel;

    public AnurognathusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AnurognathusModel<>(pContext.bakeLayer(MMModelLayers.ANUROGNATHUS_LAYER)), 0.25f);
        this.adultModel = new AnurognathusModel<>(pContext.bakeLayer(MMModelLayers.ANUROGNATHUS_LAYER));
        this.babyModel = new BabyAnurognathusModel<>(pContext.bakeLayer(MMModelLayers.BABY_ANUROGNATHUS_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/anurognathus/"+(pEntity.isBaby() ? "baby_" : "")+"anurognathus.png");
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
