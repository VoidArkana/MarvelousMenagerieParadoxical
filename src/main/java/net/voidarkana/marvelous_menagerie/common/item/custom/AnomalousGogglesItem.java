package net.voidarkana.marvelous_menagerie.common.item.custom;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.enchantment.MMEnchantmentsClass;
import net.voidarkana.marvelous_menagerie.common.item.MMArmorMaterials;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Consumer;

public class AnomalousGogglesItem extends ArmorItem {

    public AnomalousGogglesItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        if (stack.getEnchantmentLevel(MMEnchantmentsClass.CAMBRIAN_VISION.get()) > 0 && (player.isEyeInFluidType(Fluids.WATER.getFluidType()) || player.isEyeInFluidType(Fluids.FLOWING_WATER.getFluidType()))) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 10, 0, false, false));
        }
    }

    public static ItemStack getWornItem(Entity entity){
        if (!(entity instanceof LivingEntity livingEntity)){
            return ItemStack.EMPTY;
        }
        ItemStack item = livingEntity.getItemBySlot(EquipmentSlot.HEAD);
        if (!(item.getItem() instanceof AnomalousGogglesItem)){
            return ItemStack.EMPTY;
        }
        else {
            return item;
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) MarvelousMenagerie.PROXY.getArmorRenderProperties());
    }

    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {

        if (this.material == MMArmorMaterials.CARIS_SCUTE) {
            return "marvelous_menagerie:textures/models/armor/anomalous_goggles.png";
        }

        return super.getArmorTexture(stack, entity, slot, type);
    }
}
