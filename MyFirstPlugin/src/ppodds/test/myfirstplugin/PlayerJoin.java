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
	//���o�˨ӫإߤ@�Өƥ�
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event)
	{
		//���ͤ@��Firework����A�ѩ�Firework�L�k�����غc�A�����ϥΫ᭱�o�������@��Ө��o����C
		//�̧ǬO�ƥ�->���o���a->���o�@��->�ͦ�����(��m�ϥΪ��a��e��m�A���O�O�Ϥ�)�C
		Firework f = (Firework) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.FIREWORK);
		//���o�Ϥ���Meta�~���ʸ�ơA�o�̴N�b���oMeta�C
		FireworkMeta fm = f.getFireworkMeta();
		//��Meta�W�[�ĪG�A���e����ˡA�@�ˬO�C��(RED)�A�@�ˬO�˦�(BURST)�C
		fm.addEffect(FireworkEffect.builder().withColor(Color.RED).with(Type.BURST).build());
		//�]�w�Ϥ��V�W�����ʤO�q(3)�A�]����3�ҥH�|���������C
		fm.setPower(3);
		//�NFirework����Meta�]�w���w�ק諸Meta�C
		f.setFireworkMeta(fm);
	}


}
