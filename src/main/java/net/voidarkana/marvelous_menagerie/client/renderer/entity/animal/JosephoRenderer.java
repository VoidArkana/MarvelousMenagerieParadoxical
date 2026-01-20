package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.flubber.BabyFlubberModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.flubber.FlubberModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.josephoartigasia.BabyJosephoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.josephoartigasia.JosephoModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.JosephoSaddleLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Flubber;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Josephoartigasia;

public class JosephoRenderer<T extends Josephoartigasia> extends MobRenderer<T, MarvelousModel<T>>  {

    private final JosephoModel<T> adultModel;
    private final BabyJosephoModel<T> babyModel;

    public JosephoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new JosephoModel<>(pContext.bakeLayer(MMModelLayers.JOSEPHO_LAYER)), 1f);
        this.addLayer(new JosephoSaddleLayer(this, pContext));

        adultModel = new JosephoModel<>(pContext.bakeLayer(MMModelLayers.JOSEPHO_LAYER));
        babyModel = new BabyJosephoModel<>(pContext.bakeLayer(MMModelLayers.BABY_JOSEPHO_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/josepho/"+(pEntity.isBaby()?"baby_":"")+"josepho.png");
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
