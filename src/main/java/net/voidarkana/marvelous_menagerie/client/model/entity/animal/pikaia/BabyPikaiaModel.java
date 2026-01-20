package net.voidarkana.marvelous_menagerie.client.model.entity.animal.pikaia;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.AnomalocarisAnims;
import net.voidarkana.marvelous_menagerie.client.animations.PikaiaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Pikaia;

public class BabyPikaiaModel<T extends Pikaia> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart head;
	private final ModelPart gill3;
	private final ModelPart gill1;
	private final ModelPart antennae_l;
	private final ModelPart antennae_r;

	public BabyPikaiaModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		this.body = this.swim_control.getChild("body");
		this.tail = this.body.getChild("tail");
		this.head = this.body.getChild("head");
		this.gill3 = this.head.getChild("gill3");
		this.gill1 = this.head.getChild("gill1");
		this.antennae_l = this.head.getChild("antennae_l");
		this.antennae_r = this.head.getChild("antennae_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -3.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, -1).addBox(0.0F, -3.0F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition gill3 = head.addOrReplaceChild("gill3", CubeListBuilder.create().texOffs(0, 3).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -0.25F));

		PartDefinition gill1 = head.addOrReplaceChild("gill1", CubeListBuilder.create().texOffs(0, 1).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -0.75F));

		PartDefinition antennae_l = head.addOrReplaceChild("antennae_l", CubeListBuilder.create().texOffs(9, 9).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, -2.0F));

		PartDefinition antennae_r = head.addOrReplaceChild("antennae_r", CubeListBuilder.create().texOffs(9, 12).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -1.0F, -2.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(Pikaia entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){
			this.animateWalk(PikaiaAnims.SWIM, limbSwing, limbSwingAmount, 2f, 3f);
		}

		this.swim_control.xRot = Mth.lerp( entity.getOutOfWaterTicks()/5f, headPitch * ((float)Math.PI / 180F), 0);

		this.animateIdle(entity.idleAnimationState, PikaiaAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, PikaiaAnims.FLOP, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));

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