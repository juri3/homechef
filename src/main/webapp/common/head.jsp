<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>Home Chef</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Amatic+SC:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Gamja+Flower&family=Nanum+Gothic&family=Nanum+Myeongjo&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/animate.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/magnific-popup.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/aos.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ionicons.min.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/jquery.timepicker.css">


    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/flaticon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/icomoon.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <!-- <link href="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.11.1.min.js"></script> -->

</head>

<body class="goto-here">
    <div class="py-1 bg-primary">
        <div class="container">
            <div class="row no-gutters d-flex align-items-start align-items-center px-md-0">
                <div class="col-lg-12 d-block">
                    <div class="row d-flex">
                        <div class="col-md pr-4 d-flex topper align-items-center">
                            <form action="<%=request.getContextPath()%>/rcp/list" class="search-form">
                                <div class="form-group">
                                    <span class="icon ion-ios-search"></span>
                                    <input type="hidden" name="sorting" value="latest">
                                    <input type="hidden" name="cateNum" value="0">
                                    <input type="text" class="form-control" name="keyword" placeholder="음식명, 재료명으로 검색해주세요." autocomplete="off">
                                </div>
                            </form>
                        </div>
                        <!-- <div class="col-md pr-4 d-flex topper align-items-center">
                            <div class="icon mr-2 d-flex justify-content-center align-items-center"><span class="icon-paper-plane"></span></div>
                            <span class="text">youremail@email.com</span>
                        </div> -->
                        <div class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
                            <span class="text">
                            <c:if test="${! empty authUser}">
								${authUser.name }님, 안녕하세요.
								<a href="${pageContext.request.contextPath}/member/mypage?memNum=${memNum}">mypage</a>
								<a href="${pageContext.request.contextPath}/member/logout">logout</a>
							</c:if>	
								<c:if test="${empty authUser}">
								<a href="${pageContext.request.contextPath}/member/login">login</a>
								<a href="${pageContext.request.contextPath}/member/join">join</a>
							</c:if>
                            	<!-- <a>mypage</a>
                            	<a>logout</a> 
                            	<a>login</a>
                            	<a>join</a> -->
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
        <div class="container">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/main"><img src="<%=request.getContextPath()%>/images/logo.png"></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

            <div class="collapse navbar-collapse" id="ftco-nav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active"><a href="${pageContext.request.contextPath }/main" class="nav-link">Home</a></li>
                    <li class="nav-item"><a href="<%=request.getContextPath()%>/rcp/list?sorting=latest&cateNum=0" class="nav-link">레시피</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
                        <div class="dropdown-menu" aria-labelledby="dropdown04">
                            <a class="dropdown-item" href="shop.html">Shop</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath }/shopping/jjimlist">Wishlist</a>
                            <a class="dropdown-item" href="product-single.html">Single Product</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath }/shopping/cartview">Cart</a>
                            <a class="dropdown-item" href="checkout.html">Checkout</a>
                        </div>
                    </li>
                    <li class="nav-item"><a href="<%=request.getContextPath()%>/shop" class="nav-link">Store</a></li>
                    <li class="nav-item"><a href="${pageContext.request.contextPath }/shopping/orderinfo" class="nav-link">Blog</a></li>
                    <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
                    <li class="nav-item cta cta-colored"><a href="${pageContext.request.contextPath }/shopping/cartview" class="nav-link"><span class="icon-shopping_cart"></span>[0]</a></li>

                </ul>
            </div>
        </div>
    </nav>
    <!-- END nav -->

