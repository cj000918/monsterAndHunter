package com.chenjian.enums;

/**
 * 怪物名称
 * @author chenjian
 *
 */
public enum MonsterNameEnums {
	
	DI_JING(1,"di_jing","地精"),
	CAI_LANG_REN(2,"cai_lang_ren","豺狼人"),
	GOU_TOU_REN(3,"gou_tou_ren","狗头人"),
	SHI_REN_MO(4,"shi_ren_mo","食人魔"),
	XI_YI_REN(5,"xi_yi_ren","蜥蜴人"),
	
	NIU_TOU_REN(6,"niu_tou_ren","牛头人"),
	SHI_XIANG_GUI(7,"shi_xiang_gui","石像鬼"),
	YING_SHEN_NV_YAO(8,"ying_shen_nv_yao","鹰身女妖"),
	KU_LOU(9,"ku_lou","骷髅"),
	SEN_LIN_JU_MO(10,"sen_lin_ju_mo","森林巨魔"),
	
	JU_XIANG_KUI_LEI(11,"ju_xiang_kui_lei","巨像傀儡"),
	YE_LANG(12,"ye_lang","野狼"),
	BAN_REN_MA(13,"ban_ren_ma","半人马"),
	LEI_TING_XI_YI(14,"lei_ting_xi_yi","雷霆蜥蜴"),
	SHU_YAO(15,"shu_yao","树妖"),
	
	HEI_LONG(16,"hei_long","黑龙");
	
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


	private MonsterNameEnums(int value, String name, String showName){
		this.value=value;
		this.name=name;
		this.showName=showName;
	}	
	
	public static String getShowNameByValue(int value){
		MonsterNameEnums[] values = MonsterNameEnums.values();
		for(int i=0;i<values.length;i++){
			if(values[i].getValue()==value){
				return values[i].getShowName();
			}
		}
		return MonsterNameEnums.DI_JING.getShowName();
	}
	
}
