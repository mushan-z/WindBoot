package com.wind.dataBase.jdbc.mapper;

import com.wind.dataBase.jdbc.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author zhoubin
 * @time 2020/12/27 上午 11:58
 */
@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user=new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setAge(resultSet.getInt("age"));
        user.setCreateDate(new Date(resultSet.getTimestamp("create_date").getTime()));
        return user;
    }
}
