<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>


	
	<div class="row" style="margin-top:75px;margin-left:auto;margin-right:auto;background-color:rgba(255,255,255,0.7);">
	${paginationDto}
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>
							아이디
						</th>
						<th>
							이름
						</th>
						<th>
							email
						</th>
						<th>
							성별
						</th>
						<th>
							유저등급
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="userInfoVo">
					<tr>
						<td>${userInfoVo.user_id}</td>
						<td>${userInfoVo.user_name}</td>
						<td>${userInfoVo.user_email}</td>
						<td>
						<c:if test="${userInfoVo.user_gender eq 'M'}">
						남
						</c:if>
						
						<c:if test="${userInfoVo.user_gender eq 'F'}">
						여
						</c:if>
						
						
						</td>
						<td>
						<c:if test="${userInfoVo.user_level == 0}">
						관리자
						</c:if>
						
						<c:if test="${userInfoVo.user_level == 1}">
						스태프
						</c:if>
						
						<c:if test="${userInfoVo.user_level == 2}">
						인디팀
						</c:if>
						
						<c:if test="${userInfoVo.user_level == 3}">
						일반유저
						</c:if>
						
					</tr>
					</c:forEach>
				</tbody>
			</table>
			
			
		</div>
		
		<div class="row">
		<div class="col-md-12">
			<nav>
				<ul class="pagination">
					<li class="page-item">
						<c:if test="${paginationDto.prev == true }">
							<a class="page-link" href="#">Previous</a>
						</c:if>
						
					</li>
					<c:forEach var="i" begin="${paginationDto.startPage}" 
									end="${paginationDto.endPage }">
						<li class="page-item">
							<a class="page-link" 
							href="/indiefes/user/user-management?page=${i}">
							${i}</a>
						</li>			
					</c:forEach>
					
					
					<c:if test="${paginationDto.next == true }">
						<li class="page-item">
							<a class="page-link" href="#">Next</a>
						</li>
					</c:if>
					
				</ul>
			</nav>
		</div>
	</div>
	</div>





<%@ include file="../include/footer.jsp" %>