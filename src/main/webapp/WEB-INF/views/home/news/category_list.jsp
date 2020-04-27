<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/header.jsp" %>
<section class="container">
    <div class="content-wrap">
        <div class="content" id="news-list">
            <article class="excerpt-minic excerpt-minic-index">
                <h2><span class="red">【推荐】</span><a target="_blank" href="<%=request.getContextPath()%>/news/detail?id=${tj_news.id }" title="${tj_news.title }" >${tj_news.title }</a>
                </h2>
                <p class="note">${tj_news.abstrs }</p>
            </article>
            <div class="title">
                <!--newsCategory对应HomeNewsController中categoryList方法中model.addObject("newsCategory", newsCategory);中的"newsCategory"-->
                <h3>${newsCategory.name }</h3>
            </div>
            <%--newsList对应HomeNewsController中categoryList方法中model.addObject("newsList", newsService.findList(queryMap));中的"newsList"--%>
            <c:forEach items="${newsList }" var="news">
                <article class="excerpt excerpt-1" style="">
                <%--${news.id }中的news对应的是上面<c:forEach items="${newsList }" var="news">中定义的变量var="news"--%>
                    <%--id对应HomeNewsController中detail方法中model.addObject("news", newsService.find(id));中的id--%>
                    <a class="focus" href="../news/detail?id=${news.id }" title="${news.title }" target="_blank" >
                         <img class="thumb" data-original="${news.photo }" src="${news.photo }" alt="${news.title }"  style="display: inline;">
                    </a>
                    <header>
                        <%--category_list对应HomeNewsController中categoryList方法中@RequestMapping(value = "/category_list",method = RequestMethod.GET)中的category_list--%>
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
            <div class="ias_trigger"><a href="javascript:;" id="load-more-btn">查看更多</a></div>
        </div>
    </div>
    <%@ include file="../common/sidebar.jsp" %>
</section>
<%@ include file="../common/footer.jsp" %>
<script>
/*当前页，从第2页开始，对应下面data:{page:page++,rows:10},中的前一个page*/
var page = 2;
/*整个页面加载完成后再运行*/
$(document).ready(function() {
    $("#load-more-btn").click(function () {
        if ($("#load-more-btn").attr('data-key') == 'all')return;
        $("#load-more-btn").text('查看更多');
        $.ajax({
            <!-- get_category_list对应HomeNewsController中getCategoryList方法中@RequestMapping(value = "/get_category_list",method = RequestMethod.POST)中的get_category_list -->
            url:'../news/get_category_list',
            type:'post',
            // page对应上面定义的变量var page = 2;即page/admin/Page中的page，起始页码； rows对应page/admin/Page中的rows，即每页显示的条数，即点击查看更多10条10条的显示
            data:{page:page++,rows:10,cid:'${newsCategory.id }'},
            dataType:'json',
            success:function(data) {
                if (data.type == 'success'){
                    $("#load-more-btn").text('查看更多');
                    // data.newsList;中newsList对应HomeNewsController中getCategoryList方法中ret.put("newsList", newsService.findList(queryMap));中的"newsList"
                    var newsList = data.newsList;
                    if (newsList.length == 0){
                        $("#load-more-btn").text('没有更多了！');
                        $("#load-more-btn").attr('data-key', 'all');
                    }
                    var html = '';
                    for (var i=0; i<newsList.length; i++) {
                        var article = '<article class="excerpt excerpt-1" style="">';
                        article += '<a class="focus" href="../news/detail?id='+ newsList[i].id +'" title="'+ newsList[i].title +'" target="_blank" >';
                        article += '<img class="thumb" data-original="'+ newsList[i].photo +'" src="'+ newsList[i].photo +'" alt="'+ newsList[i].title +'"  style="display: inline;"></a>';
                        article += '<header><a class="cat" href="../news/category_list?cid='+ newsList[i].categoryId +'" title="'+ newsList[i].newsCategory.name +'" >'+ newsList[i].newsCategory.name +'<i></i></a>';
                        article += '<h2><a href="../news/detail?id='+ newsList[i].id +'" title="'+ newsList[i].title +'" target="_blank" >'+ newsList[i].title +'</a></h2></header>';
                        article += '<p class="meta"><time class="time"><i class="glyphicon glyphicon-time"></i>'+ format(newsList[i].createTime) +'</time>';
                        article += '<span class="views"><i class="glyphicon glyphicon-eye-open"></i>'+ newsList[i].viewNumber +'</span>';
                        article += '<a class="comment" href="../news/detail?id='+ newsList[i].id +'#comment" title="评论" target="_blank" ><i class="glyphicon glyphicon-comment"></i>'+ newsList[i].commentNumber +'</a></p>';
                        article += '<p class="note">'+ newsList[i].abstrs +'</p>';
                        article += '</article>';
                        html += article;
                    }
                    $("#load-more-btn").parent("div").before(html);
                }
            }
        });
    });
});
</script>