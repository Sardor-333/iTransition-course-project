const en = document.getElementById('en');
en.addEventListener('click', function () {
    changeLang('en');
});

const uz = document.getElementById('uz');
uz.addEventListener('click', function () {
    changeLang('uz');
})

function changeLang(langCode) {
    console.log('=== CHANGE LANG PRESSED ===');
    let href = window.location.href;
    console.log('=== CURRENT BROWSER HREF === ' + href);

    fetch(href + '?lang=' + langCode, {
        method: 'GET'
    }).then(r => console.log(response.body));
}
