package net.voidarkana.marvelous_menagerie.common.entity;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;

public class MMEntityPlacements {

    public static void entityPlacement() {
        SpawnPlacements.register(MMEntities.RIFT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules);
    }

}
