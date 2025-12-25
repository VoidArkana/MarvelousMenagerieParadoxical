package net.voidarkana.marvelous_menagerie.client.model.block;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class HerpetogasterModel extends Model {

	private final ModelPart root;
	private final ModelPart herpetogaster;
	private final ModelPart body;
	private final ModelPart ears;
	private final ModelPart ear_r;
	private final ModelPart ear_l;

	public HerpetogasterModel(ModelPart root) {
        super(RenderType::entityTranslucentCull);
        this.root = root.getChild("root");
		this.herpetogaster = this.root.getChild("herpetogaster");
		this.body = this.herpetogaster.getChild("body");
		this.ears = this.body.getChild("ears");
		this.ear_r = this.ears.getChild("ear_r");
		this.ear_l = this.ears.getChild("ear_l");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-0.5F, 24.0F, 0.0F));

		PartDefinition herpetogaster = root.addOrReplaceChild("herpetogaster", CubeListBuilder.create().texOffs(11, 19).addBox(0.0F, -3.0F, -1.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(21, 0).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = herpetogaster.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 7).addBox(-1.525F, -6.0F, -1.5F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-4.525F, -3.0F, -1.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(18, 19).addBox(-4.525F, -4.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.525F, -8.0F, -2.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.025F, -2.0F, 0.0F));

		PartDefinition ears = body.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.5F, -8.0F, 0.0F));

		PartDefinition ear_r = ears.addOrReplaceChild("ear_r", CubeListBuilder.create().texOffs(15, 13).addBox(-0.5F, -5.0F, 0.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 0.0F, 0.0F));

		PartDefinition ear_l = ears.addOrReplaceChild("ear_l", CubeListBuilder.create().texOffs(15, 7).addBox(-5.5F, -5.0F, 0.0F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	public void setupAnim(float pTime, int rotation) {
		this.herpetogaster.setRotation( 0, (float) Math.toRadians(rotation * 45), 0);
		this.body.xRot = (float) Math.toRadians(Math.sin(pTime/15/4)*2);
		this.body.yRot =  (float) Math.toRadians(Math.sin(pTime/15/6)*2);
		this.body.zRot =  (float) Math.toRadians(Math.sin(pTime/15/8)*2);
		this.ear_r.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-50)*5);
		this.ear_l.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-100)*5);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}