package io.github.aidenlindsay.improvedmcarmoury;

import io.github.aidenlindsay.improvedmcarmoury.core.init.ItemInit;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen.ItemPickerMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MOD_ID)
public class Main {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "improvedmcarmoury";
    public static final CreativeModeTab MOD_GROUP = new ModGroup("modtab");

    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        bus.addListener(this::setup);

        ItemInit.ITEMS.register(bus);
        //BlockInit.BLOCKS.register(bus);

        //MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGeneration::generateOres);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    public static class ModGroup extends CreativeModeTab{

        public ModGroup(String label) { super(label); }

        @Override
        public ItemStack makeIcon() {
            return ItemInit.AMETHYST_AXE.get().getDefaultInstance();
        }
    }

}
