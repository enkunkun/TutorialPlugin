package net.enkun.plugins.tutorial;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TutorialPlugin extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getLogger().info("onEnableメソッドが呼び出されたよ！！");

		//onPlayerJoin を利用するために必要
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisableメソッドが呼び出されたよ！！");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//ゲーム内で /ainau コマンドを使った時
		if(command.getName().equalsIgnoreCase("ainau")){
			if ((sender instanceof Player)) {
				sender.sendMessage("あいなうは神");
			} else {
				sender.sendMessage(ChatColor.RED + "ゲーム内から実行してください！");
				return false;
			}
		}
	    return false;
	}

	@EventHandler
	public void onPlayerJoin(PlayerLoginEvent event) {
		Player player = event.getPlayer();

		//サーバーに初めて入ってきた人か
		if (!player.hasPlayedBefore()) {
			player.sendMessage("○○サーバーへようこそ！初期支援物資をインベントリに配布しますね。");
			ItemStack bread = new ItemStack(Material.BREAD, 16);
			player.getInventory().addItem(bread);
		} else { //そうでないか
			player.sendMessage("○○サーバーへようこそ！");
		}
	}

}
