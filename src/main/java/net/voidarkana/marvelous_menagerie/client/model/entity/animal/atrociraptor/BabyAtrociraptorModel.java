package net.voidarkana.marvelous_menagerie.client.model.entity.animal.atrociraptor;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.AtrociraptorAnims;
import net.voidarkana.marvelous_menagerie.client.animations.BabyAtrociraptorAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Atrociraptor;

public class BabyAtrociraptorModel<T extends Atrociraptor> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart atrociraptor;
	private final ModelPart LegControl;
	private final ModelPart leftLeg;
	private final ModelPart leftFoot;
	private final ModelPart leftClaw;
	private final ModelPart rightLeg;
	private final ModelPart rightFoot;
	private final ModelPart rightClaw;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart wings;
	private final ModelPart rightWing;
	private final ModelPart leftWing;

	public BabyAtrociraptorModel(ModelPart root) {
		this.root = root.getChild("root");
		this.atrociraptor = this.root.getChild("atrociraptor");
		this.LegControl = this.atrociraptor.getChild("LegControl");
		this.leftLeg = this.LegControl.getChild("leftLeg");
		this.leftFoot = this.leftLeg.getChild("leftFoot");
		this.leftClaw = this.leftFoot.getChild("leftClaw");
		this.rightLeg = this.LegControl.getChild("rightLeg");
		this.rightFoot = this.rightLeg.getChild("rightFoot");
		this.rightClaw = this.rightFoot.getChild("rightClaw");
		this.body = this.atrociraptor.getChild("body");
		this.tail = this.body.getChild("tail");
		this.wings = this.body.getChild("wings");
		this.rightWing = this.wings.getChild("rightWing");
		this.leftWing = this.wings.getChild("leftWing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(2.0F, 21.0F, 1.0F));

		PartDefinition atrociraptor = root.addOrReplaceChild("atrociraptor", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LegControl = atrociraptor.addOrReplaceChild("LegControl", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftLeg = LegControl.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(22, 29).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftFoot = leftLeg.addOrReplaceChild("leftFoot", CubeListBuilder.create().texOffs(11, 29).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition leftClaw = leftFoot.addOrReplaceChild("leftClaw", CubeListBuilder.create().texOffs(11, 32).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, -1.0F));

		PartDefinition rightLeg = LegControl.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(22, 29).mirror().addBox(-0.5F, 0.0F, 0.0F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 0.0F, 0.0F));

		PartDefinition rightFoot = rightLeg.addOrReplaceChild("rightFoot", CubeListBuilder.create().texOffs(11, 29).mirror().addBox(-1.5F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 3.0F, 0.0F));

		PartDefinition rightClaw = rightFoot.addOrReplaceChild("rightClaw", CubeListBuilder.create().texOffs(11, 32).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, -1.0F));

		PartDefinition body = atrociraptor.addOrReplaceChild("body", CubeListBuilder.create().texOffs(25, 27).addBox(-1.5F, -2.0F, -6.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -2.0F, -1.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(25, 17).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.5F, 0.0F, 1.0F, 7.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 3.0F));

		PartDefinition wings = body.addOrReplaceChild("wings", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, -2.5F));

		PartDefinition rightWing = wings.addOrReplaceChild("rightWing", CubeListBuilder.create().texOffs(1, 34).mirror().addBox(-0.5F, -1.0F, -2.5F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 29).mirror().addBox(-1.0F, -1.0F, -0.5F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.0F, 6.0F, 1.0F));

		PartDefinition leftWing = wings.addOrReplaceChild("leftWing", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, -1.0F, -0.5F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(1, 34).addBox(0.5F, -1.0F, -2.5F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 6.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float partialTick = ageInTicks - entity.tickCount;

		animateWalk(BabyAtrociraptorAnims.walk, limbSwing/3, limbSwingAmount, 2f,
				2.5f*(1-entity.getSittingMultiplier(partialTick))*(1-entity.getInWaterMultiplier(partialTick))*entity.getOnGroundMultiplier(partialTick));
		animateWalk(BabyAtrociraptorAnims.run, limbSwing/5, limbSwingAmount, 1.5f,
				entity.getSprintingMultiplier(partialTick)*(1-entity.getSittingMultiplier(partialTick))*(1-entity.getInWaterMultiplier(partialTick)));

		this.animate(entity.shakingAnimationState, BabyAtrociraptorAnims.shake, ageInTicks, 1);
		this.animate(entity.jumpingAnimationState, BabyAtrociraptorAnims.jump, ageInTicks, 1);
		this.animate(entity.attackAnimationState, AtrociraptorAnims.attack, ageInTicks, 1);

		this.animateIdle(entity.idleAnimationState, BabyAtrociraptorAnims.swim, ageInTicks, 1.0f, entity.getInWaterMultiplier(partialTick));

		this.animateIdle(entity.idleAnimationState, BabyAtrociraptorAnims.idle, ageInTicks, 1.0f,
				Math.max(0, entity.getOnGroundMultiplier(partialTick)*(1-entity.getInWaterMultiplier(partialTick))-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, BabyAtrociraptorAnims.idle_rotation, ageInTicks, 1.0f,
				Math.max(0, entity.getOnGroundMultiplier(partialTick)*(1-entity.getInWaterMultiplier(partialTick))-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState, BabyAtrociraptorAnims.fall, ageInTicks, 1.0f,
				Mth.clamp((1 - entity.getOnGroundMultiplier(partialTick))*(1-entity.getInWaterMultiplier(partialTick)), 0f,1f));

		this.body.xRot += headPitch * ((float)Math.PI / 180F)/4;
		this.body.yRot += netHeadYaw * ((float)Math.PI / 180F)/4;
	}

	@Override
	public ModelPart root() {
		return root;
	}
}