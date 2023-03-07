package com.jafa.cofig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;

import com.jafa.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@ComponentScan("com.jafa.security")
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	
	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	LogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	DataSource dataSource;
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService())
		.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}

	//암호화로 인한 필터 꺠짐  >>addFilterBefore(filter,CsrfFilter.class)을 추가함 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		
		http.addFilterBefore(filter,CsrfFilter.class);
		
		http.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler)
			.and()
			.formLogin()
			.loginPage("/member/login")
			.loginProcessingUrl("/member/login")
			.usernameParameter("id")
			.passwordParameter("pwd")
			.failureHandler(authenticationFailureHandler)
			.successHandler(authenticationSuccessHandler)
			.and()
			.logout()
			.invalidateHttpSession(true)
			.logoutUrl("/member/logout")
			.logoutSuccessHandler(logoutSuccessHandler)
			.logoutSuccessUrl("/")
			.deleteCookies("remember-me","JSESSION_ID")
			.and()
			.rememberMe()
			.key("lee")
			.tokenRepository(persistentTokenRepository())
			.rememberMeParameter("remember-me")
			.tokenValiditySeconds(9999);
	}
	

	
	
}
