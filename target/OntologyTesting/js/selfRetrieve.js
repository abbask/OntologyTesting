$(window).on("load",function(){
	
	$('#retrieveTests').click(function(){	
		
		endpoint = $('#endpoint').val();
		graphName = $('#graphName').val();
		
		
    	
    	if (endpoint != "" && graphName !=""){
    		
    		$.get("RetrieveSelfTests",{
    			endpoint : endpoint,
    			graphName : graphName
		    },
	
		    function(data, status){
		    	
		    	textTable = "<div class='row'><table class='table'>";
		    	textTable += "<thead><tr><th scope='col'>name</th><th scope='col'>Edit</th><th scope='col'>Run</th></tr></thead><tbody>";
//		    	
//		    	list = data.list;
//		    	system_test_history_id = data.system_test_history_id;
//		    	
//		    	$.each(list, function( index, value ) {
//		    		
//		    		textTable += "<tr><td><span class='testToRun' unitTestID='" + value.ID + "'>" + value.ID + "</span></td><td>" + value.name + "</td><td><span unitTestID=" + value.ID + " class='statusID'><div class='loader'></div></span></td></tr>";
//	    		  
//	    		}); // $.each(obj, function( index, value ) 
//		    	
//		    	if (list.length == 0)
//		    		textTable='<tr><td colspan="3"><small class="form-text text-muted">No unit test found. Please add unit test first.</small></td></tr>';
		    	textTable += "</tbody></table></div>";
		    	textTable += "<div class='row'><a href='UnitSelfTestNew' class='btn btn-primary btn-md active' role='button' aria-pressed='true'>Add Unit Test</a></div>";
		    	$("#divTable").empty();
		    	$("#divTable").html(textTable);
		    			    			    	
		    	
		    }); // function(data, status){
	    
		} // if (system_test_id != 0){
	});
	
});