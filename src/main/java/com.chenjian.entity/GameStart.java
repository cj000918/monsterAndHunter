package com.chenjian.entity;


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
           
        	System.out.println("------------------寻找对手中---------------------"+"\r\n");
            /**让程序休息3秒钟**/
           try{
                Thread.sleep(3000);
           } catch(Exception e){
            	   
           }

           long monsterId =  redisUtil.incr("Monster_id_list",1);

           Monster  monster = new Monster(hunter);

            monster.setMonsterId(monsterId);
            
            System.out.println(" 遇到敌人 , " + monster.type);

            monster.showMonsterInfo();

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
            System.out.println("GAME OVER");

//            redisUtil.hset("info","game_over","GAME OVER");
        }
    }
}
