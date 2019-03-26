package br.com.arquitetura.account.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String CLIEN_ID = "apparquitetura";
	private static final String CLIENT_SECRET = "{noop}arquiteturasc";
	private static final String GRANT_TYPE_PASSWORD = "password";
	private static final String AUTHORIZATION_CODE = "authorization_code";
	private static final String REFRESH_TOKEN = "refresh_token";
	private static final String SCOPE_READ = "read";
	private static final String SCOPE_WRITE = "write";
	
	@Autowired
	private DataSource dataSource;

	@Autowired 
	private JwtAccessTokenConverter jwtAccessTokenConverter;
	
	@Autowired 
	private TokenStore tokenStore;

	private AuthenticationManager authenticationManager;

	public OAuth2AuthorizationServerConfig(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public JwtAccessTokenConverter converter(){
		return new JwtAccessTokenConverter();
	}
	
	@Bean
	public TokenStore tokenStore(){
		return new JdbcTokenStore(this.dataSource);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(CLIEN_ID)
			.authorizedGrantTypes(GRANT_TYPE_PASSWORD, REFRESH_TOKEN, AUTHORIZATION_CODE)
			.secret(CLIENT_SECRET)
			.scopes(SCOPE_READ, SCOPE_WRITE);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				.accessTokenConverter(jwtAccessTokenConverter)
				.authenticationManager(authenticationManager);
	}
}
