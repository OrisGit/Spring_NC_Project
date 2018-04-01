function findLocation() {
    let url = window.location.pathname;
    let type = url.split('/')[1];
    let id = url.split('/')[2];

    $(`.navbar-nav a[href="/${type}"]`).addClass('active');

    return {
        type: type,
        id: id
    };
}

function createCustomCheckbox(checked) {
    let div = $('<div class="custom-control custom-checkbox">')
        .append($('<input class="custom-control-input">')
            .attr('type', 'checkbox')
            .attr('disabled', true)
            .attr('checked', checked))
        .append($('<label class="custom-control-label">'));
    return div;
}

function formatAddress(district, street, building) {
    return `${district} р-н, ул. ${street}, ${building}`;
}

function formatPhone(phone) {
    return phone;
}

function info(row) {
    let type = row.parents('table').data().type;
    window.open(`/${type}/${row.data().id}`);
}

function importIn() {
    let format = $('input[name="format"]:checked').val();
    let file = $('#importFile')[0].files[0];
    sendXHR('/api/import?format=' + format, 'POST', file, console.log);
}

function exportFrom() {
    let format = $('input[name="format"]:checked').val();
    window.open('/api/export?format=' + format, '_self');
}

function formObject() {
    let object = {};
    let form = $('form');

    form.find('input, textarea').each(function (i, tag) {
        let value = $(tag).val();
        if (tag.type === 'checkbox') {
            value = +tag.checked;
        }
        object[tag.id] = value;
    });
    form.find('select').each(function (i, tag) {
        let value = $(tag).val();
        if (value) {
            let key = tag.id;
            let type = key.replace('producer', 'manufacturer');
            $.ajax({
                url: `/api/${type}/${value}`,
                dataType: 'json',
                async: false,
                success: (response) => {
                    object[key] = response
                }
            });
        }
    });

    return object;
}

function sendXHR(url, method, payload, callback) {//todo jquery replace
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        let response = xhr.response;

        if (this.readyState === 4) {
            if (this.status === 200) {
                callback(response);
            } else {
                alert(`${response.status}: ${response.error}\n${response.message}`);
            }
        }
    };

    xhr.open(method, url);

    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.responseType = 'json';

    xhr.send(payload);
}

function templateDrugs(table, drug) {
    $('<tr>').data('id', drug.id).appendTo(table)
        .append($('<td>').text(drug.name))
        .append($('<td>').text(drug.releaseForm))
        .append($('<td>').text(drug.activeIngredient))
        .append($('<td>').text(drug.indicationsForUse))
        .append($('<td>').append($('<a href="manufacturer/' + drug.producer.id + '"  target="_blank">')
            .text(drug.producer.name)))
        .append($('<td>').append($('<a href="pharmTerGroup/' + drug.pharmTerGroup.id + '"  target="_blank">')
            .text(drug.pharmTerGroup.name)));
}

function templateDrugstores(table, drugstore) {
    $('<tr>').data('id', drugstore.id).appendTo(table)
        .append($('<td>').text(drugstore.name))
        .append($('<td>').text(formatAddress(drugstore.district, drugstore.street, drugstore.building)))
        .append($('<td>').text(formatPhone(drugstore.phone)))
        .append($('<td>').text(drugstore.workingHours))
        .append($('<td>').append(createCustomCheckbox(!!+drugstore.isRoundTheClock)));
}

function showPagination(type, pageSize) {
    $('#paginationNav').pagination({
        dataSource: `api/${type}`,
        locator: 'content',
        pageSize: pageSize,
        totalNumberLocator: (response) => {
            let first = response.number * response.size + 1;
            let last = response.number * response.size + response.numberOfElements;
            let total = response.totalElements;

            $('#paginationInfo').text(`Показаны записи ${first}-${last} из ${total}`);

            return total;
        },
        callback: function (data, pagination) {
            let table = $('.table');

            table.find('tbody').empty();
            $.each(data, function(i, object){
                (type === 'drug') ? templateDrugs(table, object) : templateDrugstores(table, object);
            });
        },
        ajax: {
            cache: true
        },
        alias: {
            pageNumber: 'page',
            pageSize: 'size'
        },
        classPrefix: 'page-item pagination',
        ulClassName: 'pagination justify-content-center'
    });
}

$(document).ready(() => {
    let location = findLocation();
    let type = location.type;
    let id = location.id;

    $('.table').on('click', 'tr', function(event) {
        if(!$(event.target).is($('th')) && !$(event.target)[0].hasAttribute('href')) {
            info($(this));
        }
    });

    $('#importBtn').click(importIn);

    $('#exportBtn').click(exportFrom);

    $('#editBtn').click(() => {
        window.open(window.location.href + '/edit');
    });

    $('#deleteBtn').click(() => {
        if (confirm('Вы уверены?')) {
            sendXHR(`/api/${type}/${id}`, 'DELETE', null, console.log);
        }
        window.location.reload();
    });

    $('#submitBtn').click(() => {
        let payload = JSON.stringify(formObject());
        let method;
        let url;

        if (id && ~id.search(/\w{8}-\w{4}-\w{4}-\w{4}-\w{12}/)) {
            url = `/api/${type}/${id}`;
            method = 'PUT';
        } else {
            url = `/api/${type}`;
            method = 'POST';
        }

        sendXHR(url, method, payload, function () {
            alert('OK');
        })
    });

    $('#searchBtn').click(() => {
        let params = {
            //all parameters
        };
        Object.assign(params, formObject());
        //if (params.isRoundTheClock === 0) params.isRoundTheClock = '';
        console.log(params);
        alert(type.slice(0, -1))
    });

    $('#importFile').change(function () {
        let file = $(this)[0].files[0].name;
        $(this).next('label').text(file);
    });

    $('#entriesPerPage').find('a').click(function () {
        let pageSize = $(this)[0].innerHTML;
        showPagination(type.slice(0, -1), pageSize)
    });

    if ($('#paginationNav').length) {
        showPagination(type.slice(0, -1), 5)
    }
});