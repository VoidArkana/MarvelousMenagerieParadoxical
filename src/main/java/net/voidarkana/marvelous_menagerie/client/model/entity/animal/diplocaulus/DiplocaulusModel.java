package net.voidarkana.marvelous_menagerie.client.model.entity.animal.diplocaulus;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.DiplocaulusAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Diplocaulus;

public class DiplocaulusModel<T extends Diplocaulus> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart swim_ctrl;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart tail;
	private final ModelPart f_leg_left;
	private final ModelPart f_leg_right;
	private final ModelPart b_leg_left;
	private final ModelPart b_leg_right;

	public DiplocaulusModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.swim_ctrl = this.root.getChild("swim_ctrl");
		this.body_main = this.swim_ctrl.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.head = this.body.getChild("head");
		this.tail = this.body.getChild("tail");
		this.f_leg_left = this.body_main.getChild("f_leg_left");
		this.f_leg_right = this.body_main.getChild("f_leg_right");
		this.b_leg_left = this.body_main.getChild("b_leg_left");
		this.b_leg_right = this.body_main.getChild("b_leg_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_ctrl = root.addOrReplaceChild("swim_ctrl", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -4.0F));

		PartDefinition body_main = swim_ctrl.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -1.0F, -5.0F, 6.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(15, 28).addBox(0.0F, -2.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -0.75F, -5.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(19, 11).addBox(-7.0F, -2.0F, -1.0F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.1F))
		.texOffs(19, 17).addBox(-2.0F, -2.0F, 2.2F, 3.0F, 2.0F, 5.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 1.0F, -3.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -2.5F, 0.0F, 0.0F, 4.0F, 10.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -0.5F, 3.0F));

		PartDefinition f_leg_left = body_main.addOrReplaceChild("f_leg_left", CubeListBuilder.create().texOffs(0, 25).addBox(-0.5F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.5F, 0.75F, -2.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition f_leg_right = body_main.addOrReplaceChild("f_leg_right", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(-3.5F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 0.75F, -2.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition b_leg_left = body_main.addOrReplaceChild("b_leg_left", CubeListBuilder.create().texOffs(0, 25).addBox(-0.5F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(2.5F, 0.75F, 2.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition b_leg_right = body_main.addOrReplaceChild("b_leg_right", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(-3.5F, 0.0F, -1.5F, 4.0F, 0.0F, 3.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 0.75F, 2.5F, 0.0F, 0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(DiplocaulusAnims.SWIM, limbSwing, limbSwingAmount*4f*entity.getInWaterTicks()/5f, 4, 2.5f);
		this.animateWalk(DiplocaulusAnims.WALK, limbSwing, limbSwingAmount*4f*(1-entity.getInWaterTicks()/5f), 4, 2.5f);

		this.animateIdle(entity.idleAnimationState, DiplocaulusAnims.SWIM_IDLE, ageInTicks, 1.0f, entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount));
		this.animateIdle(entity.idleAnimationState, DiplocaulusAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.head.xRot = headPitch * ((float)Math.PI / 180F)/2;
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F)/2;

		this.swim_ctrl.xRot = Mth.rotLerp(entity.getInWaterTicks()/5f, 0, headPitch * ((float)Math.PI / 180F)/2);
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