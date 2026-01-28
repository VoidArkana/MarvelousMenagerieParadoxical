package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.thylacine.BabyThylacineModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.thylacine.ThylacineModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ThylacineHaloLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ThylacineHandkerchiefLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Thylacine;

public class ThylacineRenderer<T extends Thylacine> extends MobRenderer<T, MarvelousModel<T>> {

    private final ThylacineModel<T> adultModel;
    private final BabyThylacineModel<T> babyModel;

    public ThylacineRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ThylacineModel<>(pContext.bakeLayer(MMModelLayers.THYLACINE_LAYER)), 0.5f);
        this.addLayer(new ThylacineHandkerchiefLayer(this, pContext));
        this.addLayer(new ThylacineHaloLayer<>(this, pContext));

        adultModel = new ThylacineModel<>(pContext.bakeLayer(MMModelLayers.THYLACINE_LAYER));
        babyModel = new BabyThylacineModel<>(pContext.bakeLayer(MMModelLayers.BABY_THYLACINE_LAYER));
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/thylacine/thylacine"+(pEntity.isMetro() ? "_metro" : "")
                + (pEntity.isBaby() ? "_baby" : "") +".png");
    }
}
