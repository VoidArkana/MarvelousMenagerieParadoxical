package net.voidarkana.marvelous_menagerie.client.model.entity.animal.tlatolophus;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.TlatolophusAnims;
import net.voidarkana.marvelous_menagerie.client.animations.baby.BabyTlatolophusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Tlatolophus;

public class BabyTlatolophusModel<T extends Tlatolophus> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart tail;
	private final ModelPart arms;
	private final ModelPart arm_l;
	private final ModelPart arm_r;
	private final ModelPart legs;
	private final ModelPart thigh_l;
	private final ModelPart thigh_r;

	public BabyTlatolophusModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.neck = this.body.getChild("neck");
		this.tail = this.body.getChild("tail");
		this.arms = this.body_main.getChild("arms");
		this.arm_l = this.arms.getChild("arm_l");
		this.arm_r = this.arms.getChild("arm_r");
		this.legs = this.body_main.getChild("legs");
		this.thigh_l = this.legs.getChild("thigh_l");
		this.thigh_r = this.legs.getChild("thigh_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -0.5F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(19, 19).addBox(-1.0F, -3.0F, -1.5F, 2.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(21, 3).addBox(-1.0F, -5.0F, -3.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(21, 7).addBox(-1.0F, -6.0F, 1.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 11).addBox(-1.0F, -6.0F, -2.5F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.5F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 11).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.65F, 3.0F));

		PartDefinition arms = body_main.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -2.4F));

		PartDefinition arm_l = arms.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(11, 21).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.4F, 0.0F, 0.0F));

		PartDefinition arm_r = arms.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(11, 21).mirror().addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.4F, 0.0F, 0.0F));

		PartDefinition legs = body_main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, 1.6F));

		PartDefinition thigh_l = legs.addOrReplaceChild("thigh_l", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(21, 0).addBox(-1.5F, 3.49F, -0.59F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F))
		.texOffs(16, 21).addBox(-0.5F, 1.49F, 1.4F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		PartDefinition thigh_r = legs.addOrReplaceChild("thigh_r", CubeListBuilder.create().texOffs(0, 21).mirror().addBox(-1.0F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 0).mirror().addBox(-1.5F, 3.49F, -0.59F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)).mirror(false)
		.texOffs(16, 21).mirror().addBox(-0.5F, 1.49F, 1.4F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float partialTick = ageInTicks - entity.tickCount;

		this.animateWalk(BabyTlatolophusAnims.WALK, limbSwing/2, limbSwingAmount, 2f,
				(1-entity.getSprintingMultiplier(partialTick))
						*(1-entity.getInWaterMultiplier(partialTick))
						*(1-entity.getSittingMultiplier(partialTick)));
		this.animateWalk(BabyTlatolophusAnims.RUN, limbSwing, limbSwingAmount, 2f,
				(entity.getSprintingMultiplier(partialTick)-entity.getInWaterMultiplier(partialTick))
						*(1-entity.getSittingMultiplier(partialTick)));

		this.animateIdle(entity.idleAnimationState, BabyTlatolophusAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterMultiplier(partialTick)-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, BabyTlatolophusAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterMultiplier(partialTick));

		this.animate(entity.standUpAnimationState, BabyTlatolophusAnims.SIT_END, ageInTicks);
		this.animate(entity.sitAnimationState, BabyTlatolophusAnims.SIT_START, ageInTicks);
		this.animate(entity.sitPoseAnimationState, BabyTlatolophusAnims.SIT_POSE, ageInTicks);

		this.neck.xRot = headPitch * ((float)Math.PI / 180F);
		this.neck.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}