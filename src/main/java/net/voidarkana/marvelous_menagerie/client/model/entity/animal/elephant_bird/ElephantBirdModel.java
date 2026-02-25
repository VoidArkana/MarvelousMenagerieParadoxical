package net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.EleBirdAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ElephantBird;

public class ElephantBirdModel<T extends ElephantBird> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart elephant_bird;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart neck_shag;
	private final ModelPart hair;
	private final ModelPart body_shag;
	private final ModelPart carpet;
	private final ModelPart chest_l;
	private final ModelPart chest_r;
	private final ModelPart legs;
	private final ModelPart leg_left;
	private final ModelPart foot_left;
	private final ModelPart leg_right;
	private final ModelPart foot_right;

	public ElephantBirdModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.elephant_bird = this.root.getChild("elephant_bird");
		this.body = this.elephant_bird.getChild("body");
		this.neck = this.body.getChild("neck");
		this.neck_shag = this.neck.getChild("neck_shag");
		this.hair = this.neck.getChild("hair");
		this.body_shag = this.body.getChild("body_shag");
		this.carpet = this.body.getChild("carpet");
		this.chest_l = this.body.getChild("chest_l");
		this.chest_r = this.body.getChild("chest_r");
		this.legs = this.elephant_bird.getChild("legs");
		this.leg_left = this.legs.getChild("leg_left");
		this.foot_left = this.leg_left.getChild("foot_left");
		this.leg_right = this.legs.getChild("leg_right");
		this.foot_right = this.leg_right.getChild("foot_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition elephant_bird = root.addOrReplaceChild("elephant_bird", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = elephant_bird.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 67).addBox(-7.0F, -16.0F, 3.0F, 14.0F, 16.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-9.0F, -20.0F, -12.0F, 18.0F, 20.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(67, 0).addBox(-4.0F, -5.0F, -11.0F, 8.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(67, 62).addBox(-4.0F, 3.0F, -11.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(92, 110).addBox(-4.0F, 3.0F, -11.0F, 0.0F, 4.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(67, 24).addBox(-4.0F, -34.0F, -11.0F, 8.0F, 29.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(123, 115).addBox(-2.0F, -34.0F, -16.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, -12.0F));

		PartDefinition neck_shag = neck.addOrReplaceChild("neck_shag", CubeListBuilder.create().texOffs(100, 24).addBox(4.0F, 0.0F, -6.0F, 0.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, -5.0F));

		PartDefinition hair = neck.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(123, 87).addBox(-2.0F, -3.5F, -4.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(123, 101).addBox(2.0F, -3.5F, -4.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -33.5F, -7.0F));

		PartDefinition body_shag = body.addOrReplaceChild("body_shag", CubeListBuilder.create().texOffs(100, 53).addBox(-7.0F, 0.0F, 12.0F, 14.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(73, 117).addBox(-7.0F, 0.0F, 3.0F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(92, 62).addBox(-9.0F, 0.0F, -12.0F, 0.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(92, 86).addBox(9.0F, 0.0F, -12.0F, 0.0F, 8.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(100, 44).addBox(-9.0F, 0.0F, -12.0F, 18.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(123, 62).addBox(7.0F, 0.0F, 3.0F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition carpet = body.addOrReplaceChild("carpet", CubeListBuilder.create().texOffs(0, 36).addBox(-9.0F, -4.0F, -12.0F, 18.0F, 15.0F, 15.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, -16.0F, 0.0F));

		PartDefinition chest_l = body.addOrReplaceChild("chest_l", CubeListBuilder.create().texOffs(114, 0).addBox(0.0F, -2.0F, -4.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -16.0F, -5.0F));

		PartDefinition chest_r = body.addOrReplaceChild("chest_r", CubeListBuilder.create().texOffs(50, 117).addBox(-3.0F, -2.0F, -4.0F, 3.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, -16.0F, -5.0F));

		PartDefinition legs = elephant_bird.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(7.0F, 0.0F, 2.0F));

		PartDefinition leg_left = legs.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(47, 67).addBox(-5.0F, -4.0F, -4.0F, 10.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -5.0F));

		PartDefinition foot_left = leg_left.addOrReplaceChild("foot_left", CubeListBuilder.create().texOffs(33, 93).addBox(3.0F, 15.0F, -9.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(129, 53).addBox(-3.0F, 15.0F, -9.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(123, 80).addBox(-3.0F, 15.0F, -9.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(92, 130).addBox(-3.0F, 15.0F, -9.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 114).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 5.0F));

		PartDefinition leg_right = legs.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(47, 92).addBox(-5.0F, -4.0F, -4.0F, 10.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-14.0F, -4.0F, -5.0F));

		PartDefinition foot_right = leg_right.addOrReplaceChild("foot_right", CubeListBuilder.create().texOffs(114, 17).addBox(-3.0F, 15.0F, -9.0F, 6.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(33, 113).addBox(-3.0F, 15.0F, -9.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(123, 124).addBox(-3.0F, 15.0F, -9.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(33, 103).addBox(3.0F, 15.0F, -9.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(25, 117).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 160, 160);
	}

	@Override
	public void setupAnim(ElephantBird entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (!entity.isInWaterOrBubble()){

			if (entity.isSprinting()){
				animateWalk(EleBirdAnims.RUN, limbSwing, limbSwingAmount, 1.5f, 1);
			}else {
				animateWalk(EleBirdAnims.WALK, limbSwing*1.9f, limbSwingAmount, 2, 2.5f);
			}

		}

		this.animate(entity.smhAnimationState, EleBirdAnims.NUH_UH, ageInTicks, 1);

		this.animate(entity.shakeAnimationState, EleBirdAnims.SHAKE, ageInTicks, 1);
		this.animateIdle(entity.idleAnimationState, EleBirdAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);
		this.animateIdle(entity.idleAnimationState, EleBirdAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.neck.xRot = this.neck.xRot + headPitch * ((float)Math.PI / 180F)/2;
		this.neck.yRot = this.neck.yRot + netHeadYaw * ((float)Math.PI / 180F)/2;
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