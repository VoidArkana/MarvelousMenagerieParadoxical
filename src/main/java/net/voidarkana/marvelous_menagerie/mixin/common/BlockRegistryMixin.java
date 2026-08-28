package net.voidarkana.marvelous_menagerie.mixin.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TorchflowerCropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlockRegistryMixin {

    @ModifyArg(method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/FlowerBlock;<init>(Lnet/minecraft/world/effect/MobEffect;ILnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V"),
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=torchflower"), to = @At(value = "CONSTANT", args = "stringValue=poppy")))
    private static BlockBehaviour.Properties torchflowerBlockLightLevel(BlockBehaviour.Properties properties) {
        return properties.lightLevel(blockState -> 15);
    }

    @ModifyArg(method = "flowerPot(Lnet/minecraft/world/level/block/Block;[Lnet/minecraft/world/flag/FeatureFlag;)Lnet/minecraft/world/level/block/FlowerPotBlock;",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/FlowerPotBlock;<init>(Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V"))
    private static BlockBehaviour.Properties torchflowerPotLightLevel(Block block, BlockBehaviour.Properties properties) {
        return block.equals(Blocks.TORCHFLOWER) ? properties.lightLevel(blockState -> 12) : properties;
    }

    @ModifyArg(method = "<clinit>",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/TorchflowerCropBlock;<init>(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V"))
    private static BlockBehaviour.Properties torchflowerCropBlockLightLevel(BlockBehaviour.Properties properties) {
        return properties.lightLevel(blockState -> blockState.getValue(TorchflowerCropBlock.AGE) * 4);
    }
}
