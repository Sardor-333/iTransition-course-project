function deleteCollection(collId) {
    fetch('/api/v1/collections/' + collId, {
        method: 'delete'
    }).then(response => location.reload());
}
