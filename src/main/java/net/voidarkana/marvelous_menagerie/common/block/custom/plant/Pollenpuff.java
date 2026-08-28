package net.voidarkana.marvelous_menagerie.common.block.custom.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.AABB;
import net.voidarkana.marvelous_menagerie.client.particles.MMParticles;

import java.util.List;

public class Pollenpuff extends FlowerBlock {
    private static final EnumProperty<PollenPuffState> POLLEN_PUFF_STATE = EnumProperty.create("pollen_puff_state", PollenPuffState.class);
    public int timer = 600;

    public Pollenpuff(MobEffect effectSupplier, int p_53513_, Properties p_53514_) {
        super(effectSupplier, p_53513_, p_53514_);
        this.registerDefaultState(this.defaultBlockState().setValue(POLLEN_PUFF_STATE, PollenPuffState.DORMANT));
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        super.entityInside(pState, pLevel, pPos, pEntity);

        if (pState.getValue(POLLEN_PUFF_STATE)==PollenPuffState.PUFFING) {
            if (pEntity instanceof Bee bee && !bee.hasNectar()){
                bee.setHasNectar(true);
            }
        }
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.randomTick(pState, pLevel, pPos, pRandom);

        PollenPuffState puffState = pState.getValue(POLLEN_PUFF_STATE);

        if (pRandom.nextInt(puffState.getChance())==0){
            switch (puffState){
                case DORMANT ->{
                    pLevel.setBlockAndUpdate(pPos, pState.setValue(POLLEN_PUFF_STATE, PollenPuffState.CHARGING));

                }
                case CHARGING ->{
                    pLevel.setBlockAndUpdate(pPos, pState.setValue(POLLEN_PUFF_STATE, PollenPuffState.PUFFING));

                }
                default ->{
                    pLevel.setBlockAndUpdate(pPos, pState.setValue(POLLEN_PUFF_STATE, PollenPuffState.DORMANT));
                }
            }
        }
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
        if (pState.getValue(POLLEN_PUFF_STATE)==PollenPuffState.PUFFING && timer % 5 == 0) {
            List<Bee> list = pLevel.getEntitiesOfClass(Bee.class, new AABB(pPos).inflate(16, 8, 16));
            for (Bee e : list) {
                if (!e.hasNectar()){
                    e.setTarget(null);
                    e.setLastHurtByMob(null);
                    e.setSavedFlowerPos(pPos);
                    e.getMoveControl().setWantedPosition(pPos.getX(), pPos.getY()+1, pPos.getZ(), 1.5);
                }
            }
        }
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {

        double d0 = (double)pPos.getX() + 0.5D + pState.getOffset(pLevel, pPos).x();
        double d1 = (double)pPos.getY();
        double d2 = (double)pPos.getZ() + 0.5D + pState.getOffset(pLevel, pPos).z();

        if (pRandom.nextBoolean()){
            double d4 = pRandom.nextDouble() * 0.3D - 0.15D;
            double d5 = pRandom.nextDouble() * 0.3D - 0.15D;
            pLevel.addParticle(ParticleTypes.FALLING_NECTAR,
                    d0+d4,
                    d1 + pRandom.nextDouble()*1.25f,
                    d2+d5,
                    0.0D,
                    0,
                    0.0D);
        }

        if (pState.getValue(POLLEN_PUFF_STATE) == PollenPuffState.CHARGING) {
            double d4 = pRandom.nextDouble() * 0.3D - 0.15D;

            pLevel.addParticle(MMParticles.POLLEN_CHARGE.get(),
                    d0+d4,
                    d1 + pRandom.nextDouble() * 6.0D / 16.0D,
                    d2+d4,
                    0.0D,
                    -0.005D,
                    0.0D);
        }

        if (pState.getValue(POLLEN_PUFF_STATE) == PollenPuffState.PUFFING) {

            if (pRandom.nextBoolean())
                pLevel.playLocalSound(d0, d1, d2, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 0.15f, (pRandom.nextFloat() - pRandom.nextFloat()) * 0.2F + 1.0F, false);

            if (pRandom.nextBoolean()){
                for (int x = pRandom.nextInt(4); x > 0; x--){

                    pLevel.addParticle(MMParticles.POLLEN_CLOUD.get(),
                            d0+(pRandom.nextFloat()*pRandom.nextInt(-1, 2)),
                            d1 + 1 + pRandom.nextDouble() * 6.0D / 16.0D,
                            d2+(pRandom.nextFloat()*pRandom.nextInt(-1, 2)),
                            0.0D,
                            -0.005D,
                            0.0D);
                }
            }

            for (int x = pRandom.nextInt(7); x > 0; x--) {
                pLevel.addParticle(MMParticles.POLLEN.get(),
                        d0,
                        d1 + 1,
                        d2,
                        pRandom.nextFloat()*0.05,
                        0.05D,
                        pRandom.nextFloat()*0.05);
            }
        }
    }

    public enum PollenPuffState implements StringRepresentable {
        DORMANT("dormant",2400),
        CHARGING("charging", 100),
        PUFFING("puffing", 50);

        final public String name;
        final public int chance;

        PollenPuffState(String pName, int pChance){
            this.name = pName;
            this.chance = pChance;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }

        public int getChance() {
            return chance;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POLLEN_PUFF_STATE);
    }
}
