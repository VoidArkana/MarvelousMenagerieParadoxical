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
import net.voidarkana.marvelous_menagerie.client.animations.EolactoriaAnims;
import net.voidarkana.marvelous_menagerie.client.animations.FalcatusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Eolactoria;

public class EolactoriaModel<T extends Eolactoria> extends MarvelousModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart body;
	private final ModelPart leftPectoralFin;
	private final ModelPart rightPectoralFin;
	private final ModelPart rightBackHorn;
	private final ModelPart leftBackHorn;
	private final ModelPart caudalFin;
	private final ModelPart analFin;
	private final ModelPart dorsalFin;
	private final ModelPart rightFrontHorn;
	private final ModelPart leftFrontHorn;

	public EolactoriaModel(ModelPart root) {
        super(0.5f, 1f);
        this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.body = this.swim_rot.getChild("body");
		this.leftPectoralFin = this.body.getChild("leftPectoralFin");
		this.rightPectoralFin = this.body.getChild("rightPectoralFin");
		this.rightBackHorn = this.body.getChild("rightBackHorn");
		this.leftBackHorn = this.body.getChild("leftBackHorn");
		this.caudalFin = this.body.getChild("caudalFin");
		this.analFin = this.body.getChild("analFin");
		this.dorsalFin = this.body.getChild("dorsalFin");
		this.rightFrontHorn = this.body.getChild("rightFrontHorn");
		this.leftFrontHorn = this.body.getChild("leftFrontHorn");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -1.0F));

		PartDefinition body = swim_rot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -3.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(6, 24).addBox(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 1.0F));

		PartDefinition leftPectoralFin = body.addOrReplaceChild("leftPectoralFin", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, -1.5F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offsetAndRotation(2.0F, -1.5F, -1.0F, 0.0F, 1.0472F, 0.0F));

		PartDefinition rightPectoralFin = body.addOrReplaceChild("rightPectoralFin", CubeListBuilder.create().texOffs(16, 18).addBox(0.0F, -1.5F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offsetAndRotation(-2.0F, -1.5F, -1.0F, 0.0F, -1.0472F, 0.0F));

		PartDefinition rightBackHorn = body.addOrReplaceChild("rightBackHorn", CubeListBuilder.create().texOffs(0, 18).addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0025F)), PartPose.offset(-2.0F, 0.0F, 3.0F));

		PartDefinition leftBackHorn = body.addOrReplaceChild("leftBackHorn", CubeListBuilder.create().texOffs(12, 11).addBox(0.0F, -1.0F, 0.0F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0025F)), PartPose.offset(2.0F, 0.0F, 3.0F));

		PartDefinition caudalFin = body.addOrReplaceChild("caudalFin", CubeListBuilder.create().texOffs(10, 18).addBox(0.0F, -1.0F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, -3.0F, 3.0F));

		PartDefinition analFin = body.addOrReplaceChild("analFin", CubeListBuilder.create().texOffs(12, 24).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition dorsalFin = body.addOrReplaceChild("dorsalFin", CubeListBuilder.create().texOffs(12, 24).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, -5.0F, 2.0F));

		PartDefinition rightFrontHorn = body.addOrReplaceChild("rightFrontHorn", CubeListBuilder.create().texOffs(20, 0).addBox(0.0F, -1.0F, -6.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0025F)), PartPose.offset(-2.0F, -4.0F, -3.0F));

		PartDefinition leftFrontHorn = body.addOrReplaceChild("leftFrontHorn", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, -1.0F, -6.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0025F)), PartPose.offset(2.0F, -4.0F, -3.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Eolactoria entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){
			this.animateIdle(entity.idleAnimationState, EolactoriaAnims.IDLE, ageInTicks, 1,1-Math.abs(limbSwingAmount));

			this.animateWalk(EolactoriaAnims.SWIM, limbSwing, limbSwingAmount,2f, 3f);
		}
		else{
			if (entity.flopSide()){
				this.animate(entity.idleAnimationState, EolactoriaAnims.FLOP1, ageInTicks, 1.0F);
			}else {
				this.animate(entity.idleAnimationState, EolactoriaAnims.FLOP2, ageInTicks, 1.0F);
			}
		}

		this.swim_rot.xRot = headPitch * ((float)Math.PI / 180F);
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