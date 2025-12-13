package net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.BorealoAnims;
import net.voidarkana.marvelous_menagerie.client.animations.EleBirdAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ElephantBird;

public class EleBirdModel<T extends ElephantBird> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart main;
	private final ModelPart body_overlay;
	private final ModelPart body;
	private final ModelPart body_shag;
	private final ModelPart carpet;
	private final ModelPart passenger;
	private final ModelPart chest_l;
	private final ModelPart chest_r;
	private final ModelPart head_look;
	private final ModelPart neck_overlay;
	private final ModelPart neck;
	private final ModelPart neck_shag;
	private final ModelPart hat;
	private final ModelPart legs;
	private final ModelPart leg_left;
	private final ModelPart leg_right;

	public EleBirdModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.main = this.root.getChild("main");
		this.body_overlay = this.main.getChild("body_overlay");
		this.body = this.body_overlay.getChild("body");
		this.body_shag = this.body.getChild("body_shag");
		this.carpet = this.body.getChild("carpet");
		this.passenger = this.carpet.getChild("passenger");
		this.chest_l = this.body.getChild("chest_l");
		this.chest_r = this.body.getChild("chest_r");
		this.head_look = this.body.getChild("head_look");
		this.neck_overlay = this.head_look.getChild("neck_overlay");
		this.neck = this.neck_overlay.getChild("neck");
		this.neck_shag = this.neck.getChild("neck_shag");
		this.hat = this.neck.getChild("hat");
		this.legs = this.main.getChild("legs");
		this.leg_left = this.legs.getChild("leg_left");
		this.leg_right = this.legs.getChild("leg_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, -30.0F, 2.0F));

		PartDefinition body_overlay = main.addOrReplaceChild("body_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, -1.6F, -0.4F));

		PartDefinition body = body_overlay.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -7.4F, -12.6F, 18.0F, 15.0F, 25.0F, new CubeDeformation(0.05F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body_shag = body.addOrReplaceChild("body_shag", CubeListBuilder.create().texOffs(0, 82).addBox(-9.0F, 0.0F, -12.5F, 18.0F, 6.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.6F, -0.1F));

		PartDefinition carpet = body.addOrReplaceChild("carpet", CubeListBuilder.create().texOffs(0, 41).addBox(-9.0F, 0.175F, -12.5F, 18.0F, 15.0F, 25.0F, new CubeDeformation(0.2F)), PartPose.offset(0.0F, -7.575F, -0.1F));

		PartDefinition passenger = carpet.addOrReplaceChild("passenger", CubeListBuilder.create(), PartPose.offset(0.0F, 0.025F, 0.0F));

		PartDefinition chest_l = body.addOrReplaceChild("chest_l", CubeListBuilder.create().texOffs(25, 126).addBox(0.0F, -1.0F, -4.5F, 4.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -4.5F, 7.0F));

		PartDefinition chest_r = body.addOrReplaceChild("chest_r", CubeListBuilder.create().texOffs(25, 126).mirror().addBox(-4.0F, -1.0F, -4.5F, 4.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-9.0F, -4.5F, 7.0F));

		PartDefinition head_look = body.addOrReplaceChild("head_look", CubeListBuilder.create(), PartPose.offset(0.0F, -1.65F, -11.6F));

		PartDefinition neck_overlay = head_look.addOrReplaceChild("neck_overlay", CubeListBuilder.create(), PartPose.offset(0.0F, 0.25F, 0.0F));

		PartDefinition neck = neck_overlay.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 114).addBox(-4.0F, -4.0F, -5.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.075F))
		.texOffs(87, 0).addBox(-4.0F, -30.0F, -13.0F, 8.0F, 34.0F, 8.0F, new CubeDeformation(0.05F))
		.texOffs(29, 114).addBox(-2.0F, -30.0F, -19.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck_shag = neck.addOrReplaceChild("neck_shag", CubeListBuilder.create().texOffs(87, 43).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 4.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -13.0F));

		PartDefinition hat = neck.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(112, 122).addBox(-4.0F, 0.15F, -4.0F, 8.0F, 14.0F, 8.0F, new CubeDeformation(0.3F)), PartPose.offset(0.0F, -30.15F, -9.0F));

		PartDefinition legs = main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, 1.0F));

		PartDefinition leg_left = legs.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(87, 62).addBox(-3.5F, -2.0688F, -3.4284F, 7.0F, 26.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 0.0F, 0.0F));

		PartDefinition leg_right = legs.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(87, 96).addBox(-3.5F, -2.0688F, -3.4284F, 7.0F, 26.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 144, 144);
	}

	@Override
	public void setupAnim(ElephantBird entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (!entity.isInWaterOrBubble()){

			if (entity.isSprinting()){
				animateWalk(EleBirdAnims.RUN, limbSwing, limbSwingAmount, 1.5f, 1);
			}else {
				animateWalk(EleBirdAnims.WALK, limbSwing, limbSwingAmount, 2, 2.5f);
			}

			this.animate(entity.shakeAnimationState, EleBirdAnims.SHAKE, ageInTicks, 1);
		}

		this.animate(entity.smhAnimationState, EleBirdAnims.NUH_UH, ageInTicks, 1);

		this.animateIdle(entity.idleAnimationState, EleBirdAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);
		this.animateIdle(entity.idleAnimationState, EleBirdAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.head_look.xRot = headPitch * ((float)Math.PI / 180F);
		this.head_look.yRot = netHeadYaw * ((float)Math.PI / 180F);
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