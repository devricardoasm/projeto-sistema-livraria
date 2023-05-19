$.validator.addMethod("equal", function (value, element, param) {
    return value === param;
});

$().ready(function () {
    var validator = $("#formLogin").bind("invalid-form.validate", function () {
        $("#msg").html("OPS! Este formulario tem " + validator.numberOfInvalids() + " erro(s)");

    }).validate(
            {
                errorElement: "el",
                errorPlacement: function (error, element) {
                    element.parent("td").next("td").html(error);
                },
                success: function (label) {
                    label.text("✓").removeClass("error").addClass("ok");
                },
                submitHandler: function (form) {
                    form.submit();
                },
                rules: {
                    txtLogin: {
                        required: true,
                        email: true
                    },
                    txtSenha: {
                        required: true

                    }

                },
                messages: {
                    txtLogin: {
                        required: "Digite seu email",
                    },
                    txtSenha: {
                        required: "Digite sua senha"

                    }

                }
            }
    );
});

