package net.voidarkana.marvelous_menagerie.client.renderer.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.voidarkana.marvelous_menagerie.client.model.MMModelLayers;
import net.voidarkana.marvelous_menagerie.client.model.armor.AnomalousGogglesModel;
import net.voidarkana.marvelous_menagerie.common.item.MMItems;

public class CustomArmorRenderProperties implements IClientItemExtensions {

    private static boolean init;

    public static AnomalousGogglesModel GOGGLES_MODEL;

    public static void initializeModels() {
        init = true;
        GOGGLES_MODEL = new AnomalousGogglesModel(Minecraft.getInstance().getEntityModels().bakeLayer(MMModelLayers.GOGGLES_LAYER));
    }

    @Override
    public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
        if(!init){
            initializeModels();
        }
        final var item = itemStack.getItem();


        if(item == MMItems.ANOMALOUS_GOGGLES.get()){
            return GOGGLES_MODEL;
        }

        return _default;
    }
}
