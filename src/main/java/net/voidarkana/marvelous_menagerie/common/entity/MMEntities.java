package net.voidarkana.marvelous_menagerie.common.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Chud;
import net.voidarkana.marvelous_menagerie.common.entity.animal.*;
import net.voidarkana.marvelous_menagerie.common.entity.misc.Fracture;

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

    public static final RegistryObject<EntityType<Fracture>> FRACTURE =
            ENTITY_TYPES.register("fracture", () -> EntityType.Builder.<Fracture>of(Fracture::new, MobCategory.MISC)
                    .sized(1.5f, 1.5f).build("fracture"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
