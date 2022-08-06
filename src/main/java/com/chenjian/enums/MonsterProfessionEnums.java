package com.chenjian.enums;

import com.chenjian.enums.base.IIntegerEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 怪物职业
 *
 * @author chenjian
 */
@Getter
@AllArgsConstructor
public enum MonsterProfessionEnums implements IIntegerEnum {

    ZHAN_SHI(1, "zhan_shi", "战士"),
    GONG_SHOU(2, "gong_shou", "弓手"),
    DAO_FU_SHOU(3, "dao_fu_shou", "刀斧手"),
    FA_SHI(4, "fa_shi", "法师"),

    ;


    private Integer code;
    private String msg;
    private String showName;

}
