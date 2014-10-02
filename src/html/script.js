$(document).ready(function () {

    fetchAll();
    deletePerson();
    initPersons();
    initAddBtn();
    initCancelBtn();
    initSaveBtn();
    initAddRoleBtn();

});

function initAddRoleBtn() {
    $("#btn_role").click(function () {
        initRoleDetails(true);
        //First create post argument as a JavaScript object
        var newSchoolRole = {"degree": $("#pfunction").val(), "roleName": $("#prole").val()};
        //{"degree": "BITCH","roleName": "Teacher"}
        alert(JSON.stringify(newSchoolRole));
        $.ajax({
            url: "../role/" + $("#id").val(),
            data: JSON.stringify(newSchoolRole), //Convert newRole to JSON
            type: "post",
            dataType: 'json',
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Error in add new SchoolRole" + jqXHR.responseText + ": " + textStatus);
            }
        }).done(function (newSchoolRole) {
           $("#id").val(newSchoolRole.roleName);

        });
    });
}


function initAddBtn() {
    $("#btn_add").click(function () {
        initDetails(true);
        fetchAll();
    });
}

function initSaveBtn() {
    $("#btn_save").click(function () {
        //First create post argument as a JavaScript object
        var newPerson = {"firstName": $("#fname").val(), "lastName": $("#lname").val(), "phone": $("#phone").val(), "mail": $("#email").val()};
        $.ajax({
            url: "../person",
            data: JSON.stringify(newPerson), //Convert newPerson to JSON
            type: "post",
            dataType: 'json',
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText + ": " + textStatus);
            }
        }).done(function (newPerson) {
            $("#id").val(newPerson.id);
            initDetails(false);
            fetchAll();
        });
    });
}

function initCancelBtn() {
    $("#btn_cancel").click(function () {
        clearDetails();
        initDetails(false);
        fetchAll();
    });
}


function initPersons() {
    $("#persons").click(function (e) {
        var id = e.target.id;
        if (isNaN(id)) {
            return;
        }
        updateDetails(id);
    });
}

function deletePerson() {
    $("#delete").click(function () {
        $.ajax({
            url: "../person/" + $("#persons option:selected").attr("id"),
            type: "DELETE",
            dataType: 'json',
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText + ": " + textStatus);
            }
        }).done(function () {
            fetchAll();
        });
    });
}

function initDetails(init) {
    if (init) {
        $("#fname").removeAttr("disabled");
        $("#lname").removeAttr("disabled");
        $("#phone").removeAttr("disabled");
        $("#email").removeAttr("disabled");
        $("#btn_save").removeAttr("disabled");
        $("#btn_cancel").removeAttr("disabled");
        $("#btn_add").attr("disabled", "disabled");


    }
    else {
        $("#fname").attr("disabled", "disabled");
        $("#lname").attr("disabled", "disabled");
        $("#phone").attr("disabled", "disabled");
        $("#email").attr("disabled", "disabled");
        $("#btn_save").attr("disabled", "disabled");
        $("#btn_cancel").attr("disabled", "disabled");
        $("#btn_add").removeAttr("disabled");
        $("#btn_role").removeAttr("disabled");
    }
}

function initRoleDetails(init) {
    if (init) {
        $("#prole").removeAttr("disabled");
        $("#pfunction").removeAttr("disabled");
        $("#btn_save").removeAttr("disabled");
        $("#btn_cancel").removeAttr("disabled");
        $("#btn_add").attr("disabled", "disabled");
    }
    else {
        $("#fname").attr("disabled", "disabled");
        $("#lname").attr("disabled", "disabled");
        $("#phone").attr("disabled", "dsiabled");
        $("#btn_save").attr("disabled", "disabled");
        $("#btn_cancel").attr("disabled", "disabled");
        $("#btn_add").removeAttr("disabled");
    }
}

function clearDetails() {
    $("#id").val("");
    $("#fname").val("");
    $("#lname").val("");
    $("#phone").val("");
    $("#email").val("");
}
function updateDetails(id) {
    $.ajax({
        url: "../person/" + id,
        type: "GET",
        dataType: 'json',
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.getResonseText + ": " + textStatus);
        }
    }).done(function (person) {
        $("#id").val(person.id);
        $("#fname").val(person.firstName);
        $("#lname").val(person.lastName);
        $("#phone").val(person.phone);
        $("#email").val(person.mail);
        $("#btn_role").removeAttr("disabled");
    });
}

function fetchAll() {
    $.ajax({
        url: "../person",
        type: "GET",
        dataType: 'json',
        error: function (jqXHR, textStatus, errorThrown) {
            alert(textStatus);
        }
    }).done(function (persons) {
        var options = "";
        persons.forEach(function (person) {
            options += "<option id=" + person.id + ">" + person.firstName + ", " + person.lastName + ", " + person.phone + ", " + person.mail + ", " + JSON.stringify(person.listOfSchoolRoles) + "</option>";
        });
        $("#persons").html(options);
        clearDetails();
    });
}