package net.voidarkana.marvelous_menagerie.common.entity;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anomalocaris;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Arandaspis;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Pikaia;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Sacabambaspis;
import net.voidarkana.marvelous_menagerie.common.entity.misc.RiftEntity;

public class MMEntityPlacements {

    public static void entityPlacement() {
        SpawnPlacements.register(MMEntities.RIFT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                RiftEntity::checkRiftSpawnRules);

        SpawnPlacements.register(MMEntities.SACABAMBASPIS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Sacabambaspis::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(MMEntities.ANOMALOCARIS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Anomalocaris::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(MMEntities.PIKAIA.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Pikaia::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(MMEntities.ARANDASPIS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Arandaspis::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(MMEntities.FALCATUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Pikaia::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(MMEntities.SLOVENICUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Arandaspis::checkSurfaceWaterDinoSpawnRules);
        SpawnPlacements.register(MMEntities.EOLACTORIA.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Arandaspis::checkSurfaceWaterDinoSpawnRules);

//        SpawnPlacements.register(MMEntities.DODO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DodoEntity::checkSurfaceDinoSpawnRules);
//        SpawnPlacements.register(MMEntities.THYLACINE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ThylacineEntity::checkSurfaceDinoSpawnRules);
//        SpawnPlacements.register(MMEntities.ELEPHANT_BIRD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ElephantBirdEntity::checkSurfaceDinoSpawnRules);
//        SpawnPlacements.register(MMEntities.JOSEPHO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JosephoEntity::checkSurfaceDinoSpawnRules);
//        SpawnPlacements.register(MMEntities.OPHTHALMO.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, OphthalmoEntity::checkSurfaceWaterDinoSpawnRules);
//        SpawnPlacements.register(MMEntities.STELLER_SEA_COW.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StellerEntity::checkSurfaceWaterDinoSpawnRules);
//        SpawnPlacements.register(MMEntities.TRILOBITE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, TrilobiteEntity::checkSurfaceWaterDinoSpawnRules);
//        SpawnPlacements.register(MMEntities.HALLUCIGENIA.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, HallucigeniaEntity::checkSurfaceWaterDinoSpawnRules);
    }

}
