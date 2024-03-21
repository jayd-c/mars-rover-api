const buttons = document.querySelectorAll("button[id*='marsApi']")

    buttons.forEach(button => button.addEventListener("click", function () {
        const buttonId = this.id
        const roverId = buttonId.split('marsApi')[1]
        let apiData = document.getElementById('marsApiRoverData')
        apiData.value = roverId
        document.getElementById('frmRoverType').submit();

    }));

    const queryString = window.location.search;
    console.log(queryString);
    // ?product=shirt&color=blue&newuser&size=m
    // We can then parse the query string’s parameters using URLSearchParams:

    const urlParams = new URLSearchParams(queryString);
    // Then we call any of its methods on the result.

    // For example, URLSearchParams.get() will return the first value associated with the given search parameter:

    let marsRoverType = urlParams.get('marsApiRoverData');

    highlightBtnByRoverType(marsRoverType);

    function highlightBtnByRoverType (roverType) {
        if(roverType == '') {
            document.getElementById('marsApiopportunity').classList.remove('btn-secondary');
            document.getElementById('marsApiopportunity').classList.add('btn-primary');
        } else {
             document.getElementById('marsApi'+roverType).classList.remove('btn-secondary');
             document.getElementById('marsApi'+roverType).classList.add('btn-primary');}
        }