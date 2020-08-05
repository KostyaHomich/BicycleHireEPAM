<#import "/spring.ftl" as spring/>
<div class="container">
    <section id="content">

        <form action="user/update" method="post">
            <h1> <@spring.message 'page.title.user_details'/></h1>
<#if error??>
 <@spring.message '${error}'/>
</#if>

<#--            <c:if test="${not empty requestScope.errorsList}">-->
<#--                <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">-->
<#--                    <c:if test="${entry.value.size() >0}">-->
<#--                        <c:forEach var="value" items="${entry.value}">-->
<#--                             <@spring.message '${value}'/>-->
<#--                        </c:forEach>-->
<#--                    </c:if>-->
<#--                </c:forEach>-->
<#--            </c:if>-->

            <input type="hidden" id="id" name="id" value="${user.id}">

            <input type="hidden" value="${user.login}" name="login"/>
            <div>
                <p> <@spring.message 'user.email'/></p>
                <input type="text" value="${user.email}"
                       placeholder=" <@spring.message 'user.email'/>"
                       id="email" name="email"/>
            </div>
            <div>
                <p> <@spring.message 'user.first_name'/></p>

                <input type="text" value="${user.firstName}"
                       placeholder=" <@spring.message 'user.first_name'/>" id="first_name"
                       name="first_name"/>
            </div>
            <div>
                <p> <@spring.message 'user.last_name'/></p>

                <input type="text" value="${user.lastName}"
                       placeholder=" <@spring.message 'user.last_name'/>"
                       id="last_name" name="last_name"/>
            </div>

            <div>
                <input type="submit" value=" <@spring.message 'page.button.update'/>">
            </div>

        </form>
        <form action="users" method="post">
            <div>
                <input type="submit" value=" <@spring.message 'page.button.back'/>">
            </div>
        </form>
        <!-- form -->
    </section>
    <!-- content -->
</div>
