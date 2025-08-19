package net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.TrilobiteMidAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Trilobite;

public class TrilobiteMidModel<T extends Trilobite> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart legs_l;
	private final ModelPart leg_1_l;
	private final ModelPart leg_2_l;
	private final ModelPart leg_3_l;
	private final ModelPart leg_4_l;
	private final ModelPart legs_r;
	private final ModelPart leg_1_r;
	private final ModelPart leg_2_r;
	private final ModelPart leg_3_r;
	private final ModelPart leg_4_r;
	private final ModelPart head;
	private final ModelPart eyes;
	private final ModelPart horn;
	private final ModelPart wisker_l_overlay;
	private final ModelPart wisker_l;
	private final ModelPart wisker_r_overlay;
	private final ModelPart wisker_r;

	public TrilobiteMidModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.legs_l = this.body.getChild("legs_l");
		this.leg_1_l = this.legs_l.getChild("leg_1_l");
		this.leg_2_l = this.legs_l.getChild("leg_2_l");
		this.leg_3_l = this.legs_l.getChild("leg_3_l");
		this.leg_4_l = this.legs_l.getChild("leg_4_l");
		this.legs_r = this.body.getChild("legs_r");
		this.leg_1_r = this.legs_r.getChild("leg_1_r");
		this.leg_2_r = this.legs_r.getChild("leg_2_r");
		this.leg_3_r = this.legs_r.getChild("leg_3_r");
		this.leg_4_r = this.legs_r.getChild("leg_4_r");
		this.head = this.body.getChild("head");
		this.eyes = this.head.getChild("eyes");
		this.horn = this.head.getChild("horn");
		this.wisker_l_overlay = this.head.getChild("wisker_l_overlay");
		this.wisker_l = this.wisker_l_overlay.getChild("wisker_l");
		this.wisker_r_overlay = this.head.getChild("wisker_r_overlay");
		this.wisker_r = this.wisker_r_overlay.getChild("wisker_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(14, 33).addBox(-6.5F, 0.4F, 0.0F, 13.0F, 0.0F, 8.0F, new CubeDeformation(0.001F))
		.texOffs(27, 9).addBox(2.5F, -0.5F, -2.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(18, 38).addBox(1.0F, -4.5F, -1.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(18, 38).addBox(-1.0F, -4.5F, -1.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(21, 27).addBox(-2.5F, -0.5F, -2.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(23, 16).addBox(-2.5F, -0.5F, 3.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.001F))
		.texOffs(0, 22).addBox(-2.5F, -0.5F, -2.0F, 5.0F, 0.0F, 5.0F, new CubeDeformation(0.001F))
		.texOffs(23, 18).addBox(-1.5F, -0.5F, 3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.7F, 0.0F));

		PartDefinition legs_l = body.addOrReplaceChild("legs_l", CubeListBuilder.create(), PartPose.offset(1.5F, -0.3F, 1.0F));

		PartDefinition leg_1_l = legs_l.addOrReplaceChild("leg_1_l", CubeListBuilder.create().texOffs(18, 28).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.55F, -1.5F));

		PartDefinition leg_2_l = legs_l.addOrReplaceChild("leg_2_l", CubeListBuilder.create().texOffs(18, 30).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.05F, -0.5F));

		PartDefinition leg_3_l = legs_l.addOrReplaceChild("leg_3_l", CubeListBuilder.create().texOffs(0, 31).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.05F, 0.5F));

		PartDefinition leg_4_l = legs_l.addOrReplaceChild("leg_4_l", CubeListBuilder.create().texOffs(3, 31).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.05F, 1.5F));

		PartDefinition legs_r = body.addOrReplaceChild("legs_r", CubeListBuilder.create(), PartPose.offset(-1.5F, -0.3F, 1.0F));

		PartDefinition leg_1_r = legs_r.addOrReplaceChild("leg_1_r", CubeListBuilder.create().texOffs(6, 31).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.55F, -1.5F));

		PartDefinition leg_2_r = legs_r.addOrReplaceChild("leg_2_r", CubeListBuilder.create().texOffs(9, 31).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.05F, -0.5F));

		PartDefinition leg_3_r = legs_r.addOrReplaceChild("leg_3_r", CubeListBuilder.create().texOffs(12, 31).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.05F, 0.5F));

		PartDefinition leg_4_r = legs_r.addOrReplaceChild("leg_4_r", CubeListBuilder.create().texOffs(15, 31).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.05F, 1.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(24, 23).addBox(-2.5F, -0.3F, -3.125F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(13, 42).addBox(1.0F, -3.3F, -3.125F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(13, 42).addBox(-1.0F, -3.3F, -3.125F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.5F, 0.6F, -5.125F, 13.0F, 0.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -0.2F, 0.125F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 35).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 1.0F, 2.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, -0.3F, -2.125F));

		PartDefinition horn = head.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(26, 41).addBox(-3.5F, 0.0F, -7.0F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -0.3F, -3.125F, -0.7854F, 0.0F, 0.0F));

		PartDefinition wisker_l_overlay = head.addOrReplaceChild("wisker_l_overlay", CubeListBuilder.create(), PartPose.offset(1.0F, 0.5F, -3.125F));

		PartDefinition wisker_l = wisker_l_overlay.addOrReplaceChild("wisker_l", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, 0.0F, -5.0F, 6.0F, 0.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wisker_r_overlay = head.addOrReplaceChild("wisker_r_overlay", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.5F, -3.125F));

		PartDefinition wisker_r = wisker_r_overlay.addOrReplaceChild("wisker_r", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-5.5F, 0.0F, -5.0F, 6.0F, 0.0F, 5.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(Trilobite entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isSprinting()){
			this.animateWalk(TrilobiteMidAnims.RUN_MID, limbSwing, limbSwingAmount, 10f, 100);
		}else {
			this.animateWalk(TrilobiteMidAnims.WALK_MID, limbSwing, limbSwingAmount, 10f, 100);
		}

		this.animateIdle(entity.idleAnimationState, TrilobiteMidAnims.IDLE_MID, ageInTicks, 1, 1-Math.abs(limbSwingAmount));

		applyStatic(TrilobiteMidAnims.BASE_MID);
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