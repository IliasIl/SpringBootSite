<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout />
    </div>
    <span><@l.a "/user" "User list"/></span>
    <div>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${filter?ifExists}"/>
            <button type="submit">Найти</button>
        </form>
    </div>

    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="text" placeholder="Сообщение"/>
            <input type="file" name="file"/>
            <input type="text" name="tag" placeholder="Тэг"/>
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <#list messages as message>
        <div>
            <span>${message.id}</span>
            <span>${message.text}</span>
            <span>${message.tag}</span>
            <strong>${message.authorName}</strong>
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
        </div>
    <#else >
        No message
    </#list>
</@c.page>
