

var userName = new URL(location.href).searchParams.get("userName");
window.onload = async function() {

  fetchUserList();
    console.log("Getting Movies...");
  axios.get("http://localhost:8080/movies").then((res) => {
    console.log(res.data);
    populateMovies(res.data);
  })

  }

function openMovie(userName, id) {

console.log("openMovie is called");
const userTemplateUrl = "/movie.html";

fetch(userTemplateUrl) 
  .then(response => response.text())
  .then(templateHtml => {
         axios.get(`http://localhost:8080/users/${userName}/movieList/${id}`)
  .then(response => {
    let userData = response.data;

    localStorage.setItem('userData', JSON.stringify(userData));

    let userContent = Mustache.render(templateHtml, userData);
    let div = document.createElement("div");
    div.classList.add("usr-container");

    div.innerHTML = userContent;

     window.open(`/movie.html?username=${userName}&id=${id}`, '_blank');
  })

  }) 
}



const movieTable = document.querySelector("#movies");


function populateMovies(movieData) {
  let table = document.getElementById("movie-table");

  for (let movie of movieData) {
    let row = document.createElement('tr');
    let idCell = document.createElement("td");

    idCell.appendChild(document.createTextNode(movie.id));

    row.appendChild(idCell);

    let descriptionCell = document.createElement("td");
    descriptionCell.appendChild(document.createTextNode(movie.description));

     row.onclick =  function() {
      addMovie(idCell.innerHTML, descriptionCell.innerHTML);
    }

    row.appendChild(descriptionCell);
    table.appendChild(row);
  }
  movieTable.appendChild(table);

  }


  function addMovie(movieId, movieDescription) {
    console.log(movieId);
    console.log(movieDescription);

    let movie = {
      id: movieId,
      description: movieDescription
    };


  axios.put(`http://localhost:8080/users/${userName}`, movie)
  .then(response => {
    console.log(response.data);
    fetchUserList();
  })
  .catch(error => {
    console.error(error);
  });
  

  }

function fetchUserList() {

 axios.get(`http://localhost:8080/users/${userName}`)
  .then(response => {
    let userData = response.data;
    let name = document.getElementById("userNm");
  name.innerHTML = userData.userName;
  let movieList = userData.movies;

  console.log(movieList);

  let usrMvLi = document.getElementById("movie-list");
 usrMvLi.innerHTML = '';

  for (let movie of movieList) {
    let li = document.createElement("li");
      let span = document.createElement("span");
    span.textContent = movie.id;


    span.addEventListener("click", function() {
      openMovie(userData.userName, movie.id);
    });

    li.appendChild(span);
    li.appendChild(document.createTextNode(": "));
    li.appendChild(document.createTextNode(movie.description));

    usrMvLi.appendChild(li);
  }
    
  })
  

}


