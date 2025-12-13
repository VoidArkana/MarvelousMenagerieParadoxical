package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.FalcatusAnims;
import net.voidarkana.marvelous_menagerie.client.animations.HallucigeniaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Hallucigenia;

public class HallucigeniaModel<T extends Hallucigenia> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart hallucigenia;
	private final ModelPart body;
	private final ModelPart spikes;
	private final ModelPart neck;
	private final ModelPart arm_l;
	private final ModelPart arm_r;
	private final ModelPart look_control;
	private final ModelPart neck_2;
	private final ModelPart head;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart legs;
	private final ModelPart leg_l1;
	private final ModelPart leg_l2;
	private final ModelPart leg_l3;
	private final ModelPart leg_l4;
	private final ModelPart leg_l5;
	private final ModelPart leg_r1;
	private final ModelPart leg_r2;
	private final ModelPart leg_r3;
	private final ModelPart leg_r4;
	private final ModelPart leg_r5;

	public HallucigeniaModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.hallucigenia = this.root.getChild("hallucigenia");
		this.body = this.hallucigenia.getChild("body");
		this.spikes = this.body.getChild("spikes");
		this.neck = this.body.getChild("neck");
		this.arm_l = this.neck.getChild("arm_l");
		this.arm_r = this.neck.getChild("arm_r");
		this.look_control = this.neck.getChild("look_control");
		this.neck_2 = this.look_control.getChild("neck_2");
		this.head = this.neck_2.getChild("head");
		this.tail1 = this.body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.legs = this.hallucigenia.getChild("legs");
		this.leg_l1 = this.legs.getChild("leg_l1");
		this.leg_l2 = this.legs.getChild("leg_l2");
		this.leg_l3 = this.legs.getChild("leg_l3");
		this.leg_l4 = this.legs.getChild("leg_l4");
		this.leg_l5 = this.legs.getChild("leg_l5");
		this.leg_r1 = this.legs.getChild("leg_r1");
		this.leg_r2 = this.legs.getChild("leg_r2");
		this.leg_r3 = this.legs.getChild("leg_r3");
		this.leg_r4 = this.legs.getChild("leg_r4");
		this.leg_r5 = this.legs.getChild("leg_r5");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition hallucigenia = root.addOrReplaceChild("hallucigenia", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition body = hallucigenia.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.0F, -4.5F, 3.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition spikes = body.addOrReplaceChild("spikes", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -5.5F, -3.5F, 2.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 0.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(19, 24).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, -1.0F, -4.5F));

		PartDefinition arm_l = neck.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(11, 31).addBox(0.0F, -0.5F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(1.5F, 0.0F, -2.0F));

		PartDefinition arm_r = neck.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(20, 31).addBox(-4.0F, -0.5F, 0.0F, 4.0F, 6.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(-1.5F, 0.0F, -2.0F));

		PartDefinition look_control = neck.addOrReplaceChild("look_control", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -1.0F));

		PartDefinition neck_2 = look_control.addOrReplaceChild("neck_2", CubeListBuilder.create().texOffs(19, 12).addBox(-1.5F, -2.0F, -2.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = neck_2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(25, 0).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, -1.0F, -2.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 26).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, -1.0F, 4.5F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(19, 18).addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 1.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(30, 24).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, -1.0F, 2.0F));

		PartDefinition legs = hallucigenia.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition leg_l1 = legs.addOrReplaceChild("leg_l1", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, -4.0F));

		PartDefinition cube_r1 = leg_l1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(29, 31).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition leg_l2 = legs.addOrReplaceChild("leg_l2", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, -2.0F));

		PartDefinition cube_r2 = leg_l2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 7).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition leg_l3 = legs.addOrReplaceChild("leg_l3", CubeListBuilder.create().texOffs(32, 13).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition leg_l4 = legs.addOrReplaceChild("leg_l4", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 2.0F));

		PartDefinition cube_r3 = leg_l4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 33).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leg_l5 = legs.addOrReplaceChild("leg_l5", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 4.0F));

		PartDefinition cube_r4 = leg_l5.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition leg_r1 = legs.addOrReplaceChild("leg_r1", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -4.0F));

		PartDefinition cube_r5 = leg_r1.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(29, 31).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition leg_r2 = legs.addOrReplaceChild("leg_r2", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -2.0F));

		PartDefinition cube_r6 = leg_r2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(32, 7).addBox(-1.0F, 0.0F, -0.5F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leg_r3 = legs.addOrReplaceChild("leg_r3", CubeListBuilder.create().texOffs(32, 13).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition leg_r4 = legs.addOrReplaceChild("leg_r4", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 2.0F));

		PartDefinition cube_r7 = leg_r4.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 33).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition leg_r5 = legs.addOrReplaceChild("leg_r5", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 4.0F));

		PartDefinition cube_r8 = leg_r5.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(36, 0).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(Hallucigenia entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (this.young){
			this.applyStatic(HallucigeniaAnims.BABY);
		}

		if (entity.isInWaterOrBubble()){

			animateWalk(HallucigeniaAnims.WALK, limbSwing, limbSwingAmount, 15f, 100);

			this.head.xRot = headPitch * ((float)Math.PI / 180F);
			this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);

		}

		this.animateIdle(entity.idleAnimationState, HallucigeniaAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));
		this.animateIdle(entity.idleAnimationState, HallucigeniaAnims.BEACHED, ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

		if (this.young){
			poseStack.scale(0.6f, 0.6f, 0.6f);
			poseStack.translate(0, 1, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}
}