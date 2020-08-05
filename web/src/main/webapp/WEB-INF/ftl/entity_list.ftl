<#import "/spring.ftl" as spring/>
<head>
    <title>List page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="resources/css/reset.css">
    <link rel="stylesheet" href="resources/css/responsive.css">

    <link rel="text/javascript" href="resources/js/jquery.js">
    <link rel="text/javascript" href="resources/js/main.js">

    <link href="resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="resources/css/nprogress.css" rel="stylesheet">
    <link href="resources/css/green.css" rel="stylesheet">
    <link href="resources/css/custom.min.css" rel="stylesheet">

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

<#include "header.ftl"/>

<div class="x_panel">
    <div class="x_content">
<#if error??>
    <@spring.message '${error}'/>
</#if>
<#--<c:if test="${not empty requestScope.error}">-->
<#--    <fmt:message key="${requestScope.error}"/>-->
<#--</c:if>-->

        <div class="table-responsive">

            <#include "./frames/${viewName}.ftl"/>
            <#include "footer.ftl"/>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script type="text/javascript">
    window.onload = function () {
        window.scrollTo(0, 1000);
    }
</script>
</body>

