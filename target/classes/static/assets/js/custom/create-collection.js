// CUSTOM FIELD CREATE
const addFieldButton = document.getElementById('add-field');
const customFields = document.getElementById('custom-fields');

addFieldButton.addEventListener('click', function (e) {
    e.preventDefault();
    addCustomField(customFields);
});

function addCustomField(customFields) {

    // Create row
    let row_g_3 = document.createElement('div');
    row_g_3.classList.add('row', 'g-3');
    customFields.appendChild(row_g_3);


    // Create column ([ Field Name ])
    let colFieldName = document.createElement('div');
    colFieldName.classList.add('col-md-6');
    row_g_3.appendChild(colFieldName);

    let labelFieldName = document.createElement('label');
    labelFieldName.classList.add('form-label');
    labelFieldName.innerHTML = 'Field Name';
    colFieldName.appendChild(labelFieldName);

    let inputFieldName = document.createElement('input');
    inputFieldName.type = "text";
    inputFieldName.classList.add('form-control');
    inputFieldName.placeholder = "Field Name";
    colFieldName.appendChild(inputFieldName);


    // Create column ([ Field Type ])
    let colFieldType = document.createElement('div');
    colFieldType.classList.add('col-md-6');
    row_g_3.appendChild(colFieldType);

    let labelFieldType = document.createElement('label');
    labelFieldType.classList.add('form-label');
    labelFieldType.innerHTML = "Field Type";
    colFieldType.appendChild(labelFieldType);

    let selectFieldType = document.createElement('select');
    selectFieldType.classList.add('form-select');

    selectFieldType.add(new Option('Text', 'text'));
    selectFieldType.add(new Option('Long text', 'textarea'));
    selectFieldType.add(new Option('Number', 'number'));
    selectFieldType.add(new Option('Date', 'date'));
    selectFieldType.add(new Option('Checkbox', 'checkbox'));
    selectFieldType.add(new Option('File', 'file'));

    colFieldType.appendChild(selectFieldType);

    let colDelField = document.createElement('div');
    colDelField.classList.add('col-12');
    row_g_3.appendChild(colDelField);

    let inner = document.createElement('div');
    inner.classList.add('d-grid');
    colDelField.appendChild(inner);

    let btnDelete = document.createElement('button');
    btnDelete.type = "button";
    btnDelete.classList.add('btn', 'btn-danger');
    btnDelete.innerHTML = "Delete";
    inner.appendChild(btnDelete);

    btnDelete.addEventListener('click', function () {
        row_g_3.remove();
    })
}


// ------------------------------------------------------------ //


// SUBMIT FORM
const submitFormButton = document.getElementById('submit-form-button');
submitFormButton.addEventListener('click', function () {

    const fieldDtoList = [];

    const customFieldsById = document.getElementById('custom-fields');
    const rows = customFieldsById.getElementsByClassName('row');
    for (let i = 0; i < rows.length; i++) {
        let fieldName = rows[i].childNodes[0].childNodes[1].value;
        let fieldType = rows[i].childNodes[1].childNodes[1].value;

        console.log('Field Name: ' + fieldName);
        console.log('Field Type: ' + fieldType);

        if (fieldName !== null && fieldType !== null) {
            fieldDtoList.push({
                'name': fieldName,
                'type': fieldType
            });
        }
    }

    const name = document.getElementById('collection-name').value;
    const description = document.getElementById('collection-description').value;
    const topicId = document.getElementById('select-topic').value;

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

    const collectionPhoto = document.getElementById('collection-img').files[0];
    formData.append('photo', collectionPhoto);

    if (fieldDtoList.length > 0) {
        fetch('/api/v1/collections/create', {
            method: 'post',
            body: formData
        }).then(response => location.reload());
    }
});
// ------------------------------------------------------------ //


// LOAD TOPICS
window.onload = () => {
    getTopics();
}

function getTopics() {
    fetch('/api/v1/topics', {
        method: 'get'
    }).then(response => {
        response.json()
            .then(data => {
                const select = document.getElementById('select-topic');

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
