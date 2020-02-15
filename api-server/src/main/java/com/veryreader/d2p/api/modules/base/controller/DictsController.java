package com.veryreader.d2p.api.modules.base.controller;


import com.veryreader.d2p.api.model.vo.DefaultEnumDict;
import com.veryreader.d2p.api.model.vo.EnumDict;
import com.veryreader.d2p.api.model.vo.Ret;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaojunnuo
 * @since 2018-06-20
 */
@RestController
@RequestMapping("/base/dicts")
public class DictsController  {

    @Autowired
    private ResourceLoader resourceLoader;



    /**
     */
    @GetMapping("/")
    public Ret<Map<String,List<EnumDict>>> dicts(HttpServletResponse response) throws IOException, ClassNotFoundException {
        //5小时浏览器过期
        response.setDateHeader("Expires", System.currentTimeMillis()+ 5*DateUtils.MILLIS_PER_HOUR);
        Map<String, List<EnumDict>> map = getEnumDicts();
        return Ret.success("",map);
    }

    /**
     */
    @GetMapping("/{name}")
    public Ret<List<EnumDict>> dictByName(HttpServletResponse response, @PathVariable("name") String name) throws IOException, ClassNotFoundException {
        //5小时浏览器过期
        response.setDateHeader("Expires", System.currentTimeMillis()+5*DateUtils.MILLIS_PER_HOUR);
        Map<String, List<EnumDict>> map = getEnumDicts();
        return Ret.success("",map.get(name));
    }

    private Map<String, List<EnumDict>> getEnumDicts() throws IOException, ClassNotFoundException {
        List<Class> enumClasses = scanEnumDicts();
        Map<String,List<EnumDict>> map = new HashMap<>();
        for (Class item : enumClasses) {
            if(item.isEnum() && ArrayUtils.contains(item.getInterfaces(), EnumDict.class)){
                map.put(item.getSimpleName(), DefaultEnumDict.create(item) );
            }
        }
        return map;
    }


    private List<Class> scanEnumDicts() throws IOException, ClassNotFoundException {
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory(resourceLoader);
        Resource[] resources = resolver.getResources("classpath*:com/veryreader/**/enums/**/*.class");
        List<Class> enums = new ArrayList<>();
        for (Resource r : resources) {
            MetadataReader reader = metaReader.getMetadataReader(r);
            enums.add(Class.forName(reader.getClassMetadata().getClassName()));
        }
        return enums;
    }
}
