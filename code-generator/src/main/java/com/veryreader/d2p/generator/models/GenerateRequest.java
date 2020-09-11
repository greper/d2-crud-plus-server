package com.veryreader.d2p.generator.models;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private String logicDeleteField;
    private String superControllerClass;
    private List<TableFill> fillList;
    private Long platformId;

    public  GenerateRequest addFill(String fieldName, FieldFill fieldFill){
        if(fillList == null){
            fillList = new ArrayList<>();
        }
        fillList.add(new TableFill(fieldName,fieldFill));
        return this;
    }
}
