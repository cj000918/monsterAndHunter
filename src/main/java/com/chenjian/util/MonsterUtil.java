package com.chenjian.util;


import com.chenjian.enums.MonsterGradeEnums;
import com.chenjian.enums.MonsterNameEnums;
import com.chenjian.enums.MonsterProfessionEnums;
import com.chenjian.enums.base.IEnum;

public class MonsterUtil {
	
	
	/**
	 * 获取怪物种类
	 * @return
	 */
	public static String getMonsterName(long hunterLevel){
		
		if(hunterLevel < 5){
			
			hunterLevel = 5;
		} else {
			hunterLevel = MonsterNameEnums.values().length;
		}

		int monsterNameResult = (int) (Math.random() * hunterLevel);

		if (monsterNameResult < 1) {

			monsterNameResult = 1;
		}

		return IEnum.getShowName(monsterNameResult, MonsterNameEnums.class);
	}
	
	/**
	 * 获取怪物级别
	 * @return
	 */
	public static String getMonsterGrade(long hunterLevel){
		
		if(hunterLevel < 5){
			
			hunterLevel = 5;
		} else {
			hunterLevel = MonsterGradeEnums.values().length;
		}

		int monsterGradeResult = (int) (Math.random() * hunterLevel);

		if (monsterGradeResult < 1) {

			monsterGradeResult = 1;
		}
		return IEnum.getShowName(monsterGradeResult, MonsterGradeEnums.class);
	}
	
	/**
	 * 获取怪物职业
	 * @return
	 */
	public static String getMonsterProfession(long hunterLevel){
		
		if(hunterLevel < 5){
			
			hunterLevel = 5;
		} else {
			hunterLevel = MonsterProfessionEnums.values().length;
		}

		int monsterProfessionResult = (int) (Math.random() * hunterLevel);

		if (monsterProfessionResult < 1) {

			monsterProfessionResult = 1;
		}
		return IEnum.getShowName(monsterProfessionResult, MonsterProfessionEnums.class);
	}
	
	
	/**
	 * 获取怪物最大攻击力
	 * @param monsterGrade
	 * @return
	 */
	public static long getMaxAttack(String monsterGrade){
		
		long maxAttack = 0;
		
		if(monsterGrade.equals(MonsterGradeEnums.HUAN_BING.getShowName())){

			maxAttack = GameUtil.randomNumber(5) + 5;

		}else if(monsterGrade.equals(MonsterGradeEnums.PU_TONG.getShowName())) {

			maxAttack = GameUtil.randomNumber(10) + 10;

		}else if(monsterGrade.equals(MonsterGradeEnums.YI_BAN.getShowName())) {

			maxAttack = GameUtil.randomNumber(15) + 15;

		}else if(monsterGrade.equals(MonsterGradeEnums.BIAN_YI.getShowName())) {

			maxAttack = GameUtil.randomNumber(20) + 15;

		}else if(monsterGrade.equals(MonsterGradeEnums.CHAO_JI.getShowName())) {

			maxAttack = GameUtil.randomNumber(25) + 20;

		}else if(monsterGrade.equals(MonsterGradeEnums.JING_YING.getShowName())) {

			maxAttack = GameUtil.randomNumber(30) + 35;

		}else if(monsterGrade.equals(MonsterGradeEnums.KUANG_HUA.getShowName())) {

			maxAttack = GameUtil.randomNumber(30) + 30;

		}else if(monsterGrade.equals(MonsterGradeEnums.FEN_NU.getShowName())) {

			maxAttack = GameUtil.randomNumber(35) + 30;

		}else if(monsterGrade.equals(MonsterGradeEnums.BAO_ZAO.getShowName())) {

			maxAttack = GameUtil.randomNumber(40) + 35;

		}else if(monsterGrade.equals(MonsterGradeEnums.SHI_XUE.getShowName())) {

			maxAttack = GameUtil.randomNumber(45) + 40;

		}else if(monsterGrade.equals(MonsterGradeEnums.ZHI_MING.getShowName())) {

			maxAttack = GameUtil.randomNumber(50) + 45;
		}
		
		return maxAttack;
	}
	
