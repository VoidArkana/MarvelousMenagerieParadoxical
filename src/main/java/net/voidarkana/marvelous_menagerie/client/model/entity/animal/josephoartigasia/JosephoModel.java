package net.voidarkana.marvelous_menagerie.client.model.entity.animal.josephoartigasia;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.JosephoAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Josephoartigasia;

public class JosephoModel<T extends Josephoartigasia> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart main;
	private final ModelPart body;
	private final ModelPart body_without_head;
	private final ModelPart passenger;
	private final ModelPart head;
	private final ModelPart ears;
	private final ModelPart ear_l;
	private final ModelPart ear_r;
	private final ModelPart arms;
	private final ModelPart arm_left;
	private final ModelPart arm_right;
	private final ModelPart legs;
	private final ModelPart leg_left;
	private final ModelPart leg_right;

	public JosephoModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.main = this.root.getChild("main");
		this.body = this.main.getChild("body");
		this.body_without_head = this.body.getChild("body_without_head");
		this.passenger = this.body_without_head.getChild("passenger");
		this.head = this.body.getChild("head");
		this.ears = this.head.getChild("ears");
		this.ear_l = this.ears.getChild("ear_l");
		this.ear_r = this.ears.getChild("ear_r");
		this.arms = this.main.getChild("arms");
		this.arm_left = this.arms.getChild("arm_left");
		this.arm_right = this.arms.getChild("arm_right");
		this.legs = this.main.getChild("legs");
		this.leg_left = this.legs.getChild("leg_left");
		this.leg_right = this.legs.getChild("leg_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, -18.5F, 0.0F));

		PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition body_without_head = body.addOrReplaceChild("body_without_head", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, -8.5F, -23.5F, 17.0F, 17.0F, 29.0F, new CubeDeformation(0.25F))
		.texOffs(0, 47).addBox(-8.5F, -8.5F, -23.5F, 17.0F, 17.0F, 29.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition passenger = body_without_head.addOrReplaceChild("passenger", CubeListBuilder.create(), PartPose.offset(0.0F, -8.5F, -9.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(64, 0).addBox(-5.5F, -7.5F, -16.0F, 11.0F, 11.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -13.5F));

		PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, -7.5F, -1.0F));

		PartDefinition ear_l = ears.addOrReplaceChild("ear_l", CubeListBuilder.create().texOffs(0, 19).addBox(-0.5F, -3.0F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, 0.0F));

		PartDefinition ear_r = ears.addOrReplaceChild("ear_r", CubeListBuilder.create().texOffs(9, 19).addBox(-2.5F, -3.0F, -0.5F, 3.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.0F, 0.0F));

		PartDefinition arms = main.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(-5.0F, 8.5F, -8.5F));

		PartDefinition arm_left = arms.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(86, 87).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(9.5F, 0.0F, 0.0F));

		PartDefinition arm_right = arms.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(64, 47).addBox(-3.0F, -1.0F, -3.5F, 7.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs = main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(4.5F, 8.5F, 13.5F));

		PartDefinition leg_left = legs.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(0, 47).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_right = legs.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -1.0F, -3.5F, 7.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Josephoartigasia entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(entity.headShakeAnimationState, JosephoAnims.HEAD_SHAKE, ageInTicks, 1);
		this.animate(entity.earsWiggleAnimationState, JosephoAnims.EAR_WIGGLE_BOTH, ageInTicks, 1);
		this.animate(entity.leftEarWiggleAnimationState, JosephoAnims.EAR_WIGGLE_LEFT, ageInTicks, 1);
		this.animate(entity.rightEarWiggleAnimationState, JosephoAnims.EAR_WIGGLE_RIGHT, ageInTicks, 1);

		if (!entity.isInWaterOrBubble()){
			if (!entity.isInSittingPose()){
				animateWalk(JosephoAnims.WALK, limbSwing, limbSwingAmount, 2, 2.5f);
			}

			this.animate(entity.sitEndAnimationState, JosephoAnims.SIT_END, ageInTicks, 1);
			this.animate(entity.sitStartAnimationState, JosephoAnims.SIT_START, ageInTicks, 1);
			this.animate(entity.sitIdleAnimationState, JosephoAnims.SIT_IDLE, ageInTicks, 1);
		}

		this.animateIdle(entity.idleAnimationState, JosephoAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);
		this.animateIdle(entity.idleAnimationState, JosephoAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));


		float prevHeadX = this.head.xRot;
		float prevHeadY = this.head.yRot;
		this.head.xRot = prevHeadX + headPitch * ((float)Math.PI / 180F);
		this.head.yRot = prevHeadY + netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

		if (this.young){
			poseStack.scale(0.45f, 0.45f, 0.45f);
			poseStack.translate(0, 1.75, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();	}

	@Override
	public ModelPart root() {
		return root;
	}
}