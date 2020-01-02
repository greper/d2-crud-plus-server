package com.veryreader.d2p.generator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2020/1/2 0002$
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateRequest {
    private String rootDir;
    private String tableName;
    private String parentPackage;
    private String moduleName;
    private String tablePrefix;
    private String apiUrlPrefix;
}
