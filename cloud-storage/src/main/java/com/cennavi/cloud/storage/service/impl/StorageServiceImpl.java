package com.cennavi.cloud.storage.service.impl;

import com.cennavi.cloud.storage.dao.StorageDao;
import com.cennavi.cloud.storage.service.StorageService;
import com.cennavi.cloud.storage.utils.TileUtils;
import lombok.extern.slf4j.Slf4j;
import no.ecc.vectortile.VectorTileEncoder;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {


    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        storageDao.decrease(productId, count);
    }

    @Override
    public String qeuryNameById(String productId) {
        return storageDao.qeuryNameById(productId);
    }

    @Override
    public byte[] pbdTest(int z, int x, int y) {
        String tile = TileUtils.parseXyz2Bound(x, y, z);
        log.info(tile);

        VectorTileEncoder vte = new VectorTileEncoder(4096, 16, false);

        List<Map<String,Object>> list = storageDao.queryGeom(tile);
        log.info("查询结果："+list.size());

        for(Map<String,Object> item: list){
            String geomWkt = item.get("geom").toString();
            WKTReader reader = new WKTReader();
            try {
                Geometry geom = reader.read(geomWkt);
                TileUtils.convert2Piexl(x, y, z, geom);
                item.remove("geom");
                vte.addFeature("pbfTest", item, geom);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return vte.encode();
    }

    @Override
    public ResponseEntity<byte[]> getpic() {
        String property = System.getProperty("user.dir");
        File file3 = new File(property);
        String filePath = "C:\\Users\\cennavi\\Pictures\\1.png";

        try {
            InputStream in = new FileInputStream(new File(filePath));// 将该文件加入到输入流之中
            byte[] body = null;
            body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
            in.read(body);// 读入到输入流里面

            HttpHeaders headers = new HttpHeaders();// 设置响应头
            headers.add("Content-Disposition", "attachment;filename=123.png" );
            HttpStatus statusCode = HttpStatus.OK;// 设置响应吗
            ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
