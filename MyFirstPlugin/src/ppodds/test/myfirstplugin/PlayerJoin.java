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
	
		//�ŧi���a����ɮת���
		//�ѩ󪱮a�W�ٷ|�ܰʡA���UUID���O�I
		File playerData = new File(mfp.getDataFolder() + File.separator + "PlayerData" + File.separator + event.getPlayer().getUniqueId().toString() + ".yml"); 
		//�ˬd���a����ɮ׬O�_�s�b
		if (!playerData.exists())
		{
			try
			{
				//���s�b�ҥH�إ�
				playerData.createNewFile();
				//�ǳƼg�J��ƨ�yml�ɤ��A�ҥH����L�নYamlConfiguration��
				YamlConfiguration y = YamlConfiguration.loadConfiguration(playerData);
				//�ഫ��i�Q��YamlConfiguration�ɾާ@�A�ާ@��k�MConfig�@��
				//�g�J�@��Amana: 100 
				y.set("mana", 100);
				//�s��
				y.save(playerData);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			//�ǳ�Ū��yml�ɪ����
			YamlConfiguration y = YamlConfiguration.loadConfiguration(playerData);
			//���o���
			Integer mana = Integer.valueOf(y.getInt("mana"));
			//���a�n�J�ɳ]�w�]�O�q
			MyFirstPlugin.mana.put(event.getPlayer().getName(), mana);
		}
	}
}
