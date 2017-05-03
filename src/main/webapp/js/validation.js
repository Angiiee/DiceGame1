function validate_form ( )
{
    var valid = true;

        if ( document.sign_up.pswd.value != document.sign_up.rpswd.value )
        {
                valid = false;

        }



    return valid;
};