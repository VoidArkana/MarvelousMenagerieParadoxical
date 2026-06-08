package net.voidarkana.marvelous_menagerie.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.voidarkana.marvelous_menagerie.common.block.MMBlocks;

import java.util.function.Supplier;

public class LystroEggsBlock extends FourEggsBlock{

    public LystroEggsBlock(Properties pProperties, Supplier<? extends EntityType> entityType, TagKey<Block> nestBlock) {
        super(pProperties, entityType, nestBlock);
    }

    @Override
    public boolean isValidBlock(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.below()).isFaceSturdy(level, pos, Direction.UP);
    }

    @Override
    void onEggBreak(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        BlockState blockstate = MMBlocks.LYSTRO_EGG_FRAGMENTS.get().defaultBlockState();
        level.setBlock(pos, blockstate, 3);
        level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(blockstate));
    }
}
