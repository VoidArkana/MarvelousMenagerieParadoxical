package net.voidarkana.marvelous_menagerie.client.model.base;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.AnimationState;

public class MarvelousAnimationState extends AnimationState {

    private final int transitionTicks;
    float scale;
    float prevScale;

    public MarvelousAnimationState(){
        this(5);
    }

    public MarvelousAnimationState(int pTransitionTicks){
        this.transitionTicks = pTransitionTicks;
    }

    @Override
    public void animateWhen(boolean pCondition, int pTickCount) {

        float targetScale = pCondition ? 1 : 0;
        prevScale = this.scale;
        this.scale += (targetScale - this.scale) * this.transitionTicks/10;

        this.scale = Mth.clamp(this.scale, 0.0F, 1.0F);

        if (scale>0.05 && pCondition) {
            this.startIfStopped(pTickCount);
        } else {
            this.stop();
        }
    }

    public float getScale(){
        return this.scale;
    }
}
