package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.borealopelta.BabyBorealopeltaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.borealopelta.BorealoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird.BabyEleBirdModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird.EleBirdModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Borealopelta;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ElephantBird;

public class BorealopeltaRenderer extends MobRenderer<Borealopelta, MarvelousModel<Borealopelta>> {

    private final BorealoModel<Borealopelta> adultModel;
    private final BabyBorealopeltaModel<Borealopelta> babyModel;

    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/borealopelta/borealopelta.png");
    private static final ResourceLocation BABY_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/borealopelta/borealopelta_baby.png");

    public BorealopeltaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BorealoModel<>(pContext.bakeLayer(MMModelLayers.BOREALOPELTA_LAYER)), 1.25f);
        this.adultModel = new BorealoModel<>(pContext.bakeLayer(MMModelLayers.BOREALOPELTA_LAYER));
        this.babyModel = new BabyBorealopeltaModel<>(pContext.bakeLayer(MMModelLayers.BABY_BOREALOPELTA_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(Borealopelta pEntity) {
        return pEntity.isBaby() ? BABY_TEXTURE : BASE_TEXTURE;
    }

    @Override
    public void render(Borealopelta pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
