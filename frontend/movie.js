window.onload = async function(evt) {
  let userData = JSON.parse(localStorage.getItem('userData'));
evt.preventDefault();

  let myMovies = localStorage['userData'];
  console.log("userdata is: ", userData);

  populateMovies(userData);
let params = new URLSearchParams(window.location.search);
let userName = params.get('userName');

let backLink = document.getElementById('back-link');
backLink.href = `/user-template.html?userName=${userName}`;

   
}

function populateMovies(movieData) {
  let table = document.getElementById("movie-table");
  console.log("Getting the movie info...");
    let row = document.createElement('tr');
    let idCell = document.createElement("td");
    idCell.appendChild(document.createTextNode(movieData.id));
    row.appendChild(idCell);

    let descriptionCell = document.createElement("td");
    descriptionCell.appendChild(document.createTextNode(movieData.description));

    row.appendChild(descriptionCell);
    table.appendChild(row);

  

  }


