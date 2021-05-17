//Affichage de la Popup de Confirmation('Voulez-vous supprimez ce media?);

 function afficherPopupConfirmationLien(question, lien) {

function afficherPopupConfirmationLien(question, lien, requestType, refresh) {
    // crée la division qui sera convertie en popup
    $('body').append('<div id="popupconfirmation" title="Confirmation"></div>');
    $("#popupconfirmation").html(question);

    // transforme la division en popup
    var popup = $("#popupconfirmation").dialog({
        autoOpen: true,
        width: 400,
        dialogClass: 'dialogstyleperso',
        hide: "fade",
        buttons: [
            {
                text: "Oui",
                class: "ui-state-question",
                click: function () {
                    $(this).dialog("close");
                   $.ajax({
                        url: lien,
                        type: requestType,
                        contentType: "application/json",
                        cache: false,
                        timeout: 600000,
                       success: function () {
                            if( refresh ){
                                location.reload();
                            }
                       },
                    });
                    $("#popupconfirmation").remove();
                }
            },
            {
                text: "Non",
                class: "ui-state-question",
                click: function () {
                    $(this).dialog("close");
                    $("#popupconfirmation").remove();
                }
            }
        ]
    });
    $("#popupconfirmation").prev().addClass('ui-state-question');
    return popup;
}