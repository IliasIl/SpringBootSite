<#include "security.ftl">
<#import "login.ftl" as l>
<nav class="navbar navbar-expand-sm navbar-light bg-light">
    <a class="navbar-brand" href="/">Sweater</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="navbarSupport">
        <span class="navbar-toggler-icon"/>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupport">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Message</a>
            </li>
            <#if isAdmin>
                <li class="nav-item">
                    <a class="nav-link" href="/user">User list</a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item">
                    <a class="nav-link" href="/user/profile">Редактировать</a>
                </li>
            </#if>
        </ul>

    </div>
    <#if buttonOn>
        <div class="navbar-text mr-3">${name}</div>
        <@l.logout />
    </#if>
</nav>