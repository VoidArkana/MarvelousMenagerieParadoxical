package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;

public class CustomTallGrassBlock extends TallGrassBlock {
    final Block growsInto;

    public CustomTallGrassBlock(Properties pProperties, Block pGrowsInto) {
        super(pProperties);
        this.growsInto = pGrowsInto;
    }

    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        if (this.canSurvive(pState, pLevel, pPos) && (!(growsInto instanceof DoublePlantBlock) || (growsInto instanceof DoublePlantBlock && pLevel.isEmptyBlock(pPos.above())))) {
            DoublePlantBlock.placeAt(pLevel, growsInto.defaultBlockState(), pPos, 2);
        }

    }
}
