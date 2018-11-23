﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>修改用户角色</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>

	<script src="${pageContext.request.contextPath}/js/jquery-1.4.4.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/LoadDictSelect.js"></script>



<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/user/edit.action"
		method=post>

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background=${pageContext.request.contextPath }/images/new_020.jpg
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 修改用户角色</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<TR>
								<td>用户编号：</td>
								<td>
									<INPUT type="text" readonly="readonly" class=textbox style="WIDTH: 180px" maxLength=50
										   name="user.userId" value="${user.userId}">
								</td>
								<td>用户帐号：</td>
								<td id="custLevel">
									<INPUT type="text" readonly="readonly" class=textbox style="WIDTH: 180px" maxLength=50
										   name="user.userCode" value="${user.userCode}" >
								</td>
							</TR>

							<TR>

								<td>用户昵称 ：</td>
								<td id=" source">
									<INPUT type="text" class="textbox" style="WIDTH: 180px" maxLength=50
										   name="user.userName" value="${user.userName}">
								</td>
								<td>用户状态：</td>
								<td id="status">
									<INPUT type="text" readonly="readonly" class="textbox" style="WIDTH: 180px" maxLength=50
										   name="user.userState" value="${user.userState}">
								</td>
							</TR>
							<TR>

								<td>用户角色：</td>
								<td>
									<c:forEach items="${roleList}" var="role">
											<input <c:forEach items="${user.roleList}" var="roleByUser"><c:if test="${roleByUser.roleId.roleId == role.roleId}">checked</c:if></c:forEach> type="checkbox" name="roleId"  value="${role.roleId}"/> ${role.roleName}
									</c:forEach>
								</td>

							</TR>
							

							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
