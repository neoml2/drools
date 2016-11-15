package org.drools.examples.taobao.domain;

import java.util.Set;

/**
 * Created by xiyan on 16/10/28.
 */


public class BizParam {
    /**
     * 从ttid解析出来的客户端名称：
     * taobao、tmall、juhuasuan
     */
    private String client;
    /**
     * 平台信息：android、iphone、apd、ipad
     */
    private String platform;
    /**
     * 从ttid解析出来的客户端版本信息
     */
    private String version;

    private Set<Integer> itemTags;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Set<Integer> getItemTags() {
        return itemTags;
    }

    public void setItemTags(Set<Integer> itemTags) {
        this.itemTags = itemTags;
    }
}
