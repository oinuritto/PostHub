let countField = document.getElementById("postTextCounter");
let field = document.getElementById("postText");
let limit = 1600;

field.addEventListener("keyup", function (evt) {
    let len = field.value.length;
    countField.innerText = limit - len;
});

