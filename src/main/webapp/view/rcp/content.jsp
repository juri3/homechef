<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<style>
.box_write {
    background-color: #f7f7f7;
    width: 660px;
    height: 95px;
    box-sizing: border-box;
    padding: 13px 18px;
    border: 1px solid #e6e6e6;
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
.lst_step {
    counter-reset: li;
    position: relative;
}
.lst_step li {
    width: 600px;
    margin: 50px auto 0;
    counter-increment: step;
    position: relative;
}
.lst_step img {
  	display: block;
    width: 600px;
    height: 340px;
    margin: 0 auto 13px;
}
.lst_step p {
    font-size: 16px;
    color: #6d6e71;
    font-family: Microsoft YaHei,'NS';
    line-height: 22px;
}

</style>

<body>

<div class="container">
        <div class="row">
            <!-- Boxes de Acoes -->
            <div class="col-xs-12 col-sm-6 col-lg-8">
                <div class="box" style="width: 1000px">
                    <div class="icon">
                        <div class="image"><i class="fa fa-soundcloud"></i></div>
                        <div class="info">

                            <!-- Modal -->
                            <div >
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="slider_recipe">
                                            <div class="modal-body">
                                                <div class="sec_detail">
                                                    <div id="carousel-thumb" class="carousel slide carousel-fade carousel-thumbnails" data-ride="carousel">
                                                        <div class="carousel-inner">
                                                            <div class="carousel-item active">
                                                                <img src="<%=request.getContextPath()%>/uploadRcpFile/${rcpContent.thumbnail}" class="d-block w-100" alt="...">
                                                            </div>
                                                            <c:forEach var="rcpContent2" items="${rcpContent2}">
                                                            <div class="carousel-item">
                                                                <img src="<%=request.getContextPath()%>/uploadRcpContentFile/${rcpContent2.fileName}" class="d-block w-100" alt="...">
                                                            </div>
                                                            </c:forEach>                                                        
                                                        </div>
                                                        <a class="carousel-control-prev" href="#carousel-thumb" role="button" data-slide="prev">
                                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                                            <span class="sr-only">Previous</span>
                                                        </a>
                                                        <a class="carousel-control-next" href="#carousel-thumb" role="button" data-slide="next">
                                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                                            <span class="sr-only">Next</span>
                                                        </a>
                                                        <!--/.Controls-->
                                                        <ol class="carousel-indicators">
                                                            <li data-target="#carousel-thumb" data-slide-to="0" class="active"> <img class="d-block w-100" src="<%=request.getContextPath()%>/uploadRcpFile/${rcpContent.thumbnail}" class="img-fluid"></li>
                                                            <c:forEach var="rcpContent2" items="${rcpContent2}" varStatus="status">                                                         
                                                            <li data-target="#carousel-thumb" data-slide-to="${status.count}"><img class="d-block w-100" src="<%=request.getContextPath()%>/uploadRcpContentFile/${rcpContent2.fileName}" class="img-fluid"></li>
                                                            </c:forEach>                                                         
                                                        </ol>
                                                    </div>
                                                    <h2>영양정보<span>(하루 적정섭취량, 1인분 기준)</span></h2>
                                                    <h2>태그 정보</h2>
                                                    <h2>레시피 정보</h2>
                                                    <ol class="lst_step">
                                                    	<c:forEach var="rcpContent2" items="${rcpContent2}" varStatus="status">
                                                    	<li>
                                                    		<div class="img_cover">
                                                    			<img src="<%=request.getContextPath()%>/uploadRcpContentFile/${rcpContent2.fileName}">
                                                    		</div>
                                                    		<p>${rcpContent2.content}</p>
                                                    	</li>
                                                    	</c:forEach>
                                                    </ol>
                                                    
                                                    <section class="sec_comment">
														<h2>한줄댓글</h2>
														<form class="box_write">
															<textarea placeholder="한 줄 댓글을 남겨주세요." name="comment"></textarea>
															<button type="submit">댓글남기기</button>
														</form>
														
														<ul class="lst_comment">
															<li>
																<div class="img-cover">
																	<img src="<%=request.getContextPath()%>/uploadFile/profile.png">
																</div>
																<strong>Test</strong>
																<time>2015.02.02</time>
																<p>Good</p>
															</li>
														</ul>
													</section>
                                                </div>
                                                <section class="sec_info">
                                                    <div class="aside">
                                                        <div class="top">
                                                            <div class="user">
                                                                <div class="img-cover">
                                                                    <a href="<%=request.getContextPath()%>/member/mypage?memNum=${rcpContent.memnum}">
                                                                        <img class="img_user" src="<%=request.getContextPath()%>/uploadFile/${rcpContent.profile}">
                                                                    </a>
                                                                </div>
                                                                <strong class="best"><a href="<%=request.getContextPath()%>/member/mypage?memNum=${rcpContent.memnum}">${rcpContent.name}</a></strong>
                                                            </div>

                                                            <h1>
                                                                <strong>${rcpContent.title}</strong>
                                                                ${rcpContent.subtitle}
                                                            </h1>
                                                            <dl class="info_basic">
                                                                <dt class="time">조리시간</dt>
                                                                <dd>${rcpContent.cookingtime}분</dd>
                                                                <dt class="scrap">스크랩</dt>
                                                                <dd id="scrap-cnt">${scrapCount}</dd>
                                                                <!-- [D] 스크랩이 된 경우 :
														  <dt class="scrap on"><a href="#">스크랩</a></dt><dd>1,230</dd>
														  -->
                                                                <dt class="cal">칼로리</dt>
                                                                <dd>180.4 kcal</dd>
                                                            </dl>

                                                            <!-- [D] 버튼영역 추가 150314 -->
                                                            <div class="btn_area">
                                                            <c:if test="${loginNum!=rcpContent.memnum}">
                                                            	<c:if test="${checkScrap==0}">
                                                            	<form action="<%=request.getContextPath()%>/member/scrap" method="post">                                                                    
                                                                    <input type="hidden" name="scrapnum" value="${rcpContent.rcpnum}">
                                                                    <button type="submit" class="btn_scrap">스크랩</button>
                                                                </form>
                                                            	</c:if>
                                                            	<c:if test="${checkScrap==1}">
                                                            	<form action="<%=request.getContextPath()%>/member/delScrap" method="post">                                                                    
                                                                    <input type="hidden" name="scrapnum" value="${rcpContent.rcpnum}">
                                                                    <button type="submit" class="btn_scrap">스크랩취소</button>
                                                                </form>
                                                            	</c:if>
                                                            </c:if>                                                             
                                                            </div>
                                                            <!-- //버튼영역 추가 -->
                                                        </div>                                                      

                                                        <div class="btm" style="position: relative; top: 0px; left: 0px; margin-left: 0px;">
                                                            <h2>재료리스트</h2>
                                                            <div class="dropdown">
                                                                1인 기준
                                                            </div>

                                                            <ul class="lst_ingrd">
                                                            	<c:forEach var="rcpContent3" items="${rcpContent3}">
                                                            	<li><span>${rcpContent3.ingredient}</span><em>${rcpContent3.quantity}</em></li>
                                                            	</c:forEach>                                                               
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </section>
                                            </div>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default pull-left" onclick="history.go(-1);">Close</button>
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->
                            <!-- Modal -->
                        </div>
                    </div>
                </div>
                <div class="space"></div>
            </div>
        </div>
        <!-- /Boxes de Acoes -->
    </div>

</body>
</html>