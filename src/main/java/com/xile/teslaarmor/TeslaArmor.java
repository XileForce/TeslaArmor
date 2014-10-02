package com.xile.teslaarmor;

import cofh.api.energy.IEnergyContainerItem;
import cofh.core.item.ItemArmorAdv;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.StringHelper;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;


public class TeslaArmor extends ItemArmorAdv implements ISpecialArmor, IEnergyContainerItem {


    public static final ArmorProperties UNBLOCKABLE = new ArmorProperties(0, 0.0D, 0);

    public static int EnergyStored;


    public int maxEnergy = 550000;
    public int maxTransfer = 2500;

    public double absorbRatio = 0.88D;
    public int energyPerDamage = 95;

    public String[] textures = new String[2];

    public TeslaArmor(ArmorMaterial material, int type) {

        super(material, type);
    }

    @Override
    public boolean getIsRepairable(ItemStack itemToRepair, ItemStack stack) {

        return false;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {

        return EnumRarity.uncommon;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {

        if (StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown()) {
            list.add(StringHelper.shiftForDetails());
        }
        if (!StringHelper.isShiftKeyDown()) {

            return;
        }

        if (stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }
        list.add(StringHelper.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");
        list.add("\u00a7a" + "Click X To Toggle AOE Tesla Ability.");
        list.add("Note: Requires Full Suit Worn");
        list.add("Warning: Drains Energy Fast!");
        if (KeyInputHandler.ArmorOn == true)
            list.add("Tesla AOE Effect Activated");
        if (KeyInputHandler.ArmorOn == false)
            list.add("Tesla AOE Effect Deactivated");
        if (EventHandler.Flight == true)
            list.add("Flight Mode On");
        if (EventHandler.Flight == false)
            list.add("Flight Mode Off");
    }

    @Override
    public int getDisplayDamage(ItemStack stack) {

        if (stack.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(stack, 0);
        }
        return 1 + maxEnergy - stack.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxDamage(ItemStack stack) {

        return 1 + maxEnergy;
    }

    @Override
    public boolean isDamaged(ItemStack stack) {

        return stack.getItemDamage() != Short.MAX_VALUE;
    }

    protected int getBaseAbsorption() {

        return 20;
    }

    /**
     * Returns a % that each piece absorbs, set sums to 100.
     */
    protected int getAbsorptionRatio() {

        switch (armorType) {
            case 0:
                return 15;
            case 1:
                return 40;
            case 2:
                return 30;
            case 3:
                return 15;
        }
        return 0;
    }

    /* ISpecialArmor */
    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {

        if (source.isUnblockable()) {
            return UNBLOCKABLE;
        }
        int absorbMax = energyPerDamage > 0 ? 25 * getEnergyStored(armor) / energyPerDamage : 0;
        return new ArmorProperties(0, absorbRatio, absorbMax);
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {

        if (getEnergyStored(armor) >= energyPerDamage) {
            return Math.min(getBaseAbsorption(), 20) * getAbsorptionRatio() / 100;
        }
        return 0;
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {

       
        extractEnergy(stack, damage * energyPerDamage, false);
        
    }

    /* IEnergyContainerItem */
    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        int stored = container.stackTagCompound.getInteger("Energy");
        int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

        if (!simulate) {
            stored += receive;
            container.stackTagCompound.setInteger("Energy", stored);
        }
        return receive;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        int stored = container.stackTagCompound.getInteger("Energy");
        int extract = Math.min(maxExtract, stored);

        if (!simulate) {
            stored -= extract;
            container.stackTagCompound.setInteger("Energy", stored);
        }
        return extract;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        if (container.stackTagCompound == null) {
            EnergyHelper.setDefaultEnergyTag(container, 0);
        }
        return container.stackTagCompound.getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {

        return maxEnergy;
    }


//Armor Effects
int Ticks = 0;
public static ItemStack armorslot0;
public static ItemStack armorslot1;
public static ItemStack armorslot2;
public static ItemStack armorslot3;
public static boolean TBootsWorn;
public static boolean TLegsWorn;
public static boolean TChestWorn;
public static boolean THelmWorn;

@Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack armor) {
    //0 is Boots
    //1 is Legs
    //2 is Chest
    //3 is Helm
    armorslot0 = player.inventory.armorItemInSlot(0);
    armorslot1 = player.inventory.armorItemInSlot(1);
    armorslot2 = player.inventory.armorItemInSlot(2);
    armorslot3 = player.inventory.armorItemInSlot(3);

    //Detects Armor Worn By Player

    if (armorslot0 != null && armorslot0.getItem() == ItemLoader.itemBootsTesla) {
        TBootsWorn = true;
    }
    if (armorslot0 == null || armorslot0.getItem() != ItemLoader.itemBootsTesla) {
        TBootsWorn = false;
    }

    if ( armorslot1 != null && armorslot1.getItem() == ItemLoader.itemLegsTesla) {
        TLegsWorn = true;
    }
    if (armorslot1 == null || armorslot1.getItem() != ItemLoader.itemLegsTesla) {
        TLegsWorn = false;
    }

    if (armorslot2 != null && armorslot2.getItem() == ItemLoader.itemChestTesla) {
        TChestWorn = true;
    }
    if (armorslot2 == null || armorslot2.getItem() != ItemLoader.itemChestTesla) {
        TChestWorn = false;
    }

    if (armorslot3 != null && armorslot3.getItem() == ItemLoader.itemHelmetTesla) {
        THelmWorn = true;
    }
    if (armorslot3 == null || armorslot3.getItem() != ItemLoader.itemHelmetTesla) {
        THelmWorn = false;
    }

//Sets Up The NoPower Boolean And The EnergyStored Integer For Ease Of Use
    EnergyStored = armor.stackTagCompound.getInteger("Energy");

    //Just A Test For Charging Armor Without External Mods!
    //if (KeyInputHandler.ArmorOn == false)
       // armor.stackTagCompound.setInteger("Energy", (EnergyStored + 1000));


    //PotionEffects
    if (TBootsWorn == true && (EnergyStored > 0)) {
            player.addPotionEffect(new PotionEffect(Potion.jump.id, 500, 4));
    }
    if (THelmWorn == true && EnergyStored > 0){
        player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 500, 4));
    }
    if (EventHandler.Flight == true){
        armor.stackTagCompound.setInteger("Energy", (EnergyStored - 50));
    }
    //lets try and avoid negative energy levels...
if (EnergyStored < 0)
    armor.stackTagCompound.setInteger("Energy", 0);
    //And Too much power...
if (EnergyStored > 550000)
    armor.stackTagCompound.setInteger("Energy", 550000);

    //Attempt at Entity scan and target
    //Works But needs to filter player now.
    if (KeyInputHandler.ArmorOn == true && (EnergyStored > 0) && TChestWorn == true && THelmWorn == true && TLegsWorn == true && TBootsWorn == true) {
            armor.stackTagCompound.setInteger("Energy", (EnergyStored - 450));

            List entities = player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(player.posX - 12, player.posY - 12, player.posZ - 12, player.posX + 12, player.posY + 12, player.posZ + 12));
            if (entities != null && !entities.isEmpty()) {
                Iterator iterator = entities.iterator();

                double posX;
                double posY;
                double posZ;

                EntityLivingBase ent;
                Ticks++;
                System.out.println("This many ticks has passed " + Ticks);

                while (iterator.hasNext() && (Ticks >= 40)) {

                    ent = (EntityLivingBase) iterator.next();
                    posX = ent.posX;
                    posY = ent.posY;
                    posZ = ent.posZ;
//Pretty sure this is going to only ignore the player wearing the armor...could be wrong though...
                 if (ent != null && ent != player) {
                     EntityLightningBolt Lightning = new EntityLightningBolt(world, posX, posY, posZ);
                     world.spawnEntityInWorld(Lightning);
                     Ticks = 0;
                 }
                    {

                    }

                    }

                }
            }
        }







    public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {







    }


}











