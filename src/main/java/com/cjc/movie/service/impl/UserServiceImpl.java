package com.cjc.movie.service.impl;

import com.cjc.movie.entity.User;
import com.cjc.movie.mapper.UserMapper;
import com.cjc.movie.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cjc
 * @since 2020-10-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
