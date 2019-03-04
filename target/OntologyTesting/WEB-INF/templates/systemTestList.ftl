<!DOCTYPE html>
<html lang="en">
<head>
  <title>System Test List</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/DiscoverOntology/OntologyTest">Ontology Test</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/DiscoverOntology/SystemTestList">System Testing</a></li>
      <li><a href="/DiscoverOntology/UnitTestList">Unit Testing</a></li>
      <li><a href="/DiscoverOntology/TestHistory">History</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>List of System Tests</h3>
	<p>You can see list of system test and load them.</p>
	<div>
		<a href="SystemTestNew" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Add System Test</a>
	
	</div>
	<div>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">name</th>
		      <th scope="col">end point</th>
		      <th scope="col">graph</th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list testSystems as testSystem>
				<tr>
					<td>
						${testSystem.name}
					</td>
					<td>
						${testSystem.endPoint}
					</td>
					<td>
						${testSystem.graph}
					</td>
					<td>
						<a href="SystemTestRemove?id=${testSystem.ID}" class="btn btn-danger btn-md active" role="button" aria-pressed="true">Remove</a>
					</td>
				</tr>
			<#else>
			  <tr>
			  	<td colspan="4">
			  		No data found
			  	</td>
			  </tr>
			</#list>
			

		  </tbody>
		</table>
	</div>
</div>

</body>
</html>