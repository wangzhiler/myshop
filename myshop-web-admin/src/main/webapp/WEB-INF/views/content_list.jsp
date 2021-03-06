<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.css"/>
</head>

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

                    <div class="box box-info box-info-search" style="display: none;">
                        <div class="box-header with-border">
                            <h3 class="box-title">高级搜索</h3>
                        </div>

                        <div class="box-body">
                            <div class="row form-horizontal" style="margin-top: 20px">

                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="title" class="col-sm-4 control-label">标题</label>
                                        <div class="col-sm-8">
                                            <input id="title" class="form-control" placeholder="请输入标题"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="subTitle" class="col-sm-4 control-label">子标题</label>
                                        <div class="col-sm-8">
                                            <input id="subTitle" class="form-control" placeholder="请输入子标题"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="titleDesc" class="col-sm-4 control-label">标题描述</label>
                                        <div class="col-sm-8">
                                            <input id="titleDesc" class="form-control" placeholder="请输入描述"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xs-12 col-sm-3">
                                    <div class="col-cs-12" style="text-align: center">
                                        <button onclick="search()" class="btn btn-info">搜索</button>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>


                    <div class="box">
                        <%--<div class="box-header">--%>
                        <div class="box-header with-border">
                            <h3 class="box-title">列表</h3>
                        </div>
                        <%--</div>--%>

                        <div class="box-body">
                            <a href="/content/form" type="button" class="btn btn-sm btn-default"><i class="fa fa-plus"></i> 新增</a>&nbsp;&nbsp;&nbsp;
                            <button onclick="App.deleteMulti('/content/delete')" type="button" class="btn btn-sm btn-default"><i class="fa fa-trash-o"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-upload"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-sm btn-default"><i class="fa fa-download"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                            <button onclick="$('.box-info-search').css('display')=='none'?$('.box-info-search').show('fast'):$('.box-info-search').hide('fast')"
                                    type="button" class="btn btn-sm btn-primary"><i class="fa fa-search"></i> 搜索
                            </button>
                        </div>


                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master"/></th>
                                    <th>ID</th>
                                    <th>所属分类</th>
                                    <th>标题</th>
                                    <th>子标题</th>
                                    <th>标题描述</th>
                                    <th>链接</th>
                                    <th>图片1</th>
                                    <th>图片2</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>

                                <%--<tbody>--%>

                                <%--</tbody>--%>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="../includes/copyright.jsp"/>
</div>

<jsp:include page="../includes/footer.jsp"/>
<
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core.min.js"></script>

<%--<sys:modal title="第一个模" msg="aaa"/>--%>
<sys:modal></sys:modal>

<script>
    var _dataTable;

    $(function () {
        var _columns=[
            {
                "data":function (row,type,val,meta) {
                    return "<th><input type='checkbox' id='"+row.id+"' class='minimal'/></th>";
                }
            },
            {"data":"id"},
            {"data":"tbContentCategory.name"},
            {"data":"title"},
            {"data":"subTitle"},
            {"data":"titleDesc"},
            {
                "data":function (row,type,val,meta) {
                    if(row.url==null || row.url=='') {
                        return '';
                    }
                    return '<a href="' + row.url + '" target="_blank">查看</a>';
                }
            },
            {
                "data":function (row,type,val,meta) {
                    if(row.pic==null || row.pic=='') {
                        return '';
                    }
                    return '<a href="' + row.pic + '" target="_blank">查看</a>';
                }
            },
            {
                "data":function (row,type,val,meta) {
                    if(row.pic2==null || row.pic2=='') {
                        return '';
                    }
                    return '<a href="' + row.pic2 + '" target="_blank">查看</a>';
                }
            },
            {
                "data":function(row, type, val, meta){
                    return DateTime.format(row.updated, "yyyy-MM-dd HH:mm:ss");
                }
            },
            {
                "data":function (row,type,val,meta) {
                    var detailUrl = "/content/detail?id=" + row.id;
                    return '<button onclick="App.showDetail(\'' + detailUrl + '\')" type="button" class="btn btn-sm btn-default">'
                        + '<i class="fa fa-search"></i>查看</button>&nbsp;&nbsp;'
                        + '<a href="/content/form?id=' + row.id + '" type="button" class="btn btn-sm btn-primary">'
                        + '<i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;'
                        + '<a href="#"  type="button" class="btn btn-sm btn-danger">'
                        + '<i class="fa fa-trash-o"></i>删除</a>';
                }
            }
        ];
        _dataTable=App.initDataTables("/content/page", _columns);


    });

    function search(){
        var title = $("#title").val();
        var subTitle = $("#subTitle").val();
        var titleDesc = $("#titleDesc").val();

        var params = {
            "title": title,
            "subTitle": subTitle,
            "titleDesc": titleDesc
        };

        _dataTable.settings()[0].ajax.data = params;
        _dataTable.ajax.reload();
    }


</script>

</body>

</html>

























