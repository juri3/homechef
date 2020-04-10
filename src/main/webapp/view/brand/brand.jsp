<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
$(document).ready(function(){
	 // 회원가입처리
    $("form").submit(function(){
    	 if ($('#brand').val() == '') {
 	        alert('업체명을 입력해주세요.')
 	        return false;
 	      }
    	 if ($('#name').val() == '') {
  	        alert('담당자를 입력해주세요.')
  	        return false;
  	      }
    	 if ($('#tel').val() == '') {
  	        alert('담당자번호를 입력해주세요.')
  	        return false;
  	      }
    	 if ($('#email').val() == '') {
  	        alert('이메일을 입력해주세요.')
  	        return false;
  	      }
    	  if ($('.username_input').attr("check_result") == "fail"){
    		    alert("아이디 중복체크를 해주시기 바랍니다.");
    		    $('.username_input').focus();
    		    return false;
    		  }
    	 
    });
   
});
   

function chkID() {
	var id = document.getElementById("id").value;

$.ajax({
    type:"POST",
    url:"idCheck",
    data:{
           "id":$('#id').val()
    },
    success:function(data){	//data : checkSignup에서 넘겨준 결과값
           if($.trim(data)=="YES"){
        	 
              if($('#id').val()!=''){ 
              	//alert("사용가능한 아이디입니다.");
              	var exptext = /^[a-z0-9]{5,12}$/;
              if (exptext.test(id) == false) {
         			alert("희망 아이디는 5~12자의 영문 소문자, 숫자만 사용 가능합니다.");
        			document.getElementById('id').focus();
        			return false;
        		}else{
        		 	alert("사용가능한 아이디입니다.");
        		     $('.username_input').attr("check_result", "success");
        		}
              }
          	}else{
          		
        
             if($('#id').val()!=''){
                 alert("중복된 아이디입니다.");
                
         		
                 $('#id').val('');
                 $('#id').focus();
              } 
           }
        }
   }) 
};


<%-- function chkID() {
		var id = document.getElementById("id").value;
		var query={"id":$("#id").val()}
		 $.ajax({
	            type:"POST",
	            url:"<%=request.getContextPath()%>/idCheck",
	            data:query,
	            success:function(data){
	            	if(data==1){
	            		alert("이미 사용중인 아이디입니다.");
	            	}
	            }
	            });
		 var exptext = /^[a-z0-9]{5,12}$/;
		if (exptext.test(id) == false) {
			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우		
			
			alert("희망 아이디는 5~12자의 영문 소문자, 숫자만 사용 가능합니다.");
			document.getElementById('id').focus();
			return false;
		}else{
			alert("사용할 수 있는 아이디입니다.");
		}
	}  --%>
</script>
<body>


	<div class="hero-wrap hero-bread sub_headbg"
		style="background-image: url('images/bg_4.jpg');">
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-center justify-content-center">
				<div class="col-md-9 ftco-animate text-center">
					<p class="breadcrumbs">
						<span class="mr-2"><a href="<%=request.getContextPath()%>/main">Home</a></span> <span
							class="mr-2"><a href="<%=request.getContextPath()%>/brand">HOMECHEF <spring:message
									code="nav.brand" /></a></span>
					</p>
					<h1 class="mb-0 bread">
						HOMECHEF
						<spring:message code="nav.brand" />
					</h1>
				</div>
			</div>
		</div>
	</div>


	<section class="ftco-section contact-section bg-light">
	<div class="container">
		<div style="text-align: center;">
			<img src="<%=request.getContextPath()%>/images/chef_top2.jpg">
		</div>
		<form name="applyFrm" id="applyFrm" method="post"
			action="${pageContext.request.contextPath}/brand"
			class="bg-white p-5 contact-form">

			<div class="form-group">

				<input type="text" name="brand" id="brand" class="form-control"
					placeholder="<spring:message code="brand.name" />">
			</div>
			<div class="form-group">
				<input type="text" name="name" id="name" class="form-control "
					placeholder="<spring:message code="brand.manager" />">

			</div>
			<div class="form-group">
				<input type="text" name="tel" id="tel" class="form-control"
					placeholder="<spring:message code="brand.phone" />">

			</div>

			<div class="form-group">
				<input type="text" name="email" id="email" class="form-control"
					placeholder="<spring:message code="brand.email" />">

			</div>
			<div class="form-group">
				<textarea id="selfIntroduction" name="selfIntroduction" cols="30"
					rows="7" class="form-control"
					placeholder="<spring:message code="brand.introduce" />"></textarea>
			</div>
			<div class="cont_add">
				<div class="cont_add_tt">
					<b><spring:message code="brand.questions" /></b><span>(<spring:message code="brand.quecheck" />)</span>
				</div>
				<div class="cont_add_in">
					<label class="radio-inline"><input type="checkbox"
						name="inquiry" id="reason_join" value="브랜드관 입점신청"> <spring:message code="brand.quecheck1" /></label> <label class="radio-inline"><input type="checkbox"
						name="inquiry" id="reason_event" value="레시피 공모전문의"> <spring:message code="brand.quecheck2" /></label> <label class="radio-inline"><input type="checkbox"
						name="inquiry" id="reason_ppl" value="PPL영상 제작문의"> <spring:message code="brand.quecheck3" />
						제작문의</label> <label class="radio-inline"><input type="checkbox"
						name="inquiry" id="reason_shop" value="쇼핑몰 입점문의"><spring:message code="brand.quecheck4" /></label>
					<label class="radio-inline"><input type="checkbox"
						name="inquiry" id="reason_etc" value="기타 홍보 문의"><spring:message code="brand.quecheck5" /></label>
				</div>
			</div>
			<div class="cont_add">
				<div class="cont_add_tt">
					<span><spring:message code="brand.idtitle" /></span>
				</div>
