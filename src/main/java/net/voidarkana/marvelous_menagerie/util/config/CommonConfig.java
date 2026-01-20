package net.voidarkana.marvelous_menagerie.util.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> BONEMEAL_PROTO;

    public static final ForgeConfigSpec.ConfigValue<Boolean> HALLU_FLASH;

    public static final ForgeConfigSpec.ConfigValue<Boolean> NATURAL_SPAWNS;

    public static final ForgeConfigSpec.ConfigValue<Integer> PLAINS_VILLAGE_WEIGHT;
    public static final ForgeConfigSpec.ConfigValue<Integer> SAVANNA_VILLAGE_WEIGHT;
    public static final ForgeConfigSpec.ConfigValue<Integer> SNOWY_VILLAGE_WEIGHT;
    public static final ForgeConfigSpec.ConfigValue<Integer> TAIGA_VILLAGE_WEIGHT;
    public static final ForgeConfigSpec.ConfigValue<Integer> DESERT_VILLAGE_WEIGHT;

    static {
        BUILDER.push("Configs for Marvelous Menagerie");

        BONEMEAL_PROTO = BUILDER.comment("Defines if Prototaxites can be bonemealed, turned on by default, if playing on NeoForge should set it to false as it causes crashes")
                .define("Can be bonemealed?", true);

        HALLU_FLASH = BUILDER.comment("Defines if the Hallucinating effect uses a shader that causes flashing lights. " +
                        "If this setting is set to \"false\" the effect will use a gentler shader instead.")
                .define("Flashy hallucinations?", true);


        NATURAL_SPAWNS = BUILDER.comment("Defines if mobs should naturally spawn in the world.")
                .define("Natural Dinos?", false);

        PLAINS_VILLAGE_WEIGHT = BUILDER.comment("Defines the weight of museums in plains villages. The higher the value the more common they become. " +
                        "Default value is 20 and the number must be 0 or higher.")
                .define("Plains museum weight:", 20);

        SAVANNA_VILLAGE_WEIGHT = BUILDER.comment("Defines the weight of museums in savanna villages. The higher the value the more common they become. " +
                        "Default value is 25 and the number must be 0 or higher.")
                .define("Savanna museum weight:", 25);

        TAIGA_VILLAGE_WEIGHT = BUILDER.comment("Defines the weight of museums in taiga villages. The higher the value the more common they become. " +
                        "Default value is 20 and the number must be 0 or higher.")
                .define("Taiga museum weight:", 20);

        SNOWY_VILLAGE_WEIGHT = BUILDER.comment("Defines the weight of museums in snowy villages. The higher the value the more common they become. " +
                        "Default value is 20 and the number must be 0 or higher.")
                .define("Snowy museum weight:", 20);

        DESERT_VILLAGE_WEIGHT = BUILDER.comment("Defines the weight of museums in desert villages. The higher the value the more common they become. " +
                        "Default value is 30 and the number must be 0 or higher.")
                .define("Desert museum weight:", 30);

        BUILDER.pop();
        SPEC= BUILDER.build();
    }
}
