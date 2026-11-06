package net.voidarkana.marvelous_menagerie.client.model.entity.animal.tlatolophus;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.TlatolophusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Tlatolophus;

public class TlatolophusModel<T extends Tlatolophus> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart front_body;
	private final ModelPart body;
	private final ModelPart tail_base;
	private final ModelPart tail_tip;
	private final ModelPart neck;
	private final ModelPart dewlap;
	private final ModelPart arms;
	private final ModelPart arm_l;
	private final ModelPart forearm_l;
	private final ModelPart arm_r;
	private final ModelPart forearm_r;
	private final ModelPart legs;
	private final ModelPart thigh_l;
	private final ModelPart back_leg_l;
	private final ModelPart thigh_r;
	private final ModelPart back_leg_r;

	public TlatolophusModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.front_body = this.body_main.getChild("front_body");
		this.body = this.front_body.getChild("body");
		this.tail_base = this.body.getChild("tail_base");
		this.tail_tip = this.tail_base.getChild("tail_tip");
		this.neck = this.body.getChild("neck");
		this.dewlap = this.neck.getChild("dewlap");
		this.arms = this.front_body.getChild("arms");
		this.arm_l = this.arms.getChild("arm_l");
		this.forearm_l = this.arm_l.getChild("forearm_l");
		this.arm_r = this.arms.getChild("arm_r");
		this.forearm_r = this.arm_r.getChild("forearm_r");
		this.legs = this.body_main.getChild("legs");
		this.thigh_l = this.legs.getChild("thigh_l");
		this.back_leg_l = this.thigh_l.getChild("back_leg_l");
		this.thigh_r = this.legs.getChild("thigh_r");
		this.back_leg_r = this.thigh_r.getChild("back_leg_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 3.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -31.5F, -3.0F));

		PartDefinition front_body = body_main.addOrReplaceChild("front_body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 10.0F));

		PartDefinition body = front_body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -13.5F, -1.0F, 15.0F, 25.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(75, 0).addBox(-5.5F, -8.5F, -18.0F, 11.0F, 20.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -10.0F));

		PartDefinition tail_base = body.addOrReplaceChild("tail_base", CubeListBuilder.create().texOffs(77, 38).addBox(-4.5F, -6.0F, -4.0F, 9.0F, 12.0F, 19.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -6.5F, 21.0F));

		PartDefinition tail_tip = tail_base.addOrReplaceChild("tail_tip", CubeListBuilder.create().texOffs(0, 48).addBox(-2.5F, -3.5F, -5.0F, 5.0F, 7.0F, 33.0F, new CubeDeformation(-0.02F)), PartPose.offset(0.0F, -1.5F, 15.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 89).addBox(-3.5F, -25.0F, -4.5F, 7.0F, 7.0F, 9.0F, new CubeDeformation(0.01F))
		.texOffs(58, 89).addBox(-1.0F, -21.0F, 7.5F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(100, 100).addBox(-1.0F, -25.0F, 4.5F, 2.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(33, 114).addBox(-2.5F, -25.0F, -9.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(100, 114).addBox(-3.5F, -22.0F, -12.5F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 106).addBox(-3.5F, -18.0F, -4.5F, 7.0F, 23.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, -16.5F));

		PartDefinition dewlap = neck.addOrReplaceChild("dewlap", CubeListBuilder.create().texOffs(83, 98).addBox(0.0F, -4.0F, -16.5F, 0.0F, 20.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -23.0F, 8.0F));

		PartDefinition arms = front_body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, -22.0F));

		PartDefinition arm_l = arms.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(58, 100).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 0.0F, 0.0F));

		PartDefinition forearm_l = arm_l.addOrReplaceChild("forearm_l", CubeListBuilder.create().texOffs(83, 100).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 13.0F, 1.0F));

		PartDefinition arm_r = arms.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(58, 100).mirror().addBox(-3.0F, -3.0F, -3.0F, 6.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.5F, 0.0F, 0.0F));

		PartDefinition forearm_r = arm_r.addOrReplaceChild("forearm_r", CubeListBuilder.create().texOffs(83, 100).mirror().addBox(-2.0F, -2.0F, -2.0F, 4.0F, 15.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 13.0F, 1.0F));

		PartDefinition legs = body_main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 5.5F, 8.0F));

		PartDefinition thigh_l = legs.addOrReplaceChild("thigh_l", CubeListBuilder.create().texOffs(77, 70).addBox(-4.0F, -5.0F, -6.0F, 8.0F, 16.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 0.0F, 0.0F));

		PartDefinition back_leg_l = thigh_l.addOrReplaceChild("back_leg_l", CubeListBuilder.create().texOffs(33, 89).addBox(-1.0F, -2.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(119, 70).addBox(-1.0F, 14.0F, -7.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 10.0F, 6.0F));

		PartDefinition thigh_r = legs.addOrReplaceChild("thigh_r", CubeListBuilder.create().texOffs(77, 70).mirror().addBox(-4.0F, -5.0F, -6.0F, 8.0F, 16.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 0.0F, 0.0F));

		PartDefinition back_leg_r = thigh_r.addOrReplaceChild("back_leg_r", CubeListBuilder.create().texOffs(33, 89).mirror().addBox(-5.0F, -2.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(119, 70).mirror().addBox(-5.0F, 14.0F, -7.0F, 6.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 10.0F, 6.0F));

		return LayerDefinition.create(meshdefinition, 144, 144);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		dewlap.visible = entity.getCast() == Tlatolophus.TlatolophusCast.ALPHA;

		float partialTick = ageInTicks - entity.tickCount;

		this.animateWalk(TlatolophusAnims.WALK, limbSwing, limbSwingAmount, 2f,
				(1-entity.getSprintingMultiplier(partialTick))
						*(1-entity.getInWaterMultiplier(partialTick))
						*(1-entity.getSittingMultiplier(partialTick)));
		this.animateWalk(TlatolophusAnims.RUN, limbSwing, limbSwingAmount, 2f,
				(entity.getSprintingMultiplier(partialTick)-entity.getInWaterMultiplier(partialTick))
						*(1-entity.getSittingMultiplier(partialTick)));

		this.animateIdle(entity.idleAnimationState, TlatolophusAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterMultiplier(partialTick)-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, TlatolophusAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterMultiplier(partialTick));

		this.animate(entity.standUpAnimationState, TlatolophusAnims.SIT_END, ageInTicks);
		this.animate(entity.sitAnimationState, TlatolophusAnims.SIT_START, ageInTicks);
		this.animate(entity.sitPoseAnimationState, TlatolophusAnims.SIT_POSE, ageInTicks);

		this.neck.xRot = headPitch * ((float)Math.PI / 180F);
		this.neck.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}