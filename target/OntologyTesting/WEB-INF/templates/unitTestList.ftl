<!DOCTYPE html>
<html lang="en">
<head>
  <title>Unit Test List</title>
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
      <li><a href="/DiscoverOntology/SystemTestList">System Testing</a></li>      
      <li class="active"><a href="/DiscoverOntology/UnitTestList">Unit Testing</a></li>
      <li><a href="/DiscoverOntology/TestHistory">History</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>List of Unit Tests</h3>
	<p>You can see list of unit test and load them.</p>
	<div  style="float: left;">
		<a href="UnitTestNew" class="btn btn-primary btn-md active" role="button" aria-pressed="true">Add Unit Test</a>
	</div>
	<div style="float: right;">
		<form class="form-inline" method="post">
		  <div class="form-group mx-sm-3 mb-2">
		    <label for="systemTest">Filter</label>
		    <select class="form-control" id="systemTestSelect" name="systemTestSelect">
		    	<#list systemTests as systemTest>
		    		<#if systemTest.ID == systemTestId>
		    			<option selected  value="${systemTest.ID}">${systemTest.name}</option>
		    		<#else>
		    			<option  value="${systemTest.ID}">${systemTest.name}</option>
		    		</#if>
		    	<#else>
		    		
		    	</#list>
		    </select>
		  </div>
		  <button type="submit" class="btn btn-primary mb-2">Go</button>
		</form>
	</div>

	<div>
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">name</th>
		      <th scope="col">query</th>
		      <th scope="col">message</th>
		      <th scope="col">system test</th>
		      <th scope="col"></th>
		    </tr>
		  </thead>
		  <tbody>
		  	<#list unitTests as unitTest>
				<tr>
					<td>
						${unitTest.name}
					</td>
					<td style="text-overflow: ellipsis;">

						
						${unitTest.query}
						
					</td>
					<td>
						${unitTest.message}
					</td>
					<td>
						${unitTest.systemTest.name}
					</td>
					<td>
						<a href="UnitTestRemove?id=${unitTest.ID}" class="btn btn-danger btn-md active" role="button" aria-pressed="true">Remove</a>
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