<html>
<head>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="js/classResult.js"></script>
<script src="js/objectResult.js"></script>
<script src="js/dataResult.js"></script>
<script src="js/targetChange.js"></script>

<link rel="stylesheet" href="css/style.css">	
  <title>Discovering Ontology</title>
</head>
<body>
<div class="floatLeft">
	<div class="classCount">Number of Classes: ${classCount}</div>
	<div class="dataPropertyCount">Number of Data Properties: ${dataPropertyCount}</div>
	<div class="objectPropertyCount">Number of Object Properties: ${objectPropertyCount}</div>	
	<div class="classInstance">Number of Individuals: ${classInstance}</div>	
</div>
<div class="floatRight form">
	<form>
		<label for="endpoint">End Point:</label><input placeholder="${endpoint}" type="text" id="endpoint" name="endpoint"><br/>
		<label for="graphName">Graph Name:</label><input placeholder="${graphName}" type="text" id="graphName" name="graphName"> 
		<button id="changeTarget" name="changeTarget" type="button" >change</button>
	</form>  	
</div>
<div class="clear">
	<table>
		<tr>
			<td>
				<button id="showClasses" name="showClasses" type="button" >Show Classes</button>				
			</td>
			<td>
				<button id="showObjectProperties" name="showObjectProperties" type="button" >Show Object Properties</button>
			</td>
			<td>
				<button id="showDataProperties" name="showDataProperties"  type="button" >Show Data Properties</button>	
			</td>
		</tr>
	</table>
<div>
<div id="resultDiv">
</div>
</body>
</html>
