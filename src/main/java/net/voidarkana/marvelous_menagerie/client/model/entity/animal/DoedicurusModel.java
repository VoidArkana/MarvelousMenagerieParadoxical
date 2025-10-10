package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.DoedicurusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Doedicurus;

public class DoedicurusModel<T extends Doedicurus> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tailClub;
	private final ModelPart spikes;
	private final ModelPart head;
	private final ModelPart leftEar;
	private final ModelPart rightEar;
	private final ModelPart arms;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart legs;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public DoedicurusModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.tail1 = this.body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tailClub = this.tail2.getChild("tailClub");
		this.spikes = this.tailClub.getChild("spikes");
		this.head = this.body.getChild("head");
		this.leftEar = this.head.getChild("leftEar");
		this.rightEar = this.head.getChild("rightEar");
		this.arms = this.body_main.getChild("arms");
		this.leftArm = this.arms.getChild("leftArm");
		this.rightArm = this.arms.getChild("rightArm");
		this.legs = this.body_main.getChild("legs");
		this.rightLeg = this.legs.getChild("rightLeg");
		this.leftLeg = this.legs.getChild("leftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -7.5F, -0.75F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.5F, -19.5F, -10.25F, 17.0F, 20.0F, 19.0F, new CubeDeformation(0.0F))
		.texOffs(0, 39).addBox(-6.5F, -13.5F, 8.75F, 13.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(56, 61).addBox(-4.0F, -4.0F, 0.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.5F, 16.75F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(28, 61).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 6.0F));

		PartDefinition tailClub = tail2.addOrReplaceChild("tailClub", CubeListBuilder.create().texOffs(0, 61).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition spikes = tailClub.addOrReplaceChild("spikes", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 4.4979F, 0.0F, 0.0F, 0.7854F));

		PartDefinition spikes_r1 = spikes.addOrReplaceChild("spikes_r1", CubeListBuilder.create().texOffs(26, 101).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.5021F, -0.3927F, 0.0F, 0.0F));

		PartDefinition spikes_r2 = spikes.addOrReplaceChild("spikes_r2", CubeListBuilder.create().texOffs(26, 101).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -3.4979F, -0.3927F, 0.0F, 0.0F));

		PartDefinition spikes_r3 = spikes.addOrReplaceChild("spikes_r3", CubeListBuilder.create().texOffs(10, 101).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -3.4979F, 0.3927F, 0.0F, 0.0F));

		PartDefinition spikes_r4 = spikes.addOrReplaceChild("spikes_r4", CubeListBuilder.create().texOffs(38, 92).mirror().addBox(-3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.5021F, 0.0F, 0.3927F, 0.0F));

		PartDefinition spikes_r5 = spikes.addOrReplaceChild("spikes_r5", CubeListBuilder.create().texOffs(38, 92).mirror().addBox(-3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 0.0F, -3.4979F, 0.0F, 0.3927F, 0.0F));

		PartDefinition spikes_r6 = spikes.addOrReplaceChild("spikes_r6", CubeListBuilder.create().texOffs(22, 97).addBox(0.0F, -1.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, -3.4979F, 0.0F, -0.3927F, 0.0F));

		PartDefinition spikes_r7 = spikes.addOrReplaceChild("spikes_r7", CubeListBuilder.create().texOffs(22, 97).addBox(0.0F, -1.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.5021F, 0.0F, -0.3927F, 0.0F));

		PartDefinition spikes_r8 = spikes.addOrReplaceChild("spikes_r8", CubeListBuilder.create().texOffs(10, 101).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 0.5021F, 0.3927F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(44, 39).addBox(-4.0F, -5.0F, -6.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(22, 88).addBox(-3.0F, -4.0F, -8.0F, 6.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, -10.25F));

		PartDefinition leftEar = head.addOrReplaceChild("leftEar", CubeListBuilder.create().texOffs(42, 101).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -5.0F, -2.0F));

		PartDefinition rightEar = head.addOrReplaceChild("rightEar", CubeListBuilder.create().texOffs(42, 101).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -5.0F, -2.0F));

		PartDefinition arms = body_main.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -6.25F));

		PartDefinition leftArm = arms.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(0, 75).addBox(-3.5F, 0.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 0.0F, 0.0F));

		PartDefinition rightArm = arms.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 75).addBox(-1.5F, 0.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, 0.0F, 0.0F));

		PartDefinition legs = body_main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-4.5F, 0.5F, 6.75F));

		PartDefinition rightLeg = legs.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(0, 75).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftLeg = legs.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(0, 75).addBox(-2.5F, 0.0F, -3.0F, 5.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Doedicurus entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (this.young){
			this.applyStatic(DoedicurusAnims.BABY);
		}

		if (entity.isInWaterOrBubble()){

			this.animate(entity.idleAnimationState, DoedicurusAnims.SWIM, ageInTicks, 1);

		}else {
			animateWalk(DoedicurusAnims.WALK, limbSwing, limbSwingAmount*4f, 4, 2.5f);

			this.animateIdle(entity.idleAnimationState, DoedicurusAnims.IDLE, ageInTicks, 1.0f, 1-Math.abs(limbSwingAmount));

			this.animate(entity.attackAnimationState1, DoedicurusAnims.ATTACK_1, ageInTicks, 1F);
			this.animate(entity.attackAnimationState2, DoedicurusAnims.ATTACK_2, ageInTicks, 1F);

			this.animate(entity.idleShakeState, DoedicurusAnims.IDLE_SHAKE, ageInTicks, 1);
		}

		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();


		poseStack.scale(1.25f, 1.25f, 1.25f);
		poseStack.translate(0, -0.3, 0);

		if (this.young){
			poseStack.scale(0.5f, 0.5f, 0.5f);
			poseStack.translate(0, 1.5, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}
}