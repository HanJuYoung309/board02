/**
 * 
 */

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