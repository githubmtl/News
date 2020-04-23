<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>注册</title>
    <link type="text/css" rel="stylesheet" href="../resources/home/login/css/zhuce.css" />
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
            <img src="../resources/home/login/images/zhuce-image-3.png" class="theimg"/>
            <img src="../resources/home/login/images/zhuce-image-2.png" class="secimg"/>
            <img src="../resources/home/login/images/zhuce-image-1.png" class="firimg"/>
        </div>
        <div class="main_right">
            <div class="main_r_up">
                <img src="../resources/home/login/images/user.png" />
                <div class="pp">注册</div>
            </div>
            <div class="sub">
                <p>已经注册？
                    <a href="/index/login">
                        <span class="blue">请登录</span>
                    </a>
                </p>
                <p>
                    <a href="/index/index">
                        <span class="blue">返回首页&nbsp;&nbsp;&nbsp;</span>
                    </a>
                </p>
            </div>
            <div class="txt">
                <span style="letter-spacing:10px;">用户名:</span>
                <input name="" type="text" class="txtphone" placeholder="请输入用户名" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入用户名&#39;">
            </div>
            <div class="txt">
                <span style="letter-spacing:12px;">密 码:</span>
                <input name="" type="password" class="txtphone" placeholder="请输入密码" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入密码&#39;">
            </div>
            <div class="txt">
                <span style="letter-spacing:25px;">性别:</span>
                <select>
                    <option value="1">男</option>
                    <option value="0">女</option>
                </select>
            </div>
            <div class="txt">
                <span style="letter-spacing:12px;">年 龄:</span>
                <input name="" type="number" class="txtphone" placeholder="请输入年龄" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入年龄&#39;">
            </div>
            <div class="txt">
                <span style="letter-spacing:12px;">地 址:</span>
                <input name="" type="text" class="txtphone" placeholder="请输入地址" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入地址&#39;">
            </div>
            <div class="txt">
                <span style="letter-spacing:10px;">验证码:</span>
                <input style="width: 22%" type="text" name="cpacha" id="cpacha" value="" placeholder="请输入验证码" onfocus="this.placeholder=&#39;&#39;" onblur="this.placeholder=&#39;请输入验证码&#39;">
                <img id="cpacha-img" title="点击切换验证码" style="cursor: pointer" src="get_cpacha?vl=4&w=150&h=40&type=loginCpacha" width="100px" height="30px" onclick="changeCpacha()">
            </div>
            <%--<div class="txt">
                <span style=" float:left;letter-spacing:10px;">验证码:</span>
                <input name="" type="text" class="txtyzm" placeholder="请输入验证码"/>
                <img src="../resources/home/login/images/yanzhengma.png" class="yzmimg"/>
            </div>--%>
            <%--<div class="xieyi">
                <input name="" type="checkbox" value="" checked="checked"/>
                我已经阅读并遵守 <span class="blue" style="cursor:pointer">《光子金融服务协议》</span>
            </div>--%>
            <a href="zhuce1.html"><div class="xiayibu">注册</div></a>
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