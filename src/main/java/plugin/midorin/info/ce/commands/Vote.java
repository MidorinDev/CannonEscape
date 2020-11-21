package plugin.midorin.info.ce.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import plugin.midorin.info.ce.Items;
import plugin.midorin.info.ce.Maps.Map1;
import plugin.midorin.info.ce.Maps.Map2;
import plugin.midorin.info.ce.Maps.Map3;
import plugin.midorin.info.ce.util.Messages;

public class Vote implements CommandExecutor
{
    public static Inventory voteGUI = Bukkit.createInventory(null, 45, ChatColor.DARK_AQUA + "マップ投票");
    public static int mapNum = 0;
    public static int voteStart = 0;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player p = (Player) sender;
        try
        {
            if (args[0].equalsIgnoreCase("start"))
            {
                if (sender.hasPermission("CannonEscape.vote.start") || sender.isOp())
                {
                    voteStart = 1;
                    Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.GREEN + "マップ投票が開始されました。");
                    Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.AQUA + "/vote で投票画面が開かれます。");
                }
                else Messages.sendPermissionError(sender);
            }
            else if (args[0].equalsIgnoreCase("finish"))
            {
                if (sender.hasPermission("CannonEscape.vote.finish") || sender.isOp())
                {
                    Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.RED + "マップ投票が終了しました。");
                    voteStart = 0;
                    if (Map1.vote < Map2.vote)
                    {
                        if (Map2.vote < Map3.vote)
                        {
                            mapNum = 3;
                            Bukkit.broadcastMessage(ChatColor.AQUA + "マップ投票の結果使用するマップが " + ChatColor.WHITE + "LavaPool" + ChatColor.AQUA + " に設定されました。");
                        }
                        else if (Map2.vote > Map3.vote)
                        {
                            mapNum = 2;
                            Bukkit.broadcastMessage(ChatColor.AQUA + "マップ投票の結果使用するマップが " + ChatColor.WHITE + "TheSashimi" + ChatColor.AQUA + " に設定されました。");
                        }
                    }
                    else if (Map1.vote > Map2.vote)
                    {
                        if (Map1.vote > Map3.vote)
                        {
                            mapNum = 1;
                            Bukkit.broadcastMessage(ChatColor.AQUA + "マップ投票の結果使用するマップが " + ChatColor.WHITE + "default" + ChatColor.AQUA + " に設定されました。");
                        }
                    }
                }
                else Messages.sendPermissionError(sender);
            }
        }
        catch (Exception ex)
        {
            if (voteStart == 0)
                sender.sendMessage(Messages.PREFIX + ChatColor.RED + "まだ投票は開始されていません。");
            else
                p.openInventory(voteGUI);
        }
        return true;
    }
    public static void setFrame(Inventory inventory)
    {
        inventory.setItem(0, Items.frame1);
        inventory.setItem(1, Items.frame1);
        inventory.setItem(2, Items.frame1);
        inventory.setItem(3, Items.frame1);
        inventory.setItem(4, Items.frame1);
        inventory.setItem(5, Items.frame1);
        inventory.setItem(6, Items.frame1);
        inventory.setItem(7, Items.frame1);
        inventory.setItem(8, Items.frame1);
        inventory.setItem(36, Items.frame1);
        inventory.setItem(37, Items.frame1);
        inventory.setItem(38, Items.frame1);
        inventory.setItem(39, Items.frame1);
        inventory.setItem(40, Items.frame1);
        inventory.setItem(41, Items.frame1);
        inventory.setItem(42, Items.frame1);
        inventory.setItem(43, Items.frame1);
        inventory.setItem(44, Items.frame1);
    }
}
