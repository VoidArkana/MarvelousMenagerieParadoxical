package net.voidarkana.marvelous_menagerie.client.model.entity.animal.hallucigenia;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.animations.HallucigeniaAnims;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Hallucigenia;

public class HallucigeniaModel<T extends Hallucigenia> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart hallucigenia;
	private final ModelPart legs;
	private final ModelPart legs_l;
	private final ModelPart leg_l1;
	private final ModelPart leg_l2;
	private final ModelPart leg_l3;
	private final ModelPart leg_l4;
	private final ModelPart leg_l5;
	private final ModelPart legs_r;
	private final ModelPart leg_r1;
	private final ModelPart leg_r2;
	private final ModelPart leg_r3;
	private final ModelPart leg_r4;
	private final ModelPart leg_r5;
	private final ModelPart body;
	private final ModelPart neck_overlay;
	private final ModelPart neck;
	private final ModelPart neck_2;
	private final ModelPart neck_3;
	private final ModelPart head;
	private final ModelPart arms;
	private final ModelPart arms_l;
	private final ModelPart arm_l1;
	private final ModelPart arm_l2;
	private final ModelPart arm_l3;
	private final ModelPart arm_l4;
	private final ModelPart arms_r;
	private final ModelPart arm_r1;
	private final ModelPart arm_r2;
	private final ModelPart arm_r3;
	private final ModelPart arm_r4;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;
	private final ModelPart spikes;
	private final ModelPart spikes_r;
	private final ModelPart spikes_l;

	public HallucigeniaModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.hallucigenia = this.root.getChild("hallucigenia");
		this.legs = this.hallucigenia.getChild("legs");
		this.legs_l = this.legs.getChild("legs_l");
		this.leg_l1 = this.legs_l.getChild("leg_l1");
		this.leg_l2 = this.legs_l.getChild("leg_l2");
		this.leg_l3 = this.legs_l.getChild("leg_l3");
		this.leg_l4 = this.legs_l.getChild("leg_l4");
		this.leg_l5 = this.legs_l.getChild("leg_l5");
		this.legs_r = this.legs.getChild("legs_r");
		this.leg_r1 = this.legs_r.getChild("leg_r1");
		this.leg_r2 = this.legs_r.getChild("leg_r2");
		this.leg_r3 = this.legs_r.getChild("leg_r3");
		this.leg_r4 = this.legs_r.getChild("leg_r4");
		this.leg_r5 = this.legs_r.getChild("leg_r5");
		this.body = this.hallucigenia.getChild("body");
		this.neck_overlay = this.body.getChild("neck_overlay");
		this.neck = this.neck_overlay.getChild("neck");
		this.neck_2 = this.neck.getChild("neck_2");
		this.neck_3 = this.neck_2.getChild("neck_3");
		this.head = this.neck_3.getChild("head");
		this.arms = this.neck.getChild("arms");
		this.arms_l = this.arms.getChild("arms_l");
		this.arm_l1 = this.arms_l.getChild("arm_l1");
		this.arm_l2 = this.arms_l.getChild("arm_l2");
		this.arm_l3 = this.arms_l.getChild("arm_l3");
		this.arm_l4 = this.arms_l.getChild("arm_l4");
		this.arms_r = this.arms.getChild("arms_r");
		this.arm_r1 = this.arms_r.getChild("arm_r1");
		this.arm_r2 = this.arms_r.getChild("arm_r2");
		this.arm_r3 = this.arms_r.getChild("arm_r3");
		this.arm_r4 = this.arms_r.getChild("arm_r4");
		this.tail1 = this.body.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.tail4 = this.tail3.getChild("tail4");
		this.spikes = this.body.getChild("spikes");
		this.spikes_r = this.spikes.getChild("spikes_r");
		this.spikes_l = this.spikes.getChild("spikes_l");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition hallucigenia = root.addOrReplaceChild("hallucigenia", CubeListBuilder.create(), PartPose.offset(-0.5F, -8.2F, 3.5F));

		PartDefinition legs = hallucigenia.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs_l = legs.addOrReplaceChild("legs_l", CubeListBuilder.create(), PartPose.offset(1.0F, -0.9F, -3.5F));

		PartDefinition leg_l1 = legs_l.addOrReplaceChild("leg_l1", CubeListBuilder.create().texOffs(52, 38).addBox(-0.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(-1, 70).addBox(2.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.5F, -7.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition leg_l2 = legs_l.addOrReplaceChild("leg_l2", CubeListBuilder.create().texOffs(52, 49).addBox(-0.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(10, 70).addBox(2.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.5F, -3.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leg_l3 = legs_l.addOrReplaceChild("leg_l3", CubeListBuilder.create().texOffs(54, 0).addBox(-0.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(46, 70).addBox(2.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, -0.5F, 0.0F));

		PartDefinition leg_l4 = legs_l.addOrReplaceChild("leg_l4", CubeListBuilder.create().texOffs(26, 58).addBox(-0.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(57, 70).addBox(2.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.5F, 3.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition leg_l5 = legs_l.addOrReplaceChild("leg_l5", CubeListBuilder.create().texOffs(37, 58).addBox(-0.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(72, 9).addBox(2.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.5F, 7.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition legs_r = legs.addOrReplaceChild("legs_r", CubeListBuilder.create(), PartPose.offset(0.0F, -0.9F, -3.5F));

		PartDefinition leg_r1 = legs_r.addOrReplaceChild("leg_r1", CubeListBuilder.create().texOffs(11, 60).addBox(-4.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(72, 15).addBox(-5.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.5F, -7.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition leg_r2 = legs_r.addOrReplaceChild("leg_r2", CubeListBuilder.create().texOffs(48, 60).addBox(-4.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(72, 18).addBox(-5.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.5F, -3.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition leg_r3 = legs_r.addOrReplaceChild("leg_r3", CubeListBuilder.create().texOffs(59, 60).addBox(-4.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(72, 21).addBox(-5.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -0.5F, 0.0F));

		PartDefinition leg_r4 = legs_r.addOrReplaceChild("leg_r4", CubeListBuilder.create().texOffs(62, 11).addBox(-4.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(72, 24).addBox(-5.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.5F, 3.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leg_r5 = legs_r.addOrReplaceChild("leg_r5", CubeListBuilder.create().texOffs(0, 60).addBox(-4.5F, -0.5F, 0.0F, 5.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(72, 12).addBox(-5.5F, 9.5F, -1.5F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -0.5F, 7.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition body = hallucigenia.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -8.0F, 3.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.8F, -3.0F));

		PartDefinition neck_overlay = body.addOrReplaceChild("neck_overlay", CubeListBuilder.create(), PartPose.offset(0.5F, 2.0F, -8.0F));

		PartDefinition neck = neck_overlay.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(26, 46).addBox(-1.5F, -8.0F, -3.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck_2 = neck.addOrReplaceChild("neck_2", CubeListBuilder.create().texOffs(0, 49).addBox(-1.5F, -7.0F, 0.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition neck_3 = neck_2.addOrReplaceChild("neck_3", CubeListBuilder.create().texOffs(37, 0).addBox(-1.5F, -3.0F, -5.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -7.0F, 3.0F));

		PartDefinition head = neck_3.addOrReplaceChild("head", CubeListBuilder.create().texOffs(49, 18).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(-0.01F))
		.texOffs(62, 22).addBox(0.5F, 1.0F, -4.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.004F))
		.texOffs(62, 29).addBox(-2.5F, 1.0F, -4.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.004F)), PartPose.offset(0.0F, -3.0F, -5.0F));

		PartDefinition arms = neck.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -3.5F, -3.0F));

		PartDefinition arms_l = arms.addOrReplaceChild("arms_l", CubeListBuilder.create(), PartPose.offset(0.75F, 0.0F, 0.0F));

		PartDefinition arm_l1 = arms_l.addOrReplaceChild("arm_l1", CubeListBuilder.create().texOffs(73, 28).addBox(0.0F, -1.5F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(-0.25F, -3.0F, 0.0F));

		PartDefinition arm_l2 = arms_l.addOrReplaceChild("arm_l2", CubeListBuilder.create().texOffs(34, 20).addBox(0.0F, -4.5F, -7.0F, 0.0F, 5.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.25F, -1.0F, 0.0F));

		PartDefinition arm_l3 = arms_l.addOrReplaceChild("arm_l3", CubeListBuilder.create().texOffs(0, 20).addBox(0.0F, -2.5F, -8.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(-0.001F)), PartPose.offset(-0.25F, 1.0F, 0.0F));

		PartDefinition arm_l4 = arms_l.addOrReplaceChild("arm_l4", CubeListBuilder.create().texOffs(13, 35).addBox(0.0F, -0.5F, -6.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.25F, 3.0F, 0.0F));

		PartDefinition arms_r = arms.addOrReplaceChild("arms_r", CubeListBuilder.create(), PartPose.offset(-0.75F, 0.0F, 0.0F));

		PartDefinition arm_r1 = arms_r.addOrReplaceChild("arm_r1", CubeListBuilder.create().texOffs(54, 11).addBox(0.0F, -1.5F, -2.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.25F, -3.0F, 0.0F));

		PartDefinition arm_r2 = arms_r.addOrReplaceChild("arm_r2", CubeListBuilder.create().texOffs(34, 33).addBox(0.0F, -4.5F, -7.0F, 0.0F, 5.0F, 7.0F, new CubeDeformation(-0.001F)), PartPose.offset(-0.25F, -1.0F, 0.0F));

		PartDefinition arm_r3 = arms_r.addOrReplaceChild("arm_r3", CubeListBuilder.create().texOffs(17, 20).addBox(0.0F, -2.5F, -8.0F, 0.0F, 6.0F, 8.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.25F, 1.0F, 0.0F));

		PartDefinition arm_r4 = arms_r.addOrReplaceChild("arm_r4", CubeListBuilder.create().texOffs(0, 35).addBox(0.0F, -0.5F, -6.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(-0.001F)), PartPose.offset(-0.25F, 3.0F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(39, 46).addBox(-1.5F, -8.0F, 0.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(0.5F, 2.0F, 7.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(13, 49).addBox(-1.5F, -7.0F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create().texOffs(37, 9).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -7.0F, -3.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create().texOffs(49, 28).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, new CubeDeformation(-0.01F)), PartPose.offset(0.0F, -3.0F, 5.0F));

		PartDefinition spikes = body.addOrReplaceChild("spikes", CubeListBuilder.create(), PartPose.offset(0.5F, -2.0F, -0.5F));

		PartDefinition spikes_r = spikes.addOrReplaceChild("spikes_r", CubeListBuilder.create().texOffs(27, 69).addBox(-1.5F, -10.5F, 2.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(70, 70).addBox(-1.5F, -8.5F, 6.0F, 2.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(32, 69).addBox(-1.5F, -9.5F, 4.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(37, 69).addBox(-1.5F, -9.5F, -4.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(68, 48).addBox(-1.5F, -10.5F, -2.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(22, 69).addBox(-1.5F, -10.5F, 0.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(70, 60).addBox(-1.5F, -8.5F, -6.0F, 2.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition spikes_l = spikes.addOrReplaceChild("spikes_l", CubeListBuilder.create().texOffs(42, 69).addBox(-0.5F, -8.5F, 0.0F, 2.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(65, 0).addBox(-0.5F, -9.5F, 2.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(63, 36).addBox(-0.5F, -10.5F, 4.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(63, 48).addBox(-0.5F, -10.5F, 6.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(68, 36).addBox(-0.5F, -10.5F, 8.0F, 2.0F, 11.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(26, 35).addBox(-0.5F, -9.5F, 10.0F, 2.0F, 10.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(70, 0).addBox(-0.5F, -8.5F, 12.0F, 2.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.5F, -6.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	public static LayerDefinition createEyesLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition hallucigenia = root.addOrReplaceChild("hallucigenia", CubeListBuilder.create(), PartPose.offset(-0.5F, -8.2F, 3.5F));

		PartDefinition legs = hallucigenia.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition legs_l = legs.addOrReplaceChild("legs_l", CubeListBuilder.create(), PartPose.offset(1.0F, -0.9F, -3.5F));

		PartDefinition leg_l1 = legs_l.addOrReplaceChild("leg_l1", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, -0.5F, -7.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition leg_l2 = legs_l.addOrReplaceChild("leg_l2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, -0.5F, -3.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leg_l3 = legs_l.addOrReplaceChild("leg_l3", CubeListBuilder.create(), PartPose.offset(0.5F, -0.5F, 0.0F));

		PartDefinition leg_l4 = legs_l.addOrReplaceChild("leg_l4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, -0.5F, 3.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition leg_l5 = legs_l.addOrReplaceChild("leg_l5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.5F, -0.5F, 7.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition legs_r = legs.addOrReplaceChild("legs_r", CubeListBuilder.create(), PartPose.offset(0.0F, -0.9F, -3.5F));

		PartDefinition leg_r1 = legs_r.addOrReplaceChild("leg_r1", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -0.5F, -7.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition leg_r2 = legs_r.addOrReplaceChild("leg_r2", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -0.5F, -3.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition leg_r3 = legs_r.addOrReplaceChild("leg_r3", CubeListBuilder.create(), PartPose.offset(-0.5F, -0.5F, 0.0F));

		PartDefinition leg_r4 = legs_r.addOrReplaceChild("leg_r4", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -0.5F, 3.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition leg_r5 = legs_r.addOrReplaceChild("leg_r5", CubeListBuilder.create(), PartPose.offsetAndRotation(-0.5F, -0.5F, 7.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition body = hallucigenia.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -1.8F, -3.0F));

		PartDefinition neck_overlay = body.addOrReplaceChild("neck_overlay", CubeListBuilder.create(), PartPose.offset(0.5F, 2.0F, -8.0F));

		PartDefinition neck = neck_overlay.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck_2 = neck.addOrReplaceChild("neck_2", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition neck_3 = neck_2.addOrReplaceChild("neck_3", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 3.0F));

		PartDefinition head = neck_3.addOrReplaceChild("head", CubeListBuilder.create().texOffs(62, 22).addBox(0.5F, 1.0F, -4.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.004F))
				.texOffs(62, 29).addBox(-2.5F, 1.0F, -4.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.004F)), PartPose.offset(0.0F, -3.0F, -5.0F));

		PartDefinition arms = neck.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -3.5F, -3.0F));

		PartDefinition arms_l = arms.addOrReplaceChild("arms_l", CubeListBuilder.create(), PartPose.offset(0.75F, 0.0F, 0.0F));

		PartDefinition arm_l1 = arms_l.addOrReplaceChild("arm_l1", CubeListBuilder.create(), PartPose.offset(-0.25F, -3.0F, 0.0F));

		PartDefinition arm_l2 = arms_l.addOrReplaceChild("arm_l2", CubeListBuilder.create(), PartPose.offset(0.25F, -1.0F, 0.0F));

		PartDefinition arm_l3 = arms_l.addOrReplaceChild("arm_l3", CubeListBuilder.create(), PartPose.offset(-0.25F, 1.0F, 0.0F));

		PartDefinition arm_l4 = arms_l.addOrReplaceChild("arm_l4", CubeListBuilder.create(), PartPose.offset(0.25F, 3.0F, 0.0F));

		PartDefinition arms_r = arms.addOrReplaceChild("arms_r", CubeListBuilder.create(), PartPose.offset(-0.75F, 0.0F, 0.0F));

		PartDefinition arm_r1 = arms_r.addOrReplaceChild("arm_r1", CubeListBuilder.create(), PartPose.offset(0.25F, -3.0F, 0.0F));

		PartDefinition arm_r2 = arms_r.addOrReplaceChild("arm_r2", CubeListBuilder.create(), PartPose.offset(-0.25F, -1.0F, 0.0F));

		PartDefinition arm_r3 = arms_r.addOrReplaceChild("arm_r3", CubeListBuilder.create(), PartPose.offset(0.25F, 1.0F, 0.0F));

		PartDefinition arm_r4 = arms_r.addOrReplaceChild("arm_r4", CubeListBuilder.create(), PartPose.offset(-0.25F, 3.0F, 0.0F));

		PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create(), PartPose.offset(0.5F, 2.0F, 7.0F));

		PartDefinition tail2 = tail1.addOrReplaceChild("tail2", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition tail3 = tail2.addOrReplaceChild("tail3", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, -3.0F));

		PartDefinition tail4 = tail3.addOrReplaceChild("tail4", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 5.0F));

		PartDefinition spikes = body.addOrReplaceChild("spikes", CubeListBuilder.create(), PartPose.offset(0.5F, -2.0F, -0.5F));

		PartDefinition spikes_r = spikes.addOrReplaceChild("spikes_r", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 0.5F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition spikes_l = spikes.addOrReplaceChild("spikes_l", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 0.5F, -6.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){
			animateWalk(HallucigeniaAnims.WALK, limbSwing*10, limbSwingAmount*5, 2.5f, 12);
		}

		this.head.xRot = Mth.lerp(entity.getOutOfWaterTicks()/5f, head.xRot + headPitch * ((float)Math.PI / 180F)/2,0);
		this.head.yRot = Mth.lerp(entity.getOutOfWaterTicks()/5f,head.yRot + netHeadYaw * ((float)Math.PI / 180F)/2,0);

		this.neck.xRot = Mth.lerp(entity.getOutOfWaterTicks()/5f, neck.xRot + headPitch * ((float)Math.PI / 180F)/2,0);
		this.neck.yRot = Mth.lerp(entity.getOutOfWaterTicks()/5f, neck.yRot + netHeadYaw * ((float)Math.PI / 180F)/2,0);

		this.animate(entity.stingAnimationState, HallucigeniaAnims.STING, ageInTicks, 1);
		this.animateIdle(entity.idleAnimationState, HallucigeniaAnims.IDLE, ageInTicks, 1, Math.max(0, 1-entity.getOutOfWaterTicks()/5f-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState,
				entity.flopSide() ? HallucigeniaAnims.BEACHED_L : HallucigeniaAnims.BEACHED_R,
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