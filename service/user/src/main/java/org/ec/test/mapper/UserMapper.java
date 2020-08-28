package org.ec.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ec.test.entity.User;

import java.util.List;
public interface UserMapper extends BaseMapper<User> {

}