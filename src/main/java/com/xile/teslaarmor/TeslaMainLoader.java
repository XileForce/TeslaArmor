package com.xile.teslaarmor;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cofh.CoFHCore;
import cofh.core.CoFHProps;
import cofh.core.util.ConfigHandler;
import cofh.mod.BaseMod;
import cofh.mod.updater.UpdateManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.xile.teslaarmor.ItemLoader;
import org.lwjgl.input.Keyboard;

@Mod(modid="TA", name="Tesla Armor", version="0.0.1")



public class TeslaMainLoader {
    public static final Logger log = LogManager.getLogger("TA");
    public static Item ElectrifiedIngot;
    public static KeyBinding ArmorToggle;
    public static KeyBinding SwordToggle;
    public static KeyBinding FlightToggle;

    // The instance of your mod that Forge uses.
    @Instance(value = "TA")

    public static TeslaMainLoader instance;

    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="com.xile.teslaarmor.ClientProxy", serverSide="com.xile.teslaarmor.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler // used in 1.6.2
    //@PreInit    // used in 1.5.2
    public void preInit(FMLPreInitializationEvent event) {
        ItemLoader.preInit();


        // The second parameter is an unique registry identifier (not the displayed name)
        // Please don't use genericItem.getUnlocalizedName(), or you will make Lex sad

    }

    @EventHandler // used in 1.6.2
    //@Init       // used in 1.5.2
    public void load(FMLInitializationEvent event) {
        proxy.registerRenderers();
        ItemLoader.initialize();
        ArmorToggle = new KeyBinding("key.ArmorToggle", Keyboard.KEY_X, "key.categories.TeslaArmor");
        SwordToggle = new KeyBinding("key.SwordToggle", Keyboard.KEY_V, "key.categories.TeslaArmor");
        FlightToggle = new KeyBinding("key.FlightToggle", Keyboard.KEY_BACKSLASH, "key.categories.TeslaArmor");
        ClientRegistry.registerKeyBinding(ArmorToggle);
        ClientRegistry.registerKeyBinding(SwordToggle);
        ClientRegistry.registerKeyBinding(FlightToggle);
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
        MinecraftForge.EVENT_BUS.register(new com.xile.teslaarmor.EventHandler());


    }

    @EventHandler // used in 1.6.2
    //@PostInit   // used in 1.5.2
    public void postInit(FMLPostInitializationEvent event) {
        ItemLoader.postInit();
        // Stub Method
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        // On Startup
        System.out.println("Initializing Tesla Armor Mod");

    }
    public static CreativeTabs TeslaArmor = new Tab("TeslaArmor");







}