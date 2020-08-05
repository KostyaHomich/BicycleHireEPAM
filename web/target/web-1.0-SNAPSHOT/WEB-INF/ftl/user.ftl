<#import "/spring.ftl" as spring/>
<head>
    <title>User page</title>
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="resources/css/reset.css">
    <link rel="stylesheet" href="resources/css/responsive.css">

    <link rel="text/javascript" href="resources/js/jquery.js">
    <link rel="text/javascript" href="resources/js/main.js">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="http://fontawesome.io/assets/font-awesome/css/font-awesome.css">

    <link rel="stylesheet" href="resources/css/style_locale.css">


    <link href="resources/css/bootstrap.min.css" rel="stylesheet">

    <link href="resources/css/nprogress.css" rel="stylesheet">
    <link href="resources/css/green.css" rel="stylesheet">
    <link href="resources/css/custom.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link href='https://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
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



<#include "header.ftl">



<table class="table table-striped jambo_table bulk_action">
    <thead>
    <tr class="headings">
        <th class="column-title"><@spring.message 'user.login'/></th>
        <th class="column-title"><@spring.message 'user.email'/></th>
        <th class="column-title"><@spring.message 'user.first_name'/></th>
        <th class="column-title"><@spring.message 'user.last_name'/></th>
    </tr>
    </thead>
    <tbody>
        <tr class="even pointer">
            <td class=" "> ${Session.signInUser.login}</td>
            <td class=" "> ${Session.signInUser.email}</td>
            <td class=" "> ${Session.signInUser.firstName}</td>
            <td class=" "> ${Session.signInUser.lastName}</td>
        </tr>
    </tbody>
</table>
<#include "footer.ftl" >

</body>
