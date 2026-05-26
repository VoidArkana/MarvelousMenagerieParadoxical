package net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.BabyEleBirdAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ElephantBird;

public class BabyElephantBirdModel<T extends ElephantBird> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart elephant_bird;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart legs;
	private final ModelPart leg_right;
	private final ModelPart leg_left;

	public BabyElephantBirdModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.elephant_bird = this.root.getChild("elephant_bird");
		this.body = this.elephant_bird.getChild("body");
		this.neck = this.body.getChild("neck");
		this.legs = this.elephant_bird.getChild("legs");
		this.leg_right = this.legs.getChild("leg_right");
		this.leg_left = this.legs.getChild("leg_left");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition elephant_bird = root.addOrReplaceChild("elephant_bird", CubeListBuilder.create(), PartPose.offset(0.5F, -8.0F, -2.0F));

		PartDefinition body = elephant_bird.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -4.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 3.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 13).addBox(-2.0F, -9.0F, -3.0F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(13, 19).addBox(-1.5F, -7.0F, -5.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -3.0F));

		PartDefinition cube_r1 = neck.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(11, 20).addBox(0.0F, -0.5F, -3.5F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -10.5F, -1.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r2 = neck.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(11, 20).addBox(0.0F, -0.5F, -3.5F, 0.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -10.5F, -1.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition legs = elephant_bird.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(-2.0F, 4.0F, 1.5F));

		PartDefinition leg_right = legs.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(22, 19).addBox(-0.5F, -1.1F, 0.5F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(13, 16).addBox(-1.5F, 3.9F, -1.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_left = legs.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(23, 0).addBox(-0.5F, -1.1F, 0.5F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(13, 13).addBox(-1.5F, 3.9F, -1.5F, 3.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		animateWalk(BabyEleBirdAnims.RUN, limbSwing, limbSwingAmount, 1.5f, entity.getSprintingMultiplier()-entity.getInWaterMultiplier());
		animateWalk(BabyEleBirdAnims.WALK, limbSwing, limbSwingAmount, 2, 2.5f*(1-entity.getSprintingMultiplier())*(1-entity.getInWaterMultiplier()));

		this.animate(entity.shakeAnimationState, BabyEleBirdAnims.SHAKE, ageInTicks, 1);
		this.animateIdle(entity.idleAnimationState, BabyEleBirdAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterMultiplier());
		this.animateIdle(entity.idleAnimationState, BabyEleBirdAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterMultiplier()-Math.abs(limbSwingAmount)));

		this.neck.xRot = this.neck.xRot + headPitch * ((float)Math.PI / 180F);
		this.neck.yRot = this.neck.yRot + netHeadYaw * ((float)Math.PI / 180F);
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