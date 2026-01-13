/**
 * Bài kiểm thử cho chức năng đăng nhập
 * Trang web: https://www.saucedemo.com
 */

describe('Login Test', () => {
  
  // Kịch bản 1: Kiểm tra đăng nhập thành công
  it('Should login successfully with valid credentials', () => {
    // Truy cập trang web
    cy.visit('https://www.saucedemo.com');
    
    // Nhập tên người dùng
    cy.get('#user-name').type('standard_user');
    
    // Nhập mật khẩu
    cy.get('#password').type('secret_sauce');
    
    // Nhấn nút Login
    cy.get('#login-button').click();
    
    // Xác minh chuyển hướng đến trang inventory
    cy.url().should('include', '/inventory.html');
    
    // Xác minh tiêu đề trang hiển thị
    cy.get('.title').should('have.text', 'Products');
  });

  // Kịch bản 2: Kiểm tra đăng nhập thất bại
  it('Should show error message with invalid credentials', () => {
    // Truy cập trang web
    cy.visit('https://www.saucedemo.com');
    
    // Nhập tên người dùng không hợp lệ
    cy.get('#user-name').type('invalid_user');
    
    // Nhập mật khẩu sai
    cy.get('#password').type('wrong_password');
    
    // Nhấn nút Login
    cy.get('#login-button').click();
    
    // Xác minh thông báo lỗi xuất hiện
    cy.get('.error-message-container').should('be.visible');
    cy.get('[data-test="error"]').should('contain', 'Username and password do not match');
  });

  // Kịch bản bổ sung: Kiểm tra đăng nhập với trường rỗng
  it('Should show error message when username is empty', () => {
    cy.visit('https://www.saucedemo.com');
    
    // Chỉ nhập mật khẩu, bỏ trống username
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();
    
    // Xác minh thông báo lỗi
    cy.get('[data-test="error"]').should('contain', 'Username is required');
  });

  it('Should show error message when password is empty', () => {
    cy.visit('https://www.saucedemo.com');
    
    // Chỉ nhập username, bỏ trống password
    cy.get('#user-name').type('standard_user');
    cy.get('#login-button').click();
    
    // Xác minh thông báo lỗi
    cy.get('[data-test="error"]').should('contain', 'Password is required');
  });
});
