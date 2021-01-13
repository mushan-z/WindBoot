package com.wind.redis;

import com.wind.dataBase.jdbc.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhoubin
 * @time 2021/1/5 上午 10:44
 */
@Api(tags="redis接口")
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "redisTest",notes = "获取用户")
    @GetMapping("/redisTest")
    public Map<String,Object> redisTest(){
        Map<String,Object> map = new HashMap<>();
        redisTemplate.opsForValue().set("bbb","testbbb");
        String value = (String)redisTemplate.opsForValue().get("bbb");
        map.put("bbb",value);
        //通过redisCallBack接口实现
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set("ccc".getBytes(),"testCCC".getBytes());
                String cResult = new String(redisConnection.get("ccc".getBytes()));
                map.put("ccc",cResult);
                return null;
            }
        });
        //通过sessionCallBack接口实现 (高级接口，优先使用)
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("ddd","testddd");
                String dResult = redisOperations.opsForValue().get("ddd").toString();
                map.put("ddd",dResult);
                return null;
            }
        });

        //lambda idea编辑器提示错误，运行没有影响
//        redisTemplate.execute((RedisOperations r)->{
//            r.opsForValue().set("eee","testeee");
//            String eResult = r.opsForValue().get("eee").toString();
//            map.put("eee",eResult);
//            return null;
//        });

        return map;
    }


    @ApiOperation(value = "redisTestStr",notes = "redis中的字符串")
    @GetMapping("/redisTestStr")
    public Map<String,Object> redisTestStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String key="redis:string:"+dateStr;
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
        String date2Str = sdf2.format(new Date());
        String secondKey="redis:string:"+date2Str;
        Map<String,Object> map = new HashMap<>();
        redisTemplate.opsForValue().set(key,"test"+dateStr,3600, TimeUnit.SECONDS);
        String getValue = (String)redisTemplate.opsForValue().get(key);
        map.put("get",getValue);
        String valuePart = redisTemplate.opsForValue().get(key,1,4);
        map.put("valuePart",valuePart);
        boolean absentFlag = redisTemplate.opsForValue().setIfAbsent(key,"isAbsent-day",3600,TimeUnit.SECONDS);
        boolean absentSecondFlag = redisTemplate.opsForValue().setIfAbsent(secondKey,"isAbsent-second",3600,TimeUnit.SECONDS);
        map.put("absentFlag",absentFlag);
        map.put("absentSecondFlag",absentSecondFlag);
        String numberKey = "redis:string:number";
        Long incrementResult = redisTemplate.opsForValue().increment(numberKey,2L);
        map.put("incrementResult",incrementResult);
        Long decrementResult = redisTemplate.opsForValue().decrement(numberKey,1);
        map.put("decrementResult",decrementResult);
        String getSetKey = "redis:String:getSet";
        //存入新值返回旧值
        String getSet = (String)redisTemplate.opsForValue().getAndSet(getSetKey,"getSetValue4");
        map.put("getSet",getSet);
        redisTemplate.opsForValue().append(key,"append");
        String valueAppend = (String)redisTemplate.opsForValue().get(key);
        map.put("valueAppend",valueAppend);
        Long size = redisTemplate.opsForValue().size(key);
        map.put("size",size);
        Map<String,String> multiMap = new HashMap<>();
        multiMap.put("a","aaa");
        multiMap.put("b","bbb");
        multiMap.put("c","ccc");
        redisTemplate.opsForValue().multiSet(multiMap);
        //list初始化
