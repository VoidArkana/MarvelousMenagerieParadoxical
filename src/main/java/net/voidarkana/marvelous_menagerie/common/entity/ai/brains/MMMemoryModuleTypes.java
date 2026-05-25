package net.voidarkana.marvelous_menagerie.common.entity.ai.brains;

import com.mojang.serialization.Codec;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMMemoryModuleTypes {

    public static final DeferredRegister<MemoryModuleType<?>> MEMORY_MODULE_TYPES = DeferredRegister.create(ForgeRegistries.MEMORY_MODULE_TYPES, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<MemoryModuleType<Boolean>> SITTING = MEMORY_MODULE_TYPES.register("sitting",
            () -> new MemoryModuleType<>(Optional.of(Codec.BOOL)));

}
