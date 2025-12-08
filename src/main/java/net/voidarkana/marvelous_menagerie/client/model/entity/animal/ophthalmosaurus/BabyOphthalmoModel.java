package net.voidarkana.marvelous_menagerie.client.model.entity.animal.ophthalmosaurus;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.OphthalmoAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Ophthalmosaurus;

public class BabyOphthalmoModel<T extends Ophthalmosaurus> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart body;
	private final ModelPart f_flipper_r;
	private final ModelPart f_flipper_l;
	private final ModelPart b_flipper_r;
	private final ModelPart b_flipper_l;
	private final ModelPart tail;
	private final ModelPart tail_fin;

	public BabyOphthalmoModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		this.body = this.swim_control.getChild("body");
		this.f_flipper_r = this.body.getChild("f_flipper_r");
		this.f_flipper_l = this.body.getChild("f_flipper_l");
		this.b_flipper_r = this.body.getChild("b_flipper_r");
		this.b_flipper_l = this.body.getChild("b_flipper_l");
		this.tail = this.body.getChild("tail");
		this.tail_fin = this.tail.getChild("tail_fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -6.0F, 6.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-4.0F, -2.0F, -5.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(19, 24).addBox(-1.0F, 1.0F, -12.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -3.0F, -2.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.25F, 2.5F, -0.3927F, 0.0F, 0.0F));

		PartDefinition f_flipper_r = body.addOrReplaceChild("f_flipper_r", CubeListBuilder.create().texOffs(30, 21).addBox(-3.0F, -1.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 4.0F, -2.5F));

		PartDefinition f_flipper_l = body.addOrReplaceChild("f_flipper_l", CubeListBuilder.create().texOffs(7, 31).addBox(0.0F, -1.0F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 4.0F, -2.5F));

		PartDefinition b_flipper_r = body.addOrReplaceChild("b_flipper_r", CubeListBuilder.create().texOffs(0, 7).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 4.0F, 4.0F));

		PartDefinition b_flipper_l = body.addOrReplaceChild("b_flipper_l", CubeListBuilder.create().texOffs(30, 26).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 4.0F, 4.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(25, 0).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 6.0F));

		PartDefinition tail_fin = tail.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(0, 30).addBox(-0.5F, -5.0F, 0.0F, 1.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(Ophthalmosaurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){
			this.animateWalk(OphthalmoAnims.SWIM, limbSwing, limbSwingAmount*4f, 1.5f, 3f);

			this.swim_control.xRot = headPitch * ((float)Math.PI / 180F);

		}else {
			this.swim_control.resetPose();
		}

		this.animateIdle(entity.idleAnimationState, OphthalmoAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, OphthalmoAnims.FLOP, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));
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