package com.chai.warpBooks;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import com.chai.shortUtils.ShortUtils;

public class RenameCommand implements CommandExecutor
{
	private final Main plugin = JavaPlugin.getPlugin(Main.class);

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		// Sanity check
		if (!(sender instanceof Player) || args.length == 0) { return false; }

		// Check if holding banner
		Player ply = (Player) sender;
		ItemStack item = ply.getInventory().getItemInMainHand();
		ItemMeta meta = item.getItemMeta();

		// Check if warp banner
		if (ShortUtils.hasKey(meta, plugin.warpBannerKey))
		{
			// Modify
			String name = String.join(" ", args);
			meta.getPersistentDataContainer().set(plugin.nameKey, PersistentDataType.STRING, name);

			List<String> list = meta.getLore();
			list.set(0, "�f" + name);
			meta.setLore(list);

			item.setItemMeta(meta);
			ply.sendMessage("�6Warp renamed to �c" + name);
		}
		return true;
	}
}