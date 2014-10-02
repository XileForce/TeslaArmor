package com.xile.teslaarmor;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.Iterator;
import java.util.List;

/**
 * Created by RDAguiar on 9/29/2014.
 */
public class EventHandler {
    EntityLivingBase ent;
    World world;
    public static boolean Flight;
    @SubscribeEvent
    public void onLivingUpdateEvent(LivingEvent.LivingUpdateEvent event) {
        // This event has an Entity variable, access it like this:

// do something to player every update tick:
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            //0 is Boots
            //1 is Legs
            //2 is Chest
            //3 is Helm


            //Tesla Chestplate ElectroStatic Flight Ability

            if ((TeslaArmor.TChestWorn == true) && (TeslaArmor.EnergyStored > 0) && (KeyInputHandler.FlightOn == true)) {
                player.capabilities.allowFlying = true;
                Flight = true;
            }
            if ((TeslaArmor.TChestWorn == false && !player.capabilities.isCreativeMode) || (TeslaArmor.EnergyStored <= 0) || (KeyInputHandler.FlightOn == false)) {
                player.capabilities.allowFlying = false;
                player.capabilities.isFlying = false;
            }
            if ((TeslaArmor.TChestWorn == false) || (TeslaArmor.EnergyStored <= 0) || (KeyInputHandler.FlightOn == false))
            Flight = false;
            //Legs SpeedBoost
            if (TeslaArmor.TLegsWorn == true && (TeslaArmor.EnergyStored > 0)) {
                player.capabilities.setPlayerWalkSpeed(0.2F);
            }
            if (TeslaArmor.TLegsWorn == false || TeslaArmor.EnergyStored <= 0){
                player.capabilities.setPlayerWalkSpeed(0.1F);
            }

            }
        }
    }






