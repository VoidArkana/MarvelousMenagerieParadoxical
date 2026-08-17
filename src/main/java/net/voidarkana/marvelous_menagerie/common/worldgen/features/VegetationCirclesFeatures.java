package net.voidarkana.marvelous_menagerie.common.worldgen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class VegetationCirclesFeatures  extends Feature<VegetationPatchConfiguration> {

    public VegetationCirclesFeatures(Codec<VegetationPatchConfiguration> pCodec) {
        super(pCodec);
    }

    public boolean place(FeaturePlaceContext<VegetationPatchConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        VegetationPatchConfiguration vegetationpatchconfiguration = pContext.config();
        RandomSource randomsource = pContext.random();
        BlockPos blockpos = pContext.origin();
        int i = vegetationpatchconfiguration.xzRadius.sample(randomsource) + 1;
        int j = vegetationpatchconfiguration.xzRadius.sample(randomsource) + 1;
        Set<BlockPos> set = this.replaceGround(vegetationpatchconfiguration, randomsource, worldgenlevel, blockpos);
        this.distributeVegetation(pContext, worldgenlevel, vegetationpatchconfiguration, randomsource, set, i, j);
        return !set.isEmpty();
    }


    protected void distributeVegetation(FeaturePlaceContext<VegetationPatchConfiguration> pContext, WorldGenLevel pLevel, VegetationPatchConfiguration pConfig, RandomSource pRandom, Set<BlockPos> pPossiblePositions, int pXRadius, int pZRadius) {
        for(BlockPos blockpos : pPossiblePositions) {
            if (pConfig.vegetationChance > 0.0F && pRandom.nextFloat() < pConfig.vegetationChance && pLevel.isStateAtPosition(blockpos.relative(pConfig.surface.getDirection().getOpposite()), BlockBehaviour.BlockStateBase::isAir)) {
                this.placeVegetation(pLevel, pConfig, pContext.chunkGenerator(), pRandom, blockpos);
            }
        }
    }

    protected boolean placeVegetation(WorldGenLevel pLevel, VegetationPatchConfiguration pConfig, ChunkGenerator pChunkGenerator, RandomSource pRandom, BlockPos pPos) {
        return pConfig.vegetationFeature.value().place(pLevel, pChunkGenerator, pRandom, pPos.relative(pConfig.surface.getDirection().getOpposite()));
    }

    public Set<BlockPos> replaceGround(VegetationPatchConfiguration config, RandomSource pRandom, WorldGenLevel level, BlockPos pos) {

        Set<BlockPos> set = new HashSet<>();
        this.placeCircle(config, pRandom, level, pos.west().north());
        this.placeCircle(config, pRandom, level, pos.east(2).north());
        this.placeCircle(config, pRandom, level, pos.west().south(2));
        this.placeCircle(config, pRandom, level, pos.east(2).south(2));

        for(int j = 0; j < 6; ++j) {
            set.addAll(this.placeCircle(config, pRandom, level, pos.offset(-3 + config.xzRadius.sample(pRandom),
                    0, -3 + config.xzRadius.sample(pRandom))));
        }

        return set;
    }

    private Set<BlockPos> placeCircle(VegetationPatchConfiguration config, RandomSource pRandom, WorldGenLevel level, BlockPos pPos) {
        Set<BlockPos> set = new HashSet<>();
        BlockState state = config.groundState.getState(pRandom, pPos);

        int m = config.depth.sample(pRandom);
        int l = m + (config.extraBottomBlockChance > 0.0F && pRandom.nextFloat() < config.extraBottomBlockChance ? 1 : 0);

        for (int k = m; k > -l; k--){
            for(int i = -2; i <= 2; ++i) {
                for(int j = -2; j <= 2; ++j) {
                    if (Math.abs(i) != 2 || Math.abs(j) != 2) {
                        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();

                        for(int k1 = 0; level.isStateAtPosition(blockpos$mutableblockpos, BlockBehaviour.BlockStateBase::isAir)
                                && k1 < config.verticalRange; ++k1) {
                            blockpos$mutableblockpos.move(config.surface.getDirection());
                        }

                        for(int i1 = 0; level.isStateAtPosition(blockpos$mutableblockpos, (p_284926_) -> {
                            return !p_284926_.isAir();
                        }) && i1 < config.verticalRange; ++i1) {
                            blockpos$mutableblockpos.move(config.surface.getDirection().getOpposite());
                        }

                        set.addAll(this.placeGroundAt(config, pRandom, level, blockpos$mutableblockpos.offset(i, k, j), state));
                    }
                }
            }
        }

        return set;
    }

    private Set<BlockPos> placeGroundAt(VegetationPatchConfiguration config, RandomSource pRandom, WorldGenLevel level, BlockPos blockpos, BlockState state) {
        Set<BlockPos> set = new HashSet<>();


        for(int i = 2; i >= -3; --i) {
            BlockPos pPos = blockpos.above(i);
            if (Feature.isGrassOrDirt(level, pPos)) {
                set.add(pPos);
                this.setBlock(level, pPos, net.minecraftforge.event.ForgeEventFactory.alterGround(level, pRandom, blockpos, state));
                break;
            }

            if (!(level.isStateAtPosition(pPos, BlockBehaviour.BlockStateBase::isAir)) && i < 0) {
                break;
            }
        }

        return set;
    }

}
