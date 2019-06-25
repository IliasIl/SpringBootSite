<#macro pager url page>
    <ul class="pagination m-3">
        <il class="page-item disabled">
            <a class="page-link" tabindex="-1" href="#"> Страницы</a>
        </il>

        <#list 1..page.getTotalPages() as p>
            <#if p-1==page.getNumber()>
                <il class="page-item active">
                    <a class="page-link " href="#" tabindex="-1">${p}</a>
                </il>
            <#else>
                <il class="page-item">
                    <a class="page-link" href="${url}?page=${p-1}" tabindex="-1">${p}</a>
                </il>
            </#if>
        </#list>

    </ul>


</#macro>