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
            <#elseif objectType=="manufacturer">
                Производитель
            </#if></h3>
        <form id="editForm">
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
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
                <select class="form-control custom-select col-md m-1" size="1" id="pharmTerGroup">
                    <option selected disabled>Фармакотерапевтическая группа</option>
                    <option value="1">One</option>
                    <option value="2">Two</option>
                    <option value="3">Three</option>
                </select>
                <textarea class="form-control m-1" id="description" placeholder="Описание"
                       value="${(object.description)!''}" autocomplete="off"></textarea>
            <#elseif objectType=="manufacturer">
                <textarea class="form-control m-1" id="description" placeholder="Описание"
                       value="${(object.description)!''}" autocomplete="off"></textarea>
            </#if>
            <button type="button" class="btn btn-success m-1" id="submitBtn">
                <i class="fas fa-edit"></i> Сохранить
            </button>
        </form>
    </div>
</body>
</html>