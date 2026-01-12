package net.voidarkana.marvelous_menagerie.client.model.entity.animal.steller_sea_cow;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.BabyStellerAnims;
import net.voidarkana.marvelous_menagerie.client.animations.OphthalmoAnims;
import net.voidarkana.marvelous_menagerie.client.animations.StellerAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.StellerSeaCow;

public class BabyStellerModel<T extends StellerSeaCow> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart main;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart flipper_l;
	private final ModelPart flipper_r;
	private final ModelPart tail;
	private final ModelPart tail_fin;

	public BabyStellerModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.main = this.swim_rot.getChild("main");
		this.body = this.main.getChild("body");
		this.head = this.body.getChild("head");
		this.flipper_l = this.body.getChild("flipper_l");
		this.flipper_r = this.body.getChild("flipper_r");
		this.tail = this.body.getChild("tail");
		this.tail_fin = this.tail.getChild("tail_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -5.0F));

		PartDefinition main = swim_rot.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, -3.0F, 10.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 25).addBox(-3.5F, -4.25F, -7.5F, 7.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.25F, -2.5F));

		PartDefinition flipper_l = body.addOrReplaceChild("flipper_l", CubeListBuilder.create().texOffs(35, 5).addBox(0.0F, -1.0F, -1.5F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 4.0F, 0.0F));

		PartDefinition flipper_r = body.addOrReplaceChild("flipper_r", CubeListBuilder.create().texOffs(35, 0).addBox(-5.0F, -1.0F, -1.5F, 5.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 4.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(23, 34).addBox(-2.5F, -3.0F, -0.5F, 5.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 10.5F));

		PartDefinition tail_fin = tail.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(31, 25).addBox(-5.0F, -0.5F, 0.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 5.5F));

		return LayerDefinition.create(meshdefinition, 80, 80);
	}

	@Override
	public void setupAnim(StellerSeaCow entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isFromInventory())
			this.applyStatic(BabyStellerAnims.POSE);

		if (entity.isInWaterOrBubble()){
			this.animateWalk(BabyStellerAnims.SWIM, limbSwing, limbSwingAmount*4f, 1.5f, 3f);

			this.swim_rot.xRot = headPitch * ((float)Math.PI / 180F)/2;

		}else {
			this.swim_rot.resetPose();
		}

		this.animateIdle(entity.idleAnimationState, BabyStellerAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, BabyStellerAnims.FLOP, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));


		this.head.yRot = netHeadYaw * (float)Math.PI / 180F;
		this.head.xRot = headPitch * (float)Math.PI / 180F;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

//			poseStack.translate(0, 25, 0);

			root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}
}