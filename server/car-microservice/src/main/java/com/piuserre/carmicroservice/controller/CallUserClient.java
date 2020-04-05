package com.piuserre.carmicroservice.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-ms")
public interface CallUserClient {

	@GetMapping(value = "/name/{userId}", consumes ="application/json")
	String getUserName(@PathVariable Long userId);
}
