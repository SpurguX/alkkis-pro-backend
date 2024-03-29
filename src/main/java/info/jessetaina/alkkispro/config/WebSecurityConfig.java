package info.jessetaina.alkkispro.config;

/**
 * Security solution adapted from the following:
 * https://www.javainuse.com/spring/boot-jwt
 * https://www.javainuse.com/spring/boot-jwt-mysql
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import info.jessetaina.alkkispro.controller.CorsFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	// @Autowired
	// private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private CorsFilter corsFilter;
	
	// This block threw an exception (circular dependency apparently) that was fixed by commenting it out
	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	// 	// configure AuthenticationManager so that it knows from where to load
	// 	// user for matching credentials
	// 	// Use BCryptPasswordEncoder
	// 	auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	// }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		// TODO csrf config
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests().antMatchers("/authenticate").permitAll()
		.anyRequest().authenticated();

		// make sure we use stateless session; session won't be used to
		// store user's state.
		httpSecurity.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
