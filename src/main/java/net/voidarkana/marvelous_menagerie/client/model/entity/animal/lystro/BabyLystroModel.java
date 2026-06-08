package net.voidarkana.marvelous_menagerie.client.model.entity.animal.lystro;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.voidarkana.marvelous_menagerie.client.animations.BabyLystroAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Lystrosaurus;

public class BabyLystroModel<T extends Lystrosaurus> extends MarvelousModel<T> {
	private final ModelPart root;
	private final ModelPart lystro;
	private final ModelPart body_back;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart legs_b;
	private final ModelPart leg_bl;
	private final ModelPart leg_br;
	private final ModelPart legs_f;
	private final ModelPart leg_fl;
	private final ModelPart leg_fr;

	public BabyLystroModel(ModelPart root) {
        super(1, 0, RenderType::entityCutout);
        this.root = root.getChild("root");
		this.lystro = this.root.getChild("lystro");
		this.body_back = this.lystro.getChild("body_back");
		this.body = this.body_back.getChild("body");
		this.head = this.body.getChild("head");
		this.legs_b = this.lystro.getChild("legs_b");
		this.leg_bl = this.legs_b.getChild("leg_bl");
		this.leg_br = this.legs_b.getChild("leg_br");
		this.legs_f = this.lystro.getChild("legs_f");
		this.leg_fl = this.legs_f.getChild("leg_fl");
		this.leg_fr = this.legs_f.getChild("leg_fr");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition lystro = root.addOrReplaceChild("lystro", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_back = lystro.addOrReplaceChild("body_back", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 2.0F));

		PartDefinition body = body_back.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.0F, -3.0F, 7.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-1.0F, -3.0F, 3.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -4.0F, -2.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(19, 12).addBox(-1.0F, -3.0F, -4.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, -3.0F));

		PartDefinition legs_b = lystro.addOrReplaceChild("legs_b", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, 3.0F));

		PartDefinition leg_bl = legs_b.addOrReplaceChild("leg_bl", CubeListBuilder.create().texOffs(22, 26).addBox(0.0F, -1.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(0.0F, 1.9F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(-1.0F, -2.0F, -1.0F));

		PartDefinition leg_br = legs_b.addOrReplaceChild("leg_br", CubeListBuilder.create().texOffs(27, 0).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(13, 22).addBox(-3.0F, 1.9F, -1.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(-7.0F, -2.0F, -1.0F));

		PartDefinition legs_f = lystro.addOrReplaceChild("legs_f", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_fl = legs_f.addOrReplaceChild("leg_fl", CubeListBuilder.create().texOffs(13, 26).addBox(0.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(19, 18).addBox(-1.0F, 1.9F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(3.0F, -2.0F, -3.0F));

		PartDefinition leg_fr = legs_f.addOrReplaceChild("leg_fr", CubeListBuilder.create().texOffs(27, 6).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 25).addBox(-2.0F, 1.9F, -2.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(-3.0F, -2.0F, -3.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		this.animate(entity.standUpAnimationState, BabyLystroAnims.STAND_UP, ageInTicks, 1);
		this.animate(entity.sitAnimationState, BabyLystroAnims.SIT_DOWN, ageInTicks, 1);
		this.animate(entity.sitPoseAnimationState, BabyLystroAnims.LAYING_DOWN_POSE, ageInTicks, 1);

		this.animate(entity.sniffAnimationState, BabyLystroAnims.SNIFF, ageInTicks, 1);
		this.animate(entity.headTurnAnimationState, BabyLystroAnims.HEAD_TURN, ageInTicks, 1);

		this.animateWalk(BabyLystroAnims.WALK, limbSwing, limbSwingAmount*4f, 4, 2.5f*(1-entity.getSprintingMultiplier())*(1-entity.getInWaterMultiplier()));
		this.animateWalk(BabyLystroAnims.RUN, limbSwing, limbSwingAmount*4f, 2, entity.getSprintingMultiplier()*2.5f*(1-entity.getInWaterMultiplier()));

		this.animateIdle(entity.idleAnimationState, BabyLystroAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterMultiplier());

		this.animateIdle(entity.idleAnimationState, BabyLystroAnims.RESTING, ageInTicks, 1.0f, Math.max(0, entity.getSittingMultiplier()-entity.getInWaterMultiplier()-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState, BabyLystroAnims.IDLE, ageInTicks, 1.0f, Math.max(0, (1-entity.getSittingMultiplier())*(1-entity.getInWaterMultiplier())-Math.abs(limbSwingAmount)));

		this.animate(entity.eatAnimationState, BabyLystroAnims.EAT, ageInTicks, 1);
		this.animate(entity.digAnimationState, BabyLystroAnims.DIG, ageInTicks, 1);

		this.head.xRot += headPitch * ((float)Math.PI / 180F);
		this.head.yRot += netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}