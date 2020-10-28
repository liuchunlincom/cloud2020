package com.cennavi.cloud.storage.beans;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Rain
 * @Date 2020/10/26 16:49
 **/
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
