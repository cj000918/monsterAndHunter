package com.chenjian.enums;

/**
 * 怪物职业
 * @author chenjian
 *
 */
public enum MonsterProfessionEnums {

	ZHAN_SHI(1,"zhan_shi","战士"),
	GONG_SHOU(2,"gong_shou","弓手"),
	DAO_FU_SHOU(3,"dao_fu_shou","刀斧手"),
	FA_SHI(4,"fa_shi","法师"),
	
	;

	
	int value;
	String name;
	String showName;
		
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}


	private MonsterProfessionEnums(int value, String name, String showName){
		this.value=value;
		this.name=name;
		this.showName=showName;
	}	
	
	public static String getShowNameByValue(int value){
		MonsterProfessionEnums[] values = MonsterProfessionEnums.values();
		for(int i=0;i<values.length;i++){
			if(values[i].getValue()==value){
				return values[i].getShowName();
			}
		}
		return MonsterProfessionEnums.ZHAN_SHI.getShowName();
	}
}
