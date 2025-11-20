package net.voidarkana.marvelous_menagerie.client.animations;// Save this class in your mod and generate all required imports

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

/**
 * Made with Blockbench 5.0.4
 * Exported for Minecraft version 1.19 or later with Mojang mappings
 * @author Author
 */
public class StellerAnims {
	public static final AnimationDefinition IDLE = AnimationDefinition.Builder.withLength(4.0F).looping()
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-1.46F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-1.46F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(-8.54F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-8.54F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.73F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, -1.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.posVec(0.0F, 1.0F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.posVec(0.0F, 2.0F, -0.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, 1.73F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(9.33F, 0.0F, 4.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(3.71F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.17F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(6.29F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(9.83F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(9.33F, 0.0F, 4.33F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(0.52F, 0.0F, -4.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(2.5F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(9.33F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(7.5F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(2.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_l", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 57.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(14.87F, 0.0F, 43.04F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(5.74F, 0.0F, 58.86F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 57.99F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_r", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, -57.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(-14.87F, 0.0F, -43.04F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.875F, KeyframeAnimations.degreeVec(1.96F, 0.0F, -30.13F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(14.87F, 0.0F, -43.04F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -60.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, -57.99F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-19.49F, 0.0F, -4.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.25F, KeyframeAnimations.degreeVec(-19.87F, 0.0F, -4.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(10.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-19.49F, 0.0F, -4.33F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(-19.83F, 0.0F, -4.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.18F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(19.83F, 0.0F, -4.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(5.18F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition SWIM = AnimationDefinition.Builder.withLength(4.0F).looping()
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 3.54F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 3.54F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.degreeVec(5.0F, 0.0F, -3.54F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -3.54F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 1.73F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.87F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.posVec(0.0F, 2.0F, 0.26F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.posVec(0.0F, -2.0F, -0.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.posVec(0.0F, -1.0F, -0.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.posVec(0.0F, 1.73F, -0.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.posVec(0.0F, 1.73F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(4.33F, 0.0F, 4.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -1.29F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(1.91F, 0.0F, -4.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.875F, KeyframeAnimations.degreeVec(4.96F, 0.0F, -4.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 1.29F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.75F, KeyframeAnimations.degreeVec(4.83F, 0.0F, 4.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(4.33F, 0.0F, 4.33F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2917F, KeyframeAnimations.degreeVec(-4.96F, 0.0F, -4.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2917F, KeyframeAnimations.degreeVec(-4.96F, 0.0F, 4.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(-1.91F, 0.0F, 4.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_l", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 57.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, 57.99F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_r", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, -57.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -60.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -30.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -45.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-7.5F, 0.0F, -57.99F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-14.49F, 0.0F, -4.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(-13.86F, 0.0F, -4.9F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -1.91F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 4.62F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 1.91F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-14.49F, 0.0F, -4.33F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2917F, KeyframeAnimations.degreeVec(19.83F, 0.0F, -4.48F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(-20.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 4.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition GRAZE = AnimationDefinition.Builder.withLength(4.0F).looping()
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(19.33F, 0.0F, 4.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.2083F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 3.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.4583F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 1.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.7083F, KeyframeAnimations.degreeVec(10.0F, 0.0F, -0.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9583F, KeyframeAnimations.degreeVec(20.0F, 0.0F, -2.21F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2083F, KeyframeAnimations.degreeVec(10.0F, 0.0F, -3.76F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.4583F, KeyframeAnimations.degreeVec(20.0F, 0.0F, -4.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.7083F, KeyframeAnimations.degreeVec(10.0F, 0.0F, -4.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.9583F, KeyframeAnimations.degreeVec(20.0F, 0.0F, -4.48F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.2083F, KeyframeAnimations.degreeVec(10.0F, 0.0F, -3.3F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.4583F, KeyframeAnimations.degreeVec(20.0F, 0.0F, -1.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.7083F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.9583F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 2.21F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.2083F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 3.76F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.4583F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 4.73F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.7083F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 4.99F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(19.33F, 0.0F, 4.33F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -5.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.5F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -3.04F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, -4.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -4.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.8333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, -4.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -3.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -0.65F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 1.29F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 3.04F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 4.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 4.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 4.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 3.97F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5833F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 0.65F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.8333F, KeyframeAnimations.degreeVec(5.0F, 0.0F, -1.29F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(12.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -5.0F, 2.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 0.75F), AnimationChannel.Interpolations.LINEAR)
		))
		.build();

	public static final AnimationDefinition BEACHED = AnimationDefinition.Builder.withLength(4.0F).looping()
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -0.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.3333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 1.25F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -8.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.9F, 1.1F, 1.2F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 1.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 1.25F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(0.9F, 1.1F, 0.8F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_l", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 20.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_r", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -20.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -10.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-20.0F, 5.18F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.0833F, KeyframeAnimations.degreeVec(-29.65F, 7.65F, 1.29F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.75F, KeyframeAnimations.degreeVec(-30.0F, 19.83F, -4.83F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.25F, KeyframeAnimations.degreeVec(-27.55F, 15.87F, 1.29F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(0.0F, 5.18F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0833F, KeyframeAnimations.degreeVec(-27.26F, -7.65F, 1.29F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.8333F, KeyframeAnimations.degreeVec(-30.0F, -20.0F, -4.33F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.25F, KeyframeAnimations.degreeVec(-26.65F, -15.87F, 1.29F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(0.0F, -5.18F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(-20.0F, 5.18F, 2.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -7.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("tail_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(55.98F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.375F, KeyframeAnimations.degreeVec(53.8F, 0.0F, -3.04F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6667F, KeyframeAnimations.degreeVec(30.0F, 0.0F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.6667F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.1667F, KeyframeAnimations.degreeVec(60.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.625F, KeyframeAnimations.degreeVec(33.92F, 0.0F, -4.96F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.1667F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.6667F, KeyframeAnimations.degreeVec(30.0F, 0.0F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.degreeVec(55.98F, 0.0F, 2.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail_fin", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -3.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("root", new AnimationChannel(AnimationChannel.Targets.SCALE, 
			new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.2F, 0.8F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.scaleVec(1.2F, 0.85F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.scaleVec(1.2F, 0.75F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.5F, KeyframeAnimations.scaleVec(1.2F, 0.85F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(3.5F, KeyframeAnimations.scaleVec(1.2F, 0.75F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(4.0F, KeyframeAnimations.scaleVec(1.2F, 0.8F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition SLEEP_START = AnimationDefinition.Builder.withLength(2.0F)
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-22.5F, -5.3F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, -5.0F, -90.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(7.5F, -1.77F, -150.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -180.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 7.25F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 5.83F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 2.25F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-6.68F, 14.27F, 14.12F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-7.75F, 5.88F, -6.41F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.89F, -2.94F, 3.38F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-5.5F, 0.0F, 4.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-8.43F, 12.99F, 12.37F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-11.25F, 8.66F, -9.91F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-11.14F, 0.0F, -1.87F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_l", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, -27.39F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 37.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-3.75F, 0.0F, 73.54F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_r", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, 27.39F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 6.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-3.75F, 0.0F, -49.54F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -60.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(32.84F, 0.0F, 19.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-32.84F, 0.0F, 30.23F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(32.84F, 0.0F, -10.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-13.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(15.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-15.0F, 0.0F, 25.98F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(15.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();

	public static final AnimationDefinition SLEEP_END = AnimationDefinition.Builder.withLength(2.0F)
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 180.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-6.55F, 15.0F, 156.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-7.5F, 1.0F, 81.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-5.52F, -5.0F, 26.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION, 
			new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 3.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.posVec(0.0F, 13.29F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.0F, KeyframeAnimations.posVec(0.0F, 12.37F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(1.5F, KeyframeAnimations.posVec(0.0F, 5.58F, 0.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(2.0F, KeyframeAnimations.posVec(0.0F, 1.73F, 0.0F), AnimationChannel.Interpolations.LINEAR)
		))
		.addAnimation("neck", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.5F, 0.0F, 4.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-11.1F, 14.27F, 17.45F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-10.08F, 11.88F, -4.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-2.15F, -2.94F, 4.37F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-14.05F, 12.99F, 10.49F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-11.75F, 8.66F, -11.16F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-3.52F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_l", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, 60.0F), AnimationChannel.Interpolations.LINEAR),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-3.75F, 0.0F, 43.61F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, -16.5F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, 0.53F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -0.01F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("flipper_r", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-5.0F, 0.0F, -60.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(-3.75F, 0.0F, -38.36F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.0F, KeyframeAnimations.degreeVec(-2.5F, 0.0F, 15.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(-1.25F, 0.0F, -1.28F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -0.74F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-13.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3F, KeyframeAnimations.degreeVec(14.27F, 0.0F, 10.58F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(33.49F, 0.0F, 19.25F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9F, KeyframeAnimations.degreeVec(9.56F, 0.0F, 33.59F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(10.83F, 0.0F, 16.45F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(33.49F, 0.0F, -10.75F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8F, KeyframeAnimations.degreeVec(6.99F, 0.0F, -10.28F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.addAnimation("tail_fin", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
			new Keyframe(0.0F, KeyframeAnimations.degreeVec(-22.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.3F, KeyframeAnimations.degreeVec(-7.03F, 0.0F, -9.39F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.5F, KeyframeAnimations.degreeVec(20.0F, 0.0F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.6F, KeyframeAnimations.degreeVec(28.27F, 0.0F, -6.24F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(0.9F, KeyframeAnimations.degreeVec(12.09F, 0.0F, 20.07F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.2F, KeyframeAnimations.degreeVec(-9.56F, 0.0F, 29.84F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.5F, KeyframeAnimations.degreeVec(20.0F, 0.0F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(1.8F, KeyframeAnimations.degreeVec(9.35F, 0.0F, -4.88F), AnimationChannel.Interpolations.CATMULLROM),
			new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
		))
		.build();
}