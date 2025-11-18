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
import net.voidarkana.marvelous_menagerie.common.entity.misc.MMBoatEntity;
import net.voidarkana.marvelous_menagerie.common.entity.misc.MMChestBoatEntity;
import net.voidarkana.marvelous_menagerie.common.entity.misc.RiftEntity;

public class MMEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MarvelousMenagerie.MOD_ID);


    public static final RegistryObject<EntityType<Chud>> CHUD =
            ENTITY_TYPES.register("chud",
                    () -> EntityType.Builder.of(Chud::new, MobCategory.MONSTER)
                            .sized(0.7f, 0.9f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "chud").toString()));

    public static final RegistryObject<EntityType<Arandaspis>> ARANDASPIS =
            ENTITY_TYPES.register("arandaspis",
                    () -> EntityType.Builder.of(Arandaspis::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.35f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "arandaspis").toString()));

    public static final RegistryObject<EntityType<Sacabambaspis>> SACABAMBASPIS =
            ENTITY_TYPES.register("sacabambaspis",
                    () -> EntityType.Builder.of(Sacabambaspis::new, MobCategory.WATER_AMBIENT)
                            .sized(1.1f, 0.5f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "sacabambaspis").toString()));

    public static final RegistryObject<EntityType<Falcatus>> FALCATUS =
            ENTITY_TYPES.register("falcatus",
                    () -> EntityType.Builder.of(Falcatus::new, MobCategory.WATER_AMBIENT)
                            .sized(1.1f, 0.5f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "falcatus").toString()));

    public static final RegistryObject<EntityType<Eolactoria>> EOLACTORIA =
            ENTITY_TYPES.register("eolactoria",
                    () -> EntityType.Builder.of(Eolactoria::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.35f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "eolactoria").toString()));

    public static final RegistryObject<EntityType<Slovenicus>> SLOVENICUS =
            ENTITY_TYPES.register("slovenicus",
                    () -> EntityType.Builder.of(Slovenicus::new, MobCategory.WATER_AMBIENT)
                            .sized(0.35f, 0.75f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "slovenicus").toString()));

    public static final RegistryObject<EntityType<Anomalocaris>> ANOMALOCARIS =
            ENTITY_TYPES.register("anomalocaris",
                    () -> EntityType.Builder.of(Anomalocaris::new, MobCategory.WATER_AMBIENT)
                            .sized(1f, 0.6f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "anomalocaris").toString()));

    public static final RegistryObject<EntityType<Pikaia>> PIKAIA =
            ENTITY_TYPES.register("pikaia",
                    () -> EntityType.Builder.of(Pikaia::new, MobCategory.WATER_AMBIENT)
                            .sized(0.7f, 0.3f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "pikaia").toString()));

    public static final RegistryObject<EntityType<Apthoroblattina>> ROACH
            = ENTITY_TYPES.register("apthoroblattina",
            () -> EntityType.Builder.of(Apthoroblattina::new, MobCategory.CREATURE)
                    .sized(1.2F, 0.45F)
                    .build(new ResourceLocation(MarvelousMenagerie.MOD_ID,"apthoroblattina").toString()));

    public static final RegistryObject<EntityType<Beholder>> BEHOLDER
            = ENTITY_TYPES.register("beholder",
            () -> EntityType.Builder.of(Beholder::new, MobCategory.MONSTER)
                    .sized(1.2F, 1F)
                    .build(new ResourceLocation(MarvelousMenagerie.MOD_ID,"beholder").toString()));


    public static final RegistryObject<EntityType<DawnHorse>> DAWN_HORSE =
            ENTITY_TYPES.register("dawn_horse",
                    () -> EntityType.Builder.of(DawnHorse::new, MobCategory.CREATURE)
                            .sized(0.5F, 0.5F)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID,"dawn_horse").toString()));


    public static final RegistryObject<EntityType<SkeletonDawnHorse>> SKELETON_DAWN_HORSE =
            ENTITY_TYPES.register("skeleton_dawn_horse",
                    () -> EntityType.Builder.of(SkeletonDawnHorse::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.5F)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID,"skeleton_dawn_horse").toString()));


    public static final RegistryObject<EntityType<ZombieDawnHorse>> ZOMBIE_DAWN_HORSE =
            ENTITY_TYPES.register("zombie_dawn_horse",
                    () -> EntityType.Builder.of(ZombieDawnHorse::new, MobCategory.MONSTER)
                            .sized(0.5F, 0.5F)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID,"zombie_dawn_horse").toString()));

    public static final RegistryObject<EntityType<Hallucigenia>> HALLUCIGENIA =
            ENTITY_TYPES.register("hallucigenia",
                    () -> EntityType.Builder.of(Hallucigenia::new, MobCategory.WATER_AMBIENT)
                            .sized(1f, 0.7f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "hallucigenia").toString()));

    public static final RegistryObject<EntityType<Trilobite>> TRILOBITE =
            ENTITY_TYPES.register("trilobite",
                    () -> EntityType.Builder.of(Trilobite::new, MobCategory.WATER_AMBIENT)
                            .sized(0.6f, 0.15f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "trilobite").toString()));

    public static final RegistryObject<EntityType<Leptictidium>> LEPTICTIDIUM =
            ENTITY_TYPES.register("leptictidium",
                    () -> EntityType.Builder.of(Leptictidium::new, MobCategory.CREATURE)
                            .sized(0.5F, 0.4F)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "leptictidium").toString()));

    public static final RegistryObject<EntityType<Thylacine>> THYLACINE =
            ENTITY_TYPES.register("thylacine",
                    () -> EntityType.Builder.of(Thylacine::new, MobCategory.CREATURE)
                            .sized(0.7f, 0.7f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "thylacine").toString()));

    public static final RegistryObject<EntityType<Dodo>> DODO =
            ENTITY_TYPES.register("dodo",
                    () -> EntityType.Builder.of(Dodo::new, MobCategory.CREATURE)
                            .sized(0.7f, 0.9f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "dodo").toString()));

    public static final RegistryObject<EntityType<ElephantBird>> ELEPHANT_BIRD =
            ENTITY_TYPES.register("elephant_bird",
                    () -> EntityType.Builder.of(ElephantBird::new, MobCategory.CREATURE)
                            .sized(1.2f, 3.2f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "elephant_bird").toString()));

    public static final RegistryObject<EntityType<Doedicurus>> DOEDICURUS =
            ENTITY_TYPES.register("doedicurus",
                    () -> EntityType.Builder.of(Doedicurus::new, MobCategory.CREATURE)
                            .sized(1.75F, 1.75F).build("doedicurus"));

    public static final RegistryObject<EntityType<Josephoartigasia>> JOSEPHO =
            ENTITY_TYPES.register("josepho",
                    () -> EntityType.Builder.of(Josephoartigasia::new, MobCategory.CREATURE)
                            .sized(1.5f, 1.9f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "josepho").toString()));


    public static final RegistryObject<EntityType<MMBoatEntity>> MM_BOAT =
            ENTITY_TYPES.register("mm_boat", () -> EntityType.Builder.<MMBoatEntity>of(MMBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mm_boat"));

    public static final RegistryObject<EntityType<MMChestBoatEntity>> MM_CHEST_BOAT =
            ENTITY_TYPES.register("mm_chest_boat", () -> EntityType.Builder.<MMChestBoatEntity>of(MMChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mm_chest_boat"));


    //    public static final RegistryObject<EntityType<Borealopelta>> BOREALOPELTA = ENTITIES.register("borealopelta", () -> EntityType.Builder.of(Borealopelta::new, MobCategory.CREATURE).sized(1.8F, 1.7F).build("borealopelta"));


    public static final RegistryObject<EntityType<RiftEntity>> RIFT =
            ENTITY_TYPES.register("rift",
                    () -> EntityType.Builder.of(RiftEntity::new, MobCategory.MONSTER)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "rift").toString()));

    public static final RegistryObject<EntityType<Fracture>> FRACTURE =
            ENTITY_TYPES.register("fracture", () -> EntityType.Builder.<Fracture>of(Fracture::new, MobCategory.MISC)
                    .sized(1.5f, 1.5f).build("fracture"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
