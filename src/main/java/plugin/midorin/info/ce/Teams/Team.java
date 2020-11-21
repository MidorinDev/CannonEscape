package plugin.midorin.info.ce.Teams;

import org.bukkit.entity.Player;

import java.util.Random;

public class Team
{
    public static Random random = new Random();
    public static void random(Player player)
    {
        if (!(Admin.teamList.contains(player.getUniqueId())))
        {
            if (Archer.team.getPlayers().size() == Runner.team.getPlayers().size())
            {
                if (random.nextBoolean())
                    Runner.addPlayer(player, false);
                else
                    Archer.addPlayer(player, false);
            }
            else
            {
                if (Runner.team.getPlayers().size() < Archer.team.getPlayers().size())
                    Runner.addPlayer(player, false);
                else
                    Archer.addPlayer(player, false);
            }
        }
    }
}
