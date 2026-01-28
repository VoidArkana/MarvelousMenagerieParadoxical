package net.voidarkana.marvelous_menagerie.common.entity.animal;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.MMSounds;
import net.voidarkana.marvelous_menagerie.common.entity.MMEntities;
import net.voidarkana.marvelous_menagerie.common.entity.ai.AnimatedAttackGoal;
import net.voidarkana.marvelous_menagerie.common.entity.base.IAnimatedAttacker;
import net.voidarkana.marvelous_menagerie.common.entity.base.MarvelousAnimal;
import net.voidarkana.marvelous_menagerie.util.MMTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Thylacine extends MarvelousAnimal implements IAnimatedAttacker {

    public final AnimationState howlAnimationState = new AnimationState();
    public final AnimationState yawnAnimationState = new AnimationState();
    public final AnimationState attackAnimationState1 = new AnimationState();
    public final AnimationState attackAnimationState2 = new AnimationState();
    public int attackAnimationTimeout;
    public int howlAnimationTimeout;
    public int yawnAnimationTimeout;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.RABBIT, Items.RABBIT_FOOT, Items.COOKED_RABBIT);

    public Thylacine(EntityType<? extends MarvelousAnimal> entityType, Level level) {
        super(entityType, level);
    }

    public Player playerWhoFedIt;

    private static final EntityDataAccessor<Integer> HOWLING_TIME = SynchedEntityData.defineId(Thylacine.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> YAWNING_TIME = SynchedEntityData.defineId(Thylacine.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HANDKERCHIEF_COLOR = SynchedEntityData.defineId(Thylacine.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> WOOL_ITEM = SynchedEntityData.defineId(Thylacine.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Thylacine.class, EntityDataSerializers.BOOLEAN);

    public int prevHowlTime;
    public int prevYawnTime;
    public int howlTickDuration = 80;

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 2);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(1, new AnimatedAttackGoal(this, 1.25D, true, 7, 3));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Leptictidium.class, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Rabbit.class, false));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));

        super.registerGoals();
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return FOOD_ITEMS.test(pStack);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOWLING_TIME, 0);
        this.entityData.define(YAWNING_TIME, 0);
        this.entityData.define(HANDKERCHIEF_COLOR, 0);
        this.getEntityData().define(WOOL_ITEM, ItemStack.EMPTY);
        this.entityData.define(IS_ATTACKING, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("handkerchiefColor", this.getHandkerchiefColor());

        if (!this.getWoolItem().isEmpty()) {
            compound.put("woolItem", this.getWoolItem().save(new CompoundTag()));
        }
        compound.putBoolean("IsAttacking", this.isAttacking());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        this.setHandkerchiefColor(compound.getInt("handkerchiefColor"));
        CompoundTag compoundtag = compound.getCompound("woolItem");
        if (compoundtag != null && !compoundtag.isEmpty()) {
            ItemStack itemstack = ItemStack.of(compoundtag);
            this.setWoolItem(itemstack);
        }
    }

    //wool item
    public ItemStack getWoolItem() {
        return this.getEntityData().get(WOOL_ITEM);
    }

    public void setWoolItem(ItemStack pStack) {
        if (!pStack.isEmpty()) {
            pStack = pStack.copyWithCount(1);
        }

        this.getEntityData().set(WOOL_ITEM, pStack);
    }

    //handkerchief
    public int getHandkerchiefColor(){
        return this.entityData.get(HANDKERCHIEF_COLOR);
    }

    public void setHandkerchiefColor(int color){
        this.entityData.set(HANDKERCHIEF_COLOR, color);
    }

    public boolean hasHandkerchief(){
        return this.getWoolItem() != ItemStack.EMPTY;
    }

    //howling
    public int getHowlingTime(){
        return this.entityData.get(HOWLING_TIME);}

    public void setHowlingTime(int howlingTicksTime){
        this.entityData.set(HOWLING_TIME, howlingTicksTime);}

    public boolean isHowling() {
        return this.getHowlingTime()>0;
    }

    //yawning
    public int getYawningTime(){
        return this.entityData.get(YAWNING_TIME);}

    public void setYawningTime(int yawningTicksTime){
        this.entityData.set(YAWNING_TIME, yawningTicksTime);}

    public boolean isYawning() {
        return this.getYawningTime()>0;
    }

    public void travel(Vec3 pTravelVector) {
        if (this.isHowling() || this.isYawning()) {
            if (this.getNavigation().getPath() != null) {
                this.getNavigation().stop();
            }
            pTravelVector = Vec3.ZERO;
            super.travel(pTravelVector);
        } else {
            super.travel(pTravelVector);
        }
    }

    //custom name
    public boolean isMetro() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && s.toLowerCase().contains("metropolitan");
    }

    public boolean isEndling() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && (s.toLowerCase().contains("benjamin") || s.toLowerCase().contains("ben") || s.toLowerCase().contains("hobart"));
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.hasHandkerchief()) {
            this.spawnAtLocation(this.getWoolItem());
        }
    }

    //interactions
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult type = super.mobInteract(player, hand);
        if ((itemstack.is(Items.CHICKEN) || itemstack.is(Items.RABBIT)
                || itemstack.is(Items.COOKED_CHICKEN) || itemstack.is(Items.COOKED_RABBIT))
                && !this.isHowling() && this.onGround() && !this.isYawning()) {
            this.usePlayerItem(player, hand, itemstack);
            this.setHowlingTime(80);
            this.playerWhoFedIt = player;
            return InteractionResult.SUCCESS;

        } else if (itemstack.is(ItemTags.WOOL_CARPETS) || itemstack.is(MMTags.Items.DYE_DEPOT_WOOL_ITEM)){

            if (this.hasHandkerchief()){
                this.spawnAtLocation(this.getWoolItem());
            }

            if (itemstack.is(Items.RED_CARPET)){ this.setHandkerchiefColor(1); }
            else if (itemstack.is(Items.ORANGE_CARPET)){this.setHandkerchiefColor(2);}
            else if (itemstack.is(Items.YELLOW_CARPET)){this.setHandkerchiefColor(3);}
            else if (itemstack.is(Items.LIME_CARPET)){this.setHandkerchiefColor(4);}
            else if (itemstack.is(Items.GREEN_CARPET)){this.setHandkerchiefColor(5);}
            else if (itemstack.is(Items.CYAN_CARPET)){this.setHandkerchiefColor(6);}
            else if (itemstack.is(Items.LIGHT_BLUE_CARPET)){this.setHandkerchiefColor(7);}
            else if (itemstack.is(Items.BLUE_CARPET)){this.setHandkerchiefColor(8);}
            else if (itemstack.is(Items.PURPLE_CARPET)){this.setHandkerchiefColor(9);}
            else if (itemstack.is(Items.MAGENTA_CARPET)){this.setHandkerchiefColor(10);}
            else if (itemstack.is(Items.PINK_CARPET)){this.setHandkerchiefColor(11);}
            else if (itemstack.is(Items.BROWN_CARPET)){this.setHandkerchiefColor(12);}
            else if (itemstack.is(Items.BLACK_CARPET)){this.setHandkerchiefColor(13);}
            else if (itemstack.is(Items.GRAY_CARPET)){this.setHandkerchiefColor(14);}
            else if (itemstack.is(Items.LIGHT_GRAY_CARPET)){this.setHandkerchiefColor(15);}

            else if (itemstack.is(MMTags.Items.DYE_DEPOT_AMBER_WOOL_ITEM)){ this.setHandkerchiefColor(16);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_AQUA_WOOL_ITEM )){this.setHandkerchiefColor(17);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_BEIGE_WOOL_ITEM )){this.setHandkerchiefColor(18);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_CORAL_WOOL_ITEM )){this.setHandkerchiefColor(19);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_FOREST_WOOL_ITEM )){this.setHandkerchiefColor(20);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_GINGER_WOOL_ITEM )){this.setHandkerchiefColor(21);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_INDIGO_WOOL_ITEM )){this.setHandkerchiefColor(22);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_MAROON_WOOL_ITEM )){this.setHandkerchiefColor(23);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_MINT_WOOL_ITEM )){this.setHandkerchiefColor(24);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_NAVY_WOOL_ITEM )){this.setHandkerchiefColor(25);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_OLIVE_WOOL_ITEM )){this.setHandkerchiefColor(26);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_ROSE_WOOL_ITEM )){this.setHandkerchiefColor(27);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_SLATE_WOOL_ITEM )){this.setHandkerchiefColor(28);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_TAN_WOOL_ITEM )){this.setHandkerchiefColor(29);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_TEAL_WOOL_ITEM )){this.setHandkerchiefColor(30);}
            else if (itemstack.is(MMTags.Items.DYE_DEPOT_VERDANT_WOOL_ITEM )){this.setHandkerchiefColor(31);}
            else {this.setHandkerchiefColor(0);}

            this.playSound(SoundEvents.LLAMA_SWAG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

            this.setWoolItem(itemstack);

            this.usePlayerItem(player, hand, itemstack);

            return InteractionResult.SUCCESS;

        } else if (itemstack.is(Items.SHEARS) && this.hasHandkerchief()){

            this.spawnAtLocation(this.getWoolItem());

            this.setWoolItem(ItemStack.EMPTY);

            this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

            return InteractionResult.SUCCESS;

        } else {
            return type;
        }

    }

    private void alertThreats() {
        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(24, 12, 24));
        for (LivingEntity target : list) {
            if(!(target instanceof Thylacine) && (target.getType().is(MMTags.EntityTypes.THYLA_ALERT_TARGET)
                    || target instanceof Monster || target instanceof NeutralMob)){
                if (!target.isAlliedTo(this.playerWhoFedIt)){
                    target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60));
                }
            }
        }
    }


    public void setupAnimationStates() {
        super.setupAnimationStates();

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 10;

            if (this.getRandom().nextBoolean())
                attackAnimationState2.start(this.tickCount);
            else
                attackAnimationState1.start(this.tickCount);

        } else {
            --this.attackAnimationTimeout;
        }

        if(this.isHowling() && howlAnimationTimeout <= 0) {
            howlAnimationTimeout = 80;
            howlAnimationState.start(this.tickCount);
        } else {
            --this.howlAnimationTimeout;
        }

        if(this.isYawning() && yawnAnimationTimeout <= 0) {
            yawnAnimationTimeout = 60;
            yawnAnimationState.start(this.tickCount);
        } else {
            --this.yawnAnimationTimeout;
        }
    }

    public void tick (){
        super.tick();

        //handles howling
        if (this.isHowling()){
            this.getNavigation().stop();
            this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);

            if (this.getHowlingTime()==65 && !this.level().isClientSide){
                this.playSound(MMSounds.THYLACINE_ALERT.get());
            }
            if (this.getHowlingTime()==21 && !this.level().isClientSide){
                this.playSound(SoundEvents.BELL_RESONATE);
            }
            if (this.getHowlingTime()==1 && !this.level().isClientSide){
                alertThreats();
            }
            prevHowlTime = this.getHowlingTime();
            this.setHowlingTime(prevHowlTime - 1);

            if (this.getHowlingTime() == 0)
                this.goalSelector.getRunningGoals().forEach(WrappedGoal::start);
        }

        //handles yawning
        if (this.getRandom().nextInt(5000) == 0 && !this.isYawning() && this.onGround() && !this.isHowling()){
            this.setYawningTime(60);
            this.playSound(MMSounds.THYLACINE_YAWN.get());
        }

        if (isYawning()){
            this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
            this.getNavigation().stop();
            prevYawnTime = this.getYawningTime();
            this.setYawningTime(prevYawnTime - 1);

            if (this.getYawningTime() == 0)
                this.goalSelector.getRunningGoals().forEach(WrappedGoal::start);
        }

    }

    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            this.setPose(Pose.STANDING);
            this.setSprinting(d0 >= 1.25D);
        } else {
            this.setPose(Pose.STANDING);
            this.setSprinting(false);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return MMEntities.THYLACINE.get().create(pLevel);
    }

    protected SoundEvent getAmbientSound() {
        return MMSounds.THYLACINE_IDLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return MMSounds.THYLACINE_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return MMSounds.THYLACINE_DEATH.get();
    }

    @Override
    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void setAttacking(boolean pFromBucket) {
        this.entityData.set(IS_ATTACKING, pFromBucket);
    }

    @Override
    public int attackAnimationTimeout() {
        return this.attackAnimationTimeout;
    }

    @Override
    public void setAttackAnimationTimeout(int attackAnimationTimeout) {
        this.attackAnimationTimeout = attackAnimationTimeout;
    }
}
