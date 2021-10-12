<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" 
src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$(function(){
    
    //댓글쓰기 버튼 (버튼을 눌러서 id값이 넘어와서 실행되는 자바스크립트 구문)
    listReply();
    
        
    $("#btnReply").click(function(){
        
    var replytext = $("#replytext").val();    //댓글의 내용
    var bno =  "${board.bnum}";
    var params = {"replytext" : replytext, "bnum" : bno};
    
    
    $.ajax({
        type: "post", //데이터를 보낼 방식
        url: "/reply_insert.do", //데이터를 보낼 url
        data: params, //보낼 데이터
    
        success: function(data){//데이터를 보내는 것이 성공했을 시 출력되는 메시지
        	console.log(data);
            alert("댓글이 등록되었습니다.");
            listReply2();
                }
            });
        });
    
//댓글 목록을 출력하는 함수
function listReply(){
	  var bno =  "${board.bnum}";
	
    $.ajax({
        type: "get",   //get방식으로 자료를 전달
        url: "/reply_list.do?bno="+bno,    
        //컨트롤러에 있는 list.do로 맵핑되고 게시글 번호도 같이 보낸다.
        success: function(result){    //자료를 보내는것이 성공했을때 출력되는 메시지
            
            //댓글목록을 실행한 결과를 가져온다.
        $("#listReply").html(result);
            }
        });
}
 
 
 
 
function listReply2(){
	
	var bno= $("#bnum").val();
    $.ajax({
        type: "get",
        contentType: "application/json",
        url: "/reply_list_json.do?bno="+bno,
        success: function(result){
            console.log(result);
            var output="<table>";
            for(var i in result){
                var repl=result[i].replytext;
                repl = repl.replace(/  /gi,"&nbsp;&nbsp;");//공백처리
                repl = repl.replace(/</gi,"&lt;"); //태그문자 처리
                repl = repl.replace(/>/gi,"&gt;");
                repl = repl.replace(/\n/gi,"<br>"); //줄바꿈 처리
                
                output += "<tr><td>"+result[i].name;
                date = changeDate(result[i].regdate);
                output += "("+date+")";
                output += "<br>"+repl+"</td></tr>";
            }
                output+="</table>";
                $("#listReply").html(output);
        }
    });
}
    
    
});
 
 

// /* JSP SCRIPT */
// var bbsidx = ${bbsidx};
// var useridx = ${useridx};
 
// var btn_like = document.getElementById("btn_like");
//  btn_like.onclick = function(){ changeHeart(); }
 
// /* 좋아요 버튼 눌렀을떄 */
// //  function changeHeart(){ 
// //      $.ajax({
// //             type : "POST",  
// //             url : "/clickLike",       
// //             dataType : "json",   
// //             data : "bbsidx="+bbsidx+"&useridx="+useridx,
// //             error : function(){
// //                 Rnd.alert("통신 에러","error","확인",function(){});
// //             },
// //             success : function(jdata) {
// //                 if(jdata.resultCode == -1){
// //                     Rnd.alert("좋아요 오류","error","확인",function(){});
// //                 }
// //                 else{
// //                     if(jdata.likecheck == 1){
// //                         $("#btn_like").attr("src","/home/img/ico_like_after.png");
// //                         $("#likecnt").empty();
// //                         $("#likecnt").append(jdata.likecnt);
// //                     }
// //                     else if (jdata.likecheck == 0){
// //                         $("#btn_like").attr("src","/home/img/ico_like_before.png");
// //                         $("#likecnt").empty();
// //                         $("#likecnt").append(jdata.likecnt);
                        
// //                     }
// //                 }
// //             }
// //         });
// //  }
 
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
       <h1>게시글 상세보기</h1>
       

<div id="likecnt" style="margin-left:5px;">${likecnt}</div>

    번호  : <p> <input type="text" name="bnum" value="${board.bnum }" /> </p>
       
    제목 :  <p> <input type="text" name="title" value="${board.title }" /> </p>
       
     작성자:  <p> <input type="text" name="writer" value="${board.writer }" /> </p>
       
     날짜:  <p> <input type="text" name="bdate" value="${board.bdate }" /> </p>
      조회수:  <p> <input type="text" name="bcnt" value="${board.bcnt }" /> </p>
       내용:  <p> <input type="text" name="bcontent" value="${board.bcontent }" /> </p>
       분류:   <p> <input type="text" name="btype" value="${board.btype }" /> </p>


<!-- 댓글작성 -->
<div style="width:700px; text-align: center;">

<textarea rows="5" cols="80" id="replytext" name="replytext" placeholder="댓글을 작성하새요">
   
</textarea><br>
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