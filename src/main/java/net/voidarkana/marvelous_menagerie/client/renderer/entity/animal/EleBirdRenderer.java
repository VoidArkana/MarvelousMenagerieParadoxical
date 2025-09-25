package net.voidarkana.marvelous_menagerie.client.renderer.entity.animal;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.base.MarvelousModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird.BabyEleBirdModel;
import net.voidarkana.marvelous_menagerie.client.model.entity.animal.elephant_bird.EleBirdModel;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.EleBirdChestLayer;
import net.voidarkana.marvelous_menagerie.client.renderer.entity.layers.EleBirdSwagLayer;
import net.voidarkana.marvelous_menagerie.common.entity.animal.ElephantBird;

public class EleBirdRenderer extends MobRenderer<ElephantBird, MarvelousModel<ElephantBird>> {

    private final EleBirdModel<ElephantBird> elebirdModel;
    private final BabyEleBirdModel<ElephantBird> babyElebirdModel;

    private static final ResourceLocation REGULAR_TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/elephant_bird/elephant_bird.png");
    private static final ResourceLocation BABY_TEXTURE = new ResourceLocation(MarvelousMenagerie.MODID, "textures/entity/animal/elephant_bird/baby_elephant_bird.png");

    public EleBirdRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new EleBirdModel<>(pContext.bakeLayer(MMModelLayers.ELEBIRD_LAYER)), 1f);

        this.elebirdModel = new EleBirdModel<>(pContext.bakeLayer(MMModelLayers.ELEBIRD_LAYER));
        this.babyElebirdModel = new BabyEleBirdModel<>(pContext.bakeLayer(MMModelLayers.BABY_ELEBIRD_LAYER));

        this.addLayer(new EleBirdSwagLayer(this, pContext));
        this.addLayer(new EleBirdChestLayer(this, pContext));
    }

    @Override
    public ResourceLocation getTextureLocation(ElephantBird entity) {
        return entity.isBaby() ? BABY_TEXTURE : REGULAR_TEXTURE;
    }

    @Override
    public void render(ElephantBird pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isBaby()){
            this.model = babyElebirdModel;
        }
        else
            this.model = elebirdModel;

        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
