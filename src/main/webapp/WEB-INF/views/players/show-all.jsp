<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<jsp:include page="../common/header.jsp"/>
<title>${pageTitle}</title>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>All Players <small>players</small></h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
							</li>
							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
								   aria-expanded="false"><i class="fa fa-wrench"></i></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">Settings 1</a>
									</li>
									<li><a href="#">Settings 2</a>
									</li>
								</ul>
							</li>
							<li><a class="close-link"><i class="fa fa-close"></i></a>
							</li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<p class="text-muted font-13 m-b-30">
							Internation cricket Council registered players!
						</p>
						<div id="datatable_wrapper" class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<div class="row">
								<div class="col-sm-12">
									<table id="datatable" class="table table-striped table-bordered dataTable no-footer"
										   role="grid" aria-describedby="datatable_info">
										<thead>
										<tr role="row">
											<th class="sorting_asc" tabindex="0" aria-controls="datatable" rowspan="1"
												colspan="1" aria-sort="ascending"
												aria-label="Name: activate to sort column descending" style="width: 157px;">
												ID
											</th>
											<th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
												colspan="1" aria-label="Position: activate to sort column ascending"
												style="width: 261px;"> Player Name
											</th>
											<th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
												colspan="1" aria-label="Office: activate to sort column ascending"
												style="width: 117px;"> Player Expertise
											</th>
											<th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
												colspan="1" aria-label="Age: activate to sort column ascending"
												style="width: 60px;">Player Status
											</th>
											<th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
												colspan="1" aria-label="Salary: activate to sort column ascending"
												style="width: 88px;">Edit
											</th>
											<th class="sorting" tabindex="0" aria-controls="datatable" rowspan="1"
												colspan="1" aria-label="Salary: activate to sort column ascending"
												style="width: 88px;">Details
											</th>

										</tr>
										</thead>

										<tbody>
										<c:forEach items="${players}" var="player">
											<tr role="row" class="odd">
												<td  class="sorting_1">${ player.id }</td>
												<td>${ player.userInfo.name }</td>
												<td>${ player.expertise }</td>
												<td>${ player.playerStatus }</td>
												<td><a href="edit?id=${ player.id }">Edit</a></td>
												<td><a href="details?id=${ player.id }">Details</a></td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>

<!-- GLOBAL FOOTER -->
<jsp:include page="../common/footer.jsp"/>
