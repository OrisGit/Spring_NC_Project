<html>
<head>
    <meta charset="utf-8">
    <title>Home</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">

    <script defer src="js/fontawesome-all.min.js"></script>

    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>
    <#include "*/navbar.ftl">

    <div class="container jumbotron bg-light border my-5 p-4 pb-5">
        <h3>Импорт</h3>
        <div class="input-group my-2">
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="importFile">
                <label class="custom-file-label" for="importFile">Выберите файл...</label>
            </div>
            <div class="input-group-append">
                <button class="btn btn-outline-primary ml-auto" type="button" id="importBtn">
                    <i class="fas fa-upload"></i> Импорт
                </button>
            </div>
        </div>
        <h3>Экспорт</h3>
        <div class="form-check form-check-inline input-group align-content-center my-2">
            <div class="custom-control custom-radio m-1">
                <input type="radio" id="XMLformat" name="format" class="custom-control-input" value="XML" checked>
                <label class="custom-control-label" for="XMLformat">XML</label>
            </div>
            <div class="custom-control custom-radio m-1">
                <input type="radio" id="JSONformat" name="format" class="custom-control-input" value="JSON">
                <label class="custom-control-label" for="JSONformat">JSON</label>
            </div>
            <button class="btn btn-outline-primary ml-auto" type="button" id="exportBtn">
                <i class="fas fa-download"></i> Экспорт
            </button>
        </div>
    </div>
</body>
</html>
