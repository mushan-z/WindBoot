package com.wind.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author zhoubin
 * @date 2021-11-24 9:48
 */
@Component
public class WindJobExecutor {

    @XxlJob("testWindJob")
    public void testWindJob() throws Exception {
        System.out.println("out xxl-job");
        XxlJobHelper.log("XXL-JOB, Hello World.");
        //ReturnT
    }


    @XxlJob("testWindJobReturn")
    public ReturnT<String> testWindJobReturn() {
        String param = XxlJobHelper.getJobParam();
        System.out.println("testWindJobReturn param:"+param);
        ReturnT<String> returnT = new ReturnT<>();
        returnT.setCode(200);
        returnT.setContent("this is content");
        returnT.setMsg("this is msg");
        return returnT;
    }

}
