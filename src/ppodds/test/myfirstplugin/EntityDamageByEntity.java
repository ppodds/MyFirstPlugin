package ppodds.test.myfirstplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntity implements Listener
{
	//���ˤ@�Өƥ�X��
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e)
	{
		//�������̬O���O���a
		if (e.getDamager() instanceof Player)
		{
			Player p = (Player) e.getDamager();
			//����˪F��
			//1. ���a�k��W�����~����ܦW�٥s�����C
			//2. ���a�k��W�����~������O�ݩ��K�C
			if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("���C") && p.getInventory().getItemInMainHand().getType().equals(Material.IRON_SWORD))
			{
				//�ΨӧPŪ�ƥ�O�_��Ĳ�o��
				p.sendMessage("�A�ϥΥ��C�o�ʤF����");
				//���s�]�w�ˮ`��������
				e.setDamage(10);
			}
		}
	}
}
