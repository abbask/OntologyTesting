<!DOCTYPE html>
<html lang="en">
<head>
  	<title>System Test List</title>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	
  	<script src="js/testprocess.js"></script>
  	
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
      <a class="navbar-brand" href="/DiscoverOntology/OntologyTest">Ontology Test</a>
    </div>
    <ul class="nav navbar-nav">
      <li ><a href="/DiscoverOntology/SystemTestList">System Testing</a></li>
      <li><a href="/DiscoverOntology/UnitTestList">Unit Testing</a></li>
      <li><a href="/DiscoverOntology/TestHistory">History</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>Ontology Test</h3>
	<p>Select your system test to run</p>
	<form class="form-inline">
		
		<div class="col-xs-16">
			
			<select class="form-control" id="systemTest" name="systemTest">
				<option value="0">-- select --</option>
				<#list testSystems as testSystem>
					<option value="${testSystem.ID}">${testSystem.name}</option>
				</#list>			    		
		    </select>
			<button id="runTest" type="button" class="btn btn-primary">Run</button>
		</div>
	</form>
	
	<div id="divTable">
	</div>	
	
	

</div>

</body>
</html>