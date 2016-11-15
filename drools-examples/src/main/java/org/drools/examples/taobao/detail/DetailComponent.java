package org.drools.examples.taobao.detail;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiyan on 16/10/29.
 */
public class DetailComponent {

    static {
   //     TypeUtils.compatibleWithJavaBean = true;
    }


    private String ID;
    private String key;
    private List<ComponentChildren> children = new ArrayList<ComponentChildren>();

    public String getID() {
        return ID;
    }

    public String getKey() {
        return key;
    }

    public List<ComponentChildren> getChildren() {
        return children;
    }

    public DetailComponent(String ID,String key){
        this.ID = ID;
        this.key = key;
    }

    public boolean addChild(ComponentChildren componentChildren){
        children.add(componentChildren);
        return true;
    }
    public boolean addChild(Integer index , ComponentChildren componentChildren){
        children.add(index,componentChildren);
        return true;
    }
    public boolean updateChild(Integer index , ComponentChildren componentChildren){
        children.set(index,componentChildren);
        return true;
    }

}
