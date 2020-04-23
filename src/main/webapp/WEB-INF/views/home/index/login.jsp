<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="../resources/home/login/css/login.css" />
    <script type="text/javascript" src="../resources/home/login/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var height=$(document).height();
            $('.main').css('height',height);
        })

        function changeCpacha() {
            $("#cpacha-img").attr("src", 'get_cpacha?vl=4&w=150&h=40&type=loginCpacha&t=' + new Date().getTime())
        }
    </script>
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
                        <span class="blue">立即注册</span>
                    </a>
                </p>
                <p>
                    <a href="/index/index">
                        <span class="blue">返回首页&nbsp;&nbsp;&nbsp;</span>
                    </a>
                </p>
            </div>
            <div class="txt">
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
            </div>
            <%--<div class="txt">
                <span style=" float:left;letter-spacing:8px;">验证码:</span>
                <input name="" type="text" class="txtyzm" placeholder="请输入验证码"/>
                <img src="../resources/home/login/images/yanzhengma.png" class="yzmimg"/>
            </div>--%>
            <%--<div class="xieyi">
                <input name="" type="checkbox" value="" checked="checked"/>
                记住我 <a href="password.html"><span class="blue" style=" padding-left:130px; cursor:pointer">忘记密码?</span></a>
            </div>--%>
            <div class="xiayibu">登录</div>
        </div>
    </div>
</div>

<div class="footer">
    <div class="footer0">
        <div class="footer_l">使用条款 | 隐私保护</div>
        <div class="footer_r">© 2020 HH新闻</div>
    </div>
</div>
</body>
</html>
