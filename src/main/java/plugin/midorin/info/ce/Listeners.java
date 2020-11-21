package plugin.midorin.info.ce;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.util.BlockIterator;

import plugin.midorin.info.ce.Maps.Map1;
import plugin.midorin.info.ce.Maps.Map2;
import plugin.midorin.info.ce.Maps.Map3;
import plugin.midorin.info.ce.Teams.Admin;
import plugin.midorin.info.ce.Teams.Archer;
import plugin.midorin.info.ce.Teams.Runner;
import plugin.midorin.info.ce.commands.Vote;
import plugin.midorin.info.ce.util.Messages;

public class Listeners implements Listener
{
    @EventHandler
    public void onHitBlock(ProjectileHitEvent e)
    {
        Projectile projectile = e.getEntity();
        Arrow arrow = (Arrow) projectile;
        // Player p = (Player) arrow.getShooter();
        World world = arrow.getWorld();
        BlockIterator bi = new BlockIterator(world, arrow.getLocation().toVector(), arrow.getVelocity().normalize(), 0, 4);
        Block hit = null;
        Block hitBlock = e.getHitBlock();
        MaterialData SANDSTONE2 = new MaterialData(Material.SANDSTONE, (byte) 2);
        if (projectile instanceof Arrow)
        {
            while (bi.hasNext())
            {
                hit = bi.next();
                if(hit.getTypeId()!=0)
                    break;
            }
            if (hit.getType() == Material.SANDSTONE)
            {
                hitBlock.setType(Material.AIR);
                arrow.remove();
            }
            else if (hit.getType() == SANDSTONE2.getItemType())
            {
                hitBlock.setType(Material.AIR);
                arrow.remove();
            }
            else if (hit.getType() == Material.WOOD)
            {
                hitBlock.setType(Material.AIR);
                arrow.remove();
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        if (Runner.teamList.contains(p.getUniqueId()))
            Runner.removePlayer(p, true);
        else if (Archer.teamList.contains(p.getUniqueId()))
            Archer.removePlayer(p, true);
        else if (Admin.teamList.contains(p.getUniqueId()))
            Admin.removePlayer(p, true);

    }
    @EventHandler
    public void onDeath(PlayerDeathEvent e)
    {
        Player deather = e.getEntity().getPlayer();
        if (Runner.teamList.contains(deather.getUniqueId()))
        {
            deather.setGameMode(GameMode.SPECTATOR);
            Runner.removePlayer(deather, false);
            e.setDeathMessage(Messages.PREFIX + ChatColor.AQUA + deather.getDisplayName() + ChatColor.GRAY + " is dead.");
        }
        else if (Archer.teamList.contains(deather.getUniqueId()))
            e.setDeathMessage(Messages.PREFIX + ChatColor.RED + deather.getDisplayName() + ChatColor.GRAY + " is dead.");
        else
            e.setDeathMessage(Messages.PREFIX + ChatColor.GRAY + deather.getDisplayName() + " is dead.");
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e)
    {
        Player p = e.getPlayer();
        Player killer = e.getPlayer().getKiller();
        if (killer != null)
            e.setRespawnLocation(new Location(p.getWorld(), killer.getLocation().getX(), killer.getLocation().getY(), killer.getLocation().getZ()));
    }
    @EventHandler
    public void onDropItem(PlayerDropItemEvent e)
    {
        Player p = e.getPlayer();
        String i = e.getItemDrop().getName();
        if ((i.equals(Items.bow1.getItemMeta().getDisplayName()) || i.equals(Items.arrow.getItemMeta().getDisplayName())) || (Runner.teamList.contains(p.getUniqueId()) || Archer.teamList.contains(p.getUniqueId())))
            e.setCancelled(true);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        Location loc = e.getWhoClicked().getLocation();
        Player clicker = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        if (e.getInventory().getName().equalsIgnoreCase(Vote.voteGUI.getName()))
        {
            if (item.getType() == Material.WOOD)
            {
                e.setCancelled(true);
                if (Map1.votePlayers.contains(clicker.getUniqueId()))
                {
                    clicker.closeInventory();
                    clicker.sendMessage(Messages.PREFIX + ChatColor.RED + "投票は１回までしかできません。");
                }
                else if (Map2.votePlayers.contains(clicker.getUniqueId()) || Map3.votePlayers.contains(clicker.getUniqueId()))
                {
                    clicker.closeInventory();
                    clicker.sendMessage(Messages.PREFIX + ChatColor.RED + "複数のマップへ投票はできません。");
                }
                else
                {
                    Map1.vote = Map1.vote + 1;
                    clicker.playSound(loc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 50, 1);
                    Map1.votePlayers.add(clicker.getUniqueId());
                    clicker.closeInventory();
                    clicker.sendMessage(Messages.PREFIX + ChatColor.GREEN + "投票が完了しました。");
                }
            }
            else if (item.getType() == Material.SAND)
            {
                e.setCancelled(true);
                if (Map2.votePlayers.contains(clicker.getUniqueId()))
                {
                    clicker.closeInventory();
                    clicker.sendMessage(Messages.PREFIX + ChatColor.RED + "投票は１回までしかできません。");
                }
                else if (Map1.votePlayers.contains(clicker.getUniqueId()) || Map3.votePlayers.contains(clicker.getUniqueId()))
                {
                    clicker.closeInventory();
                    clicker.sendMessage(Messages.PREFIX + ChatColor.RED + "複数のマップへ投票はできません。");
                }
                else
                {
                    Map2.vote = Map2.vote + 1;
                    clicker.playSound(loc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 50, 1);
                    Map2.votePlayers.add(clicker.getUniqueId());
                    clicker.closeInventory();
                    clicker.sendMessage(Messages.PREFIX + ChatColor.GREEN + "投票が完了しました。");
                }
            }
            else if (item.getType() == Material.NETHER_BRICK)
            {
                e.setCancelled(true);
                if (Map3.votePlayers.contains(clicker.getUniqueId()))
                {
                    clicker.closeInventory();
                    clicker.sendMessage(Messages.PREFIX + ChatColor.RED + "投票は１回までしかできません。");
                }
                else if (Map1.votePlayers.contains(clicker.getUniqueId()) || Map2.votePlayers.contains(clicker.getUniqueId()))
                {
                    clicker.closeInventory();
                    clicker.sendMessage(Messages.PREFIX + ChatColor.RED + "複数のマップへ投票はできません。");
                }
                else
                {
                    Map3.vote = Map3.vote + 1;
                    clicker.playSound(loc, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 50, 1);
                    Map3.votePlayers.add(clicker.getUniqueId());
                    clicker.closeInventory();
                    clicker.sendMessage(Messages.PREFIX + ChatColor.GREEN + "投票が完了しました。");
                }
            }
        }
    }

}
