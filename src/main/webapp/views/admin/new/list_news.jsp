<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="main-content">
	<form action="<c:url value='/admin-new'/>" id="formSubmit" method="get">
		<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Trang chủ</a>
							</li>
						</ul>
						<!-- /.breadcrumb -->
					</div>
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<div class="row">
									<div class="col-xs-12">
										<div class="table-responsive">
											<table class="table table-bordered">
                                                <thead>
                                                  <tr>
                                                    <th scope="col">Tên Bài Viết</th>
                                                    <th scope="col">Mô tả ngắn</th>
                                                    
                                                  </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="item" items = "${model.listResult}">
	                                                 <tr>
	                                                   
	                                                    <td>${item.title}</td>
	                                                    <td>${item.shortDescription}</ td>
	                                                   
	                                                  </tr>
                                                </c:forEach>
                                                 
                                                 
                                                </tbody>
                                              </table>
                                             <!-- Phân Trang  -->
											<ul class="pagination" id="pagination"></ul>
											
											 <!-- name trùng với filed trong model -->
											<input type="hidden" value="" id="page" name="page"/>
											<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
											<input type="hidden" value="" id="sortName" name="sortName"/>
											<input type="hidden" value="" id="sortBy" name="sortBy"/>
											<input type="hidden" value="" id="type" name="type"/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		 </form>
		</div>
        <!-- /.main-content -->

    
		<script type="text/javascript">
			var totalPages = ${model.totalPage};
			var currentPage = ${model.page};
			var limit = 2;
            $(function () {
				window.pagObj = $('#pagination').twbsPagination({
					totalPages: totalPages,
					visiblePages: 10,
					startPage: currentPage,
					onPageClick: function (event, page) {
						if (currentPage != page) {
							$('#maxPageItem').val(limit);
							$('#page').val(page);
							$('#sortName').val('title');
							$('#sortBy').val('desc');
							$('#formSubmit').submit();
						}
					}
				});
			});
        </script>
</body>
</html>