package com.cennavi.cloud.gateway.filters;

import com.cennavi.cloud.gateway.service.StorageService;
import com.cennavi.cloudalibaba.commons.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Rain
 * @Date 2020/10/15 22:21
 **/
@Component
@Slf4j
public class LogGatewayFilter implements GlobalFilter,Ordered {

    private static final String START_TIME = "startTime";

    @Resource
    StorageService storageService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("LogGatewayFilter---------------:" + new Date());
        String s = exchange.getRequest().getQueryParams().getFirst("lcl");
        System.out.println(s);

        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        if(s==null){
            return chain.filter(exchange).then(Mono.fromRunnable(()->{

                String path = exchange.getRequest().getPath().value();
                Long startTime = exchange.getAttribute(START_TIME);

                long cost = (System.currentTimeMillis() - startTime);
                log.warn(path+ "耗时：" + cost);
                CommonResult commonResult = storageService.decrease("1",1);
                System.out.println(commonResult.getMessage());
            }));
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
