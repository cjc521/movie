package com.cjc.movie.config;/*
package com.cjc.movie.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

import java.util.List;

*/
/**
 * @description:
 * @author: cjc
 * @time: 2020/10/12 11:35
 *//*

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PermissionService permissionService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<Permission> permissions = permissionService.getPermissions();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        permissions.forEach(permission -> {
            List<Role> roles = permission.getRoles();
            String roleArr[]=new String[roles.size()];
            for (int i = 1; i < roles.size(); i++) {
                roleArr[i]=roles.get(i).getRoleName();
            }
            registry.antMatchers(permission.getPerName()).hasAnyRole();
        });
        super.configure(http);
    }
}
*/