	/**
	 * 获取怪物最小攻击力
	 * @param monsterGrade
	 * @return
	 */
	public static long getMinAttack(String monsterGrade){
		
		long minAttack = 0;
		
		if(monsterGrade.equals(MonsterGradeEnums.HUAN_BING.getShowName())) {

			minAttack = GameUtil.randomNumber(5) + 3;

		}else if(monsterGrade.equals(MonsterGradeEnums.PU_TONG.getShowName())) {

			minAttack = GameUtil.randomNumber(5) + 8;

		}else if(monsterGrade.equals(MonsterGradeEnums.YI_BAN.getShowName())) {

			minAttack = GameUtil.randomNumber(10) + 10;

		}else if(monsterGrade.equals(MonsterGradeEnums.BIAN_YI.getShowName())) {

			minAttack = GameUtil.randomNumber(15) + 10;

		}else if(monsterGrade.equals(MonsterGradeEnums.CHAO_JI.getShowName())) {

			minAttack = GameUtil.randomNumber(17) + 15;

		}else if(monsterGrade.equals(MonsterGradeEnums.JING_YING.getShowName())) {

			minAttack = GameUtil.randomNumber(20) + 16;

		}else if(monsterGrade.equals(MonsterGradeEnums.KUANG_HUA.getShowName())) {

			minAttack = GameUtil.randomNumber(18) + 20;

		}else if(monsterGrade.equals(MonsterGradeEnums.FEN_NU.getShowName())) {

			minAttack = GameUtil.randomNumber(19) + 25;

		}else if(monsterGrade.equals(MonsterGradeEnums.BAO_ZAO.getShowName())) {

			minAttack = GameUtil.randomNumber(20) + 30;

		}else if(monsterGrade.equals(MonsterGradeEnums.SHI_XUE.getShowName())) {

			minAttack = GameUtil.randomNumber(21) + 35;

		}else if(monsterGrade.equals(MonsterGradeEnums.ZHI_MING.getShowName())) {

			minAttack = GameUtil.randomNumber(22) + 40;
		}
		
		return minAttack;
	}

	/**
	 * 获取怪物防御力
	 * 当前设定, 敏捷:防御 = 3:1
	 *
	 * @param agile
	 * @return
	 */
	public static long getDefend(long agile){
		
		long defend = 0;
		
		defend = agile/3;
		
		if(defend < 1){
			
			defend = 1;
		}
		 
		return defend;
	}
	
	/**
	 * 获取怪物敏捷
	 * @param monsterGrade
	 * @return
	 */
	public static long getAgile(String monsterGrade){
		
		
		long agile = 0;
		
		if(monsterGrade.equals(MonsterGradeEnums.HUAN_BING.getShowName())) {

			agile = GameUtil.randomNumber(1) + 5;

		}else if(monsterGrade.equals(MonsterGradeEnums.PU_TONG.getShowName())) {

			agile = GameUtil.randomNumber(6) + 8;

		}else if(monsterGrade.equals(MonsterGradeEnums.YI_BAN.getShowName())) {

			agile = GameUtil.randomNumber(7) + 10;

		}else if(monsterGrade.equals(MonsterGradeEnums.BIAN_YI.getShowName())) {

			agile = GameUtil.randomNumber(10) + 12;

		}else if(monsterGrade.equals(MonsterGradeEnums.CHAO_JI.getShowName())) {

			agile = GameUtil.randomNumber(30) + 15;

		}else if(monsterGrade.equals(MonsterGradeEnums.JING_YING.getShowName())) {

			agile = GameUtil.randomNumber(35) + 18;

		}else if(monsterGrade.equals(MonsterGradeEnums.KUANG_HUA.getShowName())) {

			agile = GameUtil.randomNumber(40) + 20;

		}else if(monsterGrade.equals(MonsterGradeEnums.FEN_NU.getShowName())) {

			agile = GameUtil.randomNumber(50) + 30;

		}else if(monsterGrade.equals(MonsterGradeEnums.BAO_ZAO.getShowName())) {

			agile = GameUtil.randomNumber(55) + 20;

		}else if(monsterGrade.equals(MonsterGradeEnums.SHI_XUE.getShowName())) {

			agile = GameUtil.randomNumber(60) + 30;

		}else if(monsterGrade.equals(MonsterGradeEnums.ZHI_MING.getShowName())) {

			agile = GameUtil.randomNumber(70) + 30;
		}
		
		return agile;
	}

	/**
	 * 获取怪物闪避率
	 * @param hunterHideRate
	 * @return
	 */
	public static long getHideRate(long hunterHideRate) {

		long hideRate = 5;//基础闪避率

		long ran = GameUtil.randomNumber(100);

		long result = hideRate * 1 / 4;

		if (result < 1) {
			result = 1;
		}

		if (ran > 50) {

			hideRate = hunterHideRate + result;
		}else {
			hideRate = hunterHideRate - result;
		}
		
		return hideRate;
	}
	
	/**
	 * 获取最大生命值
	 * @param defend
	 * @return
	 */
	public static long getMonsterMaxLife(long defend){
		
		long maxLife = 0;
		
		if(defend > 100){
			maxLife = defend * 7/100;
		}else if(defend > 80 ){
			maxLife = defend * 6/100;
		}else if(defend > 70 ){
			maxLife = defend * 5/100;
		}else if(defend > 60 ){
			maxLife = defend * 4/100;
		}else if(defend < 50){
			maxLife = defend * 2 * 3;
		}
		
		if(maxLife < 50){
			
			maxLife = 50;
		}
		
		return maxLife;
	}


	
}
