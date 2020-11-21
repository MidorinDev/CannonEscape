package plugin.midorin.info.ce.Teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import plugin.midorin.info.ce.Scoreboards;
import plugin.midorin.info.ce.util.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Admin
{
    public static final String TEAM = "admin";
    public static org.bukkit.scoreboard.Team team;
    public static List<UUID> teamList = new ArrayList<UUID>();
    public static void setup()
    {
        team = Scoreboards.board.getTeam(TEAM);
        if (team == null)
        {
            team = Scoreboards.board.registerNewTeam(TEAM);
            team.setPrefix(ChatColor.GOLD + "[Admin] " + ChatColor.WHITE);
            team.setSuffix(ChatColor.RESET.toString());
            team.setDisplayName(ChatColor.GOLD + "Admin");
            team.setOption(org.bukkit.scoreboard.Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OTHER_TEAMS);
            team.setAllowFriendlyFire(false);
        }
    }
    public static void addPlayer(Player player, boolean message)
    {
        if (Runner.teamList.contains(player.getUniqueId()))
            Runner.removePlayer(player, false);
        else if (Archer.teamList.contains(player.getUniqueId()))
            Archer.removePlayer(player, false);
        else if (Admin.teamList.contains(player.getUniqueId())) return;
        team.addEntry(player.getName());
        teamList.add(player.getUniqueId());
        player.setGameMode(GameMode.CREATIVE);
        if (message)
            Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.GRAY + player.getName() + "が" + ChatColor.GOLD + "Adminチーム" + ChatColor.GRAY + "に参加しました。");
    }
    public static void removePlayer(Player player, boolean message)
    {
        if (Runner.teamList.contains(player.getUniqueId()) || Archer.teamList.contains(player.getUniqueId())) return;
        team.removeEntry(player.getName());
        teamList.remove(player.getUniqueId());
        player.setGameMode(GameMode.SURVIVAL);
        if (message)
            Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.GRAY + player.getName() + "が" + ChatColor.GOLD + "Adminチーム" + ChatColor.GRAY + "から離脱しました。");
    }
}
