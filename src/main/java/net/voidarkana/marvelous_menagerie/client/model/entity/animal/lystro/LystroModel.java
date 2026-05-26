package net.voidarkana.marvelous_menagerie.client.model.entity.animal.lystro;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.voidarkana.marvelous_menagerie.client.animations.LystroAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Lystrosaurus;

public class LystroModel<T extends Lystrosaurus> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart lystro;
	private final ModelPart body_back;
	private final ModelPart body_front;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart eyes;
	private final ModelPart jaw;
	private final ModelPart legs_f;
	private final ModelPart leg_fl;
	private final ModelPart leg_fr;
	private final ModelPart legs_b;
	private final ModelPart leg_bl;
	private final ModelPart leg_br;

	public LystroModel(ModelPart root) {
		super(1, 0, RenderType::entityCutoutNoCull);
        this.root = root.getChild("root");
		this.lystro = this.root.getChild("lystro");
		this.body_back = this.lystro.getChild("body_back");
		this.body_front = this.body_back.getChild("body_front");
		this.body = this.body_front.getChild("body");
		this.head = this.body.getChild("head");
		this.eyes = this.head.getChild("eyes");
		this.jaw = this.head.getChild("jaw");
		this.legs_f = this.lystro.getChild("legs_f");
		this.leg_fl = this.legs_f.getChild("leg_fl");
		this.leg_fr = this.legs_f.getChild("leg_fr");
		this.legs_b = this.lystro.getChild("legs_b");
		this.leg_bl = this.legs_b.getChild("leg_bl");
		this.leg_br = this.legs_b.getChild("leg_br");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, -2.0F));

		PartDefinition lystro = root.addOrReplaceChild("lystro", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 3.0F));

		PartDefinition body_back = lystro.addOrReplaceChild("body_back", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 5.0F));

		PartDefinition body_front = body_back.addOrReplaceChild("body_front", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -5.0F));

		PartDefinition body = body_front.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.5F, -4.0F, -7.0F, 13.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(57, 6).addBox(-1.5F, -4.0F, 8.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 24).addBox(-4.5F, -3.0F, -6.0F, 9.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(34, 56).addBox(-3.5F, -3.0F, -9.0F, 7.0F, 3.0F, 3.0F, new CubeDeformation(0.01F))
		.texOffs(39, 65).addBox(-1.5F, 0.0F, -9.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.01F))
		.texOffs(57, 11).addBox(2.5F, 0.0F, -8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(57, 16).addBox(-3.5F, 0.0F, -8.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -6.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(55, 56).addBox(1.0F, -1.0F, -6.0F, 7.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, -3.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(57, 0).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition legs_f = lystro.addOrReplaceChild("legs_f", CubeListBuilder.create(), PartPose.offset(5.5F, 0.0F, -6.5F));

		PartDefinition leg_fl = legs_f.addOrReplaceChild("leg_fl", CubeListBuilder.create().texOffs(0, 52).addBox(0.0F, -2.0F, -2.5F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(42, 40).addBox(-2.0F, 5.99F, -4.5F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_fr = legs_f.addOrReplaceChild("leg_fr", CubeListBuilder.create().texOffs(17, 53).addBox(-3.0F, -2.0F, -2.5F, 3.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(42, 48).addBox(-3.0F, 5.99F, -4.5F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 0.0F, 0.0F));

		PartDefinition legs_b = lystro.addOrReplaceChild("legs_b", CubeListBuilder.create(), PartPose.offset(5.5F, 0.0F, 6.5F));

		PartDefinition leg_bl = legs_b.addOrReplaceChild("leg_bl", CubeListBuilder.create().texOffs(0, 39).addBox(-2.0F, -1.0F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(37, 24).addBox(-1.0F, 5.99F, -4.5F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_br = legs_b.addOrReplaceChild("leg_br", CubeListBuilder.create().texOffs(21, 40).addBox(-3.0F, -1.0F, -2.5F, 5.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(37, 32).addBox(-5.0F, 5.99F, -4.5F, 6.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-11.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 80, 80);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

//		this.applyStatic(LystroAnims.POSE);

		this.animate(entity.standUpAnimationState, LystroAnims.STAND_UP, ageInTicks, 1);
		this.animate(entity.sitAnimationState, LystroAnims.SIT_DOWN, ageInTicks, 1);
		this.animate(entity.sitPoseAnimationState, LystroAnims.LAYING_DOWN_POSE, ageInTicks, 1);

		this.animate(entity.sniffAnimationState, LystroAnims.SNIFF, ageInTicks, 1);
		this.animate(entity.headTurnAnimationState, LystroAnims.HEAD_TURN, ageInTicks, 1);

		this.animateWalk(LystroAnims.WALK, limbSwing*1.5f, limbSwingAmount*4f, 4, 2.5f*(1-entity.getSprintingMultiplier())*(1-entity.getInWaterMultiplier()));
		this.animateWalk(LystroAnims.RUN, limbSwing*1.15f, limbSwingAmount*4f, 2, entity.getSprintingMultiplier()*2.5f*(1-entity.getInWaterMultiplier()));

		this.animateIdle(entity.idleAnimationState, LystroAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterMultiplier());

		this.animateIdle(entity.idleAnimationState, LystroAnims.RESTING, ageInTicks, 1.0f, Math.max(0, entity.getSittingMultiplier()-entity.getInWaterMultiplier()-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState, LystroAnims.IDLE, ageInTicks, 1.0f, Math.max(0, (1-entity.getSittingMultiplier())*(1-entity.getInWaterMultiplier())-Math.abs(limbSwingAmount)));
		this.animate(entity.layEggAnimationState, LystroAnims.EGG_LAY, ageInTicks, 1);

		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}