package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class MMTags {
    public static class Blocks {
        public static final TagKey<Block> FOSSIL_BLOCKS = tag("fossil_blocks");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(MarvelousMenagerie.MODID, name));
        }
    }
}
