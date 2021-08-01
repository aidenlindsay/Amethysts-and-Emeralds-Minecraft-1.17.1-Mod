package io.github.aidenlindsay.improvedmcarmoury.core.init;

import io.github.aidenlindsay.improvedmcarmoury.Main;
import io.github.aidenlindsay.improvedmcarmoury.common.items.SpecialAxe;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            Main.MOD_ID);

    public static final RegistryObject<Item> AMETHYST_AXE = ITEMS.register("amethyst_axe",
            () -> new Item(new Item.Properties()
                    .addToolType(ToolType.AXE, 10).stacksTo(1).fireResistant().tab(Main.MOD_GROUP)));

    public static final RegistryObject<Item> AMETHYST_SHOVEL = ITEMS.register("amethyst_shovel",
            () -> new Item(new Item.Properties()
                    .addToolType(ToolType.SHOVEL, 10).stacksTo(1).fireResistant().tab(Main.MOD_GROUP)));

    public static final RegistryObject<Item> IRON_BAR = ITEMS.register("iron_bar",
            () -> new Item(new Item.Properties().stacksTo(64)));
}
