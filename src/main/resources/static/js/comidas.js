//CARGAR DOM
document.addEventListener("DOMContentLoaded", function() {
	mostrarComidas();
	$("#Anadir").click(crearComida);
	//$("#editar").click(editarComida);
	//$('#borrar').click(borrarComida);
});

var csrfToken = $("[name='_csrf']").attr("value");
console.log(csrfToken);



function mostrarComidas() {
	let resultados = document.getElementById("root");
	resultados.replaceChildren();
	fetch('/comidas', { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(comidas => {
			mostrarTodas(comidas);
		});

}

const mostrarTodas = (comidas) => {
	let root = document.getElementById('root');
	let resultado = ``;
	comidas.forEach(comida => {
		resultado += `<div class="card">
							<img src="" alt="imagen"></img>	
								<div class="card-body">
									<h5 class="cart-title">${comida.id_comida}</h5>
									<h5 class="cart-title">${comida.nombre}</h5>
									<h5 class="cart-title">${comida.pais}</h5>
									<p class="card-text">${comida.descripcion}</p>
									<button class="btn btn-danger" id="borrar" type="button">Borrar</button>
									<button class="btn btn-info" id="editar" type="button">Info</button>
								</div>
					  </div>`
	});
	root.innerHTML = resultado;
}

//CREAR COMIDA -------------------------------------------
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
				addComida(res);
			});
	};
}
const addComida = (comidas) => {
	let root = document.getElementById('root');
	let resultado = ``;
	resultado += `<div class="card">
							<img src="" alt="imagen"></img>	
								<div class="card-body">
									<h5 class="cart-title">${comidas.id_comida}</h5>
									<h5 class="cart-title">${comidas.nombre}</h5>
									<h5 class="cart-title">${comida.pais}</h5>
									<p class="card-text"></p>
									<button class="btn btn-danger" id="borrar" type="button">Borrar</button>
									<button class="btn btn-info" id="editar" type="button">Info</button>
								</div>
			      </div>`
	root.innerHTML += resultado;
}
//BORRAR COMIDA -------------------------------------------
$(document).on('click', '#borrar', function() {
	var div = $(this).closest(".card");
	var divId = $(this).closest(".card-body");
	var id = divId[0].childNodes[1].innerText;
	fetch("/comida/borrar/" + id, {
		headers: { "Content-Type": "application/json; charset=utf-8" }
	});
	div.remove();
	console.log(id);
});
//EDITAR COMIDA ----------------------------------------------
$(document).on("click", "#editar", function() {
	$("#modal").modal('show');

	let modal = document.getElementsByClassName("modal-body")[0];
	modal.replaceChildren();

	var divId = $(this).closest(".card-body");
	var id = divId[0].childNodes[1].innerText;
	fetch('/comida/info/' + id, {
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	})
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(comida => {
			//let ModalFooter = document.getElementsByClassName("modal-footer")[0];
			//ModalFooter.replaceChildren();
			let ModalTitle = document.getElementsByClassName("modal-title")[0];
			ModalTitle.textContent = comida.nombre;

			let idTitulo = crearElemento("h3", "", "", "", "ID");
			modal.appendChild(idTitulo);
			let id = crearElemento("h5", "", "idComida", "idComida", comida.id_comida);
			modal.appendChild(id);
		});
	const paises = `
		<div >
		<div class="form-group">
				<label for="selectProducto">Selecciona Pais</label>
				<select class="form-select" id="selectPaisEditar">
					<option>Selecciona un pais</option>
					<option value="AF">Afghanistan</option>
					<option value="AX">Aland Islands</option>
					<option value="AL">Albania</option>
					<option value="DZ">Algeria</option>
					<option value="AS">American Samoa</option>
					<option value="AD">Andorra</option>
					<option value="AO">Angola</option>
					<option value="AI">Anguilla</option>
				</select>
			</div>`
	modal.innerHTML += paises;

	$("#cerrar-modal").on('click', function() {
		$("#modal").modal("hide");
	});
});
$("#btnGuardar").on("click", function() {
	let idForSave = document.getElementById("idComida").textContent;
	fetch('/editar/comida/' + idForSave, {
		headers: {
			credentials: 'same-origin',
			'Content-type': 'application/json',
			'X-CSRF-TOKEN': csrfToken
		},
		method: 'PUT',
		body: JSON.stringify({ pais: $('#selectPaisEditar').val() })
	})
		.then(function(response) {
			if (response.ok) {

				return response.json()
			} else {
				throw "La comida ya existe";
			}

		}).then(comida => {

			editarTabla(comida);

		});
});

function editarTabla(comida) {
	let tbody = document.getElementById("root");
	for (var i = 0, root; root = tbody.childNodes[i]; i++) {
		var arrayElements = root.childNodes[3];
		var arrayHijos = arrayElements.childNodes;
		if (arrayHijos[1].innerText == comida.id_comida) {
			arrayHijos[5].textContent = comida.pais;
			$('#modal').modal('toggle');
			break;
		}

	}
}
/*function editarTabla(oferta) {
	var tbody = document.getElementById("idbody");
	for (var i = 0, row; row = tbody.rows[i]; i++) {
		var arrayTds = row.childNodes;
		if (arrayTds[0].innerText == oferta.idOferta) {
			arrayTds[1].textContent = oferta.nombreOferta;
			arrayTds[2].textContent = oferta.precioOferta;
			editarPrioridad(oferta, row);
			$('#modal').modal('toggle');
			break;
		}
	}
}*/

