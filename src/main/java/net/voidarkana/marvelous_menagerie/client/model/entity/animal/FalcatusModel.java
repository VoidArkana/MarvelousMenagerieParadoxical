package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AgeableHierarchicalModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.AnomalocarisAnims;
import net.voidarkana.marvelous_menagerie.client.animations.ArandaspisAnims;
import net.voidarkana.marvelous_menagerie.client.animations.FalcatusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Falcatus;

public class FalcatusModel<T extends Falcatus> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart horn;
	private final ModelPart leftEye;
	private final ModelPart rightEye;
	private final ModelPart jaw;
	private final ModelPart nose;
	private final ModelPart tail;
	private final ModelPart caudalFin;
	private final ModelPart dorsalFin;
	private final ModelPart leftFin;
	private final ModelPart rightFin;
	private final ModelPart leftPelvicFin;
	private final ModelPart rightPelvicFin;

	public FalcatusModel(ModelPart root) {
        super(0.5f, 1.1f);
        this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.body = this.swim_rot.getChild("body");
		this.head = this.body.getChild("head");
		this.horn = this.head.getChild("horn");
		this.leftEye = this.head.getChild("leftEye");
		this.rightEye = this.head.getChild("rightEye");
		this.jaw = this.head.getChild("jaw");
		this.nose = this.head.getChild("nose");
		this.tail = this.body.getChild("tail");
		this.caudalFin = this.tail.getChild("caudalFin");
		this.dorsalFin = this.body.getChild("dorsalFin");
		this.leftFin = this.body.getChild("leftFin");
		this.rightFin = this.body.getChild("rightFin");
		this.leftPelvicFin = this.body.getChild("leftPelvicFin");
		this.rightPelvicFin = this.body.getChild("rightPelvicFin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, -3.0F));

		PartDefinition body = swim_rot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -3.0F, -4.0F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 3.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -4.0F));

		PartDefinition horn = head.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(29, 1).addBox(0.0F, -3.0F, -7.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition leftEye = head.addOrReplaceChild("leftEye", CubeListBuilder.create().texOffs(6, 22).mirror().addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.5F, 0.5F, -1.5F));

		PartDefinition rightEye = head.addOrReplaceChild("rightEye", CubeListBuilder.create().texOffs(6, 22).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.5F, 0.5F, -1.5F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(14, 14).addBox(-1.5F, -0.5F, -3.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, -0.5F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(28, 0).addBox(-2.0F, -1.0F, -4.0F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, -1.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 5.0F));

		PartDefinition caudalFin = tail.addOrReplaceChild("caudalFin", CubeListBuilder.create().texOffs(18, 25).addBox(0.0F, -4.5F, 0.0F, 0.0F, 9.0F, 2.0F, new CubeDeformation(0.0025F))
				.texOffs(2, 33).addBox(0.0F, -4.5F, 2.0F, 0.0F, 9.0F, 1.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition dorsalFin = body.addOrReplaceChild("dorsalFin", CubeListBuilder.create().texOffs(4, 33).addBox(0.0F, -2.0F, -1.5F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, -3.0F, 2.5F));

		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create().texOffs(12, 40).addBox(0.0F, 0.0F, -2.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0025F))
				.texOffs(4, 38).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 41).addBox(0.0F, 1.0F, 3.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0025F)), PartPose.offsetAndRotation(2.5F, 2.0F, -3.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create().texOffs(14, 44).addBox(0.0F, 0.0F, -2.0F, 0.0F, 4.0F, 5.0F, new CubeDeformation(0.0025F))
				.texOffs(4, 38).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 41).addBox(0.0F, 1.0F, 3.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0025F)), PartPose.offsetAndRotation(-2.5F, 2.0F, -3.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition leftPelvicFin = body.addOrReplaceChild("leftPelvicFin", CubeListBuilder.create().texOffs(0, 43).addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0025F)), PartPose.offset(1.5F, 2.0F, 3.0F));

		PartDefinition rightPelvicFin = body.addOrReplaceChild("rightPelvicFin", CubeListBuilder.create().texOffs(0, 43).addBox(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0025F)), PartPose.offset(-1.5F, 2.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Falcatus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);


		if (entity.isInWaterOrBubble()){
			this.animateWalk(FalcatusAnims.swim, limbSwing, limbSwingAmount*4f, 1.5f, 3f);
			this.swim_rot.xRot = headPitch * ((float)Math.PI / 180F);
		}
		else{
			this.swim_rot.resetPose();
		}

		this.animateIdle(entity.idleAnimationState, FalcatusAnims.idle, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, FalcatusAnims.flop, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

		if (this.young){
			poseStack.scale(0.6f, 0.6f, 0.6f);
			poseStack.translate(0, 1.1, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}
}