// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


public class baby_elephant_bird<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "baby_elephant_bird"), "main");
	private final ModelPart root;
	private final ModelPart main;
	private final ModelPart body;
	private final ModelPart neck;
	private final ModelPart legs;
	private final ModelPart leg_left;
	private final ModelPart leg_right;

	public baby_elephant_bird(ModelPart root) {
		this.root = root.getChild("root");
		this.main = this.root.getChild("main");
		this.body = this.main.getChild("body");
		this.neck = this.body.getChild("neck");
		this.legs = this.main.getChild("legs");
		this.leg_left = this.legs.getChild("leg_left");
		this.leg_right = this.legs.getChild("leg_right");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition main = root.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 5.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -8.0F, 0.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(15, 12).addBox(-1.0F, -5.5F, -3.0F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-1.0F, -9.5F, -4.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(17, 0).addBox(-0.5F, -6.5F, -6.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -3.0F));

		PartDefinition legs = main.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(1.5F, -5.0F, 0.0F));

		PartDefinition leg_left = legs.addOrReplaceChild("leg_left", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg_left_r1 = leg_left.addOrReplaceChild("leg_left_r1", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 5.0F, -0.75F, 0.0F, -0.7854F, 0.0F));

		PartDefinition leg_right = legs.addOrReplaceChild("leg_right", CubeListBuilder.create().texOffs(3, 0).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offset(-3.0F, 0.0F, 0.0F));

		PartDefinition leg_right_r1 = leg_right.addOrReplaceChild("leg_right_r1", CubeListBuilder.create().texOffs(7, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 0.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, 5.0F, -0.75F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}