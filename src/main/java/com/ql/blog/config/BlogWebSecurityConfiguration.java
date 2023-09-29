package com.ql.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ql.blog.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class BlogWebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 사용자가 입력한 username으로 User 객체를 검사하고 password를 비교
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeHttpRequests()
				.antMatchers("/webjars/**", "/js/**", "/image/**", "/", "/auth/**").permitAll()
				.anyRequest().authenticated();
			http.csrf().disable();
			
			http.formLogin().loginPage("/auth/login");
			http.formLogin().loginProcessingUrl("/auth/securityLogin");
			
			http.logout().logoutUrl("/auth/logout").logoutSuccessUrl("/");
	}
	

}
