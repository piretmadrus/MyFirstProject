package org.mypr;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

/**
 * Created by Reio on 17.02.2015.
 */
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
//@ComponentScan("org.mypr")
//@Import({DbConfig.class})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth .inMemoryAuthentication()
	 * .withUser("admin").password("siru123").roles("SiruAdmin"); }
	 */

	@Autowired
	public DataSource dataSource;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		 auth
         .jdbcAuthentication().dataSource(dataSource).passwordEncoder(new Md5PasswordEncoder())
             .usersByUsernameQuery("select username, passw, status from user_account where username=?")
             .authoritiesByUsernameQuery("select u.username, r.authority from user_account u, user_roles r where u.username =?");
	}

	protected void configure(HttpSecurity http) throws Exception {		
		
		/* http.authorizeRequests()
		 	.antMatchers("/", "/app/subjektid/**").access("hasAnyRole('admin', 'admin1')")
			
			.and()
			  .formLogin().loginPage("/app/login").failureUrl("/login?error")
			  .usernameParameter("username").passwordParameter("password")
			.and()
			  .logout().logoutSuccessUrl("/login?logout")
			
			.and()
			  .csrf();*/
		
		/* http.authorizeRequests()
	            .anyRequest().authenticated()                                                   
	            .and()
	            .formLogin().loginPage("/app/login").permitAll()
				.and()
				.logout()
				.permitAll().logoutSuccessUrl("/app/login");*/
		
		
		http.headers().frameOptions().disable()
				.requestMatcher(new RegexRequestMatcher(".*", null)).csrf()
				.disable()
				.authorizeRequests()
				.antMatchers("/", "/app/tere/**").permitAll()
				//.antMatchers("/", "/app/subjektid/**").access("hasRole('admin') and hasRole ('admin1')")
				.antMatchers("/", "/app/subjektid/**").access("hasAnyRole('admin', 'admin1')")
				.and()
				// .httpBasic()
				.formLogin().loginPage("/app/login").defaultSuccessUrl("/app/firstpage").permitAll()
				.and()
				.logout()
				;
	}
}
