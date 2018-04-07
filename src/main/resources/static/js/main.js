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

function del(type, id) {
    if (confirm('Вы уверены?')) {
        sendXHR(`/api/${type}/${id}`, 'DELETE', null, console.log);
    }
}

function importIn() {
    let file = $('#importFile')[0].files[0];
    let format = file.name.split('.').pop();
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

function sendXHR(url, method, payload, callback) {
    $.ajax({
        url: url,
        method: method,
        data: payload,
        contentType: 'application/json',
        //beforeSend: alert('Sending...'),
        //complete: alert('Done'),
        error: function (xhr) {
            if (xhr.status === 500) alert('Server error');
            let response = xhr.responseJSON;
            alert(`${response.status}: ${response.error}\n${response.message}`);
        },
        success: function (xhr) {
            alert('Success');
            callback(xhr);
        }
    });
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

function showPagination(url, pageSize) {
    $('#paginationNav').pagination({
        dataSource: url,
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
            let template = (table.data().type === 'drug') ? templateDrugs : templateDrugstores;

            table.find('tbody').empty();
            $.each(data, function (i, object) {
                template(table, object);
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
    let paginationUrl = `/api/${type.slice(0, -1)}`;
    let pageSize = 5;

    $('.table').on('click', 'tr', function (event) {
        if (!$(event.target).is($('th, a, .dropdown-toggle'))) {
            info($(this));
        }
    });

    $('.table a.delete').click(function () {
        let drugId;
        let drugstoreId;

        if (type === 'drug') {
            drugId = id;
            drugstoreId = $(this).parents('tr').data().id;
        } else {
            drugId = $(this).parents('tr').data().id;
            drugstoreId = id;
        }
        del('price', drugId + '&' + drugstoreId);
    });

    $('#importBtn').click(importIn);

    $('#exportBtn').click(exportFrom);

    $('#editBtn').click(() => {
        window.open(window.location.href + '/edit');
    });

    $('#deleteBtn').click(() => {
        del(type, id);
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
        let object = formObject();
        if (object.producer) object.producer = object.producer.id;
        if (object.pharmTerGroup) object.pharmTerGroup = object.pharmTerGroup.id;
        let params = $.param(object);

        paginationUrl = `/api/${type.slice(0, -1)}/search?${params}`;

        showPagination(paginationUrl, pageSize);
    });

    $('#importFile').change(function () {
        let file = $(this)[0].files[0].name;
        $(this).next('label').text(file);
    });

    $('#clearBtn').click(function() {
        $('form').trigger('reset');
    });

    $('#entriesPerPage').find('a').click(function () {
        pageSize = parseInt($(this)[0].innerHTML);
        showPagination(paginationUrl, pageSize);
    });

    if ($('#paginationNav').length) {
        showPagination(paginationUrl, pageSize);
    }
});