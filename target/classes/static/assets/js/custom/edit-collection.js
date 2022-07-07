const button = document.getElementById('submit-form-button');

button.addEventListener('click', function () {

    const collectionId = document.getElementById('collectionId').value;
    const collectionName = document.getElementById('collectionName').value;
    const collectionDescription = document.getElementById('collectionDescription').value;
    const topicId = document.getElementById('topicId').value;

    const body = JSON.stringify({
        'name': collectionName,
        'description': collectionDescription,
        'topicId': topicId
    });

    const formData = new FormData();

    formData.append('collection',
        new Blob([body], {
            type: 'application/json'
        }));

    const collectionImg = document.getElementById('collectionImg').files[0];
    formData.append('img', collectionImg);

    fetch('/api/v1/collections/edit/' + collectionId, {
        method: 'post',
        body: formData
    }).then(response => {
        response.json()
            .then(data => {
                location.reload();
            })
    })
})
