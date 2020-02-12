package com.veryreader.d2p.generator;

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
                .moduleName("user")
                .tableName("pm_user_role")
                .parentPackage("com.veryreader.d2p.api.modules")
                .tablePrefix("pm")
                .apiUrlPrefix("")
                .build();
        generateService.generate(request);
        request.setTableName("pm_role");
        generateService.generate(request);
        request.setTableName("pm_menu");
        generateService.generate(request);
        request.setTableName("pm_role_menu");
        generateService.generate(request);
    }

}