//        List<String> list = Arrays.asList("a","b","c");
        List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
        List<String> multiResult = redisTemplate.opsForValue().multiGet(list);
        map.put("multiResult",multiResult);
        return map;
    }




    @ApiOperation(value = "redisTestList",notes = "redis中的List")
    @GetMapping("/redisTestList")
    public Map<String,Object> redisTestList(){
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String listKey="redis:list:"+dateStr;
        redisTemplate.opsForList().leftPush(listKey,"aaa");
        redisTemplate.opsForList().leftPush(listKey,"bbb");
        redisTemplate.opsForList().leftPush(listKey,"ccc");
        redisTemplate.opsForList().leftPushAll(listKey,"ddd","eee","fff");
        String index3Value = (String)redisTemplate.opsForList().index(listKey,3);
        map.put("index3Value",index3Value);
        List<String> list = redisTemplate.opsForList().range(listKey,0,-1);
        map.put("list",list);
        //移除集合最右边的元素在等待的时间里，如果超过等待的时间仍没有元素则退出
        String rightPop = (String)redisTemplate.opsForList().rightPop(listKey,3,TimeUnit.SECONDS);
        map.put("rightPop",rightPop);
        //存在list时应该是返回list中的元素数量
        long leftPushIfPresent = redisTemplate.opsForList().leftPushIfPresent(listKey,"leftPushIfPresent");
        map.put("leftPushIfPresent",leftPushIfPresent);
        //不存在list返回0
        long leftPushIfPresentNo = redisTemplate.opsForList().leftPushIfPresent("leftPushKey","leftPushIfPresent");
        map.put("leftPushIfPresentNo",leftPushIfPresentNo);
        long size = redisTemplate.opsForList().size(listKey);
        map.put("size",size);
        //向index位置插入给定元素
        redisTemplate.opsForList().set(listKey,2,String.valueOf(System.currentTimeMillis()/1000));
        //截取集合元素长度，保留长度内的数据
        redisTemplate.opsForList().trim(listKey,0,5);
        //把最后一个参数值放到指定集合的第一个出现中间参数的前面，如果中间参数值存在的话
        redisTemplate.opsForList().leftPush(listKey,"ccc","ddd");
        //从存储在键中的列表中删除等于值的元素的第一个计数事件。
        // count> 0：删除等于从左到右移动的值的count个元素；
        // count< 0：删除等于从右到左移动的值的count绝对值个元素；
        // count = 0：删除等于value的所有元素。
        redisTemplate.opsForList().remove(listKey,2,"ddd");
        return map;
    }

    @ApiOperation(value = "redisTestHash",notes = "redis中的Hash")
    @GetMapping("/redisTestHash")
    public Map<String,Object> redisTestHash(){
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String hashKey="redis:hash:"+dateStr;
        redisTemplate.opsForHash().put(hashKey,"a","aaa");
        redisTemplate.opsForHash().put(hashKey,"b","bbb");
        redisTemplate.opsForHash().put(hashKey,"c","ccc");
        String getA = (String)redisTemplate.opsForHash().get(hashKey,"a");
        map.put("getA",getA);
        redisTemplate.opsForHash().delete(hashKey,"b","c");
        redisTemplate.opsForHash().put(hashKey,"d","ddd");
        redisTemplate.opsForHash().put(hashKey,"e","eee");
        Map<String,Object> entries = redisTemplate.opsForHash().entries(hashKey);
        entries.forEach((x,y)->{
            System.out.println("key:"+x+",value:"+y);
        });
        Set<String> keySet = redisTemplate.opsForHash().keys(hashKey);
        map.put("keySet",keySet);
        List<String> valueList = redisTemplate.opsForHash().values(hashKey);
        map.put("valueList",valueList);
        redisTemplate.opsForHash().put(hashKey,"number","1");
        redisTemplate.opsForHash().increment(hashKey,"number",3);
        String increment = (String)redisTemplate.opsForHash().get(hashKey,"number");
        map.put("increment",increment);
        redisTemplate.opsForHash().put(hashKey,"a2","aaa2");
        ScanOptions sop = ScanOptions.scanOptions().match("a*").build();
        ScanOptions sop2 = ScanOptions.scanOptions().match("a*").count(1).build();
        Cursor<Map.Entry<String,String>> cursor = redisTemplate.opsForHash().scan(hashKey,sop2);
        while(cursor.hasNext()){
            Map.Entry<String,String> entry = cursor.next();
            System.out.println("scan entry key:"+entry.getKey()+",value:"+entry.getValue());
        }
        return map;
    }

    @ApiOperation(value = "redisTestSet",notes = "redis中的set")
    @GetMapping("/redisTestSet")
    public Map<String,Object> redisTestSet(){
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String setKey="redis:set:"+dateStr;
        redisTemplate.opsForSet().add(setKey,"a","b","c","d","f");
        Set<String> members = redisTemplate.opsForSet().members(setKey);
        map.put("members",members);



        redisTemplate.opsForSet().add("set2","c","d","e","h");
        redisTemplate.opsForSet().add("set3","d","e","f");
        redisTemplate.opsForSet().add("set3","f","g","h");

        List<String> list=Arrays.asList("set2","set3","set4");
        //把对比的key放在list中,取出第一个key和剩余key的并集不一样的元素
        Set<String> diffSet = redisTemplate.opsForSet().difference(list);
        map.put("diffSet",diffSet);
        //取比setKey和list中所有key里的元素的并集都不一样的值
        Set<String> diffSet2 = redisTemplate.opsForSet().difference(setKey,list);
        map.put("diffSet2",diffSet2);
        return map;
    }


    @ApiOperation(value = "redisTestZSet",notes = "redis中的ZSet")
    @GetMapping("/redisTestZSet")
    public Map<String,Object> redisTestZSet(){
        Map<String,Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String zsetKey="redis:zset:"+dateStr;
        redisTemplate.opsForZSet().add(zsetKey,"a",1);
        redisTemplate.opsForZSet().add(zsetKey,"c",2);
        redisTemplate.opsForZSet().add(zsetKey,"d",3);
        Set<String> zset = redisTemplate.opsForZSet().range(zsetKey,0,-1);
        map.put("zset",zset);
        return map;
    }

}
