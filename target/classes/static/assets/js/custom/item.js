const websocket = new SockJS('/ws-endpoint');
const stompClient = Stomp.over(websocket);













// INIT
appendInitialComments();
connectToWebsocket();






















// WEBSOCKET
function connectToWebsocket() {
    stompClient.connect({}, function (frame) {
        subscribeToComments();
    });
    console.log('=== CONNECTED TO WEBSOCKET ===');
}

function subscribeToComments() {
    stompClient.subscribe(
        '/topic/items/' + item.id + '/comments',

        function (response) {
            const comment = JSON.parse(response.body);

            appendSingleComment(comment, true);
        }
    )
    console.log('=== SUBSCRIBED TO COMMENTS ===');
}



















// LEAVE COMMENT
const btnSendComment = document.getElementById('btn-send-comment');
btnSendComment.addEventListener('click', function (e) {
    console.log('=== BTN SEND COMMENT PRESSED ===');
    sendLeaveCommentRequest(item.id);
});

function sendLeaveCommentRequest(itemId) {
    let commentBody = document.getElementById('new-comment-body').value;
    stompClient.send(
        '/app/items/' + itemId + '/comments',
        {},
        JSON.stringify(
            {
                'body': commentBody
            }
        )
    )
    console.log('=== SEND COMMENT TO THE API ===');
}






















// DRAW COMMENTS
function appendInitialComments() {

    for (let i = 0; i < comments.length; i++) {
        let comment = comments[i];
        appendSingleComment(comment, false);
    }
}

function appendSingleComment(comment, toTheTop) {

    let li = document.createElement('li');
    li.classList.add('d-flex', 'align-items-center', 'border-bottom', 'pb-2');

    let img = document.createElement('img');
    img.src = comment.user.imgUrl;
    img.classList.add('rounded-circle', 'p-1', 'border');
    img.width = 90;
    img.height = 90;
    img.alt = 'User img';
    li.appendChild(img);

    let group = document.createElement('div');
    group.classList.add('flex-grow-1', 'ms-3', 'text-muted');
    group.innerHTML = comment.user.firstName + ' ' + comment.user.lastName + ', ' + comment.createdAt;
    li.appendChild(group);

    let user = document.createElement('h5');
    user.classList.add('mt-0', 'mb-1');
    user.innerHTML = comment.body;
    group.appendChild(user);

    const ul = document.getElementById('comments');
    if (toTheTop)
        ul.prepend(li);
    else
        ul.appendChild(li);
}

















// LIKE OR DISLIKE
const btnLike = document.getElementById('like-item');
const btnDislike = document.getElementById('dislike-item');

if (item.likedByMe) {
    btnLike.style.display="none";
} else {
    btnDislike.style.display="none";
}

// WHEN PRESSED
btnLike.addEventListener('click', function (e) {
    sendRequestLikeOrDislikeItem();
});

btnDislike.addEventListener('click', function (e) {
    sendRequestLikeOrDislikeItem();
});

function sendRequestLikeOrDislikeItem() {

    fetch('/api/v1/likes/item/' + item.id, {
        method: 'post'
    }).then(response => location.reload())
}
