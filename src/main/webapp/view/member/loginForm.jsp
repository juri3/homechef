<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--Intro Section-->
    <section class="view intro-2">
        <div class="mask">
            <div class="container">
                <div class="row">
                    <div class="col-xl-5 col-lg-6 col-md-10 col-sm-12 mx-auto mt-lg-5">
  <div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-50 p-b-90">
				<form action="${pageContext.request.contextPath}/member/login" method="post">

					<span class="login100-form-title ">
						<img src="<%=request.getContextPath()%>/images/logo.png">
					</span>

					
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
					
						<input type="text" name="email" value="${param.email}"class="input100 "  autocomplete="off"  placeholder="<spring:message code="form.email" />">
						<c:if test="${errors.email }">ID를 입력하세요</c:if>
						
					</div>
					
					
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
					   <input type="password" name="passwd" class="input100" placeholder="<spring:message code="form.password" />">
						  <c:if test="${errors.passwd }">암호를 입력하세요</c:if>
					</div>
					
					<div class="flex-sb-m w-full p-t-3 p-b-24">
						<div class="contact100-form-checkbox">
						<c:if test="${errors.idOrPwNotMatch }">
아이디와 암호가 일치하지 않습니다.
</c:if>
							<!-- <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
							<label class="label-checkbox100" for="ckb1">
								Remember me
							</label> -->
						</div>

						<!-- <div>
							<a href="#" class="txt1">
								Forgot?
							</a>
						</div> -->
					</div>

					<div class="container-login100-form-btn m-t-17">
						<button class="login100-form-btn" type="submit" value="로그인">
							<spring:message code="login" />
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>
                       

                    </div>
                </div>
            </div>
        </div>
    </section>
  
	
<%-- 
<form action="${pageContext.request.contextPath}/member/login" method="post">
<c:if test="${errors.idOrPwNotMatch }">
아이디와 암호가 일치하지 않습니다.
</c:if>
<p>
	아이디:<br/><input type="text" name="email" value="${param.email}" autocomplete="off">
	<c:if test="${errors.email }">ID를 입력하세요</c:if>
</p>
<p>
	암호:<br/><input type="password" name="passwd">
	<c:if test="${errors.passwd }">암호를 입력하세요</c:if>
</p>
<input type="submit" value="로그인">
<a href="${pageContext.request.contextPath}/member/join">[회원가입하기]</a>
<a href="${pageContext.request.contextPath}/member/main">[메인으로]</a>
</form> --%>
</body>
</html>