package com.veryreader.d2p.generator;

import com.veryreader.d2p.generator.models.GenerateRequest;
import com.veryreader.d2p.generator.services.GenerateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class CodeGeneratorApplicationTests {

    @Autowired
    private GenerateService generateService;
    @Test
    void generate() {
        GenerateRequest request = GenerateRequest.builder()
                .moduleName("hotel")
                .tableName("check_in")
                .parentPackage("com.veryreader.d2p")
                .tablePrefix("")
                .apiUrlPrefix("")
                .build();
        generateService.generate(request);
    }

}
