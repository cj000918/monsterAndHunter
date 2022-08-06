package com.chenjian.enums;

import com.chenjian.enums.base.IIntegerEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 怪物级别
 *
 * @author chenjian
 */

@Getter
@AllArgsConstructor
public enum MonsterGradeEnums implements IIntegerEnum {

	HUAN_BING(1, "huan_bing", "患病"),
	PU_TONG(2, "pu_tong", "普通"),
	YI_BAN(3, "yi_ban", "一般"),
	BIAN_YI(4, "bian_yi", "变异"),
	CHAO_JI(5, "chao_ji", "超级"),
	JING_YING(6, "jing_ying", "精英"),


	KUANG_HUA(7, "kuang_hua", "狂化"),
	FEN_NU(8, "fen_nu", "愤怒"),
	BAO_ZAO(9, "bao_zao", "暴躁"),
	SHI_XUE(10, "shi_xue", "嗜血"),

	ZHI_MING(11, "zhi_ming", "致命"),

	;

	private Integer code;
	private String msg;
	private String showName;
}
