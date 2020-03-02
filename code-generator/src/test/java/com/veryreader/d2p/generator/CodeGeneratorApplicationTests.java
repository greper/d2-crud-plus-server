package com.veryreader.d2p.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.veryreader.d2p.generator.models.GenerateRequest;
import com.veryreader.d2p.generator.services.GenerateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeGeneratorApplicationTests {

    @Autowired
    private GenerateService generateService;

    @Test
    void pm() {
        GenerateRequest request = GenerateRequest.builder()
                .moduleName("permission")
                .tableName("pm_user_role")
                .parentPackage("com.veryreader.d2p.api.modules")
                .tablePrefix("pm")
                .apiUrlPrefix("")
                .logicDeleteField("del_flag")
                .build();
        request.addFill("createTime", FieldFill.INSERT);
        request.addFill("updateTime",FieldFill.INSERT_UPDATE);
        generateService.generate(request);
        request.setTableName("pm_role");
        generateService.generate(request);
        request.setTableName("pm_resource");
        generateService.generate(request);
        request.setTableName("pm_role_resource");
        generateService.generate(request);
        request.setTableName("pm_platform");
        generateService.generate(request);
    }

    @Test
    void sys() {
        GenerateRequest request = GenerateRequest.builder()
                .moduleName("usersphere")
                .tableName("sys_user")
                .parentPackage("com.veryreader.d2p.api.modules")
                .tablePrefix("sys")
                .apiUrlPrefix("")
                .logicDeleteField("del_flag")
                .build();
        request.addFill("createTime", FieldFill.INSERT);
        request.addFill("updateTime",FieldFill.INSERT_UPDATE);
        generateService.generate(request);
    }
}
