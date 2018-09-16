<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>


<!DOCTYPE html>
<HTML>
<HEAD>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/demo.css" type="text/css">
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="/static/assets/plugins/jquery-ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core.js"></script>
</HEAD>


<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>



                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContentCategory.id==null?"新增":"编辑"}分类</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal required" action="/content/category/save" method="post"
                                   modelAttribute="tbContentCategory">

                            <form:hidden path="id"/>
                            <div class="box-body">

                                <div class="form-group">
                                    <label for="id" class="col-sm-2 control-label">父级类目</label>
                                    <div class="col-sm-10">
                                        <form:hidden path="id"/>
                                        <input id="categoryName" class="form-control" placeholder="请选择"
                                               readonly="true" data-toggle="modal" data-target="#modal-default"
                                               value="${tbContentCategory.parent.name}"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="name" class="col-sm-2 control-label">分类名称</label>

                                    <div class="col-sm-10">
                                        <form:input path="name" cssClass="form-control" placeholder="请输入分类名称"
                                                    />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="sortOrder" class="col-sm-2 control-label">分类排序</label>

                                    <div class="col-sm-10">
                                        <form:input path="sortOrder" cssClass="form-control" placeholder="请输入分类排序"
                                                    />
                                    </div>
                                </div>

                            </div>

                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>

                    </div>

                    <div>

                        <%--<ul id="treeDemo" class="ztree"></ul>--%>

                    </div>

                </div>
            </div>
        </section>
    </div>





    <jsp:include page="../includes/copyright.jsp"/>
</div>




<jsp:include page="../includes/footer.jsp"/>
<script type="text/javascript" src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core.js"></script>


<sys:modal title="请选择" msg="<ul id='myTree' class='ztree'></ul>"></sys:modal>




<script>
    $(function () {
        var url = "/content/category/tree/data";
        var autoParam = ["id"];
        App.initZTree(url, autoParam, function (nodes) {
            var node = nodes[0];
            $("#id").val(node.id);
            $("#categoryName").val(node.name);
            $("#modal-default").modal("hide");
        });
    })
</script>

</body>

</HTML>