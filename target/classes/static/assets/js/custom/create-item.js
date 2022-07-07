$("#btn-save-item").on('click', function () {
    sendCreateItemRequest();
})

function extractItemData() {
    const itemName = $("#item-name").val();
    console.log('Item name: ' + itemName);

    const itemTags = $("#item-tags").val();
    console.log('Item tags: ' + itemTags);

    const valueCreateDtoList = [];
    $("#item-values > div").each(function () {
        let fieldName = $(this).find('input').first().attr('name');
        console.log(fieldName);
        let fieldValue = $(this).find('input').first().val();
        console.log(fieldValue);

        valueCreateDtoList.push({
            'fieldName': fieldName,
            'fieldValue': fieldValue
        });
    });

    const item = {};
    item.name = itemName; // item name
    item.tagIdList = itemTags; // tags
    item.valueCreateDtoList = valueCreateDtoList;

    return item;
}

function sendCreateItemRequest() {
    const reqBody = extractItemData();
    console.log(JSON.stringify(reqBody));

    fetch('/api/v1/items/create/' + collectionId, {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(reqBody)
    }).then(response => location.reload())
}
