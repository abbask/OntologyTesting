$(document).ready(function(){


	//handle show class button
	$( "#showClasses" ).click(function() {
		
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
				action: "showClasses",
				graph: graphName,
				point: endpoint,
			},			
			success: function( data ) {
				
				var classTable = createHTML(data);
				$('#resultDiv').html('');
				$('#resultDiv').html(classTable);
				
			}
		});
	});
	


});

function createHTML(data){
	var classes = data.classes;
	
	//console.log(classes);
	var html;
	html = '<h3 class="classType">List of Classes</h3><table border=1 class="classType"><tr><th>Name</th><th>Label</th><th>SuperClass</th><th>count</th></tr>';	
	
	$.each(classes, function(i, c) {
		var row;
		row = '<tr><td>' + c.className + '</td><td>' + c.classLabel + '</td><td>' + c.superClass +'</td><td>' + c.count +'</td></tr>';
		
		html = html + row;				
	});
		
	html = html + '</table>';
	return html;
	

}

