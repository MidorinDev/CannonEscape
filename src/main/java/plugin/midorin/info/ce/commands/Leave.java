package plugin.midorin.info.ce.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugin.midorin.info.ce.Teams.Admin;
import plugin.midorin.info.ce.Teams.Archer;
import plugin.midorin.info.ce.Teams.Runner;
import plugin.midorin.info.ce.util.Messages;

public class Leave implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player p = (Player) sender;
        try
        {
            if (sender.hasPermission("CannonEscape.leave"))
            {
                if (Bukkit.getPlayer(args[0]) != null)
                {
                    if (Runner.teamList.contains(Bukkit.getPlayer(args[0]).getUniqueId()))
                        Runner.removePlayer(Bukkit.getPlayer(args[0]), true);
                    else if (Archer.teamList.contains(Bukkit.getPlayer(args[0]).getUniqueId()))
                        Archer.removePlayer(Bukkit.getPlayer(args[0]), true);
                    Admin.addPlayer(Bukkit.getPlayer(args[0]), true);
                }
                else
                    sender.sendMessage(Messages.PREFIX + ChatColor.RED + "このユーザーはオフラインです。");
                return true;
            }
            else
                Messages.sendPermissionError(sender);
        }
        catch (Exception ex)
        {
            if (sender.hasPermission("CannonEscape.leave"))
            {
                if (Runner.teamList.contains(p.getUniqueId()))
                    Runner.removePlayer(p, true);
                else if (Archer.teamList.contains(p.getUniqueId()))
                    Archer.removePlayer(p, true);
                Admin.addPlayer(p, true);
            }
            else
                Messages.sendPermissionError(sender);
        }
        return true;
    }
}
