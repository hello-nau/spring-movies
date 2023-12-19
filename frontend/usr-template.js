

window.onload = async function() {

  let userData = JSON.parse(localStorage.getItem('userData'));
  let movieString = userData.movieSet;
  let movieList = JSON.parse(movieString);

  console.log(movieList);

  let usrMvLi = document.getElementById("movie-list");
  for (let movie of movieList) {
    let li = document.createElement("li");
    li.appendChild(document.createTextNode(movieList.id));

    li.appendChild(document.createTextNode(movieList.description));

    usrMvLi.appendChild(li);
  }
  
  }

