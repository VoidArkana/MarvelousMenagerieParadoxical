package net.voidarkana.marvelous_menagerie.client.model.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.BorealoAnims;
import net.voidarkana.marvelous_menagerie.client.animations.DoedicurusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Borealopelta;

public class BorealoModel<T extends Borealopelta> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart spikes2;
	private final ModelPart neck;
	private final ModelPart spikes;
	private final ModelPart head;
	private final ModelPart tail1;
	private final ModelPart spikes3;
	private final ModelPart tail2;
	private final ModelPart spikes4;
	private final ModelPart arms;
	private final ModelPart armRight;
	private final ModelPart armLeft;
	private final ModelPart legs;
	private final ModelPart legRight;
	private final ModelPart legLeft;

	public BorealoModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.spikes2 = this.body.getChild("spikes2");
		this.neck = this.body.getChild("neck");
		this.spikes = this.neck.getChild("spikes");
		this.head = this.neck.getChild("head");
		this.tail1 = this.body.getChild("tail1");
		this.spikes3 = this.tail1.getChild("spikes3");
		this.tail2 = this.tail1.getChild("tail2");
		this.spikes4 = this.tail2.getChild("spikes4");
		this.arms = this.body_main.getChild("arms");
		this.armRight = this.arms.getChild("armRight");
		this.armLeft = this.arms.getChild("armLeft");
		this.legs = this.body_main.getChild("legs");
		this.legRight = this.legs.getChild("legRight");
		this.legLeft = this.legs.getChild("legLeft");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0417F, -12.5903F, 1.4167F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-12.625F, -9.5625F, -17.25F, 25.0F, 11.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(-9.625F, 1.4375F, -15.25F, 19.0F, 5.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0833F, -2.8472F, 1.8333F));

		PartDefinition spikes2 = body.addOrReplaceChild("spikes2", CubeListBuilder.create(), PartPose.offset(-9.625F, -0.8125F, -11.25F));

		PartDefinition cube_r1 = spikes2.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(87, 62).addBox(2.0F, -1.0F, -2.0F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(19.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r2 = spikes2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(87, 62).mirror().addBox(-17.0F, -1.0F, -2.0F, 15.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(88, 40).addBox(-5.75F, -3.25F, -12.0F, 11.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.125F, -0.3125F, -15.25F));

		PartDefinition spikes = neck.addOrReplaceChild("spikes", CubeListBuilder.create().texOffs(107, 0).addBox(5.5F, 1.5F, -7.0F, 3.0F, 0.0F, 14.0F, new CubeDeformation(0.001F))
		.texOffs(88, 97).addBox(5.5F, -0.5F, -7.0F, 3.0F, 0.0F, 14.0F, new CubeDeformation(0.001F))
		.texOffs(107, 0).mirror().addBox(-8.5F, 0.5F, -7.0F, 3.0F, 0.0F, 14.0F, new CubeDeformation(0.001F)).mirror(false)
		.texOffs(88, 97).mirror().addBox(-8.5F, -1.5F, -7.0F, 3.0F, 0.0F, 14.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-0.25F, -1.75F, -5.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(108, 15).addBox(-2.5F, -2.0F, -6.0F, 5.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(107, 28).addBox(2.5F, -1.0F, -6.0F, 3.0F, 0.0F, 8.0F, new CubeDeformation(0.001F))
		.texOffs(107, 28).mirror().addBox(-5.5F, -1.0F, -6.0F, 3.0F, 0.0F, 8.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-0.25F, -0.25F, -12.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 70).addBox(-6.5F, -4.25F, -4.0F, 13.0F, 9.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.125F, -1.3125F, 9.75F));

		PartDefinition spikes3 = tail1.addOrReplaceChild("spikes3", CubeListBuilder.create().texOffs(51, 97).addBox(6.0F, -5.0F, -18.0F, 3.0F, 0.0F, 15.0F, new CubeDeformation(0.001F))
		.texOffs(51, 97).mirror().addBox(-10.0F, -5.0F, -18.0F, 3.0F, 0.0F, 15.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(0.5F, 3.75F, 14.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(57, 70).addBox(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.25F, 11.0F));

		PartDefinition spikes4 = tail2.addOrReplaceChild("spikes4", CubeListBuilder.create().texOffs(0, 95).mirror().addBox(-5.5F, 0.0F, -11.0F, 3.0F, 0.0F, 22.0F, new CubeDeformation(0.001F)).mirror(false)
		.texOffs(0, 95).addBox(2.5F, 0.0F, -11.0F, 3.0F, 0.0F, 22.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -1.0F, 8.0F));

		PartDefinition arms = body_main.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(-0.0417F, 1.5903F, -9.9167F));

		PartDefinition armRight = arms.addOrReplaceChild("armRight", CubeListBuilder.create().texOffs(112, 69).mirror().addBox(-2.5F, -2.0F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, 0.0F, 0.0F));

		PartDefinition armLeft = arms.addOrReplaceChild("armLeft", CubeListBuilder.create().texOffs(112, 69).addBox(-2.5F, -2.0F, -2.5F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 0.0F, 0.0F));

		PartDefinition legs = body_main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-0.0417F, 1.2569F, 8.0833F));

		PartDefinition legRight = legs.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(111, 112).mirror().addBox(-2.0F, 4.3333F, -0.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(112, 88).mirror().addBox(-2.0F, 9.3333F, -2.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(88, 112).mirror().addBox(-2.0F, -4.6667F, -3.5F, 4.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.5F, 0.0F, 0.0F));

		PartDefinition legLeft = legs.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(111, 112).addBox(-2.0F, 4.3333F, -0.5F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(112, 88).addBox(-2.0F, 9.3333F, -2.5F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(88, 112).addBox(-2.0F, -4.6667F, -3.5F, 4.0F, 9.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(9.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 144, 144);
	}

	@Override
	public void setupAnim(Borealopelta entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (this.young){
			this.applyStatic(BorealoAnims.BABY);
		}

		if (!entity.isInWaterOrBubble()){
			this.animateWalk(BorealoAnims.WALK, limbSwing, limbSwingAmount*4f, 4, 2.5f);

			this.animate(entity.attackAnimationState1, BorealoAnims.ATTACK_1, ageInTicks, 1F);
			this.animate(entity.attackAnimationState2, BorealoAnims.ATTACK_2, ageInTicks, 1F);

			this.animate(entity.idleShakeState, BorealoAnims.SHAKE, ageInTicks, 1);
		}

		this.animateIdle(entity.idleAnimationState, BorealoAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);
		this.animateIdle(entity.idleAnimationState, BorealoAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.neck.xRot = headPitch * ((float)Math.PI / 180F)/4;
		this.neck.yRot = netHeadYaw * ((float)Math.PI / 180F)/4;

		this.head.xRot = headPitch * ((float)Math.PI / 180F)/2;
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F)/2;
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
		poseStack.popPose();	}

	@Override
	public ModelPart root() {
		return root;
	}
}