package net.voidarkana.marvelous_menagerie.client.model.entity.animal;
// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.RoachAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Apthoroblattina;

public class RoachModel<T extends Apthoroblattina> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftAntenna;
	private final ModelPart rightAntenna;
	private final ModelPart leftWing;
	private final ModelPart rightWing;
	private final ModelPart leftLeg1;
	private final ModelPart rightLeg1;
	private final ModelPart leftLeg2;
	private final ModelPart rightLeg2;
	private final ModelPart leftLeg3;
	private final ModelPart rightLeg3;

	public RoachModel(ModelPart root) {
        super(0.3f, 0);
        this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.head = this.body.getChild("head");
		this.leftAntenna = this.head.getChild("leftAntenna");
		this.rightAntenna = this.head.getChild("rightAntenna");
		this.leftWing = this.body.getChild("leftWing");
		this.rightWing = this.body.getChild("rightWing");
		this.leftLeg1 = this.body_main.getChild("leftLeg1");
		this.rightLeg1 = this.body_main.getChild("rightLeg1");
		this.leftLeg2 = this.body_main.getChild("leftLeg2");
		this.rightLeg2 = this.body_main.getChild("rightLeg2");
		this.leftLeg3 = this.body_main.getChild("leftLeg3");
		this.rightLeg3 = this.body_main.getChild("rightLeg3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, 0.0F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -1.6F, -5.0F, 9.0F, 3.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(18, 30).addBox(-2.5F, -1.0F, -3.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.4F, -5.0F));

		PartDefinition leftAntenna = head.addOrReplaceChild("leftAntenna", CubeListBuilder.create().texOffs(27, 15).mirror().addBox(0.0F, 0.0F, -3.0F, 10.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(1.5F, 0.0F, -3.0F));

		PartDefinition rightAntenna = head.addOrReplaceChild("rightAntenna", CubeListBuilder.create().texOffs(27, 15).addBox(-10.0F, 0.0F, -3.0F, 10.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(-2.5F, 0.0F, -3.0F));

		PartDefinition leftWing = body.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offset(-1.0F, -1.6F, -4.0F));

		PartDefinition cube_r1 = leftWing.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(28, 0).addBox(-4.5F, -0.001F, -0.5F, 6.0F, 0.0F, 12.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition rightWing = body.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offset(1.0F, -1.6F, -4.0F));

		PartDefinition cube_r2 = rightWing.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(-1.5F, 0.0F, -0.5F, 6.0F, 0.0F, 12.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition leftLeg1 = body_main.addOrReplaceChild("leftLeg1", CubeListBuilder.create().texOffs(19, 14).addBox(0.0F, 0.0F, -6.0F, 2.0F, 0.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offset(4.5F, 1.4F, -3.0F));

		PartDefinition rightLeg1 = body_main.addOrReplaceChild("rightLeg1", CubeListBuilder.create().texOffs(19, 14).mirror().addBox(-2.0F, 0.0F, -6.0F, 2.0F, 0.0F, 7.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-4.5F, 1.4F, -3.0F));

		PartDefinition leftLeg2 = body_main.addOrReplaceChild("leftLeg2", CubeListBuilder.create(), PartPose.offset(4.5F, 1.4F, 0.0F));

		PartDefinition cube_r3 = leftLeg2.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 23).mirror().addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.0F));

		PartDefinition rightLeg2 = body_main.addOrReplaceChild("rightLeg2", CubeListBuilder.create(), PartPose.offset(-4.5F, 1.4F, 0.0F));

		PartDefinition cube_r4 = rightLeg2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 23).addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

		PartDefinition leftLeg3 = body_main.addOrReplaceChild("leftLeg3", CubeListBuilder.create().texOffs(0, 14).mirror().addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 9.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(4.5F, 1.4F, 3.0F));

		PartDefinition rightLeg3 = body_main.addOrReplaceChild("rightLeg3", CubeListBuilder.create().texOffs(0, 14).addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 9.0F, new CubeDeformation(0.001F)), PartPose.offset(-4.5F, 1.4F, 3.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Apthoroblattina entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		animateWalk(RoachAnims.WALK, limbSwing, limbSwingAmount, 2, entity.isSprinting() ? 5f : 2.5f);

		this.animateIdle(entity.idleAnimationState, RoachAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getTicksOffGround()/5f-Math.abs(limbSwingAmount)));

		this.animate(entity.idleVibrateState, RoachAnims.IDLE_VIBRATE, ageInTicks, 1.0F);

		this.animate(entity.idleRotBothState, RoachAnims.IDLE_ROT_BOTH, ageInTicks, 1.0F);
		this.animate(entity.idleRotLeftState, RoachAnims.IDLE_ROT_LEFT, ageInTicks, 1.0F);
		this.animate(entity.idleRotRightState, RoachAnims.IDLE_ROT_RIGHT, ageInTicks, 1.0F);

		this.animateIdle(entity.fallFlyState, RoachAnims.FALL_FLY, ageInTicks, 1.0F, (entity.getTicksOffGround()/5f));

		this.animate(entity.johnAnimationState, RoachAnims.JOHN, ageInTicks, 1.0F);

		if (this.young){
			applyStatic(RoachAnims.BABY);
		}

		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();


		if (this.young){
			poseStack.scale(0.75F, 0.75F, 0.75F);
			poseStack.translate(0, 0.5F, 0);
		}else{
			poseStack.scale(1.5F, 1.5F, 1.5F);
			poseStack.translate(0, -0.5F, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}
}