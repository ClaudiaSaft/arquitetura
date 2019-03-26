package br.com.arquitetura.account.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import br.com.arquitetura.account.enumeration.UserRole;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId("resource_id");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
				.antMatchers("/oauth/token", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**").permitAll()
				.antMatchers(HttpMethod.GET, "/app/version").permitAll()
				.antMatchers(HttpMethod.POST, "/architect").permitAll()
				.antMatchers(HttpMethod.POST, "/customer").hasAnyAuthority(UserRole.ARCHITECT.name())
				.antMatchers(HttpMethod.PUT, "/architect").hasAnyAuthority(UserRole.ARCHITECT.name())
				.antMatchers(HttpMethod.GET, "/architect/**").hasAnyAuthority(UserRole.ARCHITECT.name())
				.anyRequest().authenticated();
	}

}