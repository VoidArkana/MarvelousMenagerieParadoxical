package net.voidarkana.marvelous_menagerie.client.model.entity.animal.anomalocaris;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.AnomalocarisAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anomalocaris;

public class BabyAnomalocarisModel<T extends Anomalocaris> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart body;
	private final ModelPart eye_l;
	private final ModelPart eye_r;
	private final ModelPart claw_r;
	private final ModelPart claw_l;
	private final ModelPart flaps_r;
	private final ModelPart flap_r_1;
	private final ModelPart flap_r_2;
	private final ModelPart flap_r_3;
	private final ModelPart flap_r_4;
	private final ModelPart flaps_l;
	private final ModelPart flap_l_1;
	private final ModelPart flap_l_2;
	private final ModelPart flap_l_3;
	private final ModelPart flap_l_4;
	private final ModelPart tail;
	private final ModelPart tail_fin;

	public BabyAnomalocarisModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		this.body = this.swim_control.getChild("body");
		this.eye_l = this.body.getChild("eye_l");
		this.eye_r = this.body.getChild("eye_r");
		this.claw_r = this.body.getChild("claw_r");
		this.claw_l = this.body.getChild("claw_l");
		this.flaps_r = this.body.getChild("flaps_r");
		this.flap_r_1 = this.flaps_r.getChild("flap_r_1");
		this.flap_r_2 = this.flaps_r.getChild("flap_r_2");
		this.flap_r_3 = this.flaps_r.getChild("flap_r_3");
		this.flap_r_4 = this.flaps_r.getChild("flap_r_4");
		this.flaps_l = this.body.getChild("flaps_l");
		this.flap_l_1 = this.flaps_l.getChild("flap_l_1");
		this.flap_l_2 = this.flaps_l.getChild("flap_l_2");
		this.flap_l_3 = this.flaps_l.getChild("flap_l_3");
		this.flap_l_4 = this.flaps_l.getChild("flap_l_4");
		this.tail = this.body.getChild("tail");
		this.tail_fin = this.tail.getChild("tail_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -4.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -4.5F, 6.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.5F));

		PartDefinition eye_l = body.addOrReplaceChild("eye_l", CubeListBuilder.create().texOffs(31, 0).addBox(-1.4839F, -4.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(37, 37).addBox(-0.5161F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.2208F, -1.2436F, -2.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition eye_r = body.addOrReplaceChild("eye_r", CubeListBuilder.create().texOffs(13, 31).addBox(-1.5F, -4.5F, -2.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(13, 39).addBox(-0.5F, -2.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.2322F, -1.2322F, -2.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition claw_r = body.addOrReplaceChild("claw_r", CubeListBuilder.create().texOffs(18, 12).addBox(0.0F, -2.0F, -3.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(-2.5F, 1.0F, -4.5F));

		PartDefinition claw_l = body.addOrReplaceChild("claw_l", CubeListBuilder.create().texOffs(18, 12).addBox(0.0F, -2.0F, -3.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(2.5F, 1.0F, -4.5F));

		PartDefinition flaps_r = body.addOrReplaceChild("flaps_r", CubeListBuilder.create(), PartPose.offset(-3.0F, 1.0F, 5.0F));

		PartDefinition flap_r_1 = flaps_r.addOrReplaceChild("flap_r_1", CubeListBuilder.create().texOffs(36, 17).addBox(-2.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, -6.5F));

		PartDefinition flap_r_2 = flaps_r.addOrReplaceChild("flap_r_2", CubeListBuilder.create().texOffs(36, 20).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, -5.0F));

		PartDefinition flap_r_3 = flaps_r.addOrReplaceChild("flap_r_3", CubeListBuilder.create().texOffs(36, 23).addBox(-3.0F, 0.0F, -1.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, -3.0F));

		PartDefinition flap_r_4 = flaps_r.addOrReplaceChild("flap_r_4", CubeListBuilder.create().texOffs(28, 37).addBox(-2.0F, 0.0F, -1.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition flaps_l = body.addOrReplaceChild("flaps_l", CubeListBuilder.create(), PartPose.offset(3.0F, 1.0F, 2.0F));

		PartDefinition flap_l_1 = flaps_l.addOrReplaceChild("flap_l_1", CubeListBuilder.create().texOffs(28, 31).addBox(-1.0F, 0.0F, -2.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, -3.5F));

		PartDefinition flap_l_2 = flaps_l.addOrReplaceChild("flap_l_2", CubeListBuilder.create().texOffs(28, 34).addBox(0.0F, 0.0F, -1.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition flap_l_3 = flaps_l.addOrReplaceChild("flap_l_3", CubeListBuilder.create().texOffs(36, 14).addBox(0.0F, 0.0F, -1.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition flap_l_4 = flaps_l.addOrReplaceChild("flap_l_4", CubeListBuilder.create().texOffs(36, 26).addBox(0.0F, 0.0F, -1.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(31, 8).addBox(-1.0F, -1.5F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 4.5F));

		PartDefinition tail_fin = tail.addOrReplaceChild("tail_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition tail_fin_l_r1 = tail_fin.addOrReplaceChild("tail_fin_l_r1", CubeListBuilder.create().texOffs(4, 25).addBox(-2.0F, 0.0F, -3.5F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(1.75F, -1.0F, 2.5F, 0.0F, 0.0F, -0.7854F));

		PartDefinition tail_fin_r_r1 = tail_fin.addOrReplaceChild("tail_fin_r_r1", CubeListBuilder.create().texOffs(2, 17).addBox(-1.0F, 0.0F, -3.5F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-1.75F, -1.0F, 2.5F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){
			this.animateWalk(AnomalocarisAnims.SWIM, limbSwing, limbSwingAmount*4f, 1.5f, 3f);

			this.animate(entity.shakeAnimationState, AnomalocarisAnims.SHAKE, ageInTicks, 1.0F);

			this.animate(entity.attackAnimationState, AnomalocarisAnims.ATTACK, ageInTicks, 1.0F);
		}else {
			this.swim_control.xRot = headPitch * ((float)Math.PI / 180F);
		}

		this.animateIdle(entity.idleAnimationState, AnomalocarisAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, AnomalocarisAnims.FLOP, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));
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