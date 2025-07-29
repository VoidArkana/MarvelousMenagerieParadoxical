package net.voidarkana.marvelous_menagerie.client.renderer.entity.misc;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.misc.MMBoatEntity;
import net.voidarkana.marvelous_menagerie.common.entity.misc.MMChestBoatEntity;

import java.util.Map;
import java.util.stream.Stream;

public class MMBoatRenderer extends BoatRenderer {
    private final Map<MMBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public MMBoatRenderer(EntityRendererProvider.Context pContext, boolean pChestBoat) {
        super(pContext, pChestBoat);
        this.boatResources = Stream.of(MMBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type,
                type -> Pair.of(new ResourceLocation(MarvelousMenagerie.MODID, getTextureLocation(type, pChestBoat)), this.createBoatModel(pContext, type, pChestBoat))));
    }

    private static String getTextureLocation(MMBoatEntity.Type pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, MMBoatEntity.Type pType, boolean pChestBoat) {
        ModelLayerLocation modellayerlocation = pChestBoat ? MMBoatRenderer.createChestBoatModelName(pType) : MMBoatRenderer.createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);
        return pChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    public static ModelLayerLocation createBoatModelName(MMBoatEntity.Type pType) {
        return createLocation("boat/" + pType.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(MMBoatEntity.Type pType) {
        return createLocation("chest_boat/" + pType.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(new ResourceLocation(MarvelousMenagerie.MODID, pPath), pModel);
    }

    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if(boat instanceof MMBoatEntity MMBoat) {
            return this.boatResources.get(MMBoat.getMMVariant());
        } else if(boat instanceof MMChestBoatEntity modChestBoatEntity) {
            return this.boatResources.get(modChestBoatEntity.getMMVariant());
        } else {
            return null;
        }
    }
}
