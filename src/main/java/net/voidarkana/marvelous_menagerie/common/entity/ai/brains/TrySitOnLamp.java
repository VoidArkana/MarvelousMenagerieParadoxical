package net.voidarkana.marvelous_menagerie.common.entity.ai.brains;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.declarative.BehaviorBuilder;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.entity.base.ISittingAnimal;

public class TrySitOnLamp {
    public static BehaviorControl<LivingEntity> create() {
        return BehaviorBuilder.create((livingEntityInstance) -> {
            return livingEntityInstance.group(livingEntityInstance.absent(MMMemoryModuleTypes.SITTING.get()),
                    livingEntityInstance.present(MemoryModuleType.WALK_TARGET)).apply(livingEntityInstance,
                    (attackTargetMemoryAccessor, walkTargetMemoryAccessor) -> {
                return (level, entity, l) -> {
                    BlockPos blockpos = entity.blockPosition().below();

                    BlockState state = level.getBlockState(blockpos);
                    if (state.is(Blocks.REDSTONE_LAMP) && entity instanceof ISittingAnimal animal) {
                        BlockPos blockpos2 = blockpos.above();
                        if (level.getBlockState(blockpos2).isAir() && state.getValue(RedstoneLampBlock.LIT)) {
                            if (!animal.isSitting()){
                                animal.sitDown();
                            }
                            return true;
                        }
                    }

                    return false;
                };
            });
        });
    }
}
