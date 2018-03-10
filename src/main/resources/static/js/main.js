let objects = [
    {
        id: 12345,
        name: 'Вита',
        address: 'Советский р-н, ул.Ново-Вокзальная, 9',
        phone: '8(800)755-00-03',
        hours: 'Круглосуточно',
        rtc: true
    }, {
        id: 54321,
        name: 'Алия',
        address: 'Самарский р-н, ул.Льва Толстого, 19',
        phone: '201-16-55',
        hours: '8.00-20.00, без вых.',
        rtc: false
    }
];

function findLocation() {
    let start = window.location.href.lastIndexOf('/') + 1;
    let end = window.location.href.lastIndexOf('?');
    let currentPage;
    currentPage = ~end ? window.location.href.slice(start, end) : window.location.href.slice(start);
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

function info() {
    let id = $(this).parent().parent().data().id;
    let params = '?id=' + id;

    window.open('object.html' + params);
}

function edit() {
	let id = $(this).parent().parent().data().id;
    let params = '?id=' + id;
	
    window.open('object_edit.html' + params);
}

function del() {
	let id = $(this).parent().parent().data().id;
    let params = '?id=' + id;
	
    if (confirm('Вы уверены?')) {
        sendXHR('/api/delete' + params, 'DELETE', console.log);
    }
}

function formObject() {
	let object = {};
	let form = $('#editForm');

	form.find('input').each(function(i, input) {
		let value = $(input).val();
		if(input.type === 'checkbox'){
			value = input.checked;
		}
		$(object).attr(input.id, value);
	});
	form.find('select').each(function(i, input) {
		$(object).attr(input.id, $(input).val());
	});
	return object;
}

function searchDrugstore() {
	let table = $('#drugstoreTable');
	let data = JSON.stringify({
		//parameters
	});

	/*$.getJSON('/drugs', data)
	.success(function() {
		table.find('> tbody').empty();
			$(objects).each(function (i, drugstore) {
				$('<tr>').data('id', drugstore.id).appendTo(table)
					.append($('<td>').text(drugstore.name))
					.append($('<td>').text(drugstore.address))
					.append($('<td>').text(drugstore.phone))
					.append($('<td>').text(drugstore.hours))
					.append($('<td>').append(createCustomCheckbox(drugstore.rtc)))
					.append($('<td>').append(createButton('info'), createButton('edit'), createButton('delete')));
			})
		})
	.error(function() {
		alert("Ошибка выполнения");
		})
	.complete(function() {
		alert("Завершение выполнения");
		});*/
	table.find('> tbody').empty();
		$(objects).each(function (i, drugstore) {
			$('<tr>').data('id', drugstore.id).appendTo(table)
				.append($('<td>').text(drugstore.name))
				.append($('<td>').text(drugstore.address))
				.append($('<td>').text(drugstore.phone))
				.append($('<td>').text(drugstore.hours))
				.append($('<td>').append(createCustomCheckbox(drugstore.rtc)))
				.append($('<td>').append(createButton('info'), createButton('edit'), createButton('delete')));
		});
}

function sendXHR(url, method, payload, callback) {
    let xhr = new XMLHttpRequest();
    let json = JSON.stringify(payload);

    xhr.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 200) {
                alert('done');
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

    xhr.send(json);

    alert('loading...');
}

$(document).ready(function () {
    let searchBtn = $('#searchBtn');
    let submitBtn = $('#submitBtn');

    findLocation();
    searchDrugstore();

    searchBtn.click(function () {
        //sendXHR get with parameters
        searchDrugstore();
    });
	
	submitBtn.click(function () {
		let id = new URL(window.location.href).searchParams.get('id');
		let params = '?id=' + id;
		let payload = JSON.stringify(formObject());
		
        sendXHR('/api/edit' + params, 'PUT', payload, console.log);
	});
});