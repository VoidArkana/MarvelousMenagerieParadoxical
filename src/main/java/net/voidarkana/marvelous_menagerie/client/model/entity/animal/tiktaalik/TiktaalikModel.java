package net.voidarkana.marvelous_menagerie.client.model.entity.animal.tiktaalik;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.TiktaalikAnims;
import net.voidarkana.marvelous_menagerie.client.animations.TiktaalikAnimsExtra;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Tiktaalik;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class TiktaalikModel<T extends Tiktaalik> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart tiktaalik;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart tail;
	private final ModelPart tailFin;
	private final ModelPart ArmControl;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart LegControl;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public TiktaalikModel(ModelPart root) {
		super(1, 0, RenderType::entityCutout);
        this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.tiktaalik = this.swim_rot.getChild("tiktaalik");
		this.body = this.tiktaalik.getChild("body");
		this.head = this.body.getChild("head");
		this.jaw = this.head.getChild("jaw");
		this.tail = this.body.getChild("tail");
		this.tailFin = this.tail.getChild("tailFin");
		this.ArmControl = this.tiktaalik.getChild("ArmControl");
		this.leftArm = this.ArmControl.getChild("leftArm");
		this.rightArm = this.ArmControl.getChild("rightArm");
		this.LegControl = this.tiktaalik.getChild("LegControl");
		this.leftLeg = this.LegControl.getChild("leftLeg");
		this.rightLeg = this.LegControl.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, -4.5F));

		PartDefinition tiktaalik = swim_rot.addOrReplaceChild("tiktaalik", CubeListBuilder.create(), PartPose.offset(0.0F, 0.4F, 3.5F));

		PartDefinition body = tiktaalik.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, 0.0F, 6.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -5.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 12).addBox(-3.5F, -2.0F, -8.0F, 7.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 23).addBox(-3.5F, 0.0F, -8.0F, 7.0F, 1.0F, 9.0F, new CubeDeformation(-0.02F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(23, 4).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 9.0F));

		PartDefinition tailFin = tail.addOrReplaceChild("tailFin", CubeListBuilder.create().texOffs(21, -5).addBox(0.0F, -3.0F, -2.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 8.0F));

		PartDefinition ArmControl = tiktaalik.addOrReplaceChild("ArmControl", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -4.5F));

		PartDefinition leftArm = ArmControl.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(18, 5).addBox(0.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition rightArm = ArmControl.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(18, 5).mirror().addBox(-5.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition LegControl = tiktaalik.addOrReplaceChild("LegControl", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.5F));

		PartDefinition leftLeg = LegControl.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(18, 5).addBox(0.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition rightLeg = LegControl.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(18, 5).mirror().addBox(-5.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-3.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float partialTick = ageInTicks - entity.tickCount;
		if (entity.getActualSize() == 1)
			this.applyStatic(TiktaalikAnimsExtra.SizeMid);
		if (entity.getActualSize() == 2)
			this.applyStatic(TiktaalikAnimsExtra.SizeBig);

		this.animateIdle(entity.idleAnimationState, TiktaalikAnims.idle, ageInTicks, 1.0f,
				Mth.clamp(1-entity.getSittingMultiplier(partialTick)*(1-(entity.getInWaterMultiplier(partialTick))-Math.abs(limbSwingAmount)),0, 1));

		this.animateIdle(entity.idleAnimationState, TiktaalikAnimsExtra.basking, ageInTicks, 1.0f,
				Mth.clamp(entity.getSittingMultiplier(partialTick), 0, 1));

		this.animateIdle(entity.idleAnimationState, TiktaalikAnims.swim_idle, ageInTicks, 1.0f,
				Mth.clamp((entity.getInWaterMultiplier(partialTick)*(1-entity.getSittingMultiplier(partialTick))*(1-entity.getOnGroundMultiplier(partialTick)))-Math.abs(limbSwingAmount*2), 0, 1));

		this.animateIdle(entity.idleAnimationState, TiktaalikAnimsExtra.WATER_GROUND_IDLE, ageInTicks, 1.0f,
				Mth.clamp((entity.getInWaterMultiplier(partialTick)*(1-entity.getSittingMultiplier(partialTick))*entity.getOnGroundMultiplier(partialTick))-Math.abs(limbSwingAmount), 0, 1));

		this.animateIdle(entity.idleAnimationState, TiktaalikAnimsExtra.WATER_OPEN_MOUTH_IDLE, ageInTicks, 1.0f,
				entity.getSittingMultiplier(partialTick)*entity.getInWaterMultiplier(partialTick));

		this.animate(entity.attackAnimationState, TiktaalikAnims.attack, ageInTicks, 1.0F);

		this.animate(entity.standUpAnimationState, TiktaalikAnimsExtra.basking_end, ageInTicks);
		this.animate(entity.sitAnimationState, TiktaalikAnimsExtra.basking_start, ageInTicks);
		this.animate(entity.sitPoseAnimationState, TiktaalikAnimsExtra.BASK_POSE, ageInTicks);

		this.animateWalk(TiktaalikAnims.swim, limbSwing, limbSwingAmount*2, 1.5f,
				entity.getInWaterMultiplier(partialTick)*(1-entity.getOnGroundMultiplier(partialTick))*(1-entity.getSittingMultiplier(partialTick)));

		this.animateWalk(TiktaalikAnims.SWIM_GROUND, limbSwing, limbSwingAmount*2, 1.5f,
				entity.getInWaterMultiplier(partialTick)*entity.getOnGroundMultiplier(partialTick)*(1-entity.getSittingMultiplier(partialTick)));

		this.animateWalk(TiktaalikAnims.walk, limbSwing, limbSwingAmount*2f, 2,
				(1-entity.getInWaterMultiplier(partialTick))*(1-entity.getSittingMultiplier(partialTick)));

//		this.animateIdle(entity.fallAnimationState, TiktaalikAnimsExtra.FALL, ageInTicks, 1.0f,
//				(1-this.getOnGroundMultiplier())*(1-this.getInWaterMultiplier()));
//
//		this.animateIdle(entity.squashAnimationState, TiktaalikAnimsExtra.SQUASH, ageInTicks,
//				1.0f, (1-this.getInWaterMultiplier()));

		this.animateIdle(entity.idleAnimationState, TiktaalikAnimsExtra.AGGRO, ageInTicks,
				1.0f, entity.getAggroMultiplier(partialTick));

		this.head.xRot +=  Mth.lerp(entity.getInWaterMultiplier(partialTick), 0,  headPitch * ((float)Math.PI / 180F)/2);
		this.head.yRot += Mth.lerp(entity.getInWaterMultiplier(partialTick), 0, netHeadYaw * ((float)Math.PI / 180F)/2);

		this.swim_rot.xRot += Mth.lerp( entity.getInWaterMultiplier(partialTick)*(1-entity.getOnGroundMultiplier(partialTick)), 0,
				headPitch * ((float)Math.PI / 180F)/2);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}