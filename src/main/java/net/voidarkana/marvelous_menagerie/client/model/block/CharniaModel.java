package net.voidarkana.marvelous_menagerie.client.model.block;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

public class CharniaModel extends Model {

	private final ModelPart charnia_1;
	private final ModelPart feather_rot;
	private final ModelPart feather_base;
	private final ModelPart feather_mid;
	private final ModelPart feather_tip;
	private final ModelPart charnia_2;
	private final ModelPart feather_rot2;
	private final ModelPart feather_base2;
	private final ModelPart feather_mid2;
	private final ModelPart feather_tip2;
	private final ModelPart charnia_3;
	private final ModelPart feather_rot3;
	private final ModelPart feather_base3;
	private final ModelPart feather_mid3;
	private final ModelPart feather_tip3;
	private final ModelPart charnia_4;
	private final ModelPart feather_rot4;
	private final ModelPart feather_base4;
	private final ModelPart feather_mid4;
	private final ModelPart feather_tip4;

	public CharniaModel(ModelPart root) {
        super(RenderType::entityTranslucentCull);
        this.charnia_1 = root.getChild("charnia_1");
		this.feather_rot = this.charnia_1.getChild("feather_rot");
		this.feather_base = this.feather_rot.getChild("feather_base");
		this.feather_mid = this.feather_base.getChild("feather_mid");
		this.feather_tip = this.feather_mid.getChild("feather_tip");
		this.charnia_2 = root.getChild("charnia_2");
		this.feather_rot2 = this.charnia_2.getChild("feather_rot2");
		this.feather_base2 = this.feather_rot2.getChild("feather_base2");
		this.feather_mid2 = this.feather_base2.getChild("feather_mid2");
		this.feather_tip2 = this.feather_mid2.getChild("feather_tip2");
		this.charnia_3 = root.getChild("charnia_3");
		this.feather_rot3 = this.charnia_3.getChild("feather_rot3");
		this.feather_base3 = this.feather_rot3.getChild("feather_base3");
		this.feather_mid3 = this.feather_base3.getChild("feather_mid3");
		this.feather_tip3 = this.feather_mid3.getChild("feather_tip3");
		this.charnia_4 = root.getChild("charnia_4");
		this.feather_rot4 = this.charnia_4.getChild("feather_rot4");
		this.feather_base4 = this.feather_rot4.getChild("feather_base4");
		this.feather_mid4 = this.feather_base4.getChild("feather_mid4");
		this.feather_tip4 = this.feather_mid4.getChild("feather_tip4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition charnia_1 = partdefinition.addOrReplaceChild("charnia_1",
				CubeListBuilder.create().texOffs(16, 11)
						.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F,
								new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.0F, 23.0F, 3.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition feather_rot = charnia_1.addOrReplaceChild("feather_rot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition feather_base = feather_rot.addOrReplaceChild("feather_base", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-3.5F, -6.5F, 0.0F, 7.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition feather_mid = feather_base.addOrReplaceChild("feather_mid", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(-3.5F, -4.0F, 0.0F, 7.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -6.5F, 0.0F));

		PartDefinition feather_tip = feather_mid.addOrReplaceChild("feather_tip", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.5F, -5.0F, 0.0F, 7.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition charnia_2 = partdefinition.addOrReplaceChild("charnia_2", CubeListBuilder.create().texOffs(18, 44).addBox(-3.0F, 0.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 23.0F, -3.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition feather_rot2 = charnia_2.addOrReplaceChild("feather_rot2", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.5F, 0.5F));

		PartDefinition feather_base2 = feather_rot2.addOrReplaceChild("feather_base2", CubeListBuilder.create().texOffs(1, 43).mirror().addBox(-2.5F, -4.5F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition feather_mid2 = feather_base2.addOrReplaceChild("feather_mid2", CubeListBuilder.create().texOffs(1, 39).mirror().addBox(-2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -4.5F, 0.0F));

		PartDefinition feather_tip2 = feather_mid2.addOrReplaceChild("feather_tip2", CubeListBuilder.create().texOffs(1, 36).mirror().addBox(-2.5F, -3.0F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition charnia_3 = partdefinition.addOrReplaceChild("charnia_3", CubeListBuilder.create().texOffs(50, 44).addBox(-3.0F, 0.0F, -1.0F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 23.0F, 2.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition feather_rot3 = charnia_3.addOrReplaceChild("feather_rot3", CubeListBuilder.create(), PartPose.offset(-1.5F, 0.5F, 0.5F));

		PartDefinition feather_base3 = feather_rot3.addOrReplaceChild("feather_base3", CubeListBuilder.create().texOffs(34, 43).mirror().addBox(-2.5F, -4.5F, 0.0F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition feather_mid3 = feather_base3.addOrReplaceChild("feather_mid3", CubeListBuilder.create().texOffs(34, 39).mirror().addBox(-2.5F, -4.0F, 0.0F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -4.5F, 0.0F));

		PartDefinition feather_tip3 = feather_mid3.addOrReplaceChild("feather_tip3", CubeListBuilder.create().texOffs(34, 36).mirror().addBox(-2.5F, -3.0F, 0.0F, 5.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition charnia_4 = partdefinition.addOrReplaceChild("charnia_4", CubeListBuilder.create().texOffs(48, 10).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 23.0F, -4.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition feather_rot4 = charnia_4.addOrReplaceChild("feather_rot4", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 0.0F));

		PartDefinition feather_base4 = feather_rot4.addOrReplaceChild("feather_base4", CubeListBuilder.create().texOffs(33, 9).mirror().addBox(-3.5F, -6.5F, 0.0F, 7.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition feather_mid4 = feather_base4.addOrReplaceChild("feather_mid4", CubeListBuilder.create().texOffs(33, 5).mirror().addBox(-3.5F, -4.0F, 0.0F, 7.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -6.5F, 0.0F));

		PartDefinition feather_tip4 = feather_mid4.addOrReplaceChild("feather_tip4", CubeListBuilder.create().texOffs(33, 0).mirror().addBox(-3.5F, -5.0F, 0.0F, 7.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -4.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(float pTime, int amount, int rotation) {
		this.charnia_2.visible = false;
		this.charnia_3.visible = false;
		this.charnia_4.visible = false;

		this.feather_rot.setRotation( 0, (float) Math.toRadians(rotation * 45), 0);
		this.feather_base.xRot = (float) Math.toRadians(Math.sin(pTime/15/4)*2);
		this.feather_base.yRot =  (float) Math.toRadians(Math.sin(pTime/15/6)*2);
		this.feather_base.zRot =  (float) Math.toRadians(Math.sin(pTime/15/8)*2);
		this.feather_mid.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-50)*5);
		this.feather_tip.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-100)*5);

		if (amount<2){
			this.charnia_1.x = 0;
			this.charnia_1.z = 0;
		}

		if (amount>1){
			this.charnia_1.x = -2f;
			this.charnia_1.z = 2f;
			if (amount<3){
				this.charnia_2.x = 1f;
				this.charnia_2.z = -2f;
			}
			this.charnia_2.visible = true;
			this.feather_rot2.setRotation( 0, (float) Math.toRadians(rotation * 45), 0);
			this.feather_base2.xRot = (float) Math.toRadians(Math.sin(pTime/15/4-50)*2);
			this.feather_base2.yRot =  (float) Math.toRadians(Math.sin(pTime/15/6-50)*2);
			this.feather_base2.zRot =  (float) Math.toRadians(Math.sin(pTime/15/8-50)*2);
			this.feather_mid2.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-50-50)*5);
			this.feather_tip2.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-100-50)*5);
		}

		if (amount>2){
			this.charnia_2.x = -4f;
			this.charnia_2.z = -2f;
			this.charnia_3.visible = true;
			this.feather_rot3.setRotation( 0, (float) Math.toRadians(rotation * 45), 0);
			this.feather_base3.xRot = (float) Math.toRadians(Math.sin(pTime/15/4-100)*2);
			this.feather_base3.yRot =  (float) Math.toRadians(Math.sin(pTime/15/6-100)*2);
			this.feather_base3.zRot =  (float) Math.toRadians(Math.sin(pTime/15/8-100)*2);
			this.feather_mid3.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-50-100)*5);
			this.feather_tip3.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-100-100)*5);
		}

		if (amount>3){
			this.charnia_4.visible = true;
			this.feather_rot4.setRotation( 0, (float) Math.toRadians(rotation * 45), 0);
			this.feather_base4.xRot = (float) Math.toRadians(Math.sin(pTime/15/4-150)*2);
			this.feather_base4.yRot =  (float) Math.toRadians(Math.sin(pTime/15/6-150)*2);
			this.feather_base4.zRot =  (float) Math.toRadians(Math.sin(pTime/15/8-150)*2);
			this.feather_mid4.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-50-150)*5);
			this.feather_tip4.xRot = (float) Math.toRadians(-5+Math.sin(pTime/15/4-100-150)*5);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		charnia_1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		charnia_2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		charnia_3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		charnia_4.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}