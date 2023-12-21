const userList = document.querySelector("#user-list");


window.onload = async function(evt) {
  evt.preventDefault();
  fetchUsers();
} 


function populateUsers(userData) {
  let usrLi = document.getElementById("usr-li");

  for (let user of userData) {
    let li = document.createElement("li");
    li.appendChild(document.createTextNode(user.userName));

    let deleteButton = document.createElement("button");
    deleteButton.textContent = "Delete";
    deleteButton.classList.add("delete-btn");

    li.onclick = function() {
      openUser(user.userName);
    }

    deleteButton.onclick = function() {
      deleteUser(user.userName);
    };

    li.appendChild(deleteButton);

    usrLi.appendChild(li);
  }
  userList.appendChild(usrLi);
}


function createUser() {
  let userNameInput = document.getElementById("username");
  const newUserName = userNameInput.value;

  if(!newUserName.trim()) {
    alert("Please enter a valid user name");
    return;
  }
  axios.post("http://localhost:8080/users", {userName: newUserName})
  .then((res) => {
    console.log("User created successfully: ", res.data);
    populateUsers([res.data]);
    userNameInput.value = "";
  })
  .catch((error) => {
    console.error("Error creating user: ", error);
  });
}


function deleteUser(userName) {
if (confirm("Are you sure you want to delete this user?")) {
  axios.delete(`http://localhost:8080/users/${userName}`)
  .then((res) => {
    console.log("User deleted: ", res.data);
    fetchUsers();
  })
  .catch((error) => {
    console.error("Error deleting a user: ", error);
  });
}
}


function openUser(userName) {
  console.log("openUser function called");
 const userTemplateUrl = "/user-template.html";

 fetch(userTemplateUrl)
  .then(response => response.text())
  .then(templateHtml => {

  axios.get(`http://localhost:8080/users/${userName}`)
  .then(response => {
    let userData = response.data;


    let userContent = Mustache.render(templateHtml, userData);
    let div = document.createElement("div");
    div.classList.add("usr-container");

    div.innerHTML = userContent;

     window.open(`/user-template.html?userName=${userName}`, '_blank');
  })
  .catch(error => {
    console.error("Error fetching user data", error);
  })
  .catch(error => {
  console.error("Error loading template:", error);
})

  }) 
}



function addMovieToUser(userName) {}

function fetchUsers() {
  console.log("Getting users...");
  axios.get("http://localhost:8080/users").then((res) => {
    console.log(res.data);
    let usrLi = document.getElementById("usr-li");
    usrLi.innerHTML = '';
    populateUsers(res.data);
  })
}