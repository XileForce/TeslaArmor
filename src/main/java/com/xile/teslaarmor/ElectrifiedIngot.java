package com.xile.teslaarmor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ElectrifiedIngot extends Item {

    public ElectrifiedIngot()
    {
       setMaxStackSize(16);
       setUnlocalizedName("ElectrifiedIngot");
       setCreativeTab(TeslaMainLoader.TeslaArmor);

    }
}
