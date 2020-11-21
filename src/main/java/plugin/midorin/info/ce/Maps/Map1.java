package plugin.midorin.info.ce.Maps;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import plugin.midorin.info.ce.Items;
import plugin.midorin.info.ce.Teams.Archer;
import plugin.midorin.info.ce.Teams.Runner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Map1
{
    public static int vote = 0;
    public static List<UUID> votePlayers = new ArrayList<UUID>();
    public static void firstTp(Player player)
    {
        if (Archer.teamList.contains(player.getUniqueId()))
        {
            player.teleport(new Location(player.getWorld(), 766, 50, -13));
            player.getInventory().addItem(Items.bow1);
            player.getInventory().addItem(Items.arrow);
        }
        else if (Runner.teamList.contains(player.getUniqueId()))
            player.teleport(new Location(player.getWorld(),858, 37, -13));
    }
    public static void secoundTp(Player player)
    {
        if (Runner.teamList.contains(player.getUniqueId()))
        {
            player.teleport(new Location(player.getWorld(),813, 4, 95));
            player.getInventory().addItem(Items.sword1);
            player.getInventory().addItem(Items.pickaxe);
        }
        else if (Archer.teamList.contains(player.getUniqueId()))
            player.teleport(new Location(player.getWorld(),765,50,95));
    }
    public static void resetMap(Player player)
    {
        Location location = player.getLocation();
        WorldEditPlugin worldEditPlugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        File schematic = new File("plugins/WorldEdit/schematics/Map1.schematic");
        EditSession session = worldEditPlugin.getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(location.getWorld()), 10000);
        try
        {
            MCEditSchematicFormat.getFormat(schematic).load(schematic).paste(session, new Vector(758,51,-13), false);
        }
        catch (MaxChangedBlocksException | DataException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
