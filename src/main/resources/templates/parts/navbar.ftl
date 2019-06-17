<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-sm navbar-light bg-light">
    <a class="navbar-brand" href="/">Iliter</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="navbarSupport">
        <span class="navbar-toggler-icon"/>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupport">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Домой</a>
            </li>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/main">Общение</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user-messages/${currentUserId}">Мои сообщение</a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">Список пользователей</a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Редактировать профиль</a>
                </li>
            </#if>
        </ul>

    </div>
    <#if buttonOn>
        <div class="navbar-text mr-3">${name}</div>
        <@l.logout />
        <#else> <div class="navbar-text mr-3">Пожалуйста авторизуйтесь</div>
        <a class="btn btn-info btn-sm" href="/login">Вход</a>
    </#if>
</nav>