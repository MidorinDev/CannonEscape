package plugin.midorin.info.ce.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.midorin.info.ce.CannonEscape;
import plugin.midorin.info.ce.Game;
import plugin.midorin.info.ce.Teams.Admin;
import plugin.midorin.info.ce.Teams.Team;
import plugin.midorin.info.ce.util.CustomConfig;
import plugin.midorin.info.ce.util.Messages;

import java.io.File;
import java.io.IOException;

public class CE implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player p = (Player) sender;
        try
        {
            if (args[0].equalsIgnoreCase("start"))
            {
                if (sender.hasPermission("CannonEscape.start") || sender.isOp())
                {
                    CustomConfig.reload();
                    CustomConfig.dataFile = new File(Bukkit.getPluginManager().getPlugin("ServerGate").getDataFolder(), "config.yml");
                    CustomConfig.data.set("Nickname", false);
                    try { CustomConfig.data.save(CustomConfig.dataFile); }
                    catch (IOException e) { e.printStackTrace(); }
                    for (Player ap : Bukkit.getOnlinePlayers())
                        if (!Admin.teamList.contains(ap.getUniqueId()))
                        {
                            ap.setDisplayName(ap.getName());
                            ap.setPlayerListName(ap.getName());
                        }
                    Game.start();
                }
                else
                    Messages.sendPermissionError(sender);
            }
            else if (args[0].equalsIgnoreCase("reset"))
            {
                if (sender.hasPermission("CannonEscape.reset") || sender.isOp())
                    Game.reset(p);
                else
                    Messages.sendPermissionError(sender);
            }
            else if (args[0].equalsIgnoreCase("random"))
            {
                if (sender.hasPermission("CannonEscape.random") || sender.isOp())
                {
                    for (Player ap : Bukkit.getOnlinePlayers())
                        Team.random(ap);
                    Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.GOLD + "チームの振り分けが完了しました。");
                }
                else
                    Messages.sendPermissionError(sender);
            }
        }
        catch (Exception ex) { sender.sendMessage("Error"); }
        return true;
    }
}
