<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../common/header.jsp"%>
<%--<jsp:include page="../common/header.jsp" flush="true"/>--%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <%@include file="../common/menus.jsp"%>
        </div>
        <div class="wu-toolbar-search">
            <label>新闻标题：</label><input id="search-title" class="wu-text" style="width:100px">
            <label>新闻作者：</label><input id="search-author" class="wu-text" style="width:100px">
            <label>所属分类：</label>
            <select id="search-category" class="easyui-combobox" panelHeight="auto" style="width:120px">
                <option value="-1">全部</option>
                <!-- roleList对应UserController中model.addObject("roleList", roleService.findList(queryMap));中的roleList -->
                <c:forEach items="${newsCategoryList }" var="category">
                    <option value="${category.id }">${category.name}</option>
                </c:forEach>
            </select>
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<!-- Begin of easyui-dialog -->
<%@include file="../common/footer.jsp"%>
<!-- End of easyui-dialog -->

<script type="text/javascript">

	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
                var item = $('#data-datagrid').datagrid('getSelections');
                if (item == null || item.length == 0) {
                    $.messager.alert('信息提示','请选择要删除的数据！','info');
                    return;
                }
				$.ajax({
			        url:'delete',
			        dataType:'json',
			        type:'post',
			        data:{id:item[0].id},
			        success:function(data){
				        if (data.type == 'success') {
					        $.messager.alert('信息提示','删除成功！','info');
					        <!-- 删除成功后重新加载 -->
					        $('#data-datagrid').datagrid('reload');
				        } else {
					        $.messager.alert('信息提示',data.msg,'warning');
				        }
			        }
		        });
			}
		});
	}

	/**
	* 打开添加窗口
	*/
	function openAdd(){
		// $('#add-form').form('clear');
        window.location.href = 'add';
	}

	/**
	* 打开修改窗口
	*/
	function openEdit(){
		// $('#edit-form').form('clear');
        var item = $('#data-datagrid').datagrid('getSelected');
        if (item == null || item.length == 0) {
            $.messager.alert('信息提示','请选择要编辑的数据！','info');
            return;
        }
        window.location.href = 'edit?id=' + item.id;
	}

	// 搜索按钮监听
	$("#search-btn").click(function() {
        var option = {title:$("#search-title").val(),categoryId:$("#search-category").combobox('getValue'),author:$("#search-author").val()};
		$('#data-datagrid').datagrid('reload', option);
	});

	/**
	* 载(三声)入数据
	*/
	$('#data-datagrid').datagrid({
        url:'list',
        <!-- 属性 -->
		rownumbers:true,
		singleSelect:true,
		pageSize:20,
		pagination:true,
		multiSort:true,
		fitColumns:true,
		idField:'id',
		treeField:'name',
		fit:true,
		columns:[[
			<!--{ checkbox:true},-->
			<!-- field的值对应数据库表user中的字段 -->
            { field:'chk',checkbox:true},
			{ field:'title',title:'标题',width:200,formatter:function (value,row,index) {
			    /*../跳转到localhost:8080/admin，../../跳转到localhost:8080/*/
                return '<a href="../../news/detail?id='+ row.id +'" target="_blank">' + value + '</a>';
            }},
			{ field:'categoryId',title:'分类',width:60,formatter:function (value,row,index) {
			    return row.newsCategory.name;
            }},
			{ field:'author',title:'作者',width:80},
			{ field:'tags',title:'标签',width:100},
			{ field:'viewNumber',title:'浏览量',sortable:true,width:50},
			{ field:'commentNumber',title:'评论数',sortable:true,width:50}
		]],
	});
</script>