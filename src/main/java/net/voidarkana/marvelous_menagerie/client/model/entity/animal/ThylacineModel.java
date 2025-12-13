package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.LeptiAnims;
import net.voidarkana.marvelous_menagerie.client.animations.ThylacineAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Thylacine;

public class ThylacineModel<T extends Thylacine> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart main;
	private final ModelPart body_overlay;
	private final ModelPart body;
	private final ModelPart headrot;
	private final ModelPart head_overlay;
	private final ModelPart head;
	private final ModelPart ear_left;
	private final ModelPart ear_right;
	private final ModelPart eyebrows;
	private final ModelPart snout;
	private final ModelPart jaw_overlay;
	private final ModelPart jaw;
	private final ModelPart tail;
	private final ModelPart arms_overlay;
	private final ModelPart arms;
	private final ModelPart arm_left;
	private final ModelPart arm_right;
	private final ModelPart legs_overlay;
	private final ModelPart legs;
	private final ModelPart leg_left;
	private final ModelPart leg_right;

	public ThylacineModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.main = this.root.getChild("main");
		this.body_overlay = this.main.getChild("body_overlay");
		this.body = this.body_overlay.getChild("body");
		this.headrot = this.body.getChild("headrot");
		this.head_overlay = this.headrot.getChild("head_overlay");
		this.head = this.head_overlay.getChild("head");
		this.ear_left = this.head.getChild("ear_left");
		this.ear_right = this.head.getChild("ear_right");
		this.eyebrows = this.head.getChild("eyebrows");
		this.snout = this.head.getChild("snout");
		this.jaw_overlay = this.head.getChild("jaw_overlay");
		this.jaw = this.jaw_overlay.getChild("jaw");
		this.tail = this.body.getChild("tail");
		this.arms_overlay = this.main.getChild("arms_overlay");
		this.arms = this.arms_overlay.getChild("arms");
		this.arm_left = this.arms.getChild("arm_left");
		this.arm_right = this.arms.getChild("arm_right");
		this.legs_overlay = this.main.getChild("legs_overlay");
		this.legs = this.legs_overlay.getChild("legs");
		this.leg_left = this.legs.getChild("leg_left");
		this.leg_right = this.legs.getChild("leg_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition body_overlay = main.addOrReplaceChild("body_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 5.0F));

		PartDefinition body = body_overlay.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.5F, -13.0F, 5.0F, 5.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(27, 0).addBox(-2.5F, -2.5F, -13.0F, 5.0F, 7.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition headrot = body.addOrReplaceChild("headrot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -13.0F));

		PartDefinition head_overlay = headrot.addOrReplaceChild("head_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = head_overlay.addOrReplaceChild("head", CubeListBuilder.create().texOffs(17, 22).addBox(-3.0F, -4.0F, -5.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ear_left = head.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(9, 4).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -4.0F, -1.0F));

		PartDefinition ear_right = head.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(9, 0).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -4.0F, -1.0F));

		PartDefinition eyebrows = head.addOrReplaceChild("eyebrows", CubeListBuilder.create().texOffs(9, 8).addBox(1.0F, -3.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.05F))
		.texOffs(9, 8).mirror().addBox(-3.0F, -3.0F, -5.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.05F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(0, 11).addBox(-1.5F, -9.0F, -15.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 8.0F, 7.5F));

		PartDefinition jaw_overlay = head.addOrReplaceChild("jaw_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -4.5F));

		PartDefinition jaw = jaw_overlay.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(10, 37).addBox(-1.5F, -0.5F, -3.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -1.25F, 3.0F));

		PartDefinition tail_r1 = tail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.25F, -0.75F, -0.2182F, 0.0F, 0.0F));

		PartDefinition arms_overlay = main.addOrReplaceChild("arms_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -6.0F));

		PartDefinition arms = arms_overlay.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arm_left = arms.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(0, 37).addBox(-0.9F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		PartDefinition arm_right = arms.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(0, 0).addBox(-1.1F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

		PartDefinition legs_overlay = main.addOrReplaceChild("legs_overlay", CubeListBuilder.create(), PartPose.offset(2.0F, 2.0F, 5.0F));

		PartDefinition legs = legs_overlay.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_left = legs.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(29, 33).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_right = legs.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(Thylacine entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (this.young){
			this.applyStatic(ThylacineAnims.BABY);
		}

		if (!entity.isInWaterOrBubble()){

			if (entity.isSprinting()){
				animateWalk(ThylacineAnims.RUN, limbSwing, limbSwingAmount, 1.5f, 1);
			}else {
				animateWalk(ThylacineAnims.WALK, limbSwing, limbSwingAmount, 2, 2.5f);
			}

			this.animate(entity.attackAnimationState1, ThylacineAnims.ATTACK_1, ageInTicks, 1);
			this.animate(entity.attackAnimationState2, ThylacineAnims.ATTACK_2, ageInTicks, 1);

			this.animate(entity.yawnAnimationState, ThylacineAnims.YAWN, ageInTicks, 1);

			this.animate(entity.howlAnimationState, ThylacineAnims.HOWL, ageInTicks, 1);

		}

		this.animateIdle(entity.idleAnimationState, ThylacineAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, ThylacineAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);


		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

		if (this.young){
			poseStack.scale(0.6f, 0.6f, 0.6f);
			poseStack.translate(0, 1, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}
}