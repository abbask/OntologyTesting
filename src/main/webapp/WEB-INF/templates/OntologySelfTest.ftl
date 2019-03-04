<!DOCTYPE html>
<html lang="en">
<head>
  	<title>Ontology Self Testing</title>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	
  	<script src="js/selfRetrieve.js"></script>
  	
	<script type='text/javascript'>
	$(window).on("load",function(){
		selectedSystemTest = 0;
		
		$('#systemTest').change(function(){
			selectedSystemTest = $(this).val();
		});
	
		$('#runTest').click(function(){	
			event.preventDefault();
			
		});
		
		
	});
	</script>
	
	<style>
	.loader {
	  border: 16px solid #f3f3f3;
	  border-radius: 50%;
	  border-top: 16px solid #3498db;
	  width: 10px;
	  height: 10px;
	  -webkit-animation: spin 2s linear infinite; /* Safari */
	  animation: spin 2s linear infinite;
	}
	
	/* Safari */
	@-webkit-keyframes spin {
	  0% { -webkit-transform: rotate(0deg); }
	  100% { -webkit-transform: rotate(360deg); }
	}
	
	@keyframes spin {
	  0% { transform: rotate(0deg); }
	  100% { transform: rotate(360deg); }
	}
	</style>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/OntologyTesting/OntologySelfTesting">Ontology Self Testing</a>
    </div>
    <ul class="nav navbar-nav">
      <!--<li ><a href="/DiscoverOntology/SystemTestList">System Testing</a></li>
      <li><a href="/OntologyTesting/UnitTestList">Unit Testing</a></li>
      <li><a href="/OntologyTesting/TestHistory">History</a></li>-->
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>Retriving Tests:</h3>
	<p>Please specify endpoint and graph names to search for tests.</p>
	
	<form>
	  	<div class="form-row align-items-center">
	    	<div class="col-sm-4 my-1">
	      		<label class="sr-only" for="endpoint">End Point</label>      			
		    	<input type="text" class="form-control mb-2" id="endpoint" name="endpoint" placeholder="Enter Endpoint" required>
	    	</div>
	    	<div class="col-sm-4 my-1">		    	
		    	<label class="sr-only" for="graphName">Graph Name</label>
		    	<input type="text" class="form-control mb-3" id="graphName" name="graphName"  placeholder="Enter Graph Name" required>		    	
		  	</div>
		  	<div class="col-auto">
		  		<button id="retrieveTests" type="button" class="btn btn-primary">Retrive</button>
		  	</div>
		</div>
	</form>
	
	<div id="divTable" class="row">
	</div>	
	<!--<div class="row">
	    <div class="col-md-2 col-md-offset-5">Hello Wordl</div>
	</div>-->
	

	

</div>

</body>
</html>