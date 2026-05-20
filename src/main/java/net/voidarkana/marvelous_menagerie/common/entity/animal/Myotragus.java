package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.animal.goat.GoatAi;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;

import javax.annotation.Nullable;

public class Myotragus extends Goat {
    public Myotragus(EntityType<? extends Goat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public ItemStack createHorn() {
        return super.createHorn();
    }

    protected SoundEvent getAmbientSound() {
        return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_AMBIENT : SoundEvents.GOAT_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_HURT : SoundEvents.GOAT_HURT;
    }

    protected SoundEvent getDeathSound() {
        return this.isScreamingGoat() ? SoundEvents.GOAT_SCREAMING_DEATH : SoundEvents.GOAT_DEATH;
    }

    public static boolean checkAnimalSpawnRules(EntityType<? extends Animal> pAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return CommonConfig.NATURAL_SPAWNS.get() && pLevel.getBlockState(pPos.below()).is(BlockTags.GOATS_SPAWNABLE_ON) && isBrightEnoughToSpawn(pLevel, pPos);
    }

    @Nullable
    public Myotragus getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        Myotragus goat = MMEntities.MYOTRAGUS.get().create(pLevel);
        if (goat != null) {
            boolean flag1;
            label22: {
                label21: {
                    GoatAi.initMemories(goat, pLevel.getRandom());
                    AgeableMob ageablemob = pLevel.getRandom().nextBoolean() ? this : pOtherParent;
                    if (ageablemob instanceof Myotragus) {
                        Myotragus goat1 = (Myotragus)ageablemob;
                        if (goat1.isScreamingGoat()) {
                            break label21;
                        }
                    }

                    if (!(pLevel.getRandom().nextDouble() < 0.02D)) {
                        flag1 = false;
                        break label22;
                    }
                }

                flag1 = true;
            }

            boolean flag = flag1;
            goat.setScreamingGoat(flag);
        }

        return goat;
    }
}
