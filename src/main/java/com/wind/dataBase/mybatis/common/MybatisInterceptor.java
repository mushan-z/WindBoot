package com.wind.dataBase.mybatis.common;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * @author zhoubin
 * @time 2020/12/31 下午 5:19
 */
@Component
@Intercepts({@Signature(type = Executor.class,method = "update",args={MappedStatement.class, Object.class})})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] argArr = invocation.getArgs();
        MappedStatement mst = (MappedStatement) argArr[0];
        SqlCommandType commandType = mst.getSqlCommandType();
        if((SqlCommandType.UPDATE).equals(commandType) || (SqlCommandType.INSERT).equals(commandType)){
            Object parameter = argArr[1];
            Field[] fields = parameter.getClass().getDeclaredFields();
            for(Field f:fields){
                if("createDate".equals(f.getName())){
                    f.setAccessible(true);
                    f.set(parameter,new Date());
                    f.setAccessible(false);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
