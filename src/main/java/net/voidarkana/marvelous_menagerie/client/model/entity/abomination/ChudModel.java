package net.voidarkana.marvelous_menagerie.client.model.entity.abomination;// Made with Blockbench 4.12.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.ChudAnimations;
import net.voidarkana.marvelous_menagerie.common.entity.abomination.Chud;

public class ChudModel<T extends Chud> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "chud"), "main");
	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart eye_left;
	private final ModelPart eye_right;
	private final ModelPart nose_overlay;
	private final ModelPart nose;
	private final ModelPart ear_left;
	private final ModelPart ear_right;
	private final ModelPart legs_front;
	private final ModelPart leg_front_l;
	private final ModelPart leg_front_r;
	private final ModelPart legs_back;
	private final ModelPart leg_back_l;
	private final ModelPart leg_back_r;

	public ChudModel(ModelPart root) {
		this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.eye_left = this.body.getChild("eye_left");
		this.eye_right = this.body.getChild("eye_right");
		this.nose_overlay = this.body.getChild("nose_overlay");
		this.nose = this.nose_overlay.getChild("nose");
		this.ear_left = this.body.getChild("ear_left");
		this.ear_right = this.body.getChild("ear_right");
		this.legs_front = this.body_main.getChild("legs_front");
		this.leg_front_l = this.legs_front.getChild("leg_front_l");
		this.leg_front_r = this.legs_front.getChild("leg_front_r");
		this.legs_back = this.body_main.getChild("legs_back");
		this.leg_back_l = this.legs_back.getChild("leg_back_l");
		this.leg_back_r = this.legs_back.getChild("leg_back_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -5.0F, -6.0F, 10.0F, 10.0F, 12.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eye_left = body.addOrReplaceChild("eye_left", CubeListBuilder.create().texOffs(1, 1).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -1.5F, -6.125F));

		PartDefinition eye_right = body.addOrReplaceChild("eye_right", CubeListBuilder.create().texOffs(1, 1).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -3.5F, -6.125F));

		PartDefinition nose_overlay = body.addOrReplaceChild("nose_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, -6.0F));

		PartDefinition nose = nose_overlay.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ear_left = body.addOrReplaceChild("ear_left", CubeListBuilder.create().texOffs(13, 29).addBox(0.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -1.0F, -3.0F));

		PartDefinition ear_right = body.addOrReplaceChild("ear_right", CubeListBuilder.create().texOffs(13, 29).mirror().addBox(-2.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -1.0F, -3.0F));

		PartDefinition legs_front = body_main.addOrReplaceChild("legs_front", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, -4.5F));

		PartDefinition leg_front_l = legs_front.addOrReplaceChild("leg_front_l", CubeListBuilder.create().texOffs(13, 23).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 0.0F, 0.0F));

		PartDefinition leg_front_r = legs_front.addOrReplaceChild("leg_front_r", CubeListBuilder.create().texOffs(13, 23).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.5F, 0.0F, 0.0F));

		PartDefinition legs_back = body_main.addOrReplaceChild("legs_back", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 5.5F));

		PartDefinition leg_back_l = legs_back.addOrReplaceChild("leg_back_l", CubeListBuilder.create().texOffs(26, 23).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 0.0F, 0.0F));

		PartDefinition leg_back_r = legs_back.addOrReplaceChild("leg_back_r", CubeListBuilder.create().texOffs(26, 23).mirror().addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(Chud entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isSprinting()){
			animateWalk(ChudAnimations.run,limbSwing, limbSwingAmount, 2f, 1);
		}else {
			animateWalk(ChudAnimations.walk,limbSwing, limbSwingAmount, 2, 2.5f);
		}

		this.animate(entity.idleAnimationState, ChudAnimations.idle, ageInTicks, 1.0F);

		this.animate(entity.rotateAnimationState, ChudAnimations.rotate, ageInTicks, 1.0F);

		this.animate(entity.idleNoseState, ChudAnimations.nose, ageInTicks, 1.0F);
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