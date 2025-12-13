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
import net.voidarkana.marvelous_menagerie.client.animations.SlovenicusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Slovenicus;

public class SlovenicusModel<T extends Slovenicus> extends MarvelousModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart bodyFin;
	private final ModelPart head;
	private final ModelPart snout;
	private final ModelPart leftEye;
	private final ModelPart rightEye;
	private final ModelPart fin;

	public SlovenicusModel(ModelPart root) {
        super(0.6f, 1f);
        this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.body = this.swim_rot.getChild("body");
		this.tail = this.body.getChild("tail");
		this.bodyFin = this.body.getChild("bodyFin");
		this.head = this.body.getChild("head");
		this.snout = this.head.getChild("snout");
		this.leftEye = this.head.getChild("leftEye");
		this.rightEye = this.head.getChild("rightEye");
		this.fin = this.head.getChild("fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 1.0F));

		PartDefinition body = swim_rot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(14, 0).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 1.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 7).addBox(-0.5F, 0.0F, -3.0F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition bodyFin = body.addOrReplaceChild("bodyFin", CubeListBuilder.create().texOffs(12, 15).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -1.0F));

		PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(8, 7).addBox(-0.5F, -0.5F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(2, 20).addBox(-0.5F, -1.5F, -3.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, -0.5F, -4.0F));

		PartDefinition leftEye = head.addOrReplaceChild("leftEye", CubeListBuilder.create().texOffs(6, 15).addBox(-1.0F, -1.0F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, -2.0F, -2.0F));

		PartDefinition rightEye = head.addOrReplaceChild("rightEye", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(-1.0F, -1.0F, -2.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0025F)).mirror(false), PartPose.offset(2.0F, -2.0F, -2.0F));

		PartDefinition fin = head.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, -1.5F, 1.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Slovenicus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){
			this.animateWalk(SlovenicusAnims.SWIM, limbSwing, limbSwingAmount, 2f, 3f);
			this.swim_rot.xRot = (headPitch * ((float)Math.PI / 180F))/4;
		}else{
			this.swim_rot.resetPose();
		}

		this.animateIdle(entity.idleAnimationState, SlovenicusAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, SlovenicusAnims.FLOP, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));

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