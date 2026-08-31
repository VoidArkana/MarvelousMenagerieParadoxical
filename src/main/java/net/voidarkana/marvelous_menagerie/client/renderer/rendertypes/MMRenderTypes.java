package net.voidarkana.marvelous_menagerie.client.renderer.rendertypes;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.Util;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ForgeRenderTypes;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.event.client.MMClientEvents;

import java.util.function.Function;

public class MMRenderTypes extends RenderType {

    protected static final RenderStateShard.ShaderStateShard RENDERTYPE_SEPIA_SHADER = new RenderStateShard.ShaderStateShard(MMClientEvents::getSepiaShader);

    public MMRenderTypes(String s, VertexFormat format, VertexFormat.Mode mode, int i, boolean b1, boolean b2, Runnable runnable1, Runnable runnable2) {
        super(s, format, mode, i, b1, b2, runnable1, runnable2);
    }

    public static RenderType getBookWidget(ResourceLocation locationIn, boolean sepia) {
        if(sepia){
            return create("book_widget", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256,
                    false, true, RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_SEPIA_SHADER)
                    .setCullState(NO_CULL)
                    .setTextureState(new RenderStateShard.TextureStateShard(locationIn, false, false))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setOverlayState(NO_OVERLAY)
                    .createCompositeState(true));
        }else{
            return ForgeRenderTypes.getUnlitTranslucent(locationIn);
        }
    }


    public static final RenderStateShard.TransparencyStateShard ADDITIVE_TRANSPARENCY = new RenderStateShard.TransparencyStateShard("lightning_transparency", () -> {
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
    }, () -> {
        RenderSystem.disableBlend();
        RenderSystem.defaultBlendFunc();
    });

    public static final RenderType GLOWING = RenderType.create(
            MarvelousMenagerie.MOD_ID + ":glowing",
            DefaultVertexFormat.POSITION_COLOR,
            VertexFormat.Mode.QUADS, 256, true, false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(MMClientEvents::getGlowingShader))
                    .setLightmapState(new RenderStateShard.LightmapStateShard(false))
                    .setTextureState(new RenderStateShard.TextureStateShard(new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/white.png"),false, false))
                    .setWriteMaskState(COLOR_WRITE)
                    .setTransparencyState(ADDITIVE_TRANSPARENCY)
                    .setCullState(NO_CULL)
                    .setDepthTestState(EQUAL_DEPTH_TEST)
                    .createCompositeState(false)
    );


    public static RenderType ScrollingTex(ResourceLocation resourceLocation, float uOffset, float vOffset) {
        return create(MarvelousMenagerie.MOD_ID + "scrolling_texture",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256, false, true,
                RenderType.CompositeState.builder()
                        .setShaderState(RenderStateShard.RENDERTYPE_ENERGY_SWIRL_SHADER)
                        .setTextureState(new RenderStateShard.TextureStateShard(resourceLocation, false, false))
                        .setTexturingState(new RenderStateShard.OffsetTexturingStateShard(uOffset, vOffset))
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setCullState(NO_CULL)
                        .setLightmapState(LIGHTMAP)
                        .setOverlayState(OVERLAY)
                        .createCompositeState(false));
    }

//    public static RenderType Whiteness() {
//        return create(MarvelousMenagerie.MOD_ID + "scrolling_texture",
//                DefaultVertexFormat.NEW_ENTITY,
//                VertexFormat.Mode.QUADS,
//                256, false, true,
//                RenderType.CompositeState.builder()
//                        .setShaderState(RenderStateShard.RENDERTYPE_ENERGY_SWIRL_SHADER)
//                        .setTextureState(new RenderStateShard.TextureStateShard(new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/white.png"),
//                                false, false))
//                        .setTexturingState(new RenderStateShard.OffsetTexturingStateShard(0, 0))
//                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
//                        .setCullState(NO_CULL)
//                        .setLightmapState(LIGHTMAP)
//                        .setOverlayState(OVERLAY)
//                        .createCompositeState(false));
//
//        private static final Function<ResourceLocation, RenderType> EYES = Util.memoize((p_286170_) -> {
//            RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(p_286170_, false, false);
//            return create("eyes", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_EYES_SHADER).setTextureState(renderstateshard$texturestateshard).setTransparencyState(ADDITIVE_TRANSPARENCY).setWriteMaskState(COLOR_WRITE).createCompositeState(false));
//        });
//    }
}
