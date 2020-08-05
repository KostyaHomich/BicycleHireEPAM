<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>


<head>
    <title>${sessionScope.signInUser.getRoles().contains(UserRole.ROLE_USER)} page</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/responsive.css">

    <link rel="text/javascript" href="${pageContext.request.contextPath}/resources/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/resources/js/main.js">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style_locale.css">


    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/css/nprogress.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/green.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/custom.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style_locale.css">

</head>
<body>
<c:choose>
    <c:when test="${not empty requestScope.get('lang')}">
        <fmt:setLocale value="${requestScope.get('lang')}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie['lang'].value}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="/text" scope="application"/>



<%@include file="header.jsp" %>



<table class="table table-striped jambo_table bulk_action">
    <thead>
    <tr class="headings">
        <th class="column-title"><fmt:message key="user.login"/></th>
        <th class="column-title"><fmt:message key="user.email"/></th>
        <th class="column-title"><fmt:message key="user.first_name"/></th>
        <th class="column-title"><fmt:message key="user.last_name"/></th>
    </tr>
    </thead>
    <tbody>
        <tr class="even pointer">
            <td class=" ">${sessionScope.signInUser.getLogin()}</td>
            <td class=" ">${sessionScope.signInUser.getEmail()}</td>
            <td class=" ">${sessionScope.signInUser.getFirstName()}</td>
            <td class=" ">${sessionScope.signInUser.getLastName()}</td>
        </tr>
    </tbody>
</table>
<%@include file="footer.jsp" %>

</body>
