package net.voidarkana.marvelous_menagerie.common.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Beholder;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Chud;
import net.voidarkana.marvelous_menagerie.common.entity.animal.*;
import net.voidarkana.marvelous_menagerie.common.entity.misc.Fracture;
import net.voidarkana.marvelous_menagerie.common.entity.misc.RiftEntity;

public class MMEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MarvelousMenagerie.MODID);


    public static final RegistryObject<EntityType<Chud>> CHUD =
            ENTITY_TYPES.register("chud",
                    () -> EntityType.Builder.of(Chud::new, MobCategory.MONSTER)
                            .sized(0.7f, 0.9f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "chud").toString()));

    public static final RegistryObject<EntityType<Arandaspis>> ARANDASPIS =
            ENTITY_TYPES.register("arandaspis",
                    () -> EntityType.Builder.of(Arandaspis::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.35f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "arandaspis").toString()));

    public static final RegistryObject<EntityType<Sacabambaspis>> SACABAMBASPIS =
            ENTITY_TYPES.register("sacabambaspis",
                    () -> EntityType.Builder.of(Sacabambaspis::new, MobCategory.WATER_AMBIENT)
                            .sized(1.1f, 0.5f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "sacabambaspis").toString()));

    public static final RegistryObject<EntityType<Falcatus>> FALCATUS =
            ENTITY_TYPES.register("falcatus",
                    () -> EntityType.Builder.of(Falcatus::new, MobCategory.WATER_AMBIENT)
                            .sized(1.1f, 0.5f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "falcatus").toString()));

    public static final RegistryObject<EntityType<Eolactoria>> EOLACTORIA =
            ENTITY_TYPES.register("eolactoria",
                    () -> EntityType.Builder.of(Eolactoria::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.35f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "eolactoria").toString()));

    public static final RegistryObject<EntityType<Slovenicus>> SLOVENICUS =
            ENTITY_TYPES.register("slovenicus",
                    () -> EntityType.Builder.of(Slovenicus::new, MobCategory.WATER_AMBIENT)
                            .sized(0.35f, 0.75f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "slovenicus").toString()));

    public static final RegistryObject<EntityType<Anomalocaris>> ANOMALOCARIS =
            ENTITY_TYPES.register("anomalocaris",
                    () -> EntityType.Builder.of(Anomalocaris::new, MobCategory.WATER_AMBIENT)
                            .sized(1f, 0.6f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "anomalocaris").toString()));

    public static final RegistryObject<EntityType<Pikaia>> PIKAIA =
            ENTITY_TYPES.register("pikaia",
                    () -> EntityType.Builder.of(Pikaia::new, MobCategory.WATER_AMBIENT)
                            .sized(0.7f, 0.3f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "pikaia").toString()));

    public static final RegistryObject<EntityType<Apthoroblattina>> ROACH
            = ENTITY_TYPES.register("apthoroblattina",
            () -> EntityType.Builder.of(Apthoroblattina::new, MobCategory.CREATURE)
                    .sized(1.2F, 0.45F)
                    .build(new ResourceLocation(MarvelousMenagerie.MODID,"apthoroblattina").toString()));

    public static final RegistryObject<EntityType<Beholder>> BEHOLDER
            = ENTITY_TYPES.register("beholder",
            () -> EntityType.Builder.of(Beholder::new, MobCategory.MONSTER)
                    .sized(1.2F, 1F)
                    .build(new ResourceLocation(MarvelousMenagerie.MODID,"beholder").toString()));


    public static final RegistryObject<EntityType<DawnHorse>> DAWN_HORSE =
            ENTITY_TYPES.register("dawn_horse",
                    () -> EntityType.Builder.of(DawnHorse::new, MobCategory.CREATURE)
                            .sized(0.5F, 0.5F)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID,"dawn_horse").toString()));


    public static final RegistryObject<EntityType<SkeletonDawnHorse>> SKELETON_DAWN_HORSE =
            ENTITY_TYPES.register("skeleton_dawn_horse",
                    () -> EntityType.Builder.of(SkeletonDawnHorse::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.5F)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID,"skeleton_dawn_horse").toString()));


    public static final RegistryObject<EntityType<ZombieDawnHorse>> ZOMBIE_DAWN_HORSE =
            ENTITY_TYPES.register("zombie_dawn_horse",
                    () -> EntityType.Builder.of(ZombieDawnHorse::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.5F)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID,"zombie_dawn_horse").toString()));

    public static final RegistryObject<EntityType<Hallucigenia>> HALLUCIGENIA =
            ENTITY_TYPES.register("hallucigenia",
                    () -> EntityType.Builder.of(Hallucigenia::new, MobCategory.WATER_AMBIENT)
                            .sized(1f, 0.7f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "hallucigenia").toString()));

//    public static final RegistryObject<EntityType<Doedicurus>> DOEDICURUS = ENTITIES.register("doedicurus", () -> EntityType.Builder.of(Doedicurus::new, MobCategory.CREATURE).sized(1.75F, 1.75F).build("doedicurus"));
//    public static final RegistryObject<EntityType<Leptictidium>> LEPTICTIDIUM = ENTITIES.register("leptictidium", () -> EntityType.Builder.of(Leptictidium::new, MobCategory.CREATURE).sized(0.5F, 0.4F).build("leptictidium"));
//    public static final RegistryObject<EntityType<Borealopelta>> BOREALOPELTA = ENTITIES.register("borealopelta", () -> EntityType.Builder.of(Borealopelta::new, MobCategory.CREATURE).sized(1.8F, 1.7F).build("borealopelta"));


    public static final RegistryObject<EntityType<RiftEntity>> RIFT =
            ENTITY_TYPES.register("rift",
                    () -> EntityType.Builder.of(RiftEntity::new, MobCategory.MONSTER)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(MarvelousMenagerie.MODID, "rift").toString()));

    public static final RegistryObject<EntityType<Fracture>> FRACTURE =
            ENTITY_TYPES.register("fracture", () -> EntityType.Builder.<Fracture>of(Fracture::new, MobCategory.MISC)
                    .sized(1.5f, 1.5f).build("fracture"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
