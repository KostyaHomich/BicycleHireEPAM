<#import "/spring.ftl" as spring/>

<head>
<title>Main</title>

    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>
    <link rel="stylesheet" href="resources/css/reset.css">
    <link rel="stylesheet" href="resources/css/responsive.css">
    <link rel="text/javascript" href="resources/js/jquery.js">
    <link rel="text/javascript" href="resources/js/main.js">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' >
    <link rel="stylesheet" href="resources/css/style_locale.css">

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

<section class="hero">
    <header>
<div class="wrapper">
<#include "./frames/lang.ftl">
<#--<lang:lang/>-->
<nav>
<#if Session.signInUser??>
<a href="user"
                       class="login_btn"><@spring.message 'page.header.button.account'/> </a>
<#else>
<a href="registration"
                       class="login_btn"><@spring.message 'page.main.button.registration'/> </a>

<a href="login"
                       class="login_btn"><@spring.message 'page.button.login'/> </a>
</#if>

</nav>
</div>
</header>

<!--  end header section  -->
<section class="caption">
        <h2 class="caption"><@spring.message 'page.main.welcome'/></h2>
</section>
</section>
<!--  end hero section  -->
<#include "footer.ftl"/>

</body>
