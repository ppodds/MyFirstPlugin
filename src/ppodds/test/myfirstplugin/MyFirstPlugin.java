package ppodds.test.myfirstplugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
//��class(���O)�s�񪺮M��
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
//��import �i�H�Ψ�²���@�ǪF�� �AIDE�q�`�|���A�� ��@�U�����N�n
//�p�G�Simport�N�n��"�����ŦX�W��"
//�ɥR:import�i�H�ϥθU�Φr��*�ӶפJ�ӮM��Ҧ���class
public class MyFirstPlugin extends JavaPlugin
{
	// public�ŧi�L�O�������O(�v���̤j)�A�Q�F�ѳo�譱�n�ۤv�ݮѡA�o�̤���
	// ���]���ڭ̼g���OJava����A�ҥH�n�~��JavaPlugin(Bukkit��API)
	// extends�OJava������r�A�i�H�Ψ��~�����O�C�bJava������h���~��
	// �p�G�A�S�~�ӡAAPI���ܦh�\�ण��ΡA�N����QBukkit����F

	public void onEnable()
	{
		//void�O���o�Ӥ�k���|���Ǧ^�ȡA���Ǥ�k�i�H�ǭ�
		//�@���k�᭱��()�i�H�Ψӳ]�w��k���Ѽ�
		/*-----------------------------------------------------------
�@�@�@�@		onEnable()�o��"��k"�b
�@�@�@�@		����QŪ�����ɭԷ|����
�@�@�@  		�i�H���@�ǵ{������l��
                                �Ҥl���ܦh�A���O����Ū���ɭ�
                                �⪱�a��ƥ���Ū��HashMap�̭�
�@�@�@�@-------------------------------------------------------------*/
		getLogger().info("���b�}��...");
		//getLogger().info()�|��X�T����cmd(�²��l)
		//�ӥB�|���A�[�J���󪺦W������[MyFirstPlugin]
		//����X���b�}�ҨõL���n�ABukkit���A�Φn�F
		getLogger().info("Hello Bukkit!!!");
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
	}

	public void onDisable()
	{
		/*-----------------------------------------------
�@�@�@�@onDisable()�o�Ӥ�k�b���A������
�@�@�@�@�έ�Ū���󪺮ɭԷ|����
�@�@�@�@�i�H����s�ɩθ귽���������u�@
�@�@�@�@-------------------------------------------------*/
		getLogger().info("���b����...");
		//����X���b�����õL���n�ABukkit���A�Φn�F
	}
	
	//�e�����йL�����O�y�k
	public boolean onCommand(CommandSender sender,Command cmd,String lable,String args[])
	{
		//�Q�α���P�_����������O
		//equals�O�ΨӤ��r��O�_�����@�˪�
		//instanceof�o������r�i�H���Ӥ��e�̪���O�_�ݩ������O����
	    if (lable.equals("jump") && sender instanceof Player)
	    {
	    	//�α���P�_���P�_�O�_�L�Ѽ�length�O�ΨӨ��o�}�C���ת�
	    	if (args.length == 0)
	    	{
	    		//�ѩ�w�g�T�wsender�@�w�OPlayer�A�ҥH�i�H���zĵ�i�i��j���૬�Ӿާ@
	    		//p�O�W�٥i�H�ۥѧ�A�D�n�l�D�t�ת��ܯ�u���q�u�A���O�p�G�h�H�@�P�}�o���ܤӵu�|�ɭP��L�H�ݤ�����!
	    		Player p = (Player) sender;
	    		//�W�[�Ĥ��ĪG�����a
	    		//�ڳo���Ϊ�addPotionEffect�ݭn�ǤJ1�ӰѼ�(�]����L���P�W��k�A�z�L�ѼƤ��P�өI�s�A����ɥR�|��
	    		//�ѼƬOPotionEffect����A��3�ӫغc�l�i�H�ϥΡA�ڨϥΪ��O��²�檺�A�u�ݶǤJ3�ӰѼ�
	    		//�Ĥ@�ӬOPotionEffectType�A�L�O�H�C�|�ŧi���A�����H�U�覡�s�X�ӴN�n
	    		//�ĤG�ӬO����ɶ��A��Minecraft����tick�Ӱ��p�q�A20ticks����1��
	    		//�̫�@�ӬO��O�j��
	    		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP , 100 , 2));
	    	}
	    }


	    return false;
	}
}