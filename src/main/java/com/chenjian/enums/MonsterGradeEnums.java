package com.chenjian.enums;

/**
 * 怪物级别
 * @author chenjian
 *
 */
public enum MonsterGradeEnums {

	HUAN_BING(1,"huan_bing","患病"),
	PU_TONG(2,"pu_tong","普通"),
	YI_BAN(3,"yi_ban","一般"),
	BIAN_YI(4,"bian_yi","变异"),
	CHAO_JI(5,"chao_ji","超级"),
	JING_YING(6,"jing_ying","精英"),
	
	
	KUANG_HUA(7,"kuang_hua","狂化"),
	FEN_NU(8,"fen_nu","愤怒"),
	BAO_ZAO(9,"bao_zao","暴躁"),
	SHI_XUE(10,"shi_xue","嗜血"),
	
	ZHI_MING(11,"zhi_ming","致命"),
	
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


	private MonsterGradeEnums(int value, String name, String showName){
		this.value=value;
		this.name=name;
		this.showName=showName;
	}	
	
	public static String getShowNameByValue(int value){
		MonsterGradeEnums[] values = MonsterGradeEnums.values();
		for(int i=0;i<values.length;i++){
			if(values[i].getValue()==value){
				return values[i].getShowName();
			}
		}
		return MonsterGradeEnums.HUAN_BING.getShowName();
	}
}
