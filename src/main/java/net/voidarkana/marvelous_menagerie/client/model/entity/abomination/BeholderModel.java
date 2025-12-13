package net.voidarkana.marvelous_menagerie.client.model.entity.abomination;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.BeholderAnims;
import net.voidarkana.marvelous_menagerie.client.animations.BorealoAnims;
import net.voidarkana.marvelous_menagerie.client.animations.SacaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Beholder;

public class BeholderModel<T extends Beholder> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart body_overlay;
	private final ModelPart body;
	private final ModelPart fins_l;
	private final ModelPart l_fin_1;
	private final ModelPart l_fin_2;
	private final ModelPart l_fin_3;
	private final ModelPart l_fin_4;
	private final ModelPart fins_r;
	private final ModelPart r_fin_1;
	private final ModelPart r_fin_2;
	private final ModelPart r_fin_3;
	private final ModelPart r_fin_4;
	private final ModelPart eye_front;
	private final ModelPart eye_midback;
	private final ModelPart eye_back;
	private final ModelPart look_control;
	private final ModelPart head_overlay;
	private final ModelPart head;
	private final ModelPart eye_mid;
	private final ModelPart pupil;
	private final ModelPart eye_l;
	private final ModelPart eye_r;
	private final ModelPart l_jaw_overlay;
	private final ModelPart l_jaw;
	private final ModelPart r_jaw_overlay;
	private final ModelPart r_jaw;
	private final ModelPart pelvis_overlay;
	private final ModelPart pelvis;
	private final ModelPart tail_overlay;
	private final ModelPart tail;
	private final ModelPart leg_control;
	private final ModelPart leg_f_l_joint;
	private final ModelPart leg_f_l;
	private final ModelPart leg_f_r_joint;
	private final ModelPart leg_f_r;
	private final ModelPart leg_b_l_joint;
	private final ModelPart leg_b_l;
	private final ModelPart leg_b_r_joint;
	private final ModelPart leg_b_r;

	public BeholderModel(ModelPart root) {
        super(0, 0);
        this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.body_overlay = this.body_main.getChild("body_overlay");
		this.body = this.body_overlay.getChild("body");
		this.fins_l = this.body.getChild("fins_l");
		this.l_fin_1 = this.fins_l.getChild("l_fin_1");
		this.l_fin_2 = this.fins_l.getChild("l_fin_2");
		this.l_fin_3 = this.fins_l.getChild("l_fin_3");
		this.l_fin_4 = this.fins_l.getChild("l_fin_4");
		this.fins_r = this.body.getChild("fins_r");
		this.r_fin_1 = this.fins_r.getChild("r_fin_1");
		this.r_fin_2 = this.fins_r.getChild("r_fin_2");
		this.r_fin_3 = this.fins_r.getChild("r_fin_3");
		this.r_fin_4 = this.fins_r.getChild("r_fin_4");
		this.eye_front = this.body.getChild("eye_front");
		this.eye_midback = this.body.getChild("eye_midback");
		this.eye_back = this.body.getChild("eye_back");
		this.look_control = this.body.getChild("look_control");
		this.head_overlay = this.look_control.getChild("head_overlay");
		this.head = this.head_overlay.getChild("head");
		this.eye_mid = this.head.getChild("eye_mid");
		this.pupil = this.eye_mid.getChild("pupil");
		this.eye_l = this.head.getChild("eye_l");
		this.eye_r = this.head.getChild("eye_r");
		this.l_jaw_overlay = this.head.getChild("l_jaw_overlay");
		this.l_jaw = this.l_jaw_overlay.getChild("l_jaw");
		this.r_jaw_overlay = this.head.getChild("r_jaw_overlay");
		this.r_jaw = this.r_jaw_overlay.getChild("r_jaw");
		this.pelvis_overlay = this.body.getChild("pelvis_overlay");
		this.pelvis = this.pelvis_overlay.getChild("pelvis");
		this.tail_overlay = this.pelvis.getChild("tail_overlay");
		this.tail = this.tail_overlay.getChild("tail");
		this.leg_control = this.body_main.getChild("leg_control");
		this.leg_f_l_joint = this.leg_control.getChild("leg_f_l_joint");
		this.leg_f_l = this.leg_f_l_joint.getChild("leg_f_l");
		this.leg_f_r_joint = this.leg_control.getChild("leg_f_r_joint");
		this.leg_f_r = this.leg_f_r_joint.getChild("leg_f_r");
		this.leg_b_l_joint = this.leg_control.getChild("leg_b_l_joint");
		this.leg_b_l = this.leg_b_l_joint.getChild("leg_b_l");
		this.leg_b_r_joint = this.leg_control.getChild("leg_b_r_joint");
		this.leg_b_r = this.leg_b_r_joint.getChild("leg_b_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 21.5F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -9.5F, 0.0F));

		PartDefinition body_overlay = body_main.addOrReplaceChild("body_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = body_overlay.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 21).addBox(-3.5F, -1.5F, -8.5F, 7.0F, 3.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fins_l = body.addOrReplaceChild("fins_l", CubeListBuilder.create(), PartPose.offset(3.5F, 0.0F, 6.0F));

		PartDefinition l_fin_1 = fins_l.addOrReplaceChild("l_fin_1", CubeListBuilder.create().texOffs(51, 9).addBox(0.0F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, -12.0F));

		PartDefinition l_fin_2 = fins_l.addOrReplaceChild("l_fin_2", CubeListBuilder.create().texOffs(51, 13).addBox(0.0F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition l_fin_3 = fins_l.addOrReplaceChild("l_fin_3", CubeListBuilder.create().texOffs(51, 17).addBox(0.0F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition l_fin_4 = fins_l.addOrReplaceChild("l_fin_4", CubeListBuilder.create().texOffs(28, 54).addBox(0.0F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fins_r = body.addOrReplaceChild("fins_r", CubeListBuilder.create(), PartPose.offset(-3.5F, 0.0F, 6.0F));

		PartDefinition r_fin_1 = fins_r.addOrReplaceChild("r_fin_1", CubeListBuilder.create().texOffs(43, 54).addBox(-4.0F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, -12.0F));

		PartDefinition r_fin_2 = fins_r.addOrReplaceChild("r_fin_2", CubeListBuilder.create().texOffs(28, 58).addBox(-4.0F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, -8.0F));

		PartDefinition r_fin_3 = fins_r.addOrReplaceChild("r_fin_3", CubeListBuilder.create().texOffs(43, 58).addBox(-4.0F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition r_fin_4 = fins_r.addOrReplaceChild("r_fin_4", CubeListBuilder.create().texOffs(58, 54).addBox(-4.0F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye_front = body.addOrReplaceChild("eye_front", CubeListBuilder.create().texOffs(58, 58).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -5.0F));

		PartDefinition eye_midback = body.addOrReplaceChild("eye_midback", CubeListBuilder.create().texOffs(28, 62).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition eye_back = body.addOrReplaceChild("eye_back", CubeListBuilder.create().texOffs(41, 62).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 5.0F));

		PartDefinition look_control = body.addOrReplaceChild("look_control", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -7.5F));

		PartDefinition head_overlay = look_control.addOrReplaceChild("head_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = head_overlay.addOrReplaceChild("head", CubeListBuilder.create().texOffs(49, 21).addBox(-4.5F, -1.5F, -9.0F, 9.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition eye_mid = head.addOrReplaceChild("eye_mid", CubeListBuilder.create().texOffs(49, 34).addBox(-2.5F, -0.45F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.05F, -3.5F));

		PartDefinition pupil = eye_mid.addOrReplaceChild("pupil", CubeListBuilder.create().texOffs(66, 9).addBox(-0.5F, 0.0F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -0.55F, 0.0F));

		PartDefinition eye_l = head.addOrReplaceChild("eye_l", CubeListBuilder.create().texOffs(54, 63).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, -3.5F));

		PartDefinition eye_r = head.addOrReplaceChild("eye_r", CubeListBuilder.create().texOffs(63, 63).addBox(-0.5F, -1.5F, -1.5F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.0F, -3.5F));

		PartDefinition l_jaw_overlay = head.addOrReplaceChild("l_jaw_overlay", CubeListBuilder.create(), PartPose.offset(4.5F, 0.0F, -7.5F));

		PartDefinition l_jaw = l_jaw_overlay.addOrReplaceChild("l_jaw", CubeListBuilder.create().texOffs(0, 42).addBox(-4.0F, 0.0F, -9.5F, 8.0F, 0.0F, 11.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition r_jaw_overlay = head.addOrReplaceChild("r_jaw_overlay", CubeListBuilder.create(), PartPose.offset(-4.5F, 0.0F, -7.5F));

		PartDefinition r_jaw = r_jaw_overlay.addOrReplaceChild("r_jaw", CubeListBuilder.create().texOffs(39, 42).addBox(-4.0F, 0.0F, -9.5F, 8.0F, 0.0F, 11.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pelvis_overlay = body.addOrReplaceChild("pelvis_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition pelvis = pelvis_overlay.addOrReplaceChild("pelvis", CubeListBuilder.create().texOffs(51, 0).addBox(-5.5F, -1.5F, -0.5F, 11.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.7854F, 0.0F, 0.0F));

		PartDefinition tail_overlay = pelvis.addOrReplaceChild("tail_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.5F));

		PartDefinition tail = tail_overlay.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 71).addBox(0.0F, -2.5F, -1.0F, 0.0F, 5.0F, 20.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_control = body_main.addOrReplaceChild("leg_control", CubeListBuilder.create(), PartPose.offset(0.0F, 1.5F, 0.0F));

		PartDefinition leg_f_l_joint = leg_control.addOrReplaceChild("leg_f_l_joint", CubeListBuilder.create(), PartPose.offset(2.5F, 0.0F, -4.0F));

		PartDefinition leg_f_l = leg_f_l_joint.addOrReplaceChild("leg_f_l", CubeListBuilder.create().texOffs(0, 54).mirror().addBox(0.0F, 0.0F, -1.5F, 0.0F, 15.0F, 3.0F, new CubeDeformation(0.0025F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_f_r_joint = leg_control.addOrReplaceChild("leg_f_r_joint", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, -4.0F));

		PartDefinition leg_f_r = leg_f_r_joint.addOrReplaceChild("leg_f_r", CubeListBuilder.create().texOffs(7, 54).addBox(0.0F, 0.0F, -1.5F, 0.0F, 15.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_b_l_joint = leg_control.addOrReplaceChild("leg_b_l_joint", CubeListBuilder.create(), PartPose.offset(2.5F, 0.0F, 4.0F));

		PartDefinition leg_b_l = leg_b_l_joint.addOrReplaceChild("leg_b_l", CubeListBuilder.create().texOffs(21, 54).mirror().addBox(0.0F, 0.0F, -1.5F, 0.0F, 15.0F, 3.0F, new CubeDeformation(0.0025F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_b_r_joint = leg_control.addOrReplaceChild("leg_b_r_joint", CubeListBuilder.create(), PartPose.offset(-2.5F, 0.0F, 4.0F));

		PartDefinition leg_b_r = leg_b_r_joint.addOrReplaceChild("leg_b_r", CubeListBuilder.create().texOffs(14, 54).addBox(0.0F, 0.0F, -1.5F, 0.0F, 15.0F, 3.0F, new CubeDeformation(0.0025F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	@Override
	public void setupAnim(Beholder entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isLandNavigator){
			if (entity.isSprinting()){
				animateWalk(BeholderAnims.RUN,limbSwing, limbSwingAmount, 2f, 1);
			}else {
				animateWalk(BeholderAnims.WALK,limbSwing, limbSwingAmount, 2, 2.5f);
			}
		}

		this.animateIdle(entity.idleAnimationState, BeholderAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);
		this.animateIdle(entity.idleAnimationState, BeholderAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.animate(entity.idleOverlay, BeholderAnims.IDLE_OVERLAY, ageInTicks, 1.0F);

		this.animate(entity.idleSignalState, BeholderAnims.SIGNAL_BLEND, ageInTicks, 1);
		this.animate(entity.idleTwitchState, BeholderAnims.TWITCH_SLOWED, ageInTicks, 2);
		this.animate(entity.attackAnimationState, BeholderAnims.BITE_OVERLAY, ageInTicks, 1);
		this.animate(entity.idleLookAround, BeholderAnims.LOOKAROUND_BLEND, ageInTicks, 1);

		this.animate(entity.openJawsStartState, BeholderAnims.JAWS_OPEN, ageInTicks, 1);
		this.animate(entity.keepJawsOpenState, BeholderAnims.OPEN_JAW_STATIC, ageInTicks, 1);
		this.animate(entity.closeJawsState, BeholderAnims.GRAB, ageInTicks, 1);
		this.animate(entity.keepGrabbingState, BeholderAnims.GRAB_OVERLAY, ageInTicks, 1);
		this.animate(entity.releaseJawsState, BeholderAnims.GRAB_END, ageInTicks, 1);

		if (entity.isLandNavigator){
			this.body_main.resetPose();
			if (entity.isGrabbing()){
				this.look_control.resetPose();
			}else{
				this.look_control.xRot = headPitch * ((float)Math.PI / 180F);
				this.look_control.yRot = netHeadYaw * ((float)Math.PI / 180F);
			}
		}else {
			this.look_control.resetPose();
			this.body_main.xRot = headPitch * ((float)Math.PI / 180F);
			this.body_main.yRot = netHeadYaw * ((float)Math.PI / 180F);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}