package net.voidarkana.marvelous_menagerie.common.entity.base;

import net.minecraft.world.entity.PathfinderMob;
import org.jetbrains.annotations.Nullable;

public interface IChaserAnimal {

    int getChaseTime();

    void setChaseTime(int time);

    int getChaseCooldown();

    void setChaseCooldown(int cooldown);

    boolean isChaseDriver();

    void setIsChaseDriver(boolean driver);

    int getMaxChaseTime();

    void setMaxChaseTime(int time);

    @Nullable
    PathfinderMob getChasePartner();

    void setChasePartner(PathfinderMob partner);

}
