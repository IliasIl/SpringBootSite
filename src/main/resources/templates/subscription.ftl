<#import "parts/common.ftl" as c>
<@c.page>

    <#--<blockquote class="blockquote text-center">-->
        <#--<p class="mb-0">${userCur.getUsername()}</p>-->
        <#--<footer class="blockquote-footer">${type}</footer>-->
    <#--</blockquote>-->

    <ul class="list-group col-sm-3 mx-auto">
        <il><h3>${userCur.getUsername()}</h3></il>
        <div>
            <il>${type}</il>
        </div>
    </ul>
    <ul class="list-group col-sm-6 mx-auto">
        <#list users as user>
            <il class="list-group-item">
                <a href="/user-messages/${user.id}">${user.getUsername()}</a>
            </il>
        </#list>
    </ul>
</@c.page>