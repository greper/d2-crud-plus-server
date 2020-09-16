package com.veryreader.d2p.api.modules.permission.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author auto generator
 * @since 2020-02-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pm_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    /**
     * 菜单名称
     */
    private String name;

    /**
     * 显示名称
     */
    private String title;

    /**
     * 权限代码
     */
    private String permission;

    /**
     * 前端路径
     */
    private String path;

    /**
     * 前端组件路径
     */
    private String component;

    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单类型
     */
    private Integer type;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 创建时间
     */
    @TableField( fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private List<Resource> children;

    public Resource addChild(Resource child){
        if(children == null){
            children = new ArrayList<>();
        }
        children.add(child);
        return this;
    }


}
