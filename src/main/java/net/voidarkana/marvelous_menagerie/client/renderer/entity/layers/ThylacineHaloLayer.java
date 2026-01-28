package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Thylacine;

@OnlyIn(Dist.CLIENT)
public class ThylacineHaloLayer<T extends Thylacine> extends RenderLayer<T, MarvelousModel<T>> {

    public ThylacineHaloLayer(RenderLayerParent<T, MarvelousModel<T>> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T entityLivingBaseIn, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        ResourceLocation texture;

        if (entityLivingBaseIn.isEndling()) {

            texture = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/thylacine/thylacine_halo"+(entityLivingBaseIn.isBaby()?"_baby":"")+".png");

            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), texture, pPoseStack, pBuffer, pPackedLight,
                    entityLivingBaseIn, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTick, 1, 1, 1);

        }
    }
}
