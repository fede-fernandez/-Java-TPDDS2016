/**
 * 
 */
function initMap() {
    var myLatlng = {lat: -34.6611354, lng: -58.4745766};

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: myLatlng
    });

    google.maps.event.addListener(map, 'click', function(event) {
	  document.getElementById('latitud').value = event.latLng.lat();
	  document.getElementById('longitud').value = event.latLng.lng();
    });		
}
