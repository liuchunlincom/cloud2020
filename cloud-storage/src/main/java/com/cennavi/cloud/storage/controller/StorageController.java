package com.cennavi.cloud.storage.controller;

import com.cennavi.cloud.storage.beans.Storage;
import com.cennavi.cloud.storage.service.StorageService;
import com.cennavi.cloudalibaba.commons.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPOutputStream;

@RestController
@Slf4j
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping(value = "storage/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId,
                                 @RequestParam("count") Integer count) {
        storageService.decrease(productId,count);
        return new CommonResult(200, "扣减库存成功");
    }

    @GetMapping(value = "storage/qeuryNameById")
    public Storage qeuryNameById(@RequestParam("productId") String productId) {

        log.info(productId);
        String name = storageService.qeuryNameById(productId);
        Storage storage = new Storage(productId,name);
        return storage;
    }

    @GetMapping(value = "storage/testGeom")
    public Geometry testGeom() {

        log.info("geom测试。。。。");
        WKTReader reader = new WKTReader();
        try {
            Geometry geometry = reader.read("POINT(123 2345)");
            return geometry;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "storage/testPbf/{z}/{x}/{y}")
    public byte[] testPbf(@PathVariable("x") int x,
                            @PathVariable("y") int y,
                            @PathVariable("z") int z) {

        System.out.println("11111111111111========");

        log.info("pbf测试。。。。123" + x + "," + y +"," + z);
        //return storageService.pbdTest(z,x,y);

       /* response.setContentType("application/x-protobuf");
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");*/

        byte[] bytes = storageService.pbdTest(z,x,y);
        log.error("" + bytes.length);
        return bytes;
    }


    @GetMapping(value = "storage/testPic")
    public ResponseEntity<byte[]> testPic() {

        log.info("图片测试。。。。" );
        //return storageService.pbdTest(z,x,y);

       /* response.setContentType("application/x-protobuf");
        response.setHeader("Content-Encoding", "gzip");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");*/

        ResponseEntity<byte[]> resp = storageService.getpic();
        return resp;
    }




}
