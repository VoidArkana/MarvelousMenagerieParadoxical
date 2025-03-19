package net.voidarkana.marvelous_menagerie.data.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SignBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import net.voidarkana.marvelous_menagerie.util.MMTags;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class MMRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public MMRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        //Sigillaria woodset
        makePlanks(MMBlocks.SIGILLARIA_PLANKS, MMTags.Items.SIGILLARIA_LOG_ITEM).save(consumer);
        makeWood(MMBlocks.SIGILLARIA_WOOD, MMBlocks.SIGILLARIA_STEM).save(consumer);
        makeWood(MMBlocks.STRIPPED_SIGILLARIA_WOOD, MMBlocks.STRIPPED_SIGILLARIA_STEM).save(consumer);
        makeStairs(MMBlocks.SIGILLARIA_STAIRS, MMBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeStairs(MMBlocks.SIGILLARIA_MOSAIC_STAIRS, MMBlocks.SIGILLARIA_MOSAIC).save(consumer);
        makeSlab(MMBlocks.SIGILLARIA_SLAB, MMBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeSlab(MMBlocks.SIGILLARIA_MOSAIC_SLAB, MMBlocks.SIGILLARIA_MOSAIC).save(consumer);
        makeFence(MMBlocks.SIGILLARIA_FENCE, MMBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeFenceGate(MMBlocks.SIGILLARIA_FENCE_GATE, MMBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeDoor(MMBlocks.SIGILLARIA_DOOR, MMBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeTrapdoor(MMBlocks.SIGILLARIA_TRAPDOOR, MMBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeButton(MMBlocks.SIGILLARIA_BUTTON, MMBlocks.SIGILLARIA_PLANKS).save(consumer);
        makePressurePlate(MMBlocks.SIGILLARIA_PRESSURE_PLATE, MMBlocks.SIGILLARIA_PLANKS).save(consumer);

        //Paleo Toolkit
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, MMItems.PALEO_TOOLKIT.get(), 1)
                .requires(Items.IRON_PICKAXE)
                .requires(Items.BRUSH)
                .requires(Blocks.BARREL)
                .unlockedBy(getHasName(MMBlocks.SHALE.get()), has(MMTags.Items.NATURAL_FOSSILS))
                .save(consumer);

        //Paleonomicon
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, MMItems.PALEONOMICON.get(), 1)
                .requires(Items.BOOK)
                .requires(MMTags.Items.ANIMAL_FOSSILS)
                .unlockedBy(getHasName(MMItems.PALEO_TOOLKIT.get()), has(MMItems.PALEO_TOOLKIT.get()))
                .save(consumer);

        //Chronotite Pillar
        makeIngotToBlock(MMBlocks.CHRONOTITE, MMItems.CHRONOTITE);
        makeBlockToIngot(MMItems.CHRONOTITE, MMBlocks.CHRONOTITE);

        //Paleo Table
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.PALEO_TABLE.get(), 1)
                .pattern("FB")
                .pattern("PP")
                .pattern("PP")
                .define('P', ItemTags.PLANKS)
                .define('F', MMTags.Items.NATURAL_FOSSILS)
                .define('B', Items.BRUSH)
                .unlockedBy(getHasName(MMItems.SHALE_FOSSIL.get()), has(MMTags.Items.NATURAL_FOSSILS))
                .save(consumer);

        //Chronotite Pedestal
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.CHRONO_PEDESTAL.get(), 2)
                .pattern("P")
                .pattern("C")
                .define('P', Blocks.PURPLE_CARPET)
                .define('C', MMBlocks.CHRONOTITE.get())
                .unlockedBy(getHasName(MMBlocks.CHRONOTITE.get()), has(MMBlocks.CHRONOTITE.get()))
                .save(consumer);

        //Chronotite Altar
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.CHRONO_ALTAR.get(), 1)
                .pattern(" F ")
                .pattern("OCO")
                .pattern("CCC")
                .define('F', MMTags.Items.ANIMAL_FOSSILS)
                .define('O', MMItems.OPAL.get())
                .define('C', MMBlocks.CHRONOTITE.get())
                .unlockedBy(getHasName(MMBlocks.CHRONO_PEDESTAL.get()), has(MMBlocks.CHRONO_PEDESTAL.get()))
                .save(consumer);


        //Sigillaria mosaic
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.SIGILLARIA_MOSAIC.get(), 1)
                .pattern("S")
                .pattern("S")
                .define('S', MMBlocks.SIGILLARIA_SLAB.get())
                .unlockedBy(getHasName(MMBlocks.SIGILLARIA_STEM.get()), has(MMBlocks.SIGILLARIA_STEM.get()))
                .save(consumer);

        //Sigillaria sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMItems.SIGILLARIA_SIGN.get(), 3)
                .pattern("SSS")
                .pattern("SSS")
                .pattern(" # ")
                .define('S', MMBlocks.SIGILLARIA_PLANKS.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(MMBlocks.SIGILLARIA_STEM.get()), has(MMBlocks.SIGILLARIA_STEM.get()))
                .save(consumer);

        //Sigillaria hanging sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMItems.SIGILLARIA_HANGING_SIGN.get(), 6)
                .pattern("# #")
                .pattern("SSS")
                .pattern("SSS")
                .define('#', Items.CHAIN)
                .define('S', MMBlocks.STRIPPED_SIGILLARIA_STEM.get())
                .unlockedBy(getHasName(MMBlocks.SIGILLARIA_STEM.get()), has(MMBlocks.SIGILLARIA_STEM.get()))
                .save(consumer);

        //Egg Shellmet
        makeHelmet(MMItems.EGG_SHELLMET, MMItems.EGG_SHELL_FRAGMENT);
