/**
 * projectName: mh
 * fileName: FightWorker.java
 * packageName: com.chenjian.job
 * date: 2019-09-27 19:25
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.job;

import com.chenjian.entity.FightInfo;
import com.chenjian.entity.HunterNew;
import com.chenjian.entity.MonsterNew;
import com.chenjian.enums.MonsterGradeEnums;
import com.chenjian.others.SpringContextHolder;
import com.chenjian.service.FightInfoService;
import com.chenjian.service.HunterService;
import com.chenjian.service.MonsterService;
import com.chenjian.util.GameUtil;
import com.chenjian.util.JobUtil;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-27 19:25
 **/

public class FightWorker extends JobWorker{

    private HunterService hunterService = (HunterService) SpringContextHolder.getBean(HunterService.class);

    private MonsterService monsterService = (MonsterService) SpringContextHolder.getBean(MonsterService.class);

    private FightInfoService fightInfoService = (FightInfoService) SpringContextHolder.getBean(FightInfoService.class);

    @Override
    public Map<String, String> work(Object data) {

        Map<String, String> returnMap = new HashMap<>();
        HashMap<String, Object> conditionMap = (HashMap<String, Object>) data;
        if(conditionMap == null || conditionMap.size() < 1){

            return returnMap;
        }

        HunterNew hunterNew = (HunterNew) conditionMap.get("hunterNew");
        MonsterNew monsterNew = (MonsterNew) conditionMap.get("monsterNew");
        Boolean isFirst = (Boolean)conditionMap.get("isFirst");

        injured(hunterNew, monsterNew, isFirst);

        return returnMap;
    }



    /**
     * 扣血
     * @param monster
     */
    public void injured(HunterNew hunterNew , MonsterNew monster, Boolean isFirst) {

        String firstCode = null;

        Map<String, Object> fightMap = firstFighting(hunterNew, monster, isFirst);
        firstCode = String.valueOf(fightMap.get("code"));
        HashMap<String,Object> map = new HashMap<>();

        //301  表示需要重新生成一个monster
        if(firstCode.equals("301")){

            MonsterNew nextMonster = monsterService.addMonsterByHunter(hunterNew);
            map.put("hunterNew", hunterNew);
            map.put("monsterNew", nextMonster);
            map.put("isFirst", true);

        }else if (firstCode.equals("300")){

            map.put("hunterNew", hunterNew);
            map.put("monsterNew", monster);
            map.put("isFirst", false);
        }
        JobUtil.post(map, new FightWorker());
    }

    /**
     * 碰面战斗
     * @param hunterNew
     * @param monster
     * @return
     */
    private Map<String, Object> firstFighting(HunterNew hunterNew , MonsterNew monster, Boolean isFirst){

        long lostLife = 0;
        long curLife = 0;

        String remark = null;
        Map<String, Object> returnMap = new HashMap<>();
        Boolean hiddenResult = GameUtil.hidden(hunterNew.getHideRate(), monster.getHideRate());

        //判断谁先手， 如果为flase则monster先手
        if ( !hiddenResult) {

            //如果是第一次进行战斗， 则提示信息不同
            if(isFirst){

                System.out.println(" 遭遇敌人 , " + monster.getTitle());

                remark = " 遭遇敌人 , " + monster.getTitle();

                addFightInfo(hunterNew,monster, remark);

                System.out.println("【" + hunterNew.getName() + "】" + " 还没反应过来， 就遭到了 【" + monster.getName() + "】的攻击" + "\r\n");

                remark = "【" + hunterNew.getName() + "】" + " 还没反应过来， 就遭到了 【" + monster.getName() + "】的攻击" + "\r\n";
                addFightInfo(hunterNew,monster, remark);

            }

            curLife = fightMonster(hunterNew, monster);

            //hunter死亡
            if(curLife < 1){
                curLife = 0;

                hunterService.died(hunterNew);

                remark = "【"+hunterNew.getName()+"】"+" 被怪物咬死了 , 头都不见了, 惨不忍睹..."+"\r\n";
                addFightInfo(hunterNew,monster, remark);

                remark = "【"+hunterNew.getName()+"】"+" , 等级"+hunterNew.getLevel()+" ,血量"+hunterNew.getCurLife()+" ,攻击力"+hunterNew.getMinAttack()+"-"+hunterNew.getMaxAttack()+" ,防御力"+hunterNew.getDefend()+" ,当前经验: "+hunterNew.getExp()+"/"+hunterNew.getNeedExp()+"\r\n";
                addFightInfo(hunterNew,monster, remark);

                returnMap.put("code", 200);
                returnMap.put("curLife", curLife);
                returnMap.put("msg", "hunter死亡， 游戏结束");
                return returnMap;
            }
            hunterService.showHunterInfo(hunterNew);

            remark = "【"+hunterNew.getName()+"】"+" , 等级"+hunterNew.getLevel()+" ,血量"+hunterNew.getCurLife()+" ,攻击力"+hunterNew.getMinAttack()+"-"+hunterNew.getMaxAttack()+" ,防御力"+hunterNew.getDefend()+" ,当前经验: "+hunterNew.getExp()+"/"+hunterNew.getNeedExp()+"\r\n";
            addFightInfo(hunterNew,monster, remark);
        }

        curLife = fightHunter(hunterNew, monster);

        //monster扣血， 并判断是否死亡
        if(curLife < 1){

            curLife = 0;

            long exp = monsterService.died(monster);

            remark = "【"+monster.getTitle()+"】"+" 被砍的四分五裂了 ."+"\r\n";
            addFightInfo(hunterNew,monster, remark);

            System.out.println("【"+hunterNew.getName()+"】" + " 增加经验:  "+monster.getMaxLife()+"\r\n");

            remark = "【"+hunterNew.getName()+"】" + " 增加经验:  "+monster.getMaxLife()+"\r\n";
            addFightInfo(hunterNew,monster, remark);
            hunterService.expAdd(hunterNew, exp);

            returnMap.put("code", 301);
            returnMap.put("curLife", curLife);
            returnMap.put("msg", "monster死亡， 进行下一轮战斗");

        }else{
            returnMap.put("code", 300);
            returnMap.put("monster", monster);
            returnMap.put("hunter", hunterNew);
            returnMap.put("curLife", curLife);
            returnMap.put("msg", "继续战斗");
        }

        return returnMap;
    }

