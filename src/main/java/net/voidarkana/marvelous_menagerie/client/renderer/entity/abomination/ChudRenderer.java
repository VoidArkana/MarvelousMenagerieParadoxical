package net.voidarkana.marvelous_menagerie.client.renderer.entity.abomination;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.ChudModel;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Chud;

public class ChudRenderer extends MobRenderer<Chud, ChudModel<Chud>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID,"textures/entity/abomination/chud.png");

    public ChudRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ChudModel<>(pContext.bakeLayer(MMModelLayers.CHUD_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Chud pEntity) {
        return TEXTURE;
    }
}
