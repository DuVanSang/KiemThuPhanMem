// ***********************************************
// Custom commands for Cypress
// ***********************************************

/**
 * Custom command để đăng nhập nhanh
 * Sử dụng: cy.login('standard_user', 'secret_sauce')
 */
Cypress.Commands.add('login', (username, password) => {
  cy.visit('https://www.saucedemo.com');
  cy.get('#user-name').type(username);
  cy.get('#password').type(password);
  cy.get('#login-button').click();
})

/**
 * Custom command để đăng nhập với user mặc định
 * Sử dụng: cy.loginAsStandardUser()
 */
Cypress.Commands.add('loginAsStandardUser', () => {
  cy.login('standard_user', 'secret_sauce')
})

/**
 * Custom command để thêm sản phẩm vào giỏ hàng theo index
 * Sử dụng: cy.addProductToCart(0) // Thêm sản phẩm đầu tiên
 */
Cypress.Commands.add('addProductToCart', (index = 0) => {
  cy.get('.inventory_item').eq(index).find('.btn_inventory').click()
})

/**
 * Custom command để xác minh số lượng giỏ hàng
 * Sử dụng: cy.verifyCartBadge(3)
 */
Cypress.Commands.add('verifyCartBadge', (count) => {
  if (count === 0) {
    cy.get('.shopping_cart_badge').should('not.exist')
  } else {
    cy.get('.shopping_cart_badge').should('have.text', count.toString())
  }
})

// Example of overwriting existing command
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => {
//   // Add custom logic before visit
//   return originalFn(url, options)
// })
