package net.voidarkana.marvelous_menagerie.client.screen;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameMenu;

import java.util.function.Supplier;

public class MMMenuTypes {

    public static final DeferredRegister<MenuType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MarvelousMenagerie.MODID);

//    public static final RegistryObject<MenuType<ElephantBirdMenu>> ELEPHANT_BIRD_CONTAINER = MENUS.register("elephant_bird_container",
//            () -> new MenuType<>(ElephantBirdMenu::new, FeatureFlags.REGISTRY.allFlags()));

    public static final Supplier<MenuType<FossilMinigameMenu>> FOSSIL_MENU = DEF_REG.register("fossil_menu",
        () -> new MenuType<FossilMinigameMenu>(FossilMinigameMenu::new, FeatureFlags.DEFAULT_FLAGS));

    public static void register(IEventBus eventBus) {
        DEF_REG.register(eventBus);
    }
}
