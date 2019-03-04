$(document).ready(function(){


	//handle show class button
	$( "#showObjectProperties" ).click(function() {
		
		var graphName ="";
		var endpoint = "";
		graphName = $('input#graphName').val();
		endpoint = $('input#endpoint').val();


		if (graphName == "")
			graphName = $('input#graphName').attr("placeholder");
		
		if (endpoint == "")
			endpoint = $('input#endpoint').attr("placeholder");
		
		$.ajax({
			type: "POST",
			url: "OntologyDetails",
			data: {
				action: "showObjectProperties",
				graph: graphName,
				point: endpoint,
			},			
			success: function( data ) {
				
				var objectTable = createObjectHTML(data);
				$('#resultDiv').html('');
				$('#resultDiv').html(objectTable);
				
			}
		});
	});
	


});

function createObjectHTML(data){
	var objects = data.objectProperties;
	
	//console.log(classes);
	var html;
	html = '<h3 class="objectType">List of Object Properties</h3><table border=1 class="objectType"><tr><th>Name</th><th>Domain</th><th>Range</th><th>Count</th></tr>';	
	
	$.each(objects, function(i, o) {
		
		var row;
		row = '<tr><td>' + o.Name + '</td><td>' + o.Domain + '</td><td>' + o.Range +'</td><td>' + o.count + '</td></tr>';
		
		html = html + row;				
	});
		
	html = html + '</table>';
	return html;
	

}

