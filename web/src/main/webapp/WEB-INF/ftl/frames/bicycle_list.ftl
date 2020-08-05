<#import "/spring.ftl" as spring/>

<table class="table table-striped jambo_table bulk_action">
    <form action="/point_hire_details" method="post">

        <thead>
        <tr class="headings">
            <th class="column-title"><@spring.message 'bicycle.name'/></th>
            <th class="column-title"><@spring.message 'bicycle.description'/></th>
            <th class="column-title no-link last"><span class="nobr"><@spring.message 'page.button.action'/></span></th>
        </tr>
        </thead>
        <tbody>
<#list bicycles as bicycle>

            <tr class="even pointer">

                <td class=" ">${bicycle.name}</td>
                <td class=" ">${bicycle.description}</td>
                <td>
                    <input value="<@spring.message 'page.button.show'/>" type="button" onclick="location.href='${bicycle.link}'" />
<#if Session.signInUser.userRole.name()=="ROLE_USER">

                        <form style="display: inline-block;" action="bicycle_best/add" method="post">
                            <input type="hidden" name="bicycleId" value="${bicycle.id}">
                            <input type="submit" value="<@spring.message 'page.button.add_best_bicycle'/>">
                        </form>
                    </#if>

                </td>

            </tr>
        </#list>

</table>
<div style="float: right">
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
           <#list 1..amountPages as i >
                <#if page != i>
                    <form action="bicycles" method="post">
                        <input type="hidden" name="page" value="${i}">
                        <input type="submit" value="${i}">
                    </form>
                </#if>
          </#list>
        </tr>
    </table>
    <#if (page != 1)>
        <form action="bicycles" method="post">
            <input type="hidden" name="page" value="${page-1}">
            <input type="submit" value="<@spring.message 'page.button.previous'/>">
        </form>
    </#if>


<#if  page lt amountPages>
        <form action="bicycles"
              method="post">
            <input type="hidden" name="page" value="${page+1}">
            <input type="submit" value="<@spring.message 'page.button.next'/>">
        </form>
        </#if>
   </div>



