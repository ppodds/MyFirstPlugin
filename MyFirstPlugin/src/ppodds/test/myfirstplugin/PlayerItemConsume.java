package ppodds.test.myfirstplugin;


import org.bukkit.Material;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;


public class PlayerItemConsume implements Listener
{
	//���o�˨ӫإߤ@�Өƥ�(���a���Ĥ��A�Y�F�赥���|�QĲ�o)
	@EventHandler
	public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent e)
	{
		//�H�U�P�_��˨Ʊ�
		//1. ���~�W�٬��^�_�Ĥ�10%
		//2. ���~�O���~(�Ĥ������ƨ���)
		if (e.getItem().hasItemMeta())
		{
			if (e.getItem().getItemMeta().getDisplayName().equals("�^�_�Ĥ�10%") && e.getItem().getType().equals(Material.POTION))
			{
				//�ŧi�ܼ�
				//���a���̤j�ͩR
				double maxh = e.getPlayer().getMaxHealth();
				//���a����e�ͩR
				double h = e.getPlayer().getHealth();
				//�ݭn�W�[���ͩR
				double addh = maxh * 0.1;
				//�H�U�P�_
				//��e�ͩR+�ݭn�W�[���ͩR > �̤j�ͩR
				if (h+addh > maxh)
				{
					//�]�w�ͩR�Ȩ����
					//(���B�O���F�קK�ͩR�ȷ��X(�ڤ]�����D�|��ˡA�D����)
					e.getPlayer().setHealth(maxh);
				}
				//�_�h
				else
				{
					//�]�w�ͩR����e�ͩR+�ݭn�W�[���ͩR
					e.getPlayer().setHealth(h+addh);
				}
			}
		}
	}


}
