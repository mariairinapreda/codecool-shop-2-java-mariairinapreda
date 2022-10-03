function onSignIn(googleUser) {
    let profile = googleUser.getBasicProfile();
    $("#username").text(profile.getEmail());
    $(".g-signin2").css("display", "none");
}


function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
    });
}
