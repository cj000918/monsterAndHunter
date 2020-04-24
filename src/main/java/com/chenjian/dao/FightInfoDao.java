/**
 * projectName: mh
 * fileName: FightInfoDao.java
 * packageName: com.chenjian.dao
 * date: 2019-09-30 16:31
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.dao;

import com.chenjian.entity.DBConnection;
import com.chenjian.entity.FightInfo;
import com.chenjian.entity.HunterNew;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.chenjian.entity.DBConnection.getConnection;


/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-30 16:31
 **/
@Component
public class FightInfoDao extends DBConnection {

    @Value("${jdbc_username}")
    private String USERNAMR;

    @Value("${jdbc_password}")
    private String PASSWORD ;

    @Value("${jdbc_url}")
    private String URL;


    /**
     * 添加fightInfo
     * @param fightInfo
     * @return
     */
    public long addFightInfo(FightInfo fightInfo){

        int result = -1;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection(USERNAMR, PASSWORD,URL);
            pstmt = con.prepareStatement(" insert into fight_info (" +
                    "hunter_id,monster_id,remark,create_time) " +
                    "values(" +
                    "?,?,?,?)"
            );
            pstmt.setString(1, fightInfo.getHunterId());
            pstmt.setString(2, fightInfo.getMonsterId());
            pstmt.setString(3, fightInfo.getRemark());
            pstmt.setTimestamp(4, fightInfo.getCreateTime());

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
     * 根据hunterId查询
     * @param hunterId
     * @return
     */
    public FightInfo getFightInfoHunterId(Long hunterId){

        FightInfo fightInfo = new FightInfo();

        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection(USERNAMR, PASSWORD,URL);
            pstmt = con.prepareStatement(" select * from  fight_info where hunter_id = ?");
            pstmt.setLong(1, hunterId);

            ResultSet  resultSet = pstmt.executeQuery();

            if(resultSet.next()){
                fightInfo.setHunterId(resultSet.getString("hunter_id"));
                fightInfo.setMonsterId(resultSet.getString("monster_id"));
                fightInfo.setRemark(resultSet.getString("remark"));
                fightInfo.setCreateTime(resultSet.getTimestamp("create_time"));
                fightInfo.setId(resultSet.getLong("id"));
            }

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
        return fightInfo;
    }


}