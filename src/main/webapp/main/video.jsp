<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>




<script>
    $(function (){
        //初始化表格
        $("#videoTable").jqGrid({
            url:"${pageContext.request.contextPath}/video/queryByPage",
            editurl:"${pageContext.request.contextPath}/video/edit",
            datatype:"json",
            mtype:"post",
            rowNum:3,
            rowList:[1,3,5,7,9],
            pager : '#videoTool',
            viewrecords : true,  //是否展示总条数
            caption : "反馈信息表表",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'ID', '标题', '描述', '视频','封面',"上传时间","所属类别ID","所属组别ID","所属用户ID"],
            colModel : [
                {name : 'id'},
                {name : 'title',editable: true},
                {name : 'description',editable: true,},
                {name : 'videoPath',editable: true,edittype:"file",
                    formatter:function (cellvalue,options,row){

                        return "<video src='"+cellvalue+"' controls height='100px' width='100%' poster='"+row.coverPath+"' />";
                    }
                },
                {name : 'coverPath',hidden:true},
                {name : 'uploadTime'},
                {name : 'cateId',editable: true},
                {name : 'groupId',editable: true,width : 100},
                {name : 'userId',editable: true},
            ]
        }).navGrid("#videoTool", //开启增删改查操作  指定分页工具栏的位置
            {edit:true,edittext:"修改",del:true,deltext:"删除",add:true,addtext:"添加",search:true,searchtext:"搜索"},  //是否展示增删改查按钮  {},都展示
            //修改
            {
                closeAfterEdit:true,
                afterSubmit:function (data){
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/video/update",
                        type:"POST",
                        dataType:"JSON",
                        data:{"id":data.responseText},
                        fileElementId:"videoPath",
                        success:function (){
                            $("#videoTable").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
            },

            //添加
            {   //在执行添加成功之后进入该括号执行
                closeAfterAdd:true, //执行成功之后关闭对话框
                afterSubmit:function (data){
                    console.log(data)
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/video/upload",
                        type:"POST",
                        dataType:"JSON",
                        data:{"id":data.responseText},
                        fileElementId:"videoPath",
                        success:function (){
                            $("#videoTable").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
            },
            //删除之后的额外操作
            {
                closeAfterDel: true,
                afterSubmit:function (){
                    $("#videoTable").trigger("reloadGrid");
                }
            }
        );
    });

</script>

<table id="videoTable"/>

<div id="videoTool"/>
