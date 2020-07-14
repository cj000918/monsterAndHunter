package com.chenjian.mapper;

import com.chenjian.entity.HunterNew;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



/**
 * @date 2020-06-24
 */
@Mapper
@Repository
public interface HunterMapper{


    int addHunterInfo(@Param("hunterNew") HunterNew hunterNew);

    HunterNew getHunterInfo(@Param("hunterId") String hunterId);

    HunterNew getHunterByName(@Param("hunterName") String hunterName);
}
