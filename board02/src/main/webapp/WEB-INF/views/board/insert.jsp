<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" 
src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
  
  function insert() {
	
	  
	  $.ajax({
		
		  url:"/insert.do",
		  type:"POST",
		  data:$("#insertForm").serialize(),
		  dataType:"JSON",
		  
		  success: function (result) {
			   console.log(result);
			   alert("글입력 성공!");
			   location.href="/list2.do";
			   
			}
				
	  })
	  
	  
}


</script>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
    <h1>글추가 페이지</h1>
    
    
    <form id="insertForm">
    
       번호:<input type="text" name="bnum"  value="${max }"/><br>
      
      제목:  <input type="text"  name="title"/><br>
       작성자:  <input type="text" name="writer" /><br>
        조회수:  <input type="text" name="bcnt" value="0"/><br>
         내용: <textarea rows="30" cols="40" name="bcontent"></textarea>  <br>
         분류: <input type="text" name="btype" /><br>
    
    </form>
    
    <button type="button" onclick="insert()">글추가</button>
</body>
</html>