/**
 * projectName: mh
 * fileName: MonsterDao.java
 * packageName: com.chenjian.dao
 * date: 2019-09-13 11:00
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.dao;

import com.chenjian.entity.DBConnection;
import com.chenjian.entity.MonsterNew;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-13 11:00
 **/
@Component
public class MonsterDao extends DBConnection {

    @Value("${jdbc_username}")
    private String USERNAMR;

    @Value("${jdbc_password}")
    private String PASSWORD ;

    @Value("${jdbc_url}")
    private String URL;

    /**
     * 添加monster
     * @param monsterNew
     * @return
     */
    public long addMonsterInfo(MonsterNew monsterNew){

        int result = -1;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection(USERNAMR, PASSWORD,URL);
            pstmt = con.prepareStatement(" insert into monster_info (" +
                    "monster_id,name,grade,profession,max_life,cur_life,max_attack,min_attack,defend,agile,hide_rate) " +
                    "values(" +
                    "?,?,?,?,?,?,?,?,?,?,?)"
            );
            pstmt.setLong(1, monsterNew.getMonsterId());
            pstmt.setString(2, monsterNew.getName());
            pstmt.setString(3, monsterNew.getGrade());
            pstmt.setString(4, monsterNew.getProfession());
            pstmt.setLong(5, monsterNew.getMaxAttack());
            pstmt.setLong(6, monsterNew.getCurLife());
            pstmt.setLong(7, monsterNew.getMaxAttack());
            pstmt.setLong(8, monsterNew.getMinAttack());
            pstmt.setLong(9, monsterNew.getDefend());
            pstmt.setLong(10, monsterNew.getAgile());
            pstmt.setLong(11, monsterNew.getHideRate());

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(con!=null) con.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            }catch (Exception e){

            }
        }
        return result;
    }

    /**
     * 根据monsterId查询
     * @param monsterId
     * @return
     */
    public MonsterNew getMonsterById(Long monsterId){

        MonsterNew monsterNew = new MonsterNew();

        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection(USERNAMR, PASSWORD,URL);
            pstmt = con.prepareStatement(" select * from  monster_info where monster_id = ?");
            pstmt.setLong(1, monsterId);

            ResultSet  resultSet = pstmt.executeQuery();

            monsterNew.setMonsterId(resultSet.getLong("monster_id"));
            monsterNew.setName(resultSet.getString("name"));
            monsterNew.setAgile(resultSet.getLong("agile"));

            monsterNew.setCurLife(resultSet.getLong("cur_life"));
            monsterNew.setDefend(resultSet.getLong("defend"));
            monsterNew.setHideRate(resultSet.getLong("hide_rate"));

            monsterNew.setMaxAttack(resultSet.getLong("max_attack"));
            monsterNew.setMaxLife(resultSet.getLong("max_life"));
            monsterNew.setMinAttack(resultSet.getLong("min_attack"));

            monsterNew.setGrade(resultSet.getString("grade"));
            monsterNew.setProfession(resultSet.getString("profession"));

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(con!=null) con.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            }catch (Exception e){

            }
        }
        return monsterNew;
    }


}