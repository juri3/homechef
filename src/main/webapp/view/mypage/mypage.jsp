<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>

<title>Insert title here</title>
</head>

<style>

</style>

<body>

<div id="content">
	<div class="user_information">
		<div class="inner">
			<img src="<%=request.getContextPath()%>/uploadFile/${member.profile}">
			<strong>${member.name}</strong>
			<span>팔로워 ${followerCount}명</span>
			<p class="dsc">
				<br><br>
				<strong>${member.selfIntroduction}</strong>
				<br>
			</p>
			<div class="btn_area">
				<c:if test="${loginNum==member.memNum}">
				<button onclick="javascript:window:location='<%=request.getContextPath()%>/member/modifyForm?memNum=${member.memNum}'">
				프로필 수정
				</button>
				</c:if>
				<c:if test="${loginNum!=member.memNum}">
					<c:if test="${checkFollow==0}">
					<button onclick="javascript:window:location='<%=request.getContextPath()%>/member/follow?memNum=${member.memNum}'">팔로우</button>
					</c:if>
				</c:if>
				<c:if test="${loginNum!=member.memNum}">
					<c:if test="${checkFollow==1}">
					팔로우중<br>
					<button onclick="javascript:window:location='<%=request.getContextPath()%>/member/unFollow?memNum=${member.memNum}'">언팔로우</button>
					</c:if>
				</c:if>
			</div>
		</div>
	</div>
	
	<div class="data-tab">
		<div class="inner">
		<ul class = "tab-list">
			<li class = "active" onclick = "openTitle('myRecipe')"><a href = "#">마이레시피</a></li>
			<li onclick = "openTitle('scrap')"><a href = "#">스크랩</a></li>
			<li onclick = "openTitle('follow')"><a href = "#">팔로우(${followCount})</a></li>
		</ul>
		</div>
	</div>
	<section class="ftco-section ftco-category ftco-no-pt">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                
                </div>
                 <div class="col-md-3">
                
                </div>
                 <div class="col-md-3">
                
                </div>
                 <div class="col-md-3">
                
                </div>
                </div>
                </div>
                </section> 
	<div class="title" id = "myRecipe" style="margin: 0px 200px;">
		<ul class="lst_recipe">
			<c:if test="${loginNum==member.memNum}">
			<li class="btn_add">
				<a href="<%=request.getContextPath()%>/rcp/writeForm"><h1 style="margin: 0px;">+</h1><br>나의 레시피 추가하기</a>
			</li>
			</c:if>
			
			<c:if test="${rcpCount==0}">
			<li class="no-recipes">
				<div class="no-content">
				아직 등록하신 레시피가 없습니다.
				</div>
			</li>
			</c:if>
			
			<c:if test="${rcpCount!=0}">
			<c:forEach var="rcpList" items="${rcpList}">
			<li>
				<a href="" class="call_recipe">
					<img src="<%=request.getContextPath()%>/uploadRcpFile/${rcpList.thumbnail}">
				</a>
				<span class="author">
					<a href="">
						<img src="<%=request.getContextPath()%>/uploadFile/${rcpList.profile}">
					</a>
				</span>
				<p>
					<a href=""><strong>${rcpList.title}</strong><p>${rcpList.subtitle}</p></a>
				</p>
			</li>
			</c:forEach>
			</c:if>		
		</ul>
	</div>
	
	<div class="title" id = "scrap" style = "display:none; margin: 0px 200px;">
		<h1 style="text-align: center;color: #b7b7b7;font-size: 20px;padding-top: 100px">아직 스크랩하신 레시피가 없습니다.</h1>
	</div>
	
	<div class="title" id = "follow" style = "display:none; margin: 0px 200px;">
		<c:if test="${followCount==0 }">
			<h1 style="text-align: center;color: #b7b7b7;font-size: 20px;padding-top: 100px">아직 팔로우 하신 유저가 없습니다.</h1>
		</c:if>
		<ul class="itemcard-list">			
			<c:if test="${followCount != 0 }">
				<c:forEach var="followList" items="${followList}">
					<li class="itemcard-list-item">
						<div class="item">
						<a href="<%=request.getContextPath()%>/member/mypage?memNum=${followList.memNum}">
							<div>
								<img src="<%=request.getContextPath()%>/uploadFile/${followList.profile}">
							</div>
							<div>
								<div>${followList.name}</div>
							</div>
						</a>
						</div>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>
	
	
</div>

<script>
var tabBtn = $(".data-tab > .inner > ul > li"); 

tabBtn.click(function(e) {
    e.preventDefault();
    var target = $(this); 
    var index = target.index(); 
    tabBtn.removeClass("active");   
    target.addClass("active");   
		    
}); 

function openTitle(titleName) {
	   var i;
	   var x = document.getElementsByClassName("title");
	   for (i = 0; i < x.length; i++) {
	      x[i].style.display = "none";  
	   }
	   document.getElementById(titleName).style.display = "block";
}

</script>

</body>
</html>