export const crudOptions = {
  columns: [
   <#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    {
        title: '${field.comment}',
        key: '${field.propertyName}',
        <#if field.propertyType == 'LocalDateTime'>type: 'datetime',<#else>// type: 'select',</#if>
        // dict: { url: ''} //数据字典
        // search: { disabled: false}, // 开启查询
        // disabled: true, // 隐藏列
        form: { // 表单配置
            // disabled: true, // 禁用表单编辑
            // rules: [{ required: true, message: '请输入${field.comment}' }]
        },
        sortable: true
    }<#if field_has_next>,</#if>
</#list>
   <#------------  END 字段循环遍历  ---------->
  ]
}