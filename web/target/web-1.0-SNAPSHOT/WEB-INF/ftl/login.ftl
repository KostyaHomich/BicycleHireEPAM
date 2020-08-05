<#import "/spring.ftl" as spring/>
<head>
    <title>Login</title>
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

<div class="container">
    <section id="content">

        <form action="logging" method="post">

            <h1><@spring.message 'page.title.login_form'/></h1>

<#if error??>
    <@spring.message '${error}'/>
</#if>
<#--            <c:if test="${not empty requestScope.error}">-->
<#--                <@spring.message '${requestScope.error}"/>-->
<#--            </c:if>-->


<#--            <c:if test="${not empty requestScope.errorsList}">-->
<#--                <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">-->
<#--                    <c:if test="${entry.value.size() >0}">-->
<#--                        <c:forEach var="value" items="${entry.value}">-->
<#--                            <@spring.message '${value}"/>-->
<#--                        </c:forEach>-->
<#--                    </c:if>-->
<#--                </c:forEach>-->
<#--            </c:if>-->
            <div>
                <p><@spring.message 'user.login'/></p>
                <input type="text"
                       placeholder="<@spring.message 'user.login'/>"
                       id="login"
                       name="login"
                       minlength="5"
                       maxlength="15"
                       pattern="^[a-zA-Z0-9]{5,15}$"
                       required
                       title="<@spring.message 'user.error.invalid_login'/>"
                />
            </div>
            <div>
                <p><@spring.message 'user.password'/></p>
                <input type="password"
                       placeholder="<@spring.message 'user.password'/>"
                       id="password"
                       name="password"
                       minlength="7"
                       maxlength="25"
                       required
                       title="<@spring.message 'user.error.invalid_password'/>"
                />
            </div>
            <div>
                <input type="submit" value="<@spring.message 'page.button.login'/>"/>

                <a href="registration">
                <@spring.message 'page.button.register'/></a>
            </div>

        </form>
        <!-- form -->

    </section>
    <!-- content -->
</div>
<!-- container -->
</body>

