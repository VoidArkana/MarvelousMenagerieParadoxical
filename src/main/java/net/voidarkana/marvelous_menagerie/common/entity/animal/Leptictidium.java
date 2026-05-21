package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.ChaseGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.IChaserAnimal;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;


public class Leptictidium extends MarvelousAnimal implements IChaserAnimal {

    private Leptictidium chasePartner;
    private int chaseTime = 0;
    private boolean chaseDriver;
    private int chaseCooldown = 0;
    private int maxChaseTime = 300;

    public final AnimationState idleTiltState = new AnimationState();
    public final AnimationState idleNoseState = new AnimationState();

    public Leptictidium(EntityType<? extends MarvelousAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private int idleTiltTimeout = this.random.nextInt(160) + 160;
    private int idleNoseTimeout = this.random.nextInt(40) + 80;

    @Override
    public int getMaxYRot() {
        return 90;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5F));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, LivingEntity.class, 6.0F, 1.15D, 1.4D,
                e -> (e instanceof Thylacine || e instanceof Wolf || e instanceof Fox || e instanceof Cat || e instanceof Player)) {
            @Override
            public void start() {
                super.start();
                playSound(MMSounds.LEPTICTIDIUM_SCARED.get(), 1, Leptictidium.this.getVoicePitch());
            }
        });

        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(1, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.25D, Ingredient.of(Items.SPIDER_EYE), false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F){
            @Override
            public boolean canUse() {
                return super.canUse() && Leptictidium.this.getNavigation().isDone();
            }
        });
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                return super.canUse() && Leptictidium.this.getNavigation().isDone();
            }
        });
        this.goalSelector.addGoal(7, new ChaseGoal(this,1200, 1.5f));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 8.0F).add(Attributes.MOVEMENT_SPEED, 0.25F);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.SPIDER_EYE);
    }

    @Override
    public void tick() {
        super.tick();

        if(chaseCooldown > 0){
            chaseCooldown--;
        }
    }

    public void setupAnimationStates() {
        super.setupAnimationStates();

        if (this.idleNoseTimeout <= 0) {
            this.idleNoseTimeout = this.random.nextInt(40) + 80;
            this.idleNoseState.start(this.tickCount);
        } else {
            --this.idleNoseTimeout;
        }

        if (this.idleTiltTimeout <= 0) {
            this.idleTiltTimeout = this.random.nextInt(160) + 160;
            this.idleTiltState.start(this.tickCount);
        } else {
            --this.idleTiltTimeout;
        }

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return MMEntities.LEPTICTIDIUM.get().create(p_146743_);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return MMSounds.LEPTICTIDIUM_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return MMSounds.LEPTICTIDIUM_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return MMSounds.LEPTICTIDIUM_AMBIENT.get();
    }


    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setSprinting(d0 >= 1.15D);
        } else {
            this.setSprinting(false);
        }
        super.customServerAiStep();
    }

    @Override
    public int getChaseTime() {
        return this.chaseTime;
    }

    @Override
    public void setChaseTime(int time) {
        this.chaseTime = time;
    }

    @Override
    public int getChaseCooldown() {
        return this.chaseCooldown;
    }

    @Override
    public void setChaseCooldown(int cooldown) {
        this.chaseCooldown = cooldown;
    }

    @Override
    public boolean isChaseDriver() {
        return this.chaseDriver;
    }

    @Override
    public void setIsChaseDriver(boolean driver) {
        this.chaseDriver = driver;
    }

    @Override
    public int getMaxChaseTime() {
        return this.maxChaseTime;
    }

    @Override
    public void setMaxChaseTime(int time) {
        this.maxChaseTime = time;
    }

    @Override
    public @Nullable PathfinderMob getChasePartner() {
        return this.chasePartner;
    }

    @Override
    public void setChasePartner(PathfinderMob partner) {
        this.chasePartner = (Leptictidium) partner;
    }
}
