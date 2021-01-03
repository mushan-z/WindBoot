package com.wind.aspect.service.impl;

import com.wind.aspect.service.IAspectTestService;
import org.springframework.stereotype.Service;

/**
 * @author zhoubin
 * @time 2021/1/3 下午 12:32
 */
@Service
public class AspectTestServiceImpl implements IAspectTestService {

    @Override
    public void show(String name) {
        System.out.println(" show : hello "+name);
    }


    public void showOut(String name){
        System.out.println( " showout : no Interface  hello:"+name);
    }
}
