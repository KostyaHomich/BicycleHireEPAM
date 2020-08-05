<#import "/spring.ftl" as spring/>
<table class="table table-striped jambo_table bulk_action">

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
    <form style="display: inline-block;"
                          action="/bicycle_best/delete"
                          method="post">
                        <input type="hidden" name="bicycleId" value="${bicycle.id}">
                        <input type="submit" value="<@spring.message 'page.button.delete'/>">
                    </form>
    </#if>
</td>

</tr>
</#list>

</tbody>

</table>


