package net.voidarkana.marvelous_menagerie.client.model.entity.animal.dodo;// Made with Blockbench 4.12.6
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
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart tail;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart legs;
	private final ModelPart leftleg;
	private final ModelPart rightleg;

	public DodoModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.main = this.root.getChild("main");
		this.body = this.main.getChild("body");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
		this.tail = this.body.getChild("tail");
		this.neck = this.body.getChild("neck");
		this.head = this.neck.getChild("head");
		this.legs = this.main.getChild("legs");
		this.leftleg = this.legs.getChild("leftleg");
		this.rightleg = this.legs.getChild("rightleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -7.5F, 0.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(17, 30).addBox(-1.0F, -0.5F, -4.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -1.5F, 2.0F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(29, 13).addBox(0.0F, -0.5F, -4.0F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.5F, 2.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(21, 15).addBox(0.0F, -7.0F, -7.159F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -1.0F, -2.841F));

		PartDefinition tail_r1 = tail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(21, 15).addBox(0.0F, -16.0F, -11.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.5F, 9.0F, 3.841F, 0.0F, -0.2618F, 0.0F));

		PartDefinition tail_r2 = tail.addOrReplaceChild("tail_r2", CubeListBuilder.create().texOffs(21, 15).addBox(0.0F, -16.0F, -11.0F, 0.0F, 7.0F, 7.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(-0.5F, 9.0F, 3.841F, 0.0F, 0.2618F, 0.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 4.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(25, 0).addBox(-1.0F, -6.0F, 4.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-2.5F, -8.0F, -1.0F, 5.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs = main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 3.475F, -2.0F));

		PartDefinition leftleg = legs.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(3, 0).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.001F))
		.texOffs(0, 32).addBox(-1.5F, 4.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(-2.5F, 0.0F, 0.0F));

		PartDefinition rightleg = legs.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(24, 30).addBox(-1.5F, 4.0F, 0.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.001F))
		.texOffs(0, 0).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(2.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
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
			this.animate(entity.lookAnimationState, DodoAnims.IDLE_LOOK, ageInTicks, 1);
		}

		this.animateIdle(entity.idleAnimationState, DodoAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);

		this.animateIdle(entity.idleAnimationState, DodoAnims.IDLE, ageInTicks, 1.0f,
				Math.max(0, 1-entity.getTicksOffGround()/5f-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState, DodoAnims.FLAP, ageInTicks, 1.0f,
				entity.getTicksOffGround()/5f-entity.getInWaterTicks()/5f);



		this.neck.xRot = -headPitch * ((float)Math.PI / 180F);
		this.neck.yRot = netHeadYaw * ((float)Math.PI / 180F);
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