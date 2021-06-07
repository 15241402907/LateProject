<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>




<script>
    $(function () {
        jQuery("#categoryTable").jqGrid(
            {
                url: "${pageContext.request.contextPath}/category/queryOne",
                editurl:"${pageContext.request.contextPath}/category/edit",
                datatype: "json",
                height: "auto",
                autowidth:true,
                rowNum: 3,
                rowList: [1, 3, 5, 7, 9, 11, 13, 15, 17],
                pager: '#categoryTool',
                sortname: 'id',
                viewrecords: true,
                sortorder: "desc",
                styleUI:"Bootstrap",
                multiselect: true,//false
                subGrid: true,
                caption: "类别信息展示",
                colNames: ['ID', '类别名', '级别'],
                colModel: [
                    {name: 'id', index: 'id', width: 55},
                    {name: 'cateName',editable:true, index: 'invdate', width: 90},
                    {name: 'level', index: 'name', width: 100},
                ],
                subGridRowExpanded: function (subgrid_id, row_id) {

                    var subgrid_table_id = subgrid_id + "_t";
                    var pager_id = "p_" + subgrid_table_id;

                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id+ "' class='scroll'></table><div id='"+ pager_id + "' class='scroll'></div>"
                    );
                    jQuery("#" + subgrid_table_id).jqGrid(
                        {
                            url: "${pageContext.request.contextPath}/category/queryTwo?id="+ row_id,
                            editurl:"${pageContext.request.contextPath}/category/edit?parentId="+row_id,
                            datatype: "json",
                            rowNum: 20,
                            pager: pager_id,
                            sortname: 'id',
                            sortorder: "asc",
                            styleUI:"Bootstrap",
                            height: "auto",
                            autowidth:true,
                            colNames: ['ID', '类别名', '级别','父类别id'],
                            colModel: [
                                {name: 'id',width: 55},
                                {name: 'cateName',editable:true, width: 90},
                                {name: 'level', width: 100},
                                {name: 'parentId',  width: 100},
                            ]
                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',"#" + pager_id, {edit: true, add: true, del: true},
                        {
                            closeAfterEdit:true
                        },
                        {
                            closeAfterAdd:true
                        },
                        {
                            closeAfterDel: true
                        },
                        {});
                },
                // subGridRowColapsed: function (subgrid_id, row_id) {
                // }
            });
        jQuery("#categoryTable").jqGrid('navGrid', '#categoryTool', {add: true, edit: true, del: true},
        {
            closeAfterEdit:true
        },
        {
            closeAfterAdd:true
        },
        {
            closeAfterDel: true
        },
        {}
        );
    });
</script>

<table id="categoryTable"/>

<div id="categoryTool"/>
