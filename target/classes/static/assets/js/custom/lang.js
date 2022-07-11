let en = document.getElementById('en');
let uz = document.getElementById('uz');

en.addEventListener('click', function () {
    console.log('=== EN ===');
    let currentLocation = window.location.href.split('?')[0];
    // window.location.replace(currentLocation + '?lang=en');

    fetch(currentLocation + '?lang=en', {})
        .then(response => console.log(response.body))
})

uz.addEventListener('click', function () {
    console.log('=== UZ ===');
    let currentLocation = window.location.href.split('?')[0];
    // window.location.replace(currentLocation + '?lang=uz');

    fetch(currentLocation + '?lang=uz', {})
        .then(response => console.log(response.body))
})

/*
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
  </script>
  <script type="text/javascript">
    $(document).ready(function() {
      $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        var currentLocation = window.location.href.split('?')[0];
        if (selectedOption != ''){
          window.location.replace(currentLocation+'?lang=' + selectedOption);
        }
      });
    });
  </script>
* */
