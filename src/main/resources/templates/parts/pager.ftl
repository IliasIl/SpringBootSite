<#macro pager url page>


    <div class="m-3">
        <ul class="pagination">
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
                        <a class="page-link" href="${url}?page=${p-1}&size=${page.getSize()}" tabindex="-1">${p}</a>
                    </il>
                </#if>
            </#list>

        </ul>


        <ul class="pagination">
            <il class="page-item disabled">
                <a class="page-link" tabindex="-1" href="#">Элементов на странице</a>
            </il>
            <#list [5, 10, 15, 20] as c>
                <#if c==page.getSize()>
                    <il class="page-item active">
                        <a class="page-link" href="#" tabindex="-1">${c}</a>
                    </il>
                <#else>
                    <il class="page-item">
                        <a class="page-link" href="${url}?page=${page.getNumber()}&size=${c}" tabindex="-1">${c}</a>
                    </il>
                </#if>
            </#list>

        </ul>


    </div>

</#macro>