    //增加战斗信息
    private Long addFightInfo(HunterNew hunterNew, MonsterNew monsterNew, String remark){

        FightInfo fightInfo = new FightInfo(hunterNew.getHunterId(), monsterNew.getMonsterId(), remark, new Timestamp(System.currentTimeMillis()));

        return fightInfoService.addFightInfo(fightInfo);

    }


    //monster攻击，hunter防守
    private Long fightMonster(HunterNew hunterNew, MonsterNew monsterNew){

        //hunter剩余生命值
        long curLife = 0;
        //hunter扣减的生命值
        long lostLife = 0;
        String remark = null;

        lostLife = GameUtil.calLostLife(monsterNew.getMaxAttack(), monsterNew.getMinAttack(), hunterNew.getDefend());

        System.out.println("【"+monsterNew.getTitle()+"】"+" 冲上去咬了 "+"【"+hunterNew.getName()+"】"+"一大口"+"\r\n");

        remark = "【"+monsterNew.getTitle()+"】"+" 冲上去咬了 "+"【"+hunterNew.getName()+"】"+"一大口"+"\r\n";
        addFightInfo(hunterNew,monsterNew, remark);

        if(lostLife > 0){

            if(monsterNew.getGrade() == MonsterGradeEnums.SHI_XUE.getShowName()){

                System.out.println("【"+hunterNew.getName()+"】"+":啊啊啊, 巨疼.....嗯? 这怪物还吸血?!!!"+"\r\n");

                remark = "【"+hunterNew.getName()+"】"+":啊啊啊, 巨疼.....嗯? 这怪物还吸血?!!!"+"\r\n";
                addFightInfo(hunterNew,monsterNew, remark);

                //怪物加血
                long upLife =  monsterNew.getCurLife() + lostLife;
                monsterNew.setCurLife(upLife);

            }else{
                System.out.println("【"+hunterNew.getName()+"】"+":疼疼疼疼疼疼疼疼......"+"\r\n");

                remark = "【"+hunterNew.getName()+"】"+":疼疼疼疼疼疼疼疼......"+"\r\n";
                addFightInfo(hunterNew,monsterNew, remark);
            }

            //hunter扣血， 并判断血量是否小于1导致死亡
            curLife = hunterNew.getCurLife() - lostLife;
            hunterNew.setCurLife(curLife);

            System.out.println("【"+hunterNew.getName()+"】"+" 血量: -"+lostLife+"\r\n");

            remark = "【"+hunterNew.getName()+"】"+" 血量: -"+lostLife+"\r\n";
            addFightInfo(hunterNew,monsterNew, remark);

            //hunter死亡
            if(curLife < 1){
                curLife = 0;
            }

        }else{
            System.out.println("【"+hunterNew.getName()+"】"+" 机智的躲过了攻击"+"\r\n");

            remark = "【"+hunterNew.getName()+"】"+" 机智的躲过了攻击"+"\r\n";
            addFightInfo(hunterNew,monsterNew, remark);
        }

        return curLife;
    }

    //hunter攻击，monster防守
    private Long fightHunter(HunterNew hunterNew, MonsterNew monsterNew){
        System.out.println("测试");
        //monster剩余生命值
        long curLife = 0;
        //monster扣减的生命值
        long lostLife = 0;
        String remark = null;

        System.out.println("【"+hunterNew.getName()+"】"+"面无表情杀向"+"【"+monsterNew.getTitle()+"】");

        remark = "【"+hunterNew.getName()+"】"+"面无表情杀向"+"【"+monsterNew.getTitle()+"】";
        addFightInfo(hunterNew,monsterNew, remark);

        //hunter攻击，对monster进行闪避=判断
        if(GameUtil.hidden(monsterNew.getHideRate())){

            System.out.println("【"+monsterNew.getTitle()+"】"+" 躲过了 "+"【"+ hunterNew.getName()+"】"+"的攻击"+"\r\n");

            remark = "【"+monsterNew.getTitle()+"】"+" 躲过了 "+"【"+ hunterNew.getName()+"】"+"的攻击"+"\r\n";
            addFightInfo(hunterNew,monsterNew, remark);
        }else{

            lostLife = GameUtil.calLostLife(hunterNew.getMaxAttack(), hunterNew.getMinAttack(), monsterNew.getDefend());

            curLife = monsterNew.getCurLife() - lostLife;
            monsterNew.setCurLife(curLife);
            System.out.println("【"+monsterNew.getTitle()+"】"+" 受到攻击 "+"\r\n");

            remark = "【"+monsterNew.getTitle()+"】"+" 受到攻击 "+"\r\n";
            addFightInfo(hunterNew,monsterNew, remark);

            System.out.println("【"+monsterNew.getTitle()+"】"+" 血量: -"+lostLife+"\r\n");

            remark = "【"+monsterNew.getTitle()+"】"+" 血量: -"+lostLife+"\r\n";
            addFightInfo(hunterNew,monsterNew, remark);

            //monster扣血， 并判断是否死亡
            if(curLife < 1){

                curLife = 0;
            }
        }
        return curLife;

    }


}