package com.corn.auth.config;

import com.corn.auth.provider.PreAuthenticatedAuthenticationProvider;
import com.corn.auth.provider.UsernamePasswordAuthenticationProvider;
import com.corn.security.service.AuthenticAccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

/**
 * @author suyiming3333
 * @version 1.0
 * @className: AuthenticationServerConfiguration
 * @description:
 * 1、security 认证服务，用于用户登录验证，通过OAuth2的密码模式，在授权过程中同时完成认证
 * 2、需要实现、注入一下服务：
 *    - 认证用户信息查询服务 UserDetailsService
 *    - 基于用户名、密码的身份认证器 AuthenticationProvider
 *    - 预验证身份认证器（用于JWT令牌过期后重刷新时的验证，）
 *    - 密码加密算法
 * @date 2022/8/8 15:54
 */

@Configuration
@EnableWebSecurity
//注解权限开启
//@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true)
public class AuthenticationServerConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticAccountDetailsService authenticAccountDetailsService;

    @Autowired
    private UsernamePasswordAuthenticationProvider userProvider;

    @Autowired
    private PreAuthenticatedAuthenticationProvider preProvider;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * 需要把AuthenticationManager主动暴漏出来
     * 以便在授权服务器{AuthorizationServerConfiguration}中可以使用它来完成用户名、密码的认证
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 配置Spring Security的安全认证服务
     * Spring Security的Web安全设置，
     * 如设置哪些方法需要鉴权等
     * 将在资源服务器配置{ResourceServerConfiguration}中完成
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticAccountDetailsService).passwordEncoder(encoder);
        auth.authenticationProvider(userProvider);
        auth.authenticationProvider(preProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf
        http.csrf().disable();
        //鉴权异常处理
        //http.httpBasic().authenticationEntryPoint(myAuthenticationEntryPoint);
        //设置表单登录成功或失败的处理器(注释则关闭security表单登录)
//        http.formLogin().loginProcessingUrl("/myLogin").permitAll()//设置登录的url
//            .successHandler(successHandler)
//            .failureHandler(authFailureHandler);
        //设置自定义认证拦截器
        //http.addFilter(new MyAuthenticationFilter(authenticationManager(),clientDetailsService,authorizationServerTokenServices));
        //设置自定义认证url白名单
        http.authorizeRequests().antMatchers("/hello").permitAll();
        //其他所有接口资源都需要认证后才能访问
        http.authorizeRequests().anyRequest().authenticated();
        //移除静态资源目录的安全控制，避免Spring Security默认禁止HTTP缓存的行为
        //http.headers().cacheControl().disable();
    }



}
