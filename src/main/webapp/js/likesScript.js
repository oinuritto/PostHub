let jsonResponse;
let sessionUserId;


function setLike(query) {
    fetch('/posthub/post/setLike?postId=' + query)
        .then(() => {
            document.getElementById("ifLiked-post-" + query).setAttribute("value", "liked");
            document.getElementById("likedCount-post-" + query).className = "bi bi-hand-thumbs-up-fill";
            update(query);
            // updateIcon(query, sessionUserId);

        })
}

function dislike(query) {
    fetch('/posthub/post/dislike?postId=' + query)
        .then(() => {
            document.getElementById("ifLiked-post-" + query).setAttribute("value", "nonLiked");
            document.getElementById("likedCount-post-" + query).className = "bi bi-hand-thumbs-up";
            update(query);
            // updateIcon(query, sessionUserId);

        })
}

function workWithLike(query) {
    // console.log(sessionUserId);
    if (sessionUserId != null) {
        if (document.getElementById("ifLiked-post-" + query).getAttribute("value") === "liked") {
            dislike(query);
            // console.log(sessionUserId);

        } else {
            setLike(query);
            // console.log(sessionUserId);

        }
    }
}

function update(query) {
    return fetch('/posthub/post/likes?postId=' + query)
        .then((response) => {
            jsonResponse = response.json();
            return jsonResponse;
        }).then((likes) => {
            let likedCount = document.getElementById("likedCount-post-" + query);
            likedCount.innerHTML = getJSONLength(likes);
        })
}

function updateIcon(query, userId) {
    return fetch('/posthub/post/likes?postId=' + query)
        .then((response) => {
            jsonResponse = response.json();
            return jsonResponse;
        }).then((likes) => {
            for (let i = 0; i < likes.length; i++) {
                if (likes[i].userId === userId) {
                    document.getElementById("likedCount-post-" + query).className = "bi bi-hand-thumbs-up-fill";
                    document.getElementById("ifLiked-post-" + query).setAttribute("value", "liked");
                    break;
                }
                document.getElementById("likedCount-post-" + query).className = "bi bi-hand-thumbs-up";
                document.getElementById("ifLiked-post-" + query).setAttribute("value", "nonLiked");
            }
    })
}

function updateIcons(ids, userId) {
    sessionUserId = userId;
    for (let i = 0; i < ids.length; i++ ) {
        let id = ids[i];
        if (sessionUserId != null) {
            updateIcon(id, userId);
        }
        // console.log(i, id, sessionUserId);
    }
    return 0;
}


function getJSONLength(response) {
    var count = 0;
    for (let k in response) {
        count++;
    }
    return count;
}


// throws exceptions in console
// window.onload = function () {
//     let ids = document.getElementById("idOfPosts").value;
//     let userId = document.getElementById("userId").value;
//     updateIcons(ids, userId);
// }