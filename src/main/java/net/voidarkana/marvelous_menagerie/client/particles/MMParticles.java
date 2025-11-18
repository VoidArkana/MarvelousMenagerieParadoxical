package net.voidarkana.marvelous_menagerie.client.particles;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class MMParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<SimpleParticleType> TIME_SHARD = register("time_shard", false);

    public static final RegistryObject<SimpleParticleType> RIFT = register("rift", false);

    public static final RegistryObject<SimpleParticleType> ITEM_MORPH = register("item_morph", false);

    private static RegistryObject<SimpleParticleType> register(String id, boolean flag) {
        return PARTICLES.register(id, () -> new SimpleParticleType(flag));
    }

    public static void register(IEventBus eventBus){
        PARTICLES.register(eventBus);
    }
}
