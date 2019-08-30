package com.chenjian.service.Impl;

import com.chenjian.dao.HunterDao;
import com.chenjian.entity.Hunter;
import com.chenjian.entity.HunterNew;
import com.chenjian.service.HunterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName HunterServiceImpl
 * @Description
 * @Author chenjian
 * @Date 2019/2/27 11:51
 **/
@Service
public class HunterServiceImpl implements HunterService {

    @Autowired
    private HunterDao hunterDao;

    @Override
    public long addHunter(HunterNew hunterNew) {
        return hunterDao.addHunterInfo(hunterNew);
    }

}
