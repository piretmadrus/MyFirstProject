package org.mypr;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
         .jdbcAuthentication().dataSource(dataSource)
             .usersByUsernameQuery("select * from users where username=?")
             .authoritiesByUsernameQuery("select username, authority from user_roles where username =?");
	}

	protected void configure(HttpSecurity http) throws Exception {		
		
		http.headers().frameOptions().disable()
				.requestMatcher(new RegexRequestMatcher(".*", null)).csrf()
				.disable()
				.authorizeRequests()
				//.antMatchers("/", "/app/subjektid/**").permitAll()
				.antMatchers("/", "/app/subjektid/**").access("hasAnyRole('admin', 'admin1')")
				.and()
				// .httpBasic()
				.formLogin().loginPage("/app/login").permitAll()
				.and()
				.logout()
				.permitAll().invalidateHttpSession(true).logoutSuccessUrl("/app/login");
	}
}
