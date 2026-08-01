package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.atrociraptor.*;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Atrociraptor;

public class AtrociraptorRenderer<T extends Atrociraptor> extends MobRenderer<T, MarvelousModel<T>> {

    private final AtrociraptorModel<T> adultModel;
    private final BabyAtrociraptorModel<T> babyModel;

    public AtrociraptorRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new AtrociraptorModel<>(pContext.bakeLayer(MMModelLayers.ATROCIRAPTOR_LAYER)), 0.4f);

        this.adultModel = new AtrociraptorModel<>(pContext.bakeLayer(MMModelLayers.ATROCIRAPTOR_LAYER));
        this.babyModel = new BabyAtrociraptorModel<>(pContext.bakeLayer(MMModelLayers.BABY_ATROCIRAPTOR_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/atrociraptor/" +(entity.isBaby() ? "baby_" : "") + "atrociraptor_" + entity.getVariantName() + ".png");
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby())
            this.model = babyModel;
        else
            this.model = adultModel;

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
