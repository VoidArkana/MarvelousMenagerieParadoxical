package net.voidarkana.marvelous_menagerie.client.screen.guiparticle;

import net.minecraft.util.Mth;

public class ParticleAttractor extends ParticleAffector{
    float acceleration = 0.1f;
    boolean falloff = false;
    public ParticleAttractor(float radius, float x, float y,float acceleration){
        super(radius, x, y);
        this.acceleration=acceleration;
    }

    public ParticleAttractor setFalloff(boolean falloff){
        this.falloff = falloff;
        return this;
    }

    @Override
    public void affect(GUIParticle particle){
        float dx = x-particle.x;
        float dy = y-particle.y;
        float d2 = dx*dx+dy*dy;
        if(d2>radius*radius || d2==0){return;}
        float d =  Mth.sqrt(d2);
        if(falloff){
            d*=d;
        }
        particle.vx+=acceleration*dx/d;
        particle.vy+=acceleration*dy/d;
    }
}
