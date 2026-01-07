package net.voidarkana.marvelous_menagerie.client.model.entity.abomination;// Made with Blockbench 5.0.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.geom.ModelPart;
import net.voidarkana.marvelous_menagerie.client.animations.BeholderAnims;
import net.voidarkana.marvelous_menagerie.client.animations.MoltenAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Molten;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class MoltenModel<T extends Molten> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart molten;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head_pivot;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart tail_base;
	private final ModelPart tail_2;
	private final ModelPart tail_mid;
	private final ModelPart tail_4;
	private final ModelPart tail_end;
	private final ModelPart tail_tip;
	private final ModelPart spine;
	private final ModelPart spine5;
	private final ModelPart spine4;
	private final ModelPart spine3;
	private final ModelPart spine2;
	private final ModelPart spine1;
	private final ModelPart arms;
	private final ModelPart arm_pivot_l;
	private final ModelPart arm_l;
	private final ModelPart elbow_l;
	private final ModelPart claw_l;
	private final ModelPart arm_pivot_r;
	private final ModelPart arm_r;
	private final ModelPart elbow_r;
	private final ModelPart claw_r;

	public MoltenModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.molten = this.root.getChild("molten");
		this.body = this.molten.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head_pivot = this.torso.getChild("head_pivot");
		this.head = this.head_pivot.getChild("head");
		this.jaw = this.head.getChild("jaw");
		this.tail_base = this.torso.getChild("tail_base");
		this.tail_2 = this.tail_base.getChild("tail_2");
		this.tail_mid = this.tail_2.getChild("tail_mid");
		this.tail_4 = this.tail_mid.getChild("tail_4");
		this.tail_end = this.tail_4.getChild("tail_end");
		this.tail_tip = this.tail_end.getChild("tail_tip");
		this.spine = this.body.getChild("spine");
		this.spine5 = this.spine.getChild("spine5");
		this.spine4 = this.spine.getChild("spine4");
		this.spine3 = this.spine.getChild("spine3");
		this.spine2 = this.spine.getChild("spine2");
		this.spine1 = this.spine.getChild("spine1");
		this.arms = this.body.getChild("arms");
		this.arm_pivot_l = this.arms.getChild("arm_pivot_l");
		this.arm_l = this.arm_pivot_l.getChild("arm_l");
		this.elbow_l = this.arm_l.getChild("elbow_l");
		this.claw_l = this.elbow_l.getChild("claw_l");
		this.arm_pivot_r = this.arms.getChild("arm_pivot_r");
		this.arm_r = this.arm_pivot_r.getChild("arm_r");
		this.elbow_r = this.arm_r.getChild("elbow_r");
		this.claw_r = this.elbow_r.getChild("claw_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition molten = root.addOrReplaceChild("molten", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = molten.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -10.0F, -10.0F, 20.0F, 20.0F, 20.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head_pivot = torso.addOrReplaceChild("head_pivot", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, -10.0F));

		PartDefinition head = head_pivot.addOrReplaceChild("head", CubeListBuilder.create().texOffs(8, 68).addBox(-3.0F, -1.0F, -14.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(51, 105).addBox(-4.0F, -5.0F, -6.0F, 8.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(6, 47).addBox(-3.0F, -5.0F, -14.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(78, 112).addBox(-4.0F, -1.0F, -3.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.15F)), PartPose.offsetAndRotation(0.0F, -4.75F, -3.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 77).addBox(-3.0F, 0.0F, -14.0F, 6.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(28, 63).addBox(-3.0F, 1.0F, -14.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(82, 13).addBox(-3.0F, 4.0F, -14.0F, 6.0F, 0.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(81, 0).addBox(-4.0F, 0.0F, -6.0F, 8.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_base = torso.addOrReplaceChild("tail_base", CubeListBuilder.create().texOffs(45, 41).addBox(-5.0F, -5.0F, -2.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 10.0F));

		PartDefinition tail_2 = tail_base.addOrReplaceChild("tail_2", CubeListBuilder.create().texOffs(78, 74).addBox(-3.5F, -3.5F, 0.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.0F));

		PartDefinition tail_mid = tail_2.addOrReplaceChild("tail_mid", CubeListBuilder.create().texOffs(81, 22).addBox(-2.5F, -3.0F, 0.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.5F));

		PartDefinition tail_4 = tail_mid.addOrReplaceChild("tail_4", CubeListBuilder.create().texOffs(86, 51).addBox(-1.5F, -2.5F, 0.0F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 7.5F));

		PartDefinition tail_end = tail_4.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(90, 62).addBox(-0.5F, -2.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 6.0F));

		PartDefinition tail_tip = tail_end.addOrReplaceChild("tail_tip", CubeListBuilder.create(), PartPose.offset(1.5F, 0.0F, 5.0F));

		PartDefinition cube_r2 = tail_tip.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(78, 89).addBox(0.0F, -3.5F, -4.0F, 0.0F, 7.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.0F, 0.0F, 2.3562F));

		PartDefinition cube_r3 = tail_tip.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(78, 89).addBox(0.0F, -3.5F, -4.0F, 0.0F, 7.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition spine = body.addOrReplaceChild("spine", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition spine5 = spine.addOrReplaceChild("spine5", CubeListBuilder.create().texOffs(44, 95).addBox(0.0F, -9.0F, -2.0F, 1.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -11.0F, 9.0F));

		PartDefinition spine4 = spine.addOrReplaceChild("spine4", CubeListBuilder.create().texOffs(9, 92).addBox(0.0F, -15.0F, -2.0F, 1.0F, 15.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -11.0F, 5.0F));

		PartDefinition spine3 = spine.addOrReplaceChild("spine3", CubeListBuilder.create().texOffs(33, 77).addBox(0.0F, -19.0F, -3.0F, 1.0F, 19.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -11.0F, 1.0F));

		PartDefinition spine2 = spine.addOrReplaceChild("spine2", CubeListBuilder.create().texOffs(0, 92).addBox(0.0F, -18.0F, -2.0F, 1.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -11.0F, -4.0F));

		PartDefinition spine1 = spine.addOrReplaceChild("spine1", CubeListBuilder.create().texOffs(18, 92).addBox(0.0F, -13.0F, -2.0F, 1.0F, 13.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -11.0F, -8.0F));

		PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 2.0F));

		PartDefinition arm_pivot_l = arms.addOrReplaceChild("arm_pivot_l", CubeListBuilder.create(), PartPose.offset(10.0F, 0.0F, 0.0F));

		PartDefinition arm_l = arm_pivot_l.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(86, 35).mirror().addBox(0.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition elbow_l = arm_l.addOrReplaceChild("elbow_l", CubeListBuilder.create(), PartPose.offset(2.0F, 8.0F, 0.0F));

		PartDefinition claw_l = elbow_l.addOrReplaceChild("claw_l", CubeListBuilder.create().texOffs(45, 74).mirror().addBox(-4.0F, 0.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(45, 62).mirror().addBox(-2.0F, 11.9F, -9.0F, 11.0F, 0.0F, 11.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arm_pivot_r = arms.addOrReplaceChild("arm_pivot_r", CubeListBuilder.create(), PartPose.offset(-10.0F, 0.0F, 0.0F));

		PartDefinition arm_r = arm_pivot_r.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(86, 35).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition elbow_r = arm_r.addOrReplaceChild("elbow_r", CubeListBuilder.create(), PartPose.offset(-2.0F, 8.0F, 0.0F));

		PartDefinition claw_r = elbow_r.addOrReplaceChild("claw_r", CubeListBuilder.create().texOffs(45, 74).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(45, 62).addBox(-9.0F, 11.9F, -9.0F, 11.0F, 0.0F, 11.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (!entity.isAlive()){
			this.applyStatic(MoltenAnims.POSE);
		}

		if (entity.isSprinting()){
			animateWalk(MoltenAnims.RUN,limbSwing, limbSwingAmount, 2f, 1);
		}else {
			animateWalk(MoltenAnims.WALK,limbSwing, limbSwingAmount, 2, 2.5f);
		}

		this.animateIdle(entity.idleAnimationState, MoltenAnims.IDLE, ageInTicks, 1.0f, 1-Math.abs(limbSwingAmount));

		this.animate(entity.biteAttackAnimationState, MoltenAnims.BITE, ageInTicks, 1);
		this.animate(entity.slamAttackAnimationState, MoltenAnims.TAIL_SLAM, ageInTicks, 1);
		this.animate(entity.swirlAttackAnimationState1, MoltenAnims.TAIL_SWIRL_1, ageInTicks, 1);
		this.animate(entity.swirlAttackAnimationState2, MoltenAnims.TAIL_SWIRL_2, ageInTicks, 1);

		this.animate(entity.breatheFireAnimationState, MoltenAnims.FIREBREATHE, ageInTicks, 1);
		this.animate(entity.shootFireballAnimationState, MoltenAnims.FIREBALL, ageInTicks, 1);

		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
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