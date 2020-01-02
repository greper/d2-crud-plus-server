export const crudOptions = {
  columns: [
   <#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    {
        title: '${field.comment}',
        key: '${field.name}',
        sortable: true,
        <#if field.propertyType == 'LocalDateTime'>type: 'date',<#else>// type: 'select',</#if>
        // search: { disabled: true }, //开启查询
        // form: { disabled: true } //表单配置
        // disabled: false //是否隐藏列
    }<#if field_has_next>,</#if>
</#list>
   <#------------  END 字段循环遍历  ---------->
  ]
}