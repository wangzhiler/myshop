var App = function() {

    // iCheck
    var _masterCheckbox;
    var _checkbox;

    // 数组 用于存放ID
    var _idArray;

    //默认的dropzone参数
    var defaultDropzoneOpts = {
        url: "",
        paramName: "dropFile", // 传到后台的参数名称
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

    /**
     * 私有方法，初始化icheck
     */
    var handlerInitCheckbox = function () {
        console.log("handlerInitCheckbox");
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });

        //获取控制端CheckBox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
        //获取全部checkbox集合
        _checkbox=$('input[type="checkbox"].minimal');

    }

    /**
     * CheckBox全选功能
     */
    var handlerCheckboxAll=function() {
        console.log("handlerCheckboxAll");
        _masterCheckbox.on("ifClicked", function (e) {
            //返回true 表示未选中
            if(e.target.checked){
                _checkbox.iCheck("uncheck");
            }

            //选中状态
            else{
                _checkbox.iCheck("check");
            }
        });

    };

    var handlerDelete=function(url){

    }

    /**
     * 批量删除
     */
    var handlerDeleteMulti=function(url) {
        _idArray = new Array();

        //将选中的元素放入数组
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefined" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        //判断用户是否选择了数据项
        if (_idArray.length == 0) {
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项");
        }
        else{
            $("#modal-message").html("您确定删除数据吗？");
        }

        //点击删除按钮时弹出模态框
        $("#modal-default").modal("show");

        //如果用户选择了数据项调用删除方法
        $("#btnModalOk").bind("click", function () {
            del()
        });

        /**
         * 当前私有函数的私有函数,删除数据
         */
        function del() {
            $("#modal-default").modal("hide");
            // console.log("hello");

            // 如果没有选择数据项 则关闭模态框
            if(_idArray.length==0) {
                //...
            }

            //删除操作
            else{
                setTimeout(
                    function () {
                        $.ajax({
                            "url": url,
                            "type": "POST",
                            "data": {"ids": _idArray.toString()},
                            "dataType": "JSON",
                            "success": function (data) {

                                //请求成功后，无论成功失败，都需要弹出模态框进行提示，所以先解绑原来的click事件
                                $("#btnModalOk").unbind("click");

                                //删除成功
                                if (data.status == 200) {
                                    //刷新页面
                                    $("#btnModalOk").bind("click", function () {
                                        window.location.reload();
                                    });

                                }
                                //删除失败
                                else {
                                    //确定按钮事件改为隐藏模态框
                                    $("#btnModalOk").bind("click", function () {
                                        $("#modal-default").modal('hide');
                                        console.log("hideeee");
                                    });
                                }

                                //因为无论如何都需要提示信息，所以这里的模态框是必须调用的
                                $("#modal-message").html(data.message);
                                $("#modal-default").modal("show");
                            }
                        },500);
                    }
                )

            }
        }

    };

    /**
     * 初始化DataTables
     */
    var handlerInitDataTables=function(url, columns) {
        var _dataTable=$("#dataTable").DataTable({
            "lengthChange":false,
            "paging":true,
            "searching":false,
            "processing":true,
            "serverSide":true,
            "ajax":{
                "url": url
            },
            "deferRender":true,
            "columns": columns,
            "language":{
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback":function(settings){
                handlerInitCheckbox();
                handlerCheckboxAll();            }
        });

        return _dataTable;
    };


    /**
     * 初始化ZTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTree = function (url, autoParam, callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam
            }
        };

        $.fn.zTree.init($("#myTree"), setting);
        console.log("mytreeeeee");

        $("#btnModalOk").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();

            //未选择
            if(nodes.length==0) {
                alert("请选择一个结点");
            }

            else{
                callback(nodes);
            }
        })
    };


    /**
     * 初始化Dropzone
     * @param opts
     */
    var handlerInitDropzone = function (opts) {
        //关闭dropzone的自动发现功能
        Dropzone.autoDiscover = false;
        //继承
        $.extend(defaultDropzoneOpts, opts);
        console.log(defaultDropzoneOpts.paramName);
        new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    };



    /**
     * 查看详情
     * @param url
     */
    function handlerShowDetail(url) {
        //这里通过ajax请求html的方式将jsp装载进入模态框中
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    }


    return {
        /**
         * 初始化
         */
        init: function () {
            //关闭dropzone的自动发现功能
            // Dropzone.autoDiscover = false;
            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        /**
         * 批量删除
         * @param url
         */
        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },

        /**
         * 初始化DataTables
         * @param url
         * @param columns
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },

        /**
         * 初始化ZTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree: function (url, autoParam, callback) {
            handlerInitZTree(url, autoParam, callback);
        },


        /**
         * 初始化Dropzone
         * @param opts
         */
        initDropzone: function (opts) {
            console.log("handlerInitDropzone")
            handlerInitDropzone(opts);
        },


        /**
         * 显示详情
         * @param url
         */
        showDetail:function(url) {
            handlerShowDetail(url);
        }
    };
}();

$(document).ready(function () {
    App.init();
});