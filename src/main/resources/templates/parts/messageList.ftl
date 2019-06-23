<#include "security.ftl">
<div class="card-columns" id="idCardList">
    <#list messages as message>
        <div class="card my-3" data-id="${message.id}">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${message.text}</span> <br/>
                <i>#${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-messages/${message.author.id}">${message.authorName}</a>
                <#if message.author.id == currentUserId>
                    <a class="btn btn-outline-info btn-sm ml-2"
                       href="/user-messages/${message.author.id}/?message=${message.id}">Edit</a>
                </#if>

            </div>
        </div>

    <#else >
        No message
    </#list>
</div>