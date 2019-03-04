$(document).ready(function(){


	//handle show class button
	$( "#showDataProperties" ).click(function() {
		
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
				action: "showDataProperties",
				graph: graphName,
				point: endpoint,
			},			
			success: function( data ) {
				
				var dataTable = createDataHTML(data);
				$('#resultDiv').html('');
				$('#resultDiv').html(dataTable);
				
			}
		});
	});
	


});

function createDataHTML(data){
	
	var datas = data.dataProperties;

	var html;
	html = '<h3 class="dataType">List of Data Properties</h3><table border=1 class="dataType"><tr><th>Name</th><th>Domain</th><th>Range</th></tr>';	
	
	$.each(datas, function(i, d) {
		
		var row;
		row = '<tr><td>' + d.Name + '</td><td>' + d.Domain + '</td><td>' + d.Range +'</td></tr>';
		
		html = html + row;				
	});
		
	html = html + '</table>';
	return html;
	

}

