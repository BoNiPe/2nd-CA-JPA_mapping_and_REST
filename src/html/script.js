$(document).ready(function () {

    fetchAll();
    deletePerson();
    initPersons();
    initAddBtn();
    initCancelBtn();
    initSaveBtn();
    initSaveRoleButton();
    initAddRoleButton();
    fetchAllz();
    document.getElementById("persons").disable = true;

});

function initAddBtn() {
    $("#btn_add").click(function () {
        initDetails(true);
        fetchAll();
    });
}

function initAddRoleButton() {
    $("#btn_addrole").click(function () {
        document.getElementById("persons").disable = true;
        initRoleDetails(true);
        //fetchAllz();
    });
}

function initSaveRoleButton() {
    $("#btn_role").click(function () {
        
            var newSchoolRole;
            if ($("#prole").val() === "Teacher" && $("#prole").val() !== "" && $("#pfunction").val() !== "") {
                newSchoolRole = {"degree": $("#pfunction").val(), "roleName": $("#prole").val()};
                executeRoleSavingModule(newSchoolRole);
            } else if ($("#prole").val() === "Student" && $("#prole").val() !== "" && $("#pfunction").val() !== "") {
                newSchoolRole = {"semester": $("#pfunction").val(), "roleName": $("#prole").val()};
                executeRoleSavingModule(newSchoolRole);
            } else if ($("#prole").val() === "TeacherAssistant" && $("#prole").val() !== "") {
                newSchoolRole = {"roleName": $("#prole").val()};
                executeRoleSavingModule(newSchoolRole);
            } else {
                alert("PLEASE INPUT SOMETHING PROPER OR YOU WILL CRASH OUR PROGRAM </3 ... :/");
            }
        
        initRoleDetails(false);
        fetchAll();
    });
}

function executeRoleSavingModule(newSchoolRole) {
    alert(JSON.stringify(newSchoolRole));
    $.ajax({
        url: "../role/" + $("#id").val(),
        data: JSON.stringify(newSchoolRole), //Convert newRole to JSON
        type: "post",
        dataType: 'json',
        error: function (jqXHR, textStatus, errorThrown) {
            alert("Error in add new SchoolRole" + jqXHR.responseText + ": " + textStatus);
        }
    });
}

function initSaveBtn() {
    $("#btn_save").click(function () {
        var letters = /^[a-z]+$/;
        //if(inputtxt.value.match(letters))

        if (!$("#phone").val().match(letters) && $("#fname").val() !== ""
                && $("#lname").val() !== "" && $("#phone").val() !== ""
                && $("#email").val() !== "" && $("#email").val().match("@")) {
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
        } else {
            alert("STOP RUINING MY CODE  ... :/");
        }
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
        if ($("#persons option:selected").val()) {
            $.ajax({
                url: "../person/" + $("#persons option:selected").attr("id"),
                type: "DELETE",
                dataType: 'json',
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText + ": " + textStatus);
                    if ($("#persons option:selected").attr("id") === null) {
                        alert("Got ya.");
                    }
                }
            }).done(function () {
                fetchAll();
            });
        } else {
            alert("NOTHING SELECTED, DON'T TRY TO RUIN OUR PROGRAM PLS ;S::S:S:S;S;s;sSssS");
        }
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
        $("#btn_role").attr("disabled", "disabled");
        $("#btn_addrole").attr("disabled", "disabled");
        $("#delete").attr("disabled", "disabled");

    }
    else {
        $("#fname").attr("disabled", "disabled");
        $("#lname").attr("disabled", "disabled");
        $("#phone").attr("disabled", "disabled");
        $("#email").attr("disabled", "disabled");
        $("#btn_save").attr("disabled", "disabled");
        $("#btn_cancel").attr("disabled", "disabled");
        $("#btn_role").attr("disabled", "disabled");
        $("#btn_addrole").attr("disabled", "disabled");
        $("#btn_add").removeAttr("disabled");
        $("#delete").removeAttr("disabled");
    }
}

function initRoleDetails(init) {
    if (init) {
        $("#prole").removeAttr("disabled");
        $("#pfunction").removeAttr("disabled");
        $("#btn_cancel").removeAttr("disabled");
        $("#btn_role").removeAttr("disabled");
        $("#btn_add").attr("disabled", "disabled");
        $("#btn_addrole").attr("disabled", "disabled");
        $("#delete").attr("disabled", "disabled");
    }
    else {
        $("#prole").attr("disabled", "disabled");
        $("#pfunction").attr("disabled", "disabled");
        $("#btn_cancel").attr("disabled", "disabled");
        $("#btn_role").attr("disabled", "disabled");
        $("#btn_addrole").attr("disabled", "disabled");
        $("#btn_add").removeAttr("disabled");
        $("#delete").removeAttr("disabled");
    }
}

function clearDetails() {
    $("#id").val("");
    $("#fname").val("");
    $("#lname").val("");
    $("#phone").val("");
    $("#email").val("");
    $("#prole").val("");
    $("#pfunction").val("");
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
        $("#btn_addrole").removeAttr("disabled");
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

function fetchAllz() {
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
        //clearDetails();
    });
}