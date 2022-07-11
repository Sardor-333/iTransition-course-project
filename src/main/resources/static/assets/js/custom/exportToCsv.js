$("#btn-export-to-csv").click(function () {
    console.log('BTN CLICKED')
    collectionsToCsv();
})

function collectionsToCsv() {
    let csv_data = [];

    const headers = ['Name', 'Items count', 'Author', 'Topic'];
    csv_data.push(headers);

    const collectionRows = document.getElementsByClassName('single-collection');
    for (let i = 0; i < collectionRows.length; i++) {
        const collectionRow = collectionRows[i];

        let name = collectionRow.getElementsByClassName('single-collection-name')[0].innerHTML;
        let itemsCount = collectionRow.getElementsByClassName('single-collection-items-count')[0].innerHTML;
        let author = collectionRow.getElementsByClassName('single-collection-author')[0].innerHTML;
        let topic = collectionRow.getElementsByClassName('single-collection-topic')[0].innerHTML;

        let csv_row = [name, itemsCount, author, topic];
        csv_data.push(csv_row.join(","));
    }

    csv_data = csv_data.join('\n');
    console.log(JSON.stringify(csv_data));

    downloadCsvFile(csv_data);
}

function downloadCsvFile(csv_data) {
    let csvFile = new Blob([csv_data], {type: "text/csv"});

    let tempLink = document.createElement('a');
    tempLink.download = "collections.csv";
    tempLink.href = window.URL.createObjectURL(csvFile);

    tempLink.style.display = "none";
    let pageContentNode = document.getElementsByClassName('page-content')[0];
    pageContentNode.appendChild(tempLink);

    tempLink.click();
    pageContentNode.removeChild(tempLink);
}
