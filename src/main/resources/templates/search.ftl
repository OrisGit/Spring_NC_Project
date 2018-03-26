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
        <#if objectType=="drugstore">
            <input type="text" class="form-control m-1" id="formInputDrugstoreName" placeholder="Название" autocomplete="off">
            <input type="text" class="form-control m-1" id="formInputDistrict" placeholder="Район" autocomplete="off">
            <input type="text" class="form-control m-1" id="formInputStreet" placeholder="Улица" autocomplete="off">
            <input type="text" class="form-control m-1" id="formInputHours" placeholder="Часы работы" autocomplete="off">
            <div class="custom-control custom-checkbox m-1">
                <input class="custom-control-input" type="checkbox" id="roundTheClock">
                <label class="custom-control-label" for="roundTheClock">Круглосуточная</label>
            </div>
        <#elseif objectType=="drug">
            <input type="text" class="form-control m-1 " id="formInputDrugName" placeholder="Название" autocomplete="off">
            <input type="text" class="form-control m-1" id="formInputReleaseForm" placeholder="Форма выпуска" autocomplete="off">
            <input type="text" class="form-control m-1" id="formInputManufacturer" placeholder="Производитель" autocomplete="off">
            <input type="text" class="form-control m-1" id="formInputActiveIngredient" placeholder="Активный ингредиент" autocomplete="off">
            <select class="form-control custom-select m-1 col-2" id="formInputPEffect">
                <option selected>Производитель</option>
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