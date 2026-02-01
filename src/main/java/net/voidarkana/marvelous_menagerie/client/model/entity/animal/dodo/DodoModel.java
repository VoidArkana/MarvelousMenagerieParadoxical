package net.voidarkana.marvelous_menagerie.client.model.entity.animal.dodo;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.DodoAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Dodo;

public class DodoModel<T extends Dodo> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart main;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart neck;
	private final ModelPart legs;
	private final ModelPart leftleg;
	private final ModelPart rightleg;

	public DodoModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.main = this.root.getChild("main");
		this.body = this.main.getChild("body");
		this.tail = this.body.getChild("tail");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
		this.neck = this.body.getChild("neck");
		this.legs = this.main.getChild("legs");
		this.leftleg = this.legs.getChild("leftleg");
		this.rightleg = this.legs.getChild("rightleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 2.0F));

		PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -6.0F, -8.0F, 9.0F, 9.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -10.0F, 0.0F, 0.0F, 10.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 2.0F));

		PartDefinition cube_r1 = tail.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(46, 17).addBox(0.0F, -4.0F, -1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -1.5F, 1.5F, 0.5713F, -0.5724F, -0.8706F));

		PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(36, 38).addBox(0.0F, -7.0F, -1.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.0F, 1.0F, 0.1572F, -0.3614F, -0.4215F));

		PartDefinition cube_r3 = tail.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(11, 42).addBox(0.0F, -4.0F, -1.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -1.5F, 1.5F, 0.5713F, 0.5724F, 0.8706F));

		PartDefinition cube_r4 = tail.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(21, 38).addBox(0.0F, -7.0F, -1.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, 1.0F, 0.1572F, 0.3614F, 0.4215F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(41, 8).addBox(0.0F, -0.5F, -0.5F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.6F, -4.5F, -5.5F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 42).addBox(0.0F, -0.5F, -0.5F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.6F, -4.5F, -5.5F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(21, 21).addBox(-3.0F, -8.0F, -4.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(46, 30).addBox(-1.5F, -6.0F, -6.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(41, 0).addBox(-1.5F, -7.0F, -9.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(51, 36).addBox(-1.5F, -3.0F, -9.0F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -7.0F));

		PartDefinition legs = main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(2.5F, 3.0F, -1.0F));

		PartDefinition leftleg = legs.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(13, 51).addBox(-0.5F, -1.05F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(-3, 51).addBox(-1.5F, 3.95F, -3.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rightleg = legs.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(16, 51).addBox(-0.5F, -1.05F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(-3, 51).addBox(-1.5F, 3.95F, -3.0F, 3.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Dodo entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (!entity.isInWaterOrBubble()){

			this.animate(entity.peckingAnimationState, DodoAnims.PECK, ageInTicks, 1);

			if (entity.isSprinting()){
				animateWalk(DodoAnims.RUN, limbSwing, limbSwingAmount, 1.5f, 1);
			}else {
				animateWalk(DodoAnims.WALK, limbSwing, limbSwingAmount, 2, 2.5f);
			}

			this.animate(entity.shakingAnimationState, DodoAnims.SHAKE, ageInTicks, 1);
		}

		this.animate(entity.lookAnimationState, DodoAnims.IDLE_LOOK, ageInTicks, 1);
		this.animateIdle(entity.idleAnimationState, DodoAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);

		this.animateIdle(entity.idleAnimationState, DodoAnims.IDLE, ageInTicks, 1.0f,
				Math.max(0, 1-entity.getTicksOffGround()/5f-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState, DodoAnims.FLAP, ageInTicks, 1.0f,
				entity.getTicksOffGround()/5f-entity.getInWaterTicks()/5f);


		float prevNeckX = this.neck.xRot;
		float prevNeckY = this.neck.yRot;
		this.neck.xRot = prevNeckX + headPitch * ((float)Math.PI / 180F);
		this.neck.yRot = prevNeckY + netHeadYaw * ((float)Math.PI / 180F);
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