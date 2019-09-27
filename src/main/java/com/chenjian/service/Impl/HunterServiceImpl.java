package com.chenjian.service.Impl;

import com.chenjian.dao.HunterDao;
import com.chenjian.entity.HunterNew;
import com.chenjian.service.HunterService;
import com.chenjian.util.SnowflakeIdWorker;
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

        long snowFlake= SnowflakeIdWorker.getSnowFlakeId();
        hunterNew.setHunterId(snowFlake);
        long result  = hunterDao.addHunterInfo(hunterNew);

        if(result > 0){
            result = snowFlake;
        }
        return result;
    }

    @Override
    public HunterNew getHunterNewById(Long hunterId) {
        return hunterDao.getHunterById(hunterId);
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
        hunterNew.setIsLive(0);
        showHunterInfo(hunterNew);
    }


    /**
     * 增加经验
     * @param exp
     */
    public void expAdd(HunterNew hunterNew, long exp){

        //存活的hunter才需要计算经验
        if(hunterNew.getIsLive() == 1 && hunterNew.getCurLife() > 0){
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
