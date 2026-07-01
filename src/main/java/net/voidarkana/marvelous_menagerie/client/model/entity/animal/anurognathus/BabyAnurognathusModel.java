package net.voidarkana.marvelous_menagerie.client.model.entity.animal.anurognathus;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.AnurognathusAnims;
import net.voidarkana.marvelous_menagerie.client.animations.BabyAnurognathusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Anurognathus;

public class BabyAnurognathusModel<T extends Anurognathus> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart fly_rot;
	private final ModelPart anuro;
	private final ModelPart feet;
	private final ModelPart foot_r;
	private final ModelPart foot_l;
	private final ModelPart wings_closed;
	private final ModelPart closed_wing_r;
	private final ModelPart closed_wing_l;
	private final ModelPart wings_open;
	private final ModelPart open_wing_r;
	private final ModelPart wing_r_mid;
	private final ModelPart wing_r_tip;
	private final ModelPart open_wing_l;
	private final ModelPart wing_l_mid;
	private final ModelPart wing_l_tip;
	private final ModelPart body;

	public BabyAnurognathusModel(ModelPart root) {
		super(RenderType::entityCutout);
		this.root = root.getChild("root");
		this.fly_rot = this.root.getChild("fly_rot");
		this.anuro = this.fly_rot.getChild("anuro");
		this.feet = this.anuro.getChild("feet");
		this.foot_r = this.feet.getChild("foot_r");
		this.foot_l = this.feet.getChild("foot_l");
		this.wings_closed = this.anuro.getChild("wings_closed");
		this.closed_wing_r = this.wings_closed.getChild("closed_wing_r");
		this.closed_wing_l = this.wings_closed.getChild("closed_wing_l");
		this.wings_open = this.anuro.getChild("wings_open");
		this.open_wing_r = this.wings_open.getChild("open_wing_r");
		this.wing_r_mid = this.open_wing_r.getChild("wing_r_mid");
		this.wing_r_tip = this.wing_r_mid.getChild("wing_r_tip");
		this.open_wing_l = this.wings_open.getChild("open_wing_l");
		this.wing_l_mid = this.open_wing_l.getChild("wing_l_mid");
		this.wing_l_tip = this.wing_l_mid.getChild("wing_l_tip");
		this.body = this.anuro.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition fly_rot = root.addOrReplaceChild("fly_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -0.5F));

		PartDefinition anuro = fly_rot.addOrReplaceChild("anuro", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition feet = anuro.addOrReplaceChild("feet", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 1.0F));

		PartDefinition foot_r = feet.addOrReplaceChild("foot_r", CubeListBuilder.create().texOffs(15, 9).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.0F, 0.0F));

		PartDefinition foot_l = feet.addOrReplaceChild("foot_l", CubeListBuilder.create().texOffs(5, 16).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.0F, 0.0F));

		PartDefinition wings_closed = anuro.addOrReplaceChild("wings_closed", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.5F));

		PartDefinition closed_wing_r = wings_closed.addOrReplaceChild("closed_wing_r", CubeListBuilder.create().texOffs(15, 0).mirror().addBox(-1.0F, 0.0F, -0.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(13, 15).mirror().addBox(-2.0F, 1.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 16).mirror().addBox(-1.0F, 0.0F, -0.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(15, 4).mirror().addBox(-1.0F, 0.0F, -0.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 16).mirror().addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, -1.0F));

		PartDefinition closed_wing_l = wings_closed.addOrReplaceChild("closed_wing_l", CubeListBuilder.create().texOffs(15, 0).addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(13, 15).addBox(0.0F, 1.0F, -0.5F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 16).addBox(0.0F, 0.0F, -0.5F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(15, 4).addBox(1.0F, 0.0F, -0.5F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, -1.0F));

		PartDefinition wings_open = anuro.addOrReplaceChild("wings_open", CubeListBuilder.create(), PartPose.offset(0.0F, -0.5F, 0.5F));

		PartDefinition open_wing_r = wings_open.addOrReplaceChild("open_wing_r", CubeListBuilder.create().texOffs(0, 6).mirror().addBox(-1.0F, 0.0F, -2.5F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, 0.0F));

		PartDefinition wing_r_mid = open_wing_r.addOrReplaceChild("wing_r_mid", CubeListBuilder.create().texOffs(0, 11).mirror().addBox(-2.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-1.0F, 0.0F, -0.5F));

		PartDefinition wing_r_tip = wing_r_mid.addOrReplaceChild("wing_r_tip", CubeListBuilder.create().texOffs(13, 11).mirror().addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 0.0F, -1.0F));

		PartDefinition open_wing_l = wings_open.addOrReplaceChild("open_wing_l", CubeListBuilder.create().texOffs(0, 6).addBox(-2.0F, 0.0F, -2.5F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		PartDefinition wing_l_mid = open_wing_l.addOrReplaceChild("wing_l_mid", CubeListBuilder.create().texOffs(0, 11).addBox(0.0F, 0.0F, -2.0F, 2.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 0.0F, -0.5F));

		PartDefinition wing_l_tip = wing_l_mid.addOrReplaceChild("wing_l_tip", CubeListBuilder.create().texOffs(13, 11).addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, -1.0F));

		PartDefinition body = anuro.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float partialTick = ageInTicks - entity.tickCount;
		float flyProgress = entity.getFlyProgress(partialTick);
		float attachProgress = entity.getAttachedProgress(partialTick);
		float groundProgress = (1F - flyProgress)*(1F - attachProgress);
		float groundMove = groundProgress * limbSwingAmount*4;

		float rollAmount = entity.getFlightRoll(partialTick) / 57.295776F * flyProgress;
		float flightPitchAmount = entity.getFlightPitch(partialTick) / 57.295776F * flyProgress;

		animateWalk(BabyAnurognathusAnims.BABY_WALK, limbSwing, groundMove, 4, 2.5f*(1-entity.getInWaterMultiplier()));
		animateWalk(BabyAnurognathusAnims.BABY_FLYING, limbSwing/4, limbSwingAmount, 4, 2.5f*(flyProgress));

		if (entity.isAttached())
			this.animateIdle(entity.idleAnimationState, BabyAnurognathusAnims.BABY_WALL_IDLE, ageInTicks, 1.0f, 1);
		else
			this.animateIdle(entity.idleAnimationState, AnurognathusAnims.GROUND_IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterMultiplier()-Math.abs(limbSwingAmount)));


		this.animateIdle(entity.idleLookState1, AnurognathusAnims.GROUND_IDLE_1, ageInTicks, 1.0f, 1);
		this.animateIdle(entity.idleLookState2, BabyAnurognathusAnims.BABY_GROUND_IDLE_2, ageInTicks, 1.0f, 1);
		this.animateIdle(entity.idleAttachedState, AnurognathusAnims.WALL_IDLE_1, ageInTicks, 1.0f, 1);

		if (entity.isFlying()){
			this.wings_open.visible=true;
			this.wings_closed.visible=false;
		}
		else{
			this.wings_open.visible=false;
			this.wings_closed.visible=true;
		}


		float yawAmount = netHeadYaw / 57.295776F / 2;
		float pitchAmount = headPitch / 57.295776F / 2;
		this.body.xRot += pitchAmount;
		this.body.zRot += yawAmount * flyProgress;
		this.body.yRot += yawAmount * (1F - flyProgress)/2;
		this.fly_rot.xRot += flightPitchAmount;
		this.fly_rot.zRot += rollAmount;

		if (entity.isAttached()){
			this.fly_rot.xRot = (float) Math.toRadians(-90);
		}
		else{
			this.fly_rot.xRot += flightPitchAmount;
		}

		this.fly_rot.z += Mth.lerp(entity.getAttachedProgress(partialTick), 0, 1f);
		this.fly_rot.y += Mth.lerp(entity.getAttachedProgress(partialTick), 0, -3f);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}