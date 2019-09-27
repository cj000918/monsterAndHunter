/**
 * projectName: mh
 * fileName: FightServiceImpl.java
 * packageName: com.chenjian.service.Impl
 * date: 2019-09-13 11:58
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.service.Impl;

import com.chenjian.entity.HunterNew;
import com.chenjian.entity.MonsterNew;
import com.chenjian.enums.MonsterGradeEnums;
import com.chenjian.service.FightService;
import com.chenjian.service.HunterService;
import com.chenjian.service.MonsterService;
import com.chenjian.util.GameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-13 11:58
 **/
@Service
public class FightServiceImpl implements FightService {

    @Autowired
    private HunterService hunterService;

    @Autowired
    private MonsterService monsterService;

    @Override
    @Async
    public String fightings(HunterNew hunterNew, MonsterNew... monsterNews) {

        if(monsterNews == null || monsterNews.length < 1){
            return null;
        }

        for (int i = 0; i < monsterNews.length; i++){
            MonsterNew monsterNew = monsterNews[i];
            this.hunterAndMonsterFighting(hunterNew, monsterNew);
        }
        return "成功";
    }


    /**
     * hunter死亡则返回false
     * @return
     */
    private Boolean hunterAndMonsterFighting(HunterNew hunterNew, MonsterNew monsterNew){

        Boolean fightsResult = false;

        String fightStr = "主角"+"【"+hunterNew.getName()+"】"+"已经牺牲了";

        if(hunterNew.getIsLive() == 0 && monsterNew.getIsLive() == 0) {

             injured(hunterNew, monsterNew);

        }else{

            fightStr ="拜托啊！这个丧尸已经被你打死啦！";
        }

        return fightsResult;
    }

    /**
     * 扣血
     * @param monster
     */
    public void injured(HunterNew hunterNew , MonsterNew monster) {

        Boolean hiddenResult = GameUtil.hidden(hunterNew.getHideRate(), monster.getHideRate());
        long lostLife = 0;
        long curLife = 0;

        System.out.println(" 遭遇敌人 , " + monster.getTitle());

        //判断谁先手， 如果为flase则monster先手
        if (!hiddenResult) {

            System.out.println("【" + hunterNew.getName() + "】" + " 还没反应过来， 就遭到了 【" + monster.getName() + "】的攻击" + "\r\n");
            System.out.println("【"+monster.getTitle()+"】"+" 冲上去咬了 "+"【"+hunterNew.getName()+"】"+"一大口"+"\r\n");

            lostLife = GameUtil.calLostLife(monster.getMaxAttack(), monster.getMinAttack(), hunterNew.getDefend());

            if(lostLife > 0){

                if(monster.getGrade() == MonsterGradeEnums.SHI_XUE.getShowName()){

                    System.out.println("【"+hunterNew.getName()+"】"+":啊啊啊, 巨疼.....嗯? 这怪物还吸血?!!!"+"\r\n");

                    //怪物加血
                    long upLife =  monster.getCurLife() + lostLife;
                    monster.setCurLife(upLife);

                }else{
                    System.out.println("【"+hunterNew.getName()+"】"+":疼疼疼疼疼疼疼疼......"+"\r\n");
                    System.out.println("【"+hunterNew.getName()+"】"+" 血量: -"+lostLife+"\r\n");
                }

                //hunter扣血， 并判断血量是否小于1导致死亡
                curLife = hunterNew.getCurLife() - lostLife;
                if(curLife < 1){
                    curLife = 0;
                    hunterService.died(hunterNew);
                    return;
                }
            }else{
                System.out.println("【"+hunterNew.getName()+"】"+" 机智的躲过了攻击"+"\r\n");
            }
        }

        System.out.println("【"+hunterNew.getName()+"】"+"面无表情杀向"+"【"+monster.getTitle()+"】");

        //hunter攻击，对monster进行闪避=判断
        if(GameUtil.hidden(monster.getHideRate())){

            System.out.println("【"+monster.getTitle()+"】"+" 躲过了 "+"【"+ hunterNew.getName()+"】"+"的攻击"+"\r\n");

        }else{

            lostLife = GameUtil.calLostLife(hunterNew.getMaxAttack(), hunterNew.getMinAttack(), monster.getDefend());

            curLife = monster.getCurLife() - lostLife;

            System.out.println("【"+monster.getTitle()+"】"+" 受到攻击 "+"\r\n");
            System.out.println("【"+monster.getTitle()+"】"+" 血量: -"+lostLife+"\r\n");

            if(curLife < 1){

                curLife = 0;

                long exp = monsterService.died(monster);

                System.out.println("【"+hunterNew.getName()+"】" + " 增加经验:  "+monster.getMaxLife()+"\r\n");

                hunterService.expAdd(hunterNew, exp);

                return;

            }
        }

        System.out.println("【"+monster.getTitle()+"】"+" 冲上去咬了 "+"【"+hunterNew.getName()+"】"+"一大口"+"\r\n");

        //monster进行攻击， 对hunter进行闪避判断
        if(GameUtil.hidden(hunterNew.getHideRate())){

            System.out.println("【"+hunterNew.getName()+"】"+" 机智的躲过了攻击 "+"\r\n");

        }else{

            lostLife = GameUtil.calLostLife(monster.getMaxAttack(), monster.getMinAttack(), hunterNew.getDefend());

            curLife = hunterNew.getCurLife() - lostLife;

            System.out.println("【"+hunterNew.getName()+"】"+" 受到攻击 "+"\r\n");
            System.out.println("【"+hunterNew.getName()+"】"+" 血量: -"+lostLife+"\r\n");

            if(curLife < 1){

                curLife = 0;
                hunterService.died(hunterNew);
                return;
            }
        }
    }

}