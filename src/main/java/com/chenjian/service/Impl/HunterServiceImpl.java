package com.chenjian.service.Impl;

import com.chenjian.entity.base.HunterNew;
import com.chenjian.mapper.HunterMapper;
import com.chenjian.service.HunterService;
import com.chenjian.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName HunterServiceImpl
 * @Description
 * @Author chenjian
 * @Date 2019/2/27 11:51
 **/
@Service
public class HunterServiceImpl implements HunterService {

//    @Autowired
//    private HunterDao hunterDao;

    @Resource
    private HunterMapper hunterMapper;

    @Override
    public Long addHunter(HunterNew hunterNew) {

        Long DBResult = null;

        long snowFlake= SnowflakeIdWorker.getSnowFlakeId();

        hunterNew.setHunterId(snowFlake);
        long result  = hunterMapper.addHunterInfo(hunterNew);

        if(result > 0){
            DBResult = snowFlake;
        }
        return DBResult;
    }

    @Override
    public HunterNew getHunterNewById(Long hunterId) {
        return hunterMapper.getHunterInfo(hunterId);
    }

    @Override
    public HunterNew getHunterNewByName(String hunterName) {
        return hunterMapper.getHunterByName(hunterName);
    }


    /**
     * 展示信息
     */
    @Override
    public void showHunterInfo(HunterNew hunterNew){
        System.out.println("【"+hunterNew.getName()+"】"+" , 等级"+hunterNew.getLevel()+" ,血量"+hunterNew.getCurLife()+" ,攻击力"+hunterNew.getMinAttack()+"-"+hunterNew.getMaxAttack()+" ,防御力"+hunterNew.getDefend()+" ,当前经验: "+hunterNew.getExp()+"/"+hunterNew.getNeedExp()+"\r\n");
    }

    /**
     * 死亡
     */
    @Override
    public void died(HunterNew hunterNew){

        System.out.println("【"+hunterNew.getName()+"】"+" 被怪物咬死了 , 头都不见了, 惨不忍睹..."+"\r\n");
        hunterNew.setCurLife(0L);
        hunterNew.setIsLive(1);
        showHunterInfo(hunterNew);
    }


    /**
     * 增加经验
     * @param exp
     */
    @Override
    public void expAdd(HunterNew hunterNew, long exp){

        //存活的hunter才需要计算经验
        if(hunterNew.getIsLive() == 0 && hunterNew.getCurLife() > 0){
            //计算升级需要的经验
            if(hunterNew.getExp() >= hunterNew.getNeedExp()){
                long needExp = hunterNew.getNeedExp();
                for(int i = 1 ; i <= hunterNew.getLevel(); i++){
                    needExp+=i*100;
                }
                hunterNew.setNeedExp(needExp);
            }
        }

        long needExp = hunterNew.getNeedExp();
        long nowExp = hunterNew.getExp();

        nowExp = nowExp + exp;
        hunterNew.setExp(nowExp);
        if( nowExp >= needExp){
            upgrade(hunterNew);
        }else{
            showHunterInfo(hunterNew);
        }
    }



    /**
     * 升级
     */
    public void upgrade(HunterNew hunterNew){

        hunterNew.setMaxAttack(hunterNew.getMaxAttack() + 8);
        hunterNew.setMinAttack(hunterNew.getMinAttack() + 5);
        hunterNew.setLevel(hunterNew.getLevel() + 1);
        hunterNew.setDefend(hunterNew.getDefend() + 3);
        hunterNew.setMaxLife(hunterNew.getMaxLife() + 10);
        hunterNew.setCurLife(hunterNew.getMaxLife());

        System.out.println("-----------------------等级提升----------------------------"+"\r\n");
        System.out.println("系统提示：升级啦!"+"\r\n");

        showHunterInfo(hunterNew);
    }

}
