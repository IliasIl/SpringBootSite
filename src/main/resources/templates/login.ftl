<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as login>
<@c.page>
    <div class="mb-1">Login page</div>
    <div class="mt-4">${message?ifExists}</div>
    <@login.login "/login" false/>
</@c.page>