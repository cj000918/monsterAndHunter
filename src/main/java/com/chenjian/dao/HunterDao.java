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
import java.sql.SQLException;
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

    private HunterNew gethunterNewInfo (ResultSet  resultSet) throws SQLException {

        HunterNew hunterNew = new HunterNew();

        if(resultSet.next()){
            hunterNew.setHunterId(resultSet.getString("hunter_id"));
            hunterNew.setName(resultSet.getString("name"));
            hunterNew.setAgile(resultSet.getLong("agile"));

            hunterNew.setCurLife(resultSet.getLong("cur_life"));
            hunterNew.setDefend(resultSet.getLong("defend"));
            hunterNew.setExp(resultSet.getLong("exp"));

            hunterNew.setHideRate(resultSet.getLong("hide_rate"));
            hunterNew.setLevel(resultSet.getLong("level"));

            hunterNew.setMaxAttack(resultSet.getLong("max_attack"));
            hunterNew.setMaxLife(resultSet.getLong("max_life"));
            hunterNew.setMinAttack(resultSet.getLong("min_attack"));

            hunterNew.setNeedExp(resultSet.getLong("need_exp"));
        }

        return hunterNew;
    }


    /**
     * 添加hunter
     * @param hunter
     * @return
     */
    public long addHunterInfo(HunterNew hunter){

        int result = -1;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection(USERNAMR, PASSWORD,URL);
            pstmt = con.prepareStatement(" insert into hunter_info (" +
                    "name,max_life,cur_life,max_attack,min_attack,defend,level,exp,need_exp,agile,hide_rate,hunter_id) " +
                    "values(" +
                    "?,?,?,?,?,?,?,?,?,?,?,?)"
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
            pstmt.setString(12, hunter.getHunterId());

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
    public HunterNew getHunterById(String hunterId){

        HunterNew hunterNew = new HunterNew();

        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection(USERNAMR, PASSWORD,URL);
            pstmt = con.prepareStatement(" select * from  hunter_info where hunter_id = ?");
            pstmt.setString(1, hunterId);

            ResultSet  resultSet = pstmt.executeQuery();

            hunterNew =  gethunterNewInfo(resultSet);

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
        return hunterNew;
    }


    /**
     * 根据hunterName查询
     * @param hunterName
     * @return
     */
    public HunterNew getHunterByName(String hunterName){

        HunterNew hunterNew = new HunterNew();

        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection(USERNAMR, PASSWORD,URL);
            pstmt = con.prepareStatement(" select * from  hunter_info where name like '%"+hunterName+"%'");

            ResultSet  resultSet = pstmt.executeQuery();

            hunterNew =  gethunterNewInfo(resultSet);

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
        return hunterNew;
    }

}