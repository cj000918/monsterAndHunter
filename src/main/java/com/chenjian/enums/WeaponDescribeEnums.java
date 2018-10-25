package com.chenjian.enums;

public enum WeaponDescribeEnums {
	
	can_que(1,"can_que","残缺的"),
	wan_zheng(2,"wan_zheng","完整的"),
	ji_ping(3,"ji_ping","极品的");
	
	
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


	private WeaponDescribeEnums(int value, String name, String showName){
		this.value=value;
		this.name=name;
		this.showName=showName;
	}
	
	
	public static String getShowNameByValue(int value){
		WeaponDescribeEnums[] values = WeaponDescribeEnums.values();
		for(int i=0;i<values.length;i++){
			if(values[i].getValue()==value){
				return values[i].getShowName();
			}
		}
		return WeaponDescribeEnums.can_que.getShowName();
	}

}
