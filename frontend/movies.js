const movieTable = document.querySelector("#movies");


window.onload = async function(evt) {
evt.preventDefault();
  console.log("Getting Movies...");
  axios.get("http://localhost:8080/movies").then((res) => {
    console.log(res.data);
    populateMovies(res.data);
  })
}

function populateMovies(movieData) {
  let table = document.getElementById("movie-table");

  for (let movie of movieData) {
    let row = document.createElement('tr');

    let idCell = document.createElement("td");
    idCell.appendChild(document.createTextNode(movie.id));
    row.appendChild(idCell);

    let descriptionCell = document.createElement("td");
    descriptionCell.appendChild(document.createTextNode(movie.description));

    row.appendChild(descriptionCell);
    table.appendChild(row);

  }

  movieTable.appendChild(table);

  }

