import { updateMenuVisibility } from './menuManager.js';
document.addEventListener("DOMContentLoaded", function () {
    let role = localStorage.getItem("role");
    alert("role: " + role);
    updateMenuVisibility(role);
});

document.addEventListener("DOMContentLoaded", function () {
    const registerForm = document.getElementById("registerForm");

    if (registerForm) {
        registerForm.addEventListener("submit", async function (event) {
            event.preventDefault(); // Ngăn form reload trang

            let username = document.getElementById("username").value.trim();
            let name = document.getElementById("name").value.trim();
            let password = document.getElementById("password").value;
            let email = document.getElementById("email").value.trim();

            if (!username || !name || !password || !email) {
                alert("Vui lòng nhập đầy đủ tất cả các trường.");
                return;
            }

            let passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
            let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (!passwordRegex.test(password)) {
                alert("Mật khẩu phải có ít nhất 8 ký tự, bao gồm chữ, số và ký tự đặc biệt.");
                return;
            }

            if (!emailRegex.test(email)) {
                alert("Email không hợp lệ.");
                return;
            }
            try {
                let response = await fetch("http://localhost:8080/api/auth/register", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        username: username,
                        tenNguoiDung: name,
                        email: email,
                        matKhau: password
                    })
                });

                let data = await response.json();

                if (response.ok) {
                    alert("Đăng ký thành công! Vui lòng kiểm tra email để xác thực.");
                } else {
                    alert("Lỗi: " + data.message);
                }
            } catch (error) {
                alert("Lỗi kết nối đến server.");
                console.error("Lỗi:", error.response);
            }
        });
    }

    // Kiểm tra nếu URL chứa mã xác thực, hiển thị alert
    const params = new URLSearchParams(window.location.search);
    if (params.has("token")) {
        alert("Xác thực thành công! Bạn có thể đăng nhập ngay bây giờ.");
    }
});
