package com.cennavi.cloud.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author Rain
 * @Date 2020/10/15 22:21
 **/
@Component
@Slf4j
public class LogGatewayFilter implements GlobalFilter,Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("LogGatewayFilter---------------:" + new Date());
        String s = exchange.getRequest().getQueryParams().getFirst("lcl");
        System.out.println(s);
        if(s==null){
            return chain.filter(exchange);
        }else{
            exchange.getResponse().setStatusCode(HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
