let userId = getUrlParameter('userId')
if(userId == null || userId == ''){
 userId = localStorage.getItem('userId')
 }
 if(userId != null && userId != '') {
    localStorage.setItem('userId', userId)
    document.getElementById('userId').value = userId;
 }



let marsApiButtons = document.querySelectorAll("button[id*='marsApi']")

marsApiButtons.forEach( button => button.addEventListener('click', function () {
									const buttonId = this.id
									const roverId = buttonId.split('marsApi')[1]
									let apiData = document.getElementById('marsApiRoverData')
									apiData.value = roverId
									document.getElementById('frmRoverType').submit()
								  }))

function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};

let marsRoverType = getUrlParameter('marsApiRoverData')

highlightBtnByRoverType(marsRoverType)

let marsSol = getUrlParameter('marsSol')
if (marsSol != null && marsSol != '' && marsSol >= 0) {
	document.getElementById('marsSol').value = marsSol
}

function highlightBtnByRoverType (roverType) {
	if (roverType == '')
		roverType = 'Opportunity'

	document.getElementById('marsApi'+roverType).classList.remove('btn-secondary')
	document.getElementById('marsApi'+roverType).classList.add('btn-primary')
}