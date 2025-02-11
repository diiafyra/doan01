import { updateMenuVisibility } from './menuManager.js';
document.addEventListener("DOMContentLoaded", function () {
    let role = localStorage.getItem("role");
    alert("role: " + role);
    updateMenuVisibility(role);
});

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("loginForm").addEventListener("submit", async function (event) {
        event.preventDefault();

        let email = document.getElementById("email").value.trim();
        let password = document.getElementById("password").value;

        if (!email || !password) {
            alert("Vui lòng nhập đầy đủ email và mật khẩu.");
            return;
        }

        let requestData = {
            usernameOrEmail: email,
            password: password
        };

        let data = null;

        try {
            let response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(requestData)
            });
    

            if (!response.ok) {
                let contentType = response.headers.get("content-type");
            
                if (contentType && contentType.includes("application/json")) {
                    let errorData = await response.json();
                    throw new Error(errorData.message);
                } else {
                    let errorText = await response.text();
                    throw new Error(errorText || "Lỗi không xác định!");
                }
            }
            

            let data = await response.json();
        
            alert(`Đăng nhập thành công! Vai trò của bạn là: ${data.role}`);
        
            localStorage.setItem("token", data.token);
            localStorage.setItem("role", data.role);
        
            window.location.href = "home.html";
        
        } catch (error) {
            alert(error.message);
        }
        


    });
});
