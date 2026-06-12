package net.voidarkana.marvelous_menagerie.client.model.entity.animal.inostrancevia;

import net.minecraft.client.model.geom.ModelPart;
import net.voidarkana.marvelous_menagerie.client.animations.BabyInostranceviaAnims;
import net.voidarkana.marvelous_menagerie.client.animations.InostranceviaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Inostrancevia;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BabyInostranceviaModel<T extends Inostrancevia> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart inostrancevia;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart tail;
	private final ModelPart arms;
	private final ModelPart arm_l;
	private final ModelPart arm_r;
	private final ModelPart legs;
	private final ModelPart leg_l;
	private final ModelPart leg_r;

	public BabyInostranceviaModel(ModelPart root) {
		this.root = root.getChild("root");
		this.inostrancevia = this.root.getChild("inostrancevia");
		this.body = this.inostrancevia.getChild("body");
		this.head = this.body.getChild("head");
		this.tail = this.body.getChild("tail");
		this.arms = this.inostrancevia.getChild("arms");
		this.arm_l = this.arms.getChild("arm_l");
		this.arm_r = this.arms.getChild("arm_r");
		this.legs = this.inostrancevia.getChild("legs");
		this.leg_l = this.legs.getChild("leg_l");
		this.leg_r = this.legs.getChild("leg_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition inostrancevia = root.addOrReplaceChild("inostrancevia", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, -0.5F));

		PartDefinition body = inostrancevia.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -4.5F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-1.5F, -3.5F, -7.5F, 3.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -4.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 39).addBox(-1.5F, 0.5F, -1.0F, 3.0F, 2.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, -4.5F, -4.5F, 0.3927F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(23, 14).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 4.5F));

		PartDefinition arms = inostrancevia.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, -2.5F));

		PartDefinition arm_l = arms.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(23, 25).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		PartDefinition arm_r = arms.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(23, 25).mirror().addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

		PartDefinition legs = inostrancevia.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, 4.5F));

		PartDefinition leg_l = legs.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(27, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		PartDefinition leg_r = legs.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(27, 0).mirror().addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		animateWalk(BabyInostranceviaAnims.WALK, limbSwing/3, limbSwingAmount, 4, 2.5f*(1-entity.getInWaterMultiplier())*(1-entity.getSprintingMultiplier()));
		animateWalk(BabyInostranceviaAnims.RUN,  limbSwing/3, limbSwingAmount, 4, 2.5f*(1-entity.getInWaterMultiplier())*(entity.getSprintingMultiplier()));

		this.animate(entity.attackAnimationState1, BabyInostranceviaAnims.ATTACK, ageInTicks, 1F);
		this.animate(entity.attackAnimationState2, BabyInostranceviaAnims.ATTACK, ageInTicks, 1F);
		this.animate(entity.attackAnimationState3, BabyInostranceviaAnims.ATTACK, ageInTicks, 1F);

		this.animate(entity.shakeAnimationState, InostranceviaAnims.SHAKE, ageInTicks, 1);
		this.animate(entity.yawnAnimationState, InostranceviaAnims.SHAKE, ageInTicks, 1);

		this.animate(entity.standUpAnimationState, BabyInostranceviaAnims.SIT_END, ageInTicks, 1);
		this.animate(entity.sitAnimationState, BabyInostranceviaAnims.SIT_START, ageInTicks, 1);
		this.animate(entity.sitPoseAnimationState, BabyInostranceviaAnims.SIT_POSE, ageInTicks, 1);

		this.animateIdle(entity.idleAnimationState, BabyInostranceviaAnims.IDLE_LEGLESS, ageInTicks, 1.0f, Math.max(0, (1-entity.getInWaterMultiplier())-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, BabyInostranceviaAnims.IDLE_LEGS, ageInTicks, 1.0f, Math.max(0, (1-entity.getInWaterMultiplier())*(1-entity.getSittingMultiplier())-Math.abs(limbSwingAmount)));

		if (entity.isEvil())
			this.animate(entity.idleAnimationState, InostranceviaAnims.AGGRO, ageInTicks, 1.0f);
		else
			this.animateIdle(entity.idleAnimationState, InostranceviaAnims.AGGRO, ageInTicks, 1.0f, Math.max(0, entity.getAggroMultiplier()));

		this.animateIdle(entity.idleAnimationState, BabyInostranceviaAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterMultiplier());

		this.head.xRot += headPitch * ((float)Math.PI / 180F);
		this.head.yRot += netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}