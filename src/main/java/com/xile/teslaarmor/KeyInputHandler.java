package com.xile.teslaarmor;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import com.xile.teslaarmor.TeslaArmor;

public class KeyInputHandler {


    public static boolean ArmorOn;
    public static boolean SwordOn;
    public static boolean FlightOn;


    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(TeslaMainLoader.ArmorToggle.isPressed())
            ArmorOn ^= true;
        if (ArmorOn == true)
            System.out.println("Armor Powered On");
        if (ArmorOn == false)
            System.out.println("Armor Powered Off");
        if(TeslaMainLoader.FlightToggle.isPressed())
            FlightOn ^= true;
        if (FlightOn == true) System.out.println("Flight On");
        if (FlightOn == false)
        System.out.println("Armor Powered Off");


        if(TeslaMainLoader.SwordToggle.isPressed())
            SwordOn ^= true;



    }

}

