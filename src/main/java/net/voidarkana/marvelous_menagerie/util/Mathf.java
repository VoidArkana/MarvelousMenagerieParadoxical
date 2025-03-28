package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.util.lambda.Cons;

import java.util.Random;

import static net.voidarkana.marvelous_menagerie.util.mini2DX.compat.MathUtils.cos;
import static net.voidarkana.marvelous_menagerie.util.mini2DX.compat.MathUtils.sin;
import static org.joml.Math.sqrt;

public class Mathf {

    public static final float STARTING_ANGLE = 0.0174532925F;

    static Random rand = new Random();
    static {

    }
    public static Vec3 randVec3(){
        float t  = rand.nextFloat()*Mth.PI*2.0f;
        float z =  rand.nextFloat()*2-1;
        float s1z = sqrt(1-z*z);
        return new Vec3(s1z* cos(t),s1z* sin(t),z);
    }
    public static Vec2 randVec2(){
        float t  = rand.nextFloat()*Mth.PI*2.0f;
        return new Vec2(cos(t), sin(t));
    }
    public static Vec2 randVec2Uniform(){
        return new Vec2(rand.nextFloat(), rand.nextFloat());
    }

    public static float randFloat(float max){
        return rand.nextFloat()*max;
    }

    public static float randInt(int exclusiveMax){
        return rand.nextInt(exclusiveMax);
    }

    public static float randFloat(float min,float max){
        return rand.nextFloat()*(max-min) + min;
    }
    public static void randVec3(Cons.Cons3<Float,Float,Float> cons){
        float t  = rand.nextFloat()*Mth.PI*2.0f;
        float z =  rand.nextFloat()*2-1;
        float s1z = sqrt(1-z*z);
        cons.get(s1z* cos(t),s1z* sin(t),z);
    }
    public static float sinDeg(float deg){
        return Mth.sin(deg*Mth.DEG_TO_RAD);
    }
    public static float cosDeg(float deg){
        return Mth.cos(deg*Mth.DEG_TO_RAD);
    }

    public static float getHorzAngle(Vec3 vec){
        return (float)Math.toDegrees(Mth.atan2(vec.z(),vec.x()))+90;
    }

    public static Vec3 relativeDirectionHorzF(Direction dir, Vec3 vec){
        Vec3 dirvecZ = new Vec3(dir.getStepX(),dir.getStepY(), dir.getStepZ());
        Vec3 dirvecX = new Vec3(-dir.getStepZ(),dir.getStepY(), dir.getStepX());
        Vec3 dirvecY = new Vec3(dirvecX.x(),dirvecX.y(),dirvecX.z());
        dirvecY.cross(dirvecZ);
        dirvecX.scale(vec.x());
        dirvecY.scale(vec.y());
        dirvecZ.scale(vec.z());
        dirvecX.add(dirvecY);
        dirvecX.add(dirvecZ);
        return  dirvecX;

    }
    public static Vec3 relativeDirectionHorz(Direction dir, Vec3 vec){
        Vec3 v = relativeDirectionHorzF(dir,vec);
        return new Vec3(v.x(),v.y(),v.z());
    }
    public static Vec3 vec3i(Vec3 v){
        return new Vec3(v.x(),v.y(),v.z());
    }
    public static float lerp(float t, float x,float x2){return t*(x2-x) + x;}
    public static float map(float r, float rmin,float rmax, float mapmin,float mapmax){
        return mapmin + (mapmax-mapmin)*(r-rmin)/(rmax-rmin);
    }
    public static float mapClamped(float r, float rmin,float rmax, float mapmin,float mapmax){
        return mapmin + (mapmax-mapmin)*Mth.clamp((r-rmin)/(rmax-rmin),0,1);
    }
    public static float catmull(float t, float x,float x2,float m1,float m2){
        float t2 = t*t;
        float t3 = t2*t;
        return  (2*t3 - 3*t2 + 1)*x + (t3 - 2*t2 + t)*m1 + (-2*t3 + 3*t2)*x2 + (t3-t2)*m2;
    }
    public static float catmullNorm(float t,float m1,float m2){
        float t2 = t*t;
        float t3 = t2*t;
        return  (t3 - 2*t2 + t)*m1 + (-2*t3 + 3*t2) + (t3-t2)*m2;
    }

//    public static Quaternion fromEulerDegXYZ(float x,float y,float z){
//        return Quaternion.method_35825(x*MathConstants.RADIANS_PER_DEGREE,y*MathConstants.RADIANS_PER_DEGREE,z*MathConstants.RADIANS_PER_DEGREE);
//    }


    public static float dst2(float x,float y){
        return x*x+y*y;
    }

    public static float approach(float x, float target,float speed){
        return x+(target>=x?Math.min(speed,target-x):-Math.min(speed,x-target));
    }
    public static float lerpTowards(float x, float target,float speed){
        return x+(target-x)*speed;
    }

    public static final int lookupSize = 256;
    public static float[][] randLookup = new float[lookupSize][lookupSize];

    public static float getRandFromPoint(int x,int y){
        return randLookup[x&0xFF][y&0xFF];
    }
    static {
        for(int i = 0;i<lookupSize;i++){
            for(int j = 0;j<lookupSize;j++){
                randLookup[i][j] = Mathf.randFloat(1);
            }
        }
    }

}
