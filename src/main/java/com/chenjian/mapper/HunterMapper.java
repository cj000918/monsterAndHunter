package com.chenjian.mapper;

import com.chenjian.entity.FightInfo;
import com.chenjian.entity.HunterNew;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @date 2020-06-24
 */
@Mapper
public interface HunterMapper{


    int addHunterInfo(@Param("hunterNew") HunterNew hunterNew);

    HunterNew getHunterInfo(@Param("hunterId") Long hunterId);

    HunterNew getHunterByName(@Param("hunterName") String hunterName);

    List<FightInfo> getFightInfoList(@Param("hunterId") Long hunterName);
}
