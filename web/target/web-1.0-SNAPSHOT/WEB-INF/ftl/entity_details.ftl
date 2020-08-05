<head>
    <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>

<#--<c:choose>-->
<#--    <c:when test="${not empty requestScope.get('lang')}">-->
<#--        <fmt:setLocale value="${requestScope.get('lang')}"/>-->
<#--    </c:when>-->
<#--    <c:otherwise>-->
<#--        <fmt:setLocale value="${cookie['lang'].value}"/>-->
<#--    </c:otherwise>-->
<#--</c:choose>-->
<#--<fmt:setBundle basename="/text" scope="application"/>-->
<#include "./frames/${viewName}.ftl"/>
</body>
