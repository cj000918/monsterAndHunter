package com.chenjian.enums;

import com.chenjian.enums.base.IIntegerEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeaponDescribeEnums implements IIntegerEnum {

	can_que(1, "can_que", "残缺的"),
	wan_zheng(2, "wan_zheng", "完整的"),
	ji_ping(3, "ji_ping", "极品的");


	private Integer code;
	private String msg;
	private String showName;
}
