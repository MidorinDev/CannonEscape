package plugin.midorin.info.ce;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;

import plugin.midorin.info.ce.Maps.Map1;
import plugin.midorin.info.ce.Maps.Map2;
import plugin.midorin.info.ce.Maps.Map3;
import plugin.midorin.info.ce.Teams.Admin;
import plugin.midorin.info.ce.Teams.Archer;
import plugin.midorin.info.ce.Teams.Runner;
import plugin.midorin.info.ce.commands.Vote;
import plugin.midorin.info.ce.util.Messages;

public class Game
{

    static int before = 5;
    public static int countdown = 600;
    static int m = 10;
    public static BukkitRunnable timer;
    public static void help()
    {
    }
    public static void start()
    {
        BukkitRunnable task1 = new BukkitRunnable()
        {
            public void run()
            {
                if (before == 0)
                {
                    Scoreboards.objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    for (Player ap : Bukkit.getOnlinePlayers())
                    {
                        Location loc = ap.getPlayer().getLocation();
                        loc.getWorld().playSound(loc, Sound.ENTITY_ENDERDRAGON_AMBIENT, 50, 1);
                        ap.sendTitle(ChatColor.GOLD + "ゲーム開始！", ChatColor.GRAY + "CannonEscape", 10, 10, 10);
                        ap.setGameMode(GameMode.SURVIVAL);
                        if (Vote.mapNum == 1)
                            Map1.firstTp(ap);
                        else if (Vote.mapNum == 2)
                            Map2.firstTp(ap);
                        else if (Vote.mapNum == 3)
                            Map3.firstTp(ap);
                    }
                    m = m - 1;

                    this.cancel();

                    timer = new BukkitRunnable()
                    {
                        public void run()
                        {

                            if (countdown == 540) m=m-1;
                            else if (countdown == 480) m=m-1;
                            else if (countdown == 420) m=m-1;
                            else if (countdown == 360) m=m-1;
                            else if (countdown == 300) m=m-1;
                            else if (countdown == 240) m=m-1;
                            else if (countdown == 180) m=m-1;
                            else if (countdown == 120) m=m-1;
                            else if (countdown == 60) m=m-1;
                            Scoreboards.setTime(m, countdown - m * 60);
                            Scoreboards.objective.getScoreboard().resetScores(Scoreboards.runner_players.getEntry());
                            Scoreboards.runner_players = Scoreboards.objective.getScore(ChatColor.AQUA + "  Runner:  " + ChatColor.WHITE + Runner.teamList.size() + "人");
                            Scoreboards.runner_players.setScore(-4);
                            Scoreboards.objective.getScoreboard().resetScores(Scoreboards.archer_players.getEntry());
                            Scoreboards.archer_players = Scoreboards.objective.getScore(ChatColor.RED + "  Archer:  " + ChatColor.WHITE + Archer.teamList.size() + "人");
                            Scoreboards.archer_players.setScore(-5);

                            if (countdown == 240)
                            {
                                if (Vote.mapNum == 1)
                                    for (Player ap : Bukkit.getOnlinePlayers())
                                        Map1.secoundTp(ap);
                            }
                            else if (countdown == 0)
                            {
                                if (Vote.mapNum == 1)
                                {
                                    finish();
                                    Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.RED + "コアが破壊されなかったためアーチャー側の勝利となります！");
                                }
                                else if (Vote.mapNum == 2 || Vote.mapNum == 3)
                                {
                                    finish();
                                    Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.AQUA + "ランナーが残っていたためランナー側の勝利となります！");
                                }
                            }
                            else if (Runner.teamList.size() == 0)
                            {
                                finish();
                                Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.RED + "アーチャー陣がランナー陣を全滅させたためアーチャー側の勝利となります！！");
                            }
                            countdown--;
                        }
                    };
                    timer.runTaskTimer(CannonEscape.getPlugin(), 0L, 20L);
                }
                else
                {
                    for (Player ap : Bukkit.getOnlinePlayers())
                    {
                        ap.sendTitle(ChatColor.GREEN + " " + before + " ", ChatColor.GRAY + "開始まで・・・", 10, 10, 10);
                        ap.getLocation().getWorld().playSound(ap.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                    }
                }
                before--;
            }
        };
        task1.runTaskTimer(CannonEscape.getPlugin(), 0L, 20L);
    }
    public static void reset(Player player)
    {
        for (Player ap : Bukkit.getOnlinePlayers())
            if (Runner.teamList.contains(ap.getUniqueId()) || Archer.teamList.contains(ap.getUniqueId()))
                ap.getInventory().clear();
        Scoreboards.reset();
        before = 5;
        countdown = 600;
        m = 10;
        if (Vote.mapNum == 1) Map1.resetMap(player);
        if (Vote.mapNum == 2) Map2.resetMap(player);
        if (Vote.mapNum == 3) Map3.resetMap(player);
        Vote.mapNum = 0;
        Vote.voteStart = 0;
        Vote.voteGUI.clear();
        Map1.vote = 0;
        if (Map1.votePlayers != null) Map1.votePlayers.clear();
        Map2.vote = 0;
        if (Map1.votePlayers != null) Map2.votePlayers.clear();
        Map3.vote = 0;
        if (Map1.votePlayers != null) Map3.votePlayers.clear();
        if (timer != null && !timer.isCancelled()) timer.cancel();
        if (Runner.teamList != null) Runner.teamList.clear();
        if (Archer.teamList != null) Archer.teamList.clear();
        if (Admin.teamList != null) Admin.teamList.clear();
        Scoreboards.setup();
        Items.setup();
        Vote.setFrame(Vote.voteGUI);
        Vote.voteGUI.setItem(20, Items.wood1);
        Vote.voteGUI.setItem(22, Items.sand1);
        Vote.voteGUI.setItem(24, Items.nether1);
        Bukkit.broadcastMessage(Messages.PREFIX + ChatColor.GREEN + "ゲームのリセットが完了しました。");
    }
    private static void finish()
    {
        for (Player ap : Bukkit.getOnlinePlayers())
        {
            ap.sendTitle(ChatColor.GOLD + "ゲーム終了", ChatColor.GRAY + "CannonEscape", 10, 10 ,10);
            ap.getLocation().getWorld().playSound(ap.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 50, 1);
            if (!Admin.teamList.contains(ap.getUniqueId()))
                ap.setGameMode(GameMode.SPECTATOR);
        }
        timer.cancel();
    }
}
