package com.chenjian.enums;

public enum WeaponNameEnums {
	
	NONE(0,"none","未知"),
	
	TU_LONG_DAO(1,"tu_long_dao","屠龙刀"),
	SHE_RI_GONG(2,"she_ri_gong","射日弓"),
	YI_TIAN_JIAN(3,"yi_tian_jian","倚天剑"),
	ZHUI_HUN_QIANG(4,"zhui_hun_qiang","追魂枪"),
	DUO_MING_GUN(5,"duo_ming_gun","夺命棍"),
	
	MU_DAO(6,"mu_dao","木刀"),
	MU_GONG(7,"mu_gong","木弓"),
	MU_JIAN(8,"mu_jian","木剑"),
	MU_QIANG(9,"mu_qiang","木枪"),
	MU_GUN(10,"mu_gun","木棍");
	
	
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


	private WeaponNameEnums(int value, String name, String showName){
		this.value=value;
		this.name=name;
		this.showName=showName;
	}
	
	
	public static String getShowNameByValue(int value){
		WeaponNameEnums[] values = WeaponNameEnums.values();
		for(int i=0;i<values.length;i++){
			if(values[i].getValue()==value){
				return values[i].getShowName();
			}
		}
		return WeaponNameEnums.NONE.getShowName();
	}

}
