<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.header{
  position: fixed; /* fix the header to the top of the page */
        top: 0;
        left: 0;
        width: 100%;
        background-color: #10629a; /* set background color */
        color: white; /* set text color */
        padding: 1px;
        text-align: center; /* center the text within the header */
}
.content-body{
	margin: 4% 2%;
}
.excel-data-table{
	width:100%;
}

</style>
</head>
<body>
	<div class="wrapper">
		<div class="main-body">
			<div class="header">
				<div class="header-heading">
					Distribution Target Setup [URL]
				</div>
			</div>
			<div class="content-body">
				<div class="content-fields">
					<label>Select JCYear :</label> 
					<select onchange = "return getJcMonth()" id="jcyear">
					 <option value="-1" selected="selected">Select Year</option>
						<c:forEach var="year" items="${years}">
							<option value="${year}">Year ${year}</option>
						</c:forEach>
					</select> <br>
					
					
					<label>Select JCMonth :</label>
					<select id="monthDropdown">
					 <option value="-1" selected="selected">Select Month</option>
					 <c:forEach var="month" items="${monthList}">
							<option value="${month}">Month ${month}</option>
					 </c:forEach>
					</select><br>
					
					
						 <label>Max Target Cap :</label> <input type="number">
						 <label>Min Target Cap :</label> <input type="number"><br>
						 
						 <form >
						 <label>Select File :</label> <input type="file" id="excelFile" accept=".xls,.xlsx" required > <input type="button" value="Upload" id="uploadButton"/>
						 </form>
				</div>
			</div>
			<div class="excel-body">
				<div>
					<table style="width: 100%">
						<thead>
							<tr style="background-color: lightgrey; text-align: center;">
								<th>SAP Code</th>
								<th>SKU Code</th>
								<th>SKU Name</th>
								<th>Target City(Ctn)</th>
								<th>Target Qty(Pcs)</th>
							</tr>
						</thead>
						<tbody>
							<!-- Add table data here -->
						</tbody>
					</table>
				</div>
			<div class="excel-body-header">
					<table>

					</table>
				</div>
			</div>
		</div>
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

function bindData(data) {
	alert("called")
    var select = document.getElementById("monthDropdown");
    for (var i = 0; i < data.length; i++) {
        var option = document.createElement("option");
        option.value = data[i];
        option.text = data[i];
        select.appendChild(option);
    }
}
	function getJcMonth(){
		alert("getJcMonth Called")
		let year = document.getElementById("jcyear").value;
		if(year!=null || year !=''){
			 $.ajax({
				    type: "GET",
				    url: "/SpringMVC4withHibernateCRUD/customer/jcmonth?year=" + year,
				    success: function(response) {
				      console.log("response is : " +response)
				      bindData(response);
				    },
				    error: function(xhr, status, error) {
				        console.log("error is : " +error);
				    }
				  });
		}
	}
	
	 $(document).ready(function() {
	        $("#uploadButton").click(function() {
	            var formData = new FormData();
	            formData.append("excelFile", $("#excelFile")[0].files[0]);

	            $.ajax({
	                url: "/SpringMVC4withHibernateCRUD/customer/uploadExcel",
	                type: "POST",
	                data: formData,
	                processData: false,
	                contentType: false,
	                success: function(excelDataList) {
	                    console.log(excelDataList)
	                    var table = $("<table>").addClass("excel-data-table");
	                 /*    var headerRow = $("<tr>");
	                    headerRow.append("<th>SAP Code</th>");
	                    headerRow.append("<th>SKU Code</th>");
	                    headerRow.append("<th>SKU Name</th>");
	                    headerRow.append("<th>Target Qty(Ctn)</th>");
	                    headerRow.append("<th>Target Qty(Pcs)</th>");
	                    table.append(headerRow); */
	                    excelDataList.forEach(function(data) {
	                        var row = $("<tr>");
	                        row.append("<td>" + data.sapCode + "</td>");
	                        row.append("<td>" + data.skuCode + "</td>");
	                        row.append("<td>" + data.skuName + "</td>");
	                        row.append("<td>" + data.targetQtyCtn + "</td>");
	                        row.append("<td>" + data.targetQtyPcs + "</td>");
	                        table.append(row);
	                    });
	                    $("body").append(table);
	                }
	            });
	        });
	    });
	
</script>
</body>
</html>