package net.voidarkana.marvelous_menagerie.client.model.entity.animal.dodo;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.BabyDodoAnims;
import net.voidarkana.marvelous_menagerie.client.animations.DodoAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Dodo;

public class BabyDodoModel<T extends Dodo> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart main;
	private final ModelPart body;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart tail;
	private final ModelPart neck;
	private final ModelPart head_rot;
	private final ModelPart head;
	private final ModelPart legs;
	private final ModelPart leftleg;
	private final ModelPart rightleg;

	public BabyDodoModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.main = this.root.getChild("main");
		this.body = this.main.getChild("body");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
		this.tail = this.body.getChild("tail");
		this.neck = this.body.getChild("neck");
		this.head_rot = this.neck.getChild("head_rot");
		this.head = this.head_rot.getChild("head");
		this.legs = this.main.getChild("legs");
		this.leftleg = this.legs.getChild("leftleg");
		this.rightleg = this.legs.getChild("rightleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, 3.1416F, 0.0F, 3.1416F));

		PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 9).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.5F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(7, 18).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -1.0F, 1.0F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 18).addBox(-0.5F, 0.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.0F, 1.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(14, 6).addBox(0.0F, -3.0F, -2.5F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -1.0F, -1.5F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(15, 16).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.5F));

		PartDefinition head_rot = neck.addOrReplaceChild("head_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 1.0F));

		PartDefinition head = head_rot.addOrReplaceChild("head", CubeListBuilder.create().texOffs(19, 11).addBox(-0.5F, -2.0F, 2.5F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, -4.0F, -1.5F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs = main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -2.0F));

		PartDefinition leftleg = legs.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 23).addBox(-0.75F, -1.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition leftleg_r1 = leftleg.addOrReplaceChild("leftleg_r1", CubeListBuilder.create().texOffs(17, 3).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.35F, 3.0F, 0.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition rightleg = legs.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(21, 6).addBox(-0.25F, -1.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition rightleg_r1 = rightleg.addOrReplaceChild("rightleg_r1", CubeListBuilder.create().texOffs(13, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.35F, 3.0F, 0.5F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Dodo entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){

			this.animate(entity.idleAnimationState, BabyDodoAnims.SWIM, ageInTicks, 1);

		}else {

			if (entity.isSprinting()){
				animateWalk(BabyDodoAnims.RUN, limbSwing, limbSwingAmount, 1f, 1);
			}else {
				animateWalk(BabyDodoAnims.WALK, limbSwing, limbSwingAmount, 1.1f, 2.5f);
			}

			this.animateIdle(entity.idleAnimationState, BabyDodoAnims.IDLE, ageInTicks, 1.0f, Math.min(0, 1-entity.getTicksOffGround()/5f-Math.abs(limbSwingAmount)));
			this.animateIdle(entity.flappingAnimationState, BabyDodoAnims.FLAP, ageInTicks, 1.0f, (entity.getTicksOffGround()/5f));

			this.animate(entity.lookAnimationState, BabyDodoAnims.IDLE_LOOK, ageInTicks, 1);
			this.animate(entity.peckingAnimationState, BabyDodoAnims.PECK, ageInTicks, 1);
		}

		this.neck.xRot = -headPitch * ((float)Math.PI / 180F)/4;
		this.neck.yRot = netHeadYaw * ((float)Math.PI / 180F)/4;
		this.head.xRot = -headPitch * ((float)Math.PI / 180F)/2;
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F)/2;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}