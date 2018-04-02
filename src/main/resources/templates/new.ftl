<#ftl output_format="HTML" strip_whitespace=true>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css">

    <script defer src="/js/fontawesome-all.min.js"></script>

    <script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="/js/pagination.min.js"></script>
    <script type="text/javascript" src="/js/main.js"></script>
</head>
<body>
    <#include "*/navbar.ftl">

    <div class="w-75 my-3 mx-auto">
        <h3 class="ml-1">
        <#if objectType=="drugstore">
            Аптека
        <#elseif objectType=="drug">
            Препарат
        <#elseif objectType=="price">
            Цена
        <#elseif objectType=="manufacturer">
            Производитель
        <#elseif objectType=="pharmTerGroup">
            Фармакотерапевтическая группа
        </#if></h3>
        <form id="editForm">
            <#if objectType=="price">
                <select class="form-control custom-select col-md m-1" size="5" id="drug">
                    <option disabled>Препарат</option>
                    <#list drugList as drug>
                        <option value="${drug.id}" <#if (object.drug.id == drug.id)!false>selected</#if>>
                            Препарат ${drug.name} (${drug.releaseForm}, ${drug.activeIngredient}, ${drug.producer.name})
                        </option>
                    </#list>
                </select>
                <select class="form-control custom-select col-md m-1" size="5" id="drugstore">
                    <option disabled>Аптека</option>
                    <#list drugstoreList as drugstore>
                        <option value="${drugstore.id}" <#if (object.drugstore.id == drugstore.id)!false>selected</#if>>
                            Аптека ${drugstore.name} (${drugstore.district} р-н, ул. ${drugstore.street}, ${drugstore.building})
                        </option>
                    </#list>
                </select>
                <input type="text" class="form-control m-1" id="cost" placeholder="Цена"
                       value="${(object.cost)!''}" autocomplete="off" required>
            <#else>
                    <input type="text" class="form-control m-1" id="name" placeholder="Название"
                       value="${(object.name)!''}" autocomplete="off" required>
                <#if objectType=="drugstore">
                    <input type="text" class="form-control m-1" id="district" placeholder="Район"
                           value="${(object.district)!''}" autocomplete="off" required>
                    <input type="text" class="form-control m-1" id="street" placeholder="Улица"
                           value="${(object.street)!''}" autocomplete="off" required>
                    <input type="text" class="form-control m-1" id="building" placeholder="Дом"
                           value="${(object.building)!''}" autocomplete="off" required>
                    <input type="text" class="form-control m-1" id="phone" placeholder="Телефон"
                           value="${(object.phone)!''}" autocomplete="off" required>
                    <input type="text" class="form-control m-1" id="workingHours" placeholder="Часы работы"
                           value="${(object.workingHours)!''}" autocomplete="off" required>
                    <div class="custom-control custom-checkbox m-1">
                        <input class="custom-control-input" type="checkbox" id="isRoundTheClock"
                               <#if (object??) && (object.isRoundTheClock > 0)>checked</#if>>
                        <label class="custom-control-label" for="isRoundTheClock">Круглосуточная</label>
                    </div>
                <#elseif objectType=="drug">
                    <input type="text" class="form-control m-1" id="releaseForm" placeholder="Форма выпуска"
                           value="${(object.releaseForm)!''}" autocomplete="off" required>
                    <input type="text" class="form-control m-1" id="activeIngredient" placeholder="Активный ингредиент"
                           value="${(object.activeIngredient)!''}" autocomplete="off" required>
                    <input type="text" class="form-control m-1" id="indicationsForUse" placeholder="Показания к применению"
                           value="${(object.indicationsForUse)!''}" autocomplete="off" required>
                    <select class="form-control custom-select col-md m-1" size="1" id="producer">
                        <option selected disabled>Производитель</option>
                        <#list manufacturerList as manufacturer>
                            <option value="${manufacturer.id}">${manufacturer.name}</option>
                        </#list>
                    </select>
                    <select class="form-control custom-select col-md m-1" size="1" id="pharmTerGroup">
                        <option selected disabled>Фармакотерапевтическая группа</option>
                        <#list pharmTerGroupList as pharmTerGroup>
                            <option value="${pharmTerGroup.id}">${pharmTerGroup.name}</option>
                        </#list>
                    </select>
                    <textarea class="form-control m-1" id="description" placeholder="Описание"
                              autocomplete="off">${(object.description)!''}</textarea>
                <#elseif objectType=="manufacturer" || objectType=="pharmTerGroup">
                    <textarea class="form-control m-1" id="description" placeholder="Описание"
                              autocomplete="off">${(object.description)!''}</textarea>
                </#if>
            </#if>
            <button type="button" class="btn btn-success m-1" id="submitBtn">
                <i class="fas fa-edit"></i> Сохранить
            </button>
        </form>
    </div>
</body>
</html>