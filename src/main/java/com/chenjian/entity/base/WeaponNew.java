package com.chenjian.entity.base;

import com.chenjian.util.WeaponUtil;
import lombok.Data;

/**
 * All rights Reserved, Designed By www.freemud.cn
 *
 * @version V1.0
 * @Title: WeaponNew
 * @Package com.chenjian.entity
 * @Description:
 * @author: jian.chen@freemud.com
 * @date: 2019/9/4 9:19
 * @Copyright: 2018 www.freemud.cn Inc. All rights reserved.
 * 注意：本内容仅限于上海非码科技内部传阅，禁止外泄以及用于其他的商业目的
 */
@Data
public class WeaponNew {

    private String weaponName; //名称
    private String weaponDescribe;//品质
    private long maxAggressivity;//最大攻击力
    private  long minAggressivity;//最小攻击力

    /**
     * 初始化武器属性
     */
    public WeaponNew(){

        this.weaponName = WeaponUtil.randomaWeaponName();

        this.weaponDescribe = WeaponUtil.randomaWeaponDescribeName(weaponName);

        this.minAggressivity = WeaponUtil.getMinAggressivity(weaponDescribe);

        this.maxAggressivity = WeaponUtil.getMaxAggressivity(weaponDescribe, minAggressivity);
    }

    public void showWeaponInfo(){
        System.out.println("获得武器: "+weaponDescribe + weaponName+"\n");
        System.out.println("武器属性: "+"\n");
        System.out.println("攻击力: "+minAggressivity+"-" + maxAggressivity+"\n");

    }
}