package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.jwt.AuthEntryPointJwt;
import com.example.demo.jwt.AuthTokenFilter;
import com.example.demo.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests().antMatchers("/api/auth/**").permitAll()
			.antMatchers("/**").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/api/test/profile/**").authenticated()
            .antMatchers("/api/test/cart/**").access("hasAnyRole('USER')")
            .antMatchers("/api/test/order/finish/**").access("hasAnyRole('MODERATOR', 'ADMIN')")
            .antMatchers("/api/test/order/**").authenticated()
            .antMatchers("/api/test/profiles/**").authenticated()
            .antMatchers("/api/test/seller/product/new").access("hasAnyRole('ADMIN')")
            .antMatchers("/api/test/seller/**/delete").access("hasAnyRole( 'ADMIN')")
            .antMatchers("/api/test/seller/**").access("hasAnyRole('EMPLOYEE', 'ADMIN')")
           
            
			.antMatchers("/api/test/**").permitAll()
			.antMatchers("/api/test/cart/**").permitAll()
			.anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
