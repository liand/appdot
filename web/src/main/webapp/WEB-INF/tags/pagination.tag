<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="paginationSize" type="java.lang.Integer" required="true"%>
<%@ attribute name="onclick" type="java.lang.String" required="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
int current =  page.getNumber() + 1;
int begin = Math.max(1, current - paginationSize/2);
int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

request.setAttribute("current", current);
request.setAttribute("begin", begin);
request.setAttribute("end", end);
%>
<script type="text/javascript">
	function  jumpToPage(page){
		// body is  empty  without onclick
		<%if(onclick!=null){%>
		var oldPage=this.page;
		this.page=page;
		<%=onclick%>
		this.page=oldPage;
		return false;
		<%}%>
	}
</script>
<div>
	<ul class="pagination">
		 <% if (page.hasPreviousPage()){%>
               	<li><a href="?page=1&sortType=${sortType}&${searchParams}" onclick="return jumpToPage(1);">&lt;&lt;</a></li>
                <li><a href="?page=${current-1}&sortType=${sortType}&${searchParams}" onclick="return jumpToPage(${current-1});">&lt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&lt;&lt;</a></li>
                <li class="disabled"><a href="#">&lt;</a></li>
         <%} %>
 
		<c:forEach var="i" begin="${begin}" end="${end}">
            <c:choose>
                <c:when test="${i == current}">
                    <li class="active"><a href="javascript:void(0);">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="?page=${i}&sortType=${sortType}&${searchParams}" onclick="return jumpToPage(${i});">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
	  
	  	 <% if (page.hasNextPage()){%>
               	<li><a href="?page=${current+1}&sortType=${sortType}&${searchParams}" onclick="return jumpToPage(${current+1});">&gt;</a></li>
                <li><a href="?page=${page.totalPages}&sortType=${sortType}&${searchParams}" onclick="return jumpToPage(${page.totalPages});">&gt;&gt;</a></li>
         <%}else{%>
                <li class="disabled"><a href="#">&gt;</a></li>
                <li class="disabled"><a href="#">&gt;&gt;</a></li>
         <%} %>

	</ul>
</div>

