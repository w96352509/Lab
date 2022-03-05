<%@ page isErrorPage="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spform"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
		href="https://unpkg.com/purecss@2.0.6/build/pure-min.css"/>
<meta charset="UTF-8">
<title>Fundstock</title>
<script>
  function updateFundstock(sid){
     document.getElementById('fundstock').action='/Labpractise/mvc/lab/fundstock/' + sid;
     document.getElementById('fundstock').submit();
  }
  function deleteFundstock(sid){
    document.getElementById('_method').value = 'DELETE';
    updateFundstock(sid);
  }
  </script>
</head>
<body style="padding: 15px">
   <table>
     <tr>
       <td valign="top">
         <spform:form class="pure-form" method="post"
					  modelAttribute="fundstock"
					  action="${ pageContext.request.contextPath }/mvc/lab/fundstock/">
           <fieldset>
             <legend> Fundstock Form</legend>
             <input type="hidden" id="_method" name="_method" value="${_method}">
             序號:
             <spform:input path="sid"/>
             <p/>
             代號:
             <spform:input path="symbol"/>
             <p/>
             數量
             <spform:input path="share"/>
             <p/>
             基金
             <spform:select path="fid">
             <spform:option value="">請選擇</spform:option>
             <spform:options items="${funds}" itemValue="fid"  itemLabel="fname"/>
             </spform:select>
             <p/>
             <button type="submit" class="pure-button pure-button-primary"
							${ _method=='POST'?'':'disabled' }>新增</button>
			 <button type="button" onclick="updateFundstock(${sid})" class="pure-button pure-button-primary"
							${ _method=='PUT'?'':'disabled' }>修改</button>				
           </fieldset>
         </spform:form>
       </td>
       <!-- Fundstock List -->
       <td valign="top">
         <form class="pure-form">
           <fieldset>
             <legend>
			Fundstock List&nbsp;|&nbsp;
			<a href="${ pageContext.request.contextPath }/mvc/lab/fundstock/page/0">全部</a>
			&nbsp;&nbsp;總筆數:${count}&nbsp;&nbsp;|&nbsp;
			<c:forEach var="num" begin="1" end="${ pageTotalCount+1 }">
			<a href="${ pageContext.request.contextPath }/mvc/lab/fundstock/page/${ num }">${ num }</a>
			</c:forEach>
			 </legend>
              <table class="pure-table pure-table-bordered">
              <thead>
                <tr>
                  <th>序號(sid)</th>
                  <th>代號(symbol)</th>
                  <th>數量(share)</th>
                  <th>基金(fund)</th>
                  <th>刪除</th>
                </tr>
               </thead>
               <tbody>
                 <c:forEach  items="${fundstocks}" var="fundstock" >
                  <tr>
                    <td>
                    <a href="${pageContext.request.contextPath}/mvc/lab/fundstock/${fundstock.sid}">
                    ${fundstock.sid}
                    </a>
                    </td>
                    <td>${fundstock.symbol}</td>
                    <td>${fundstock.share}</td>
                    <td>${fundstock.fund.fname}</td>
                    <td><button type="button" onclick="deleteFundstock(${fundstock.sid})" class="pure-button pure-button-primary">
                         刪除</button>
					</td>
                  </tr>
                 </c:forEach> 
               </tbody>
              </table>
           </fieldset>
         </form>
     </tr>
 </table>
</body>
</html>