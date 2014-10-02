package com.xile.teslaarmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Tab extends CreativeTabs {

    public Tab(String tabLabel)
    {
        super(tabLabel);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return ItemLoader.itemChestTesla;
    }

}