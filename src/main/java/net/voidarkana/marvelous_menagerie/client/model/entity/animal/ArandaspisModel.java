package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.ArandaspisAnims;
import net.voidarkana.marvelous_menagerie.client.animations.SacaAnims;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Arandaspis;

public class ArandaspisModel<T extends Arandaspis> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart fin;

	public ArandaspisModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.body = this.swim_rot.getChild("body");
		this.tail = this.body.getChild("tail");
		this.fin = this.tail.getChild("fin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -4.0F));

		PartDefinition body = swim_rot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 0).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(1, 12).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition fin = tail.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(17, 10).addBox(0.0F, -3.0F, -1.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Arandaspis entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(entity.swimAnimationState, ArandaspisAnims.SWIM, ageInTicks, limbSwingAmount);
		this.animate(entity.idleAnimationState, ArandaspisAnims.IDLE, ageInTicks, 1-Math.abs(limbSwingAmount));
		this.animate(entity.flopAnimationState, ArandaspisAnims.FLOP, ageInTicks, 1.0F);

		this.swim_rot.xRot = headPitch * ((float)Math.PI / 180F);
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