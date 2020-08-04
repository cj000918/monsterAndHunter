package com.chenjian.entity.base;


import com.chenjian.util.DateUtil;
import com.chenjian.util.JsonUtil;
import com.chenjian.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GameStart {

    public GameStart(){

    }

    @Autowired
    private RedisUtil redisUtil;

	Hunter hunter;

    public GameStart setName(String name){

        Monster.setRedisUtil(redisUtil);
        Hunter.setRedisUtil(redisUtil);
        Weapon.setRedisUtil(redisUtil);

    	hunter = new Hunter(name);

        redisUtil.hset("hunter_info",name, JsonUtil.objectToString(hunter));

    	return this;
 
    }
    
    public void start(){

        while(hunter.isLive){

            redisUtil.hset("hunter_info",hunter.name, JsonUtil.objectToString(hunter));
           
        	System.out.println("------------------正在探险中---------------------"+"\r\n");
            redisUtil.lSet("fight_info_"+hunter.name, DateUtil.getNowTime()+"【"+hunter.name+"】------------------正在探险中---------------------");

            /**让程序休息3秒钟**/
           try{
                Thread.sleep(3000);
           } catch(Exception e){
            	   
           }

           long monsterId =  redisUtil.incr("Monster_id_list",1);

           Monster  monster = new Monster(hunter);

            monster.setMonsterId(monsterId);
            
            System.out.println(" 遭遇敌人 , " + monster.title);
            redisUtil.lSet("fight_info_"+hunter.name, DateUtil.getNowTime()+"【"+hunter.name+"】"+"遭遇敌人 , ");

            monster.showMonsterInfo(hunter);

            redisUtil.lSet("Monster_list",monster);

            redisUtil.hset("Monster_info",monster.getMonsterId()+"", JsonUtil.objectToString(monster));
            
            hunter.fight(monster);
        }
        
        end();
    }
    
    /**
     * 结束
     */
    public void end(){
        if(!hunter.isLive){
            redisUtil.lSet("fight_info_"+hunter.name, DateUtil.getNowTime()+"【"+hunter.name+"】"+" GAME_OVER...");
            System.out.println("GAME_OVER");
        }
    }
}
