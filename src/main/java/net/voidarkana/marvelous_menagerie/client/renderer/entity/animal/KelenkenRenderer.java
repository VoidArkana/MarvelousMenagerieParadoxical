package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.inostrancevia.BabyInostranceviaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.inostrancevia.InostranceviaModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.kelenken.BabyTerrorBirdModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.kelenken.TerrorBirdModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.JosephoSaddleLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.KelenkenSaddleLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.inostrancevia.InostranceviaCollarLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.inostrancevia.InostranceviaEyesLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.inostrancevia.InostranceviaPatternLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Inostrancevia;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Kelenken;

@OnlyIn(Dist.CLIENT)
public class KelenkenRenderer<T extends Kelenken> extends MobRenderer<T, MarvelousModel<T>> {

    private final TerrorBirdModel<T> adultModel;
    private final BabyTerrorBirdModel<T> babyModel;

    public KelenkenRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new TerrorBirdModel<>(pContext.bakeLayer(MMModelLayers.KELENKEN_LAYER)), 0.75f);
        this.addLayer(new KelenkenSaddleLayer<>(this, pContext));

        this.adultModel = new TerrorBirdModel<>(pContext.bakeLayer(MMModelLayers.KELENKEN_LAYER));
        this.babyModel = new BabyTerrorBirdModel<>(pContext.bakeLayer(MMModelLayers.BABY_KELENKEN_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/kelenken/"
                +(pEntity.isBaby() ? "baby_" : "")
                +("kelenken_")
                +(pEntity.getVariantName())
                +".png");
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
