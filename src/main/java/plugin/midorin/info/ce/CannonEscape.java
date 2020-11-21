package plugin.midorin.info.ce;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import plugin.midorin.info.ce.commands.CE;
import plugin.midorin.info.ce.commands.Join;
import plugin.midorin.info.ce.commands.Leave;
import plugin.midorin.info.ce.commands.Vote;

public final class CannonEscape extends JavaPlugin
{
    public static Inventory voteGui = Bukkit.createInventory(null, 9, ChatColor.DARK_AQUA + "マップ投票");
    public static JavaPlugin plugin;

    @Override
    public void onEnable()
    {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        getCommand("cannonescape").setExecutor(new CE());
        getCommand("join").setExecutor(new Join());
        getCommand("leave").setExecutor(new Leave());
        getCommand("vote").setExecutor(new Vote());

        Scoreboards.setup();
        Items.setup();
        Vote.setFrame(Vote.voteGUI);
        Vote.voteGUI.setItem(20, Items.wood1);
        Vote.voteGUI.setItem(22, Items.sand1);
        Vote.voteGUI.setItem(24, Items.nether1);

        super.onEnable();
    }

    @Override
    public void onDisable()
    {
        Scoreboards.reset();
        super.onDisable();
    }

    public static JavaPlugin getPlugin()
    {
        return plugin;
    }
}
