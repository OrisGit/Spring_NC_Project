<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Справочник</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse mt-1" id="navbarNavAltMarkup">
        <div class="navbar-nav mr-auto">
            <a class="nav-item nav-link" href="/drugstores">Аптек</a>
            <a class="nav-item nav-link mr-1" href="/drugs">Лекарств</a>
            <a class="nav-item nav-link" href="/">
                <i class="fa fa-home m-1"></i>
            </a>
        </div>
        <div class="dropdown mr-2">
            <button class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Добавить
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a class="dropdown-item" href="/create/drugstore">Аптеку</a>
                <a class="dropdown-item" href="/create/drug">Лекарство</a>
                <a class="dropdown-item" href="/create/price">Цену</a>
                <a class="dropdown-item" href="/create/manufacturer">Производителя</a>
            </div>
        </div>
        <button class="btn btn-outline-light my-2 my-lg-0" type="button">
            <i class="fas fa-sign-in-alt"></i> Войти
        </button>
    </div>
</nav>