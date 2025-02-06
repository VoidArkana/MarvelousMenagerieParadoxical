package net.voidarkana.marvelous_menagerie.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class MMModelLayers {

    public static final ModelLayerLocation SKULL_LAYER =
            new ModelLayerLocation(new ResourceLocation(MarvelousMenagerie.MODID, "skull"), "main");

    public static final ModelLayerLocation CHUD_LAYER =
            new ModelLayerLocation(new ResourceLocation(MarvelousMenagerie.MODID, "chud"), "main");
    public static final ModelLayerLocation SACA_LAYER =
            new ModelLayerLocation(new ResourceLocation(MarvelousMenagerie.MODID, "sacabambaspis"), "main");
    public static final ModelLayerLocation ARANDASPIS_LAYER =
            new ModelLayerLocation(new ResourceLocation(MarvelousMenagerie.MODID, "arandaspis"), "main");

    public static final ModelLayerLocation FRACTURE_LAYER =
            new ModelLayerLocation(new ResourceLocation(MarvelousMenagerie.MODID, "fracture"), "main");
    public static final ModelLayerLocation FRACTURE_EMMISSIVE =
            new ModelLayerLocation(new ResourceLocation(MarvelousMenagerie.MODID, "fracture_emmissive"), "main");

}
