$(document).ready(function () {

    initiallyAppendComments(); // LIST COMMENTS



});

// -------------------------------------------------------------- //
function initiallyAppendComments() {
    for (let i = 0; i < comments.length; i++) {
        if (i % 2 === 0)
            appendContactToLeft(comments[i]);
        else
            appendContactToRight(comments[i]);
    }
}

function appendContactToLeft(comment) {
    const commentsBlock = document.getElementById('comments');

    let divChatContentLeftside = document.createElement('div');
    divChatContentLeftside.classList.add('chat-content-leftside');
    commentsBlock.appendChild(divChatContentLeftside);

    let divDFlex = document.createElement('div');
    divDFlex.classList.add('d-flex');
    divChatContentLeftside.appendChild(divDFlex);

    let commentOwnerImg = document.createElement('img');
    commentOwnerImg.src = comment.user.imgUrl;
    commentOwnerImg.width = 48;
    commentOwnerImg.height = 48;
    commentOwnerImg.classList.add('rounded-circle');
    commentOwnerImg.alt = 'Comment owner img';
    divDFlex.appendChild(commentOwnerImg);

    let divCommentWrapper = document.createElement('div');
    divCommentWrapper.classList.add('flex-grow-1', 'ms-2');
    divDFlex.appendChild(divCommentWrapper);

    let commentTitle = document.createElement('p');
    commentTitle.classList.add('mb-0', 'chat-time');
    commentTitle.innerHTML = comment.user.firstName + ' ' + comment.user.lastName + ', ' + comment.createdAt;
    divCommentWrapper.appendChild(commentTitle);

    let commentBody = document.createElement('p');
    commentBody.classList.add('chat-left-msg');
    commentBody.innerHTML = comment.body;
    divCommentWrapper.appendChild(commentBody);
}

function appendContactToRight(comment) {
    const commentsBlock = document.getElementById('comments');

    let divChatContentRightSide = document.createElement('div');
    divChatContentRightSide.classList.add('chat-content-rightside');
    commentsBlock.appendChild(divChatContentRightSide);

    let dFlex = document.createElement('div');
    dFlex.classList.add('d-flex');
    divChatContentRightSide.appendChild(dFlex);

    let commentWrapper = document.createElement('div');
    commentWrapper.classList.add('flex-grow-1', 'me-2');
    dFlex.appendChild(commentWrapper);

    let commentTitle = document.createElement('p');
    commentTitle.classList.add('mb-0', 'chat-time', 'text-end');
    commentTitle.innerHTML = comment.user.firstName + ' ' + comment.user.lastName + ', ' + comment.createdAt;
    commentWrapper.appendChild(commentTitle);

    let commentBody = document.createElement('p');
    commentBody.classList.add('chat-right-msg');
    commentBody.innerHTML = comment.body;
    commentWrapper.appendChild(commentBody);

    let commentOwnerImg = document.createElement('img');
    commentOwnerImg.src = comment.user.imgUrl;
    commentOwnerImg.width = 48;
    commentOwnerImg.height = 48;
    commentOwnerImg.classList.add('rounded-circle');
    dFlex.appendChild(commentOwnerImg);
}
// -------------------------------------------------------------- //
