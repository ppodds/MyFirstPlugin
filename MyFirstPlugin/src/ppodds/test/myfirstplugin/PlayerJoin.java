package ppodds.test.myfirstplugin;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;


public class PlayerJoin implements Listener
{
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
	}


}
