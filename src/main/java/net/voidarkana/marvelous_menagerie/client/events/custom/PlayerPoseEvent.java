package net.voidarkana.marvelous_menagerie.client.events.custom;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class PlayerPoseEvent extends Event {
    private LivingEntity entityIn;
    private HumanoidModel model;
    private float ageInTicks;
    private float netHeadYaw;
    private float headPitch;

    public PlayerPoseEvent(LivingEntity entityIn, HumanoidModel model, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.entityIn = entityIn;
        this.model = model;
        this.ageInTicks = pAgeInTicks;
        this.netHeadYaw = pNetHeadYaw;
        this.headPitch = pHeadPitch;
    }

    public Entity getEntityIn() {
        return entityIn;
    }

    public HumanoidModel getModel() {
        return model;
    }

    public float getAgeInTicks() {
        return ageInTicks;
    }

    public float getHeadPitch() {
        return headPitch;
    }

    public float getNetHeadYaw() {
        return netHeadYaw;
    }
}
