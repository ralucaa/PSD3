$(function() {
    // Set up datepicker
    $( ".date_picker" ).datepicker();
    
    // Session frequency listener
    var sessionFrequency = $( "#session_frequency" );
    sessionFrequency.change(function() {
        theValue = sessionFrequency.val();
        additionalSettings = $( "#repeat_until_wrapper" );
        if (theValue == "0") {
            additionalSettings.addClass( "hidden" );
        } else {
            additionalSettings.removeClass( "hidden" );
        }        
    });
    
    // Set up accordion
    $( ".accordion" ).accordion({collapsible:true});
});
