package net.voidarkana.marvelous_menagerie.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AnomalousGogglesModel extends HumanoidModel {

	public AnomalousGogglesModel(ModelPart root) {
        super(root);
	}

	public static LayerDefinition createArmorLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(new CubeDeformation(0.1f), 0.0F);
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.getChild("head");

		head.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(0, 40).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.25F))
				.texOffs(5, 52).addBox(-3.5F, -5.5F, -5.25F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(5, 52).addBox(0.5F, -5.5F, -5.25F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}