package net.voidarkana.marvelous_menagerie.client.model.entity.animal.thylacine;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.BabyThylacineAnims;
import net.voidarkana.marvelous_menagerie.client.animations.ThylacineAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Thylacine;

public class BabyThylacineModel<T extends Thylacine> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart main;
	private final ModelPart legs;
	private final ModelPart leg_left;
	private final ModelPart leg_right;
	private final ModelPart arms;
	private final ModelPart arm_left;
	private final ModelPart arm_right;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart head;
	private final ModelPart halo;
	private final ModelPart eyebrows;
	private final ModelPart ear_right;
	private final ModelPart ear_left;
	private final ModelPart jaw;

	public BabyThylacineModel (ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.main = this.root.getChild("main");
		this.legs = this.main.getChild("legs");
		this.leg_left = this.legs.getChild("leg_left");
		this.leg_right = this.legs.getChild("leg_right");
		this.arms = this.main.getChild("arms");
		this.arm_left = this.arms.getChild("arm_left");
		this.arm_right = this.arms.getChild("arm_right");
		this.body = this.main.getChild("body");
		this.tail = this.body.getChild("tail");
		this.head = this.body.getChild("head");
		this.halo = this.head.getChild("halo");
		this.eyebrows = this.head.getChild("eyebrows");
		this.ear_right = this.head.getChild("ear_right");
		this.ear_left = this.head.getChild("ear_left");
		this.jaw = this.head.getChild("jaw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.5F, -3.0F, 0.5F));

		PartDefinition legs = main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-0.5F, -5.0F, 4.5F));

		PartDefinition leg_left = legs.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(32, 28).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 5.0F, -2.0F));

		PartDefinition leg_right = legs.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(20, 33).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 5.0F, -2.0F));

		PartDefinition arms = main.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.0F, -3.5F));

		PartDefinition arm_left = arms.addOrReplaceChild("arm_left", CubeListBuilder.create().texOffs(11, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 1.0F));

		PartDefinition arm_right = arms.addOrReplaceChild("arm_right", CubeListBuilder.create().texOffs(29, 34).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 1.0F));

		PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -5.5F, 5.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-3.0F, -2.0F, -5.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(27, 10).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, 1.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(21, 20).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(21, 28).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(9, 37).addBox(-1.0F, -0.25F, -4.75F, 2.0F, 1.0F, 2.0F, new CubeDeformation(-0.2F))
		.texOffs(25, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, -5.5F));

		PartDefinition halo = head.addOrReplaceChild("halo", CubeListBuilder.create().texOffs(0, 12).addBox(-4.0F, -0.5F, -2.4F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -4.5F, -1.6F));

		PartDefinition eyebrows = head.addOrReplaceChild("eyebrows", CubeListBuilder.create().texOffs(25, 6).addBox(-3.0F, 0.5F, -1.0F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -2.5F, -2.0F));

		PartDefinition ear_right = head.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(27, 17).mirror().addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-2.0F, -3.0F, 0.0F));

		PartDefinition ear_left = head.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(27, 17).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(2.0F, -3.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -1.15F, -2.55F, 2.0F, 1.0F, 3.0F, new CubeDeformation(-0.3F))
		.texOffs(0, 36).addBox(-1.0F, -0.5F, -2.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(39, 35).addBox(-1.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -2.5F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (!entity.isInWaterOrBubble()){
			if (entity.isSprinting()){
				animateWalk(BabyThylacineAnims.RUN, limbSwing/2, limbSwingAmount, 1.5f, 1);
			}else {
				animateWalk(BabyThylacineAnims.WALK, limbSwing/2, limbSwingAmount, 2, 2.5f);
			}

			this.animate(entity.yawnAnimationState, BabyThylacineAnims.YAWN, ageInTicks, 1);
		}

		if (entity.isEndling()){
			this.animateIdle(entity.idleAnimationState, ThylacineAnims.HALO, ageInTicks, 1.0f, 1);
		}

		this.animate(entity.attackAnimationState1, BabyThylacineAnims.ATTACK_1, ageInTicks, 1);
		this.animate(entity.attackAnimationState2, BabyThylacineAnims.ATTACK_2, ageInTicks, 1);

		this.animateIdle(entity.idleAnimationState, BabyThylacineAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, BabyThylacineAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);

		float headX = this.head.xRot;
		float headY = this.head.yRot;

		this.head.xRot = headX + headPitch * ((float)Math.PI / 180F);
		this.head.yRot = headY + netHeadYaw * ((float)Math.PI / 180F);
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