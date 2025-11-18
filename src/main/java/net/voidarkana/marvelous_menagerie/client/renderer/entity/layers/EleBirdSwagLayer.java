package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ElephantBird;

@OnlyIn(Dist.CLIENT)
public class EleBirdSwagLayer extends RenderLayer<ElephantBird, MarvelousModel<ElephantBird>> {

    public EleBirdSwagLayer(RenderLayerParent<ElephantBird, MarvelousModel<ElephantBird>> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, ElephantBird entityLivingBaseIn, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        if (entityLivingBaseIn.hasSwag()) {

            ResourceLocation texture = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/elephant_bird/elephant_bird_"+entityLivingBaseIn.getSwagColor()+".png");

            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), texture, pPoseStack, pBuffer, pPackedLight,
                    entityLivingBaseIn, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTick, 1, 1, 1);

        }
    }
}
