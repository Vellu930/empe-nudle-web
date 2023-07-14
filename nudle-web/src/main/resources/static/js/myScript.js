
function addtext(string) {
    document.getElementById('comment').value += "\n Kod " + string;
}

function disableButton() {
    console.log("disabling button...")
    let submit = document.getElementById("submit1");
    submit.disabled = true;
    return true;
}