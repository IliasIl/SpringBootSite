<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as login>
<@c.page>
    <div class="mb-1">Login page</div>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <#if message??>
        <div class="alert alert-${param}" role="alert">${message}</div>
    </#if>
    <@login.login "/login" false/>
</@c.page>