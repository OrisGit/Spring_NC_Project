<div class="container mx-auto">
    <table class="table table-sm table-responsive-md table-hover" id="${tableType}Table" data-type="${tableType}">
        <thead>
        <tr>
        <#if tableType=="drug">
            <th>Название</th>
            <th>Форма</th>
            <th>Производитель</th>
            <th>Активный ингредиент</th>
            <th>Фармакологический</th>
            <th>Терапевтический</th>
        <#elseif tableType=="drugstore">
            <th>Название</th>
            <th>Адрес</th>
            <th>Телефон</th>
            <th>Рабочие часы</th>
            <th>Круглосуточная</th>
        </#if>
        <#if tableContent?? && objectType!="manufacturer">
            <th>Цена</th>
        </#if>
        </tr>
        </thead>
        <tbody>
        <#if tableContent??>
            <#list tableContent as object>
            <tr data-id="${object.id}">
                <#if tableType=="drug">
                    <td>${object.name}</td>
                    <td>${object.releaseForm}</td>
                    <td>${object.manufacturer}</td>
                    <td>${object.activeIngredient}</td>
                    <td>${object.pharmachologicEffect.name}</td>
                    <td>${object.therapeuticEffect.name}</td>
                <#elseif tableType=="drugstore">
                    <td>${object.name}</td>
                    <td>${object.district} р-н, ул. ${object.street}, ${object.building}</td>
                    <td>${object.phone?c}</td>
                    <td>${object.workingHours}</td>
                    <td>
                        <div class="custom-control custom-checkbox">
                            <input class="custom-control-input" type="checkbox" id="roundTheClock"
                                   <#if (object.isRoundTheClock > 0)>checked</#if> disabled>
                            <label class="custom-control-label" for="roundTheClock"></label>
                        </div>
                    </td>
                </#if>
                <#if objectType!="manufacturer">
                    <td>999.99</td>
                </#if>
            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>

<#if !tableContent??>
<nav id="paginationNav"></nav>
</#if>