package net.voidarkana.marvelous_menagerie.client.model.entity.animal.hallucigenia;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.BabyHallucigeniaAnims;
import net.voidarkana.marvelous_menagerie.client.animations.HallucigeniaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Hallucigenia;

public class BabyHallucigeniaModel<T extends Hallucigenia> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart hallucigenia;
	private final ModelPart body;
	private final ModelPart spikes;
	private final ModelPart spikes_l;
	private final ModelPart spikes_r;
	private final ModelPart neck;
	private final ModelPart arm_l;
	private final ModelPart arm_r;
	private final ModelPart neck_2;
	private final ModelPart head;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart legs;
	private final ModelPart leg_l1;
	private final ModelPart leg_l2;
	private final ModelPart leg_l3;
	private final ModelPart leg_l4;
	private final ModelPart leg_l5;
	private final ModelPart leg_r1;
	private final ModelPart leg_r2;
	private final ModelPart leg_r3;
	private final ModelPart leg_r4;
	private final ModelPart leg_r5;

	public BabyHallucigeniaModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.hallucigenia = this.root.getChild("hallucigenia");
		this.body = this.hallucigenia.getChild("body");
		this.spikes = this.body.getChild("spikes");
		this.spikes_l = this.spikes.getChild("spikes_l");
		this.spikes_r = this.spikes.getChild("spikes_r");
		this.neck = this.body.getChild("neck");
		this.arm_l = this.neck.getChild("arm_l");
		this.arm_r = this.neck.getChild("arm_r");
		this.neck_2 = this.neck.getChild("neck_2");
		this.head = this.neck_2.getChild("head");
		this.tail1 = this.body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.legs = this.hallucigenia.getChild("legs");
		this.leg_l1 = this.legs.getChild("leg_l1");
		this.leg_l2 = this.legs.getChild("leg_l2");
		this.leg_l3 = this.legs.getChild("leg_l3");
		this.leg_l4 = this.legs.getChild("leg_l4");
		this.leg_l5 = this.legs.getChild("leg_l5");
		this.leg_r1 = this.legs.getChild("leg_r1");
		this.leg_r2 = this.legs.getChild("leg_r2");
		this.leg_r3 = this.legs.getChild("leg_r3");
		this.leg_r4 = this.legs.getChild("leg_r4");
		this.leg_r5 = this.legs.getChild("leg_r5");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition hallucigenia = root.addOrReplaceChild("hallucigenia", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));

		PartDefinition body = hallucigenia.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.0F, -4.5F, 3.0F, 2.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition spikes = body.addOrReplaceChild("spikes", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, 0.0F));

		PartDefinition spikes_l = spikes.addOrReplaceChild("spikes_l", CubeListBuilder.create(), PartPose.offsetAndRotation(1.5F, 0.5F, -1.5F, 0.0F, 0.0F, -0.3927F));

		PartDefinition spikes_r1 = spikes_l.addOrReplaceChild("spikes_r1", CubeListBuilder.create().texOffs(10, 31).addBox(-0.5F, -6.0F, 0.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(19, 31).addBox(-0.5F, -6.0F, 3.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(13, 31).addBox(-0.5F, -6.0F, -3.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(16, 31).addBox(-0.5F, -6.0F, 1.5F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(7, 31).addBox(-0.5F, -6.0F, -1.5F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.5F, 0.0F, 0.0F, 0.7854F));

		PartDefinition spikes_r = spikes.addOrReplaceChild("spikes_r", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.5F, 0.5F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition spikes_r2 = spikes_r.addOrReplaceChild("spikes_r2", CubeListBuilder.create().texOffs(10, 31).mirror().addBox(-0.5F, -6.0F, 0.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(19, 31).mirror().addBox(-0.5F, -6.0F, 3.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(13, 31).mirror().addBox(-0.5F, -6.0F, -3.0F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(16, 31).mirror().addBox(-0.5F, -6.0F, 1.5F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(7, 31).mirror().addBox(-0.5F, -6.0F, -1.5F, 1.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(22, 12).addBox(-1.5F, -3.0F, -2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -1.0F, -4.5F));

		PartDefinition arm_l = neck.addOrReplaceChild("arm_l", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -0.5F, -4.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(1.0F, 0.0F, -2.0F));

		PartDefinition arm_r = neck.addOrReplaceChild("arm_r", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -0.5F, -4.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.001F)), PartPose.offset(-1.0F, 0.0F, -2.0F));

		PartDefinition neck_2 = neck.addOrReplaceChild("neck_2", CubeListBuilder.create().texOffs(9, 12).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition head = neck_2.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 19).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -2.0F, -3.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(0, 24).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -1.0F, 4.5F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(9, 18).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(11, 24).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -2.0F, 3.0F));

		PartDefinition legs = hallucigenia.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition leg_l1 = legs.addOrReplaceChild("leg_l1", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, -4.0F));

		PartDefinition leg_l1_r1 = leg_l1.addOrReplaceChild("leg_l1_r1", CubeListBuilder.create().texOffs(25, 0).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition leg_l2 = legs.addOrReplaceChild("leg_l2", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, -2.0F));

		PartDefinition leg_l2_r1 = leg_l2.addOrReplaceChild("leg_l2_r1", CubeListBuilder.create().texOffs(25, 6).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition leg_l3 = legs.addOrReplaceChild("leg_l3", CubeListBuilder.create().texOffs(22, 26).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition leg_l4 = legs.addOrReplaceChild("leg_l4", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 2.0F));

		PartDefinition leg_l4_r1 = leg_l4.addOrReplaceChild("leg_l4_r1", CubeListBuilder.create().texOffs(29, 26).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leg_l5 = legs.addOrReplaceChild("leg_l5", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 4.0F));

		PartDefinition leg_l5_r1 = leg_l5.addOrReplaceChild("leg_l5_r1", CubeListBuilder.create().texOffs(0, 31).mirror().addBox(-2.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition leg_r1 = legs.addOrReplaceChild("leg_r1", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -4.0F));

		PartDefinition leg_r1_r1 = leg_r1.addOrReplaceChild("leg_r1_r1", CubeListBuilder.create().texOffs(25, 0).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition leg_r2 = legs.addOrReplaceChild("leg_r2", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -2.0F));

		PartDefinition leg_r2_r1 = leg_r2.addOrReplaceChild("leg_r2_r1", CubeListBuilder.create().texOffs(25, 6).addBox(-1.0F, 0.0F, -0.5F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.5F, 0.0F, 0.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leg_r3 = legs.addOrReplaceChild("leg_r3", CubeListBuilder.create().texOffs(22, 26).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition leg_r4 = legs.addOrReplaceChild("leg_r4", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 2.0F));

		PartDefinition leg_r4_r1 = leg_r4.addOrReplaceChild("leg_r4_r1", CubeListBuilder.create().texOffs(29, 26).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition leg_r5 = legs.addOrReplaceChild("leg_r5", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, 4.0F));

		PartDefinition leg_r5_r1 = leg_r5.addOrReplaceChild("leg_r5_r1", CubeListBuilder.create().texOffs(0, 31).addBox(-0.5F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 48, 48);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){
			animateWalk(BabyHallucigeniaAnims.WALK, limbSwing*3, limbSwingAmount, 2.5f, 15);
		}

		this.head.xRot = Mth.lerp(entity.getOutOfWaterTicks()/5f, head.xRot + headPitch * ((float)Math.PI / 180F)/2,0);
		this.head.yRot = Mth.lerp(entity.getOutOfWaterTicks()/5f,head.yRot + netHeadYaw * ((float)Math.PI / 180F)/2,0);

		this.neck.xRot = Mth.lerp(entity.getOutOfWaterTicks()/5f, neck.xRot + headPitch * ((float)Math.PI / 180F)/2,0);
		this.neck.yRot = Mth.lerp(entity.getOutOfWaterTicks()/5f, neck.yRot + netHeadYaw * ((float)Math.PI / 180F)/2,0);

		this.animate(entity.stingAnimationState, HallucigeniaAnims.STING, ageInTicks, 1);

		this.animateIdle(entity.idleAnimationState, BabyHallucigeniaAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState,
				entity.flopSide() ? BabyHallucigeniaAnims.BEACHED_L : BabyHallucigeniaAnims.BEACHED_R,
				ageInTicks, 1.0F, (entity.getOutOfWaterTicks()/5f));
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