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
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.lystro.BabyLystroModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.lystro.LystroModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Borealopelta;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Lystrosaurus;

public class LystroRenderer<T extends Lystrosaurus> extends MobRenderer<T, MarvelousModel<T>> {

    private final LystroModel<T> adultModel;
    private final BabyLystroModel<T> babyModel;

    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/lystrosaurus/lystrosaurus.png");
    private static final ResourceLocation BABY_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/lystrosaurus/baby_lystrosaurus.png");

    public LystroRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new LystroModel<>(pContext.bakeLayer(MMModelLayers.LYSTRO_LAYER)), 0.5f);
        this.adultModel = new LystroModel<>(pContext.bakeLayer(MMModelLayers.LYSTRO_LAYER));
        this.babyModel = new BabyLystroModel<>(pContext.bakeLayer(MMModelLayers.BABY_LYSTRO_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return pEntity.isBaby() ? BABY_TEXTURE : BASE_TEXTURE;
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
