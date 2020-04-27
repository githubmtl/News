<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>
<section class="container">
    <div class="content-wrap">
        <div class="content">
            <%--<div id="focusslide" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#focusslide" data-slide-to="0" class="active"></li>
                    <li data-target="#focusslide" data-slide-to="1"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <a href="#" target="_blank" title="HH新闻网站" >
                            <img src="../resources/home/images/cover-pic1.jpg" alt="HH新闻网站" class="img-responsive"></a>
                    </div>
                    <div class="item">
                        <a href="#" target="_blank" title="看新闻" >
                            <img src="../resources/home/images/cover-pic2.jpg" alt="看新闻" class="img-responsive"></a>
                    </div>
                </div>
            </div>--%>
            <div class="title">
                <h3>我的历史浏览</h3>
            </div>
            <c:forEach items="${newsList }" var="news">
                <article class="excerpt excerpt-1" style="">
                <%--${news.id }中的news对应的是上面<c:forEach items="${newsList }" var="news">中定义的变量var="news"--%>
                    <a class="focus" href="../news/detail?id=${news.id }" title="${news.title }" target="_blank" ><img class="thumb" data-original="${news.photo }" src="${news.photo }" alt="${news.title }"  style="display: inline;"></a>
                    <header>
                        <!--category_list对应HomeNewsController中categoryList方法中@RequestMapping(value = "/category_list",method = RequestMethod.GET)中的category_list-->
                        <a class="cat" href="../news/category_list?cid=${news.categoryId }" title="${news.newsCategory.name }" >${news.newsCategory.name }<i></i></a>
                        <h2>
                            <a href="../news/detail?id=${news.id }" title="${news.title }" target="_blank" >${news.title }</a>
                        </h2>
                    </header>
                    <p class="meta">
                        <time class="time"><i class="glyphicon glyphicon-time"></i> <fmt:formatDate value="${news.createTime }" pattern="yyyy-MM-dd hh:mm:ss" /></time>
                        <span class="views"><i class="glyphicon glyphicon-eye-open"></i> ${news.viewNumber }</span>
                        <a class="comment" href="../news/detail?id=${news.id }#comment" title="评论" target="_blank" ><i class="glyphicon glyphicon-comment"></i>${news.commentNumber }</a>
                    </p>
                    <p class="note">${news.abstrs }</p>
                </article>
            </c:forEach>
            <div class="ias_trigger" style="display:none;"><a href="javascript:;">查看更多</a></div>
        </div>
    </div>
    <%@ include file="../common/sidebar.jsp" %>
</section>
<%@ include file="../common/footer.jsp" %>