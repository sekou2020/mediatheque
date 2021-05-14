// Utilisation : afficherPopupConfirmationLien('Désirez-vous vraiment supprimer cet item ?', this);

 

function afficherPopupConfirmationLien(question, lien) {

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
                    var hrefdefini = false;
                    if (lien != null) {
                        if ($(lien).attr("href") != undefined) {
                            hrefdefini = true;
                            // affiche la page de l'attribut href
                            window.location.href = $(lien).attr("href");
                        }
                    }
                    if (!hrefdefini) {
                        // réaffiche la page actuelle
                        window.location.reload();
                    }
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