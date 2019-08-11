var XMPP = {
    connection: null,

    log: function(msg) {
        $("#log").append("<p>" + msg + "</p>");
    }
};

$(document).ready(function () {
    //alert("Document Ready");
    $('#login_dialog').dialog({
        autoOpen: true,
        draggable: false,
        modal: true,
        title: "Connect to XMPP",
        buttons: {
            "Connect": function() {
                $(document).trigger("connect", {
                    jid: $("#jid").val(),
                    pwd: $("#pwd").val()
                });
                $("#pwd").val("");
                $(this).dialog("close");
            }
        }
    });
});

$(document).bind("connect", function(ev, data) {
    var conn = new Strophe.Connection("http://ip-0a1e0af4:5280/http-bind");
    //var conn = new Strophe.Connection("http://bosh.metajack.im:5280/xmpp-httpbind");
    conn.connect(data.jid, data.pwd, function(status) {
        if (status === Strophe.Status.CONNECTED) {
            $(document).trigger("connected");
        } else if (status === Strophe.Status.DISCONNECTED) {
            $(document).trigger("disconnected");
        }
    });

    XMPP.connection = conn;
});

$(document).bind("connected", function() {
    XMPP.log("Connection established.");
});

$(document).bind("disconnected", function() {
    XMPP.log("Connection terminated.");
    XMPP.connection = null;
});