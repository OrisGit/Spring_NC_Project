function findLocation() {
    let url = window.location.href;
    let start = url.lastIndexOf('/');
    let end = url.lastIndexOf('?');
    let currentPage = ~end ? url.slice(start, end) : url.slice(start);

    $('.navbar-nav > a[href="' + currentPage + '"]').addClass('active');
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

function params(btn) {
    let type = btn.parents('table').data().type;
    let id = btn.parents('tr').data().id;

    return type + '/' + id;
}

function info() {
    window.open('/api/' + params($(this)));
}

function edit() {
    window.open('object_edit.html');
}

function del() {
    if (confirm('Вы уверены?')) {
        sendXHR('/api/' + params($(this)), 'DELETE', null, console.log);
    }
}

function importIn() {
	let format = $('input[name="format"]:checked').val();
	let file = $('#importFile')[0].files[0];
    sendXHR('/api/import?format=' + format, 'POST', file, console.log);
}

function exportFrom() {
	let format = $('input[name="format"]:checked').val();
    sendXHR('/api/export?format=' + format, 'GET', null, console.log);
}

function formObject() {
    let object = {};
    let form = $('#editForm');

    form.find('input').each(function (i, input) {
        let value = $(input).val();
        if (input.type === 'checkbox') {
            value = +input.checked;
        }
        object[input.id] = value;
    });
    return object;
}

function searchDrug() {
    let table = $('#drugTable');
    let data = JSON.stringify({
        //parameters
    });
    $.getJSON('/api/drug', function (json) {
        table.find('> tbody').empty();
        $(json).each(function (i, drug) {
            $('<tr>').data('id', drug.id).appendTo(table)
                .append($('<td>').text(drug.name))
                .append($('<td>').text(drug.releaseForm))
                .append($('<td>').text(drug.manufacturer))
                .append($('<td>').text(drug.activeIngredient))
                .append($('<td>').text(drug.pharmachologicEffect.name))
                .append($('<td>').text(drug.therapeuticEffect.name))
                .append($('<td>').append(createButton('info'), createButton('edit'), createButton('delete')));
        });
    });
}

function searchDrugstore() {
    let table = $('#drugstoreTable');
    let data = JSON.stringify({
        //parameters
    });
    $.getJSON('/api/drugstore', function (json) {
        table.find('> tbody').empty();
        $(json).each(function (i, drugstore) {
            $('<tr>').data('id', drugstore.id).appendTo(table)
                .append($('<td>').text(drugstore.name))
                .append($('<td>').text(formatAddress(drugstore.district, drugstore.street, drugstore.building)))
                .append($('<td>').text(formatPhone(drugstore.phone)))
                .append($('<td>').text(drugstore.workingHours))
                .append($('<td>').append(createCustomCheckbox(!!+drugstore.isRoundTheClock)))
                .append($('<td>').append(createButton('info'), createButton('edit'), createButton('delete')));
        });
    });
}

function searchPrice() {
    let table = $('#priceTable');
    let data = JSON.stringify({
        //parameters
    });
    $.getJSON('/api/price', function (json) {
        table.find('> tbody').empty();
        $(json).each(function (i, price) {
            $('<tr>').data('id', price.drug.id + '&' + price.drugstore.id).appendTo(table)
                .append($('<td>').text(price.drugstore.name))
                .append($('<td>').text(price.drug.name))
                .append($('<td>').text(price.cost))
                .append($('<td>').append(createButton('info'), createButton('edit'), createButton('delete')));
        });
    });
}

function sendXHR(url, method, payload, callback) {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                callback('Server response: ' + xhr.response);
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

$(document).ready(function () {

    findLocation();

    $('#searchDrugstoreBtn').click(searchDrugstore);
    $('#searchDrugBtn').click(searchDrug);
    $('#searchPriceBtn').click(searchPrice);

    $('#importBtn').click(importIn);
    $('#exportBtn').click(exportFrom);

    $('#submitBtn').click(function () {
        //let id = new URL(window.location.href).searchParams.get('id');

        let payload = formObject();

        sendXHR('/api/drugstore/new', 'POST', payload, console.log);
    });

    $('#importFile').change(function() {
        let file = $(this)[0].files[0].name;
        $(this).next('label').text(file);
    });
});