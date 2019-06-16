<#import "parts/common.ftl" as c>
<@c.page>
    <#if isCurrent>
        <#include "parts/messageEdit.ftl" />
    </#if>
    <#include "parts/messageList.ftl" />
</@c.page>