function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("role");

    alert("Bạn đã đăng xuất thành công!");

    window.location.href = "home.html";
}

document.addEventListener("DOMContentLoaded", function () {
    let logoutButton = document.getElementById("menuDangXuat");

    if (logoutButton) {
        logoutButton.addEventListener("click", function (event) {
            event.preventDefault(); // Ngăn chặn hành vi mặc định của thẻ `<a>`
            logout(); // Gọi hàm logout chung
        });
    }
});
