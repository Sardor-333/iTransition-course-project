// CUSTOM FIELD CREATE
const addFieldButton = document.getElementById('add-field');
const customFields = document.getElementById('custom-fields');

addFieldButton.addEventListener('click', function (e) {
    e.preventDefault();
    addCustomField(customFields);
});

function addCustomField(customFields) {
    // CREATE ROW
    const row = document.createElement('div');
    row.classList.add('row', 'custom-list__item');
    customFields.appendChild(row);
    // -----------------------------------------------------


    // -----------------------------------------------------
    // FIELD NAME
    const col1 = document.createElement('div');
    col1.classList.add('col');
    row.appendChild(col1);

    const fieldName = document.createElement('input');
    fieldName.classList.add('form-control');
    fieldName.type = "text";
    fieldName.placeholder = "Enter field name";
    fieldName.required = true;
    col1.appendChild(fieldName);
    // -----------------------------------------------------


    // -----------------------------------------------------
    // CREATE FIELD DATA TYPE
    const col2 = document.createElement('div');
    col2.classList.add('col');
    row.appendChild(col2);

    const fieldTypes = document.createElement('select');
    fieldTypes.classList.add('form-select');
    col2.appendChild(fieldTypes);

    fieldTypes.add(new Option("Text", "text"));
    fieldTypes.add(new Option("Long text", "textarea"));
    fieldTypes.add(new Option("Number", "number"));
    fieldTypes.add(new Option("Date", "date"));
    fieldTypes.add(new Option("Checkbox", "checkbox"));
    // -----------------------------------------------------

    // -----------------------------------------------------
    // BUTTON DELETE FIELD
    const col3 = document.createElement('div');
    col3.classList.add('col');
    row.appendChild(col3);

    const deleteButton = document.createElement('a');
    deleteButton.classList.add('btn', 'btn-danger');
    deleteButton.text = "Delete";
    col3.appendChild(deleteButton);

    deleteButton.addEventListener('click', function () {
        row.remove();
        console.log('=== BTN REMOVED ===');
    });
    // -----------------------------------------------------
}

// SUBMIT FORM
const submitFormButton = document.getElementById('submit-form-button');

submitFormButton.addEventListener('click', function () {
    const customFields = document.getElementsByClassName('custom-list__item');
    const fieldDtoList = [];

    for (let i = 0; i < customFields.length; i++) {
        let fieldName = customFields[i].childNodes[0].childNodes[0].value;
        let fieldType = customFields[i].childNodes[1].childNodes[0].value;

        if (fieldName !== null && fieldType !== null) {
            fieldDtoList.push({
                'name': fieldName,
                'type': fieldType
            });
        }
    }

    const name = document.getElementById('collection-name').value;
    const description = document.getElementById('collection-description').value;
    const topicId = document.getElementById('topic-id').value;

    const body = JSON.stringify({
        'name': name,
        'description': description,
        'topicId': topicId,
        'fieldDtoList': fieldDtoList
    });

    const formData = new FormData();

    formData.append('collection',
        new Blob([body], {
            type: "application/json"
        }));

    const collectionPhoto = document.getElementById('collection-photo').files[0];
    formData.append('photo', collectionPhoto);

    if (fieldDtoList.length > 0) {
        fetch('/api/v1/collections/create', {
            method: 'post',
            body: formData
        }).then(response => {
            response.json()
                .then(data => {
                    console.log(JSON.stringify(data));
                    const alert = document.createElement('p');
                    alert.classList.add('alert', data.success ? "alert-success" : "alert-danger");
                    alert.textContent = data.message;

                    const responseBlock = document.getElementById('response');
                    responseBlock.appendChild(alert);
                    setTimeout(function () {
                        alert.remove();
                    }, 5000)
                })
        });
    }
});

// LOAD TOPICS
window.onload = () => {
    loadTopics();
}

function loadTopics() {
    fetch('/api/v1/topics', {
        method: 'get'
    }).then(response => {
        response.json()
            .then(data => {
                const select = document.getElementById('topic-id');

                for (let i = 0; i < data.length; i++) {
                    const currTopic = data[i];

                    const opt = document.createElement('option');
                    opt.value = currTopic.id;
                    opt.text = currTopic.name;
                    select.appendChild(opt);
                }
            })
    })
}
