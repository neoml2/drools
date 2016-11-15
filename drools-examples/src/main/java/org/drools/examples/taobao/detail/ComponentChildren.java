package org.drools.examples.taobao.detail;

import lombok.Data;

/**
 * Created by xiyan on 2016/11/2.
 */
@Data
public class ComponentChildren {
    private String key;
    private String ruleId;

    public ComponentChildren (String key,String ruleId){
        this.key = key;
        this.ruleId = ruleId;
    }
    public ComponentChildren (String key){
        this.key = key;
    }
    public ComponentChildren (){
    }

}
