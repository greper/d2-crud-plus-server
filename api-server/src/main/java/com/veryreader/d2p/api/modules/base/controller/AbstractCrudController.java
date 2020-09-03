package com.veryreader.d2p.api.modules.base.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @Description:
 * @Author: xiaojunnuo
 * @CreateDate: 2018/12/29 0029$
 */
public class AbstractCrudController<T> {
    public void setDefaultSort(Page page, String sortProp, boolean isDesc) {
        if(page!= null && page.getOrders()!= null && page.getOrders().size()>0){
           return;
        }
        if(isDesc){
            page.addOrder(OrderItem.desc(sortProp));
        }else{
            page.addOrder(OrderItem.asc(sortProp));
        }
    }

    public void betweenDateRange( LambdaQueryWrapper<T> wrapper, SFunction<T, Object> column,String dateRange) {
        if(StringUtils.isNotBlank(dateRange)){
            String[] range = dateRange.split("-");
            wrapper.between(column, new Date(Long.parseLong(range[0])), new Date(Long.parseLong(range[1])));
        }
    }

}
