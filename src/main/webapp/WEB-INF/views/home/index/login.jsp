<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="../resources/home/login/css/login.css" />
    <link type="text/css" rel="stylesheet" href="../resources/home/login/css/zhuce.css" />
    <link href="../resources/static/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../resources/static/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="../resources/static/css/animate.css" rel="stylesheet"/>
    <link href="../resources/static/css/style.css?v=20200318" rel="stylesheet"/>
    <link href="../resources/static/ruoyi/css/ry-ui.css" rel="stylesheet"/>
</head>

<body>
<div class="main">
    <div class="main0">
        <div class="main_left">
            <img src="../resources/home/login/images/login-image-3.png" class="theimg"/>
            <img src="../resources/home/login/images/login-image-2.png" class="secimg"/>
            <img src="../resources/home/login/images/login-image-1.png" class="firimg"/>
        </div>
        <div class="main_right">
            <div class="main_r_up">
                <img src="../resources/home/login/images/user.png" />
                <div class="pp">登录</div>
            </div>
            <div class="sub">
                <p>还没有账号？
                    <a href="/index/register">
                        <span class="blue" style="margin-right: 20px">立即注册</span>
                    </a>
                </p>
                <p>
                    <a href="/index/index">
                        <span class="blue">返回首页&nbsp;&nbsp;&nbsp;</span>
                    </a>
                </p>
            </div>
            <form class="form-horizontal m-t" id="userLogin" autocomplete="off">
                <input name="_newsId_" value="${_newsId_}" style="display: none"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户名:</label>
                    <div class="col-sm-8">
                        <input id="username" name="username" minlength="2" type="text" placeholder="请输入用户名" class="form-control" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码:</label>
                    <div class="col-sm-8">
                        <input id="password" name="password" minlength="2" type="password" placeholder="请输入密码" class="form-control" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">验证码:</label>
                    <div class="col-sm-4">
                        <input id="cpacha" name="cpacha" minlength="2" type="text" placeholder="请输入验证码" class="form-control" required>
                    </div>
                    <div class="col-sm-4">
                        <img id="cpacha-img" title="点击切换验证码" style="cursor: pointer" src="get_cpacha?vl=4&w=150&h=40&type=loginCpacha" width="100px" height="30px" onclick="changeCpacha()">
                    </div>
                </div>
            </form>
            <%--<div class="txt">
                <span style="letter-spacing:8px;">用户名:</span>
                <input name="" type="text" class="txtphone" placeholder="请输入用户名" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入用户名&#39;">
            </div>
            <div class="txt">
                <span style="letter-spacing:4px;">登录密码:</span>
                <input name="" type="text" class="txtphone" placeholder="请输入登录密码" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入登录密码&#39;">
            </div>
            <div class="txt">
                <span style="letter-spacing:8px;">验证码:</span>
                <input style="width: 22%" type="text" name="cpacha" id="cpacha" value="" placeholder="请输入验证码" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入验证码&#39;">
                <img id="cpacha-img" title="点击切换验证码" style="cursor: pointer" src="get_cpacha?vl=4&w=150&h=40&type=loginCpacha" width="100px" height="30px" onclick="changeCpacha()">
            </div>--%>
            <%--<div class="txt">
                <span style=" float:left;letter-spacing:8px;">验证码:</span>
                <input name="" type="text" class="txtyzm" placeholder="请输入验证码"/>
                <img src="../resources/home/login/images/yanzhengma.png" class="yzmimg"/>
            </div>--%>
            <%--<div class="xieyi">
                <input name="" type="checkbox" value="" checked="checked"/>
                记住我 <a href="password.html"><span class="blue" style=" padding-left:130px; cursor:pointer">忘记密码?</span></a>
            </div>--%>
            <div class="xiayibu" onclick="loginbt()">登录</div>
        </div>
    </div>
</div>

<div class="footer">
    <div class="footer0">
        <div class="footer_l">使用条款 | 隐私保护</div>
        <div class="footer_r">© 2020 HH新闻</div>
    </div>
</div>
<a id="scroll-up" href="#" class="btn btn-sm display"><i class="fa fa-angle-double-up"></i></a>
<script src="../resources/static/js/jquery.min.js"></script>
<script src="../resources/static/js/bootstrap.min.js"></script>
<!-- jquery-validate 表单验证插件 -->
<script src="../resources/static/ajax/libs/validate/jquery.validate.min.js"></script>
<script src="../resources/static/ajax/libs/validate/messages_zh.min.js"></script>
<script src="../resources/static/ajax/libs/validate/jquery.validate.extend.js"></script>
<!-- 遮罩层 -->
<script src="../resources/static/ajax/libs/blockUI/jquery.blockUI.js"></script>
<script src="../resources/static/ajax/libs/iCheck/icheck.min.js"></script>
<script src="../resources/static/ajax/libs/layer/layer.min.js"></script>
<script src="../resources/static/ajax/libs/layui/layui.js"></script>
<script src="../resources/static/ruoyi/js/common.js?v=4.2.0"></script>
<script src="../resources/static/ruoyi/js/ry-ui.js?v=4.2.0"></script>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        var height=$(document).height();
        $('.main').css('height',height);
    })

    function changeCpacha() {
        $("#cpacha-img").attr("src", 'get_cpacha?vl=4&w=150&h=40&type=loginCpacha&t=' + new Date().getTime())
    }

    function loginbt() {
        if ($.validate.form()) {
            $.modal.loading("正在提交数据，请稍后...");
            $.ajax({
                url:'<%=request.getContextPath()%>/login/login',
                type:'POST',
                data:$('#userLogin').serialize(),
                error:function () {
                    $.modal.closeLoading();
                    $.modal.alertError("处理失败！请联系管理员！");
                },
                success:function (d) {
                    $.modal.closeLoading();
                    if(d.code==0){
                        layer.msg('登录成功', {
                            icon: 1, time: 1000, end: function () {
                                if (${_newsId_==null}){
                                    window.location.href = "<%=request.getContextPath()%>/";
                                } else{
                                    window.location.href = "<%=request.getContextPath()%>/news/detail?id=${_newsId_}";
                                }
                            }
                        });
                    }else {
                        $('#cpacha-img').click();
                        layer.alert('登录失败！'+d.msg, {icon: 5});
                    }
                }
            });
        }
    }

</script>
</html>
