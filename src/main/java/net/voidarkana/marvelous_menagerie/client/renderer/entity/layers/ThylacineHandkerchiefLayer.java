package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Thylacine;

@OnlyIn(Dist.CLIENT)
public class ThylacineHandkerchiefLayer<T extends Thylacine> extends RenderLayer<T, MarvelousModel<T>> {

    public ThylacineHandkerchiefLayer(RenderLayerParent<T, MarvelousModel<T>> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T entityLivingBaseIn, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        ResourceLocation texture;

        if (entityLivingBaseIn.hasHandkerchief()) {

            float[] afloat = switch (entityLivingBaseIn.getHandkerchiefColor()) {
                case 1 -> {
                    afloat = DyeColor.RED.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 2 -> {
                    afloat = DyeColor.ORANGE.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 3 -> {
                    afloat = DyeColor.YELLOW.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 4 -> {
                    afloat = DyeColor.LIME.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 5 -> {
                    afloat = DyeColor.GREEN.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 6 -> {
                    afloat = DyeColor.CYAN.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 7 -> {
                    afloat = DyeColor.LIGHT_BLUE.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 8 -> {
                    afloat = DyeColor.BLUE.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 9 -> {
                    afloat = DyeColor.PURPLE.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 10 -> {
                    afloat = DyeColor.MAGENTA.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 11 -> {
                    afloat = DyeColor.PINK.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 12 -> {
                    afloat = DyeColor.BROWN.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 13 -> {
                    afloat = DyeColor.BLACK.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 14 -> {
                    afloat = DyeColor.GRAY.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 15 -> {
                    afloat = DyeColor.LIGHT_GRAY.getTextureDiffuseColors();
                    yield new float[]{afloat[0] * 0.75F, afloat[1] * 0.75F, afloat[2] * 0.75F};
                }
                case 16 -> new float[]{195 / 255f, 157 / 255f, 17 / 255f};
                case 17 -> new float[]{93 / 255f, 230 / 255f, 202 / 255f};
                case 18 -> new float[]{231 / 255f, 199 / 255f, 135 / 255f};
                case 19 -> new float[]{231 / 255f, 120 / 255f, 83 / 255f};
                case 20 -> new float[]{71 / 255f, 159 / 255f, 35 / 255f};
                case 21 -> new float[]{181 / 255f, 68 / 255f, 16 / 255f};
                case 22 -> new float[]{44 / 255f, 27 / 255f, 73 / 255f};
                case 23 -> new float[]{117 / 255f, 35 / 255f, 18 / 255f};
                case 24 -> new float[]{51 / 255f, 185 / 255f, 103 / 255f};
                case 25 -> new float[]{37 / 255f, 52 / 255f, 85 / 255f};
                case 26 -> new float[]{134 / 255f, 135 / 255f, 42 / 255f};
                case 27 -> new float[]{224 / 255f, 74 / 255f, 81 / 255f};
                case 28 -> new float[]{84 / 255f, 100 / 255f, 143 / 255f};
                case 29 -> new float[]{223 / 255f, 137 / 255f, 80 / 255f};
                case 30 -> new float[]{42 / 255f, 104 / 255f, 90 / 255f};
                case 31 -> new float[]{19 / 255f, 82 / 255f, 37 / 255f};
                default -> new float[]{0.9019608F, 0.9019608F, 0.9019608F};
            };

            texture = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/thylacine/thylacine_handkerchief"+(entityLivingBaseIn.isBaby()?"_baby":"")+".png");

            float f = afloat[0];
            float f1 = afloat[1];
            float f2 = afloat[2];

            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), texture, pPoseStack, pBuffer, pPackedLight,
                    entityLivingBaseIn, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTick, f, f1, f2);

        }
    }
}
