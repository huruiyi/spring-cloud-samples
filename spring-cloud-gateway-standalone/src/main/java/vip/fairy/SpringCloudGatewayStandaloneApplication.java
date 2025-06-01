package vip.fairy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringCloudGatewayStandaloneApplication {

 @RequestMapping("/circuitbreakerfallback")
  public String circuitbreakerfallback() {
    //@formatter:off
    return "This is a fallback";
		//@formatter:on
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {


    return builder.routes()
        .route("path_route", r -> r.path("/get").uri("http://httpbin.org"))
        .route("host_route", r -> r.host("*.myhost.org").uri("http://httpbin.org"))
        .route("rewrite_route",
            r -> r.host("*.rewrite.org")
                .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
                .uri("http://httpbin.org"))
        .route("circuitbreaker_route", r -> r.host("*.circuitbreaker.org")
            .filters(f -> f.circuitBreaker(c -> c.setName("slowcmd")))
            .uri("http://httpbin.org"))
        .route("circuitbreaker_fallback_route", r -> r.host("*.circuitbreakerfallback.org")
            .filters(f -> f.circuitBreaker(c -> c.setName("slowcmd").setFallbackUri("forward:/circuitbreakerfallback")))
            .uri("http://httpbin.org"))
        .route("limit_route", r -> r
            .host("*.limited.org").and().path("/anything/**")
            .filters(f -> f.requestRateLimiter(c -> c.setRateLimiter(redisRateLimiter())))
            .uri("http://httpbin.org"))
        .route("websocket_route", r -> r.path("/echo").uri("ws://localhost:9000"))
        .build();
  }

  @Bean
  RedisRateLimiter redisRateLimiter() {
    return new RedisRateLimiter(1, 2);
  }

  @Bean
  SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
    http.httpBasic(Customizer.withDefaults());
    http.csrf(CsrfSpec::disable);
    http.authorizeExchange(authorize -> {
      authorize.pathMatchers("/anything/**").authenticated();
      authorize.anyExchange().permitAll();
    });
    return http.build();
  }

  @Bean
  public MapReactiveUserDetailsService reactiveUserDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
    return new MapReactiveUserDetailsService(user);
  }

  /**
   * <a href="https://github.com/spring-cloud-samples/spring-cloud-gateway-sample.git">参考官方</a>
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringCloudGatewayStandaloneApplication.class, args);
  }
}
