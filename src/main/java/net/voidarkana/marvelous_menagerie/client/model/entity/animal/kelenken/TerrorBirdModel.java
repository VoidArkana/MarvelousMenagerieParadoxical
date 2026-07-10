package net.voidarkana.marvelous_menagerie.client.model.entity.animal.kelenken;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.TerrorBirdAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Kelenken;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class TerrorBirdModel<T extends Kelenken> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart kelenken;
	private final ModelPart legs;
	private final ModelPart leg_r;
	private final ModelPart leg_l;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart reins;
	private final ModelPart jaw;
	private final ModelPart wing_l;
	private final ModelPart wing_r;
	private final ModelPart tail;

	public TerrorBirdModel(ModelPart root) {
		this.root = root.getChild("root");
		this.kelenken = this.root.getChild("kelenken");
		this.legs = this.kelenken.getChild("legs");
		this.leg_r = this.legs.getChild("leg_r");
		this.leg_l = this.legs.getChild("leg_l");
		this.body = this.kelenken.getChild("body");
		this.neck = this.body.getChild("neck");
		this.head = this.neck.getChild("head");
		this.reins = this.head.getChild("reins");
		this.jaw = this.head.getChild("jaw");
		this.wing_l = this.body.getChild("wing_l");
		this.wing_r = this.body.getChild("wing_r");
		this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition kelenken = root.addOrReplaceChild("kelenken", CubeListBuilder.create(), PartPose.offset(0.0F, -21.5F, 0.0F));

		PartDefinition legs = kelenken.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_r = legs.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(25, 68).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(19, 79).addBox(0.0F, 13.0F, 2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(84, 28).addBox(-2.0F, 15.0F, -5.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 4.5F, 0.0F));

		PartDefinition leg_l = legs.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(42, 71).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 18.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(84, 34).addBox(-2.0F, 15.0F, -5.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(19, 79).addBox(0.0F, 13.0F, 2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 4.5F, 0.0F));

		PartDefinition body = kelenken.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.5F, -9.0F, 12.0F, 12.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(81, 57).addBox(-5.0F, -8.5F, 5.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(81, 62).addBox(-4.0F, -8.5F, -5.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 49).addBox(-2.5F, -15.5F, -5.0F, 5.0F, 17.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -7.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(25, 49).addBox(-1.5F, -3.5F, -16.0F, 3.0F, 7.0F, 11.0F, new CubeDeformation(0.05F))
		.texOffs(54, 45).addBox(-1.5F, -0.5F, -16.0F, 3.0F, 0.0F, 11.0F, new CubeDeformation(0.001F))
		.texOffs(54, 57).addBox(-3.5F, -3.5F, -5.0F, 7.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(51, 31).addBox(-3.5F, -7.5F, -5.0F, 7.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(63, 2).addBox(-3.5F, -3.5F, 1.0F, 7.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, -2.0F));

		PartDefinition reins = head.addOrReplaceChild("reins", CubeListBuilder.create().texOffs(58, 93).addBox(3.5F, -3.5F, -0.5F, 0.0F, 14.0F, 13.0F, new CubeDeformation(0.05F))
		.texOffs(58, 93).mirror().addBox(-3.5F, -3.5F, -0.5F, 0.0F, 14.0F, 13.0F, new CubeDeformation(0.05F)).mirror(false)
		.texOffs(62, 121).mirror().addBox(-3.5F, 2.5F, 10.5F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.05F)).mirror(false), PartPose.offset(0.0F, 0.0F, -4.5F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(61, 14).addBox(-1.5F, -2.0F, -10.0F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(59, 71).addBox(-1.5F, 0.5F, -10.0F, 3.0F, 0.0F, 9.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 1.5F, -4.0F));

		PartDefinition wing_l = body.addOrReplaceChild("wing_l", CubeListBuilder.create().texOffs(78, 81).addBox(0.0F, -2.0F, 0.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 74).addBox(0.5F, -2.0F, 1.0F, 0.0F, 5.0F, 9.0F, new CubeDeformation(0.001F)), PartPose.offset(6.0F, -1.5F, -6.0F));

		PartDefinition wing_r = body.addOrReplaceChild("wing_r", CubeListBuilder.create().texOffs(0, 74).addBox(-0.5F, -2.0F, 1.0F, 0.0F, 5.0F, 9.0F, new CubeDeformation(0.001F))
		.texOffs(83, 45).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -1.5F, -6.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 31).addBox(-6.0F, 0.0F, -2.0F, 12.0F, 4.0F, 13.0F, new CubeDeformation(0.01F))
		.texOffs(0, 96).addBox(-6.0F, -10.0F, 0.0F, 12.0F, 14.0F, 13.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -6.5F, 9.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		float partialTick = ageInTicks - entity.tickCount;
		float groundScale = entity.getOnGroundMultiplier(partialTick)*(1-entity.getInWaterMultiplier(partialTick));
		float walkScale = 2.5f*(1-entity.getSprintingMultiplier(partialTick))*groundScale;
		float runScale = 2.5f*entity.getSprintingMultiplier(partialTick)*groundScale;
		float fallFlyScale = Math.max(0, (1-groundScale));
		float groundIdleScale = Math.max(0, (1-entity.getInWaterMultiplier(partialTick))-Math.abs(limbSwingAmount));

		animateWalk(TerrorBirdAnims.WALK, limbSwing, limbSwingAmount*4f, 4, walkScale);
		animateWalk(TerrorBirdAnims.RUN, limbSwing/3, limbSwingAmount*4f, 4, runScale);

		this.animate(entity.attackAnimationState1, TerrorBirdAnims.ATTACK_1, ageInTicks, 1F);
		this.animate(entity.attackAnimationState2, TerrorBirdAnims.ATTACK_2, ageInTicks, 1F);
		this.animate(entity.attackAnimationState3, TerrorBirdAnims.ATTACK_3, ageInTicks, 1F);

		this.animate(entity.eatingAnimationState, TerrorBirdAnims.EAT, ageInTicks, 1F);

		this.animateIdle(entity.headShakeAnimationState, TerrorBirdAnims.HEAD_IDLE, ageInTicks, 1, 1-entity.getInWaterMultiplier(partialTick));
		this.animate(entity.wingsShakeAnimationState, TerrorBirdAnims.WINGS_IDLE, ageInTicks, 1);
		this.animate(entity.roarAnimationState, TerrorBirdAnims.CALL, ageInTicks, 1);

		this.animate(entity.standUpAnimationState, TerrorBirdAnims.SIT_END, ageInTicks, 1);
		this.animate(entity.sitAnimationState, TerrorBirdAnims.SIT_START, ageInTicks, 1);
		this.animate(entity.sitPoseAnimationState, TerrorBirdAnims.SIT_POSE, ageInTicks, 1);

		this.animateIdle(entity.idleAnimationState, TerrorBirdAnims.IDLE, ageInTicks, 1.0f, groundIdleScale);
		this.animateIdle(entity.idleAnimationState, TerrorBirdAnims.FALL, ageInTicks, 1.0f, fallFlyScale);
		this.animateIdle(entity.idleAnimationState, TerrorBirdAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterMultiplier(partialTick));

		this.head.xRot += headPitch * ((float)Math.PI / 180F)/3;
		this.head.yRot += netHeadYaw * ((float)Math.PI / 180F)/3;
		this.neck.xRot += headPitch * ((float)Math.PI / 180F)/3;
		this.neck.yRot += netHeadYaw * ((float)Math.PI / 180F)/3;

		if (entity.isVehicle()){
			this.neck.xRot += Math.toRadians(35);
			this.head.xRot -= Math.toRadians(35);
		}

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