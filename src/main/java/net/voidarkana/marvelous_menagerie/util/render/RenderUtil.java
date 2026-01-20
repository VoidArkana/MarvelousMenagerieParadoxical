package net.voidarkana.marvelous_menagerie.util.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.CrashReport;
import net.minecraft.ReportedException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RenderUtil {
    public static final Set<UUID> hiddenEntities  = Collections.synchronizedSet(new HashSet<>());

    public static <T extends Entity> void renderEntity(T pEntity, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        EntityRenderDispatcher entityRenderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        EntityRenderer<? super T> renderer = entityRenderDispatcher.getRenderer(pEntity);
        try {
            renderer.render(pEntity, 0.0F, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
        } catch (Throwable throwable) {
            throw new ReportedException(CrashReport.forThrowable(throwable, "Error rendering entity"));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static <T extends Entity> boolean shouldSkipRendering(boolean pFirstPerson, T pEntity) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        boolean isFirstPerson = Minecraft.getInstance().options.getCameraType().isFirstPerson();
        return pFirstPerson ? isFirstPerson : !isFirstPerson && pEntity == localPlayer;
    }

    @OnlyIn(Dist.CLIENT)
    public static <T extends Entity> EntityRenderer<? super T> getEntityRenderer(@Nullable T pEntity) {
        if (pEntity == null) {
            return null;
        }
        return Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(pEntity);
    }

    public static void renderEntityInInventoryWithoutHeadFollowing(GuiGraphics pGuiGraphics, int pX, int pY, int pScale, float angleXComponent, float angleYComponent, LivingEntity pEntity) {
        float f = angleXComponent;
        float f1 = angleYComponent;
        Quaternionf quaternionf = (new Quaternionf()).rotateZ((float)Math.PI);
        Quaternionf quaternionf1 = (new Quaternionf()).rotateX(f1 * 20.0F * ((float)Math.PI / 180F));
        quaternionf.mul(quaternionf1);
        float f2 = pEntity.yBodyRot;
        float f3 = pEntity.getYRot();
        float f4 = pEntity.getXRot();
        float f5 = pEntity.yHeadRotO;
        float f6 = pEntity.yHeadRot;
        pEntity.yBodyRot = 180.0F + f * 20.0F;
        pEntity.setYRot(180.0F + f * 40.0F);

        pEntity.setXRot(0);

        pEntity.yHeadRot = (float) (180.0F +  Math.toRadians(-45) * 40.0F);
        pEntity.yHeadRotO = (float) (180.0F + Math.toRadians(-45) * 40.0F);

        InventoryScreen.renderEntityInInventory(pGuiGraphics, pX, pY, pScale, quaternionf, quaternionf1, pEntity);
        pEntity.yBodyRot = f2;
        pEntity.setYRot(f3);
        pEntity.setXRot(f4);

        pEntity.yHeadRotO = f5;
        pEntity.yHeadRot = f6;
    }
}