package plugin.midorin.info.ce;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class Items
{
    public static void setup()
    {
        getFrame();
        getGoldSword();
        getGoldPickaxe();
        getBow();
        getArrow();
        getGuiWood();
        getGuiSand();
        getGuiNether();
    }
    public static final ItemStack frame1 = new ItemStack(Material.STAINED_GLASS_PANE);
    public static final ItemMeta itemMeta0 = frame1.getItemMeta();
    static void getFrame()
    {
        itemMeta0.setDisplayName(" ");
        frame1.setItemMeta(itemMeta0);
    }
    public static final ItemStack sword1 = new ItemStack(Material.GOLD_SWORD);
    public static final ItemMeta itemMeta1 = sword1.getItemMeta();
    static void getGoldSword()
    {
        itemMeta1.setDisplayName(ChatColor.AQUA + "金のソード");
        itemMeta1.setUnbreakable(true);
        sword1.setItemMeta(itemMeta1);
    }
    public static final ItemStack pickaxe = new ItemStack(Material.GOLD_PICKAXE);
    public static final ItemMeta itemMeta2 = pickaxe.getItemMeta();
    static void getGoldPickaxe()
    {
        itemMeta2.setDisplayName(ChatColor.AQUA + "金に輝いたピッケル");
        itemMeta2.setLore(Arrays.asList(ChatColor.GOLD + "➡︎ アーチャーのダイヤブロックを破壊することができます"));
        itemMeta2.setUnbreakable(true);
        pickaxe.setItemMeta(itemMeta2);
    }


    public static final ItemStack bow1 = new ItemStack(Material.BOW);
    public static final ItemMeta itemMeta3 = bow1.getItemMeta();
    static void getBow()
    {
        itemMeta3.setDisplayName(ChatColor.RED + "撃滅の弓");
        itemMeta3.setLore(Arrays.asList(ChatColor.GOLD + "➡︎ プレイヤーに当たった場合、ノックバックを受ける", ChatColor.GOLD + "➡︎ 砂岩、オークの木に当たった場合、当たったブロックは消える"));
        itemMeta3.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        itemMeta3.addEnchant(Enchantment.ARROW_KNOCKBACK, 10, true);
        itemMeta3.setUnbreakable(true);
        itemMeta3.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bow1.setItemMeta(itemMeta3);
    }
    public static final ItemStack arrow = new ItemStack(Material.ARROW);
    public static final ItemMeta itemMeta4 = arrow.getItemMeta();
    static void getArrow()
    {
        itemMeta4.setDisplayName(ChatColor.RED + "無限の矢");
        itemMeta4.setUnbreakable(true);
        arrow.setItemMeta(itemMeta4);
    }


    public static final ItemStack wood1 = new ItemStack(Material.WOOD);
    public static final ItemMeta itemMeta5 = wood1.getItemMeta();
    public static void getGuiWood()
    {
        itemMeta5.setDisplayName(ChatColor.WHITE + "Default");
        itemMeta5.setLore(Collections.singletonList(ChatColor.GOLD + "マップID: " + ChatColor.YELLOW + "Map1"));
        wood1.setItemMeta(itemMeta5);
    }
    public static final ItemStack sand1 = new ItemStack(Material.SAND);
    public static final ItemMeta itemMeta6 = sand1.getItemMeta();
    public static void getGuiSand()
    {
        itemMeta6.setDisplayName(ChatColor.WHITE + "TheSashimi");
        itemMeta6.setLore(Collections.singletonList(ChatColor.GOLD + "マップID: " + ChatColor.YELLOW + "Map2"));
        sand1.setItemMeta(itemMeta6);
    }
    public static final ItemStack nether1 = new ItemStack(Material.NETHER_BRICK);
    public static final ItemMeta itemMeta7 = sand1.getItemMeta();
    public static void getGuiNether()
    {
        itemMeta7.setDisplayName(ChatColor.WHITE + "LavaPool");
        itemMeta7.setLore(Collections.singletonList(ChatColor.GOLD + "マップID: " + ChatColor.YELLOW + "Map3"));
        nether1.setItemMeta(itemMeta7);
    }
}