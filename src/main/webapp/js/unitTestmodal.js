	$(window).on("load",function(){
	     $(function(){
	     
	     	//var person = {firstName:"John", lastName:"Doe", age:46};
			//document.getElementById("demo").innerHTML = person["firstName"];
			
			list = [];
			listofvars = [];
			selectVars = [];
			result = [];
	     	
			//modalSave
			$('#modalSave').click(function(){	
			
				if (type == 1){
				
					list.length = 0;
					$('#formValue').val( $('#value').val());
					$('#formAssertType').val( $('#assertType').val());
					
			    	tableHtml = "<table class='table' id='expectedValueTable'><tr><th>Scalar Value</th></tr>";
			    	$('#tableDiv').empty();
					tableHtml += "<tr><td>" +  $('#value').val() + "</td><tr>";
					tableHtml += "</table>";
					
					$('#tableDiv').html(tableHtml);
				
				}
				else{ // when triple
					count = list.length
					$('#formAssertType').val( $('#assertType').val());
					$.each(selectVars, function( index, value ) {
						var varName = value.useName;
						var varValue = $("#" + varName).val();
						//console.log(varName + ' ' + varValue);
						value.value = varValue;
					});
					
					resultCount = result.length;
					result[resultCount] = selectVars;

					
					var myJSON = JSON.stringify(result);
					
					$('#formTripple').val(myJSON);
					
					tableHtml = "<table class='table' id='expectedValueTable'>";
					
					$('#tableDiv').empty();
					
					//just the table header
					singleResult = result[0];
					tableHtml += "<tr>";
					
					$.each( singleResult, function( key, value ) {
						tableHtml += "<th>" + value.originalName + "</th>";
					});
					tableHtml += "</tr>";
					
					$.each( result, function( index, single ) {
						tableHtml += "<tr>";
						$.each( single, function( key, value ) {
							tableHtml += "<td>" + value.value + "</td>";
						});
						tableHtml += "</tr>";
					});
					
					tableHtml += "</table>";
					
					$('#tableDiv').html(tableHtml);
					
					
				}
				
				$('#expectedValueModalBut').addClass('btn-primary').removeClass('btn-danger');
				
				$('#expectedValueModal').modal('toggle');
    			return false;
			
			});	
			
			$(document).on('click','#removeTriple',function(){
				index = $(this).attr('idValue');
				list.splice(index, 1);
				$('#fromTripple').val(list);
					
		    	tableHtml = "<table class='table' id='expectedValueTable'><tr><th>id</th><th>subject</th><th>predicate</th><th>object</th><th></th></tr>";
		    	$('#tableDiv').empty();
		    	$.each( list, function( key, value ) {
				  tableHtml += "<tr><td>" + value.id + "</td><td>" + value.subject + "</td><td>" + value.predicate + "</td><td>" + value.object + "</td><td><span id='removeTriple' idValue='" + value.id + "' class='glyphicon glyphicon-remove' style='color:red'></span><td></tr>";
				});
				tableHtml += "</table>";
				
				$('#tableDiv').html(tableHtml);
				
				if (list.length == 0 ) {
					$('#expectedValueModalBut').addClass('btn-danger').removeClass('btn-primary');
					$('#tableDiv').empty();	
				}
				
			});
			
			$('#expectedValueModal').on('shown.bs.modal', function (e) {
				query = $('#query').val();
				
				//check the query
				startIndex = query.toUpperCase().indexOf("SELECT") + 6 ;
				endIndex = query.toUpperCase().indexOf("WHERE");
				
				term = query.substring(startIndex, endIndex).trim();
				//console.log(term);
				counter = 0;
				
				arr  = term.split(" ");
				//console.log(arr);
				listofvars = [];
				for(var i=0 ; i<arr.length ; i++){
					var patternVar = /\?\w+/i;
					if (patternVar.test(arr[i])){
						var patternAS = /AS/i;
						if (patternAS.test(arr[i+1])){
							
							continue;
						}
						//console.log(arr[i]);
						listofvars[counter] = arr[i];
						counter++;
					}
				}
				
				selectVars = [];
				$.each(listofvars, function( index, value ) {
					var tempName = value.replace('?', '');
					tempName = tempName.replace('(','');
					tempName = tempName.replace(')','');
					value=value.replace(')','')
					
					var obj = {
						"originalName" : value,
						"useName": tempName,
						"index": index,
						"value": "0",
					};
					selectVars[index] = obj;
				});

				//modal-body
				type = 2;
				str = "";
				//HERE
				$('#expectedValueModalLabel').html('Expected Value - Triple</br><small>if you do not see all variables in the query, you might have syntax error.</small>');
				str+='<div class="form-group"><label for="assertType">Assert Type</label><select class="form-control" id="assertType" name="assertType"><option value="EQUAL">EQUAL</option><option value="LESS">LESS</option><option value="GREATER">GREATER</option></select></div>';
				$.each(selectVars, function( index, value ) {
					str += '<div class="form-group"><label for="' + value.useName + '">' + value.originalName + '</label><input type="text" class="form-control allVars" id="' + value.useName + '" name="' + value.useName + '" placeholder="Enter value"></div></div>';
				});
				
				$('.modal-body').html(str);	
				
//				if ( listofvars.length > 1 ) { // not scalar
//					//modal-body
//					type = 2;
//					str = "";
//					//HERE
//					$('#expectedValueModalLabel').html('Expected Value - Triple</br><small>if you do not see all variables in the query, you might have syntax error.</small>');
//					$.each(selectVars, function( index, value ) {
//						str += '<div class="form-group"><label for="' + value.useName + '">' + value.originalName + '</label><input type="text" class="form-control allVars" id="' + value.useName + '" name="' + value.useName + '" placeholder="Enter value"></div></div>';
//					});
//					$('.modal-body').html(str);	
//				}
//				else{  // scalar
//					type =1 ;
//					str = '<div class="form-group"><label for="assertType">Assert Type</label><select class="form-control" id="assertType" name="assertType"><option value="1">EQUAL</option><option value="2">LESS</option><option value="3">GREATER</option></select></div>';
//					str += '<div class="form-group"><label for="value">Value</label><input type="text" class="form-control" id="value" name="value" placeholder="Enter value"></div>';
//					$('.modal-body').html(str);	
//					$('#expectedValueModalLabel').html('Expected Value - Scalar');
//				}
				
			});
	     });
	});