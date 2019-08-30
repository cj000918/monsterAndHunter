/**
 * projectName: mh
 * fileName: HunterDao.java
 * packageName: com.chenjian.dao
 * date: 2019-07-26 17:58
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.dao;

import com.chenjian.entity.DBConnection;
import com.chenjian.entity.HunterNew;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

/**
 * @author: ChenJian
 * @description: 猎人操作数据库
 * @data: 2019-07-26 17:58
 **/
@Component
public class HunterDao extends DBConnection {

    @Value("${jdbc_username}")
    private String USERNAMR;

    @Value("${jdbc_password}")
    private String PASSWORD ;

    @Value("${jdbc_url}")
    private String URL;


    public HashMap<String,Object> queryColums(String tableName){

        HashMap<String,Object> map = new HashMap<>();
        PreparedStatement pstmt =null;
        ResultSet rs =null;
        try {
            con = getConnection(USERNAMR, PASSWORD,URL);
            String sql = "SELECT * FROM user_col_comments WHERE Table_Name='"+tableName+"'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()){

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(con!=null) con.close();
                if(pstmt!=null) pstmt.close();
                if(rs!=null) rs.close();
            }catch (Exception e){

            }
        }

        return map;
    }


    public long addHunterInfo(HunterNew hunter){

        int result = -1;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection(USERNAMR, PASSWORD,URL);
            pstmt = con.prepareStatement(" insert into hunter_info (" +
                    "name,max_life,cur_life,max_attack,min_attack,defend,level,exp,need_exp,agile,hide_rate) " +
                    "values(" +
                    "?,?,?,?,?,?,?,?,?,?,?)"
                    );
            pstmt.setString(1, hunter.getName());
            pstmt.setLong(2, hunter.getMaxLife());
            pstmt.setLong(3, hunter.getCurLife());
            pstmt.setLong(4, hunter.getMaxAttack());
            pstmt.setLong(5, hunter.getMinAttack());
            pstmt.setLong(6, hunter.getDefend());
            pstmt.setLong(7, hunter.getLevel());
            pstmt.setLong(8, hunter.getExp());
            pstmt.setLong(9, hunter.getNeedExp());
            pstmt.setLong(10, hunter.getAgile());
            pstmt.setLong(11, hunter.getHideRate());

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
}