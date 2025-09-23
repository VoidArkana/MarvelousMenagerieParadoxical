package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.LeptiAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Leptictidium;

public class LeptiModel<T extends Leptictidium> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart leftEar;
	private final ModelPart rightEar;
	private final ModelPart nose;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart tail;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public LeptiModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.leftEar = this.body.getChild("leftEar");
		this.rightEar = this.body.getChild("rightEar");
		this.nose = this.body.getChild("nose");
		this.leftArm = this.body.getChild("leftArm");
		this.rightArm = this.body.getChild("rightArm");
		this.tail = this.body.getChild("tail");
		this.leftLeg = this.body_main.getChild("leftLeg");
		this.rightLeg = this.body_main.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -3.05F, 2.0F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -3.0F, -5.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftEar = body.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(2, 22).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0025F)), PartPose.offset(1.0F, -3.0F, -3.0F));

		PartDefinition rightEar = body.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(2, 22).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0025F)), PartPose.offset(-1.0F, -3.0F, -3.0F));

		PartDefinition nose = body.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(8, 16).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -5.0F));

		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0025F)), PartPose.offset(1.0F, 0.0F, -3.0F));

		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0025F)), PartPose.offset(-1.0F, 0.0F, -3.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(9, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 1.0F));

		PartDefinition leftLeg = body_main.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, 0.0F, -3.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition rightLeg = body_main.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, 0.0F, -3.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Leptictidium entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isSprinting()){
			animateWalk(LeptiAnims.RUN, limbSwing, limbSwingAmount, 1.5f, 1);
		}else {
			animateWalk(LeptiAnims.WALK, limbSwing, limbSwingAmount, 2, 2.5f);
		}

		this.animateIdle(entity.idleAnimationState, LeptiAnims.IDLE, ageInTicks, 1.0f, 1-Math.abs(limbSwingAmount));

		this.animate(entity.idleNoseState, LeptiAnims.SNIFF, ageInTicks, 1.0F);
		this.animate(entity.idleTiltState, LeptiAnims.LOOKDOWN, ageInTicks, 1.0F);

		this.body.xRot = headPitch * ((float)Math.PI / 180F);
		this.body.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

		if (this.young){
			poseStack.scale(0.6f, 0.6f, 0.6f);
			poseStack.translate(0, 1, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();	}

	@Override
	public ModelPart root() {
		return root;
	}
}