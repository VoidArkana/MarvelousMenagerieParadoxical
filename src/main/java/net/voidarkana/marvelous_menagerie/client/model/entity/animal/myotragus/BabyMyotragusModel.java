package net.voidarkana.marvelous_menagerie.client.model.entity.animal.myotragus;

import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.MyotragusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Myotragus;

public class BabyMyotragusModel<T extends Myotragus> extends MarvelousModel<T> {
	private final ModelPart root;
	private final ModelPart myotragus;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart left_back_leg;
	private final ModelPart right_back_leg;
	private final ModelPart right_front_leg;
	private final ModelPart left_front_leg;

	public BabyMyotragusModel(ModelPart root) {
        super(1, 0);
        this.root = root.getChild("root");
		this.myotragus = this.root.getChild("myotragus");
		this.body = this.myotragus.getChild("body");
		this.head = this.body.getChild("head");
		this.left_back_leg = this.myotragus.getChild("left_back_leg");
		this.right_back_leg = this.myotragus.getChild("right_back_leg");
		this.right_front_leg = this.myotragus.getChild("right_front_leg");
		this.left_front_leg = this.myotragus.getChild("left_front_leg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 17.8F, 0.0F));

		PartDefinition myotragus = root.addOrReplaceChild("myotragus", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = myotragus.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-3.0F, -2.3F, -4.5F, 6.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(0, 24).addBox(-2.5F, -2.2F, -4.0F, 5.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(24, 0).addBox(0.5F, -1.75F, -0.15F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(24, 0).mirror().addBox(-1.5F, -1.75F, -0.15F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, -4.3F, -5.0F, 1.1781F, 0.0F, 0.0F));

		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-2.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.7F, -0.5F, -0.7F, 0.0F, -0.5236F, 0.0F));

		PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7F, -0.5F, -0.7F, 0.0F, 0.5236F, 0.0F));

		PartDefinition left_back_leg = myotragus.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(29, 12).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.7F, 3.0F));

		PartDefinition right_back_leg = myotragus.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(21, 12).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.7F, 3.0F));

		PartDefinition right_front_leg = myotragus.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(21, 5).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.7F, -2.0F));

		PartDefinition left_front_leg = myotragus.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(29, 5).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.7F, -2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		super.setupAnim(pEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

		this.animate(pEntity.standUpAnimationState, MyotragusAnims.STAND_UP, pAgeInTicks);
		this.animate(pEntity.sitAnimationState, MyotragusAnims.SIT, pAgeInTicks);
		this.animate(pEntity.sitPoseAnimationState, MyotragusAnims.SITTING, pAgeInTicks);

		this.right_back_leg.xRot += Mth.lerp(pEntity.getSittingMultiplier(), Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount, 0);
		this.left_back_leg.xRot += Mth.lerp(pEntity.getSittingMultiplier(), Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount, 0);
		this.right_front_leg.xRot += Mth.lerp(pEntity.getSittingMultiplier(), Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount, 0);
		this.left_front_leg.xRot += Mth.lerp(pEntity.getSittingMultiplier(), Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount, 0);

		this.head.xRot += pHeadPitch * ((float)Math.PI / 180F);
		this.head.yRot += pNetHeadYaw * ((float)Math.PI / 180F);

		this.body.y += Mth.lerp(pEntity.getSittingMultiplier(), 0, 0.5f);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}