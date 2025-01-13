package net.voidarkana.marvelous_menagerie.client.screen.guiparticle;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;
import net.voidarkana.marvelous_menagerie.util.lambda.Funcf2;

import net.minecraft.util.Mth;

public class DistortableGUIParticle extends GUIParticle{
    float[][] relativePos = new float[4][2];
    float[][] distortPos = new float[4][2];
    Funcf2 distort;
    float u0,u1,v0,v1;
    int maxLife;
    boolean hasMaxLife = false;

    public DistortableGUIParticle(float x, float y, float size, float vx, float vy, float u, float v, float texW, float texH, Funcf2 distort){
        super(x,y);
        u0 = u/texW;
        u1 = (u+size)/texW;
        v0 = v/texH;
        v1 = (v+size)/texH;
        float s2 = size*0.5f;
        relativePos = new float[][]{
                {-s2,s2},
                {s2,s2},
                {s2,-s2},
                {-s2,-s2},
        };
        this.distort=distort;
        this.vx=vx;
        this.vy=vy;
        this.prevVY = vy;
    }

    public DistortableGUIParticle(int lifetime, float x, float y, float size, float vx, float vy, float u, float v, float texW, float texH, Funcf2 distort){
        this(x, y, size, vx,vy, u, v, texW, texH, distort);
        this.maxLife = lifetime;
        this.hasMaxLife = true;
    }

    public DistortableGUIParticle(int pTurnPoint, int lifetime, float x, float y, float size, float vx, float vy, float u, float v, float texW, float texH, Funcf2 distort){
        this(lifetime, x, y, size, vx,vy, u, v, texW, texH, distort);
        this.turnPoint = pTurnPoint;
        this.canTurn = true;
    }

    @Override
    public void update() {
        super.update();
        if (hasMaxLife){
            if (this.life > maxLife)
                this.life = -99;
        }
    }

    public void draw(PoseStack matrices, FossilMinigameScreen at){
        //when too lazy to for loop
        distort.get(relativePos[0][0]+x,relativePos[0][1]+y,distortPos[0]);
        distort.get(relativePos[1][0]+x,relativePos[1][1]+y,distortPos[1]);
        distort.get(relativePos[2][0]+x,relativePos[2][1]+y,distortPos[2]);
        distort.get(relativePos[3][0]+x,relativePos[3][1]+y,distortPos[3]);

        at.drawTexturedQuad(matrices.last().pose(), distortPos,u0,u1,v0,v1);
    }

    public static Funcf2 distortTowardsPoint(float centerX,float centerY,float innerrad,float shiftam){
        return (px, py, out) -> {
            float dx = centerX-px,dy=centerY-py;
            float d = Mth.sqrt(dx*dx+dy*dy);
            float shift = shiftam/(1f+d*0.1f);
            if(d<innerrad){shift=d-innerrad;}
            if(shift>d){shift=d;}
            float d1 = 1f/d;
            out[0]= px+(dx*d1)*shift;
            out[1]= py+(dy*d1)*shift;
        };
    }
}
