<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--${title }中title对应HomeNewsController中categoryList方法中model.addObject("title", newsCategory.getName() + "分类下的新闻信息");中的title--%>
    <title>HH新闻${title }</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="stylesheet" type="text/css" href="../resources/home/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../resources/home/css/nprogress.css">
    <link rel="stylesheet" type="text/css" href="../resources/home/css/style.css">
    <link rel="stylesheet" type="text/css" href="../resources/home/css/font-awesome.min.css">
    <link rel="apple-touch-icon-precomposed" href="../resources/home/images/icon.png">
    <link rel="shortcut icon" href="images/favicon.ico">
    <script src="../resources/home/js/jquery-2.1.4.min.js"></script>
    <script src="../resources/home/js/nprogress.js"></script>
    <script src="../resources/home/js/jquery.lazyload.min.js"></script>
    <script src="../resources/home/js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="../resources/home/js/html5shiv.min.js" type="text/javascript"></script>
    <script src="../resources/home/js/respond.min.js" type="text/javascript"></script>
    <script src="../resources/home/js/selectivizr-min.js" type="text/javascript"></script>
    <script>
        function addFavorite(url, title) {
            try {
                window.external.addFavorite(url, title);
            } catch (e) {
                try {
                    window.sidebar.addPanel(title, url, '');
                } catch (e) {
                    alert("请按Ctrl+D 键添加到收藏夹", 'notice');
                }
            }
        }
    </script>
</head>
<body class="user-select">
<header class="header">
    <nav class="navbar navbar-default" id="navbar">
        <div class="container">
            <div class="header-topbar hidden-xs link-border">
                <ul class="site-nav topmenu">
                    <!--<li><a href="#" >标签云</a></li>
                    <li><a href="#" rel="nofollow" >读者墙</a></li>-->
                    <li><a href="#" onclick="addFavorite('http://localhost:8080/index/index', 'HH新闻')" title="收藏" >
                        <i class="fa fa-rss">
                        </i> 收藏
                    </a></li>
                </ul>
                【Enjoy News】</div>
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header-navbar" aria-expanded="false"> <span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                <h1 class="logo hvr-bounce-in"><a href="/index/index" title="HH新闻"><img src="../resources/home/images/logo.png" alt="HH新闻"></a></h1>
            </div>
            <div class="collapse navbar-collapse" id="header-navbar">
                <form class="navbar-form visible-xs" action="../news/search_list" method="get">
                    <div class="input-group">
                        <input type="text" name="keyword" class="form-control" placeholder="请输入关键字" maxlength="20" autocomplete="off" value="${keyword }">
                        <span class="input-group-btn">
		<button class="btn btn-default btn-search" name="search" type="submit">搜索</button>
		</span> </div>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a data-cont="HH新闻" title="HH新闻" href="/index/index">首页</a></li>
                    <c:forEach items="${newsCategoryList }" var="newsCategory">
                        <li><a data-cont="${newsCategory.name }" title="${newsCategory.name }" href="../news/category_list?cid=${newsCategory.id }">${newsCategory.name }</a></li>
                    </c:forEach>
                    <li><a data-cont="登录" title="登录" href="/index/login">登录</a></li>
                    <li><a data-cont="注册" title="注册" href="/index/register">注册</a></li>
                    <!--<li><a data-cont="404" title="404" href="404.html">404</a></li>
                    <li><a data-cont="IT技术笔记" title="IT技术笔记" href="#" >IT技术笔记</a></li>
                    <li><a data-cont="源码分享" title="源码分享" href="#" >源码分享</a></li>-->
                </ul>
            </div>
        </div>
    </nav>
</header>