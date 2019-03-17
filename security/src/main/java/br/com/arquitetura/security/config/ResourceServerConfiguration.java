package br.com.arquitetura.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

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
				//FIXME APENAS ENQUANTO NAO TEM DB
				.antMatchers(HttpMethod.GET, "/architect/**", "/customer/**", "/project-type/**", "/step/**", "/project/**", "/user/**").permitAll()
				.antMatchers(HttpMethod.POST, "/customer", "/project-type", "/step", "/project").permitAll()
				.antMatchers(HttpMethod.PATCH, "/user/**").permitAll()
				.anyRequest().authenticated();
	}

}