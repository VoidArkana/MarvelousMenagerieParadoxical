package net.voidarkana.marvelous_menagerie.client.model.entity;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.voidarkana.marvelous_menagerie.common.entity.misc.Fracture;
import org.joml.Vector3f;

public class FractureModel<T extends Fracture> extends EntityModel<T> {

	private final ModelPart root;
	private final ModelPart fracture;
	private final ModelPart slice_1;
	private final ModelPart slice_2;
	private final ModelPart slice_4;
	private final ModelPart slice_3;

	public FractureModel(ModelPart root) {
		this.root = root.getChild("root");
		this.fracture = this.root.getChild("fracture");
		this.slice_1 = this.fracture.getChild("slice_1");
		this.slice_2 = this.fracture.getChild("slice_2");
		this.slice_4 = this.fracture.getChild("slice_4");
		this.slice_3 = this.fracture.getChild("slice_3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition fracture = root.addOrReplaceChild("fracture", CubeListBuilder.create(),
				PartPose.offset(0.0F, -20.5F, 0.0F));
		PartDefinition slice_1 = fracture.addOrReplaceChild("slice_1", CubeListBuilder.create()
				.texOffs(0, 0).addBox(0.0F, -20.5F, -21.5F, 0.0F, 41.0F, 43.0F,
						new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition slice_2 = fracture.addOrReplaceChild("slice_2", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition slice2_r1 = slice_2.addOrReplaceChild("slice2_r1", CubeListBuilder.create()
				.texOffs(0, 85).addBox(0.0F, -20.5F, -21.5F, 0.0F, 41.0F, 43.0F,
						new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition slice_4 = fracture.addOrReplaceChild("slice_4", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition slice4_r1 = slice_4.addOrReplaceChild("slice4_r1", CubeListBuilder.create()
				.texOffs(87, 0).addBox(0.0F, -20.5F, -21.5F, 0.0F, 41.0F, 43.0F,
						new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		PartDefinition slice_3 = fracture.addOrReplaceChild("slice_3", CubeListBuilder.create(),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition slice3_r1 = slice_3.addOrReplaceChild("slice3_r1", CubeListBuilder.create()
				.texOffs(87, 85).addBox(0.0F, -20.5F, -21.5F, 0.0F, 41.0F, 43.0F,
						new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 2.3562F, 0.0F));
		return LayerDefinition.create(meshdefinition, 176, 176);
	}

	@Override
	public void setupAnim(Fracture entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = (ageInTicks * (float)Math.PI * -0.1F)/2;

		this.fracture.y = -12.5f+Mth.cos(f/2)*2;

		this.slice_1.yRot = f / 2.0F;
		this.slice_1.xRot = Mth.sin(f*10)/75;

		this.slice_2.yRot = f / 4.0F;
		this.slice_2.xRot = -Mth.sin(f*10)/75;

		this.slice_3.yRot = f / 6.0F;
		this.slice_3.xRot = Mth.cos(f*10)/75;

		this.slice_4.yRot = f / 8.0F;
		this.slice_4.xRot = -Mth.cos(f*10)/75;

		this.slice_1.y = -Mth.cos(f/2)*2;
		this.slice_1.xScale = 1+Mth.sin(f)/8;
		this.slice_1.yScale = 1+Mth.sin(f)/8;
		this.slice_1.zScale = 1+Mth.sin(f)/8;

		this.slice_2.y = -Mth.sin(f/2)*2;
		this.slice_2.xScale = 1+Mth.cos(f)/8;
		this.slice_2.yScale = 1+Mth.cos(f)/8;
		this.slice_2.zScale = 1+Mth.cos(f)/8;

		this.slice_3.y = Mth.cos(f/2)*2;
		this.slice_3.xScale = 1-Mth.sin(f)/8;
		this.slice_3.yScale = 1-Mth.sin(f)/8;
		this.slice_3.zScale = 1-Mth.sin(f)/8;

		this.slice_4.y = Mth.sin(f/2)*2;
		this.slice_4.xScale = 1-Mth.cos(f)/8;
		this.slice_4.yScale = 1-Mth.cos(f)/8;
		this.slice_4.zScale = 1-Mth.cos(f)/8;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}