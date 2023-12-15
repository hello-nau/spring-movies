const userList = document.querySelector("#user-list");

window.onload = async function(evt) {
  evt.preventDefault();
  console.log("Getting users");
  axios.get("http://localhost:8080/users").then((res) => {
    console.log(res.data);
    populateUsers(res.data);
  })
} 
function populateUsers(userData) {
  let usrLi = document.getElementById("usr-li");

  for (let user of userData) {
    let li = document.createElement("li");
    li.appendChild(document.createTextNode(user.userName));
    usrLi.appendChild(li);
    // li.appendChild(document.createTextNode(user.movieSet));
    // usrLi.appendChild(li);

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
function deleteUser() {
  
}