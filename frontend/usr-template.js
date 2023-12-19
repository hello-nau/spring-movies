

window.onload = async function() {

  let userData = JSON.parse(localStorage.getItem('userData'));
  // let movieString = userData.movieSet;
  let name = document.getElementById("userNm");
  name.innerHTML = userData.userName;
  let movieList = userData.movies;

  console.log(movieList);

  let usrMvLi = document.getElementById("movie-list");
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

     window.open(`/movie.html`, '_blank');
  })

  }) 
}





