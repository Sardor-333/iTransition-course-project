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
});

// EDIT COLLECTION
// WHEN WINDOW LOADS LOAD ALL TOPICS
window.onload = () => {
    loadTopics();
}

function loadTopics() {
    // EXTRACT CURRENT TOPIC FROM MODEL
    console.log('=== COLLECTION TOPIC ID === ' + selectedTopicId);

    fetch('/api/v1/topics', {
        method: 'get'
    }).then(response => {
        response.json()
            .then(topics => {
                const select = document.getElementById('topicId');

                for (let i = 0; i < topics.length; i++) {
                    const currTopic = topics[i];

                    const opt = document.createElement('option');
                    opt.value = currTopic.id;
                    opt.text = currTopic.name;
                    if (currTopic.id === selectedTopicId) opt.selected = true;

                    select.appendChild(opt);
                }
            })
    })
}
