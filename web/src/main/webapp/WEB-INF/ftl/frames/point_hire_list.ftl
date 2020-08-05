<#import "/spring.ftl" as spring/>
<table class="table table-striped jambo_table bulk_action">
    <thead>
    <tr class="headings">
        <th><@spring.message 'point_hire.location'/></th>
        <th><@spring.message 'point_hire.description'/></th>
        <th><@spring.message 'point_hire.telephone'/></th>
    </tr>
    </thead>
    <tbody>
<#list pointHireList as pointHire>
    <tr class="even pointer">
        <td>${pointHire.location}</td>
        <td>${pointHire.description}</td>
        <td>${pointHire.telephone}</td>
    </tr>
</#list>
    </tbody>
</table>


