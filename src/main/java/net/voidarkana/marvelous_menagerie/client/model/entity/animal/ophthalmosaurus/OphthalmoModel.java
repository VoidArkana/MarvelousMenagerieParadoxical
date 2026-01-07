package net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.BabyOphthalmoAnims;
import net.voidarkana.marvelous_menagerie.client.animations.OphthalmoAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Ophthalmosaurus;

public class OphthalmoModel<T extends Ophthalmosaurus> extends MarvelousModel<T> {

	private final ModelPart root;
	public final ModelPart swim_control;
	private final ModelPart body_overlay;
	public final ModelPart body;
	private final ModelPart tail_rot;
	private final ModelPart tail_overlay;
	private final ModelPart tail;
	private final ModelPart tail_tip_rot;
	private final ModelPart caudal_fin_overlay;
	private final ModelPart caudal_fin;
	private final ModelPart head_rot;
	private final ModelPart head_overlay;
	private final ModelPart head;
	private final ModelPart eye_l;
	private final ModelPart eye_r;
	private final ModelPart jaw_overlay;
	private final ModelPart jaw;
	private final ModelPart f_flipper_r;
	private final ModelPart f_flipper_l;
	private final ModelPart b_flipper_r;
	private final ModelPart b_flipper_l;
	public final ModelPart passenger;

