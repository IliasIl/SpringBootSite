<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    List of USERS
    <table>
        <thead>
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.existIsername}</td>
                <td><#list user.existRoles as role>${role}<#sep>, </#list></td>
                <td><@l.a "/user/${user.id}" "Edit"/> </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>