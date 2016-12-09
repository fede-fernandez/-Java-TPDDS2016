$(document).ready(function () {
	$("#tablaTerminales").DataTable({
		"language": {
			"sProcessing": "Procesando...",
			"sLengthMenu": "Mostrar _MENU_ terminales",
			"sZeroRecords": "No se encontraron terminales",
			"sEmptyTable": "No se encontraron terminales",
			"sInfo": "Mostrando terminales del _START_ al _END_ de un total de _TOTAL_ terminales",
			"sInfoEmpty": "Mostrando terminales del 0 al 0 de un total de 0 terminales",
			"sInfoFiltered": "(filtrado de un total de _MAX_ terminales)",
			"sInfoPostFix": "",
			"sSearch": "Buscar:",
			"sUrl": "",
			"sInfoThousands": ",",
			"sLoadingRecords": "Cargando...",
			"oPaginate": {
				"sFirst": "Primero",
				"sLast": "Último",
				"sNext": "Siguiente",
				"sPrevious": "Anterior"
			},
			"oAria": {
				"sSortAscending": ": Activar para ordenar la columna de manera ascendente",
				"sSortDescending": ": Activar para ordenar la columna de manera descendente"
			}
		}
	});
});

$("#cmbComuna").on("change", function () {
	$("#tablaTerminales").DataTable().columns(1).search($("#cmbComuna option:selected").val()).draw();
});

$("#modalEliminarTerminal").on("show.bs.modal", function (event) {
	$("#terminalAEliminar").val(event.relatedTarget.dataset.id);
});

$("#modalModificarTerminal").on("show.bs.modal", function (event) {
	var terminalSeleccionada = event.relatedTarget.dataset;
	$("#terminalAModificar").val(terminalSeleccionada.id);
	$("#modalModificarTxtNombre").val(terminalSeleccionada.nombre);
	$("#modalModificarTxtLongitud").val(terminalSeleccionada.longitud);
	$("#modalModificarTxtLatitud").val(terminalSeleccionada.latitud);
	$("#modalModificarCmbComuna").val(terminalSeleccionada.comuna);
})