package org.drools.examples.taobao.engine;

import com.alibaba.fastjson.JSON;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.CompositeKnowledgeBuilder;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.examples.taobao.domain.BizParam;
import org.drools.examples.taobao.domain.RenderResult;
import org.drools.examples.taobao.util.LoggerUtils;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;


/**
 * Created by xiyan on 2016/11/12.
 */
public class DroolsEngineV2 {

    private KnowledgeBase knowledgeBase;

    private KnowledgeBuilder knowledgeBuilder;

    public void init() throws FileNotFoundException {
        knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        KnowledgeBaseConfiguration kbConf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
//        kbConf.setProperty("drools.conflictResolver", DepthComplexConflictResolver.class.getName());

        knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase(kbConf);
        knowledgeBase.newStatefulKnowledgeSession().dispose();


        knowledgeBuilder.undo();

        CompositeKnowledgeBuilder batch = knowledgeBuilder.batch();

        FileReader fileReader = new FileReader("drools-examples/src/main/resources/org/drools/examples/taobao/rules.drl");


        batch.add(ResourceFactory.newReaderResource(fileReader), ResourceType.DRL);

        batch.build();

        if (knowledgeBuilder.hasErrors()) {
            LoggerUtils.warn("errors in knowledgeBuilder, detail>>>" + knowledgeBuilder.getErrors().toString());
            knowledgeBuilder.undo();  // 若本次加载规则有问题，直接放弃之
            throw new RuntimeException("refresh cause exception>>>" + knowledgeBuilder.getErrors());
        }


        Collection<KnowledgePackage> packages = knowledgeBuilder.getKnowledgePackages();

        if (packages == null || packages.size() == 0) {
            LoggerUtils.warn("no rule to load for " + "com.taobao.wireless.render.rules.detail.custom");
            return;
        }

        try {
            knowledgeBase.addKnowledgePackages(packages);
        } catch (Throwable t) {
            LoggerUtils.exception("when readLock causes exception>>>", t);
        } finally {
        }

    }

    public void runEngine(){

        StatefulKnowledgeSession statefulKnowledgeSession = null;

        statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();
        RenderResult renderResult = new RenderResult();
        renderResult.getDetailLayout().initNomarlTemplate();
        statefulKnowledgeSession.setGlobal("renderResult", renderResult);

        BizParam bizParam = new BizParam();
        bizParam.setClient("taobao");
        bizParam.setPlatform("ipad");
        bizParam.setVersion("1.0.0");

        statefulKnowledgeSession.insert(bizParam);
//        statefulKnowledgeSession.getAgenda().getAgendaGroup("detail_agenda").setFocus();

        statefulKnowledgeSession.fireAllRules();

        RenderResult renderMeta =  (RenderResult)(statefulKnowledgeSession.getGlobals().get("renderResult"));
        System.out.println(JSON.toJSONString(renderMeta));

        bizParam.setPlatform("iphone");
        statefulKnowledgeSession.insert(bizParam);
        statefulKnowledgeSession.fireAllRules();

        renderMeta =  (RenderResult)(statefulKnowledgeSession.getGlobals().get("renderResult"));
        System.out.println(JSON.toJSONString(renderMeta));

    }

    public static void main(String[] argv) throws FileNotFoundException {
        System.out.println("start");

        DroolsEngineV2 droolsEngineV2 = new DroolsEngineV2();
        droolsEngineV2.init();

        droolsEngineV2.runEngine();
    }

}
