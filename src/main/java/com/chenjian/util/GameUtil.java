package com.chenjian.util;

import java.util.Scanner;

public class GameUtil {
   
	
	public static long randomaRange(long start,long end){
        return (long)(Math.random()*(end - start + 1)+start);
    }
	
	public static long randomaNumber(long number){
        return (long)(Math.random() * number);
    }
    
	
	public static boolean hidden(long hideRate){
    	
		long hitRate = 100 - hideRate;//100减去被攻击方的闪避, 得到攻击方的命中率
		
		long ran = GameUtil.randomaNumber(100);
		
		long result = ran + hitRate;
		
		//命中率加上 100以内随机数, 如果结果大于100 则为被命中, 否则为闪避
		if(result > 100) {
			 return false;
		}else {
			return true;
		}
    }

	/**
	 * 如果返回false则表示先手失败
	 * @param hunterHideRate
	 * @param monsterHideRate
	 * @return
	 */
    public static Boolean hidden(Long hunterHideRate, Long monsterHideRate){

		long hitRate1 = 100 - hunterHideRate;//100减去被攻击方的闪避, 得到攻击方的命中率
		long ran1 = GameUtil.randomaNumber(100);
		long result1 = ran1 + hitRate1;

		long hitRate2 = 100 - monsterHideRate;//100减去被攻击方的闪避, 得到攻击方的命中率
		long ran2 = GameUtil.randomaNumber(100);
		long result2 = ran2 + hitRate2;

		if(result1 < result2){
			return false;
		}
		return true;
	}
  
	static long lostBasicLife = 1;
   
    public static long calLostLife(long maxAttack, long minAttack, long defend){
    	
    	long attack = randomaRange(minAttack, maxAttack);
    	
    	long lostLife = attack-defend;
    	long rel = 0;
        if(lostLife<=0){
            rel = lostBasicLife;
        }else{
            rel = (lostLife+lostBasicLife);
        }
        return rel;
    }

    
    public static String getHunterName(){
    	
    	System.out.println("请输入你的名字: ");
    	
    	Scanner scan =new Scanner(System.in);
 
    	String userInput = scan.next();
    	
    	boolean validate = true;
    	
    	while (validate){
    		
        	System.out.println("名称为: "+ userInput +" , 是否确认 ? 【yes/no】");
        	
        	String userInput2 = scan.next();
    		
        	if(userInput2.equals("yes")){
        		
        		return userInput;
        		
        	}else if(userInput2.equals("no")){
        		
        		return	getHunterName();
        		
        	}else{
        		
        		System.out.println("请正确输入指令 ");
        	}
    	}
    	return	userInput;
    }
    
}
