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
import plugin.midorin.info.ce.Teams.Team;
import plugin.midorin.info.ce.util.Messages;

public class Join implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player p = (Player) sender;
        try
        {
            if (sender.hasPermission("CannonEscape.join"))
            {
                if (args[0].equalsIgnoreCase("runner"))
                {
                    if (args.length == 1)
                    {
                        if (Bukkit.getPlayer(args[1]) != null)
                            Runner.addPlayer(Bukkit.getPlayer(args[1]), true);
                        else
                            sender.sendMessage(Messages.PREFIX + ChatColor.RED + "このユーザーはオフラインです。");
                        return true;
                    }
                    else
                        Runner.addPlayer(p, true);
                }
                else if (args[0].equalsIgnoreCase("archer"))
                {
                    if (args.length >= 1)
                    {
                        if (Bukkit.getPlayer(args[1]) != null)
                            Archer.addPlayer(Bukkit.getPlayer(args[1]), true);
                        else
                            sender.sendMessage(Messages.PREFIX + ChatColor.RED + "このユーザーはオフラインです。");
                        return true;
                    }
                    else
                        Archer.addPlayer(p, true);
                }
                else if (args[0].equalsIgnoreCase("admin"))
                {
                    if (args.length >= 1)
                    {
                        if (Bukkit.getPlayer(args[1]) != null)
                            Admin.addPlayer(Bukkit.getPlayer(args[1]), true);
                        else
                            sender.sendMessage(Messages.PREFIX + ChatColor.RED + "このユーザーはオフラインです。");
                        return true;
                    }
                    else
                        Admin.addPlayer(p, true);
                }
            }
            else
                Messages.sendPermissionError(sender);
        }
        catch (Exception ex)
        {
            if (sender.hasPermission("CannonEscape.join"))
            {
                Admin.removePlayer(p, false);
                Team.random(p);
            }
            else
                Messages.sendPermissionError(sender);
        }
        return true;
    }
}
