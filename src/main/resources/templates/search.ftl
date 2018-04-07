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
            <div class="custom-control custom-checkbox m-1">
                <input class="custom-control-input" type="checkbox" id="isRoundTheClock">
                <label class="custom-control-label" for="isRoundTheClock">Круглосуточная</label>
            </div>
            <button type="button" class="btn btn-outline-primary m-1 ml-auto" id="clearBtn">Очистить</button>
        <#elseif objectType=="drug">
            <input type="text" class="form-control m-1" id="releaseForm" placeholder="Форма выпуска" autocomplete="off">
            <input type="text" class="form-control m-1" id="activeIngredient" placeholder="Активный ингредиент" autocomplete="off">
            <input type="text" class="form-control m-1" id="indicationsForUse" placeholder="Показания к применению" autocomplete="off">
            <button type="button" class="btn btn-outline-primary m-1 ml-auto" id="clearBtn">Очистить</button>
            <div class="form-group col-3 p-0 m-1">
                <select class="form-control custom-select col-12" id="producer">
                    <option selected disabled>Производитель</option>
                    <#list manufacturerList as manufacturer>
                        <option value="${manufacturer.id}">${manufacturer.name}</option>
                    </#list>
                </select>
                <label for="producer"></label>
            </div>
            <div class="form-group col-3 p-0 m-1 mr-auto">
                <select class="form-control custom-select" id="pharmTerGroup">
                    <option selected disabled>Фармакотерапевтическая группа</option>
                    <#list pharmTerGroupList as pharmTerGroup>
                        <option value="${pharmTerGroup.id}">${pharmTerGroup.name}</option>
                    </#list>
                </select>
                <label for="pharmTerGroup"></label>
            </div>
        </#if>
            <button type="button" class="btn btn-primary m-1" id="searchBtn">
                <i class="fas fa-search"></i> Искать
            </button>
        </form>
    </div>

    <#include "*/table.ftl">
</body>
</html>