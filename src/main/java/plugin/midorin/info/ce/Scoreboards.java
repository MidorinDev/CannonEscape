package plugin.midorin.info.ce;

import org.bukkit.*;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import plugin.midorin.info.ce.Teams.Admin;
import plugin.midorin.info.ce.Teams.Archer;
import plugin.midorin.info.ce.Teams.Runner;

public class Scoreboards
{
    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static Scoreboard board = manager.getMainScoreboard();
    public static Objective objective;
    static Score air0;
    static Score time_title;
    public static Score time;
    static Score air1;
    public static Score runner_players;
    public static Score archer_players;
    static Score air2;
    public static void setup()
    {
        manager = Bukkit.getScoreboardManager();
        board = manager.getMainScoreboard();

        objective = board.registerNewObjective("cannonescape", "dummy");
        objective.setDisplayName(ChatColor.WHITE + "     CannonEscape     ");
        air0 = objective.getScore(" ");
        air0.setScore(0);
        time_title = objective.getScore(ChatColor.GOLD + "  ゲーム時間:");
        time_title.setScore(-1);
        time = objective.getScore(ChatColor.WHITE + "        00:00");
        time.setScore(-2);
        air1 = objective.getScore("  ");
        air1.setScore(-3);
        runner_players = objective.getScore(ChatColor.AQUA + "  Runner:  " + ChatColor.WHITE + "0人");
        runner_players.setScore(-4);
        archer_players = objective.getScore(ChatColor.RED + "  Archer:  " + ChatColor.WHITE + "0人");
        archer_players.setScore(-5);
        air2 = objective.getScore("   ");
        air2.setScore(-6);
        Runner.setup();
        Archer.setup();
        Admin.setup();
    }
    public static void reset()
    {
        objective.getScoreboard().resetScores(air0.getEntry());
        objective.getScoreboard().resetScores(time_title.getEntry());
        objective.getScoreboard().resetScores(time.getEntry());
        objective.getScoreboard().resetScores(air1.getEntry());
        objective.getScoreboard().resetScores(runner_players.getEntry());
        objective.getScoreboard().resetScores(archer_players.getEntry());
        objective.getScoreboard().resetScores(air2.getEntry());
        objective.unregister();
        Runner.team.unregister();
        Archer.team.unregister();
        Admin.team.unregister();
    }
    public static void setTime(int minutes, int second)
    {
        objective.getScoreboard().resetScores(time.getEntry());
        if (minutes < 10)
        {
            if (second < 10)
            {
                time = objective.getScore(ChatColor.WHITE + "        0" + minutes + ":0" + second);
                time.setScore(-2);
            }
            else
            {
                time = objective.getScore(ChatColor.WHITE + "        0" + minutes + ":" + second);
                time.setScore(-2);
            }
        }
    }
}
