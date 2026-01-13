/**
 * Bài kiểm thử cho chức năng giỏ hàng
 * Trang web: https://www.saucedemo.com
 */

describe('Cart Test', () => {
  
  // Hook: Chạy trước mỗi test case - Đăng nhập
  beforeEach(() => {
    cy.visit('https://www.saucedemo.com');
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();
    
    // Đảm bảo đã vào trang inventory
    cy.url().should('include', '/inventory.html');
  });

  // Kịch bản 3: Kiểm tra thêm sản phẩm vào giỏ hàng
  it('Should add a product to the cart', () => {
    // Nhấn nút "Add to cart" của sản phẩm đầu tiên
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    
    // Xác minh số lượng sản phẩm trong giỏ hàng là 1
    cy.get('.shopping_cart_badge').should('have.text', '1');
    
    // Xác minh nút đã đổi thành "Remove"
    cy.get('.inventory_item').first().find('.btn_inventory').should('have.text', 'Remove');
  });

  // Kịch bản 4: Kiểm tra sắp xếp sản phẩm theo giá thấp đến cao
  it('Should sort products by price low to high', () => {
    // Chọn bộ lọc "Price (low to high)"
    cy.get('.product_sort_container').select('lohi');
    
    // Xác minh sản phẩm đầu tiên có giá thấp nhất
    cy.get('.inventory_item_price').first().should('have.text', '$7.99');
  });

  // Bài tập bổ sung 1: Kiểm tra xóa sản phẩm khỏi giỏ hàng
  it('Should remove a product from the cart', () => {
    // Thêm sản phẩm vào giỏ hàng
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_badge').should('have.text', '1');
    
    // Nhấn nút "Remove"
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    
    // Xác minh giỏ hàng trống (badge không hiển thị)
    cy.get('.shopping_cart_badge').should('not.exist');
    
    // Xác minh nút đã đổi lại thành "Add to cart"
    cy.get('.inventory_item').first().find('.btn_inventory').should('have.text', 'Add to cart');
  });

  // Test bổ sung: Thêm nhiều sản phẩm vào giỏ hàng
  it('Should add multiple products to the cart', () => {
    // Thêm 3 sản phẩm vào giỏ hàng
    cy.get('.inventory_item').eq(0).find('.btn_inventory').click();
    cy.get('.inventory_item').eq(1).find('.btn_inventory').click();
    cy.get('.inventory_item').eq(2).find('.btn_inventory').click();
    
    // Xác minh số lượng sản phẩm trong giỏ hàng là 3
    cy.get('.shopping_cart_badge').should('have.text', '3');
  });

  // Test bổ sung: Kiểm tra giỏ hàng chi tiết
  it('Should display products in cart page', () => {
    // Thêm 2 sản phẩm
    cy.get('.inventory_item').eq(0).find('.btn_inventory').click();
    cy.get('.inventory_item').eq(1).find('.btn_inventory').click();
    
    // Vào trang giỏ hàng
    cy.get('.shopping_cart_link').click();
    
    // Xác minh URL
    cy.url().should('include', '/cart.html');
    
    // Xác minh có 2 sản phẩm trong giỏ hàng
    cy.get('.cart_item').should('have.length', 2);
  });

  // Test bổ sung: Sắp xếp theo tên Z-A
  it('Should sort products by name Z to A', () => {
    cy.get('.product_sort_container').select('za');
    
    // Kiểm tra sản phẩm đầu tiên là Test.allTheThings() T-Shirt (Red)
    cy.get('.inventory_item_name').first().should('contain', 'Test.allTheThings()');
  });
});
