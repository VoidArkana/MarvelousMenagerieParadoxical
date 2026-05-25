package net.voidarkana.marvelous_menagerie.client.model.entity.animal.tiktaalik;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.BabyTiktaalikAnims;
import net.voidarkana.marvelous_menagerie.client.animations.TiktaalikAnims;
import net.voidarkana.marvelous_menagerie.client.animations.TiktaalikAnimsExtra;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Tiktaalik;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BabyTiktaalikModel<T extends Tiktaalik> extends MarvelousModel<T> {

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

	public BabyTiktaalikModel(ModelPart root) {
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

		PartDefinition body = tiktaalik.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 18).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.0F, -6.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -2.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 9).addBox(-2.5F, 0.0F, -6.0F, 5.0F, 1.0F, 7.0F, new CubeDeformation(-0.02F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(21, 18).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition tailFin = tail.addOrReplaceChild("tailFin", CubeListBuilder.create().texOffs(25, 0).addBox(0.0F, -2.0F, -2.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 5.0F));

		PartDefinition ArmControl = tiktaalik.addOrReplaceChild("ArmControl", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -4.5F));

		PartDefinition leftArm = ArmControl.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(25, 9).addBox(0.0F, 0.0F, -0.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(2.0F, 0.0F, 3.0F));

		PartDefinition rightArm = ArmControl.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(25, 12).addBox(-3.0F, 0.0F, -0.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(-2.0F, 0.0F, 3.0F));

		PartDefinition LegControl = tiktaalik.addOrReplaceChild("LegControl", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 3.5F));

		PartDefinition leftLeg = LegControl.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(25, 9).addBox(0.0F, 0.0F, -1.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		PartDefinition rightLeg = LegControl.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(25, 12).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.animateIdle(entity.idleAnimationState, TiktaalikAnims.idle, ageInTicks, 1.0f,
				Math.min(1, Math.max(0, 1-(this.getSittingMultiplier()*(1-this.getInWaterMultiplier()))-Math.abs(limbSwingAmount))));

		this.animateIdle(entity.idleAnimationState, BabyTiktaalikAnims.basking, ageInTicks, 1.0f,
				Math.min(1,Math.max(0, ((this.getSittingMultiplier())*(1-this.getInWaterMultiplier())))));

		this.animateIdle(entity.idleAnimationState, BabyTiktaalikAnims.BABY_SWIM_IDLE, ageInTicks, 1.0f,
				Math.max(0, ((this.getInWaterMultiplier())*(1-this.getSittingMultiplier())*(1-this.getOnGroundMultiplier()))-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState, BabyTiktaalikAnims.BABY_WATER_GROUND_IDLE, ageInTicks, 1.0f,
				Math.max(0, ((this.getInWaterMultiplier())*(1-this.getSittingMultiplier())*(this.getOnGroundMultiplier()))-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState, BabyTiktaalikAnims.WATER_OPEN_MOUTH_IDLE, ageInTicks, 1.0f,
				Math.min(1,Math.max(0,this.getSittingMultiplier()*this.getInWaterMultiplier())));

		this.animate(entity.attackAnimationState, TiktaalikAnims.attack, ageInTicks, 1.0F);

		this.animate(entity.standUpAnimationState, BabyTiktaalikAnims.basking_end, ageInTicks);
		this.animate(entity.sitAnimationState, BabyTiktaalikAnims.basking_start, ageInTicks);
		this.animate(entity.sitPoseAnimationState, BabyTiktaalikAnims.BASK_POSE, ageInTicks);

		this.animateWalk(BabyTiktaalikAnims.BABY_SWIM, limbSwing,
				limbSwingAmount*(this.getInWaterMultiplier())*(1-this.getOnGroundMultiplier()), 1.5f,
				2.5f*(1-this.getSittingMultiplier()));

		this.animateWalk(BabyTiktaalikAnims.SWIM_GROUND, limbSwing,
				limbSwingAmount*(this.getInWaterMultiplier())*(this.getOnGroundMultiplier()), 1.5f,
				2.5f*(1-this.getSittingMultiplier()));

		this.animateWalk(BabyTiktaalikAnims.BABY_WALK, limbSwing,
				limbSwingAmount*2f*(1-this.getInWaterMultiplier()), 2,
				2.5f*(1-this.getSittingMultiplier()));

		this.animateIdle(entity.fallAnimationState, TiktaalikAnimsExtra.FALL, ageInTicks, 1.0f,
				(1-this.getOnGroundMultiplier())*(1-this.getInWaterMultiplier()));

		this.animateIdle(entity.squashAnimationState, TiktaalikAnimsExtra.SQUASH, ageInTicks,
				1.0f, (1-this.getInWaterMultiplier()));

		this.animateIdle(entity.idleAnimationState, TiktaalikAnimsExtra.AGGRO, ageInTicks,
				1.0f, this.getAggroMultiplier());

		float prevHeadxRot = this.head.xRot;
		float prevHeadyRot = this.head.yRot;
		this.head.xRot = prevHeadxRot + headPitch * ((float)Math.PI / 180F)/2;
		this.head.yRot = prevHeadyRot + netHeadYaw * ((float)Math.PI / 180F)/2;

		this.swim_rot.xRot = Mth.lerp( this.getInWaterMultiplier()*(1-this.getOnGroundMultiplier()), 0, headPitch * ((float)Math.PI / 180F));
	}

	@Override
	public ModelPart root() {
		return root;
	}
}