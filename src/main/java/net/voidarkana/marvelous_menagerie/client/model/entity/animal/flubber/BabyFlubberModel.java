package net.voidarkana.marvelous_menagerie.client.model.entity.animal.flubber;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.BabyFlubberAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Flubber;

public class BabyFlubberModel<T extends Flubber> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart head_flap;
	private final ModelPart head;
	private final ModelPart trunk;
	private final ModelPart tail;
	private final ModelPart tail_end;
	private final ModelPart hips;
	private final ModelPart leg_l;
	private final ModelPart leg_r;
	private final ModelPart arm_r;
	private final ModelPart hand_r;
	private final ModelPart arm_l;
	private final ModelPart hand_l;

	public BabyFlubberModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.body_main = this.swim_rot.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.neck = this.body.getChild("neck");
		this.head_flap = this.neck.getChild("head_flap");
		this.head = this.neck.getChild("head");
		this.trunk = this.head.getChild("trunk");
		this.tail = this.body.getChild("tail");
		this.tail_end = this.tail.getChild("tail_end");
		this.hips = this.body.getChild("hips");
		this.leg_l = this.body.getChild("leg_l");
		this.leg_r = this.body.getChild("leg_r");
		this.arm_r = this.body_main.getChild("arm_r");
		this.hand_r = this.arm_r.getChild("hand_r");
		this.arm_l = this.body_main.getChild("arm_l");
		this.hand_l = this.arm_l.getChild("hand_l");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -5.0F));

		PartDefinition body_main = swim_rot.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.5F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -2.5F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 17).addBox(-2.5F, -6.0F, -3.0F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -1.5F));

		PartDefinition head_flap = neck.addOrReplaceChild("head_flap", CubeListBuilder.create().texOffs(21, 34).addBox(0.0F, -1.5F, -1.5F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -5.5F, 0.5F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 32).addBox(-3.0F, -1.5F, -2.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.01F))
		.texOffs(1, 47).addBox(-3.0F, -1.5F, -2.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, -4.6F, -2.0F));

		PartDefinition trunk = head.addOrReplaceChild("trunk", CubeListBuilder.create().texOffs(34, 34).addBox(-1.5F, -1.6F, -3.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.1F, -2.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(11, 40).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 5.5F));

		PartDefinition tail_end = tail.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(21, 17).addBox(0.0F, -6.0F, 0.0F, 0.0F, 7.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition hips = body.addOrReplaceChild("hips", CubeListBuilder.create(), PartPose.offset(0.0F, 0.9F, 4.5F));

		PartDefinition leg_l = body.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(34, 14).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 1.8F, 4.0F));

		PartDefinition leg_r = body.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(1, 40).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.8F, 4.0F));

		PartDefinition arm_r = body_main.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(40, 17).addBox(-3.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -0.1F, -1.5F));

		PartDefinition hand_r = arm_r.addOrReplaceChild("hand_r", CubeListBuilder.create().texOffs(33, 0).addBox(-5.0F, -0.5F, -1.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.5F, 0.0F));

		PartDefinition arm_l = body_main.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(40, 20).addBox(0.0F, 0.0F, -1.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -0.1F, -1.5F));

		PartDefinition hand_l = arm_l.addOrReplaceChild("hand_l", CubeListBuilder.create().texOffs(33, 7).addBox(0.0F, -0.5F, -1.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Flubber entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isFromInventory()){
			if (entity.isInWaterOrBubble())
				this.applyStatic(BabyFlubberAnims.BABY_SWIM_POSE);
			else
				this.applyStatic(BabyFlubberAnims.LAND_IDLE_POSE);
		}

		this.animateIdle(entity.idleAnimationState, BabyFlubberAnims.BABY_LAND_IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount*3f)));
		this.animateIdle(entity.idleAnimationState, BabyFlubberAnims.BABY_SWIM_IDLE, ageInTicks, 1.0f, entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount));

		this.animateWalk(BabyFlubberAnims.BABY_SWIM, limbSwing, limbSwingAmount*entity.getInWaterTicks()/5f, 1.5f, 2.5f);
		this.animateWalk(BabyFlubberAnims.BABY_WALK, limbSwing, limbSwingAmount*2f*(1-entity.getInWaterTicks()/5f), 2, 2.5f);

//		this.animate(entity.landDanceAnimationState, FlubberAnimsBasics.CELEBRATION_LAND, ageInTicks);
//		this.animate(entity.waterDanceAnimationState1, FlubberAnimsBasics.CELEBRATION_WATER_1, ageInTicks);
//		this.animate(entity.waterDanceAnimationState2, FlubberAnimsBasics.CELEBRATION_WATER_2, ageInTicks);
//		this.animate(entity.waterDigAnimationState, FlubberAnimsBasics.SWIM_DIG_OVERLAY, ageInTicks);
//		this.animate(entity.landDigAnimationState, FlubberAnimsBasics.LAND_DIG, ageInTicks);

		float prevHeadxRot = this.neck.xRot;
		float prevHeadyRot = this.neck.yRot;
		this.neck.xRot = prevHeadxRot + (headPitch * ((float)Math.PI / 180F)/2);
		this.neck.yRot = prevHeadyRot + (netHeadYaw * ((float)Math.PI / 180F)/2);

		this.swim_rot.xRot = Mth.lerp( entity.getInWaterTicks()/5f, 0, headPitch * ((float)Math.PI / 180F));
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}