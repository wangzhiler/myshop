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
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css" type="text/css"/>
    <link rel="stylesheet" href="/static/assets/plugins/wangeditor/wangEditor.min.css" type="text/css"/>

    <script type="text/javascript" src="/static/assets/plugins/jquery-ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
    <script type="text/javascript" src="/static/assets/plugins/wangeditor/wangEditor.min.js"></script>
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
                            <h3 class="box-title">${tbContent.id==null?"新增":"编辑"}内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <!-- form start -->
                        <form:form id="inputForm" cssClass="form-horizontal required" action="/content/save" method="post"
                                   modelAttribute="tbContent">
                            <form:hidden path="id"/>
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="categoryId" class="col-sm-2 control-label">父级类目</label>

                                    <div class="col-sm-10">
                                        <form:hidden path="categoryId"/>
                                        <input id="categoryName" class="form-control required" placeholder="请选择"
                                               readonly="true" data-toggle="modal" data-target="#modal-default"
                                                value="${tbContent.tbContentCategory.name}"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="title" cssClass="form-control required" placeholder="请输入标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                                    <div class="col-sm-10">
                                        <form:input path="subTitle" cssClass="form-control required" placeholder="请输入子标题"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                                    <div class="col-sm-10">
                                        <form:input path="titleDesc" cssClass="form-control required" placeholder="请输入标题描述"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">链接</label>

                                    <div class="col-sm-10">
                                        <form:input path="url" cssClass="form-control" placeholder="请输入链接"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">图片1</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic" cssClass="form-control" placeholder="请输入图片1"/>
                                        <div id="dropz" class="dropzone"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">图片2</label>

                                    <div class="col-sm-10">
                                        <form:input path="pic2" cssClass="form-control" placeholder="请输入图片2"/>
                                        <div id="dropz2" class="dropzone"></div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">内容</label>

                                    <div class="col-sm-10">
                                        <form:hidden path="content"/>
                                        <%--<form:input path="content" cssClass="form-control" placeholder="请输入内容"/>--%>
                                            <div id="editor">
                                                ${tbContent.content}
                                            </div>
                                    </div>
                                </div>

                            </div>

                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                <button type="submit" class="btn btn-info pull-right" id="btnSubmit">提交</button>
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

//    $(function () {
//        var setting = {
//            async: {
//                enable: true,
//                url: "../asyncData/getNodes.php",
//                autoParam: ["id", "name=n", "level=lv"],
//                otherParam: {"otherParam": "zTreeAsyncTest"},
//            }
//        };
//
//        $.fn.zTree.init($("#myTree"), setting);
//
//    });




    $(function () {
        var url = "/content/category/tree/data";
        var autoParam = ["id"];
        App.initZTree(url, autoParam, function (nodes) {
            var node = nodes[0];
            $("#categoryId").val(node.id);
            $("#categoryName").val(node.name);
            $("#modal-default").modal("hide");
        });

        initWangEditor();
    });

    function initWangEditor() {
        var E = window.wangEditor;
        var editor = new E('#editor');
        // 配置服务器端地址
        editor.customConfig.uploadImgServer = '/upload';
        console.log("upload-editorFile");
        editor.customConfig.uploadFileName = 'editorFile';
        editor.create();

        $("#btnSubmit").bind("click", function () {
            var contentHtml=editor.txt.html();
            $("#content").val(contentHtml);
        });
    }

    App.initDropzone({
        id: "#dropz",
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic").val(data.fileName);
            });
        }
    });

    App.initDropzone({
        id: "#dropz2",
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic2").val(data.fileName);
            });
        }
    });





//    Dropzone.options.dropz = {
//        url: "/upload",
//        dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
//        paramName: "dropFile", // 传到后台的参数名称
//        init: function () {
//            this.on("success", function (file, data) {
//                // 上传成功触发的事件
//                $("#pic").val(data.fileName);
//            });
//        }
//    };

//    Dropzone.autoDiscover = false;
//    var myDropzone = new Dropzone("#dropz", {
//        url: "/upload",
//        dictDefaultMessage: '拖动文件至此或者点击上传', // 设置默认的提示语句
//        paramName: "dropFile", // 传到后台的参数名称
//        init: function () {
//            this.on("success", function (file, data) {
//                // 上传成功触发的事件
//                $("#pic").val(data.fileName);
//            });
//        }
//    });
</script>

</body>

</HTML>