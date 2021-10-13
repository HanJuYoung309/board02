<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
 
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script>
 
$(function(){
 
    //댓글 수정 버튼
    $("#btn_reply_Update").click(function(){
    if(confirm("수정 하시겠습니까?")){
        
  
        var rno = $("#rno").val();
        var replytext = $("textarea#replytext").text();
        var user_id = $("#user_id").val();
        var member_bno = $("#bno").val();

        
        alert("댓글이 수정되었습니다.")
                }
        });
 
    
    //댓글 삭제 버튼
    $("#btn_reply_Delete").click(function(){
        
        if(confirm("삭제 하시겠습니까?")){
     
            var rno = $("#rno").val();
            var member_bno = $("#bno").val();
            var replytext = $("textarea#replytext").text();
          
            //url로 삭제에 필요한 변수들을 보낸다.
            document.form1.action="reply_delete.do?rno="+rno+"&bno="+bno;
            
            document.form1.submit();
            
            alert("댓글이 삭제되었습니다.");
            
        }
    });
});
 
</script>
 
 
 
<body>
<!-- 배열이 비어있지 않으면 참을 출력함. (다시말해서 배열에 값들이 있으면 댓글 리스트를 출력한다.) -->
 
 
 
<c:if test = "${not empty map.list}">
<h2>댓글 리스트</h2>
<table border = "1" width = "800px" align = "left">
 
<c:forEach var = "row" items = "${map.list}">
 
 
<tr>
<td><br><br>
 
작성일자 : ${row.regdate}   댓글번호 : ${row.rno }<br><br><br>
 
${row.replytext}
 
<!-- 폼태그 안에 위쪽에 있는 자바스크립트 구문에 필요한 값들을 노출시키지 않게 하기 위해 hidden타입으로 값들을 전달한다. -->
<form method = "POST" id = "form1" >
 
<input type = "hidden" id = "rno" name = "rno" value = "${row.rno}">
<input type = "hidden" id = "bno" name = "bno" value = "${row.bno}">
 
<div style = "width : 800px;">
<textarea id = "replytext" name = "r_content" rows = "3" cols = "80"></textarea></div><br><br>
</form>    
    

<div style = "width:700px; text-align:right;">

 
<button type = "button" id = "btn_reply_Update" >댓글 수정</button>
<button type = "button" id = "btn_reply_Delete" >댓글 삭제</button>
 

</div>
 
 
<br><br>
</td>
</tr>
 
 
 
</c:forEach>
</table>
</c:if>
 
 
</body>
</html>
