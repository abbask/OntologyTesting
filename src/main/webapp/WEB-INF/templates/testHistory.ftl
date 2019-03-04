<!DOCTYPE html>
<html lang="en">
<head>
  <title>Test History</title>
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
      <a class="navbar-brand" href="/OntologyTesting/OntologyTest">Ontology Test</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="/OntologyTesting/SystemTestList">System Testing</a></li>      
      <li><a href="/OntologyTesting/UnitTestList">Unit Testing</a></li>
      <li class="active"><a href="/OntologyTesting/TestHistory">History</a></li>
      
    </ul>
  </div>
</nav>
  
<div class="container">
	<h3>Test History</h3>

	<div style="float: right;">
		<form class="form-inline" method="post">
		  <div class="form-group mx-sm-3 mb-2">
		    <label for="systemTest">Number of records:</label>
		    <select class="form-control" id="numberofRows" name="numberofRows">
		    	<option <#if numberofRows == 5>selected</#if> value="5">5</option>
		    	<option <#if numberofRows == 10>selected</#if> value="10">10</option>
		    	<option <#if numberofRows == 15>selected</#if> value="15">15</option>
		    	<option <#if numberofRows == 20>selected</#if> value="20">20</option>
		    </select>
		  </div>
		  <button type="submit" class="btn btn-primary mb-2">Go</button>
		</form>
	</div>

	<div style="float: left;">
		<#list systemHistorys as systemHistory>
			<div class="panel panel-default">
			  <div class="panel-heading">${systemHistory.mySystemTest.name} - ${systemHistory.date} </div>
			  <div class="panel-body">
			    <#list systemHistory.unitTestHistorys as unitTestHistory>
			    	 <p> name: ${unitTestHistory.myUnitTest.name} - status: ${unitTestHistory.status} - message: ${unitTestHistory.message} </p><br/>
			   	<#else>
			   		<small id="nameHelp" class="form-text text-muted">No unit test found. You should have added unit test first.</small>
			    </#list>
			  </div>
			</div>
		</#list>
	</div>
</div>

</body>
</html>