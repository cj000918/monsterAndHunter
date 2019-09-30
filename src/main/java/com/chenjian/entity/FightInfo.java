/**
 * projectName: mh
 * fileName: FightInfo.java
 * packageName: com.chenjian.entity
 * date: 2019-09-30 16:28
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-30 16:28
 **/
@Data
public class FightInfo {

//  `id` int(255) NOT NULL AUTO_INCREMENT,
//  `hunter_id` bigint(200) DEFAULT NULL,
//  `monster_id` bigint(200) DEFAULT NULL,
//  `remark` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
//  `create_time` timestamp NULL DEFAULT NULL,

    private Long id;
    private Long hunterId;
    private Long monsterId;
    private String remark;
    private Timestamp createTime;

    public FightInfo(){}

    public FightInfo(Long hunterId, Long monsterId, String remark, Timestamp createTime){

        this.hunterId = hunterId;
        this.monsterId = monsterId;
        this.remark = remark;

        if(createTime == null){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            this.createTime = timestamp;
        }
    }

}