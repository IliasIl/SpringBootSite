<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <@l.logout />
    </div>

    <div>
        <form method="post" action="filter">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input typ="text" name="filt"/>
            <button type="submit">Найти</button>
        </form>
    </div>

    <div>
        <form method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="text" name="text" placeholder="Сообщение"/>
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
        </div>
    <#else >
        No message
    </#list>
</@c.page>
