
package org.mypr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

/**
 * Created by Reio on 17.02.2015.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	
  /*@Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("admin").password("siru123").roles("SiruAdmin");
  }*/

  @Bean
  public JdbcUserDetailsManager userDetailsManager() {
      JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
      //manager.setDataSource(DataSource);
      manager.setUsersByUsernameQuery(
          "select username,password,enabled from user_account where username=?");
      //manager.setAuthoritiesByUsernameQuery("select username, role from user_roles where username=?");
      //manager.setRolePrefix("ROLE_");
      return manager;
  }
  
  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder builder)
          throws Exception {

      builder.userDetailsService(userDetailsManager());
  }
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
          throws Exception {
      auth.userDetailsService(userDetailsManager()).passwordEncoder(new Md5PasswordEncoder());
  }
  
  protected void configure(HttpSecurity http) throws Exception {
    http
        .headers().frameOptions().disable().requestMatcher(new RegexRequestMatcher(".*", null))
        .csrf().disable()
        .authorizeRequests()
        //.antMatchers("/", "/app").permitAll()
        .antMatchers("/","/app/subjektid/**")
        .hasRole("SiruAdmin")
        .and()
       // .httpBasic()
        .formLogin()
          .loginPage("/app/login")
          .permitAll()
        .and()
        .logout()
        .permitAll()
        .logoutSuccessUrl("/app/login")
    ;
  }
}

