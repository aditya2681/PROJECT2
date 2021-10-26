package com.cts.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cts.auth.jwt.JwtRequestFilter;
import com.cts.auth.service.CustomUserDetailsService;
/**
 * @author POD4
 * @version 1.8
 * @apiNote This class is used for the security configuration. It extends the
 *          class WebSecurityConfigurerAdapter It will take care of the
 *          authentication and authorization based on the user credentials.
 *
 */
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	/**
	 * This is a private field of class {@link CustomerDetailsService}
	 * {@link CustomerDetailsService} class is used to load the user credentials
	 * from the database. Based on that database fetched user credentials
	 * authentication will be performed
	 */
	@Autowired
	CustomUserDetailsService emsuserDetailsService;
	/**
	 * This is a private field of class {@link JwtRequestFilter} This class extends
	 * {@link OncePerRequestFilter} so every request will first hit this filter
	 */
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	Environment env;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(emsuserDetailsService);

	}
	/**
	 * This methods used to override the credentials which spring automatically
	 * generates By using {@link AuthenticationManagerBuilder} object we are
	 * overriding the security credentials with our own given credentials It will
	 * call the userDetailsService method on the
	 * {@link AuthenticationManagerBuilder} class object and this method is present
	 * in class {@link AuthenticationManagerBuilder}
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers(env.getProperty("authapp.validate"), env.getProperty("authapp.login")).permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
	}
	/**
	 * This method is used to inject a {@link AuthenticationManager} type bean We
	 * are annotating this method with @Bean. @Bean annotation tells that a method
	 * produces a bean to be managed by the Spring container.
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
