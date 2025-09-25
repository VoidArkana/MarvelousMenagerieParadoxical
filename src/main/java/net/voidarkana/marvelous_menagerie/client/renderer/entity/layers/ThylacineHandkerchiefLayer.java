package net.voidarkana.marvelous_menagerie.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.ThylacineModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteFatModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteIttyModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.trilobite.TrilobiteMidModel;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Thylacine;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Trilobite;

@OnlyIn(Dist.CLIENT)
public class ThylacineHandkerchiefLayer extends RenderLayer<Thylacine, ThylacineModel<Thylacine>> {

    private static final ResourceLocation RED = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/red.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/orange.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/yellow.png");
    private static final ResourceLocation LIME = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/lime.png");
    private static final ResourceLocation GREEN = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/green.png");
    private static final ResourceLocation CYAN = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/cyan.png");
    private static final ResourceLocation LIGHT_BLUE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/light_blue.png");
    private static final ResourceLocation BLUE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/blue.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/purple.png");
    private static final ResourceLocation MAGENTA = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/magenta.png");
    private static final ResourceLocation PINK = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/pink.png");
    private static final ResourceLocation BROWN = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/brown.png");
    private static final ResourceLocation BLACK = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/black.png");
    private static final ResourceLocation GRAY = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/gray.png");
    private static final ResourceLocation LIGHT_GRAY = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/light_gray.png");
    private static final ResourceLocation WHITE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/white.png");


    private static final ResourceLocation AMBER = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/amber.png");
    private static final ResourceLocation AQUA = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/aqua.png");
    private static final ResourceLocation BEIGE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/beige.png");
    private static final ResourceLocation CORAL = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/coral.png");
    private static final ResourceLocation FOREST = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/forest.png");
    private static final ResourceLocation GINGER = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/ginger.png");
    private static final ResourceLocation INDIGO = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/indigo.png");
    private static final ResourceLocation MAROON = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/maroon.png");
    private static final ResourceLocation MINT = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/mint.png");
    private static final ResourceLocation NAVY = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/navy.png");
    private static final ResourceLocation OLIVE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/olive.png");
    private static final ResourceLocation ROSE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/rose.png");
    private static final ResourceLocation SLATE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/slate.png");
    private static final ResourceLocation TAN = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/tan.png");
    private static final ResourceLocation TEAL = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/teal.png");
    private static final ResourceLocation VERDANT = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/thylacine/handkerchief/dye_depot/verdant.png");


    public ThylacineHandkerchiefLayer(RenderLayerParent<Thylacine, ThylacineModel<Thylacine>> pRenderer, EntityRendererProvider.Context pContext) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Thylacine entityLivingBaseIn, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

        Object object;
        ResourceLocation texture;

        if (entityLivingBaseIn.hasHandkerchief()) {

            switch (entityLivingBaseIn.getHandkerchiefColor()) {
                case 1 -> texture = RED;
                case 2 -> texture = ORANGE;
                case 3 -> texture = YELLOW;
                case 4 -> texture = LIME;
                case 5 -> texture = GREEN;
                case 6 -> texture = CYAN;
                case 7 -> texture = LIGHT_BLUE;
                case 8 -> texture = BLUE;
                case 9 -> texture = PURPLE;
                case 10 -> texture = MAGENTA;
                case 11 -> texture = PINK;
                case 12 -> texture = BROWN;
                case 13 -> texture = BLACK;
                case 14 -> texture = GRAY;
                case 15 -> texture = LIGHT_GRAY;

                case 16 -> texture = AMBER;
                case 17 -> texture = AQUA;
                case 18 -> texture = BEIGE;
                case 19 -> texture = CORAL;
                case 20 -> texture = FOREST;
                case 21 -> texture = GINGER;
                case 22 -> texture = INDIGO;
                case 23 -> texture = MAROON;
                case 24 -> texture = MINT;
                case 25 -> texture = NAVY;
                case 26 -> texture = OLIVE;
                case 27 -> texture = ROSE;
                case 28 -> texture = SLATE;
                case 29 -> texture = TAN;
                case 30 -> texture = TEAL;
                case 31 -> texture = VERDANT;

                default -> texture = WHITE;
            };

            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.getParentModel(), texture, pPoseStack, pBuffer, pPackedLight,
                    entityLivingBaseIn, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch,
                    pPartialTick, 1, 1, 1);

        }
    }
}
