function deleteItem(itemId) {
    fetch('/api/v1/items/' + itemId, {
        method: 'delete'
    }) .then(response => location.reload());
}
