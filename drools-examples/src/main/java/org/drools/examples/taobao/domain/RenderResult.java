package org.drools.examples.taobao.domain;


import org.drools.examples.taobao.detail.DetailLayout;

/**
 * Created by xiyan on 16/10/28.
 */
public class RenderResult {

    private DetailLayout detailLayout = new DetailLayout();

    public boolean insertComponent(Integer index, Integer childIndex,String name,String ruleId){
        System.out.println("insert " + name + ":" + ruleId);
        return detailLayout.insertComponent(index,childIndex,name,ruleId);
    }
    private String result = "";

    public void setResult(String result) {
        this.result = result;
    }

    public DetailLayout getDetailLayout() {
        return detailLayout;
    }
}
