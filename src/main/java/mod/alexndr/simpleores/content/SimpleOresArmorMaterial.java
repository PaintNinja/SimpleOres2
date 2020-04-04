package mod.alexndr.simpleores.content;

import mod.alexndr.simpleores.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum SimpleOresArmorMaterial implements IArmorMaterial
{
    COPPER ("simpleores:copper", 8, new int [] {1,2,3,2}, 8,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN , 0.0F,
            ()-> { return Ingredient.fromItems(ModItems.copper_ingot);} ),
    TIN ("simpleores:tin", 9, new int [] {1,2,3,2}, 8,
         SoundEvents.ITEM_ARMOR_EQUIP_CHAIN , 0.0F,
            ()-> { return Ingredient.fromItems(ModItems.tin_ingot);} ),
    MYTHRIL ("simpleores:mythril", 22, new int [] {3,4,5,3}, 12,
             SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F,
            ()-> { return Ingredient.fromItems(ModItems.mythril_ingot);} ),
    ADAMANTIUM("simpleores:adamantium", 28, new int [] {2,6,8,3}, 3, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F,
            ()-> { return Ingredient.fromItems(ModItems.adamantium_ingot);} ),
    ONYX("simpleores:onyx", 45, new int [] {5,6,8,5}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0F,
            ()-> { return Ingredient.fromItems(ModItems.onyx_gem);} );

    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyValue<Ingredient> repairMaterial;

    private SimpleOresArmorMaterial(String nameIn, int maxDamageIn, int[] drAmtArray,
                                    int enchantabilityIn, SoundEvent soundIn,
                                    float toughnessIn,
                                    Supplier<Ingredient> repairMatIn)
    {
        name = nameIn;
        maxDamageFactor = maxDamageIn;
        damageReductionAmountArray = drAmtArray;
        enchantability = enchantabilityIn;
        soundEvent = soundIn;
        toughness = toughnessIn;
        repairMaterial = new LazyValue<>(repairMatIn);
    } // end ctor()

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
}  // end class SimpleOresArmorMaterial
