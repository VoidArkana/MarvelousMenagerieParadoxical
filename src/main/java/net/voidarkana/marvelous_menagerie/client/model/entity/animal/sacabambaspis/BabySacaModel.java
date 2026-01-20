package net.voidarkana.marvelous_menagerie.client.model.entity.animal.sacabambaspis;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.SacaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Sacabambaspis;

public class BabySacaModel<T extends Sacabambaspis> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_control;
	private final ModelPart body;
	private final ModelPart tail_base;
	private final ModelPart tail;

	public BabySacaModel(ModelPart root) {
        super(1, 1);
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

		PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, -5.5F));

		PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -1.5F, -1.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail_base = body.addOrReplaceChild("tail_base", CubeListBuilder.create().texOffs(10, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition tail = tail_base.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -0.5F, 2.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	@Override
	public void setupAnim(Sacabambaspis pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (pEntity.isInWaterOrBubble()){
			this.animateWalk(SacaAnims.SWIM, pLimbSwing, pLimbSwingAmount, 2f, 1.5f);
		}

		this.swim_control.xRot = Mth.lerp( pEntity.getOutOfWaterTicks()/5f, pHeadPitch * ((float)Math.PI / 180F), 0);

		this.animateIdle(pEntity.idleAnimationState, SacaAnims.IDLE, pAgeInTicks, 1, Math.max(0, 1-pEntity.getOutOfWaterTicks()/5f-Math.abs(pLimbSwingAmount))*0.75F);
		this.animateIdle(pEntity.idleAnimationState, SacaAnims.FLOP, pAgeInTicks, 1.0F, (pEntity.getOutOfWaterTicks()/5f)*0.75F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}