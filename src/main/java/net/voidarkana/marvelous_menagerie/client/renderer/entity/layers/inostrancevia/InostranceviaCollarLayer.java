package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.inostrancevia;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Inostrancevia;

@OnlyIn(Dist.CLIENT)
public class InostranceviaCollarLayer<T extends Inostrancevia, M extends MarvelousModel<T>> extends RenderLayer<T, M> {

    public InostranceviaCollarLayer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity.isTame()) {
            ResourceLocation collarLocation = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/inostrancevia/"
                    + (pLivingEntity.isBaby() ? "baby_" : "")  + "inostrancevia_collar.png");
            float[] afloat = pLivingEntity.getCollarColor().getTextureDiffuseColors();
            renderColoredCutoutModel(this.getParentModel(), collarLocation, pPoseStack, pBuffer, pPackedLight, pLivingEntity, afloat[0], afloat[1], afloat[2]);
        }
    }
}
