/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




$.validator.addMethod("equal", function (value, element, param) {
    return value === param;
});

$().ready(function () {
    var validator = $("#formCadastro").bind("invalid-form.validate", function () {
        $("#msgg").html("OPS! Este formulario tem " + validator.numberOfInvalids() + " erro(s)");

    }).validate(
            {
                errorElement: "ell",
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
                    txtNome: {
                        required: true

                    },
                    txtSobrenome: {
                        required: true

                    },
                    txtLogin: {
                        required: true,
                        email: true
                    },
                    txtCpf: {
                        required: true,
                        number: true,
                        maxlength: 11,
                        minlength: 11
                    },
                    txtSenha: {
                        required: true,
                        minlength: 6
                    },
                    txtTel: {
                        required: true,
                        number: true
                    }

                },
                messages: {
                    txtNome: {
                        required: "Esse campo não pode ser vazio"


                    },
                    txtSobrenome: {
                        required: "Esse campo não pode ser vazio"


                    },
                    txtLogin: {
                        required: "Esse campo não pode ser vazio",
                        email: "Digite um endereço de email"
                    },
                    txtCpf: {
                        required: "Esse campo não pode ser vazio",
                        number: "Este campo é numerico",
                        maxlength: "Cpf deve conter 11 digitos",
                        minlength: "Cpf deve conter 11 digitos"
                    },
                    txtSenha: {
                        required: "Esse campo não pode ser vazio",
                        minlength: "Sua senha deve conter no minimo 6 digitos"
                    },
                    txtTel: {
                        required: "Esse campo não pode ser vazio",
                        number: "Este campo é numerico"
                    }
                }
            }
    )
});
        