package org.drools.examples.taobao.detail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiyan on 16/10/29.
 */
public class DetailLayout {

    private List<DetailComponent> components;

    private List<InsertComponent> insertComponents = new ArrayList<InsertComponent>();

    public void initNomarlTemplate(){

        components = new ArrayList<DetailComponent>();
        components.add(new DetailComponent("navi_bar","navi_bar"));
        components.add(new DetailComponent("pic_gallery","pic_gallery"));

        DetailComponent sysList = new DetailComponent("main_layout","sys_list");
        sysList.addChild(new ComponentChildren("image_bar"));
        sysList.addChild(new ComponentChildren("entrance","generalInterTip"));
        sysList.addChild(new ComponentChildren("title_share"));
        sysList.addChild(new ComponentChildren("price"));
        sysList.addChild(new ComponentChildren("division","tipDivision"));

        components.add(sysList);

    }

    public boolean insertComponent(Integer index,Integer childIndex, String key,String ruleId){

        //todo 以后不是这样insert,而是放进insert队列,等引擎跑完以后再统一insert
        ComponentChildren componentChildren = new ComponentChildren(key,ruleId);
        components.get(index).addChild(childIndex, componentChildren);
        return true;
    }

    public boolean updateComponent(Integer index,Integer childIndex, String key,String ruleId){

        ComponentChildren componentChildren = new ComponentChildren(key,ruleId);
        components.get(index).updateChild(childIndex, componentChildren);
        return true;
    }
    public boolean deleteComponent(Integer index,Integer childIndex){

        ComponentChildren componentChildren = new ComponentChildren();
        components.get(index).updateChild(childIndex, componentChildren);
        return true;
    }

    public List<DetailComponent> getComponents() {
        return components;
    }
}