<!-- 				<input type="text" name="id" id="id" onchange="cheID()" -->
				<input type="text" name="id" id="id" 
					class="form-control mag_b_10 has-feedback username_input"  check_result="fail" 
					placeholder="<spring:message code="brand.id" />"
					style="width:50%; display: inline-block;">
				<button type="button" onclick="chkID()" class="btn"><spring:message code="brand.idcheck" /></button>
			</div>
			<div class="form-group brand_btn">
				<input type="submit" value="<spring:message code="brand.btn" />"
					class="btn btn-primary py-3 px-5">
			</div>
		</form>


	</div>
	</section>
	<%-- <div class="container">
		<div class="row justify-content-center">
			<div class="col-md-12">
				<form name="applyFrm" id="applyFrm" method="post"
					action="${pageContext.request.contextPath}/brand">
					<!--  <input type="hidden" name="q_mode" value="insert">
                            <input type="hidden" name="frm[bra_id_user]" value=""> -->
					<input type="text" name="brand" id="brand"
						class="form-control mag_b_10 has-feedback"
						placeholder="업체명/개인명/단체명"> <input type="text" name="name"
						id="name" class="form-control mag_b_10 has-feedback"
						placeholder="담당자 명"> <input type="text" name="tel"
						id="tel" class="form-control mag_b_10 has-feedback"
						placeholder="대표번호 / 담당자번호 예)02-486-1004 / 010-486-1004"> <input
						type="text" name="email" id="email"
						class="form-control mag_b_10 has-feedback" placeholder="이메일주소">
					<textarea id="selfIntroduction" name="selfIntroduction"
						placeholder="취급품목, 단체소개, 개인소개등 간략한 회사소개를 해주세요."
						style="height: 180px; width: 100%; resize: none;"></textarea>

					<div class="cont_add">
						<div class="cont_add_tt">
							<b>문의사항 선택</b><span>(중복체크 가능합니다)</span>
						</div>
						<div class="cont_add_in">
							<label class="radio-inline"><input type="checkbox"
								name="inquiry" id="reason_join" value="브랜드관 입점신청"> 브랜드관
								입점신청</label> <label class="radio-inline"><input type="checkbox"
								name="inquiry" id="reason_event" value="레시피 공모전문의"> 레시피
								공모전문의</label> <label class="radio-inline"><input type="checkbox"
								name="inquiry" id="reason_ppl" value="PPL영상 제작문의"> PPL영상
								제작문의</label> <label class="radio-inline"><input type="checkbox"
								name="inquiry" id="reason_shop" value="쇼핑몰 입점문의"> 쇼핑몰
								입점문의</label> <label class="radio-inline"><input type="checkbox"
								name="inquiry" id="reason_etc" value="기타 홍보 문의"> 기타 홍보
								문의</label>
						</div>
					</div>
					<div class="cont_add">
						<div class="cont_add_tt">
							<span>브랜드관 입점신청에 체크하신분은 희망 아이디를 작성해주세요</span>
						</div>
						<input type="text" name="id" id="id" onchange="cheID()"
							class="form-control mag_b_10 has-feedback"
							placeholder="희망 아이디 5~12자의 영문소문자, 숫자"
							style="width: 340px; display: inline-block;">
						<button type="button" onclick="CHKID()" class="btn">중복체크</button>
					</div>
					<input type="submit" value="문의" class="btn btn-lg btn-primary"
						style="margin: 50px;">
					<!--    <button type="button" onclick="doSubmit()" class="btn btn-lg btn-primary">브랜드관 입점/광고문의</button>-->
				</form>
			</div>
		</div>
	</div> --%>