	public OphthalmoModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		this.body_overlay = this.swim_control.getChild("body_overlay");
		this.body = this.body_overlay.getChild("body");
		this.tail_rot = this.body.getChild("tail_rot");
		this.tail_overlay = this.tail_rot.getChild("tail_overlay");
		this.tail = this.tail_overlay.getChild("tail");
		this.tail_tip_rot = this.tail.getChild("tail_tip_rot");
		this.caudal_fin_overlay = this.tail_tip_rot.getChild("caudal_fin_overlay");
		this.caudal_fin = this.caudal_fin_overlay.getChild("caudal_fin");
		this.head_rot = this.body.getChild("head_rot");
		this.head_overlay = this.head_rot.getChild("head_overlay");
		this.head = this.head_overlay.getChild("head");
		this.eye_l = this.head.getChild("eye_l");
		this.eye_r = this.head.getChild("eye_r");
		this.jaw_overlay = this.head.getChild("jaw_overlay");
		this.jaw = this.jaw_overlay.getChild("jaw");
		this.f_flipper_r = this.body.getChild("f_flipper_r");
		this.f_flipper_l = this.body.getChild("f_flipper_l");
		this.b_flipper_r = this.body.getChild("b_flipper_r");
		this.b_flipper_l = this.body.getChild("b_flipper_l");
		this.passenger = this.body.getChild("passenger");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -10.5F, -4.5F));

		PartDefinition body_overlay = swim_control.addOrReplaceChild("body_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition body = body_overlay.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 45).addBox(-7.0F, -10.5F, -4.5F, 14.0F, 18.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -10.5F, -4.5F, 14.0F, 18.0F, 26.0F, new CubeDeformation(0.5F))
		.texOffs(37, 99).addBox(5.0F, -14.0F, -0.75F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(61, 99).addBox(-5.0F, -14.0F, -0.75F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -4.0F, 2.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.5F, 12.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition tail_rot = body.addOrReplaceChild("tail_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 20.5F));

		PartDefinition tail_overlay = tail_rot.addOrReplaceChild("tail_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = tail_overlay.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(55, 45).addBox(-3.0F, -4.0F, -1.0F, 6.0F, 8.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_tip_rot = tail.addOrReplaceChild("tail_tip_rot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 11.0F));

		PartDefinition caudal_fin_overlay = tail_tip_rot.addOrReplaceChild("caudal_fin_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition caudal_fin = caudal_fin_overlay.addOrReplaceChild("caudal_fin", CubeListBuilder.create().texOffs(0, 90).addBox(-0.5F, -12.0F, -1.0F, 1.0F, 24.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head_rot = body.addOrReplaceChild("head_rot", CubeListBuilder.create(), PartPose.offset(0.0F, 2.8F, -4.5F));

		PartDefinition head_overlay = head_rot.addOrReplaceChild("head_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, -1.75F, 0.0F));

		PartDefinition head = head_overlay.addOrReplaceChild("head", CubeListBuilder.create().texOffs(55, 0).addBox(-5.0F, -6.55F, -9.0F, 10.0F, 13.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(68, 77).addBox(-2.0F, 1.45F, -22.0F, 4.0F, 3.0F, 13.0F, new CubeDeformation(0.05F))
		.texOffs(81, 41).addBox(-1.0F, 4.45F, -22.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye_l = head.addOrReplaceChild("eye_l", CubeListBuilder.create().texOffs(26, 90).addBox(-1.0F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.45F, -5.0F));

		PartDefinition eye_r = head.addOrReplaceChild("eye_r", CubeListBuilder.create().texOffs(26, 90).mirror().addBox(0.0F, -3.0F, -3.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, 0.45F, -5.0F));

		PartDefinition jaw_overlay = head.addOrReplaceChild("jaw_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 4.7F, -8.0F));

		PartDefinition jaw = jaw_overlay.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(81, 26).addBox(-1.0F, -1.25F, -14.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(81, 10).addBox(-2.0F, -0.25F, -14.0F, 4.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition f_flipper_r = body.addOrReplaceChild("f_flipper_r", CubeListBuilder.create().texOffs(81, 67).addBox(-10.0F, -0.5F, -3.5F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 6.5F, 1.0F));

		PartDefinition f_flipper_l = body.addOrReplaceChild("f_flipper_l", CubeListBuilder.create().texOffs(85, 0).addBox(0.0F, -0.5F, -3.5F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 6.5F, 1.0F));

		PartDefinition b_flipper_r = body.addOrReplaceChild("b_flipper_r", CubeListBuilder.create().texOffs(0, 17).addBox(-7.0F, -0.5F, -2.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-7.0F, 7.0F, 17.0F));

		PartDefinition b_flipper_l = body.addOrReplaceChild("b_flipper_l", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -0.5F, -2.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, 7.0F, 17.0F));

		PartDefinition passenger = body.addOrReplaceChild("passenger", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Ophthalmosaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (!entity.isAlive())
			this.applyStatic(OphthalmoAnims.POSE);

		if (entity.isInWaterOrBubble() || entity.isVehicle()){
			this.animateWalk(OphthalmoAnims.SWIM, limbSwing, limbSwingAmount*4f, 1.5f, 3f);

			this.swim_control.xRot = headPitch * ((float)Math.PI / 180F)/2;
			this.head_overlay.yRot = entity.currentRoll;
			this.tail_rot.yRot = -entity.currentRoll;
			this.tail_tip_rot.yRot = -entity.currentRoll;

			this.animate(entity.leftAttackAnimationState, OphthalmoAnims.ATTACK_1, ageInTicks, 1.0F);
			this.animate(entity.rightAttackAnimationState, OphthalmoAnims.ATTACK_2, ageInTicks, 1.0F);
			this.animate(entity.eatAnimationState, OphthalmoAnims.EATING, ageInTicks, 1.0F);

			if (!entity.isVehicle()){
				this.head_rot.yRot = (netHeadYaw * (float)Math.PI / 180F)/4;
				this.head_rot.xRot = (headPitch * (float)Math.PI / 180F)/4;
			}else {
				this.head_rot.resetPose();
			}

		}else {
			this.swim_control.resetPose();
			this.head_overlay.resetPose();
			this.tail_rot.resetPose();
			this.tail_tip_rot.resetPose();
			this.head_rot.resetPose();
		}

		this.animateIdle(entity.idleAnimationState, OphthalmoAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState, OphthalmoAnims.FLOP, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	public ModelPart passenger() {
		return passenger;
	}
}