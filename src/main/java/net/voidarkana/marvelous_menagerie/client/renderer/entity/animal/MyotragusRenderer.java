package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.GoatModel;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.myotragus.BabyMyotragusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.myotragus.MyotragusModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.thylacine.BabyThylacineModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.thylacine.ThylacineModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ThylacineHaloLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.ThylacineHandkerchiefLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Myotragus;
import net.voidarkana.marvelous_menagerie.common.entity.animal.Thylacine;

public class MyotragusRenderer<T extends Myotragus> extends MobRenderer<T, QuadrupedModel<T>> {

    private final GoatModel<T> adultModel;
    private final BabyMyotragusModel<T> babyModel;

    public MyotragusRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MyotragusModel<>(pContext.bakeLayer(MMModelLayers.MYOTRAGUS_LAYER)), 0.5f);
        adultModel = new GoatModel<>(pContext.bakeLayer(MMModelLayers.MYOTRAGUS_LAYER));
        babyModel = new BabyMyotragusModel<>(pContext.bakeLayer(MMModelLayers.BABY_MYOTRAGUS_LAYER));
    }

    @Override
    public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? babyModel : adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(T pEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/animal/myotragus/"+
                (pEntity.isBaby() ? "baby_" : "") +"myotragus.png");
    }
}
