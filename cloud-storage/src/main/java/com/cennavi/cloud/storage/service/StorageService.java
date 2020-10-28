package com.cennavi.cloud.storage.service;

import org.springframework.http.ResponseEntity;

public interface StorageService {

    void decrease(Long productId, Integer count);

    String qeuryNameById(String productId);

    byte[] pbdTest(int z, int x, int y);

    ResponseEntity<byte[]> getpic();
}
