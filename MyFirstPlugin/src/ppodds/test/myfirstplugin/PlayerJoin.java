package ppodds.test.myfirstplugin;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import ppodds.test.myfirstplugin.*;

public class PlayerJoin implements Listener
{
	private final MyFirstPlugin mfp = (MyFirstPlugin)Bukkit.getPluginManager().getPlugin("MyFirstPlugin");
	
	
	//像這樣來建立一個事件
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event)
	{
		//產生一個Firework物件，由於Firework無法直接建構，必須使用後面這長長的一行來取得物件。
		//依序是事件->取得玩家->取得世界->生成實體(位置使用玩家當前位置，類別是煙火)。
		Firework f = (Firework) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.FIREWORK);
		//取得煙火的Meta才能更動資料，這裡就在取得Meta。
		FireworkMeta fm = f.getFireworkMeta();
		//對Meta增加效果，內容有兩樣，一樣是顏色(RED)，一樣是樣式(BURST)。
		fm.addEffect(FireworkEffect.builder().withColor(Color.RED).with(Type.BURST).build());
		//設定煙火向上飛的動力量(3)，因為有3所以會飛滿高的。
		fm.setPower(3);
		//將Firework物件的Meta設定成已修改的Meta。
		f.setFireworkMeta(fm);
	
		//宣告玩家資料檔案物件
		//由於玩家名稱會變動，改用UUID較保險
		File playerData = new File(mfp.getDataFolder() + File.separator + "PlayerData" + File.separator + event.getPlayer().getUniqueId().toString() + ".yml"); 
		//檢查玩家資料檔案是否存在
		if (!playerData.exists())
		{
			try
			{
				//不存在所以建立
				playerData.createNewFile();
				//準備寫入資料到yml檔內，所以先把他轉成YamlConfiguration檔
				YamlConfiguration y = YamlConfiguration.loadConfiguration(playerData);
				//轉換後可利用YamlConfiguration檔操作，操作方法和Config一樣
				//寫入一行，mana: 100 
				y.set("mana", 100);
				//存檔
				y.save(playerData);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			//準備讀取yml檔的資料
			YamlConfiguration y = YamlConfiguration.loadConfiguration(playerData);
			//取得資料
			Integer mana = Integer.valueOf(y.getInt("mana"));
			//玩家登入時設定魔力量
			MyFirstPlugin.mana.put(event.getPlayer().getName(), mana);
		}
	}
}
