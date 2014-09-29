package com.xile.teslaarmor;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Created by RDAguiar on 9/29/2014.
 */
public class EventHandler {
    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        // This event has an Entity variable, access it like this:

// do something to player every update tick:
        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) event.entity;

            //Currently Detects Keypresses to toggle flight but works without armor worn...unsure why

            if (player.inventory.armorItemInSlot(1) == ItemLoader.armorTeslaPlate && (TeslaArmor.NoPower != true) && (KeyInputHandler.FlightOn == true)) {
                        player.capabilities.allowFlying = true;
            }
            else if (player.inventory.armorItemInSlot(1) != ItemLoader.armorTeslaPlate && !player.capabilities.isCreativeMode || (TeslaArmor.NoPower == true) || KeyInputHandler.FlightOn == false) {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
            }
        }
    }
}




