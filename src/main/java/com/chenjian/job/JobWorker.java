/**
 * projectName: mh
 * fileName: JobWorker.java
 * packageName: com.chenjian.job
 * date: 2019-09-27 19:15
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.job;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-27 19:15
 **/
public abstract class JobWorker implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public JobWorker() {
    }

    public JobWorker(Object data) {
        this.data = data;
    }

    public abstract Map<String, String> work(Object data);

    @Override
    public void run() {
        try {

            long s = System.currentTimeMillis();

            Map<String, String> msg = work(data);

            logger.info("记录 cost : " + (System.currentTimeMillis() - s) + " mills.");
            logger.info("打印日志 : " + JSON.toJSONString(msg));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}