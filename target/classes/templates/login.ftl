<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as login>
<@c.page>
    <div>Login page</div>
    <@login.login "/login" />
    <a href="/registration">Add new user</a>
</@c.page>