<div class="container mx-auto">
    <table class="table table-sm table-responsive-md table-hover border-bottom" id="${tableType}Table" data-type="${tableType}">
        <thead>
        <tr>
        <#if tableType=="drug">
            <th>Название</th>
            <th>Форма выпуска</th>
            <th>Активный ингредиент</th>
            <th>Показания к применению</th>
            <th>Производитель</th>
            <th>Фармаколотерапевтическая группа</th>
        <#elseif tableType=="drugstore">
            <th>Название</th>
            <th>Адрес</th>
            <th>Телефон</th>
            <th>Рабочие часы</th>
            <th>Круглосуточная</th>
        </#if>
        <#if tableContent?? && (objectType=="drug" || objectType=="drugstore")>
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
                    <td>${object.activeIngredient}</td>
                    <td>${object.indicationsForUse}</td>
                    <td><a href="/manufacturer/${object.producer.id}" target="_blank">${object.producer.name}</a></td>
                    <td><a href="/pharmTerGroup/${object.pharmTerGroup.id}" target="_blank">${object.pharmTerGroup.name}</a></td>
                <#elseif tableType=="drugstore">
                    <td>${object.name}</td>
                    <td>${object.district} р-н, ул. ${object.street}, ${object.building}</td>
                    <td>${object.phone}</td>
                    <td>${object.workingHours}</td>
                    <td>
                        <div class="custom-control custom-checkbox">
                            <input class="custom-control-input" type="checkbox" id="roundTheClock"
                                   <#if (object.isRoundTheClock > 0)>checked</#if> disabled>
                            <label class="custom-control-label" for="roundTheClock"></label>
                        </div>
                    </td>
                </#if>
                <#if objectType=="drug" || objectType=="drugstore">
                    <td>${prices[object?index]}</td>
                </#if>
            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>

<#if !tableContent??>
<nav id="paginationNav" class="container d-flex align-items-baseline">
    <p class="mr-auto order-0 col-5 text-left text-secondary" id="entriesPerPage">
        Записей на странице:
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
    </p>
    <p class="ml-auto order-1 col-5 text-right text-secondary" id="paginationInfo"></p>
</nav>
</#if>