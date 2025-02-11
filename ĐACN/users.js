import { updateMenuVisibility } from './menuManager.js';
document.addEventListener("DOMContentLoaded", function () {
    let role = localStorage.getItem("role");
    alert("role: " + role);
    updateMenuVisibility(role);
});

document.addEventListener("DOMContentLoaded", async function () {
    let token = localStorage.getItem("token");
    alert("token " + token);

    if (!token) {
        alert("Bạn chưa đăng nhập!");
        window.location.href = "formDN.html";
        return;
    }

    try {
        let response = await fetch("http://localhost:8080/api/admin/users", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            let users = await response.json();
            let tableBody = document.getElementById("userTableBody");

            users.forEach(user => {
                let row = document.createElement("tr");
                row.innerHTML = `
                    <td>${user.idNguoiDung}</td>
                    <td>${user.tenNguoiDung}</td>
                    <td>${user.email}</td>
                    <td>${user.vaiTro}</td>
                `;
                tableBody.appendChild(row);
            });
        } else {
            alert("Bạn không có quyền truy cập hoặc phiên đăng nhập đã hết hạn!");
            window.location.href = "formDN.html";
        }
    } catch (error) {
        alert("Lỗi kết nối đến server!");
        console.error("Lỗi:", error);
    }
});
