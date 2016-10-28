package ppodds.test.myfirstplugin;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
//↑class(類別)存放的套件
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
//↑import 可以用來簡略一些東西 你IDE通常會幫你用 選一下除錯就好
//如果沒import就要打"完全符合名稱"
//補充:import可以使用萬用字元*來匯入該套件所有的class
public class MyFirstPlugin extends JavaPlugin
{
	
	// public宣告他是公用類別(權限最大)，想了解這方面要自己看書，這裡不講
	// ↑因為我們寫的是Java插件，所以要繼承JavaPlugin(Bukkit的API)
	// extends是Java的關鍵字，可以用來繼承類別。在Java中不能多重繼承
	// 如果你沒繼承，API的很多功能不能用，就不能被Bukkit執行了

	
	public static Map<String,Integer> mana = new HashMap<String,Integer>();
	
	
	public void onEnable()
	{
		//void是指這個方法不會有傳回值，有些方法可以傳值
		//一般方法後面的()可以用來設定方法的參數
		/*-----------------------------------------------------------
　　　　		onEnable()這個"方法"在
　　　　		插件被讀取的時候會執行
　　　  		可以做一些程式的初始化
                                例子有很多，像是插件重讀的時候
                                把玩家資料全部讀到HashMap裡面
　　　　-------------------------------------------------------------*/
		getLogger().info("正在開啟...");
		//getLogger().info()會輸出訊息到cmd(黑盒子)
		//而且會幫你加入插件的名稱類似[MyFirstPlugin]
		//其實輸出正在開啟並無必要，Bukkit幫你用好了
		getLogger().info("Hello Bukkit!!!");
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageByEntity(), this);
		getServer().getPluginManager().registerEvents(new PlayerItemConsume(), this);
	}

	public void onDisable()
	{
		/*-----------------------------------------------
　　　　onDisable()這個方法在伺服器結束
　　　　或重讀插件的時候會執行
　　　　可以執行存檔或資源釋放之類的工作
　　　　-------------------------------------------------*/
		getLogger().info("正在關閉...");
		//其實輸出正在關閉並無必要，Bukkit幫你用好了
	}
	
	//前面介紹過的指令語法
	public boolean onCommand(CommandSender sender,Command cmd,String lable,String args[])
	{
		//利用條件判斷式來檢驗指令
		//equals是用來比對字串是否完全一樣的
		//instanceof這個關鍵字可以拿來比對前者物件是否屬於後者類別物件
	    if (lable.equals("jump") && sender instanceof Player)
	    {
	    	//用條件判斷式判斷是否無參數length是用來取得陣列長度的
	    	if (args.length == 0)
	    	{
	    		//由於已經確定sender一定是Player，所以可以不理警告進行強制轉型來操作
	    		//p是名稱可以自由改，主要追求速度的話能短儘量短，但是如果多人共同開發的話太短會導致其他人看不懂喔!
	    		Player p = (Player) sender;
	    		//增加藥水效果給玩家
	    		//我這次用的addPotionEffect需要傳入1個參數(也有其他的同名方法，透過參數不同來呼叫，之後補充會說
	    		//參數是PotionEffect物件，有3個建構子可以使用，我使用的是最簡單的，只需傳入3個參數
	    		//第一個是PotionEffectType，他是以列舉宣告的，直接以下方式叫出來就好
	    		//第二個是持續時間，用Minecraft中的tick來做計量，20ticks等於1秒
	    		//最後一個是能力強度
	    		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP , 100 , 2));
	    	}
	    }
	    else
	    if (lable.equals("mob") && sender instanceof Player)
	    {
	    	//用條件判斷式判斷是否無參數length是用來取得陣列長度的
	    	if (args.length == 0)
	    	{
	    		//由於已經確定sender一定是Player，所以可以不理警告進行強制轉型來操作
	    		//p是名稱可以自由改，主要追求速度的話能短儘量短，但是如果多人共同開發的話太短會導致其他人看不懂喔!
	    		Player p = (Player) sender;
	    		//設定一隻殭屍
	    		Zombie zm = (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
	    		//設定生命及最大生命至50心
	    		zm.setMaxHealth(100);
	    		zm.setHealth(100);
	    		//設定裝備
	    		ItemStack is = new ItemStack(Material.IRON_SWORD);
	    		ItemMeta im = is.getItemMeta();
	    		im.setDisplayName("巨劍");
	    		is.setItemMeta(im);
	    		zm.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
	    		zm.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
	    		zm.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
	    		zm.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
	    		zm.getEquipment().setItemInMainHand(is);
	    		
	    		
	    		
	    	}
	    	

	    }
	    else
	    if (lable.equals("mana") && sender instanceof Player && args.length == 0)
	    {
	    	//發送魔力量的訊息
	    	sender.sendMessage("魔力剩餘: " + mana.get(sender.getName()));
	    }
	    else
	    if (lable.equals("explosion") && sender instanceof Player)
	    {	
	    	if (args.length == 0)
	    	{
	    		//由於已經確定sender一定是Player，所以可以不理警告進行強制轉型來操作
	    		//p是名稱可以自由改，主要追求速度的話能短儘量短，但是如果多人共同開發的話太短會導致其他人看不懂喔!
	    		Player p = (Player) sender;
	    		
	    		//設定魔力量為原本的魔力量-50
	    		if (mana.get(p.getName()) >= 50)
	    		{
	    			mana.put(p.getName(), mana.get(p.getName()) - 50);

	    			//這邊因為Eclipse分不出兩個getTargetBlock()的差異
	    			//導致要宣告一個Set物件填進去
	    			//依據原本的API，要忽略掉空氣只要輸出null就好了

	    			Set<Material> s = new HashSet<Material>();
	    			s.add(Material.AIR);

	    			//取得玩家的箭頭的方塊座標(範圍為50 + (魔力量*0.5))
	    			//註:API有提到有些伺服器能限制最大範圍只能到100格

	    			Location l = p.getTargetBlock(s, (int) (50 + mana.get(sender.getName())*0.5)).getLocation();

	    			//釋放粒子效果

	    			p.getWorld().spawnParticle(Particle.SMOKE_LARGE, l, 60);


	    			//位置為玩家的箭頭的方塊座標
	    			//持續60tick
	    			//種類為煙的粒子
	    			//剛好最近提摩做了粒子效果的翻譯，在此給上連結
	    			//http://home.gamer.com.tw/creationDetail.php?sn=3347715

	    			//5秒以後執行以下
	    			//最後一個參數是延遲時間，以Minecraft中的Tick為單位，20Tick為一秒
	    			Bukkit.getScheduler().runTaskLater(this, new Runnable() {
	    				public void run()
	    				{
	    					//讓玩家的箭頭的方塊座標發生爆炸
	    					//威力公式為(30 + (魔力量*0.7))
	    					//會產生火焰
	    					p.getWorld().createExplosion(l, (float) (30 + mana.get(sender.getName())*0.7), true);
	    				}
	    			}, 100);

	    		}
	    		else
	    		{
	    			//發送警告
	    			p.sendMessage(ChatColor.RED + "魔力不足，無法使用技能。");
	    		}
	    	}
	    }

	    return false;
	}
}