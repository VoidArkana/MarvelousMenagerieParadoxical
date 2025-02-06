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
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MarvelousMenagerie.MODID);

    public static final RegistryObject<SimpleParticleType> TIME_SHARD = register("time_shard", false);

    private static RegistryObject<SimpleParticleType> register(String id, boolean flag) {
        return PARTICLES.register(id, () -> new SimpleParticleType(flag));
    }

    public static void register(IEventBus eventBus){
        PARTICLES.register(eventBus);
    }
}
