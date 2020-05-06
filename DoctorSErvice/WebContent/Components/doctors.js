$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	}  
	$("#alertError").hide(); }); 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateItemForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{  
		url : "DoctorAPI",  
		type : type,  
		data : $("#formItem").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onItemSaveComplete(response.responseText, status);  
		} 
	}); 
}); 

function onItemSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divItemsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidItemIDSave").val("");  
	$("#formItem")[0].reset(); 
} 
 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());     
	$("#docCode").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#docname").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#specilisation").val($(this).closest("tr").find('td:eq(2)').text());
	$("#doctell").val($(this).closest("tr").find('td:eq(3)').text());
	$("#docmaill").val($(this).closest("tr").find('td:eq(4)').text()); 
}); 

//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "DoctorAPI",   
		type : "DELETE",   
		data : "docid=" + $(this).data("docid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onItemDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onItemDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divItemsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}

// CLIENT-MODEL========================================================================= 
function validateItemForm() 
{  
	// CODE  
	if ($("#docCode").val().trim() == "")  
	{   
		return "Insert Doctor Code.";  
	} 
 
	// NAME  
	if ($("#docname").val().trim() == "")  
	{   
		return "Insert Doctor Name.";  
	} 
	// SPECIL  
	if ($("#specilisation").val().trim() == "")  
	{   
		return "Insert Doctor Specilisation.";  
	} 
	//PHONE-------------------------------  
	if ($("#doctell").val().trim() == "")  
	{   
		return "Insert Phone Number.";  
	} 

	// is phone number numerical value  
	var tmpPrice = $("#doctell").val().trim();  
	if (!$.isNumeric(tmpPrice))  
	{   
		return "Insert a numerical value for Phone Number.";  
	} 
	
	// EMAILL  
	if ($("#docmaill").val().trim() == "")  
	{   
		return "Insert Doctor Emaill.";  
	} 


	return true; 
}