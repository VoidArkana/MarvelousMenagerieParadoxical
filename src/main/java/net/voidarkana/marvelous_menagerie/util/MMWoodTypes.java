package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class MMWoodTypes {
    public static final WoodType SIGILLARIA = WoodType.register(new WoodType(MarvelousMenagerie.MODID + ":sigillaria", BlockSetType.OAK));

    public static final WoodType PROTOTAXITES = WoodType.register(new WoodType(MarvelousMenagerie.MODID + ":prototaxites", BlockSetType.OAK));

    public static final WoodType CALAMITES = WoodType.register(new WoodType(MarvelousMenagerie.MODID + ":calamites", BlockSetType.OAK));

}
