package net.voidarkana.marvelous_menagerie.client.model.entity.misc;// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.villager.Watcher;

public class WhispererModel<T extends Watcher> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_A;
	private final ModelPart body_B;
	private final ModelPart body;
	private final ModelPart glyph_A;
	private final ModelPart glyph;
	private final ModelPart rightLeg_A;
	private final ModelPart rightLeg_B;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg_A;
	private final ModelPart leftLeg_B;
	private final ModelPart leftLeg;
	private final ModelPart arms_A;
	private final ModelPart arms_B;
	private final ModelPart arms;
	private final ModelPart leftArm_A;
	private final ModelPart leftArm;
	private final ModelPart leftThumb_A;
	private final ModelPart leftThumb;
	private final ModelPart leftFinger1_A;
	private final ModelPart leftFinger1;
	private final ModelPart leftFinger2_A;
	private final ModelPart leftFinger2;
	private final ModelPart rightArm_A;
	private final ModelPart rightArm;
	private final ModelPart rightThumb_A;
	private final ModelPart rightThumb;
	private final ModelPart rightFinger1_A;
	private final ModelPart rightFinger1;
	private final ModelPart rightFinger2_A;
	private final ModelPart rightFinger2;

	public WhispererModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body_A = this.root.getChild("body_A");
		this.body_B = this.body_A.getChild("body_B");
		this.body = this.body_B.getChild("body");
		this.glyph_A = this.body.getChild("glyph_A");
		this.glyph = this.glyph_A.getChild("glyph");
		this.rightLeg_A = this.body.getChild("rightLeg_A");
		this.rightLeg_B = this.rightLeg_A.getChild("rightLeg_B");
		this.rightLeg = this.rightLeg_B.getChild("rightLeg");
		this.leftLeg_A = this.body.getChild("leftLeg_A");
		this.leftLeg_B = this.leftLeg_A.getChild("leftLeg_B");
		this.leftLeg = this.leftLeg_B.getChild("leftLeg");
		this.arms_A = this.root.getChild("arms_A");
		this.arms_B = this.arms_A.getChild("arms_B");
		this.arms = this.arms_B.getChild("arms");
		this.leftArm_A = this.arms.getChild("leftArm_A");
		this.leftArm = this.leftArm_A.getChild("leftArm");
		this.leftThumb_A = this.leftArm.getChild("leftThumb_A");
		this.leftThumb = this.leftThumb_A.getChild("leftThumb");
		this.leftFinger1_A = this.leftArm.getChild("leftFinger1_A");
		this.leftFinger1 = this.leftFinger1_A.getChild("leftFinger1");
		this.leftFinger2_A = this.leftArm.getChild("leftFinger2_A");
		this.leftFinger2 = this.leftFinger2_A.getChild("leftFinger2");
		this.rightArm_A = this.arms.getChild("rightArm_A");
		this.rightArm = this.rightArm_A.getChild("rightArm");
		this.rightThumb_A = this.rightArm.getChild("rightThumb_A");
		this.rightThumb = this.rightThumb_A.getChild("rightThumb");
		this.rightFinger1_A = this.rightArm.getChild("rightFinger1_A");
		this.rightFinger1 = this.rightFinger1_A.getChild("rightFinger1");
		this.rightFinger2_A = this.rightArm.getChild("rightFinger2_A");
		this.rightFinger2 = this.rightFinger2_A.getChild("rightFinger2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_A = root.addOrReplaceChild("body_A", CubeListBuilder.create(), PartPose.offset(0.0F, -34.0F, 0.0F));

		PartDefinition body_B = body_A.addOrReplaceChild("body_B", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = body_B.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -26.0F, -3.0F, 10.0F, 26.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(32, 0).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 8.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition glyph_A = body.addOrReplaceChild("glyph_A", CubeListBuilder.create(), PartPose.offset(0.0F, -20.0F, -1.0F));

		PartDefinition glyph = glyph_A.addOrReplaceChild("glyph", CubeListBuilder.create().texOffs(32, 13).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightLeg_A = body.addOrReplaceChild("rightLeg_A", CubeListBuilder.create(), PartPose.offset(-3.5F, 0.0F, 0.0F));

		PartDefinition rightLeg_B = rightLeg_A.addOrReplaceChild("rightLeg_B", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightLeg = rightLeg_B.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(48, 41).addBox(-1.5F, 0.0F, -2.5F, 3.0F, 34.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftLeg_A = body.addOrReplaceChild("leftLeg_A", CubeListBuilder.create(), PartPose.offset(3.5F, 0.0F, 0.0F));

		PartDefinition leftLeg_B = leftLeg_A.addOrReplaceChild("leftLeg_B", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftLeg = leftLeg_B.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(64, 41).addBox(-1.5F, 0.0F, -2.5F, 3.0F, 34.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arms_A = root.addOrReplaceChild("arms_A", CubeListBuilder.create(), PartPose.offset(0.0F, -43.0F, 0.0F));

		PartDefinition arms_B = arms_A.addOrReplaceChild("arms_B", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition arms = arms_B.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(9.0F, 0.0F, 0.0F));

		PartDefinition leftArm_A = arms.addOrReplaceChild("leftArm_A", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftArm = leftArm_A.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(20, 41).addBox(-2.0F, 0.0F, -2.5F, 5.0F, 34.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftThumb_A = leftArm.addOrReplaceChild("leftThumb_A", CubeListBuilder.create(), PartPose.offset(-1.0F, 35.0F, -2.5F));

		PartDefinition leftThumb = leftThumb_A.addOrReplaceChild("leftThumb", CubeListBuilder.create().texOffs(16, 32).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftFinger1_A = leftArm.addOrReplaceChild("leftFinger1_A", CubeListBuilder.create(), PartPose.offset(3.0F, 35.0F, -1.5F));

		PartDefinition leftFinger1 = leftFinger1_A.addOrReplaceChild("leftFinger1", CubeListBuilder.create().texOffs(20, 30).addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftFinger2_A = leftArm.addOrReplaceChild("leftFinger2_A", CubeListBuilder.create(), PartPose.offset(3.0F, 35.0F, 1.5F));

		PartDefinition leftFinger2 = leftFinger2_A.addOrReplaceChild("leftFinger2", CubeListBuilder.create().texOffs(24, 30).addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightArm_A = arms.addOrReplaceChild("rightArm_A", CubeListBuilder.create(), PartPose.offset(-18.0F, 0.0F, 0.0F));

		PartDefinition rightArm = rightArm_A.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 41).addBox(-3.0F, 0.0F, -2.5F, 5.0F, 34.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightThumb_A = rightArm.addOrReplaceChild("rightThumb_A", CubeListBuilder.create(), PartPose.offset(1.0F, 35.0F, -2.5F));

		PartDefinition rightThumb = rightThumb_A.addOrReplaceChild("rightThumb", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightFinger1_A = rightArm.addOrReplaceChild("rightFinger1_A", CubeListBuilder.create(), PartPose.offset(-3.0F, 35.0F, -1.5F));

		PartDefinition rightFinger1 = rightFinger1_A.addOrReplaceChild("rightFinger1", CubeListBuilder.create().texOffs(4, 30).addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightFinger2_A = rightArm.addOrReplaceChild("rightFinger2_A", CubeListBuilder.create(), PartPose.offset(-3.0F, 35.0F, 1.5F));

		PartDefinition rightFinger2 = rightFinger2_A.addOrReplaceChild("rightFinger2", CubeListBuilder.create().texOffs(8, 30).addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 80, 80);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		this.root().getAllParts().forEach(ModelPart::resetPose);
		ageInTicks *= 0.0015f;

		this.rightFinger2.zRot += this.mathAnim(10, ageInTicks, 180, 90, 10, true, true);
		this.rightFinger2.y += this.mathAnim(-1, ageInTicks, 180, 0, -0.5f, true, false);

		this.rightFinger1.zRot += (float) Math.toRadians(10 + Math.sin(ageInTicks * 180) * 20)/2;
		this.rightFinger1.y += (float) -1 + Math.sin(ageInTicks * 180 - 90) * -1/2;

		this.rightThumb.xRot += (float) Math.toRadians(10 - Math.sin(ageInTicks * 180 - 90) * -20)/2;
		this.rightThumb.y += (float) -1 + Math.sin(ageInTicks * 180 - 180) * -1/2;

		this.leftArm.xRot += (float) Math.toRadians(-Math.sin(ageInTicks*90)*-10)/2;
		this.leftArm.zRot += (float) (Math.toRadians(-15+Math.sin(ageInTicks*180-60)*2.5
				+Math.sin(ageInTicks*45-120)*-15))/2;
		this.leftArm.x += (float) -Math.sin(ageInTicks*45)*5/2;
		this.leftArm.y += (float) 1+Math.sin(ageInTicks*90-90)*1/2;
		this.leftArm.z += (float) Math.sin(ageInTicks*90-60)*2.5/2;

		this.leftFinger2.zRot += (float) Math.toRadians(-10 + Math.sin(ageInTicks * 180 + 90) * 20)/2;
		this.leftFinger2.y += (float) -1 + Math.sin(ageInTicks * 180) * 1/2;

		this.leftFinger1.zRot += (float) Math.toRadians(-10 + Math.sin(ageInTicks * 180) * 20)/2;
		this.leftFinger1.y += (float) -1 + Math.sin(ageInTicks * 180 - 90) * 1/2;

		this.leftThumb.xRot += (float) Math.toRadians(10 - Math.sin(ageInTicks * 180 - 90) * 20)/2;
		this.leftThumb.y += (float) -1 + Math.sin(ageInTicks * 180 - 180) * 1/2;

		this.rightThumb.xRot += (float) Math.toRadians(-Math.sin(ageInTicks * 90) * -10)/2;
		this.rightThumb.zRot += (float) (Math.toRadians(-15 + Math.sin(ageInTicks * 180 - 60) * 2.5
				+ Math.sin(ageInTicks * 45 - 120) * -15)/2);
		this.rightThumb.x += (float) -1-Math.sin(ageInTicks * 45) * 5/2;
		this.rightThumb.y += (float) -0.5f + Math.sin(ageInTicks * 90 - 90) * 1/2;
		this.rightThumb.z += (float) Math.sin(ageInTicks * 90 - 60) * 2.5/2;

		this.leftLeg.xRot += (float) Math.toRadians(-Math.sin(ageInTicks*90+90)*10/2);
		this.leftLeg.zRot += (float) Math.toRadians(Math.sin(ageInTicks*45+60)*10/2);

		this.rightLeg.xRot += (float) Math.toRadians(-Math.sin(ageInTicks*90+120)*10/2);
		this.rightLeg.zRot += (float) Math.toRadians(Math.sin(ageInTicks*45+30)*10/2);

		this.glyph.zRot += (float) Math.toRadians(Math.sin(ageInTicks * 90) * 10/2);
		this.glyph.y += (float) Math.sin(ageInTicks * 360 - 90) * 0.75/2;

		this.body.xRot += (float) Math.toRadians((-Math.sin(ageInTicks * 90 + 60)) * -10/2);
		this.body.zRot += (float) Math.toRadians(Math.sin(ageInTicks*45-60)*-10/2);
		this.body.x += (float) -Math.sin(ageInTicks*45)*5/2;
		this.body.y += (float) 1+Math.sin(ageInTicks*180)*-0.25/2;
		this.body.z += (float) Math.sin(ageInTicks*90-60)*2.5/2;

		this.rightArm.xRot +=(float)  Math.toRadians(-Math.sin(ageInTicks*90)*-10)/2;
		this.rightArm.zRot +=(float)  Math.toRadians(15+Math.sin(ageInTicks*180)*-2.5
				+Math.sin(ageInTicks*45-120)*-15)/4;
		this.rightArm.x +=(float)  -Math.sin(ageInTicks*45)*5/2;
		this.rightArm.y +=(float)  1+Math.sin(ageInTicks*90-90)*-1/2;
		this.rightArm.z +=(float)  Math.sin(ageInTicks*90-60)*2.5/2;

		this.body.xRot += (float) headPitch * ((float)Math.PI / 180F)/2;
		this.rightLeg.xRot -= (float) headPitch * ((float)Math.PI / 180F)/1.5f;
		this.leftLeg.xRot -= (float) headPitch * ((float)Math.PI / 180F)/1.5f;
	}

	@Override
	public ModelPart root() {
		return root;
	}
}