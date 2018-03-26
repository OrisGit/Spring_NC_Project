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

    <div class="container jumbotron my-5 bg-light border">
        <h2>Импорт/Экспорт</h2>
        <div class="input-group my-3">
            <div class="input-group-prepend">
                <button class="btn btn-outline-secondary" type="button" id="exportBtn">
                    <i class="fas fa-download"></i> Экспорт
                </button>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="importFile">
                <label class="custom-file-label" for="importFile">Выберите файл...</label>
            </div>
            <div class="input-group-append">
                <button class="btn btn-primary" type="button" id="importBtn">
                    <i class="fas fa-upload"></i> Импорт
                </button>
            </div>
        </div>
        <form id="formatTypeForm">
            <div class="custom-control custom-radio m-1">
                <input type="radio" id="XMLformat" name="format" class="custom-control-input" value="XML">
                <label class="custom-control-label" for="XMLformat">XML</label>
            </div>
            <div class="custom-control custom-radio m-1">
                <input type="radio" id="JSONformat" name="format" class="custom-control-input" value="JSON">
                <label class="custom-control-label" for="JSONformat">JSON</label>
            </div>
        </form>
    </div>
</body>
</html>
