package org.ec.test;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.ec.test.entity.User;
import org.ec.test.service.IUserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QueryTest extends AbstractJUnit4SpringContextTests
{

    @Autowired
    IUserService userService;

    @Before
    public void before()
    {
        System.err.println("before");
    }

    @After
    public void after()
    {
        System.err.println("after");
    }



    @Test
    public void test0() throws Exception
    {
        User user=userService.getOne(new QueryWrapper(new User.Builder().account("13049682420").build()));
        System.err.println(user);
        user.setNickName("我是你爸x1");
        userService.updateById(user);
        user=userService.getOne(new QueryWrapper(new User.Builder().account("13049682420").build()));
        System.err.println(user);


    }


}
