package com.pda.itoken.web.admin.service;

import com.pda.itoken.web.admin.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 消费者接口
 */
@FeignClient(value = "iToken-service-admin",fallback = AdminServiceFallback.class)// 绑定iToken-service-admin服务,熔断回调类
public interface AdminService {
}
