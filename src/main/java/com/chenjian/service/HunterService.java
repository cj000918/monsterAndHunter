package com.chenjian.service;

import com.chenjian.entity.HunterNew;

/**
 * @ClassName HunterService
 * @Description
 * @Author chenjian
 * @Date 2019/2/27 11:51
 **/
public interface HunterService {

    Long addHunter(HunterNew hunterNew);

    HunterNew getHunterNewById(Long hunterId);

    HunterNew getHunterNewByName(String hunterName);

    void died(HunterNew hunterNew);

    void showHunterInfo(HunterNew hunterNew);

    void expAdd(HunterNew hunterNew, long exp);
}
