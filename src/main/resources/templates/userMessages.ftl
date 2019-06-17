<#import "parts/common.ftl" as c>
<@c.page>
    <#if isCurrent>
        <#if message??>
            <#include "parts/messageEdit.ftl" />
        </#if>
    </#if>
    <#include "parts/messageList.ftl" />
</@c.page>