</body>
</html>
<!-- <script>
var idFlag = 0;
function chkID(){
    if(idFlag!=1)
    {
        var s=$("#id_brand").val();
        
        if(!s)
        {
            viewMsg("id_brand","희망 아이디는 5~12자의 영문 소문자, 숫자만 사용 가능합니다.","error");
            $("#id_brand").focus();
            return;
        }
        
        var e=/^[a-z0-9]{5,12}$/;
        if(e.test(s))
        {
            $.ajax({
            type:"POST",
            url:"/brand/ajax.html",
            data:"q_mode=checkDuplicate&item=id&id="+btoa(unescape(encodeURIComponent(s))),
            success:function(s){
                switch(s){
                    case"NO_USE":
                        viewMsg("id_brand","사용할 수 있는 아이디입니다.","success");
                        idFlag=1;
                    break;
                    case"MYID":
                        viewMsg("id_brand","본인 아이디를 브랜드관 쉐프 아이디로 사용합니다.","success");
                        idFlag=1;
                    break;
                    case"DUPLICATE":
                        viewMsg("id_brand","이미 사용중인 아이디입니다.","error");
                        $("#id_brand").focus();
                    break;
                    case"ERROR":
                        viewMsg("id_brand","오류입니다. 다시 시도해 주세요.","error");
                        $("#id_brand").focus();
                    break;
                    }
                }
            });
        }
        else
        {
            viewMsg("id_brand","희망 아이디는 5~12자의 영문 소문자, 숫자만 사용 가능합니다.","error");
            $("#id_brand").focus();
        }
    }
}

function cheID()
{
    idFlag = 0;
}

function viewMsg(type, msg, flag)
{
    if(!type || !flag) return ;

    var frms = type;

    if(flag == 'error')
    {
        $("#"+frms).removeClass("has-success").addClass("has-error");
    }
    else
    {
        $("#"+frms).removeClass("has-error").addClass("has-success");
    }
    if(msg) alert(msg);
}

function doSubmit()
{
    var company = $("#nm_company").val();
    var id = $("#id_brand").val();
    var name = $("#nm_user").val();
    var email = $("#id_email").val();
    var phone = $("#no_phone").val();
    var url = $("#tx_url").val();
    var reason = $("#tx_reason").val();
    var checked = ($("#contractCheck").prop('checked')) ? true : false;
    var reason_join_checked = ($("#reason_join").prop('checked')) ? true : false;
    var reason_event_checked = ($("#reason_event").prop('checked')) ? true : false;
    var reason_ppl_checked = ($("#reason_ppl").prop('checked')) ? true : false;
    var reason_shop_checked = ($("#reason_shop").prop('checked')) ? true : false;
    var reason_etc_checked = ($("#reason_etc").prop('checked')) ? true : false;

    if(!checked)
    {
        viewMsg('contractCheck','약관에 동의 해 주세요.','error');
        $("#contractCheck").focus();
        return ;
    }
    
    if(!company)
    {
        viewMsg('nm_company','업체명/개인명/단체명을 입력해 주세요.','error');
        $("#nm_company").focus();
        return ;
    }
    var isUrl = /^[ㄱ-ㅎ|가-힣|a-zA-Z_\-0-9\.\/:\?]{5,}$/;
    if(url && !isUrl.test(url))
    {
        viewMsg('tx_url','대표운영사이트 주소를 정확히 입력해 주세요.','error');
        $("#tx_url").focus();
        return ;
    }
    viewMsg('tx_url','','success');
    if(!id && reason_join_checked)
    {
        viewMsg('id_brand','희망 아이디를 입력해 주세요.','error');
        $("#id_brand").focus();
        return ;
    }
    if(!idFlag && reason_join_checked)
    {
        chkID();
        return ;
    }
    if(!name)
    {
        viewMsg('nm_user','담당자 명을 입력해 주세요.','error');
        $("#nm_user").focus();
        return ;
    }
    if(!phone)
    {
        viewMsg('no_phone','대표번호/담당자번호를 입력해 주세요.','error');
        $("#no_phone").focus();
        return ;
    }
    /*
    if(!/^[0-9\-]{9,14}$/.test(phone))
    {
        viewMsg('no_phone','대표번호/담당자번호를 숫자와 ( - )로만 정확히 입력해 주세요.','error');
        $("#no_phone").focus();
        return ;
    }
    */
    viewMsg('no_phone','','success');
    if(!email)
    {
        viewMsg('id_email','이메일 주소를 입력해 주세요.','error');
        $("#id_email").focus();
        return ;
    }
    
    if(reason_join_checked == false && reason_event_checked == false && reason_ppl_checked  == false && reason_shop_checked  == false && reason_etc_checked == false)
    {
        viewMsg('reason_checked','문의사항을 선택해 주세요.','error');
        $("#reason_join_checked").focus();
        return ;
    }

    $("#applyFrm").submit();
}
</script> -->