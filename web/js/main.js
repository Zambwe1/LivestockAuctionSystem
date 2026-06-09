// Livestock Auction Management System - Main JavaScript

/**
 * Validate form inputs
 * @param {string} fieldId - The ID of the form field
 * @param {string} fieldType - The type of validation
 * @returns {boolean} - True if valid, false otherwise
 */
function validateField(fieldId, fieldType) {
    const field = document.getElementById(fieldId);
    const value = field.value.trim();
    
    switch (fieldType) {
        case 'email':
            return /^\S+@\S+\.\S+$/.test(value);
        case 'phone':
            return /^\d{10,}$/.test(value.replace(/\D/g, ''));
        case 'number':
            return !isNaN(value) && value !== '';
        case 'required':
            return value !== '';
        default:
            return true;
    }
}

/**
 * Show alert message
 * @param {string} message - The message to display
 * @param {string} type - The type of alert (success, error, info)
 */
function showAlert(message, type = 'info') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;
    alertDiv.style.marginBottom = '20px';
    
    const container = document.querySelector('.container') || document.body;
    container.insertBefore(alertDiv, container.firstChild);
    
    // Auto-remove alert after 5 seconds
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

/**
 * Format currency
 * @param {number} value - The value to format
 * @returns {string} - Formatted currency string
 */
function formatCurrency(value) {
    return new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'USD'
    }).format(value);
}

/**
 * Format date
 * @param {Date} date - The date to format
 * @returns {string} - Formatted date string
 */
function formatDate(date) {
    return new Intl.DateTimeFormat('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
    }).format(new Date(date));
}

/**
 * Confirm action with user
 * @param {string} message - The confirmation message
 * @returns {boolean} - True if confirmed, false otherwise
 */
function confirmAction(message) {
    return confirm(message);
}

/**
 * Redirect to URL
 * @param {string} url - The URL to redirect to
 */
function redirect(url) {
    window.location.href = url;
}

// DOM Content Loaded Event
document.addEventListener('DOMContentLoaded', function() {
    console.log('Livestock Auction System loaded successfully');
});