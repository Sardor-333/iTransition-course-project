function changeRole(userId) {
    fetch('/api/v1/users/' + userId + '/changeRole', {
        method: 'post'
    }).then(response => location.reload());
}

function enableOrDisableUser(userId) {
    fetch('/api/v1/users/profile/' + userId + '/enableOrDisable', {
        method: 'post'
    }).then(response => location.reload());
}

function deleteUser(userId) {
    fetch('/api/v1/users/' + userId, {
        method: 'delete'
    }).then(response => location.reload());
}
