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
            <label>角色名称：</label><input id="search-name" class="wu-text" style="width:100px">
            <a href="#" id="search-btn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar"></table>
</div>
<style>
.selected {
	background: red;
}
</style>
<!-- Begin of easyui-dialog -->
<!-- 添加窗口 -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">角色名称:</td>
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写角色名称'" /></td>
            </tr>
            <tr>
                <td align="right">角色备注:</td>
                <td><textarea name="remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<!-- 修改窗口 -->
<div id="edit-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:450px; padding:10px;">
	<form id="edit-form" method="post">
		<input type="hidden" name="id" id="edit-id">
        <table>
            <tr>
                <td width="60" align="right">角色名称:</td>
                <td><input type="text" id="edit-name" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写角色名称'" /></td>
            </tr>
            <tr>
                <td align="right">角色备注:</td>
                <td><textarea id="edit-remark" name="remark" rows="6" class="wu-textarea" style="width:260px"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<!-- 选择权限弹窗 -->
<div id="select-authority-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 230px;height: 450px; padding:10px;">
    <ul id="authority-tree" url="get_all_menu" checkbox="true"></ul>
</div>
<%@include file="../common/footer.jsp"%>
<!-- End of easyui-dialog -->

<script type="text/javascript">
	/**
	* 添加记录
	*/
	function add(){
		var validate = $("#add-form").form("validate");
		if (!validate) {
			$.messager.alert("消息提醒","请检查你输入的数据！","warning");
			return;
		}
		var data = $("#add-form").serialize();
		$.ajax({
			url:'../../admin/role/add',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if (data.type == 'success') {
					$.messager.alert('信息提示','添加成功！','info');
					$('#add-dialog').dialog('close');
					<!-- 添加成功后重新加载 -->
					$('#data-datagrid').datagrid('reload');
				} else
				{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}

	/**
	 * 选择图标icon
	 */
	function selectIcon() {
		if ($("#icons-table").children().length <= 0) {
			$.ajax({
			url:'../../admin/role/get_icons',
			dataType:'json',
			type: 'post',
			success:function(data){
				if (data.type == 'success') {
					var icons = data.content;
					var table = '';
					for (var i=0; i<icons.length; i++) {
						var tbody = '<td class="icon-td"><a onclick="selected(this)" href="javascript:void(0)" class="' + icons[i] + '">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></td>';
						if (i == 0) {
							table += '<tr>' + tbody;
							continue;
						}
						if ((i+1)%24 === 0) {
							// console.log(i + '---' + i%12);
							table += tbody + '</tr><tr>';
							continue;
						}
						table += tbody;
					}
					table += '</tr>';
					$("#icons-table").append(table);
				} else
				{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
		}
	}

	/**
	* 修改记录
	*/
	function edit(){
		var validate = $("#edit-form").form("validate");
		if (!validate) {
			$.messager.alert("消息提醒","请检查你输入的数据！","warning");
			return;
		}
		<!-- 表单序列化 -->
		var data = $("#edit-form").serialize();
		$.ajax({
			url:'edit',
			dataType:'json',
			type:'post',
			data:data,
			success:function(data){
				if (data.type == 'success') {
					$.messager.alert('信息提示','修改成功！','info');
					$('#edit-dialog').dialog('close');
					<!-- 添加成功后重新加载 -->
					$('#data-datagrid').datagrid('reload');
				} else
				{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}

	/**
	* 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var item = $('#data-datagrid').datagrid('getSelected');
				$.ajax({
			        url:'../../admin/role/delete',
			        dataType:'json',
			        type:'post',
			        data:{id:item.id},
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
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加角色信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');
                }
            }]
        });
	}

	/**
	* 打开修改窗口
	*/
	function openEdit(){
		// $('#edit-form').form('clear');
		<!-- 获取到选中的一行 -->
		var item = $('#data-datagrid').datagrid('getSelected');
		if (item == null || item.length == 0) {
			$.messager.alert('信息提示','请选择要修改的数据！','info');
			return;
		}
		$('#edit-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#edit-dialog').dialog('close');
                }
            }],
			onBeforeOpen:function() {
				$('#edit-id').val(item.id);
				$('#edit-name').val(item.name);
				$('#edit-remark').val(item.remark);
			}
        });
	}

	// 某个角色已经拥有的权限
    var existAuthority = null;
	function isAdded(row, rows) {
        for (var k=0; k<existAuthority.length; k++) {
            if (existAuthority[k].menuId == row.id && haveParent(rows, row.parentId)) {
                console.log(existAuthority[k].menuId + '---' + row.parentId);
                return true;
            }
        }
        return false;
    }

    // 判断是否有父分类
    function haveParent(rows, parentId) {
        for(var i=0; i<rows.length; i++){
            if (rows[i].id == parentId) {
                if (rows[i].parentId != 0) return true;
            }
        }
        return false;
    }

	// 判断是否有父类
    function exists(rows, parentId){
        for(var i=0; i<rows.length; i++){
            if (rows[i].id == parentId) return true;
        }
        return false;
    }

    // 转换原始数据至符合tree的要求
    function convert(rows){
        var nodes = [];
        // get the top level nodes
        // 首先获取所有的父分类
        for(var i=0; i<rows.length; i++){
            var row = rows[i];
            if (!exists(rows, row.parentId)){
                nodes.push({
                    id:row.id,
                    text:row.name
                });
            }
        }

        var toDo = [];
        for(var i=0; i<nodes.length; i++){
            // 将父分类nodes中的数据存放地址指向toDo数组，所以toDo内容改变时，指向同一内存地址的nodes内容也会改变
            toDo.push(nodes[i]);
        }
        while(toDo.length){
            // 找到所有二级分类以及新增加的父分类的下一级分类
            var node = toDo.shift();	// the parent node
            // get the children nodes
            for(var i=0; i<rows.length; i++){
                var row = rows[i];
                // 此时如果不满足条件，toDo.push(child); 语句将不会继续执行，toDo中将不会继续push。
                // var node = toDo.shift(); 语句继续执行，toDo中数据一直减少，直到循环完成，找到所有分类。
                if (row.parentId == node.id){
                    var child = {id:row.id,text:row.name};
                    if (isAdded(row, rows)) {
                        child.checked = true;
                    }
                    if (node.children){
                        node.children.push(child);
                    } else {
                        node.children = [child];
                    }
                    // 把刚才创建的孩子再添加到父分类的数组中，即二级分类作为了新的父分类。
                    // 继续循环，三级分类作为新的父分类，以此类推...直到找到所有分类。
                    toDo.push(child);
                }
            }
        }
        return nodes;
    }

    // 打开权限选择框
    function selectAuthority(roleId) {
        $('#select-authority-dialog').dialog({
            closed: false,
            modal:true,
            title: "选择权限信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: function() {
                    <!-- 获取选中内容对应的id，不会获取处于半选状态内容的id。比如：系统设置=菜单管理+角色管理，当选中菜单管理时，只会得到菜单管理的id。 -->
                    var checkedNodes = $("#authority-tree").tree('getChecked');
                    var ids = '';
                    for (var i=0; i<checkedNodes.length; i++) {
                        ids += checkedNodes[i].id + ',';
                    }
                    <!-- 获取处于半选状态的内容对应的id。比如：系统设置：菜单管理+角色管理，当选中菜单管理时，会得到菜单管理和系统设置的id。选中菜单管理和角色管理时，会得到三者的id -->
                    var checkedParentNodes = $("#authority-tree").tree('getChecked','indeterminate');
                    for (var i=0; i<checkedParentNodes.length; i++) {
                        ids += checkedParentNodes[i].id + ',';
                    }
                    // console.log(ids);
                    if (ids != '') {
                        $.ajax({
                            url:'add_authority',
                            type:'post',
                            <!-- roleId为函数中传递的参数 -->
                            data:{ids:ids,roleId:roleId},
                            dataType:'json',
                            success:function (data) {
                                if (data.type == 'success') {
                                    $.messager.alert('信息提示','权限编辑成功！','info');
                                    $('#select-authority-dialog').dialog('close');
                                } else {
                                    $.messager.alert('信息提示',data.msg,'info');
                                }
                            }
                        });
                    } else {
                        $.messager.alert('信息提示','请至少选择一个权限！','info');
                    }
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#select-authority-dialog').dialog('close');
                }
            }],
            <!-- 按树形结构加载父子结点 -->
            onBeforeOpen:function () {
                // 首先获取该角色已经拥有的权限
                $.ajax({
                    url:'get_role_authority',
                    data:{roleId:roleId},
                    type:'post',
                    dataType:'json',
                    success:function (data) {
                        existAuthority = data;
                        $("#authority-tree").tree({
                            loadFilter: function(rows) {
                                return convert(rows);
                            }
                        });
                    }
                });
            }
        });
    }

	// 搜索按钮监听
	$("#search-btn").click(function() {
		$('#data-datagrid').datagrid('reload',{
			name:$("#search-name").val()
		});
	});

	/**
	* 载(三声)入数据
	*/
	$('#data-datagrid').datagrid({
        <!-- list对应于RoleController中@RequestMapping(value = "/list", method = RequestMethod.POST)中的list -->
        url:'list',
		<!-- 去掉之后刷新功能才有效 -->
		<!-- loadFilter:pagerFilter, -->
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
            { field:'chk',checkbox:true},
			{ field:'name',title:'角色名称',width:100,sortable:true},
			{ field:'remark',title:'角色备注',width:180,sortable:true},
			{ field:'icon',title:'权限操作',width:100,formatter:function(value,row,index) {
				var test = '<a class="authority-edit" onclick="selectAuthority('+ row.id +')"></a>'
				return test;
			}},
		]],
        <!-- 事件 -->
        onLoadSuccess:function (data) {
            $('.authority-edit').linkbutton({text:'编辑权限',plain:true,iconCls:'icon-edit'});
        }
	});
</script>