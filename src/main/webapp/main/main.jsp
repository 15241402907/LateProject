<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>应学后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>

<!--容器-->
<div class="container-fluid">
    <!--栅格系统-->
    <div class="row">
        <div class="col-xs-12">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#">应学视频App后台管理系统<small>V1.0</small></a>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">${sessionScope.admin.username}</a></li>
                        <li class="dropdown">
                            <a href="${pageContext.request.contextPath}/admin/logOut" class="btn btn-danger" >退出管理系统 <span class="glyphicon glyphicon-log-out"></span></a>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>
        </div>


        <!--左侧手风琴-->
        <div class="col-xs-2" align="center">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

                <div class="panel panel-info">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <a class="btn btn-info" href="javascript:$('#right').load('user.jsp')">用户列表</a>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="panel panel-warning">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                分类管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <a class="btn btn-warning" href="javascript:$('#right').load('category.jsp')">类别列表</a>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="panel panel-success">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                视频管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <a class="btn btn-success" href="javascript:$('#right').load('video.jsp')">视频展示</a>
                        </div>
                    </div>
                </div>
                <hr>
                <div class="panel panel-danger">
                    <div class="panel-heading" role="tab" id="headingFor">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFor" aria-expanded="false" aria-controls="collapseFor">
                                日志管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFor" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFor">
                        <div class="panel-body">
                            <a class="btn btn-danger" href="javascript:$('#right').load('log.jsp')">日志展示</a>
                        </div>
                    </div>
                </div>
                <hr>

                <c:if test="${admin.level==1}">
                    <div class="panel panel-info">
                        <div class="panel-heading" role="tab" id="headingFiv">
                            <h4 class="panel-title">
                                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFiv" aria-expanded="false" aria-controls="collapseFiv">
                                    管理员管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseFiv" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFiv">
                            <div class="panel-body">
                                <a class="btn btn-info" href="javascript:$('#right').load('admin.jsp')">管理员展示</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                <hr>
                <div class="panel panel-warning">
                    <div class="panel-heading" role="tab" id="headingSix">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseSix">
                                反馈管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                        <div class="panel-body">
                            <a href="javascript:$('#right').load('feedback.jsp')" class="btn btn-warning">反馈信息展示</a>
                        </div>
                    </div>
                </div>





            </div>
        </div>



        <%--右侧部分--%>
        <div class="col-xs-10" id="right" align="center">
            <!--巨幕-->
            <div class="jumbotron">
                <p size="20px">欢迎来到应学视频App后台管理系统</p>
            </div>

            <!--轮播图-->

            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox" align="center">
                    <div class="item active">
                        <img src="../bootstrap/img/pic1.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="../bootstrap/img/pic2.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item" style="size: 600px">
                        <img src="../bootstrap/img/pic3.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img src="../bootstrap/img/pic4.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

        </div>


    </div>
</div>

<br/><br/><br/><br/>
<div class="row">
    <div class="col-xs-12 col-xs-offset-6">
        <h5>@百知教育 guohb@zprkhr.com</h5>
    </div>
</div>



</body>
</html>
