import { updateMenuVisibility } from './menuManager.js';
document.addEventListener("DOMContentLoaded", function () {
    let role = localStorage.getItem("role");
    alert("role: " + role);
    updateMenuVisibility(role);
});

