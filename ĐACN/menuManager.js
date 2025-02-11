// menuManager.js

/**
 * Cập nhật hiển thị menu theo vai trò của người dùng
 * @param {string} role - Vai trò của người dùng (QUAN_TRI_VIEN, BIEN_TAP_VIEN, NGUOI_DUNG)
 */
function updateMenuVisibility(role) {
    let menus = {
        menuUsers: ["QUAN_TRI_VIEN"],
        menuPost: ["QUAN_TRI_VIEN", "BIEN_TAP_VIEN"],
        menuDangXuat: ["QUAN_TRI_VIEN", "BIEN_TAP_VIEN", "NGUOI_DUNG"],
        menuDangNhap: [null],
        menuDangKy: [null]
    };

    for (let menuId in menus) {
        let element = document.getElementById(menuId);
        if (element) {
            element.style.display = menus[menuId].includes(role) ? "block" : "none";
        }
    }
}

// Xuất hàm để dùng ở các file khác
export { updateMenuVisibility };
