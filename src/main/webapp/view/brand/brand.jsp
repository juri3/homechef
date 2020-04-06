<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div class="container">
            <div class="row justify-content-center">
            <div class="col-md-12">
<form name="applyFrm" id="applyFrm" method="post" action="${pageContext.request.contextPath}/brand">
                           <!--  <input type="hidden" name="q_mode" value="insert">
                            <input type="hidden" name="frm[bra_id_user]" value=""> -->
                            <input type="text" name="brand" id="brand" class="form-control mag_b_10 has-feedback" placeholder="업체명/개인명/단체명">
                            <input type="text" name="name" id="name" class="form-control mag_b_10 has-feedback" placeholder="담당자 명">
                            <input type="text" name="tel" id="tel" class="form-control mag_b_10 has-feedback" placeholder="대표번호 / 담당자번호 예)02-486-1004 / 010-486-1004">
                            <input type="text" name="email" id="email" class="form-control mag_b_10 has-feedback" placeholder="이메일주소">
                            <textarea id="selfIntroduction" name="selfIntroduction" placeholder="취급품목, 단체소개, 개인소개등 간략한 회사소개를 해주세요." style="height:180px; width:100%; resize:none;"></textarea>
                             
                         <div class="cont_add">
                                <div class="cont_add_tt"><b>문의사항 선택</b><span>(중복체크 가능합니다)</span></div>
                                <div class="cont_add_in">
                                    <label class="radio-inline"><input type="checkbox" name="inquiry" id="reason_join" value="브랜드관 입점신청"> 브랜드관 입점신청</label>
                                    <label class="radio-inline"><input type="checkbox" name="inquiry" id="reason_event" value="레시피 공모전문의"> 레시피 공모전문의</label>
                                    <label class="radio-inline"><input type="checkbox" name="inquiry" id="reason_ppl" value="PPL영상 제작문의"> PPL영상 제작문의</label>
                                    <label class="radio-inline"><input type="checkbox" name="inquiry" id="reason_shop" value="쇼핑몰 입점문의"> 쇼핑몰 입점문의</label>
                                    <label class="radio-inline"><input type="checkbox" name="inquiry" id="reason_etc" value="기타 홍보 문의"> 기타 홍보 문의</label>
                                </div>
                            </div>
                            <div class="cont_add">
                                <div class="cont_add_tt"><span>브랜드관 입점신청에 체크하신분은 희망 아이디를 작성해주세요</span></div>
                                <input type="text" name="id" id="id" onchange="cheID()" class="form-control mag_b_10 has-feedback" placeholder="희망 아이디 5~12자의 영문소문자, 숫자" style="width:340px; display:inline-block;">
                                <button type="button" onclick="chkID()" class="btn">중복체크</button>
                            </div> 
                            <input type="submit" value="문의" class="btn btn-lg btn-primary" style="margin:50px;">
                            <!--    <button type="button" onclick="doSubmit()" class="btn btn-lg btn-primary">브랜드관 입점/광고문의</button>-->
                        </form>
                        </div></div></div>
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