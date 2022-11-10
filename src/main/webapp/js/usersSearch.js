function searchUsers(query) {
    return fetch('/posthub/adminPanel/searchUsers?query=' + query)
        .then((response) => {
            return response.json()
        }).then((users) => {
            fillTable(users)
        })
}

function fillTable(users) {
    let tBody = document.getElementById("usersTbody");

    tBody.innerHTML = "";

    users.sort((a, b) => b.rating - a.rating);

    for (let i = 0; i < users.length; i++) {
        let row = tBody.insertRow(-1);
        let idCell = row.insertCell(0);
        let usernameCell = row.insertCell(1);
        let firstNameCell = row.insertCell(2);
        let lastNameCell = row.insertCell(3);
        let rateCell = row.insertCell(4);
        let actionCell = row.insertCell(5);

        idCell.innerHTML = users[i].id;
        usernameCell.innerHTML = users[i].username;
        firstNameCell.innerHTML = users[i].firstName;
        lastNameCell.innerHTML = users[i].lastName;
        rateCell.innerHTML = users[i].rating;
        actionCell.innerHTML = "<a href=\"/posthub/adminPanel/userEdit?username=" + users[i].username + "\">\n" +
            "                                    <button class=\"btn btn-primary btn-sm\">Edit\n" +
            "                                    </button>\n" +
            "                                </a>"
    }
}
