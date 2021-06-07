






<%@ page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>




<script>
    $(function (){
        //初始化表格
        $("#userTable").jqGrid({
            url:"${pageContext.request.contextPath}/user/queryByPage",
            editurl:"${pageContext.request.contextPath}/user/edit",
            datatype:"json",
            mtype:"post",
            rowNum:3,
            rowList:[1,3,5,7,9],
            pager : '#userTool',
            viewrecords : true,  //是否展示总条数
            caption : "反馈信息表表",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : [ 'ID', '手机号', '头像', '用户名',"签名","微信","状态","注册时间"],
            colModel : [
                {name : 'id'},
                {name : 'phone',editable: true},
                {name : 'headImg',editable: true,edittype:"file",
                    formatter:function (cellvalue){
                        return "<img src='${path}/"+cellvalue+"' width='200px' height='100px'>";
                    }
                },
                {name : 'username',editable: true},
                {name : 'sign',editable: true},
                {name : 'wechat'},
                // {name : 'status'},
                {name : 'user_stats',width : 100,
                    formatter:function (cellvalue, options, rowObject){
                        //console.log(rowObject.status);
                        if(rowObject.status==0){
                            //状态:0 冻结   button  解除冻结  红色
                            return "<button class='btn btn-success' onclick='updateStatus(\""+rowObject.id+"\",\""+rowObject.status+"\");'>解除冻结</button>";
                        }else{
                            //状态1  正常    button  冻结   绿色

                            return "<button class='btn btn-danger' onclick='updateStatus(\""+rowObject.id+"\",\""+rowObject.status+"\");'>冻结</button>";
                        }
                    }
                },
                {name : 'registTime'},
            ]
        }).navGrid("#userTool", //开启增删改查操作  指定分页工具栏的位置
            {edit:true,edittext:"修改",del:true,deltext:"删除",add:true,addtext:"添加",search:true,searchtext:"搜索"},  //是否展示增删改查按钮  {},都展示
            {
                closeAfterEdit:true
            },  //修改之后的额外操作
            {   //在执行添加成功之后进入该括号执行
                closeAfterAdd:true, //执行成功之后关闭对话框
                afterSubmit:function (data){

                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/user/upload",
                        type:"POST",
                        dataType:"JSON",
                        data:{id:data.responseText},
                        fileElementId:"headImg",
                    });
                    return "hello";
                }
            },  //添加之后的额外操作
            {
                closeAfterDel: true
            }   //删除之后的额外操作
        );
    });

    function updateStatus(id,status){
        $.post("${path}/user/updateStatus",{id:id,status:status},function (){
            $("#userTable").trigger("reloadGrid");
        });
    }

</script>

<table id="userTable"/>

<div id="userTool"/>
