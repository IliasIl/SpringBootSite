<#import "parts/common.ftl" as c>
<#import "parts/messageEdit.ftl" as mes>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control"
                       value="${filter?ifExists}" placeholder="Поиск по тэгу"/>
                <button type="submit" class="btn btn-primary ml-2">Поиск</button>
            </form>
        </div>
    </div>
    <@mes.addMes false/>
    <#include "parts/messageList.ftl" />
</@c.page>
