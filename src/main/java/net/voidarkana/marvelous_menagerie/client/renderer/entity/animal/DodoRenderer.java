package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dodo.BabyDodoModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.dodo.DodoModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Dodo;
import org.jetbrains.annotations.Nullable;

public class DodoRenderer extends MobRenderer<Dodo, MarvelousModel<Dodo>> {

    private final DodoModel<Dodo> dodoModel;
    private final BabyDodoModel<Dodo> babyDodoModel;

    public DodoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DodoModel<>(pContext.bakeLayer(MMModelLayers.DODO_LAYER)), 0.4f);

        this.dodoModel = new DodoModel<>(pContext.bakeLayer(MMModelLayers.DODO_LAYER));
        this.babyDodoModel = new BabyDodoModel<>(pContext.bakeLayer(MMModelLayers.BABY_DODO_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(Dodo entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/dodo/" +(entity.isBaby() ? "baby_" : "") + "dodo_" + entity.getVariantName() + ".png");
    }

    @Override
    public void render(Dodo pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby())
            this.model = babyDodoModel;
        else
            this.model = dodoModel;

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    protected @Nullable RenderType getRenderType(Dodo pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return RenderType.entityCutout(this.getTextureLocation(pLivingEntity));
    }
}
