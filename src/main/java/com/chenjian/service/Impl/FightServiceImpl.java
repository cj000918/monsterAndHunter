/**
 * projectName: mh
 * fileName: FightServiceImpl.java
 * packageName: com.chenjian.service.Impl
 * date: 2019-09-13 11:58
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.service.Impl;

import com.chenjian.dao.FightInfoDao;
import com.chenjian.entity.FightInfo;
import com.chenjian.entity.HunterNew;
import com.chenjian.entity.MonsterNew;
import com.chenjian.job.FightWorker;
import com.chenjian.mapper.HunterMapper;
import com.chenjian.service.FightInfoService;
import com.chenjian.util.JobUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-13 11:58
 **/
@Service
public class FightServiceImpl implements FightInfoService {

    @Autowired
     private FightInfoDao fightInfoDao;

    @Resource
    private HunterMapper hunterMapper;

    @Override
    @Async
    public String fightings(HunterNew hunterNew, MonsterNew monsterNew) {

        if(monsterNew == null || monsterNew.getCurLife() < 1){
            return null;
        }
        this.hunterAndMonsterFighting(hunterNew, monsterNew);
        return "成功";
    }

    @Override
    public Long addFightInfo(FightInfo fightInfo) {
        return fightInfoDao.addFightInfo(fightInfo);
    }

    @Override
    public List<FightInfo> getFightInfo(Long hunterId) {
        return fightInfoDao.getFightInfoHunterId(hunterId, null);
    }

    @Override
    public List<FightInfo> getFightInfoList(Long hunterId) {
        return null;
    }


    /**
     * hunter死亡则返回false
     * @return
     */
    private Boolean hunterAndMonsterFighting(HunterNew hunterNew, MonsterNew monsterNew){

        Boolean fightsResult = false;

        String fightStr = "主角"+"【"+hunterNew.getName()+"】"+"已经牺牲了";

        if(hunterNew.getIsLive() == 0 && monsterNew.getIsLive() == 0) {

//             injured(hunterNew, monsterNew, true);
            HashMap<String,Object> map = new HashMap<>();
            map.put("hunterNew", hunterNew);
            map.put("monsterNew", monsterNew);
            map.put("isFirst", true);
            JobUtil.post(map, new FightWorker());
        }else{

            fightStr ="拜托啊！这个丧尸已经被你打死啦！";
        }

        return fightsResult;
    }









}