package net.voidarkana.marvelous_menagerie.client.model.entity.animal.steller_sea_cow;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.AnomalocarisAnims;
import net.voidarkana.marvelous_menagerie.client.animations.BabyStellerAnims;
import net.voidarkana.marvelous_menagerie.client.animations.StellerAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.StellerSeaCow;

public class StellerModel<T extends StellerSeaCow> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart main;
	private final ModelPart body;
	private final ModelPart neck_rot;
	private final ModelPart neck;
	private final ModelPart head_rot;
	private final ModelPart head;
	private final ModelPart flipper_l;
	private final ModelPart flipper_r;
	private final ModelPart tail_rot;
	private final ModelPart tail;
	private final ModelPart tail_tip_rot;
	private final ModelPart tail_fin;

	public StellerModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.main = this.swim_rot.getChild("main");
		this.body = this.main.getChild("body");
		this.neck_rot = this.body.getChild("neck_rot");
		this.neck = this.neck_rot.getChild("neck");
		this.head_rot = this.neck.getChild("head_rot");
		this.head = this.head_rot.getChild("head");
		this.flipper_l = this.body.getChild("flipper_l");
		this.flipper_r = this.body.getChild("flipper_r");
		this.tail_rot = this.body.getChild("tail_rot");
		this.tail = this.tail_rot.getChild("tail");
		this.tail_tip_rot = this.tail.getChild("tail_tip_rot");
		this.tail_fin = this.tail_tip_rot.getChild("tail_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -18.0F, -22.0F));

		PartDefinition main = swim_rot.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 22.0F));

		PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-18.0F, -18.0F, -11.0F, 36.0F, 36.0F, 68.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -23.0F));

		PartDefinition neck_rot = body.addOrReplaceChild("neck_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, -10.5F));

		PartDefinition neck = neck_rot.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(79, 104).addBox(-12.0F, -13.5F, -10.5F, 24.0F, 25.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition head_rot = neck.addOrReplaceChild("head_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -3.25F, -10.0F));

		PartDefinition head = head_rot.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.25F, -7.5F, 12.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 31).addBox(-5.0F, -6.25F, -13.5F, 10.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition flipper_l = body.addOrReplaceChild("flipper_l", CubeListBuilder.create().texOffs(27, 23).addBox(0.0F, -2.0F, -3.5F, 10.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(18.0F, 16.0F, 0.5F));

		PartDefinition flipper_r = body.addOrReplaceChild("flipper_r", CubeListBuilder.create().texOffs(0, 50).addBox(-10.0F, -2.0F, -3.5F, 10.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-18.0F, 16.0F, 0.5F));

		PartDefinition tail_rot = body.addOrReplaceChild("tail_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 56.5F));

		PartDefinition tail = tail_rot.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(1, 119).addBox(-7.0F, -7.0F, -1.5F, 14.0F, 14.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_tip_rot = tail.addOrReplaceChild("tail_tip_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 20.5F));

		PartDefinition tail_fin = tail_tip_rot.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(140, 128).addBox(-15.0F, -1.0F, -1.0F, 30.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 240, 240);
	}

	@Override
	public void setupAnim(StellerSeaCow entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isFromInventory())
			this.applyStatic(StellerAnims.POSE);

		if (entity.isInWaterOrBubble()){
			this.animateWalk(StellerAnims.SWIM, limbSwing, limbSwingAmount*4f, 1.5f, 3f);

			this.swim_rot.xRot = headPitch * ((float)Math.PI / 180F)/8;
		}else {
			this.swim_rot.resetPose();
		}

		this.animateIdle(entity.idleAnimationState, StellerAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, StellerAnims.BEACHED, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));

		this.head.yRot = (netHeadYaw * (float)Math.PI / 180F)/2;
		this.head.xRot = (headPitch * (float)Math.PI / 180F)/2;
		this.neck_rot.yRot = (netHeadYaw * (float)Math.PI / 180F)/2;
		this.neck_rot.xRot = (headPitch * (float)Math.PI / 180F)/2;
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