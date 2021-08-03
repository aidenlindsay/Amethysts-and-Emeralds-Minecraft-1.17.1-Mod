package io.github.aidenlindsay.improvedmcarmoury.core.init;

import io.github.aidenlindsay.improvedmcarmoury.Main;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.swing.*;
import java.util.function.Supplier;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Main.MOD_ID);


    //Tools
    public static final RegistryObject<Item> AMETHYST_SWORD = ITEMS.register("amethyst_sword",
            () -> new SwordItem(ModItemTier.AMETHYST, 7, 3.5f, new Item.Properties()
                    .stacksTo(1).fireResistant().tab(Main.MOD_GROUP)));

    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe",
            () -> new AxeItem(ModItemTier.AMETHYST, 9, 2.0f, new Item.Properties()
                    .stacksTo(1).fireResistant().tab(Main.MOD_GROUP)));

    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel",
            () -> new ShovelItem(ModItemTier.AMETHYST, 4, 2.0f, new Item.Properties()
                    .stacksTo(1).fireResistant().tab(Main.MOD_GROUP)));


    //Items
    public static final RegistryObject<Item> IRON_BAR = ITEMS.register("iron_bar",
            () -> new Item(new Item.Properties().stacksTo(64)));


    //Armour
    public static final RegistryObject<Item> CACTUS_HELMET = ITEMS.register("cactus_helmet",
            () -> new ArmorItem(ModArmorMaterial.CACTUS, EquipmentSlot.HEAD, new Item.Properties()
            .tab(Main.MOD_GROUP)));

    public static final RegistryObject<Item> CACTUS_CHESTPLATE = ITEMS.register("cactus_chestplate",
            () -> new ArmorItem(ModArmorMaterial.CACTUS, EquipmentSlot.CHEST, new Item.Properties()
                    .tab(Main.MOD_GROUP)));

    public static final RegistryObject<Item> CACTUS_LEGGINGS = ITEMS.register("cactus_leggings",
            () -> new ArmorItem(ModArmorMaterial.CACTUS, EquipmentSlot.LEGS, new Item.Properties()
                    .tab(Main.MOD_GROUP)));

    public static final RegistryObject<Item> CACTUS_BOOTS = ITEMS.register("cactus_boots",
            () -> new ArmorItem(ModArmorMaterial.CACTUS, EquipmentSlot.FEET, new Item.Properties()
                    .tab(Main.MOD_GROUP)));


    //Enums
    public enum ModItemTier implements Tier {

        AMETHYST(1500, 15.0f, 4.0f, 5, 250, () -> {
            return Ingredient.of(Items.AMETHYST_SHARD);
        });

        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int harvestLevel;
        private final int enchantability;
        private final Lazy<Ingredient> repairMaterial;

        private ModItemTier(int maxUses, float efficiency, float attackDamage, int harvestLevel, int enchantability, Supplier<Ingredient> repairMaterial){
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.harvestLevel = harvestLevel;
            this.enchantability = enchantability;
            this.repairMaterial = new Lazy<Ingredient>() {
                @Override
                public Ingredient get() {
                    return null;
                }
            };
        }

        @Override
        public int getUses() {
            return this.maxUses;
        }

        @Override
        public float getSpeed() {
            return this.efficiency;
        }

        @Override
        public float getAttackDamageBonus() {
            return this.attackDamage;
        }

        @Override
        public int getLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getEnchantmentValue() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return this.repairMaterial.get();
        }
    }

    public enum ModArmorMaterial implements ArmorMaterial {

        CACTUS(Main.MOD_ID + ":test", 5, new int[] {7, 9, 11, 7},  420, SoundEvents.CAVE_VINES_BREAK, 6.9f,
                () -> { return Ingredient.of(Items.CACTUS); }, 10);

        private static final int[] MAX_DAMAGE_ARRAY = new int[] {16, 16, 16, 16};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReductionAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final Lazy<Ingredient> repairMaterial;
        private final float knockbackRes;

        private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountIn,
                                 int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn,
                                 Supplier<Ingredient> repairMaterialIn, float knockbackResIn) {
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReductionAmountArray = damageReductionAmountIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.repairMaterial = new Lazy<Ingredient>(){
                @Override
                public Ingredient get() {
                    return null;
                }
            };
            this.knockbackRes = knockbackResIn;
        }

        @Override
        public int getDurabilityForSlot(EquipmentSlot equipmentSlot) {
            return MAX_DAMAGE_ARRAY[equipmentSlot.getIndex()] * this.maxDamageFactor;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot equipmentSlot) {
            return this.damageReductionAmountArray[equipmentSlot.getIndex()];
        }

        @Override
        public int getEnchantmentValue() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getEquipSound() {
            return this.soundEvent;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return this.repairMaterial.get();
        }

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return this.knockbackRes;
        }
    }
}
