<#macro addMes ifMes>
<a class="btn btn-primary"
   data-toggle="collapse" href="#collapseExample" role="button"
   aria-expanded="false">
   <#if ifMes> Редактировать сообщение <#else> Добавить сообщение </#if></a>
<div class="collapse <#if message??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">

            <div class="form-group">
                <input type="text"
                       class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.text}</#if>"
                       name="text"
                       placeholder="Сообщение"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>

            <div class="form-group">
                <input type="text"
                       class="form-control ${(tagError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.tag}</#if>"
                       name="tag"
                       placeholder="Тэг"/>
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>

            <div class="custom-file">
                <input type="file" name="file" id="customFile"/>
                <label class="custom-file-label" for="customFile">
                    Найти файл</label>
            </div>
            <div class="form-group mt-3">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id"
                   value="<#if message?? && message.getId()??>${message.getId()}</#if>"/>

        </form>
    </div>
</div>
</#macro>