<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">

$(function () {
	$("#btnReply").click( function () {
		
	   
		reply();
	});
});

   function reply() {
	   
	   var replytext=$("replytext").val();
	   var bno="${board.bnum}";
	   var param={"replytext:" replytext, "bno":bno};
	   $.ajax({
		    type:"post",
		    url:"/replyInsert.do",
		    data:param,
		    
		    success: function () {
				alert("댓글이 등록되었습니다.");
			}
	   });
	
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
       <h1>게시글 상세보기</h1>
       
    번호  : <p> <input type="text" name="bnum" value="${board.bnum }" /> </p>
       
    제목 :  <p> <input type="text" name="bnum" value="${board.title }" /> </p>
       
     작성자:  <p> <input type="text" name="bnum" value="${board.writer }" /> </p>
       
     날짜:  <p> <input type="text" name="bnum" value="${board.bdate }" /> </p>
      조회수:  <p> <input type="text" name="bnum" value="${board.bcnt }" /> </p>
       내용:  <p> <input type="text" name="bnum" value="${board.bcontent }" /> </p>
       분류:   <p> <input type="text" name="bnum" value="${board.btype }" /> </p>


<!-- 댓글작성 -->
<div style="width:700px; text-align: center;">

<textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성하새요">
   
</textarea><br>
<button type="button" id="btnReply">댓글쓰기</button>

</div>

<!-- 댓글 목록을 출력할 영역 -->
<div id="listReply">

</div>

</body>
</html>