<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
</head>

<style>
.inner {
    position: relative;
    margin: 0px 200px;
}
.lst_recipe {
    width: 1040px;
    position: relative;
    left: -16px;
    overflow: hidden;
    margin-bottom: 56px;
}
.lst_recipe>li {
    width: 244px;
    height: 390px;
    margin: 0 0 16px 16px;
    position: relative;
    border: 1px solid #e1e1e1;
    box-sizing: border-box;
    float: left;
    background-color: #fff;
    margin: 0 0 16px 16px;
}
.lst_recipe li .call_recipe {
    display: block;
    margin: 4px;
    position: relative;
}
.lst_recipe li .call_recipe img {
    width: 234px;
    height: 234px;
    display: block;
}
.lst_recipe li .author {
    text-align: center;
    width: 0;
    z-index: 5;
    display: block;
    margin-top: -40px;
    margin-left: 90px;
    position: relative;
}
.lst_recipe li .author img {
    width: 61px;
    height: 61px;
    border-radius: 50%;
    overflow: hidden;
    display: block;
    margin: 0 auto 5px;
}
.lst_recipe li p {
    text-align: center;
    font-size: 15px;
    font-family: Microsoft YaHei,'NSB';
    line-height: 19px;
    letter-spacing: -0.025em;
    padding: 2px 20px 0;
}
.lst_recipe li p strong {
    display: block;
    overflow: hidden;
    margin-top: 5px;
    word-break: keep-all;
    white-space: nowrap;
    text-overflow: ellipsis;
}
a:link{
	color:black;
}
a:visited{
	color:black;
}
.search_cate {
    background: #fff;
    padding: 16px 25px 17px;
    vertical-align: top;
    margin-top: 0;
    height: 211px;
    border-bottom: 1px solid #e6e7e8;
    border-right: none;
    border-left: none;
    border-top: none;
}
.search_cate {
    font-size: 15px;
    padding: 8px 0;
    line-height: 1;
    min-width: 1200px;
}
.search_cate .cate_list {
    font-size: 15px;
    padding: 8px 0;
    line-height: 1;
}
.search_cate span {
    background: url(//recipe1.ezmember.co.kr/img/cate_bg.gif) left top no-repeat;
    background-size: 61px;
    color: #74b243;
    display: inline-block;
    font-weight: bold;
    width: 62px;
    height: 28px;
    margin: 0 5px 0 0;
    text-align: center;
    padding-top: 6px;
    vertical-align: middle;
    font-size: 13px;
}
.cate_list a {
    padding: 3px 9px 4px;
    color: #666;
    vertical-align: middle;
    font-size: 15px;
    line-height: 1;
   	font-weight: bold;
}
.cate_list a:hover {
    color: #74b243;
}
.sec_comment .box_write textarea {
    width: 480px;
    height: 71px;
    box-sizing: border-box;
    border: 1px solid #e6e6e6;
    resize: none;
    float: left;
    color: #c7c7c7;
    font-size: 16px;
    font-family: Microsoft YaHei,'NSL';
    text-align: center;
    padding-top: 20px;
    color: #313131;
    overflow: auto;
}
.sec_comment .box_write button {
    display: block;
    width: 123px;
    height: 71px;
    background-color: #ff6d00;
    border: none;
    color: #fff;
    font-size: 17px;
    font-family: Microsoft YaHei,'NSL';
    float: right;
}
ol, ul {
    list-style: none;
    padding: 0px;
}
.lst_comment>li {
    position: relative;
    width: 620px;
    border-bottom: 1px solid #e6e6e6;
    padding: 20px 0 20px 84px;
    clear: left;
    min-height: 64px;
}
.sec_comment .lst_comment li img {
    position: absolute;
    top: 20px;
    left: 0;
    width: 64px;
    height: 64px;
    border-radius: 50%;
    overflow: hidden;
}
.sec_comment .lst_comment li strong {
    color: #839705;
    font-family: Microsoft YaHei,'NSL';
    font-size: 15px;
    overflow: hidden;
    margin-right: 13px;
    clear: left;
}
.sec_comment .lst_comment li time {
    color: #c1c1c1;
    font-size: 13px;
    font-family: Microsoft YaHei,'NSL';
}
.sec_comment .lst_comment li p {
    color: #6d6e71;
    font-size: 13px;
    font-family: Microsoft YaHei, "NS";
    margin-top: 5px;
    line-height: 1.5;
}
</style>

<body>

<div class="container">
	<div class="search_cate">
		<div class="cate_list">
			<span>상황별</span>
			<c:forEach var="category" items="${category}">
				<c:if test="${category.cateNum>=1}">
				<c:if test="${category.cateNum<=7}">
					<a href="">${category.cateName}</a>
				</c:if>
				</c:if>
			</c:forEach>
		</div>
		<div class="cate_list">
			<span>나라별</span>
			<c:forEach var="category" items="${category}">
				<c:if test="${category.cateNum>=8}">
				<c:if test="${category.cateNum<=15}">
					<a href="">${category.cateName}</a>
				</c:if>
				</c:if>
			</c:forEach>
		</div>
		<div class="cate_list">
			<span>재료별</span>
			<a href="">육류 요리</a>
			<a href="">채소류 요리</a>
			<a href="">해산물 요리</a>
			<a href="">콩/두부 요리</a>
			<a href="">과일 요리</a>
			<a href="">달걀/유제품 요리</a>
			<a href="">면/만두요리</a>
			<a href="">김치요리</a>
			<a href="">제철재료 요리</a>
			<a href="">가공식품 요리</a>
		</div>
		<div class="cate_list">
			<span>조리법별</span>
			<a href="">밥 요리</a>
			<a href="">면 요리</a>
			<a href="">국물 요리</a>
			<a href="">찜/조림/구이 요리</a>			
			<a href="">볶음/튀김/부침 요리</a>
			<a href="">나물/샐러드 요리</a>
			<a href="">김장/절임 요리</a>
			<a href="">베이킹/디저트 요리</a>
			<a href="">양념/소스/잼</a>
			<a href="">음료/차/커피</a>
		</div>
	</div>	
	
	<ul class="lst_recipe">			
		<c:if test="${rcpAllCount==0}">
			<h1 style="font-size: 50px">등록된 레시피가 없습니다.</h1>
		</c:if>
			
		<c:if test="${rcpAllCount!=0}">
		<c:forEach var="rcpAllList" items="${rcpAllList}">
		<li>
			<a href="<%=request.getContextPath()%>/rcp/content?rcpnum=${rcpAllList.rcpnum}" data-toggle="modal" class="call_recipe">
				<img src="<%=request.getContextPath()%>/uploadRcpFile/${rcpAllList.thumbnail}">
			</a>
			<span class="author">
				<a href="<%=request.getContextPath()%>/member/mypage?memNum=${rcpAllList.memnum}">
					<img src="<%=request.getContextPath()%>/uploadFile/${rcpAllList.profile}">
				</a>
			</span>
			<p>
				<a href="<%=request.getContextPath()%>/rcp/content?rcpnum=${rcpAllList.rcpnum}" data-toggle="modal">
					<br><strong>${rcpAllList.title}</strong><p>${rcpAllList.foodname}</p>
				</a>
			</p>
		</li>
		</c:forEach>
		</c:if>		
	</ul>	
</div>

</body>
</html>