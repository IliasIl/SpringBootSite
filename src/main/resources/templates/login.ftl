<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as login>
<@c.page>
    <@login.div "Login page"/>
    <@login.login "/login" />
    <@login.a "/registration" "Add new user"/>
</@c.page>