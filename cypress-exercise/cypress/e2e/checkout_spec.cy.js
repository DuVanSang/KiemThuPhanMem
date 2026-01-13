/**
 * Bài kiểm thử cho chức năng thanh toán
 * Trang web: https://www.saucedemo.com
 */

describe('Checkout Test', () => {
  
  // Hook: Chạy trước mỗi test case
  beforeEach(() => {
    // Đăng nhập
    cy.visit('https://www.saucedemo.com');
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();
    cy.url().should('include', '/inventory.html');
  });

  // Bài tập yêu cầu 3: Kiểm tra quy trình thanh toán
  it('Should complete checkout process successfully', () => {
    // Bước 1: Thêm sản phẩm vào giỏ hàng
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_badge').should('have.text', '1');
    
    // Bước 2: Vào trang giỏ hàng
    cy.get('.shopping_cart_link').click();
    cy.url().should('include', '/cart.html');
    
    // Bước 3: Nhấn nút Checkout
    cy.get('#checkout').click();
    cy.url().should('include', '/checkout-step-one.html');
    
    // Bước 4: Điền thông tin thanh toán
    cy.get('#first-name').type('John');
    cy.get('#last-name').type('Doe');
    cy.get('#postal-code').type('12345');
    
    // Bước 5: Nhấn nút Continue
    cy.get('#continue').click();
    
    // Bước 6: Xác minh chuyển đến trang xác nhận thanh toán
    cy.url().should('include', '/checkout-step-two.html');
    
    // Xác minh tiêu đề trang
    cy.get('.title').should('have.text', 'Checkout: Overview');
    
    // Xác minh thông tin sản phẩm hiển thị
    cy.get('.cart_item').should('have.length', 1);
  });

  // Test bổ sung: Hoàn tất thanh toán
  it('Should finish checkout and show success message', () => {
    // Thêm sản phẩm và đi đến checkout
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_link').click();
    cy.get('#checkout').click();
    
    // Điền thông tin
    cy.get('#first-name').type('Jane');
    cy.get('#last-name').type('Smith');
    cy.get('#postal-code').type('54321');
    cy.get('#continue').click();
    
    // Nhấn Finish
    cy.get('#finish').click();
    
    // Xác minh trang hoàn tất
    cy.url().should('include', '/checkout-complete.html');
    cy.get('.complete-header').should('have.text', 'Thank you for your order!');
  });

  // Test bổ sung: Kiểm tra lỗi khi không điền thông tin
  it('Should show error when first name is missing', () => {
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_link').click();
    cy.get('#checkout').click();
    
    // Chỉ điền last name và postal code
    cy.get('#last-name').type('Doe');
    cy.get('#postal-code').type('12345');
    cy.get('#continue').click();
    
    // Xác minh thông báo lỗi
    cy.get('[data-test="error"]').should('contain', 'First Name is required');
  });

  // Test bổ sung: Kiểm tra tính tổng tiền
  it('Should calculate total price correctly', () => {
    // Thêm 2 sản phẩm
    cy.get('.inventory_item').eq(0).find('.btn_inventory').click();
    cy.get('.inventory_item').eq(1).find('.btn_inventory').click();
    
    // Đi đến checkout
    cy.get('.shopping_cart_link').click();
    cy.get('#checkout').click();
    
    // Điền thông tin
    cy.get('#first-name').type('Test');
    cy.get('#last-name').type('User');
    cy.get('#postal-code').type('11111');
    cy.get('#continue').click();
    
    // Xác minh hiển thị tổng tiền
    cy.get('.summary_subtotal_label').should('be.visible');
    cy.get('.summary_tax_label').should('be.visible');
    cy.get('.summary_total_label').should('be.visible');
  });

  // Test bổ sung: Hủy thanh toán
  it('Should cancel checkout and return to cart', () => {
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_link').click();
    cy.get('#checkout').click();
    
    // Nhấn nút Cancel
    cy.get('#cancel').click();
    
    // Xác minh quay lại trang giỏ hàng
    cy.url().should('include', '/cart.html');
  });
});
