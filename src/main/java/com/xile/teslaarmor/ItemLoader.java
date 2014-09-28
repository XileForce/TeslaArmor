package com.xile.teslaarmor;
import cofh.lib.util.helpers.EnergyHelper;;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import com.xile.teslaarmor.TeslaArmor;
import com.xile.teslaarmor.TeslaMainLoader;
import com.xile.teslaarmor.ElectrifiedIngot;

public class ItemLoader {



    public static void preInit() {
        //Register Ingot
        ElectrifiedIngot = (ElectrifiedIngot) new ElectrifiedIngot().setTextureName("TA:ElectrifiedIngot");
        GameRegistry.registerItem(ElectrifiedIngot, "ElectrifiedIngot");

        //Register Armors
        itemHelmetTesla = (TeslaArmor) new TeslaArmor(ARMOR_MATERIAL_Tesla, 0).setArmorTextures(TEXTURE_Tesla).setUnlocalizedName("ArmorTeslaHelmet")
                .setTextureName("TA:THelm").setCreativeTab(TeslaMainLoader.TeslaArmor);
        itemChestTesla = (TeslaArmor) new TeslaArmor(ARMOR_MATERIAL_Tesla, 1).setArmorTextures(TEXTURE_Tesla).setUnlocalizedName("ArmorTeslaChest")
                .setTextureName("TA:TChest").setCreativeTab(TeslaMainLoader.TeslaArmor);
        itemLegsTesla = (TeslaArmor) new TeslaArmor(ARMOR_MATERIAL_Tesla, 2).setArmorTextures(TEXTURE_Tesla).setUnlocalizedName("ArmorTeslaLegs")
                .setTextureName("TA:Tlegs").setCreativeTab(TeslaMainLoader.TeslaArmor);
        itemBootsTesla = (TeslaArmor) new TeslaArmor(ARMOR_MATERIAL_Tesla, 3).setArmorTextures(TEXTURE_Tesla).setUnlocalizedName("ArmorTeslaBoots")
                .setTextureName("TA:TBoots").setCreativeTab(TeslaMainLoader.TeslaArmor);
        GameRegistry.registerItem(itemHelmetTesla, "armor.helmetTesla");
        GameRegistry.registerItem(itemChestTesla, "armor.chestTesla");
        GameRegistry.registerItem(itemLegsTesla, "armor.legsTesla");
        GameRegistry.registerItem(itemBootsTesla, "armor.bootsTesla");

    }


    public static void initialize() {

        loadItems();
    }

    private static void loadItems() {
        ItemStack armorTeslaHelmet;
        ItemStack armorTeslaChest;
        ItemStack armorTeslaLegs;
        ItemStack armorTeslaBoots;


		// Create Armor Stacks
        armorTeslaHelmet = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemHelmetTesla), 0);
        armorTeslaChest = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemChestTesla), 0);
        armorTeslaLegs = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemLegsTesla), 0);
        armorTeslaBoots = EnergyHelper.setDefaultEnergyTag(new ItemStack(itemBootsTesla), 0);

        GameRegistry.registerCustomItemStack("armorTeslaHelmet", armorTeslaHelmet);
        GameRegistry.registerCustomItemStack("armorTeslaPlate", armorTeslaChest);
        GameRegistry.registerCustomItemStack("armorTeslaLegs", armorTeslaLegs);
        GameRegistry.registerCustomItemStack("armorTeslaBoots", armorTeslaBoots);
    }
    public static void postInit(){}

    //Register All Public Variables
    protected static int mMaximumEnergyGeneration;
    protected static int mMaximumEnergyTransfer;

    public static TeslaArmor itemHelmetTesla;
    public static TeslaArmor itemChestTesla;
    public static TeslaArmor itemLegsTesla;
    public static TeslaArmor itemBootsTesla;

    public static ElectrifiedIngot ElectrifiedIngot;

    public static ItemStack armorTeslaHelmet;
    public static ItemStack armorTeslaPlate;
    public static ItemStack armorTeslaLegs;
    public static ItemStack armorTeslaBoots;

    //Texture Info
    public static final ItemArmor.ArmorMaterial ARMOR_MATERIAL_Tesla = EnumHelper.addArmorMaterial("RA_Tesla", 10, new int[] { 3, 8, 6, 3 }, 20);
    public static final String[] TEXTURE_Tesla = { "ta:textures/armor/" + "Tesla_Armor.png", "ta:textures/armor/" + "Tesla_Pants.png" };

    public static final String ARMOR = "ta.armor.";
    public static final String ARMOR_TEX_Tesla = "ta:armor/ArmorTesla";
    public static final String TOOL_TEX_Tesla = "ta:tool/Tesla";





}

