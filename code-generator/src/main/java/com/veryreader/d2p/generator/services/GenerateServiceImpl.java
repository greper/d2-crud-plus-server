package com.veryreader.d2p.generator.services;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.veryreader.d2p.generator.models.GenerateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2020/1/2 0002$
 */
@Service
@Slf4j
public class GenerateServiceImpl implements GenerateService {

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Override
    public void generate(GenerateRequest request) {

        String rootDir = request.getRootDir();
        if(rootDir == null ){
            rootDir =  System.getProperty("user.dir")+"/.generated/";
        }
        String outputDir = rootDir+"/backend/";



        AutoGenerator generator = new AutoGenerator();

        GlobalConfig globalConfig = new GlobalConfig();

        globalConfig.setOutputDir(outputDir);
        globalConfig.setAuthor("auto generator");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        generator.setGlobalConfig(globalConfig);




        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(dataSourceProperties.getUrl());
        dataSourceConfig.setDriverName(dataSourceProperties.getDriverClassName());
        dataSourceConfig.setUsername(dataSourceProperties.getUsername());
        dataSourceConfig.setPassword(dataSourceProperties.getPassword());
        generator.setDataSource(dataSourceConfig);


        TemplateConfig templateConfig = new TemplateConfig()
                .setEntity("templates/backend/entity.java")
                .setController("templates/backend/controller.java")
                .setMapper("templates/backend/mapper.java")
                .setService("templates/backend/service.java")
                .setServiceImpl("templates/backend/serviceImpl.java")
                .setXml("templates/backend/mapper.xml")
                ;


        //配置自定义模板
        generator.setTemplate(templateConfig);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(request.getModuleName());
        pc.setParent(request.getParentPackage());
        generator.setPackageInfo(pc);

        //自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("apiUrlPrefix", request.getApiUrlPrefix());
                this.setMap(map);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        Map<String,String> fronts = new HashMap<>();
        fronts.put("/templates/front/crud.js.ftl","/crud.js");
        fronts.put("/templates/front/page.vue.ftl","/page.vue");
        fronts.put("/templates/front/api.js.ftl","/api.js");
        fronts.put("/templates/front/router.js.ftl","/router.js");
        // 自定义配置会被优先输出
        String finalRootDir = rootDir;
        for (Map.Entry<String, String> entry : fronts.entrySet()) {

            focList.add(new FileOutConfig(entry.getKey()) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return finalRootDir + "/front/"+request.getModuleName()+"/"+ tableInfo.getEntityPath() +entry.getValue();
                }
            });
        }

        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);


        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        generator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
       // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(request.getTableName());

        //是否驼峰转连接字符
        strategy.setControllerMappingHyphenStyle(false);
        strategy.setTablePrefix(request.getTablePrefix());
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());
        generator.execute();


        log.info("{}生成完成:{}",request.getTableName(),rootDir);

    }
}
