package net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.TrilobiteAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Trilobite;

public class TrilobiteIttyModel<T extends Trilobite> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart legs_l;
	private final ModelPart leg_1_l;
	private final ModelPart leg_2_l;
	private final ModelPart leg_3_l;
	private final ModelPart legs_r;
	private final ModelPart leg_1_r;
	private final ModelPart leg_2_r;
	private final ModelPart leg_3_r;
	private final ModelPart head;
	private final ModelPart eyes;
	private final ModelPart wisker_l_overlay;
	private final ModelPart wisker_l;
	private final ModelPart wisker_r_overlay;
	private final ModelPart wisker_r;

	public TrilobiteIttyModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.body = this.root.getChild("body");
		this.legs_l = this.body.getChild("legs_l");
		this.leg_1_l = this.legs_l.getChild("leg_1_l");
		this.leg_2_l = this.legs_l.getChild("leg_2_l");
		this.leg_3_l = this.legs_l.getChild("leg_3_l");
		this.legs_r = this.body.getChild("legs_r");
		this.leg_1_r = this.legs_r.getChild("leg_1_r");
		this.leg_2_r = this.legs_r.getChild("leg_2_r");
		this.leg_3_r = this.legs_r.getChild("leg_3_r");
		this.head = this.body.getChild("head");
		this.eyes = this.head.getChild("eyes");
		this.wisker_l_overlay = this.head.getChild("wisker_l_overlay");
		this.wisker_l = this.wisker_l_overlay.getChild("wisker_l");
		this.wisker_r_overlay = this.head.getChild("wisker_r_overlay");
		this.wisker_r = this.wisker_r_overlay.getChild("wisker_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 12).addBox(1.5F, -0.6F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(5, 12).addBox(-1.5F, -0.6F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 13).addBox(-1.5F, -0.6F, 2.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(11, 4).addBox(-1.5F, -0.6F, 0.0F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -0.5F));

		PartDefinition legs_l = body.addOrReplaceChild("legs_l", CubeListBuilder.create(), PartPose.offset(0.75F, -0.5F, 1.0F));

		PartDefinition leg_1_l = legs_l.addOrReplaceChild("leg_1_l", CubeListBuilder.create().texOffs(13, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));

		PartDefinition leg_2_l = legs_l.addOrReplaceChild("leg_2_l", CubeListBuilder.create().texOffs(3, 16).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_3_l = legs_l.addOrReplaceChild("leg_3_l", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition legs_r = body.addOrReplaceChild("legs_r", CubeListBuilder.create(), PartPose.offset(-0.75F, -0.5F, 1.0F));

		PartDefinition leg_1_r = legs_r.addOrReplaceChild("leg_1_r", CubeListBuilder.create().texOffs(10, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));

		PartDefinition leg_2_r = legs_r.addOrReplaceChild("leg_2_r", CubeListBuilder.create().texOffs(6, 16).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_3_r = legs_r.addOrReplaceChild("leg_3_r", CubeListBuilder.create().texOffs(16, 15).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(11, 7).addBox(-2.0F, -0.65F, 2.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
		.texOffs(0, 0).addBox(-3.0F, 0.35F, 1.0F, 5.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.5F, 0.05F, -3.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(11, 10).addBox(-1.5F, 0.1F, -0.5F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offset(-0.5F, -0.75F, 2.5F));

		PartDefinition wisker_l_overlay = head.addOrReplaceChild("wisker_l_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.25F, 2.0F));

		PartDefinition wisker_l = wisker_l_overlay.addOrReplaceChild("wisker_l", CubeListBuilder.create().texOffs(0, 8).addBox(-0.5F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition wisker_r_overlay = head.addOrReplaceChild("wisker_r_overlay", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.25F, 2.0F));

		PartDefinition wisker_r = wisker_r_overlay.addOrReplaceChild("wisker_r", CubeListBuilder.create().texOffs(0, 4).addBox(-1.5F, 0.0F, -3.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Trilobite entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(TrilobiteAnims.WALK_SMALL, limbSwing, limbSwingAmount, 15f, 100);

		this.animateIdle(entity.idleAnimationState, TrilobiteAnims.IDLE_SMALL, ageInTicks, 1, 1-Math.abs(limbSwingAmount));

		applyStatic(TrilobiteAnims.BASE_SMALL);
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