package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.AnomalocarisAnims;
import net.voidarkana.marvelous_menagerie.client.animations.PikaiaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Pikaia;

public class PikaiaModel<T extends Pikaia> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart antennae_l;
	private final ModelPart antennae_r;
	private final ModelPart gill1;
	private final ModelPart gill2;
	private final ModelPart gill3;
	private final ModelPart tail_rot;
	private final ModelPart tail;
	private final ModelPart tail_tip_rot;
	private final ModelPart tail_tip;

	public PikaiaModel(ModelPart root) {
        super(0.6f, 0.9f);
        this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		this.body = this.swim_control.getChild("body");
		this.head = this.body.getChild("head");
		this.antennae_l = this.head.getChild("antennae_l");
		this.antennae_r = this.head.getChild("antennae_r");
		this.gill1 = this.head.getChild("gill1");
		this.gill2 = this.head.getChild("gill2");
		this.gill3 = this.head.getChild("gill3");
		this.tail_rot = this.body.getChild("tail_rot");
		this.tail = this.tail_rot.getChild("tail");
		this.tail_tip_rot = this.tail.getChild("tail_tip_rot");
		this.tail_tip = this.tail_tip_rot.getChild("tail_tip");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, -3.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(18, 0).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition antennae_l = head.addOrReplaceChild("antennae_l", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -2.5F, -2.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(1.0F, -1.0F, -3.0F));

		PartDefinition antennae_r = head.addOrReplaceChild("antennae_r", CubeListBuilder.create().texOffs(9, 0).addBox(0.0F, -2.5F, -2.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(-1.0F, -1.0F, -3.0F));

		PartDefinition gill1 = head.addOrReplaceChild("gill1", CubeListBuilder.create().texOffs(12, 0).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -1.5F, -2.5F));

		PartDefinition gill2 = head.addOrReplaceChild("gill2", CubeListBuilder.create().texOffs(9, 6).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -1.5F, -1.5F));

		PartDefinition gill3 = head.addOrReplaceChild("gill3", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -1.5F, -0.5F));

		PartDefinition tail_rot = body.addOrReplaceChild("tail_rot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = tail_rot.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(9, 6).addBox(0.0F, -2.5F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_tip_rot = tail.addOrReplaceChild("tail_tip_rot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition tail_tip = tail_tip_rot.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.5F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Pikaia entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){
			this.animateWalk(PikaiaAnims.SWIM, limbSwing, limbSwingAmount, 2f, 3f);

			this.swim_control.xRot = headPitch * ((float)Math.PI / 180F);

			this.tail_rot.yRot = -entity.currentRoll;

			this.tail_tip_rot.yRot = -entity.currentRoll;

		}else {
			this.swim_control.resetPose();
			this.tail_rot.resetPose();
			this.tail_tip_rot.resetPose();
		}

		this.animateIdle(entity.idleAnimationState, AnomalocarisAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, AnomalocarisAnims.FLOP, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

		poseStack.scale(0.6F, 0.6F, 0.6F);

		poseStack.translate(0, 0.8F, 0);

		if (this.young){
			poseStack.scale(0.6f, 0.6f, 0.6f);
			poseStack.translate(0, 1.25F, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}

}