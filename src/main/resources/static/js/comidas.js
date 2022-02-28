//CARGAR DOM
document.addEventListener("DOMContentLoaded", function() {
	$("#Anadir").click(crearComida);
});

var csrfToken = $("[name='_csrf']").attr("value");
console.log(csrfToken);

function crearComida() {

	if ($('#inputNombre').val() != "" && $('#selectPais').val() != ""
		&& $('#inputEnlace').val() != "" && $('#inputDescripcion').val() != "") {
			
		
		fetch('/crearComida', {
			headers: {
				 credentials: 'same-origin',
				 'Content-type': 'application/json',
				 'X-CSRF-TOKEN': csrfToken
			},
			method: 'POST',
			body: JSON.stringify({
				nombre: $('#inputNombre').val(), pais: $('#selectPais').val(),
				hiperenlace: $('#inputEnlace').val(), descripcion: $('#inputDescripcion').val()
			})
		})
			.then(function(response) {
				if (response.ok) {
					return response.json()
				} else {
					throw "Error";
				}

			}).then(res => {
				alert(res);
			});
	};
}