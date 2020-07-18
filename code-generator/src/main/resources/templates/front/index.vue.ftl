<template>
    <d2-container :class="{'page-compact':crud.pageOptions.compact}">
        <template slot="header">${table.comment}</template>
        <d2-crud-x
                ref="d2Crud"
                :columns="crud.columns"
                :data="crud.list"
                :rowHandle="crud.rowHandle"
                edit-title="修改"
                :add-template="crud.addTemplate"
                :add-rules="crud.addRules"
                :edit-template="crud.editTemplate"
                :edit-rules="crud.editRules"
                :form-options="crud.formOptions"
                :options="crud.options"
                :pagination="crud.pagination"
                @pagination-change="handlePaginationChange"
                @dialog-open="handleDialogOpen"
                @row-edit="handleRowEdit"
                @row-add="handleRowAdd"
                @row-remove="handleRowRemove"
                @dialog-cancel="handleDialogCancel"
                @form-data-change="handleFormDataChange"
                :selectionRow="crud.selectionRow"
                @selection-change="handleSelectionChange">

            <div slot="header">
                <crud-search ref="search" :options="crud.searchOptions" @submit="handleSearch"  />
                <el-button v-permission="'${package.ModuleName}:${table.entityPath}:add'" size="small" type="primary" @click="addRow"><i class="el-icon-plus"/> 新增</el-button>
                <crud-toolbar :search.sync="crud.searchOptions.show"
                              :compact.sync="crud.pageOptions.compact"
                              :columns="crud.columns"
                              @refresh="doRefresh()"
                              @columns-filter-changed="handleColumnsFilterChanged"/>
            </div>
            <span slot="PaginationPrefixSlot" class="prefix" >
                <el-button v-permission="'${package.ModuleName}:${table.entityPath}:del'" class="square" size="mini" title="批量删除"   @click="batchDelete" icon="el-icon-delete" :disabled="!multipleSelection || multipleSelection.length===0"  />
            </span>
        </d2-crud-x>
    </d2-container>
</template>

<script>
  import { crudOptions } from './crud'
  import { d2CrudPlus } from 'd2-crud-plus'
  import * as api from './api'
  export default {
    name: '${table.entityName}',
    mixins: [d2CrudPlus.crud],
    data () {
      return {}
    },
    methods: {
      getCrudOptions () {
        return crudOptions(this)
      },
      pageRequest (query) {
        return api.GetList(query)
      },
      addRequest (row) {
        return api.AddObj(row)
      },
      updateRequest (row) {
        return api.UpdateObj(row)
      },
      delRequest (row) {
        return api.DelObj(row.id)
      },
      batchDelRequest (ids) {
        return api.BatchDel(ids)
      },
      infoRequest (row) {
        return api.GetObj(row.id)
      }
    }
  }
</script>
