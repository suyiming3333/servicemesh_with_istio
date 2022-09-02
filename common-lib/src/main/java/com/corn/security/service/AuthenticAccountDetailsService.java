package com.corn.security.service;

import com.corn.security.model.AuthenticAccount;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author suyiming3333
 * @version 1.0
 * @className: AuthenticAccountDetailsService
 * @description: 认证用户信息查询服务
 * @date 2022/8/8 17:20
 */

@Service
public class AuthenticAccountDetailsService implements UserDetailsService {
    //根据用户名到数据库、或者LDAP的等服务获取用户
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //根据username 到数据库查询userDetails 并返回
        if("suyiming".equals(userName)){
            AuthenticAccount authenticAccount = new AuthenticAccount();
            authenticAccount.setName("corn");
            authenticAccount.setUsername("suyiming");
            authenticAccount.setPassword("$2a$10$ODXZQUcBimOug26Wk5X18.3MJVPM6hgmohXg3msk2ygprnbXa93sm");
            return authenticAccount;
        }
        throw new UsernameNotFoundException("未找到该用户:" + userName);
    }
}
