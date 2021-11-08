package com.kenfei.admin.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 * @author fei
 * @date 2021/11/8 10:41
 */
@Configuration
public class CorsConfig {
  /**
   * 添加跨域响应头
   *
   * @return 跨域响应头
   */
  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod(HttpMethod.GET);
    config.addAllowedMethod(HttpMethod.POST);
    config.addAllowedMethod(HttpMethod.PUT);
    config.addAllowedMethod(HttpMethod.DELETE);
    config.addAllowedMethod(HttpMethod.OPTIONS);
    config.setMaxAge(3600L);
    source.registerCorsConfiguration("/**", config);

    return new CorsFilter(source);
  }

}
