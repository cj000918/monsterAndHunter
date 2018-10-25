package com.chenjian.entity;


import com.chenjian.util.GameUtil;

public class GameStart {
  
	Hunter hunter;

    public GameStart(){
    	
    	String hunterName = GameUtil.getHunterName();

    	hunter = new Hunter(hunterName);
    	
    	hunter.getWeapon();
 
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
        }
    }
}
