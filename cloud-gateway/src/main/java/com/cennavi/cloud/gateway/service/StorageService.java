package com.cennavi.cloud.gateway.service;

import com.cennavi.cloudalibaba.commons.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-storage")
public interface StorageService {

    @PostMapping("storage/decrease")
    CommonResult decrease(@RequestParam("productId") String productId,
                          @RequestParam("count") Integer count);

    @GetMapping(value = "storage/testPbf/{z}/{x}/{y}")
    public byte[] testPbf(@PathVariable("z") int z,
                          @PathVariable("x") int x,
                          @PathVariable("y") int y);

    @GetMapping(value = "storage/testPic")
    public ResponseEntity<byte[]> testPic();

}