/*$(document).on('click', '#editar', function() {
	$("#modal").modal('show');

	$("#cerrar-modal").on('click', function() {
		$("#modal").modal("hide");
	});
	var divId = $(this).closest(".card-body");
	var id = divId[0].childNodes[1].innerText;
	let ModalBody = document.getElementsByClassName("modal-body")[0];
	fetch('/comida/info/' + id, { headers: { "Content-Type": "application/json; charset=utf-8" } })
		.then(res => res.json()) // parse response as JSON (can be res.text() for plain response)
		.then(comida => {
			ModalBody.replaceChildren();
			//let ModalFooter = document.getElementsByClassName("modal-footer")[0];
			//ModalFooter.replaceChildren();
			let ModalTitle = document.getElementsByClassName("modal-title")[0];
			ModalTitle.textContent = comida.nombre;

			let idTitulo = crearElemento("h3", "", "", "", "ID", "blue");
			ModalBody.appendChild(idTitulo);
			let id = crearElemento("h5", "", "idComida", "idComida", comida.id_comida, "");
			ModalBody.appendChild(id);

			let nombreTitulo = crearElemento("h3", "", "", "", "NOMBRE", "blue");
			ModalBody.appendChild(nombreTitulo);
			let nombre = crearElemento("h5", "", "nombreComida", "nombreComida", comida.nombre, "");
			ModalBody.appendChild(nombre);

			let descripcionTitulo = crearElemento("h3", "", "", "", "DESCRIPCION", "blue");
			ModalBody.appendChild(descripcionTitulo);
			let descripcion = crearElemento("h5", "", "descripcionComida", "descripcionComida", comida.descripcion, "");
			ModalBody.appendChild(descripcion);

			let paisTitulo = crearElemento("h3", "", "", "", "PAIS", "blue");
			ModalBody.appendChild(paisTitulo);
			let pais = crearElemento("h5", "", "paisComida", "paisComida", comida.pais, "");
			ModalBody.appendChild(pais);

			let hiperEnlaceTitulo = crearElemento("h3", "", "", "", "HIPERENLACE", "blue");
			ModalBody.appendChild(hiperEnlaceTitulo);
			let enlace = crearElemento("h5", "", "enlaceComida", "enlaceComida", comida.hiperenlace, "");
			ModalBody.appendChild(enlace);

		});
			ModalBody.replaceChildren();
		
			let idTitulo = crearElemento("h3", "", "", "", "ID", "blue");
			ModalBody.appendChild(idTitulo);
			let idInput= crearElemento("input", "text", "idComida", "idComida", comida.id_comida, "");
			ModalBody.appendChild(idInput);

			let nombreTitulo = crearElemento("h3", "", "", "", "NOMBRE", "blue");
			ModalBody.appendChild(nombreTitulo);
			let nombreInput = crearElemento("input", "text", "nombreComida", "nombreComida", comida.nombre, "");
			ModalBody.appendChild(nombreInput);

			let descripcionTitulo = crearElemento("h3", "", "", "", "DESCRIPCION", "blue");
			ModalBody.appendChild(descripcionTitulo);
			let descripcionInput = crearElemento("input", "text", "descripcionComida", "descripcionComida", comida.descripcion, "");
			ModalBody.appendChild(descripcionInput);

			let paisTitulo = crearElemento("h3", "", "", "", "PAIS", "blue");
			ModalBody.appendChild(paisTitulo);
			let paisInput = crearElemento("input", "select", "paisComida", "paisComida", comida.pais, "");
			ModalBody.appendChild(paisInput);

			let hiperEnlaceTitulo = crearElemento("h3", "", "", "", "HIPERENLACE", "blue");
			ModalBody.appendChild(hiperEnlaceTitulo);
			let enlaceInput = crearElemento("input", "", "enlaceComida", "enlaceComida", comida.hiperenlace, "");
			ModalBody.appendChild(enlaceInput);
		
		
		let bottonEditar = crearElemento("button", "button", "guardar-cambios", "btn btn-warning", "GUARDAR CAMBIOS", "");
		ModalBody.appendChild(bottonEditar);
	});
});*/

function crearElemento(input, tipo, id, clase, valor) {
	let elemento = document.createElement(input);
	elemento.setAttribute("type", tipo);
	elemento.setAttribute("id", id);
	elemento.setAttribute("class", clase);
	elemento.setAttribute("value", valor);
	elemento.textContent = valor;
	return elemento;
}
/*
function borrarComida(){
	var div = $(this).closest(".card");
	var id = div[0].childNodes[0].innerText;
	fetch("/comida/borrar/" + id, {
		headers: { "Content-Type": "application/json; charset=utf-8" }
	});
	div.remove();
}*/