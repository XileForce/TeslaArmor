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
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cofh.CoFHCore;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid="TA", name="Tesla Armor", version="1.0.0", dependencies = TeslaMainLoader.dependencies)



public class TeslaMainLoader {
    public static final Logger log = LogManager.getLogger("TA");
    public static Item ElectrifiedIngot;
    public static KeyBinding ArmorToggle;
    public static KeyBinding SwordToggle;
    public static KeyBinding FlightToggle;
    public static final String dependencies = "required-after:CoFHCore@[" + CoFHCore.version + ",);after:CoFHLib@";

    @Instance(value = "TA")

    public static TeslaMainLoader instance;

    @SidedProxy(clientSide="com.xile.teslaarmor.ClientProxy", serverSide="com.xile.teslaarmor.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ItemLoader.preInit();

    }

    @EventHandler
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

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ItemLoader.postInit();
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("Initializing Tesla Armor Mod");
        System.out.println("");
        System.out.println("");
        System.out.println("**********************************************");
        System.out.println(" |||||||| |||||||| |||||||| ||       ||||||||");
        System.out.println("    ||             ||       ||       ||    ||");
        System.out.println("    ||    |||||||| |||||||| ||       || || ||");
        System.out.println("    ||                   || ||       ||    ||");
        System.out.println("    ||    |||||||| |||||||| |||||||| ||    ||");
        System.out.println("**********************************************");
        System.out.println("");
        System.out.println("");

    }
    public static CreativeTabs TeslaArmor = new Tab("TeslaArmor");







}