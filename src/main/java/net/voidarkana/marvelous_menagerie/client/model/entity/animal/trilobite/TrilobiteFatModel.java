package net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.AnomalocarisAnims;
import net.voidarkana.marvelous_menagerie.client.animations.HallucigeniaAnims;
import net.voidarkana.marvelous_menagerie.client.animations.TrilobiteAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Trilobite;

public class TrilobiteFatModel<T extends Trilobite> extends MarvelousModel<T> {

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
	private final ModelPart butt;
	private final ModelPart head;
	private final ModelPart whisker_l_overlay;
	private final ModelPart whisker_l;
	private final ModelPart whisker_r_overlay;
	private final ModelPart whisker_r;
	private final ModelPart eyes;

	public TrilobiteFatModel(ModelPart root) {
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
		this.butt = this.body.getChild("butt");
		this.head = this.body.getChild("head");
		this.whisker_l_overlay = this.head.getChild("whisker_l_overlay");
		this.whisker_l = this.whisker_l_overlay.getChild("whisker_l");
		this.whisker_r_overlay = this.head.getChild("whisker_r_overlay");
		this.whisker_r = this.whisker_r_overlay.getChild("whisker_r");
		this.eyes = this.head.getChild("eyes");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 23.65F, 1.0F));

		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 20).addBox(-3.0F, -1.0F, -2.0F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(32, 7).addBox(3.0F, 0.0F, -2.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(26, 23).addBox(-5.0F, 0.0F, -2.0F, 2.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -1.0F));

		PartDefinition legs_l = body.addOrReplaceChild("legs_l", CubeListBuilder.create(), PartPose.offset(1.0F, -0.15F, 1.75F));

		PartDefinition leg_1_l = legs_l.addOrReplaceChild("leg_1_l", CubeListBuilder.create().texOffs(31, 17).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.1F, 0.15F, -2.25F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leg_2_l = legs_l.addOrReplaceChild("leg_2_l", CubeListBuilder.create().texOffs(11, 36).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.1F, 0.15F, -0.75F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leg_3_l = legs_l.addOrReplaceChild("leg_3_l", CubeListBuilder.create().texOffs(38, 17).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.1F, 0.15F, 0.75F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leg_4_l = legs_l.addOrReplaceChild("leg_4_l", CubeListBuilder.create().texOffs(22, 38).addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.1F, 0.15F, 2.25F, 0.0F, 0.0F, 0.7854F));

		PartDefinition legs_r = body.addOrReplaceChild("legs_r", CubeListBuilder.create(), PartPose.offset(-1.0F, -0.15F, 1.75F));

		PartDefinition leg_1_r = legs_r.addOrReplaceChild("leg_1_r", CubeListBuilder.create().texOffs(29, 38).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.1F, 0.15F, -2.25F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leg_2_r = legs_r.addOrReplaceChild("leg_2_r", CubeListBuilder.create().texOffs(38, 33).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.1F, 0.15F, -0.75F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leg_3_r = legs_r.addOrReplaceChild("leg_3_r", CubeListBuilder.create().texOffs(38, 35).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.1F, 0.15F, 0.75F, 0.0F, 0.0F, -0.7854F));

		PartDefinition leg_4_r = legs_r.addOrReplaceChild("leg_4_r", CubeListBuilder.create().texOffs(36, 38).addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.1F, 0.15F, 2.25F, 0.0F, 0.0F, -0.7854F));

		PartDefinition butt = body.addOrReplaceChild("butt", CubeListBuilder.create().texOffs(27, 20).addBox(-5.0F, -1.0F, 0.0F, 10.0F, 1.0F, 2.0F, new CubeDeformation(0.05F))
		.texOffs(0, 8).addBox(-6.0F, 0.0F, -1.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 1.0F, 4.75F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -0.5F, -4.6667F, 14.0F, 0.0F, 7.0F, new CubeDeformation(0.001F))
		.texOffs(0, 29).addBox(-3.0F, -2.0F, -2.6667F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.1F))
		.texOffs(1, 14).addBox(-5.0F, -1.0F, -3.6667F, 10.0F, 1.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 1.0F, -2.0833F));

		PartDefinition whisker_l_overlay = head.addOrReplaceChild("whisker_l_overlay", CubeListBuilder.create(), PartPose.offset(1.5F, -1.0F, -3.7667F));

		PartDefinition whisker_l = whisker_l_overlay.addOrReplaceChild("whisker_l", CubeListBuilder.create().texOffs(11, 38).addBox(-0.5F, 0.0F, -2.9F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition whisker_r_overlay = head.addOrReplaceChild("whisker_r_overlay", CubeListBuilder.create(), PartPose.offset(-1.5F, -1.0F, -3.7667F));

		PartDefinition whisker_r = whisker_r_overlay.addOrReplaceChild("whisker_r", CubeListBuilder.create().texOffs(0, 36).addBox(-1.5F, 0.0F, -2.9F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(21, 33).addBox(-3.0F, -1.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.15F)), PartPose.offset(0.0F, -1.0F, -1.6667F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Trilobite entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(TrilobiteAnims.WALK_FAT, limbSwing, limbSwingAmount, 10f, 100);

		this.animateIdle(entity.idleAnimationState, TrilobiteAnims.IDLE_FAT, ageInTicks, 1, 1-Math.abs(limbSwingAmount));

		this.animate(entity.spinIdleAnimationState, TrilobiteAnims.SPIN_FAT, ageInTicks, 1.0F);

		applyStatic(TrilobiteAnims.BASE_FAT);
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