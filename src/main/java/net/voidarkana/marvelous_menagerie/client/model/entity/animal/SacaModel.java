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
import net.voidarkana.marvelous_menagerie.client.animations.SacaAnims;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Sacabambaspis;

public class SacaModel<T extends Sacabambaspis> extends AgeableHierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart body;
	private final ModelPart tail_base;
	private final ModelPart tail;

	public SacaModel(ModelPart root) {
        super(0.6f, 0);
        this.root = root.getChild("root");
		this.swim_control = this.root.getChild("swim_control");
		this.body = this.swim_control.getChild("body");
		this.tail_base = this.body.getChild("tail_base");
		this.tail = this.tail_base.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -3.5F, -1.0F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -2.5F, -2.5F, 7.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition tail_base = body.addOrReplaceChild("tail_base", CubeListBuilder.create().texOffs(13, 17).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.5F));

		PartDefinition tail = tail_base.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -4.5F, -1.0F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

		if (this.young){
			poseStack.scale(0.6f, 0.6f, 0.6f);
			poseStack.translate(0, 0.9, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}

	@Override
	public void setupAnim(Sacabambaspis pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

//		if (pEntity.isInWaterOrBubble()){
//			this.animateWalk(SacaAnims.SWIM, pLimbSwingAmount, pLimbSwing, 2, 2.5f);
//		}
		//System.out.println(pLimbSwingAmount);
		//this.animate(pEntity.swimAnimationState, SacaAnims.SWIM, pAgeInTicks, pLimbSwingAmount);

		if (pEntity.isInWater()){
			this.animateWalk(SacaAnims.SWIM, pLimbSwing, pLimbSwingAmount, 2f, 3f);
		}

		this.animate(pEntity.idleAnimationState, SacaAnims.IDLE, pAgeInTicks, 1-Math.abs(pLimbSwingAmount));
		this.animate(pEntity.flopAnimationState, SacaAnims.FLOP, pAgeInTicks, 1.0F);

		this.swim_control.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}
}