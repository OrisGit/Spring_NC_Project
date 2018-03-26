function findLocation() {
    let url = window.location.href;
    let start = url.lastIndexOf('/');
    let end = url.lastIndexOf('?');
    let currentPage = ~end ? url.slice(start, end) : url.slice(start);

    $('.navbar-nav a[href="' + currentPage + '"]').addClass('active');

    return currentPage;
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
    return district + ' р-н, ул.' + street + ', ' + building;
}

function formatPhone(phone) {
    return phone;
}

function info() {
    let type = $(this).parents('table').data().type;
    window.open('/' + type + '/' + $(this).data().id);
}

function edit() {
    window.open(window.location.href + '/edit');
}

function del() {
    if (confirm('Вы уверены?')) {
        let id = findLocation().slice(1);
        alert(id);
        //sendXHR('/api/' + id, 'DELETE', null, console.log);
    }
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

    form.find('input, select, textarea').each(function (i, tag) {
        let value = $(tag).val();
        if (tag.type === 'checkbox') {
            value = +tag.checked;
        }
        object[tag.id] = value;
    });
    return object;
}

function sendXHR(url, method, payload, callback) {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                console.log('Server response: ' + xhr.response);
                callback(xhr.response);
            } else {
                alert(xhr.status + ': ' + xhr.statusText);
            }
        }
    };

    xhr.open(method, url);

    xhr.setRequestHeader('Content-type', 'application/json');
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.responseType = 'json';

    xhr.send(payload);
}

$(document).ready(() => {

    findLocation();

    $('.table').on('click', 'tr', info);

    $('#importBtn').click(importIn);

    $('#exportBtn').click(exportFrom);

    $('#editBtn').click(edit);

    $('#deleteBtn').click(del);

    $('#submitBtn').click(() => {
        console.log(formObject());
    });

    $('#searchBtn').click(() => {
        alert('');
        searchDrug();
    });

    $('#importFile').change(function () {
        let file = $(this)[0].files[0].name;
        $(this).next('label').text(file);
    });

    let pageSize = 1;

    $('#paginationNav').pagination({
        dataSource: 'api/drugstore',
        locator: 'items',
        pageSize: pageSize,
        pageRange: 1,
        totalNumberLocator: function (response) {
            return Math.floor(response.length / pageSize);
        },
        callback: function (data, pagination) {
            let table = $('.table');

            table.find('tbody').empty();
            $.each(data, function(i, drugstore){
                $('<tr>').data('id', drugstore.id).appendTo(table)
                    .append($('<td>').text(drugstore.name))
                    .append($('<td>').text(formatAddress(drugstore.district, drugstore.street, drugstore.building)))
                    .append($('<td>').text(formatPhone(drugstore.phone)))
                    .append($('<td>').text(drugstore.workingHours))
                    .append($('<td>').append(createCustomCheckbox(!!+drugstore.isRoundTheClock)));
            });
        },
        alias: {
            pageNumber: 'page',
            pageSize: 'limit'
        },
        classPrefix: 'page-item pagination',
        ulClassName: 'visible pagination justify-content-center'
    });
});

function searchDrug() {
    let table = $('#drugTable');
    let data = JSON.stringify({
        //parameters
    });
    $.getJSON('/api/drug', function (json) {
        table.find('tbody').empty();
        $(json).each(function (i, drug) {
            $('<tr>').data('id', drug.id).appendTo(table)
                .append($('<td>').text(drug.name))
                .append($('<td>').text(drug.releaseForm))
                .append($('<td>').text(drug.manufacturer))
                .append($('<td>').text(drug.activeIngredient))
                .append($('<td>').text(drug.pharmachologicEffect.name))
                .append($('<td>').text(drug.therapeuticEffect.name));
        });
    });
}

function createButton(type) {
    let btn = $('<button class="btn mybtn my-1 my-lg-0 mr-1">');
    let i = $('<i>');
    switch (type) {
        case 'info':
            $(btn).addClass('btn-info');
            $(i).addClass('fas fa-info-circle');
            btn.click(info);
            break;
        case 'edit':
            $(btn).addClass('btn-secondary');
            $(i).addClass('fas fa-edit');
            btn.click(edit);
            break;
        case 'delete':
            $(btn).addClass('btn-danger');
            $(i).addClass('fas fa-trash-alt');
            btn.click(del);
            break;
        default:
            break;
    }
    btn.append(i);
    return btn;
}