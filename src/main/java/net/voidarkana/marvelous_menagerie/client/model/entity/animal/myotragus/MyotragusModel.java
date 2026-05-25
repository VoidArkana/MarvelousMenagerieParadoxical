package net.voidarkana.marvelous_menagerie.client.model.entity.animal.myotragus;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.MyotragusAnims;
import net.voidarkana.marvelous_menagerie.client.animations.TiktaalikAnimsExtra;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Myotragus;

public class MyotragusModel<T extends Myotragus> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart myotragus;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart right_horn;
	private final ModelPart left_horn;
	private final ModelPart left_back_leg;
	private final ModelPart right_back_leg;
	private final ModelPart right_front_leg;
	private final ModelPart left_front_leg;

	public MyotragusModel(ModelPart root) {
        super(1, 0);
        this.root = root.getChild("root");
		this.myotragus = this.root.getChild("myotragus");
		this.body = this.myotragus.getChild("body");
		this.head = this.body.getChild("head");
		this.right_horn = this.head.getChild("right_horn");
		this.left_horn = this.head.getChild("left_horn");
		this.left_back_leg = this.myotragus.getChild("left_back_leg");
		this.right_back_leg = this.myotragus.getChild("right_back_leg");
		this.right_front_leg = this.myotragus.getChild("right_front_leg");
		this.left_front_leg = this.myotragus.getChild("left_front_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition myotragus = root.addOrReplaceChild("myotragus", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 0.0F));

		PartDefinition body = myotragus.addOrReplaceChild("body", CubeListBuilder.create().texOffs(1, 1).addBox(-4.0F, -6.0F, -9.0F, 9.0F, 11.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 28).addBox(-5.0F, -7.0F, -10.0F, 11.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 2.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 46).addBox(-2.5F, -5.0F, -6.1716F, 5.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -5.0F, -10.9F, 1.1781F, 0.0F, 0.0F));

		PartDefinition Head_r1 = head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(23, 52).addBox(0.0F, -3.5F, -2.5F, 0.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, -6.6716F, -1.1781F, 0.0F, 0.0F));

		PartDefinition Head_r2 = head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(2, 61).addBox(-1.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(2, 61).mirror().addBox(6.5F, -1.0F, -0.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -3.0F, 0.3284F, -0.7854F, 0.0F, 0.0F));

		PartDefinition right_horn = head.addOrReplaceChild("right_horn", CubeListBuilder.create(), PartPose.offset(-1.49F, -3.0F, 3.8284F));

		PartDefinition Head_r3 = right_horn.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(12, 55).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_horn = head.addOrReplaceChild("left_horn", CubeListBuilder.create(), PartPose.offset(1.49F, -3.0F, 3.8284F));

		PartDefinition Head_r4 = left_horn.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(12, 55).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, -1.5708F, 0.0F, 0.0F));

		PartDefinition left_back_leg = myotragus.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(36, 29).addBox(0.0F, 4.0F, 0.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, 4.0F));

		PartDefinition right_back_leg = myotragus.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(49, 29).addBox(0.0F, 4.0F, 0.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.0F, 4.0F));

		PartDefinition right_front_leg = myotragus.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(49, 2).addBox(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -1.0F, -6.0F));

		PartDefinition left_front_leg = myotragus.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(35, 2).addBox(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, -6.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.head.getChild("left_horn").visible = pEntity.hasLeftHorn();
		this.head.getChild("right_horn").visible = pEntity.hasRightHorn();
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);
		float f = pEntity.getRammingXHeadRot();

		this.animate(pEntity.standUpAnimationState, MyotragusAnims.STAND_UP, pAgeInTicks);
		this.animate(pEntity.sitAnimationState, MyotragusAnims.SIT, pAgeInTicks);
		this.animate(pEntity.sitPoseAnimationState, MyotragusAnims.SITTING, pAgeInTicks);

		this.right_back_leg.xRot += Mth.lerp(getSittingMultiplier(), 0, Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount);
		this.left_back_leg.xRot += Mth.lerp(getSittingMultiplier(), 0, Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount);
		this.right_front_leg.xRot += Mth.lerp(getSittingMultiplier(), 0, Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount);
		this.left_front_leg.xRot += Mth.lerp(getSittingMultiplier(), 0, Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount);

		this.head.xRot += (float) Mth.lerp(getSittingMultiplier(), pHeadPitch * ((float)Math.PI / 180F), pHeadPitch * ((float)Math.PI / 180F)+Math.toRadians(5));
		this.head.yRot += pNetHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}