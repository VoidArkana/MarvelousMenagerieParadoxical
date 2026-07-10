package net.voidarkana.marvelous_menagerie.client.model.entity.animal.kelenken;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.*;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Kelenken;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BabyTerrorBirdModel<T extends Kelenken> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart kelenken;
	private final ModelPart legs;
	private final ModelPart leg_l;
	private final ModelPart leg_r;
	private final ModelPart body;
	private final ModelPart look_control;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart head_overlay;
	private final ModelPart wing_l;
	private final ModelPart wing_r;

	public BabyTerrorBirdModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.root = root.getChild("root");
		this.kelenken = this.root.getChild("kelenken");
		this.legs = this.kelenken.getChild("legs");
		this.leg_l = this.legs.getChild("leg_l");
		this.leg_r = this.legs.getChild("leg_r");
		this.body = this.kelenken.getChild("body");
		this.look_control = this.body.getChild("look_control");
		this.neck = this.look_control.getChild("neck");
		this.head = this.neck.getChild("head");
		this.head_overlay = this.head.getChild("head_overlay");
		this.wing_l = this.body.getChild("wing_l");
		this.wing_r = this.body.getChild("wing_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 20.9F, 0.0F));

		PartDefinition kelenken = root.addOrReplaceChild("kelenken", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -1.0F));

		PartDefinition legs = kelenken.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_l = legs.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(23, 26).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.001F))
		.texOffs(23, 13).addBox(-1.5F, 4.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition leg_r = legs.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(23, 26).mirror().addBox(-0.5F, -1.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false)
		.texOffs(23, 13).mirror().addBox(-1.5F, 4.0F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-1.5F, 0.0F, 0.0F));

		PartDefinition body = kelenken.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, -2.5F, -4.0F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(-2, 26).addBox(-2.0F, -2.5F, 3.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 0.0F));

		PartDefinition look_control = body.addOrReplaceChild("look_control", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, -3.0F));

		PartDefinition neck = look_control.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(25, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition head_overlay = head.addOrReplaceChild("head_overlay", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -7.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(3, 5).addBox(-0.5F, 0.0F, -7.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.001F))
		.texOffs(23, 16).addBox(0.0F, -5.0F, -1.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wing_l = body.addOrReplaceChild("wing_l", CubeListBuilder.create().texOffs(25, 5).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.1F, -0.5F, -3.0F));

		PartDefinition wing_r = body.addOrReplaceChild("wing_r", CubeListBuilder.create().texOffs(25, 5).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.1F, -0.5F, -3.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		float partialTick = ageInTicks - entity.tickCount;
		float groundScale = entity.getOnGroundMultiplier(partialTick)*(1-entity.getInWaterMultiplier(partialTick));
		float walkScale = 2.5f*(1-entity.getSprintingMultiplier(partialTick))*groundScale;
		float runScale = 2.5f*entity.getSprintingMultiplier(partialTick)*groundScale;
		float fallFlyScale = Math.max(0, (1-groundScale));
		float groundIdleScale = Math.max(0, (1-entity.getInWaterMultiplier(partialTick))*(1-entity.getSittingMultiplier(partialTick))-Math.abs(limbSwingAmount));

		animateWalk(BabyTerrorBirdAnims.BABY_WALK, limbSwing/2, limbSwingAmount*4f, 4, walkScale);
		animateWalk(BabyTerrorBirdAnims.BABY_RUN, limbSwing/4, limbSwingAmount*4f, 4, runScale);

		this.animate(entity.attackAnimationState1, BabyTerrorBirdAnims.ATTACK_1, ageInTicks, 1F);
		this.animate(entity.attackAnimationState2, BabyTerrorBirdAnims.ATTACK_2, ageInTicks, 1F);
		this.animate(entity.attackAnimationState3, BabyTerrorBirdAnims.ATTACK_3, ageInTicks, 1F);

		this.animate(entity.eatingAnimationState, BabyTerrorBirdAnims.EAT, ageInTicks, 1F);

		this.animateIdle(entity.headShakeAnimationState, TerrorBirdAnims.HEAD_IDLE, ageInTicks, 1, 1-entity.getInWaterMultiplier(partialTick));
		this.animate(entity.wingsShakeAnimationState, TerrorBirdAnims.WINGS_IDLE, ageInTicks, 1);
		this.animate(entity.roarAnimationState, BabyTerrorBirdAnims.CALL, ageInTicks, 1);

		this.animate(entity.standUpAnimationState, BabyTerrorBirdAnims.SIT_END, ageInTicks, 1);
		this.animate(entity.sitAnimationState, BabyTerrorBirdAnims.SIT_START, ageInTicks, 1);
		this.animate(entity.sitPoseAnimationState, BabyTerrorBirdAnims.SIT_POSE, ageInTicks, 1);

		this.animateIdle(entity.idleAnimationState, BabyTerrorBirdAnims.BABY_IDLE, ageInTicks, 1.0f, groundIdleScale);
		this.animateIdle(entity.idleAnimationState, BabyTerrorBirdAnims.BABY_FALL, ageInTicks, 1.0f, fallFlyScale);
		this.animateIdle(entity.idleAnimationState, BabyTerrorBirdAnims.BABY_SWIM, ageInTicks, 1.0f, entity.getInWaterMultiplier(partialTick));

		this.head.xRot += headPitch * ((float)Math.PI / 180F)/3;
		this.head.yRot += netHeadYaw * ((float)Math.PI / 180F)/3;
		this.neck.xRot += headPitch * ((float)Math.PI / 180F)/3;
		this.neck.yRot += netHeadYaw * ((float)Math.PI / 180F)/3;

		this.head.xRot = Mth.clamp(this.head.xRot, -45, 45);
		this.head.yRot = Mth.clamp(this.head.yRot, -45, 45);
		this.neck.xRot = Mth.clamp(this.neck.xRot, -45, 45);
		this.neck.yRot = Mth.clamp(this.neck.yRot, -45, 45);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}