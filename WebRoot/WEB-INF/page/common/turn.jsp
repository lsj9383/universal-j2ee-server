<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>turn page</title>
<script type="text/javascript">
	var t = setTimeout("timeout()", 1000);
	
	function timeout(){
        var time=parseInt(document.getElementById("sec").innerHTML);
        time = time - 1;
        if(time < 0){
        	window.location.href="${target}";
        }else{
        	document.getElementById("sec").innerHTML = time;
        	t = setTimeout("timeout()", 1000);
        }
	}
</script>
</head>
<body>
	<div style="margin: 5% 10% 2% 10%">
		<table style="width: 100%">
			<tbody><tr>
				<td width="70%"><ul class="list-group">
		          	 <li class="list-group-item title">
		          	 	<p>${msg}, <span id="sec">${sec}</span>后跳转</p>
						<a href="${target}">立即跳转</a>
		          	 </li>
				</ul></td>
			</tr></tbody>
		</table>
	</div>
</body>
</html>