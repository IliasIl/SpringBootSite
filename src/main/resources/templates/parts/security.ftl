<#assign
known=Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
    user=Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name=user.getUsername()
    isAdmin=user.isAdmin()
    currentUserId=user.getId()
    buttonOn=true
    >

<#else>
    <#assign
    name="unknown"
    isAdmin=false
    currentUserId=-1
    buttonOn=false
    >
</#if>