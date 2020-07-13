package com.chenjian.mapper;

import com.chenjian.entity.HunterNew;
import org.apache.ibatis.annotations.Param;

/**
 * @date 2020-06-24
 */
public interface HunterMapper {


    int addHunterInfo(@Param("hunterNew") HunterNew hunterNew);

    HunterNew getHunterInfo(@Param("hunterId") Long hunterId);
}
