

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
    li.appendChild(document.createTextNode(movie.id));

    li.appendChild(document.createTextNode(movie.description));

    usrMvLi.appendChild(li);
  }
  
  }

