package net.voidarkana.marvelous_menagerie.client.model.entity.animal.inostrancevia;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.InostranceviaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Inostrancevia;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class InostranceviaModel<T extends Inostrancevia> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart inostrancevia;
	private final ModelPart legs;
	private final ModelPart leg_l;
	private final ModelPart leg_r;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart hips;
	private final ModelPart tail;
	private final ModelPart arms;
	private final ModelPart arm_l;
	private final ModelPart arm_r;

	public InostranceviaModel(ModelPart root) {
        this.root = root.getChild("root");
		this.inostrancevia = this.root.getChild("inostrancevia");
		this.legs = this.inostrancevia.getChild("legs");
		this.leg_l = this.legs.getChild("leg_l");
		this.leg_r = this.legs.getChild("leg_r");
		this.body = this.inostrancevia.getChild("body");
		this.neck = this.body.getChild("neck");
		this.head = this.neck.getChild("head");
		this.jaw = this.head.getChild("jaw");
		this.hips = this.body.getChild("hips");
		this.tail = this.hips.getChild("tail");
		this.arms = this.inostrancevia.getChild("arms");
		this.arm_l = this.arms.getChild("arm_l");
		this.arm_r = this.arms.getChild("arm_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition inostrancevia = root.addOrReplaceChild("inostrancevia", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 7.0F));

		PartDefinition legs = inostrancevia.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_l = legs.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(38, 51).addBox(-0.5F, 0.0F, -2.5F, 4.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition leg_r = legs.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(51, 0).addBox(-3.5F, 0.0F, -2.5F, 4.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition body = inostrancevia.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -6.0F, -6.0F, 9.0F, 12.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -11.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(44, 19).addBox(-3.5F, -3.5F, -5.5F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -1.3F, -6.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(36, 80).addBox(-2.5F, 2.0F, -5.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(36, 85).addBox(-1.5F, 2.0F, -13.0F, 3.0F, 0.0F, 8.0F, new CubeDeformation(0.001F))
		.texOffs(70, 0).addBox(-2.5F, -2.0F, -5.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(20, 81).addBox(-2.5F, -2.0F, 0.0F, 5.0F, 7.0F, 2.0F, new CubeDeformation(-0.01F))
		.texOffs(56, 34).addBox(-1.5F, -2.0F, -13.0F, 3.0F, 6.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(74, 9).addBox(1.25F, 0.0F, -13.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.001F))
		.texOffs(0, 64).addBox(-1.25F, 0.0F, -13.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -1.0F, -5.5F));

		PartDefinition eyebrows_r1 = head.addOrReplaceChild("eyebrows_r1", CubeListBuilder.create().texOffs(69, 82).addBox(0.0F, -1.5F, -3.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-3.0F, -2.25F, -4.5F, 0.3927F, -0.7854F, 0.0F));

		PartDefinition eyebrows_r2 = head.addOrReplaceChild("eyebrows_r2", CubeListBuilder.create().texOffs(69, 82).addBox(0.0F, -1.5F, -3.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(3.0F, -2.25F, -4.5F, 0.3927F, 0.7854F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(57, 49).addBox(-1.0F, -3.5F, -13.5F, 2.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(50, 65).addBox(-2.5F, -1.5F, -5.5F, 5.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(68, 65).addBox(-2.5F, 0.0F, -5.5F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(28, 26).addBox(-1.0F, 0.0F, -13.5F, 2.0F, 0.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 3.5F, 0.75F));

		PartDefinition hips = body.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(0, 26).addBox(-3.5F, -4.5F, 0.0F, 7.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 6.5F));

		PartDefinition tail = hips.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(27, 35).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 5.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 9.0F));

		PartDefinition arms = inostrancevia.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -13.5F));

		PartDefinition arm_l = arms.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(19, 51).addBox(-0.5F, -2.0F, -2.5F, 4.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		PartDefinition arm_r = arms.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(0, 45).addBox(-3.5F, -2.0F, -2.5F, 4.0F, 15.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		animateWalk(InostranceviaAnims.WALK, limbSwing, limbSwingAmount*4f, 4, 2.5f*(1-entity.getInWaterMultiplier())*(1-entity.getSprintingMultiplier()));
		animateWalk(InostranceviaAnims.RUN, limbSwing/2, limbSwingAmount*4f, 4, 2.5f*(1-entity.getInWaterMultiplier())*(entity.getSprintingMultiplier()));

		this.animate(entity.attackAnimationState1, InostranceviaAnims.ATTACK_1, ageInTicks, 1F);
		this.animate(entity.attackAnimationState2, InostranceviaAnims.ATTACK_2, ageInTicks, 1F);
		this.animate(entity.attackAnimationState3, InostranceviaAnims.ATTACK_3, ageInTicks, 1F);

		this.animateIdle(entity.yawnAnimationState, InostranceviaAnims.MOUTH_OPEN, ageInTicks, 1, 1-entity.getInWaterMultiplier());
		this.animate(entity.shakeAnimationState, InostranceviaAnims.SHAKE, ageInTicks, 1);
		this.animate(entity.roarAnimationState, InostranceviaAnims.ROAR, ageInTicks, 1);

		this.animate(entity.standUpAnimationState, InostranceviaAnims.SIT_END, ageInTicks, 1);
		this.animate(entity.sitAnimationState, InostranceviaAnims.SIT_START, ageInTicks, 1);
		this.animate(entity.sitPoseAnimationState, InostranceviaAnims.SIT_POSE, ageInTicks, 1);

		this.animateIdle(entity.idleAnimationState, InostranceviaAnims.IDLE_LEGLESS, ageInTicks, 1.0f, Math.max(0, (1-entity.getInWaterMultiplier())-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, InostranceviaAnims.IDLE_LEGS, ageInTicks, 1.0f, Math.max(0, (1-entity.getInWaterMultiplier())*(1-entity.getSittingMultiplier())-Math.abs(limbSwingAmount)));

		if (!entity.isTame())
			this.animateIdle(entity.idleAnimationState, InostranceviaAnims.IDLE_JAW, ageInTicks, 1.0f, 1);

		if (entity.isEvil())
			this.animate(entity.idleAnimationState, InostranceviaAnims.AGGRO, ageInTicks, 1.0f);
		else
			this.animateIdle(entity.idleAnimationState, InostranceviaAnims.AGGRO, ageInTicks, 1.0f, Math.max(0, entity.getAggroMultiplier()));

		this.animateIdle(entity.idleAnimationState, InostranceviaAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterMultiplier());

		this.head.xRot += headPitch * ((float)Math.PI / 180F)/3;
		this.head.yRot += netHeadYaw * ((float)Math.PI / 180F)/3;
		this.neck.xRot += headPitch * ((float)Math.PI / 180F)/3;
		this.neck.yRot += netHeadYaw * ((float)Math.PI / 180F)/3;

		this.head.xRot = Mth.clamp(this.head.xRot, -45, 45);
		this.head.yRot = Mth.clamp(this.head.yRot, -45, 45);
		this.neck.xRot = Mth.clamp(this.neck.xRot, -45, 45);
		this.neck.yRot = Mth.clamp(this.neck.yRot, -45, 45);

		this.jaw.xRot = Mth.clamp(this.jaw.xRot, -67.5f, 5);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}