package com.chenjian.entity;


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

        redisUtil.hset("hunter_info",name,System.currentTimeMillis());

    	hunter = new Hunter(name);
    	return this;
 
    }
    
    public void start(){



        while(hunter.isLive){
           
        	System.out.println("------------------寻找对手中---------------------"+"\r\n");
            /**让程序休息3秒钟**/
           try{
                Thread.sleep(3000);
           } catch(Exception e){
            	   
           }  
            
            Monster  monster = new Monster(hunter);
            
            System.out.println(" 遇到敌人 , " + monster.type);

            monster.showMonsterInfo();
            
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
