<#macro pager url page>
    <#if page.getTotalPages() gt 7>
        <#assign
        totalPages=page.getTotalPages()
        number=page.getNumber()+1


        head=(number>4)
        ?then([1, -1], [1, 2, 3])
        tail=(number< totalPages-3)
        ?then([-1, totalPages],[totalPages-2, totalPages-1, totalPages])
        beforeElem=(number>4 && number<totalPages-1)?then([number-2, number-1],[])
        afterElem=(number< totalPages-3 && number>2)?then([number+1, number+2],[])

        body=head+beforeElem+(number>3 && number<totalPages-2)?then([number],[])+afterElem+tail
        />
    <#else>
        <#assign body=1..page.getTotalPages() >
    </#if>



    <div class="m-3">
        <ul class="pagination">
            <il class="page-item disabled">
                <a class="page-link" tabindex="-1" href="#"> Страницы</a>
            </il>
            <#list body as p>
                <#if p-1==page.getNumber()>
                    <il class="page-item active">
                        <a class="page-link " href="#" tabindex="-1">${p}</a>
                    </il>
                <#elseif p==-1>
                    <il class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">...</a>
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