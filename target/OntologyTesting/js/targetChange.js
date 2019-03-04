$(document).ready(function(){
	
	
	
$( "#changeTarget" ).click(function() {
	
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
				action: "change",
				graph: graphName,
				point: endpoint,
			},			
			success: function( data ) {
				
				$('div.classCount').text("Number of Classes: " + data.classCount);
				$('div.dataPropertyCount').text("Number of Data Properties: " + data.dataPropertyCount);
				$('div.objectPropertyCount').text("Number of Object Properties: " + data.objectPropertyCount);
				$('div.classInstance').text("Number of Individuals: " + data.classInstance);
				$('div#resultDiv').text('');
				
			}
		});
	});
	
});