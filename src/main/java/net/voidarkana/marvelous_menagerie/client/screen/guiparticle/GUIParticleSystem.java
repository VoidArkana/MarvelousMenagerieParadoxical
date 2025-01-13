package net.voidarkana.marvelous_menagerie.client.screen.guiparticle;

import com.mojang.blaze3d.vertex.PoseStack;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;
import net.voidarkana.marvelous_menagerie.util.mini2DX.Array;


public class GUIParticleSystem {
    Array<GUIParticle> particles = new Array<>(false,50);
    Array<ParticleAffector> affectors = new Array<>();

    public GUIParticle add(GUIParticle p){
        particles.add(p);
        return p;
    }
    public void addAffector(ParticleAffector p){
        affectors.add(p);
    }

    public void update(){
        for(int i=0;i<affectors.size;i++){
            for(int p=0;p<particles.size;p++){
                if((affectors.get(i).layer & particles.get(p).layer) ==0){continue;}
                if(affectors.get(i).endTime < particles.get(p).life || affectors.get(i).startTime > particles.get(p).life){continue;}
                affectors.get(i).affect(particles.get(p));
            }
        }
        for(int i=0;i<particles.size;i++){
            particles.get(i).update();
            if(particles.get(i).life<0 && !particles.get(i).cantDie){
                particles.removeIndex(i);
                i--;
            }
        }
    }

    public void draw(PoseStack matrices, FossilMinigameScreen at){
        for(int i=0;i<particles.size;i++){
            for(int p=0;p<particles.get(i).renderAffectors.length;p++){
                particles.get(i).renderAffectors[p].effect.get(matrices,at,particles.get(i));
            }
            particles.get(i).draw(matrices,at);
        }
    }
}
