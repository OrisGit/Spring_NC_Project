<#ftl output_format="HTML" strip_whitespace=true>
<html>
<head>
    <meta charset="utf-8">
    <title>Object</title>
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

    <div class="media container my-3 mx-auto p-2 bg-light border rounded">
        <img class="align-self-start mr-3" src="/img/${objectType}.png" alt="Generic placeholder image">
        <div class="media-body">
        <#if object??>
            <#if objectType=="drugstore">
                <h3>Аптека ${object.name}</h3>
                <strong>Адрес:</strong> ${object.district} р-н, ул. ${object.street}, ${object.building}<br>
                <strong>Телефон:</strong> ${object.phone?c}<br>
                <strong>Часы работы:</strong> ${object.workingHours}<br>
            <#elseif objectType=="drug">
                <h3>Препарат ${object.name}</h3>
                <strong>Активный ингредиент:</strong> ${object.activeIngredient}<br>
                <strong>Форма выпуска:</strong> ${object.releaseForm}<br>
                <strong>Производитель:</strong> ${object.manufacturer}<br>
                <strong>Фармакологический эффект:</strong> ${object.pharmachologicEffect.name}<br>
                <strong>Терапевтический эффект:</strong> ${object.therapeuticEffect.name}<br>
                <strong>Описание:</strong> ${object.description!''}<br>
            <#elseif objectType=="manufacturer">
                <h3>Производитель ${object.name}</h3>
                <strong>Описание:</strong> ${object.description!''}<br>
            </#if>
        <#else>
            <h3>Объект не найден</h3>
        </#if>
        </div>
        <span class="ml-4 float-left">
            <button class="btn btn-info mybtn my-1 my-lg-0" id="editBtn">
                <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-danger mybtn my-1 my-lg-0" id="deleteBtn">
                <i class="fas fa-trash-alt"></i>
            </button>
        </span>
    </div>

    <#if object??>
        <#include "*/table.ftl">
    </#if>
</body>
</html>