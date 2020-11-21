package plugin.midorin.info.ce.Teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import plugin.midorin.info.ce.Scoreboards;
import plugin.midorin.info.ce.util.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Runner
{
    public static final String TEAM = "runner";
    public static Team team;
    public static List<UUID> teamList = new ArrayList<UUID>();
    public static void setup()
    {
        team = Scoreboards.board.getTeam(TEAM);
        if (team == null)
        {
            team = Scoreboards.board.registerNewTeam(TEAM);
            team.setPrefix(ChatColor.AQUA.toString());
            team.setSuffix(ChatColor.RESET.toString());
            team.setDisplayName(ChatColor.AQUA + "Runner");
            team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.FOR_OTHER_TEAMS);
            team.setAllowFriendlyFire(false);
        }
    }
    public static void addPlayer(Player player, boolean message)
    {
        if (teamList.contains(player.getUniqueId())) return;
        else if (Archer.teamList.contains(player.getUniqueId()))
            Archer.removePlayer(player, false);
        else if (Admin.teamList.contains(player.getUniqueId()))
            Admin.removePlayer(player, false);
        team.addEntry(player.getName());
        teamList.add(player.getUniqueId());
        if (message)
            Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.GRAY + player.getName() + "が" + ChatColor.AQUA + "Runnerチーム" + ChatColor.GRAY + "に参加しました。");
    }
    public static void removePlayer(Player player, boolean message)
    {
        if (Archer.teamList.contains(player.getUniqueId()) || Admin.teamList.contains(player.getUniqueId())) return;
        team.removeEntry(player.getName());
        teamList.remove(player.getUniqueId());
        if (message)
            Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.GRAY + player.getName() + "が" + ChatColor.AQUA + "Runnerチーム" + ChatColor.GRAY + "から離脱しました。");
    }
}
