<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" 
src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {

	//댓글쓰기 버튼 (버튼을 눌러서 id값이 넘어와서 실행되는 자바스크립트 구문)
	listReply();

	$("#btnReply").click(function() {

		$.ajax({
			type: "post",
			url: "/reply_insert.do",
			data: $("#replyInsert").serialize(),
			dataType: "JSON",

			success: function(data) {
				console.log(data);
				alert("댓글이 등록되었습니다.");
				listReply2(data);
			}
		});

	});

	//댓글 목록을 출력하는 함수
	function listReply() {
		var bno = $("#bno").val();

		$.ajax({
			type: "get",   
			url: "/reply_list.do?bno=" + bno,
		
			success: function(result) {    

				//댓글목록을 실행한 결과를 가져온다.
				$("#listReply").html(result);
			}
		});
	}




	function listReply2(data) {

		var bno = $("#bno").val();
		$.ajax({
			type: "get",
			contentType: "application/json",
			url: "/reply_list_json.do?bno=" + bno,
			success: function(result) {
				console.log(result);
				var output = "<table>";
				for (var i in result) {
					var repl = result[i].replytext;
					repl = repl.replace(/  /gi, "&nbsp;&nbsp;");//공백처리
					repl = repl.replace(/</gi, "&lt;"); //태그문자 처리
					repl = repl.replace(/>/gi, "&gt;");
					repl = repl.replace(/\n/gi, "<br>"); //줄바꿈 처리

					output += "<tr><td>" + result[i].name;
					date = changeDate(result[i].regdate);
					output += "(" + date + ")";
					output += "<br>" + repl + "</td></tr>";
				}
				output += "</table>";
				$("#listReply").html(output);
			}
		});
	}


});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
       <h1>게시글 상세보기</h1>
       

<div id="likecnt" style="margin-left:5px;">${likecnt}</div>

    번호  : <p> <input type="text" name="bnum" value="${board.bnum }"  id="kk"/> </p>
       
    제목 :  <p> <input type="text" name="title" value="${board.title }" /> </p>
       
     작성자:  <p> <input type="text" name="writer" value="${board.writer }" /> </p>
       
     날짜:  <p> <input type="text" name="bdate" value="${board.bdate }" /> </p>
     조회수:  <p> <input type="text" name="bcnt" value="${board.bcnt }" /> </p>
     내용:  <p> <input type="text" name="bcontent" value="${board.bcontent }" /> </p>
     분류:   <p> <input type="text" name="btype" value="${board.btype }" /> </p>


<!-- 댓글작성 -->
<div style="width:700px; text-align: center;">

<form id="replyInsert">

댓글번호<input type="text" name ="rno" value="${reply.rno }" />
게시판 번호: <input type="text" value="${board.bnum }" name="bno" id="bno" />
댓글내용: <textarea rows="5" cols="80" id="replytext"  name="replytext" placeholder="댓글을 작성하새요">
</textarea><br>
작성자:<input type="text" name="replyer" />
</form>

<button type="button" id="btnReply">댓글쓰기</button>

</div>

<!-- 댓글 목록을 출력할 영역 -->
<div id="listReply">

</div>

<div style = "width:700px; text-align:right;">
 
 
<button type = "button" id = "btn_reply_Update" >댓글 수정</button>
<button type = "button" id = "btn_reply_Delete" >댓글 삭제</button>

</div>
</body>
</html>