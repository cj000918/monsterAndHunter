package com.chenjian.util;

import com.chenjian.enums.WeaponDescribeEnums;
import com.chenjian.enums.WeaponNameEnums;

public class WeaponUtil {

 
	/**
	 * 随机获取武器名称
	 * @return
	 */
    public static String randomaWeaponName(){
    	
    	int weaponNameResult = (int)(Math.random() * WeaponNameEnums.values().length);
    	
    	String weaponName = WeaponNameEnums.getShowNameByValue(weaponNameResult);
    	
    	return weaponName;
    }
	
    /**
     * 随机获取武器品质
     * @param weaponName
     * @return
     */
    public static String randomaWeaponDescribeName(String weaponName){
    	
    	String weaponDescribe = "";
    	
    	if( ! weaponName.equals("未知")){
    		
        	int weaponDescribeResult = (int)(Math.random() * WeaponDescribeEnums.values().length);
        	  
        	weaponDescribe = WeaponDescribeEnums.getShowNameByValue(weaponDescribeResult);
    	}
    	
    	return weaponDescribe;
    }
    
    /**
     * 随机获取武器最小攻击力
     * @param weaponDescribe
     * @return
     */
    public static long getMinAggressivity(String weaponDescribe){
    	
    	long minAggressivity = 0;
    	
    	if(weaponDescribe == WeaponDescribeEnums.can_que.getShowName()){
    		
    		minAggressivity = (int)(Math.random()*10) + 1;
			
		}else if(weaponDescribe == WeaponDescribeEnums.wan_zheng.getShowName()){
			
    		minAggressivity = (int)(Math.random()*20) + 5;
			
			
		}else if(weaponDescribe == WeaponDescribeEnums.ji_ping.getShowName()){
			
    		minAggressivity = (int)(Math.random()*50) + 10;
			
		}
    	return minAggressivity;
    }
    
    /**
     * 随机获取武器最大攻击力
     * @param weaponDescribe
     * @param minAggressivity
     * @return
     */
    public static long getMaxAggressivity(String weaponDescribe, long minAggressivity){
    	
    	long maxAggressivity = 0;
    	
    	if(weaponDescribe == WeaponDescribeEnums.can_que.getShowName()){
    				
    		maxAggressivity = minAggressivity + (int)(Math.random()*10);
			
		}else if(weaponDescribe == WeaponDescribeEnums.wan_zheng.getShowName()){
			
    		maxAggressivity = minAggressivity + (int)(Math.random()*20);
			
		}else if(weaponDescribe == WeaponDescribeEnums.ji_ping.getShowName()){
			
    		maxAggressivity = minAggressivity + (int)(Math.random()*50);
		}
    	return maxAggressivity;
    }
}
