
	$(document).ready(function(){
		$("#shipmentModeError").hide();
		$("#shipmentCodeError").hide();
		$("#enableShipmentError").hide();
		$("#shipmentGradeError").hide();
		$("#descriptionError").hide();
		
		var shipmentModeError=false;
		var shipmentCodeError=false;
		var enableShipmentError=false;
		var shipmentGradeError=false;
		var descriptionError=false;
		
		$("#shipmentMode").change(function(){
			validate_shipmentMode();
		});
		$("#shipmentCode").keyup(function(){
			$("#shipmentCode").val($("#shipmentCode").val().toUpperCase());
			validate_shipmentCode();
		});
		$("#enableShipment").change(function(){
			validate_enableShipment();
		});
		$('input[type="radio"][name="shipmentGrade"]').change(function(){
			validate_shipmentGrade();
		});
		$("#description").keyup(function(){
			validate_description();
		});
		
		function validate_shipmentMode(){
			var val=$("#shipmentMode").val();
			if(val==''){
				$("#shipmentModeError").show();
				$("#shipmentModeError").html("Choose <b>shipmentMode</b>");
				$("#shipmentModeError").css("color","red");
				shipmentModeError=false;
			}else{
				$("#shipmentModeError").hide();
				shipmentModeError=true;
			}
			return shipmentModeError;
		}
		
		function validate_shipmentCode(){
			var val=$("#shipmentCode").val();
			var exp=/^[1-9][0-9]*$/;
			if(val==''){
				$("#shipmentCodeError").show();
				$("#shipmentCodeError").html("Choose <b>shipmentCode</b>");
				$("#shipmentCodeError").css("color","red");
				shipmentCodeError=false;
			}else if(!exp.test(val)){
				$("#shipmentCodeError").show();
				$("#shipmentCodeError").html("Enter <b> valid code only</b>");
				$("#shipmentCodeError").css("color","red");
				shipmentCodeError=false;
			}else{
				
				$.ajax({
					url: 'validate',
					data: {"code":val},
					success: function(resTxt){
						if(resTxt!=""){
							$("#shipmentCodeError").show();
							$("#shipmentCodeError").html(resTxt);
							$("#shipmentCodeError").css("color","red");
							shipmentCodeError=false;
						}else{
							$("#shipmentCodeError").hide();
							shipmentCodeError=true;
						}
					}
				});
			}
			return shipmentCodeError;
		}
		
		function validate_enableShipment(){
			var val=$("#enableShipment").val();
			if(val==''){
				$("#enableShipmentError").show();
				$("#enableShipmentError").html("Choose <b>Enable shipment</b>");
				$("#enableShipmentError").css("color","red");
				enableShipmentError=false;
			}else{
				$("#enableShipmentError").hide();
				enableShipmentError=true;
			}
			return enableShipmentError;
		}
		
		function validate_shipmentGrade() {
            var len = $('input[type="radio"][name="shipmentGrade"]:checked').length;
            if (len == 0) {
                $("#shipmentGradeError").show();
                $("#shipmentGradeError").html("Choose One <b>Shipment Grade</b>");
                $("#shipmentGradeError").css("color", "red");
                shipmentGradeError = false;
            } else {
                $("#shipmentGradeError").hide();
                shipmentGradeError = true;
            }
            return shipmentGradeError;
        }
		
		function validate_description() {
            var val = $("#description").val();
            if (val.length < 5 || val.length > 150) {
                $("#descriptionError").show();
                $("#descriptionError").html("Must be <b>5-150 Chars only</b>");
                $("#descriptionError").css("color", "red");
                descriptionError = false;
            } else {
                $("#descriptionError").hide();
                descriptionError = true;
            }
            return descriptionError;
        }
		$("#shipmentForm").submit(function () {
            validate_shipmentMode();
            validate_shipmentGrade();
            validate_enableShipment();
            validate_shipmentCode();
            validate_description();

            if (shipmentCodeError && shipmentGradeError && shipmentModeError
                && enableShipmentError && descriptionError)
                return true;
            else
                return false;
        });


	});
