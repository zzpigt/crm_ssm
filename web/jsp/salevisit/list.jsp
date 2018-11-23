﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>访问记录列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/LoadDictSelect.js"></script>
<SCRIPT language=javascript>

	$(function(){

	    $("#clear").click(function(){
	        $("#custId").val("");
	        $("#custName").val("");
		})

	})

    function to_page(page){
        if(page){
            $("#page").val(page);
        }
        var size = $("#size").val();
        $("#pageSizeHid").val(size);
        document.customerForm.submit();

    }

    function clickSelect(id, name){
        var win = window.opener;
        var doc = win.document;
        var custIdDom = doc.getElementById("custId");
        var custNameDom = doc.getElementById("custName");
        custIdDom.value = id;
        custNameDom.value = name;
        window.close();
    }

</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	
		
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
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
								<TD class=manageHead>当前位置：访问记录管理 &gt; 记录列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>	
										<FORM id="pageForm" name="customerForm"
										action="${pageContext.request.contextPath }/saleVisit/list.action"
										method=post>
										<!-- 隐藏域.当前页码 -->
										<input type="hidden" name="pageNum" id="page" value="${pageBean.pageNum}" />
										<!-- 隐藏域.每页显示条数 -->
										<input type="hidden" name="pageSize" id="pageSizeHid" value="${pageBean.pageSize}" />
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
													<TD>客户名称：</TD>
													<TD>
														<input type="hidden" name="customer.custId" id="custId" value="${queryVo.customer.custId}" />
														<INPUT class=textbox readonly="readonly" style="WIDTH: 80px" maxLength=50 name="customer.custName"  id="custName" value="${queryVo.customer.custName}" >
														<input type="button" value="选择客户" onclick="window.open('${pageContext.request.contextPath}/customer/list.action?select=true','','width=600,height=300')" />
													</TD>
													
													<TD><INPUT class=button id=sButton2 type=submit
														value=" 筛选 " name=sButton2></TD>
													<TD><INPUT class=button id="clear" type="button"
															   value=" 清空 " name=sButton2></TD>
												</TR>
											</TBODY>
										</TABLE>
									</FORM>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>业务员名称</TD>
													<TD>客户名称</TD>
													<TD>访问时间</TD>
													<TD>访问人</TD>
													<TD>访问地址</TD>
													<TD>访问详情</TD>
													<TD>下次访问时间</TD>
													<TD>操作</TD>
												</TR>
												<c:forEach items="${pageBean.list}" var="salevisit">
													<TR
															style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD>${salevisit.visitUserId.userName }</TD>
														<TD>${salevisit.visitCustId.custName}</TD>
														<TD>${salevisit.visitTime}</TD>
														<TD>${salevisit.visitInterviewee}</TD>
														<TD>${salevisit.visitAddr }</TD>
														<TD>${salevisit.visitDetail }</TD>
														<TD>${salevisit.visitNexttime }</TD>
														<TD>
															<c:if test="${!queryVo.select}">
																<a href="${pageContext.request.contextPath }/saleVisit/toEdit.action?visitId=${salevisit.visitId}">修改</a>
																&nbsp;&nbsp;
																<a href="${pageContext.request.contextPath }/saleVisit/delete.action?visitId=${salevisit.visitId}">删除</a>
															</c:if>
															<c:if test="${queryVo.select}">
																<input type="button" value="选择" onclick="clickSelect('${customer.custId}', '${customer.custName}')" />
															</c:if>
														</TD>
													</TR>

												</c:forEach>

											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
													style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												共[<B>${pageBean.totalCount}</B>]条记录,[<B>${pageBean.totalPage}</B>]页
												,每页显示
												<select id="size" name="pageSize">

												<option value="5" <c:if test="${pageBean.pageSize==5 }">selected</c:if>>5</option>
												<option value="10" <c:if test="${pageBean.pageSize==10 }">selected</c:if>>10</option>
												</select>
												条
												[<A href="javascript:to_page(${pageBean.pageNum-1})">前一页</A>]
												<B>${pageBean.pageNum}</B>
												[<A href="javascript:to_page(${pageBean.pageNum+1})">后一页</A>]
												到
												<input type="text" size="3" id="pageNum" name="pageNum" />
												页

												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
</BODY>
</HTML>
