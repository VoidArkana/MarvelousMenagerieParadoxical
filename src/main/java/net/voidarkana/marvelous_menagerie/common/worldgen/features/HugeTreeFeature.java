package net.voidarkana.marvelous_menagerie.common.worldgen.features;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.shapes.BitSetDiscreteVoxelShape;
import net.minecraft.world.phys.shapes.DiscreteVoxelShape;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.HugeTreeConfiguration;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class HugeTreeFeature extends Feature<HugeTreeConfiguration> {
    private static final int BLOCK_UPDATE_FLAGS = 19;

    public HugeTreeFeature(Codec<HugeTreeConfiguration> pCodec) {
        super(pCodec);
    }

    private static boolean isVine(LevelSimulatedReader pLevel, BlockPos pPos) {
        return pLevel.isStateAtPosition(pPos, (p_225299_) -> {
            return p_225299_.is(Blocks.VINE);
        });
    }

    public static boolean isAirOrLeaves(LevelSimulatedReader pLevel, BlockPos pPos) {
        return pLevel.isStateAtPosition(pPos, (p_284924_) -> {
            return p_284924_.isAir() || p_284924_.is(BlockTags.LEAVES);
        });
    }

    private static void setBlockKnownShape(LevelWriter pLevel, BlockPos pPos, BlockState pState) {
        pLevel.setBlock(pPos, pState, 19);
    }

    public static boolean validTreePos(LevelSimulatedReader pLevel, BlockPos pPos) {
        return pLevel.isStateAtPosition(pPos, (p_284925_) -> {
            return p_284925_.isAir() || p_284925_.is(BlockTags.REPLACEABLE_BY_TREES);
        });
    }

    private boolean doPlace(WorldGenLevel pLevel, RandomSource pRandom, BlockPos pPos, BiConsumer<BlockPos, BlockState> pRootBlockSetter, BiConsumer<BlockPos, BlockState> pTrunkBlockSetter, FoliagePlacer.FoliageSetter pFoliageBlockSetter, HugeTreeConfiguration pConfig) {
        int i = pConfig.trunkPlacer.getTreeHeight(pRandom);
        int j = pConfig.foliagePlacer.foliageHeight(pRandom, i, pConfig);
        int k = i - j;
        int l = pConfig.foliagePlacer.foliageRadius(pRandom, k);
        BlockPos blockpos = pConfig.rootPlacer.map((p_225286_) -> {
            return p_225286_.getTrunkOrigin(pPos, pRandom);
        }).orElse(pPos);
        int i1 = Math.min(pPos.getY(), blockpos.getY());
        int j1 = Math.max(pPos.getY(), blockpos.getY()) + i + 1;
        if (i1 >= pLevel.getMinBuildHeight() + 1 && j1 <= pLevel.getMaxBuildHeight()) {
            OptionalInt optionalint = pConfig.minimumSize.minClippedHeight();
            int k1 = this.getMaxFreeTreeHeight(pLevel, i, blockpos, pConfig);
            if (k1 >= i || !optionalint.isEmpty() && k1 >= optionalint.getAsInt()) {
                if (pConfig.rootPlacer.isPresent() && !pConfig.rootPlacer.get().placeRoots(pLevel, pRootBlockSetter, pRandom, pPos, blockpos, pConfig)) {
                    return false;
                } else {
                    List<FoliagePlacer.FoliageAttachment> list = pConfig.trunkPlacer.placeTrunk(pLevel, pTrunkBlockSetter, pRandom, k1, blockpos, pConfig);

                    list.forEach((p_272582_) -> {
                        pConfig.foliagePlacer.createFoliage(pLevel, pFoliageBlockSetter, pRandom, pConfig, k1, p_272582_, j, l);
                    });
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    protected void distributeGround(FeaturePlaceContext<HugeTreeConfiguration> pContext, WorldGenLevel pLevel, HugeTreeConfiguration pConfig, RandomSource pRandom, Set<BlockPos> pPossiblePositions, int pXRadius, int pZRadius) {
        for(BlockPos blockpos : pPossiblePositions) {
            if (pRandom.nextFloat() < 0.25f) {
                this.placeGround(pLevel, pConfig, pContext.chunkGenerator(), pRandom, blockpos);
            }
        }
    }

    protected boolean placeGround(WorldGenLevel pLevel, HugeTreeConfiguration pConfig, ChunkGenerator pChunkGenerator, RandomSource pRandom, BlockPos pPos) {
        return pConfig.ground_feature.value().place(pLevel, pChunkGenerator, pRandom, pPos);
    }

    private static boolean isDirt(LevelSimulatedReader pLevel, BlockPos pPos) {
        return pLevel.isStateAtPosition(pPos, (p_70304_) -> {
            return Feature.isDirt(p_70304_) && !p_70304_.is(Blocks.GRASS_BLOCK) && !p_70304_.is(Blocks.MYCELIUM);
        });
    }

    private int getMaxFreeTreeHeight(LevelSimulatedReader pLevel, int pTrunkHeight, BlockPos pTopPosition, HugeTreeConfiguration pConfig) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int i = 0; i <= pTrunkHeight + 1; ++i) {
            int j = pConfig.minimumSize.getSizeAtHeight(pTrunkHeight, i);

            for(int k = -j; k <= j; ++k) {
                for(int l = -j; l <= j; ++l) {
                    blockpos$mutableblockpos.setWithOffset(pTopPosition, k, i, l);
                    if (!pConfig.trunkPlacer.isFree(pLevel, blockpos$mutableblockpos) || !pConfig.ignoreVines && isVine(pLevel, blockpos$mutableblockpos)) {
                        return i - 2;
                    }
                }
            }
        }

        return pTrunkHeight;
    }

    protected void setBlock(LevelWriter pLevel, BlockPos pPos, BlockState pState) {
        setBlockKnownShape(pLevel, pPos, pState);
    }

    /**
     * Places the given feature at the given location.
     * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
     * that they can safely generate into.
     * @param pContext A context object with a reference to the level and the position the feature is being placed at
     */
    public final boolean place(FeaturePlaceContext<HugeTreeConfiguration> pContext) {
        final WorldGenLevel worldgenlevel = pContext.level();
        RandomSource randomsource = pContext.random();
        BlockPos blockpos = pContext.origin();
        HugeTreeConfiguration hugeTreeConfiguration = pContext.config();
        Set<BlockPos> set = Sets.newHashSet();
        Set<BlockPos> set1 = Sets.newHashSet();
        final Set<BlockPos> set2 = Sets.newHashSet();
        Set<BlockPos> set3 = Sets.newHashSet();
        BiConsumer<BlockPos, BlockState> biconsumer = (p_160555_, p_160556_) -> {
            set.add(p_160555_.immutable());
            worldgenlevel.setBlock(p_160555_, p_160556_, 19);
        };
        BiConsumer<BlockPos, BlockState> biconsumer1 = (p_160548_, p_160549_) -> {
            set1.add(p_160548_.immutable());
            worldgenlevel.setBlock(p_160548_, p_160549_, 19);
        };
        FoliagePlacer.FoliageSetter foliageplacer$foliagesetter = new FoliagePlacer.FoliageSetter() {
            public void set(BlockPos p_272825_, BlockState p_273311_) {
                set2.add(p_272825_.immutable());
                worldgenlevel.setBlock(p_272825_, p_273311_, 19);
            }

            public boolean isSet(BlockPos p_272999_) {
                return set2.contains(p_272999_);
            }
        };
        BiConsumer<BlockPos, BlockState> biconsumer2 = (p_160543_, p_160544_) -> {
            set3.add(p_160543_.immutable());
            worldgenlevel.setBlock(p_160543_, p_160544_, 19);
        };
        boolean flag = this.doPlace(worldgenlevel, randomsource, blockpos, biconsumer, biconsumer1, foliageplacer$foliagesetter, hugeTreeConfiguration);
        if (flag && (!set1.isEmpty() || !set2.isEmpty())) {
            if (!hugeTreeConfiguration.decorators.isEmpty()) {
                TreeDecorator.Context treedecorator$context = new TreeDecorator.Context(worldgenlevel, biconsumer2, randomsource, set1, set2, set);
                hugeTreeConfiguration.decorators.forEach((p_225282_) -> {
                    p_225282_.place(treedecorator$context);
                });
            }

            int i = hugeTreeConfiguration.xzRadius.sample(randomsource) + 1;
            int j = hugeTreeConfiguration.xzRadius.sample(randomsource) + 1;

            Predicate<BlockState> predicate = (p_204782_) -> {
                return p_204782_.is(BlockTags.DIRT);
            };

            Set<BlockPos> pSet = this.placeGroundPatch(worldgenlevel, randomsource, blockpos, predicate, i, j, hugeTreeConfiguration);

            this.distributeGround(pContext, worldgenlevel, hugeTreeConfiguration, randomsource, pSet, i, j);

            return BoundingBox.encapsulatingPositions(Iterables.concat(set, set1, set2, set3)).map((p_225270_) -> {
                DiscreteVoxelShape discretevoxelshape = updateLeaves(worldgenlevel, p_225270_, set1, set3, set);
                StructureTemplate.updateShapeAtEdge(worldgenlevel, 3, discretevoxelshape, p_225270_.minX(), p_225270_.minY(), p_225270_.minZ());
                return true;
            }).orElse(false);
        } else {
            return false;
        }
    }

    protected Set<BlockPos> placeGroundPatch(WorldGenLevel pLevel, RandomSource pRandom, BlockPos pPos, Predicate<BlockState> pState, int pXRadius, int pZRadius, HugeTreeConfiguration configuration) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = pPos.mutable();
        BlockPos.MutableBlockPos blockpos$mutableblockpos1 = blockpos$mutableblockpos.mutable();
        Direction direction = CaveSurface.FLOOR.getDirection();
        Direction direction1 = direction.getOpposite();
        Set<BlockPos> set = new HashSet<>();

        for(int i = -pXRadius; i <= pXRadius; ++i) {
            boolean flag = i == -pXRadius || i == pXRadius;

            for(int j = -pZRadius; j <= pZRadius; ++j) {
                boolean flag1 = j == -pZRadius || j == pZRadius;
                boolean flag2 = flag || flag1;
                boolean flag3 = flag && flag1;
                boolean flag4 = flag2 && !flag3;
                if (!flag3 && (!flag4)) {
                    blockpos$mutableblockpos.setWithOffset(pPos, i, 0, j);


                    blockpos$mutableblockpos1.setWithOffset(blockpos$mutableblockpos, direction);
                    BlockState blockstate = pLevel.getBlockState(blockpos$mutableblockpos1);
                    if (pLevel.isEmptyBlock(blockpos$mutableblockpos) && blockstate.isFaceSturdy(pLevel, blockpos$mutableblockpos1,
                            direction1)) {
                        BlockPos blockpos = blockpos$mutableblockpos1.immutable();
                        boolean flag5 = this.placeGround(pLevel, configuration, pState, pRandom, blockpos$mutableblockpos1, 3);
                        if (flag5) {
                            set.add(blockpos);
                        }
                    }
                }
            }
        }

        return set;
    }

    protected boolean placeGround(WorldGenLevel pLevel, HugeTreeConfiguration pConfig, Predicate<BlockState> pReplaceableblocks, RandomSource pRandom, BlockPos.MutableBlockPos pMutablePos, int pMaxDistance) {
        for(int i = 0; i < pMaxDistance; ++i) {
            BlockState blockstate1 = pLevel.getBlockState(pMutablePos);
            if (!pReplaceableblocks.test(blockstate1)) {
                return i != 0;
            }
        }

        return true;
    }

    private static DiscreteVoxelShape updateLeaves(LevelAccessor pLevel, BoundingBox pBox, Set<BlockPos> pRootPositions, Set<BlockPos> pTrunkPositions, Set<BlockPos> pFoliagePositions) {
        DiscreteVoxelShape discretevoxelshape = new BitSetDiscreteVoxelShape(pBox.getXSpan(), pBox.getYSpan(), pBox.getZSpan());
        int i = 7;
        List<Set<BlockPos>> list = Lists.newArrayList();

        for(int j = 0; j < 7; ++j) {
            list.add(Sets.newHashSet());
        }

        for(BlockPos blockpos : Lists.newArrayList(Sets.union(pTrunkPositions, pFoliagePositions))) {
            if (pBox.isInside(blockpos)) {
                discretevoxelshape.fill(blockpos.getX() - pBox.minX(), blockpos.getY() - pBox.minY(), blockpos.getZ() - pBox.minZ());
            }
        }

        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int k1 = 0;
        list.get(0).addAll(pRootPositions);

        while(true) {
            while(k1 >= 7 || !list.get(k1).isEmpty()) {
                if (k1 >= 7) {
                    return discretevoxelshape;
                }

                Iterator<BlockPos> iterator = list.get(k1).iterator();
                BlockPos blockpos1 = iterator.next();
                iterator.remove();
                if (pBox.isInside(blockpos1)) {
                    if (k1 != 0) {
                        BlockState blockstate = pLevel.getBlockState(blockpos1);
                        setBlockKnownShape(pLevel, blockpos1, blockstate.setValue(BlockStateProperties.DISTANCE, Integer.valueOf(k1)));
                    }

                    discretevoxelshape.fill(blockpos1.getX() - pBox.minX(), blockpos1.getY() - pBox.minY(), blockpos1.getZ() - pBox.minZ());

                    for(Direction direction : Direction.values()) {
                        blockpos$mutableblockpos.setWithOffset(blockpos1, direction);
                        if (pBox.isInside(blockpos$mutableblockpos)) {
                            int k = blockpos$mutableblockpos.getX() - pBox.minX();
                            int l = blockpos$mutableblockpos.getY() - pBox.minY();
                            int i1 = blockpos$mutableblockpos.getZ() - pBox.minZ();
                            if (!discretevoxelshape.isFull(k, l, i1)) {
                                BlockState blockstate1 = pLevel.getBlockState(blockpos$mutableblockpos);
                                OptionalInt optionalint = LeavesBlock.getOptionalDistanceAt(blockstate1);
                                if (!optionalint.isEmpty()) {
                                    int j1 = Math.min(optionalint.getAsInt(), k1 + 1);
                                    if (j1 < 7) {
                                        list.get(j1).add(blockpos$mutableblockpos.immutable());
                                        k1 = Math.min(k1, j1);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            ++k1;
        }
    }
}
