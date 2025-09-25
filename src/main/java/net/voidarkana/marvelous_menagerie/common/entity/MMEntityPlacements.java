package net.voidarkana.marvelous_menagerie.common.entity;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.voidarkana.marvelous_menagerie.common.entity.animal.*;
import net.voidarkana.marvelous_menagerie.common.entity.misc.RiftEntity;

public class MMEntityPlacements {

    public static void entityPlacement() {
        SpawnPlacements.register(MMEntities.RIFT.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                RiftEntity::checkRiftSpawnRules);


        SpawnPlacements.register(MMEntities.SACABAMBASPIS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Sacabambaspis::checkSurfaceWaterCreatureRules);
        SpawnPlacements.register(MMEntities.ANOMALOCARIS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Anomalocaris::checkSurfaceWaterCreatureRules);
        SpawnPlacements.register(MMEntities.PIKAIA.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Pikaia::checkSurfaceWaterCreatureRules);
        SpawnPlacements.register(MMEntities.ARANDASPIS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Arandaspis::checkSurfaceWaterCreatureRules);
        SpawnPlacements.register(MMEntities.FALCATUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Pikaia::checkSurfaceWaterCreatureRules);
        SpawnPlacements.register(MMEntities.SLOVENICUS.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Slovenicus::checkSurfaceWaterCreatureRules);
        SpawnPlacements.register(MMEntities.EOLACTORIA.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Eolactoria::checkSurfaceWaterCreatureRules);
        SpawnPlacements.register(MMEntities.ROACH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Apthoroblattina::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntities.DAWN_HORSE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, DawnHorse::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntities.SKELETON_DAWN_HORSE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SkeletonDawnHorse::checkMonsterSpawnRules);
        SpawnPlacements.register(MMEntities.HALLUCIGENIA.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Hallucigenia::checkSurfaceWaterCreatureRules);
        SpawnPlacements.register(MMEntities.TRILOBITE.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Trilobite::checkSurfaceWaterCreatureRules);
        SpawnPlacements.register(MMEntities.THYLACINE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Thylacine::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntities.LEPTICTIDIUM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Leptictidium::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntities.DODO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Dodo::checkAnimalSpawnRules);
        SpawnPlacements.register(MMEntities.ELEPHANT_BIRD.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, ElephantBird::checkAnimalSpawnRules);
//        SpawnPlacements.register(MMEntities.JOSEPHO.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, JosephoEntity::checkSurfaceDinoSpawnRules);
//        SpawnPlacements.register(MMEntities.OPHTHALMO.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, OphthalmoEntity::checkSurfaceWaterDinoSpawnRules);
//        SpawnPlacements.register(MMEntities.STELLER_SEA_COW.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, StellerEntity::checkSurfaceWaterDinoSpawnRules);
    }

}
