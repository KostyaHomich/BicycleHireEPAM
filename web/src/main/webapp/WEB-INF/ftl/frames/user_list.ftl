<#import "/spring.ftl" as spring/>

<table class="table table-striped jambo_table bulk_action">
    <thead>

    <tr class="headings">
        <th class="column-title"> <@spring.message 'user.login'/></th>
        <th class="column-title"> <@spring.message 'user.email'/></th>
        <th class="column-title"> <@spring.message 'user.first_name'/></th>
        <th class="column-title"> <@spring.message 'user.last_name'/></th>
        <th class="column-title"> <@spring.message 'user.status'/></th>
        <th class="column-title no-link last"><span class="nobr"> <@spring.message 'page.button.action'/></span></th>

    </tr>
    </thead>
    <tbody>


<#list users as user>
        <tr class="even pointer">
            <td class=" ">${user.login}</td>
            <td class=" ">${user.email}</td>
            <td class=" ">${user.firstName}</td>
            <td class=" ">${user.lastName}</td>
            <td class=" ">${user.enabled?string("Active", "Banned")}</td>
            <td>
                <form style="display: inline-block;"
                      action="user_details" method="post">
                    <input type="hidden" name="userId" value="${user.id}">
                    <input  type="submit" value="<@spring.message 'page.button.show'/>">
                </form>
            </td>
        </tr>
</#list>
    </tbody>
</table>

