package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.DawnHorseAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.DawnHorse;

public class DawnHorseModel<T extends DawnHorse> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart leftEar;
	private final ModelPart rightEar;
	private final ModelPart tail;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;

	public DawnHorseModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.head = this.body.getChild("head");
		this.leftEar = this.head.getChild("leftEar");
		this.rightEar = this.head.getChild("rightEar");
		this.tail = this.body.getChild("tail");
		this.leftArm = this.body_main.getChild("leftArm");
		this.rightArm = this.body_main.getChild("rightArm");
		this.leftLeg = this.body_main.getChild("leftLeg");
		this.rightLeg = this.body_main.getChild("rightLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -5.5F, 0.5F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.5F, -3.5F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.1F))
		.texOffs(0, 0).addBox(-0.5F, -3.5F, -3.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 12).addBox(-1.5F, -1.0F, -7.0F, 3.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(13, 12).addBox(-0.5F, -2.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -3.5F));

		PartDefinition leftEar = head.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offset(1.5F, -1.0F, -2.5F));

		PartDefinition rightEar = head.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(0, 13).addBox(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.001F)), PartPose.offset(-1.5F, -1.0F, -2.5F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(2, 22).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -1.5F, 3.5F));

		PartDefinition leftArm = body_main.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.5F, -2.5F));

		PartDefinition rightArm = body_main.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.5F, 2.5F, -2.5F));

		PartDefinition leftLeg = body_main.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 22).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.5F, 2.5F));

		PartDefinition rightLeg = body_main.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-0.5F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.5F, 2.5F, 2.5F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(DawnHorse entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);


		animateWalk(DawnHorseAnims.RUN, limbSwing, limbSwingAmount, 2f, 1);
//		if (entity.isSprinting()){
//			animateWalk(DawnHorseAnims.RUN, limbSwing, limbSwingAmount, 2f, 1);
//		}else {
//			animateWalk(DawnHorseAnims.WALK, limbSwing, limbSwingAmount, 2, 2.5f);
//		}

		this.animateIdle(entity.idleAnimationState, DawnHorseAnims.IDLE, ageInTicks, 1.0f, 1-Math.abs(limbSwingAmount));

		this.animate(entity.idleEarsState, DawnHorseAnims.IDLE_EARS, ageInTicks, 1.0F);

		this.animate(entity.idleTailState, DawnHorseAnims.IDLE_TAIL, ageInTicks, 1.0F);


		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
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