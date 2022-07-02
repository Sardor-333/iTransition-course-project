$("#collection-img").change(function () {

    let chosenFiles = $(this).files;
    if (chosenFiles.length > 1) {
        console.log('=== CHOSEN MORE THAN 1 FILES ===');
        return null;
    }

    let file = chosenFiles[0];
    let ext = extractExtension(file.name);

    // todo
})

function extractExtension(fileName) {
    if (fileName === null) return null;

    let split = fileName.split(".");
    if (split.length < 2) return null;

    return split[split.length - 1];
}
