package com.lxisoft.atm.client.activiti.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.lxisoft.atm.client.activiti.ClientConfiguration;

@FeignClient(name="${activiti.name:activiti}", url="${activiti.url:http://localhost:8080/activiti-rest/service}", configuration = ClientConfiguration.class)
public interface ExecutionsApiClient extends ExecutionsApi {
}