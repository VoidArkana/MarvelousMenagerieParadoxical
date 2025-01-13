package net.voidarkana.marvelous_menagerie.client.screen.guiparticle;

import com.mojang.blaze3d.platform.GlStateManager.*;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.voidarkana.marvelous_menagerie.client.screen.fossil.FossilMinigameScreen;
import net.voidarkana.marvelous_menagerie.util.lambda.Cons;

public abstract class GUIParticle {
    public float x,y,vx,vy;
    public float life = 0;
    public int turnPoint;
    public boolean canTurn = false;
    public RenderAffectors[] renderAffectors = {};
    public boolean cantDie = false;
    public long layer = 1;

    public float prevVY;

    public GUIParticle(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void update(){
        if (canTurn && life<turnPoint){
            y-=prevVY;
            prevVY = prevVY/1.1f;
        }else{
            prevVY = prevVY*1.1f;
            y+=prevVY;
        }
        x+=vx;
        life++;
    }

    public abstract void draw(PoseStack matrices, FossilMinigameScreen at);

    public void forceKill(){
        life=-99;
        cantDie=false;
    }

    public GUIParticle setLayer(int layer,boolean enabled){
        this.layer = (this.layer-(this.layer&(1L<<layer)))|(enabled?1L:0L)<<layer;
        return this;
    }

    public GUIParticle setRenderAffectors(RenderAffectors... renderAffectors){
        this.renderAffectors = renderAffectors;
        return this;
    }

    public static class RenderAffectors{
        Cons.Cons3<PoseStack, Screen, GUIParticle> effect = (a, b, c)->{};

        public RenderAffectors(Cons.Cons3<PoseStack, Screen, GUIParticle> effect){
            this.effect = effect;
        }



        public static RenderAffectors FADE_IN  = new RenderAffectors((a,b,p)-> {
            float[] c = RenderSystem.getShaderColor();
            RenderSystem.setShaderColor(c[0], c[1], c[2], Math.min(1.0f,p.life*0.1f));
        });
        public static RenderAffectors ADD_BLEND  = new RenderAffectors((a,b,p)-> RenderSystem.blendFunc(SourceFactor.ONE,DestFactor.ONE));
        public static RenderAffectors NORM_BLEND  = new RenderAffectors((a,b,p)-> RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA));
    }
}
