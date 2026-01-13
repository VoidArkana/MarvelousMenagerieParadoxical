package net.voidarkana.marvelous_menagerie.client.model.entity.animal.borealopelta;// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.marvelous_menagerie.client.animations.BabyBorealopeltaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Borealopelta;

public class BabyBorealopeltaModel<T extends Borealopelta> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart body_main;
	private final ModelPart body;
	private final ModelPart tail1;
	private final ModelPart neck;
	private final ModelPart arms;
	private final ModelPart armRight;
	private final ModelPart armLeft;
	private final ModelPart legs;
	private final ModelPart legRight;
	private final ModelPart legLeft;

	public BabyBorealopeltaModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.body_main = this.root.getChild("body_main");
		this.body = this.body_main.getChild("body");
		this.tail1 = this.body.getChild("tail1");
		this.neck = this.body.getChild("neck");
		this.arms = this.body_main.getChild("arms");
		this.armRight = this.arms.getChild("armRight");
		this.armLeft = this.arms.getChild("armLeft");
		this.legs = this.body_main.getChild("legs");
		this.legRight = this.legs.getChild("legRight");
		this.legLeft = this.legs.getChild("legLeft");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body_main = root.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, -0.5F));

		PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -5.5F, 8.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 1.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, -1.5F, -1.0F, 4.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.5F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(27, 16).addBox(-2.0F, -1.5F, -5.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -5.5F));

		PartDefinition arms = body_main.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -2.5F));

		PartDefinition armRight = arms.addOrReplaceChild("armRight", CubeListBuilder.create().texOffs(27, 25).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 0.0F));

		PartDefinition armLeft = arms.addOrReplaceChild("armLeft", CubeListBuilder.create().texOffs(27, 25).mirror().addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 0.0F, 0.0F));

		PartDefinition legs = body_main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, 4.5F));

		PartDefinition legRight = legs.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 0.0F, 0.0F));

		PartDefinition legLeft = legs.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(0, 29).mirror().addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(Borealopelta entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (!entity.isInWaterOrBubble()){
			this.animateWalk(BabyBorealopeltaAnims.WALK, limbSwing, limbSwingAmount*2f, 2, 2.5f);

			this.animate(entity.idleShakeState, BabyBorealopeltaAnims.SHAKE, ageInTicks, 1);
		}

		this.animateIdle(entity.idleAnimationState, BabyBorealopeltaAnims.SWIM, ageInTicks, 1.0f, entity.getInWaterTicks()/5f);
		this.animateIdle(entity.idleAnimationState, BabyBorealopeltaAnims.IDLE, ageInTicks, 1.0f, Math.max(0, 1-entity.getInWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.neck.xRot = headPitch * ((float)Math.PI / 180F);
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