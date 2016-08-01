
/**
* Theme: Velonic Admin Template
* Author: Coderthemes
* Form Validator
*/

!function($) {
    "use strict";
    
    // Edited / adapted from http://forum.jquery.com/topic/jquery-validate-comma-seperated-multiple-emails-validation#14737000002179275
	jQuery.validator.addMethod("multiemail", function (value, element) {
	    if (this.optional(element)) {
	        return true;
	    }
	
	    var emails = value.split(','),
	        valid = true;
	
	    for (var i = 0, limit = emails.length; i < limit; i++) {
	        value = jQuery.trim(emails[i]);
	        valid = valid && jQuery.validator.methods.email.call(this, value, element);
	    }
	
	    return valid;
	}, "Invalid email format: please use a comma to separate multiple email addresses.");

    var FormValidator = function() {
        this.$emailForm = $("#emailForm")
    };

    //init
    FormValidator.prototype.init = function() {
        
		this.$emailForm.validate({
		        rules: {
		            recipient: {
		                required: true,
		                email: true
		            },
		            carbonCopy: {
		                email: true
		            },
		            blindCopy: {
		                email: true
		            },
		            subject: {
		                required: true 
		            },
		            message: {
		                required: true
		            }
		        },
		        messages: {
			        recipient: {
		                required: "Please provide a recipient",
		                email: "Invalid email address"
		            },
		            carbonCopy: {
		                email: "Invalid email address"
		            },
		            blindCopy: {
		                email: "Invalid email address"
		            },
		            subject: {
		                required: "Please provide a subject"
		            },
		            message: {
		                required: "Please provide a message"
		            }
		        }
		        
		    });//end validate

    

        //code to hide topic selection, disable for demo
        var newsletter = $("#newsletter");
        // newsletter topics are optional, hide at first
        var inital = newsletter.is(":checked");
        var topics = $("#newsletter_topics")[inital ? "removeClass" : "addClass"]("gray");
        var topicInputs = topics.find("input").attr("disabled", !inital);
        // show when newsletter is checked
        newsletter.click(function() {
            topics[this.checked ? "removeClass" : "addClass"]("gray");
            topicInputs.attr("disabled", !this.checked);
        });

    },
    //init
    $.FormValidator = new FormValidator, $.FormValidator.Constructor = FormValidator
}(window.jQuery),


//initializing 
function($) {
    "use strict";
    $.FormValidator.init()
}(window.jQuery);