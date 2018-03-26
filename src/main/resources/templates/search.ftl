<#ftl output_format="HTML" strip_whitespace=true>
<html>
<head>
    <meta charset="utf-8">
    <title>Поиск</title>
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

    <div class="rounded container bg-light p-1 my-3 mx-auto">
        <form class="form-inline m-1">
            <input type="text" class="form-control m-1" id="name" placeholder="Название" autocomplete="off">
        <#if objectType=="drugstore">
            <input type="text" class="form-control m-1" id="district" placeholder="Район" autocomplete="off">
            <input type="text" class="form-control m-1" id="street" placeholder="Улица" autocomplete="off">
            <input type="text" class="form-control m-1" id="workingHours" placeholder="Часы работы" autocomplete="off">
            <div class="custom-control custom-checkbox m-1">
                <input class="custom-control-input" type="checkbox" id="isRoundTheClock">
                <label class="custom-control-label" for="roundTheClock">Круглосуточная</label>
            </div>
        <#elseif objectType=="drug">
            <input type="text" class="form-control m-1" id="releaseForm" placeholder="Форма выпуска" autocomplete="off">
            <input type="text" class="form-control m-1" id="activeIngredient" placeholder="Активный ингредиент" autocomplete="off">
            <input type="text" class="form-control m-1" id="indicationsForUse" placeholder="Показания к применению" autocomplete="off">
            <select class="form-control custom-select m-1 col-2" id="producer">
                <option selected>Производитель</option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select>
            <select class="form-control custom-select m-1 col-2" id="pharmTerGroup">
                <option selected>Фармакотерапевтическая группа</option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
            </select>
        </#if>
            <button type="button" class="btn btn-primary m-1 ml-auto" id="searchBtn">
                <i class="fas fa-search"></i> Искать
            </button>
        </form>
    </div>

    <#include "*/table.ftl">
</body>
</html>