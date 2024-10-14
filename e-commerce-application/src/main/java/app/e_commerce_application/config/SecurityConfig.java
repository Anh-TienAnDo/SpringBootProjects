// package app.e_commerce_application.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.web.DefaultSecurityFilterChain;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import app.e_commerce_application.security.JWTFilter;
// import jakarta.servlet.http.HttpServletResponse;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity(prePostEnabled = true)
// public class SecurityConfig {

//     @Autowired
// 	private JWTFilter jwtFilter;
	
// 	@Bean
// 	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// 		http.csrf().disable()
// 			.authorizeHttpRequests()
// 			.requestMatchers(AppConstants.PUBLIC_URLS).permitAll()
// 			// .requestMatchers(AppConstants.USER_URLS).hasAnyAuthority("USER", "STAFF", "ADMIN")
// 			// .requestMatchers(AppConstants.ADMIN_URLS).hasAnyAuthority("STAFF", "ADMIN")
// 			.anyRequest()
// 			.authenticated()
// 			.and()
// 			.exceptionHandling().authenticationEntryPoint(
// 					(request, response, authException) -> 
// 						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
// 			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);	
		
// 		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
// 		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();
		
// 		return defaultSecurityFilterChain;
// 	}
    
// }
