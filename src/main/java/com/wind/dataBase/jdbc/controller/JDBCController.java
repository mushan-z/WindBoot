package com.wind.dataBase.jdbc.controller;

import com.wind.dataBase.jdbc.entity.User;
import com.wind.dataBase.jdbc.mapper.UserRowMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoubin
 * @time 2020/12/27 上午 11:51
 */
@Api(tags="jdbc接口")
@RestController
@RequestMapping("/jdbc")
public class JDBCController {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private UserRowMapper userRowMapper;

    @ApiOperation(value = "getUser",notes = "获取用户")
    @GetMapping("/getUser")
    public User getUser(@ApiParam(name = "id",value = "id") @RequestParam(name="id",value = "id") Long id){
        String sql = "select * from user where id = %d ";
        sql = String.format(sql,id);
        User user = jdbcTemplate.queryForObject(sql,userRowMapper);
        return user;
    }

    @ApiOperation(value = "getUserMap",notes = "获取用户Map")
    @GetMapping("/getUserMap")
    public Map<String,Object> getUserMap(@ApiParam(name = "id",value = "id") @RequestParam(name="id",value = "id")Long id){
        String sql = "select * from user where id = %d ";
        sql = String.format(sql, id);
        Map<String,Object> userMap = jdbcTemplate.queryForMap(sql);
        return userMap;
    }
    @ApiOperation(value = "addUser",notes = "增加用户")
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        String sql="insert into user (name,age,create_date) values (?,?,?)";
        jdbcTemplate.update(sql,user.getName(),user.getAge(),new Date());
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = sdf.format(new Date());
        sql = "insert into user (name,age,create_date) values ('%s',%d,'%s')";
        sql = String.format(sql,user.getName()+"2",user.getAge(),nowStr);
        jdbcTemplate.execute(sql);
        return "成功";
    }

    @ApiOperation(value = "updateUser",notes = "修改用户")
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        String sql="update user set name='%s',age=%d where id=%d";
        sql= String.format(sql,user.getName(),user.getAge(),user.getId());
        jdbcTemplate.update(sql);
        return "成功";
    }

    @ApiOperation(value = "deleteUserById",notes = "删除用户")
    @GetMapping("/deleteUserById")
    public String deleteUserById(@ApiParam(name = "id",value = "id") @RequestParam(name="id",value = "id") Long id){
        String sql="delete from user where id=%d";
        sql= String.format(sql,id);
        jdbcTemplate.update(sql);
        return "成功";
    }


    @ApiOperation(value = "searchEdit",notes = "查询修改用户")
    @GetMapping("/searchEdit")
    public String searchEdit(@ApiParam(name = "id",value = "id") @RequestParam(name="id",value = "id") Long id){
        Connection conn;
        PreparedStatement ps;
        try{
            String sql="";
            if(id!=null){
                sql="select * from user where id=%d";
            }else{
                sql="select * from user";
            }

            conn=jdbcTemplate.getDataSource().getConnection();
            sql = String.format(sql,id);

            ps = conn.prepareStatement(sql);
            ResultSet result=ps.executeQuery();
            while(result.next()){
                String name=result.getString("name");
                String updateSql = "update user set age=100 where name='"+name+"'";
                ps=conn.prepareStatement(updateSql);
                ps.execute();
            }
            result.close();
            ps.close();
            conn.close();
            return "成功";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "成功";
    }

    @ApiOperation(value = "searchEdit2",notes = "查询修改用户")
    @GetMapping("/searchEdit2")
    public String searchEdit2(@ApiParam(name = "id",value = "id") @RequestParam(name="id",value = "id") Long id){

        Map<String,Object> result = new HashMap<>();
        try{
            String sql="select * from user where id="+id;
            //ConnectionCallBack
            jdbcTemplate.execute((Connection conn)->{
                String selectSql="select * from user where id=?";
                PreparedStatement pst = conn.prepareStatement(selectSql);
                pst.setLong(1,id);
                ResultSet rs = pst.executeQuery();
                User user = null;
                while(rs.next()){
                    int rowNum = rs.getRow();
                    user = userRowMapper.mapRow(rs,rowNum);
                }
                rs.close();
                pst.close();
                if(user!=null){
                    String sql2 = "update user set age=88 where id=?";
                    PreparedStatement pst2=conn.prepareStatement(sql2);
                    pst2.setLong(1,id);
                    pst2.execute();
                    pst2.close();
                }
                conn.close();
                return null;
            });

        }catch(Exception e){
            e.printStackTrace();
        }
        return "成功";
    }


    @ApiOperation(value = "search",notes = "查询用户")
    @GetMapping("/search")
    public Map<String,Object> search(@ApiParam(name = "id",value = "id") @RequestParam(name="id",value = "id") Long id){

        Map<String,Object> result = new HashMap<>();
        try{
            String sql="select count(0) as total from user";
            //StatementCallBack
            result = jdbcTemplate.execute((Statement st)->{
                Map<String,Object> map = new HashMap<>();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    int total = rs.getInt("total");
                    map.put("total",total);
                }
                rs.close();
                String sql2="select * from user where id="+id;
                ResultSet rs2 = st.executeQuery(sql2);
                while(rs2.next()){
                    int rowNum = rs2.getRow();
                    User user=userRowMapper.mapRow(rs2,rowNum);
                    map.put("user",user);
                }
                rs2.close();
                st.close();
                return map;
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }






}
