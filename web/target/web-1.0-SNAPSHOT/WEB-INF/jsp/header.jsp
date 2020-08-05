<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>


<section class="hero">
    <header>
        <div class="wrapper">
            <lang:lang/>
            <nav>
                <div>
                    <ul>
                        <c:if test="${sessionScope.signInUser.getRoles().contains(UserRole.ROLE_ADMIN)}">
                            <li><a style="font-size: 12px" href="${pageContext.request.contextPath}/users">
                                <fmt:message key="page.header.users"/></a></li>
                        </c:if>
                        <c:if test="${sessionScope.signInUser.getRoles().contains(UserRole.ROLE_USER)}">
                            <li><a style="font-size: 12px"  href="${pageContext.request.contextPath}/bicycles_best">
                                <fmt:message key="page.header.best_bicycles"/></a></li>
                        </c:if>
                        <li><a style="font-size: 12px" href="${pageContext.request.contextPath}/bicycles">
                            <fmt:message key="page.header.bicycles"/></a></li>
                        <li><a style="font-size: 12px" href="${pageContext.request.contextPath}/point_hires">
                            <fmt:message key="page.header.point_hires"/></a></li>
                        <li><a style="font-size: 12px" href="${pageContext.request.contextPath}/user">
                            <fmt:message key="page.header.button.account"/></a></li>
                    </ul>

                    <a href="${pageContext.request.contextPath}/user/logout"
                       class="login_btn">
                        <fmt:message key="page.header.button.logout"/>
                    </a>

                </div>
            </nav>
        </div>
    </header>
</section>

