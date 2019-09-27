/**
 * projectName: mh
 * fileName: JobUtil.java
 * packageName: com.chenjian.util
 * date: 2019-09-27 19:14
 * copyright(c) 2019 http://www.hydee.cn/ Inc. All rights reserved.
 */
package com.chenjian.util;

import com.chenjian.job.JobWorker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: ChenJian
 * @description:
 * @data: 2019-09-27 19:14
 **/
public class JobUtil {

    private static ExecutorService incrExecutor = Executors.newSingleThreadExecutor();

    //把任务丢进线程池
    public static void post(Object data, JobWorker syncWorker) {
        syncWorker.setData(data);
        incrExecutor.submit(syncWorker);
    }
}