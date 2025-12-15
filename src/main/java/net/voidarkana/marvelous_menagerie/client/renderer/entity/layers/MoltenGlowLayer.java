package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.entity.abomination.MoltenModel;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Molten;

@OnlyIn(Dist.CLIENT)
public class MoltenGlowLayer<T extends Molten, M extends MoltenModel<T>> extends EyesLayer<T, M> {

    private static final RenderType MOLTEN_GLOW = RenderType.entityTranslucentEmissive(new ResourceLocation(MarvelousMenagerie.MOD_ID,
            "textures/entity/abomination/molten/molten_glow.png"));

    public MoltenGlowLayer(RenderLayerParent<T, M> p_117507_) {
        super(p_117507_);
    }

    public RenderType renderType() {
        return MOLTEN_GLOW;
    }
}
