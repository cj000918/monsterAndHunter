package com.chenjian.enums;

import com.chenjian.enums.base.IIntegerEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WeaponNameEnums implements IIntegerEnum {

	NONE(0, "none", "未知"),

	TU_LONG_DAO(1, "tu_long_dao", "屠龙刀"),
	SHE_RI_GONG(2, "she_ri_gong", "射日弓"),
	YI_TIAN_JIAN(3, "yi_tian_jian", "倚天剑"),
	ZHUI_HUN_QIANG(4, "zhui_hun_qiang", "追魂枪"),
	DUO_MING_GUN(5, "duo_ming_gun", "夺命棍"),

	MU_DAO(6, "mu_dao", "木刀"),
	MU_GONG(7, "mu_gong", "木弓"),
	MU_JIAN(8, "mu_jian", "木剑"),
	MU_QIANG(9, "mu_qiang", "木枪"),
	MU_GUN(10, "mu_gun", "木棍"),


	;


	private final Integer code;
	private final String msg;
	private final String showName;

}
