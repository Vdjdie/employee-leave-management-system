
const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", function(event) {

    // Page refresh hou naye
    event.preventDefault();

    // Username gheto
    const username =
        document.getElementById("username").value;

    // Password gheto
    const password =
        document.getElementById("password").value;

    // Backend login API la request pathavto
    fetch("/login", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            username: username,

            password: password

        })

    })

    .then(response => response.json())

    .then(user => {

        // Login failed
        if (!user.success) {

            document.getElementById(
                "loginMessage"
            ).innerText =
                "Invalid username or password";

            return;
        }

        // User information localStorage madhe save karto
        localStorage.setItem(
            "userId",
            user.id
        );

        localStorage.setItem(
            "employeeId",
            user.employeeId
        );

        localStorage.setItem(
            "username",
            user.username
        );

        localStorage.setItem(
            "role",
            user.role
        );

        localStorage.setItem(
            "name",
            user.name
        );

        localStorage.setItem(
            "email",
            user.email
        );

        localStorage.setItem(
            "department",
            user.department
        );

        // Role based dashboard open karto

        if (user.role === "EMPLOYEE") {

            window.location.href =
                "employee-dashboard.html";

        } else if (user.role === "MANAGER") {

            window.location.href =
                "manager-dashboard.html";

        } else {

            document.getElementById(
                "loginMessage"
            ).innerText =
                "Invalid user role";
        }

    })

    .catch(error => {

        console.error(
            "Login Error:",
            error
        );

        document.getElementById(
            "loginMessage"
        ).innerText =
            "Unable to connect to server";

    });

});

