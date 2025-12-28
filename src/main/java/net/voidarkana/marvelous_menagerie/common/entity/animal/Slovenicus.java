package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ai.FishBreedGoal;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.AbstractBasicFish;
import net.voidarkana.marvelous_menagerie.common.entity.animal.base.BreedableWaterAnimal;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;
import org.jetbrains.annotations.Nullable;

public class Slovenicus extends AbstractBasicFish {

    public Slovenicus(EntityType<? extends BreedableWaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new FishBreedGoal(this, 1.5));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1, 50));
        this.goalSelector.addGoal(4 ,new SlovenicusGoToWaterPlant());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5F);
    }

    @Nullable
    @Override
    public BreedableWaterAnimal getBreedOffspring(ServerLevel pLevel, BreedableWaterAnimal pOtherParent) {
        Slovenicus baby = MMEntities.SLOVENICUS.get().create(pLevel);
        baby.setFromBucket(true);
        return baby;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(MMItems.SLOVENICUS_BUCKET.get());
    }

    @Override
    public boolean canFlop() {
        return false;
    }

    @Override
    public boolean floatsUp() {
        return false;
    }

    class SlovenicusGoToWaterPlant extends MoveToBlockGoal{

        public SlovenicusGoToWaterPlant() {
            super(Slovenicus.this, 1, 10);
        }

        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(Blocks.SEAGRASS) || pLevel.getBlockState(pPos).is(Blocks.TALL_SEAGRASS);
        }
    }
}
