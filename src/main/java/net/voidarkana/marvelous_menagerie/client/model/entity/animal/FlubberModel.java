package net.voidarkana.marvelous_menagerie.client.model.entity.animal;// Made with Blockbench 5.0.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.FlubberAnimsBasics;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Flubber;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class FlubberModel<T extends Flubber> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart head_flap;
	private final ModelPart head;
	private final ModelPart trunk;
	private final ModelPart hips;
	private final ModelPart leg_l;
	private final ModelPart leg_r;
	private final ModelPart tail;
	private final ModelPart tail_end;
	private final ModelPart arm_l;
	private final ModelPart hand_l;
	private final ModelPart arm_r;
	private final ModelPart hand_r;

	public FlubberModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.body_main = this.swim_rot.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.neck = this.body.getChild("neck");
		this.head_flap = this.neck.getChild("head_flap");
		this.head = this.neck.getChild("head");
		this.trunk = this.head.getChild("trunk");
		this.hips = this.body.getChild("hips");
		this.leg_l = this.hips.getChild("leg_l");
		this.leg_r = this.hips.getChild("leg_r");
		this.tail = this.hips.getChild("tail");
		this.tail_end = this.tail.getChild("tail_end");
		this.arm_l = this.body_main.getChild("arm_l");
		this.hand_l = this.arm_l.getChild("hand_l");
		this.arm_r = this.body_main.getChild("arm_r");
		this.hand_r = this.arm_r.getChild("hand_r");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, -5.0F));

		PartDefinition body_main = swim_rot.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 3.0F, 2.0F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 79).addBox(0.0F, -14.0F, 2.0F, 0.0F, 4.0F, 11.0F, new CubeDeformation(0.001F))
		.texOffs(0, 0).addBox(-8.0F, -11.0F, -5.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(41, 50).addBox(-2.5F, -17.0F, -5.0F, 5.0F, 20.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -3.0F));

		PartDefinition head_flap = neck.addOrReplaceChild("head_flap", CubeListBuilder.create().texOffs(72, 67).addBox(0.0F, -4.0F, -5.0F, 0.0F, 9.0F, 13.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -15.0F, 2.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(81, 54).addBox(-3.0F, -2.65F, -2.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
		.texOffs(56, 100).addBox(-3.0F, -2.5F, -5.0F, 6.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -14.6F, -3.0F));

		PartDefinition trunk = head.addOrReplaceChild("trunk", CubeListBuilder.create().texOffs(78, 90).addBox(-2.0F, -0.1F, -3.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(23, 93).addBox(-2.0F, 6.9F, -3.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.4F, -5.0F));

		PartDefinition hips = body.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(65, 0).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(23, 80).addBox(0.0F, -8.0F, 0.0F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -0.1F, 11.0F));

		PartDefinition leg_l = hips.addOrReplaceChild("leg_l", CubeListBuilder.create().texOffs(93, 90).addBox(-1.0F, 0.0F, -1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(42, 92).addBox(3.0F, 0.0F, -1.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 3.0F, 2.5F));

		PartDefinition leg_r = hips.addOrReplaceChild("leg_r", CubeListBuilder.create().texOffs(93, 90).mirror().addBox(-3.0F, 0.0F, -1.5F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(42, 92).mirror().addBox(-6.0F, 0.0F, -1.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 3.0F, 2.5F));

		PartDefinition tail = hips.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(42, 80).addBox(-1.0F, -2.5F, -2.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.4F, 8.0F));

		PartDefinition tail_end = tail.addOrReplaceChild("tail_end", CubeListBuilder.create().texOffs(0, 50).addBox(-1.0F, -7.5F, 0.0F, 2.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(72, 49).addBox(0.0F, -8.5F, 1.0F, 0.0F, 1.0F, 16.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition arm_l = body_main.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(65, 21).addBox(0.0F, -2.0F, -2.5F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -2.0F, -3.5F));

		PartDefinition hand_l = arm_l.addOrReplaceChild("hand_l", CubeListBuilder.create().texOffs(57, 33).addBox(-1.0F, -1.0F, -2.5F, 9.0F, 2.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(-1.0F, 0.0F, -2.5F, 12.0F, 0.0F, 16.0F, new CubeDeformation(0.001F)), PartPose.offset(11.0F, -1.0F, 0.0F));

		PartDefinition arm_r = body_main.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(65, 21).mirror().addBox(-10.0F, -2.0F, -2.5F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.0F, -2.0F, -3.5F));

		PartDefinition hand_r = arm_r.addOrReplaceChild("hand_r", CubeListBuilder.create().texOffs(57, 33).mirror().addBox(-8.0F, -1.0F, -2.5F, 9.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 33).mirror().addBox(-11.0F, 0.0F, -2.5F, 12.0F, 0.0F, 16.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-11.0F, -1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 112, 112);
	}

	@Override
	public void setupAnim(Flubber entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (this.young){
			this.applyStatic(FlubberAnimsBasics.BABY);
		}

		this.animateIdle(entity.idleAnimationState, FlubberAnimsBasics.LAND_IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount*3f)));
		this.animateIdle(entity.idleAnimationState, FlubberAnimsBasics.SWIM_IDLE, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);

		this.animateWalk(FlubberAnimsBasics.SWIM, limbSwing, limbSwingAmount*entity.getInWaterTicks()/5f, 2, 2.5f);
		this.animateWalk(FlubberAnimsBasics.WALK, limbSwing, limbSwingAmount*2f*(1-entity.getInWaterTicks()/5f), 2, 2.5f);

		float prevHeadxRot = this.neck.xRot;
		float prevHeadyRot = this.neck.yRot;
		this.neck.xRot = prevHeadxRot + headPitch * ((float)Math.PI / 180F)/2;
		this.neck.yRot = prevHeadyRot + netHeadYaw * ((float)Math.PI / 180F)/2;

		this.swim_rot.xRot = Mth.lerp( entity.getInWaterTicks()/5f, 0, headPitch * ((float)Math.PI / 180F));
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