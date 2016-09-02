package ppodds.test.myfirstplugin;


import org.bukkit.Material;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;


public class PlayerItemConsume implements Listener
{
	//像這樣來建立一個事件(玩家喝藥水，吃東西等等會被觸發)
	@EventHandler
	public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent e)
	{
		//以下判斷兩樣事情
		//1. 物品名稱為回復藥水10%
		//2. 物品是水瓶(藥水的材料那種)
		if (e.getItem().hasItemMeta())
		{
			if (e.getItem().getItemMeta().getDisplayName().equals("回復藥水10%") && e.getItem().getType().equals(Material.POTION))
			{
				//宣告變數
				//玩家的最大生命
				double maxh = e.getPlayer().getMaxHealth();
				//玩家的當前生命
				double h = e.getPlayer().getHealth();
				//需要增加的生命
				double addh = maxh * 0.1;
				//以下判斷
				//當前生命+需要增加的生命 > 最大生命
				if (h+addh > maxh)
				{
					//設定生命值到全滿
					//(此處是為了避免生命值溢出(我也不知道會怎樣，求測試)
					e.getPlayer().setHealth(maxh);
				}
				//否則
				else
				{
					//設定生命為當前生命+需要增加的生命
					e.getPlayer().setHealth(h+addh);
				}
			}
		}
	}


}