//        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MMItems.EGG_SHELLMET.get(), 1)
//                .pattern("SSS")
//                .pattern("S S")
//                .define('S', MMItems.EGG_SHELL_FRAGMENT.get())
//                .unlockedBy(getHasName(MMItems.EGG_SHELL_FRAGMENT.get()), has(MMItems.EGG_SHELL_FRAGMENT.get()))
//                .save(consumer);

        //Jumbo Omelette
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MMItems.JUMBO_OMELETTE.get(), 1)
                .requires(MMItems.BOILED_ELEPHANT_EGG.get())
                .requires(Items.CARROT)
                .requires(Ingredient.of(Items.POTATO, Items.BAKED_POTATO))
                .unlockedBy(getHasName(MMItems.CRACKED_ELEPHANT_EGG.get()), has(MMItems.CRACKED_ELEPHANT_EGG.get()))
                .save(consumer);

        //Steller Ice Cream
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, MMItems.STELLER_ICE_CREAM.get(), 3)
                .pattern(" S ")
                .pattern("SMS")
                .pattern("BBB")
                .define('S', Items.SNOWBALL)
                .define('M', MMItems.STELLER_MILK.get())
                .define('B', Items.BOWL)
                .unlockedBy(getHasName(MMItems.STELLER_MILK.get()), has(MMItems.STELLER_MILK.get()))
                .save(consumer);

        //Golden Sacabambaspis
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, MMItems.GOLDEN_SACA.get(), 1)
                .pattern("GGG")
                .pattern("GSG")
                .pattern("GGG")
                .define('G', Items.GOLD_INGOT)
                .define('S', MMItems.SACABAMBASPIS.get())
                .unlockedBy(getHasName(MMItems.SACABAMBASPIS.get()), has(MMItems.SACABAMBASPIS.get()))
                .save(consumer);

        //Mycelium from Prototaxites
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.MYCELIUM, 2)
                .pattern("PP")
                .pattern("DD")
                .define('P', MMBlocks.PROTOTAXITES.get())
                .define('D', Items.DIRT)
                .unlockedBy(getHasName(MMBlocks.PROTOTAXITES.get()), has(MMBlocks.PROTOTAXITES.get()))
                .save(consumer);

        //Prototaxites Block From Prototaxites
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.PROTOTAXITES_BLOCK.get(), 1)
                .pattern("PP")
                .pattern("PP")
                .define('P', MMBlocks.PROTOTAXITES.get())
                .unlockedBy(getHasName(MMBlocks.PROTOTAXITES.get()), has(MMBlocks.PROTOTAXITES.get()))
                .save(consumer);

        //Prototaxites From Prototaxites Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, MMBlocks.PROTOTAXITES.get(), 4)
                .requires(MMBlocks.PROTOTAXITES_BLOCK.get())
                .unlockedBy(getHasName(MMBlocks.PROTOTAXITES.get()), has(MMBlocks.PROTOTAXITES.get()))
                .save(consumer);


        //prototaxites woodset
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.PROTOTAXITES_PLANKS.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', MMBlocks.PROTOTAXITES_BLOCK.get())
                .unlockedBy(getHasName(MMBlocks.PROTOTAXITES_BLOCK.get()), has(MMBlocks.PROTOTAXITES_BLOCK.get()))
                .save(consumer);

        makeStairs(MMBlocks.PROTOTAXITES_STAIRS, MMBlocks.PROTOTAXITES_PLANKS).save(consumer);
        makeStairs(MMBlocks.PROTOTAXITES_MOSAIC_STAIRS, MMBlocks.PROTOTAXITES_MOSAIC).save(consumer);
        makeSlab(MMBlocks.PROTOTAXITES_SLAB, MMBlocks.PROTOTAXITES_PLANKS).save(consumer);
        makeSlab(MMBlocks.PROTOTAXITES_MOSAIC_SLAB, MMBlocks.PROTOTAXITES_MOSAIC).save(consumer);
        makeFence(MMBlocks.PROTOTAXITES_FENCE, MMBlocks.PROTOTAXITES_PLANKS).save(consumer);
        makeFenceGate(MMBlocks.PROTOTAXITES_FENCE_GATE, MMBlocks.PROTOTAXITES_PLANKS).save(consumer);
        makeDoor(MMBlocks.PROTOTAXITES_DOOR, MMBlocks.PROTOTAXITES_PLANKS).save(consumer);
        makeTrapdoor(MMBlocks.PROTOTAXITES_TRAPDOOR, MMBlocks.PROTOTAXITES_PLANKS).save(consumer);
        makeButton(MMBlocks.PROTOTAXITES_BUTTON, MMBlocks.PROTOTAXITES_PLANKS).save(consumer);
        makePressurePlate(MMBlocks.PROTOTAXITES_PRESSURE_PLATE, MMBlocks.PROTOTAXITES_PLANKS).save(consumer);

        //Prototaxites mosaic
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.PROTOTAXITES_MOSAIC.get(), 1)
                .pattern("S")
                .pattern("S")
                .define('S', MMBlocks.PROTOTAXITES_SLAB.get())
                .unlockedBy(getHasName(MMBlocks.PROTOTAXITES_BLOCK.get()), has(MMBlocks.PROTOTAXITES_BLOCK.get()))
                .save(consumer);

        //Prototaxites sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMItems.PROTOTAXITES_SIGN.get(), 3)
                .pattern("SSS")
                .pattern("SSS")
                .pattern(" # ")
                .define('S', MMBlocks.PROTOTAXITES_PLANKS.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(MMBlocks.PROTOTAXITES_BLOCK.get()), has(MMBlocks.PROTOTAXITES_BLOCK.get()))
                .save(consumer);

        //Prototaxites hanging sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMItems.PROTOTAXITES_HANGING_SIGN.get(), 6)
                .pattern("# #")
                .pattern("SSS")
                .pattern("SSS")
                .define('#', Items.CHAIN)
                .define('S', MMBlocks.PROTOTAXITES_BLOCK.get())
                .unlockedBy(getHasName(MMBlocks.PROTOTAXITES_BLOCK.get()), has(MMBlocks.PROTOTAXITES_BLOCK.get()))
                .save(consumer);

        //Anomalous Goggles
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MMItems.ANOMALOUS_GOGGLES.get(), 1)
                .pattern("LSL")
                .define('L', MMItems.CARIS_LENS.get())
                .define('S', MMItems.CARIS_SCUTE.get())
                .unlockedBy(getHasName(MMItems.CARIS_SCUTE.get()), has(MMItems.CARIS_SCUTE.get()))
                .unlockedBy(getHasName(MMItems.CARIS_LENS.get()), has(MMItems.CARIS_LENS.get()))
                .save(consumer);


        //Ophthalmo Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MMItems.OPHTHALMO_ARMOR.get(), 1)
                .pattern("S  ")
                .pattern("SSS")
                .pattern("S S")
                .define('S', MMItems.CARIS_SCUTE.get())
                .unlockedBy(getHasName(MMItems.CARIS_SCUTE.get()), has(MMItems.CARIS_SCUTE.get()))
                .save(consumer);

        //Magic Pastry
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, MMItems.MAGIC_ROLL.get(), 1)
                .requires(MMItems.HALLUCIGENIC_SLIME.get())
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .requires(Items.WHEAT)
                .requires(Items.MILK_BUCKET)
                .unlockedBy(getHasName(MMItems.HALLUCIGENIC_SLIME.get()), has(MMItems.HALLUCIGENIC_SLIME.get()))
                .save(consumer);
    }
    public ShapelessRecipeBuilder makePlanks(Supplier<? extends Block> plankOut, TagKey<Item> logIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, (ItemLike)plankOut.get(), 4).requires(logIn).group("planks").unlockedBy("has_log", has(logIn));
    }

    public ShapedRecipeBuilder makeDoor(Supplier<? extends Block> doorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)doorOut.get(), 3).pattern("PP").pattern("PP").pattern("PP").define('P', (ItemLike)plankIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)plankIn.get()).getPath(), has((ItemLike)plankIn.get()));
    }

    public ShapedRecipeBuilder makeTrapdoor(Supplier<? extends Block> trapdoorOut, Supplier<? extends Block> plankIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)trapdoorOut.get(), 2).pattern("PPP").pattern("PPP").define('P', (ItemLike)plankIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)plankIn.get()).getPath(), has((ItemLike)plankIn.get()));
    }

    public ShapelessRecipeBuilder makeButton(Supplier<? extends Block> buttonOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, (ItemLike)buttonOut.get()).requires((ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makePressurePlate(Supplier<? extends Block> pressurePlateOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)pressurePlateOut.get()).pattern("BB").define('B', (ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeStairs(Supplier<? extends Block> stairsOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)stairsOut.get(), 4).pattern("M  ").pattern("MM ").pattern("MMM").define('M', (ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeSlab(Supplier<? extends Block> slabOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)slabOut.get(), 6).pattern("MMM").define('M', (ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeWall(Supplier<? extends Block> wallOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)wallOut.get(), 6).pattern("MMM").pattern("MMM").define('M', (ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeFence(Supplier<? extends Block> fenceOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)fenceOut.get(), 6).pattern("M/M").pattern("M/M").define('M', (ItemLike)blockIn.get()).define('/', Tags.Items.RODS_WOODEN).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeFenceGate(Supplier<? extends Block> fenceGateOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)fenceGateOut.get()).pattern("/M/").pattern("/M/").define('M', (ItemLike)blockIn.get()).define('/', Tags.Items.RODS_WOODEN).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeBricks(Supplier<? extends Block> bricksOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)bricksOut.get(), 4).pattern("MM").pattern("MM").define('M', (ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeChiseledBricks(Supplier<? extends Block> bricksOut, Supplier<? extends Block> blockIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)bricksOut.get()).pattern("M").pattern("M").define('M', (ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeWood(Supplier<? extends Block> woodOut, Supplier<? extends Block> logIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)woodOut.get(), 3).pattern("MM").pattern("MM").define('M', (ItemLike)logIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)logIn.get()).getPath(), has((ItemLike)logIn.get()));
    }

    public ShapedRecipeBuilder makeIngotToBlock(Supplier<? extends Block> blockOut, Supplier<? extends Item> ingotIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)blockOut.get()).pattern("###").pattern("###").pattern("###").define('#', (ItemLike)ingotIn.get()).unlockedBy("has_" + ForgeRegistries.ITEMS.getKey((Item)ingotIn.get()).getPath(), has((ItemLike)ingotIn.get()));
    }

    public ShapelessRecipeBuilder makeBlockToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Block> blockIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ingotOut.get(), 9).requires((ItemLike)blockIn.get()).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)blockIn.get()).getPath(), has((ItemLike)blockIn.get()));
    }

    public ShapedRecipeBuilder makeNuggetToIngot(Supplier<? extends Item> ingotOut, Supplier<? extends Item> nuggetIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)ingotOut.get(), 1).pattern("NNN").pattern("NNN").pattern("NNN").define('N', (ItemLike)nuggetIn.get()).unlockedBy("has_" + ForgeRegistries.ITEMS.getKey((Item)nuggetIn.get()).getPath(), has((ItemLike)nuggetIn.get()));
    }

    public ShapelessRecipeBuilder makeIngotToNugget(Supplier<? extends Item> nuggetOut, Supplier<? extends Item> ingotIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, (ItemLike)nuggetOut.get(), 9).requires((ItemLike)ingotIn.get()).unlockedBy("has_" + ForgeRegistries.ITEMS.getKey((Item)ingotIn.get()).getPath(), has((ItemLike)ingotIn.get()));
    }

    public ShapedRecipeBuilder makeHelmet(Supplier<? extends Item> helmetOut, Supplier<? extends Item> materialIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, (ItemLike)helmetOut.get()).pattern("MMM").pattern("M M").define('M', (ItemLike)materialIn.get()).unlockedBy("has_" + ForgeRegistries.ITEMS.getKey((Item)materialIn.get()).getPath(), has((ItemLike)materialIn.get()));
    }

    public ShapedRecipeBuilder makeBoat(Supplier<? extends Item> boatOut, Supplier<? extends Block> planksIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, (ItemLike)boatOut.get()).pattern("P P").pattern("PPP").define('P', (ItemLike)planksIn.get()).group("boat").unlockedBy("in_water", insideOf(Blocks.WATER));
    }

    public ShapelessRecipeBuilder makeChestBoat(Supplier<? extends Item> chestBoatOut, Supplier<? extends Block> boatIn) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, (ItemLike)chestBoatOut.get()).requires((ItemLike)boatIn.get()).requires(Tags.Items.CHESTS_WOODEN).group("chest_boat").unlockedBy("has_boat", has(ItemTags.BOATS));
    }

    public ShapedRecipeBuilder makeSign(Supplier<? extends SignBlock> signOut, Supplier<? extends Block> planksIn) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, (ItemLike)signOut.get(), 3).pattern("PPP").pattern("PPP").pattern(" / ").define('P', (ItemLike)planksIn.get()).define('/', Tags.Items.RODS_WOODEN).unlockedBy("has_" + ForgeRegistries.BLOCKS.getKey((Block)planksIn.get()).getPath(), has((ItemLike)planksIn.get()));
    }
    public SingleItemRecipeBuilder stonecutting(Supplier<Block> input, ItemLike result) {
        SingleItemRecipeBuilder var10000 = SingleItemRecipeBuilder.stonecutting(Ingredient.of(new ItemLike[]{(ItemLike)input.get()}), RecipeCategory.BUILDING_BLOCKS, result);
        IForgeRegistry var10001 = ForgeRegistries.BLOCKS;
        return var10000.unlockedBy("has_" + var10001.getKey((Block)input.get()), has((ItemLike)input.get()));
    }

    public SingleItemRecipeBuilder stonecutting(Supplier<Block> input, ItemLike result, int resultAmount) {
        SingleItemRecipeBuilder var10000 = SingleItemRecipeBuilder.stonecutting(Ingredient.of(new ItemLike[]{(ItemLike)input.get()}), RecipeCategory.BUILDING_BLOCKS, result, resultAmount);
        IForgeRegistry var10001 = ForgeRegistries.BLOCKS;
        return var10000.unlockedBy("has_" + var10001.getKey((Block)input.get()), has((ItemLike)input.get()));
    }
}
