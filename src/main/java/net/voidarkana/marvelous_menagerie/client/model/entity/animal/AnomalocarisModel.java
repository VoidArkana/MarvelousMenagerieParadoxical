package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.AnomalocarisAnims;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anomalocaris;

public class AnomalocarisModel<T extends Anomalocaris> extends AgeableHierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart root_rotation;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart flap_l_1;
	private final ModelPart flap_l_2;
	private final ModelPart flap_r_1;
	private final ModelPart flap_r_2;
	private final ModelPart eye_l;
	private final ModelPart eye_r;
	private final ModelPart claw_r;
	private final ModelPart claw_l;
	private final ModelPart flaps_r;
	private final ModelPart flap_r_3;
	private final ModelPart flap_r_4;
	private final ModelPart flap_r_5;
	private final ModelPart flaps_l;
	private final ModelPart flap_l_3;
	private final ModelPart flap_l_4;
	private final ModelPart flap_l_5;
	private final ModelPart tail;
	private final ModelPart tail_fin;

	public AnomalocarisModel(ModelPart root) {
        super(0.6f, 1);
        this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		this.root_rotation = this.swim_control.getChild("root_rotation");
		this.body = this.root_rotation.getChild("body");
		this.head = this.body.getChild("head");
		this.flap_l_1 = this.head.getChild("flap_l_1");
		this.flap_l_2 = this.head.getChild("flap_l_2");
		this.flap_r_1 = this.head.getChild("flap_r_1");
		this.flap_r_2 = this.head.getChild("flap_r_2");
		this.eye_l = this.head.getChild("eye_l");
		this.eye_r = this.head.getChild("eye_r");
		this.claw_r = this.head.getChild("claw_r");
		this.claw_l = this.head.getChild("claw_l");
		this.flaps_r = this.body.getChild("flaps_r");
		this.flap_r_3 = this.flaps_r.getChild("flap_r_3");
		this.flap_r_4 = this.flaps_r.getChild("flap_r_4");
		this.flap_r_5 = this.flaps_r.getChild("flap_r_5");
		this.flaps_l = this.body.getChild("flaps_l");
		this.flap_l_3 = this.flaps_l.getChild("flap_l_3");
		this.flap_l_4 = this.flaps_l.getChild("flap_l_4");
		this.flap_l_5 = this.flaps_l.getChild("flap_l_5");
		this.tail = this.body.getChild("tail");
		this.tail_fin = this.tail.getChild("tail_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, -0.5F));

		PartDefinition root_rotation = swim_control.addOrReplaceChild("root_rotation", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = root_rotation.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -2.5F, 0.0F, 8.0F, 5.0F, 9.0F, new CubeDeformation(-0.02F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, -2.5F, -8.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition flap_l_1 = head.addOrReplaceChild("flap_l_1", CubeListBuilder.create().texOffs(34, 23).addBox(-1.0F, 0.0F, -2.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(4.0F, 1.5F, -5.0F));

		PartDefinition flap_l_2 = head.addOrReplaceChild("flap_l_2", CubeListBuilder.create().texOffs(0, 47).addBox(0.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(4.0F, 1.5F, -1.5F));

		PartDefinition flap_r_1 = head.addOrReplaceChild("flap_r_1", CubeListBuilder.create().texOffs(6, 29).addBox(-4.0F, 0.0F, -2.0F, 5.0F, 0.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(-4.0F, 1.5F, -5.0F));

		PartDefinition flap_r_2 = head.addOrReplaceChild("flap_r_2", CubeListBuilder.create().texOffs(42, 4).addBox(-5.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(-4.0F, 1.5F, -1.5F));

		PartDefinition eye_l = head.addOrReplaceChild("eye_l", CubeListBuilder.create().texOffs(36, 35).addBox(-1.4839F, -4.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-0.5161F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2208F, -1.7436F, -5.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition eye_r = head.addOrReplaceChild("eye_r", CubeListBuilder.create().texOffs(13, 35).addBox(-1.5F, -4.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2322F, -1.7322F, -5.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition claw_r = head.addOrReplaceChild("claw_r", CubeListBuilder.create().texOffs(27, 23).addBox(0.0F, -2.0F, -5.0F, 0.0F, 9.0F, 6.0F, new CubeDeformation(0.001F)), PartPose.offset(-2.5F, 0.5F, -8.0F));

		PartDefinition claw_l = head.addOrReplaceChild("claw_l", CubeListBuilder.create().texOffs(0, 29).addBox(0.0F, -2.0F, -5.0F, 0.0F, 9.0F, 6.0F, new CubeDeformation(0.001F)), PartPose.offset(2.5F, 0.5F, -8.0F));

		PartDefinition flaps_r = body.addOrReplaceChild("flaps_r", CubeListBuilder.create(), PartPose.offset(-4.0F, 1.5F, 1.5F));

		PartDefinition flap_r_3 = flaps_r.addOrReplaceChild("flap_r_3", CubeListBuilder.create().texOffs(39, 28).addBox(-6.0F, 0.0F, -1.5F, 6.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition flap_r_4 = flaps_r.addOrReplaceChild("flap_r_4", CubeListBuilder.create().texOffs(38, 0).addBox(-5.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition flap_r_5 = flaps_r.addOrReplaceChild("flap_r_5", CubeListBuilder.create().texOffs(34, 8).addBox(-4.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition flaps_l = body.addOrReplaceChild("flaps_l", CubeListBuilder.create(), PartPose.offset(4.0F, 1.5F, 1.5F));

		PartDefinition flap_l_3 = flaps_l.addOrReplaceChild("flap_l_3", CubeListBuilder.create().texOffs(39, 43).addBox(0.0F, 0.0F, -1.5F, 6.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition flap_l_4 = flaps_l.addOrReplaceChild("flap_l_4", CubeListBuilder.create().texOffs(25, 43).addBox(0.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition flap_l_5 = flaps_l.addOrReplaceChild("flap_l_5", CubeListBuilder.create().texOffs(10, 43).addBox(-1.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(26, 0).addBox(-2.0F, -1.5F, 0.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition tail_fin = tail.addOrReplaceChild("tail_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition tail_fin_l_r1 = tail_fin.addOrReplaceChild("tail_fin_l_r1", CubeListBuilder.create().texOffs(41, 15).addBox(-2.0F, 0.0F, -3.5F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(1.75F, -1.0F, 2.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition tail_fin_r_r1 = tail_fin.addOrReplaceChild("tail_fin_r_r1", CubeListBuilder.create().texOffs(25, 15).addBox(-2.0F, 0.0F, -3.5F, 4.0F, 0.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.75F, -1.0F, 2.5F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Anomalocaris entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(entity.swimAnimationState, AnomalocarisAnims.SWIM, ageInTicks, limbSwingAmount*4f);

		this.animate(entity.idleAnimationState, AnomalocarisAnims.IDLE, ageInTicks, 1-Math.abs(limbSwingAmount));

		this.animate(entity.flopAnimationState, AnomalocarisAnims.FLOP, ageInTicks, 1.0F);

		this.animate(entity.shakeAnimationState, AnomalocarisAnims.SHAKE, ageInTicks, 1.0F);

		if (this.young){
			applyStatic(AnomalocarisAnims.BABY);
		}

		this.swim_control.xRot = headPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

		if (this.young){
			poseStack.scale(0.6f, 0.6f, 0.6f);
			poseStack.translate(0, 0.6, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}
}