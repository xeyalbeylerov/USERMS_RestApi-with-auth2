package com.company.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
//@EnableResourceServer
//@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("$(security.signing-key}")
    private String signingKey;
//
//    @Value("$(security.encoding-strength}")
//    private String encodingStrength;
//
//    @Value("${security.security-realm}")
//    private String securityRealm;

    @Autowired
    DataSource defaultDataSource;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {//passwordu encrypt edib bazadaki ile muqaise edir
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {//oauth2 kitabxanasindan gelir, cunki token usere yox appa aid olan bir seydi
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }

    //    @Bean
////    public TokenStore tokenStore(){//tokeni ramda saxlamaq ucun
////        return new JwtTokenStore(accessTokenConverter());
////    }
    @Bean
    public TokenStore tokenStore() {//tokeni ramda saxlamaq ucun
        return new JdbcTokenStore(defaultDataSource);
    }//stackoverflow.com/questions/20958166/what-are-the-steps-to-implement-springs-token-store-as-a-mysql-file?answertab=oldest#tab-top

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .realmName("myrealm")
                .and()
                .csrf()
                .disable();
    }
}
