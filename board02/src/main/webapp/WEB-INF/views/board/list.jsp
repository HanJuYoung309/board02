<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function list(page) {
		$.ajax({
			url:"/list.do",
			type:"GET",
			data:{page:page, type:$("#type option:selected").val(), keyword:$("#keyword").val()},
		    dataType:"JSON",
		   
	        success: function (result) {
	        	 console.log(result.list);
	             $("#tbody").empty();
	             $("#paging").empty();
	             var list = result.list;
	             for(var i = 0; i < list.length; i++){
	                var html = "<tr>"
	                    html+= "<td>"+list[i].bnum+"</td>"
	                    html+= "<td><a href='getBoard.do?bnum="+list[i].bnum+"'</a>"+list[i].title+"</td>"
	                    html+= "<td>"+list[i].writer+"</td>"
	                    html+= "<td>"+list[i].bdate+"</td>"
	                    html+= "<td>"+list[i].bcnt+"</td>"
	                    html+= "<td>"+list[i].bcontent+"</td>"
	                    html+= "<td>"+list[i].btype+"</td>"
	                    $("#tbody").append(html)
	             }
	             if(result.prev){
	                $("#paging").append("<button onclick=list("+Number(page-1)+")>이전</button>");
	             }
	             for(var i = result.startPage; i <= result.endPage; i++){
	                $("#paging").append("<button onclick=list("+i+")>"+i+"</button>")               
	             }
	             if(result.next){
	                $("#paging").append("<button onclick=list("+Number(page+1)+")>이전</button>");
	             }
	          }
	       })
	       

	    }
	
	   $(function(){
		      list(1)
		})

	</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>목록페이지 </h1>
    <a href="/insert.do">글추가 </a>
    <label for="">검색</label>
   <select id="typle">
      <option value="title">제목</option>
      <option value="writer">저자</option>
      <option value="bcontent">내용</option>
      <option value="bdate">날짜</option>
   </select>
   <label for="">검색</label>
   <input type="text" id="keyword"/>
   <button type="button" onclick="list(1)">검색</button>
<table border="1" width = "70%">
   <tr>
      <td>글번호</td>
      <td>제목</td>
      <td>작성자</td>
      <td>작성일</td>
      <td>조회수</td>
      <td>내용</td>
      <td>분류</td>
   </tr>
   <tbody id="tbody"></tbody>
</table>
   <div id="paging"></div>
   
   
</body>
</html>