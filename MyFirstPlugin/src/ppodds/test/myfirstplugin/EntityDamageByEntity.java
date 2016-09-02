package ppodds.test.myfirstplugin;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener
{
	//先弄一個事件出來
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e)
	{
		//比對攻擊者是不是玩家
		if (e.getDamager() instanceof Player)
		{
			Player p = (Player) e.getDamager();
			//比對兩樣東西
			//1. 玩家右手上的物品的顯示名稱叫做巨劍
			//2. 玩家右手上的物品的材質是屬於鐵劍
			if (p.getInventory().getItemInMainHand().hasItemMeta())
			{
				if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("巨劍") && p.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD))
				{
					// 用來判讀事件是否有觸發用
					p.sendMessage("你使用巨劍發動了攻擊");
					// 重新設定傷害為五顆心
					e.setDamage(10);
				}
			}
		}
		else
		if (e.getDamager().getType().equals(EntityType.ZOMBIE))
		{
			Zombie zm = (Zombie)e.getDamager();
			if (zm.getEquipment().getItemInMainHand().hasItemMeta())
			{
				if (zm.getEquipment().getItemInMainHand().getItemMeta().getDisplayName().equals("巨劍") && zm.getEquipment().getItemInMainHand().getType().equals(Material.IRON_SWORD))
				{
					// 用來判讀事件是否有觸發用
					e.getEntity().sendMessage("你被殭屍使用巨劍攻擊了");
					// 重新設定傷害為五顆心
					e.setDamage(10);
				}
			}
		}
	}
}
