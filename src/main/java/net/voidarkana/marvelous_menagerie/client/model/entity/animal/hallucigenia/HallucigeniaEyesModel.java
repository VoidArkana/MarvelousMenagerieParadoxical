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


public class HallucigeniaEyesModel<T extends Hallucigenia> extends MarvelousModel<T> {

	private final ModelPart root;
	private final ModelPart hallucigenia;
	private final ModelPart body;
	private final ModelPart neck_overlay;
	private final ModelPart neck;
	private final ModelPart neck_2;
	private final ModelPart neck_3;
	private final ModelPart head;

	public HallucigeniaEyesModel(ModelPart root) {
        super(1, 1);
        this.root = root.getChild("root");
		this.hallucigenia = this.root.getChild("hallucigenia");
		this.body = this.hallucigenia.getChild("body");
		this.neck_overlay = this.body.getChild("neck_overlay");
		this.neck = this.neck_overlay.getChild("neck");
		this.neck_2 = this.neck.getChild("neck_2");
		this.neck_3 = this.neck_2.getChild("neck_3");
		this.head = this.neck_3.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition hallucigenia = root.addOrReplaceChild("hallucigenia", CubeListBuilder.create(), PartPose.offset(-0.5F, -8.2F, 3.5F));

		PartDefinition body = hallucigenia.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -1.8F, -3.0F));

		PartDefinition neck_overlay = body.addOrReplaceChild("neck_overlay", CubeListBuilder.create(), PartPose.offset(0.5F, 2.0F, -8.0F));

		PartDefinition neck = neck_overlay.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition neck_2 = neck.addOrReplaceChild("neck_2", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition neck_3 = neck_2.addOrReplaceChild("neck_3", CubeListBuilder.create(), PartPose.offset(0.0F, -7.0F, 3.0F));

		PartDefinition head = neck_3.addOrReplaceChild("head", CubeListBuilder.create().texOffs(62, 22).addBox(0.5F, 1.0F, -4.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.004F))
		.texOffs(62, 29).addBox(-2.5F, 1.0F, -4.0F, 2.0F, 3.0F, 3.0F, new CubeDeformation(-0.004F)), PartPose.offset(0.0F, -3.0F, -5.0F));

		return LayerDefinition.create(meshdefinition, 96, 96);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		float partialTick = ageInTicks - entity.tickCount;
		animateWalk(HallucigeniaAnims.WALK, limbSwing*10, limbSwingAmount*5, 2.5f, 12*entity.getInWaterMultiplier(partialTick));

		this.animateIdle(entity.stretchState, HallucigeniaAnims.STRETCH, ageInTicks, 1, entity.getInWaterMultiplier(partialTick));
		this.animateIdle(entity.admireState, HallucigeniaAnims.ADMIRE, ageInTicks, 1, entity.getInWaterMultiplier(partialTick));

		this.head.xRot = Mth.lerp(1-entity.getInWaterMultiplier(partialTick), head.xRot + headPitch * ((float)Math.PI / 180F)/2,0);
		this.head.yRot = Mth.lerp(1-entity.getInWaterMultiplier(partialTick),head.yRot + netHeadYaw * ((float)Math.PI / 180F)/2,0);

		this.neck.xRot = Mth.lerp(1-entity.getInWaterMultiplier(partialTick), neck.xRot + headPitch * ((float)Math.PI / 180F)/2,0);
		this.neck.yRot = Mth.lerp(1-entity.getInWaterMultiplier(partialTick), neck.yRot + netHeadYaw * ((float)Math.PI / 180F)/2,0);

		this.animate(entity.stingAnimationState, HallucigeniaAnims.STING, ageInTicks, 1);
		this.animateIdle(entity.idleAnimationState, HallucigeniaAnims.IDLE, ageInTicks, 1, Math.max(0, entity.getInWaterMultiplier(partialTick)-Math.abs(limbSwingAmount)));

		this.animateIdle(entity.idleAnimationState,
				entity.flopSide() ? HallucigeniaAnims.BEACHED_L : HallucigeniaAnims.BEACHED_R,
				ageInTicks, 1.0F, (1-entity.getInWaterMultiplier(partialTick)));
	}

	@Override
	public ModelPart root() {
		return root;
	}
}