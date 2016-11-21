/**
 * 
 */


function ajustarMapa(){
	$('#map').css('height', $(window).height()/2).resize();
}

function initMap() {
	
    var myLatlng;
    if($('#latitud').val() && $('#longitud').val()){
    	myLatlng = {lat: parseFloat($('#latitud').val()), lng: parseFloat($('#longitud').val())};
    }
    else{
    	myLatlng = {lat: -34.6611354, lng: -58.4745766};
    }

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: myLatlng
    });
    
    google.maps.event.addListener(map, 'click', function(event) {
	  $('#latitud').val(event.latLng.lat());
	  $('#longitud').val(event.latLng.lng());
    });
       
    ajustarMapa();
}

$(window).resize(function(){ajustarMapa()});