<#import "parts/common.ftl" as c>
<@c.page>
    User editor
    <form action="/user" method="post">
        <input type="text" value="${user.username}" name="username"/>
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}"
                            ${user.roles?seq_contains(role)?string("checked", "")}>${role} </label>
            </div>
        </#list>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="hidden" name="userId" value="${user.id}"/>
        <button type="submit">Сохранить</button>
    </form>

</@c.page>