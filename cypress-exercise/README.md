# Bài Tập Thực Hành Kiểm Thử E2E Với Cypress

## 📚 Mô Tả Bài Toán

Dự án này thực hiện kiểm thử tự động end-to-end cho trang web **SauceDemo** (https://www.saucedemo.com) - một trang web mẫu mô phỏng cửa hàng trực tuyến.

## 🎯 Mục Tiêu Học Tập

- ✅ Hiểu và thực hành các kịch bản kiểm thử E2E phổ biến
- ✅ Sử dụng Cypress để tự động hóa kiểm thử giao diện web
- ✅ Nắm vững các selector và API của Cypress
- ✅ Viết test cases có cấu trúc rõ ràng và dễ bảo trì

## 🗂️ Cấu Trúc Thư Mục

```
cypress-exercise/
├── cypress/
│   ├── e2e/
│   │   ├── login_spec.cy.js      # Test cases đăng nhập
│   │   ├── cart_spec.cy.js       # Test cases giỏ hàng
│   │   └── checkout_spec.cy.js   # Test cases thanh toán
│   ├── fixtures/                 # Dữ liệu test mẫu
│   ├── support/                  # Commands và helpers
│   └── videos/                   # Video ghi lại test runs
├── node_modules/
├── package.json
├── cypress.config.js
└── README.md
```

## 📦 Yêu Cầu Hệ Thống

- **Node.js**: Phiên bản 14 trở lên
- **npm**: Phiên bản 6 trở lên
- **Visual Studio Code** (hoặc editor khác)
- Kết nối internet để truy cập trang web test

## 🚀 Hướng Dẫn Cài Đặt

### 1. Clone hoặc tải repository

```bash
cd D:\Unit_test\cypress-exercise
```

### 2. Cài đặt dependencies (đã cài xong)

```bash
npm install
```

### 3. Kiểm tra cài đặt

```bash
npx cypress --version
```

## 🧪 Các Kịch Bản Kiểm Thử

### 📝 Login Test (login_spec.cy.js)

**Kịch bản 1: Đăng nhập thành công**
- Nhập username: `standard_user`
- Nhập password: `secret_sauce`
- Xác minh chuyển hướng đến `/inventory.html`

**Kịch bản 2: Đăng nhập thất bại**
- Nhập username không hợp lệ
- Xác minh hiển thị thông báo lỗi

**Bổ sung:**
- Test với trường username rỗng
- Test với trường password rỗng

### 🛒 Cart Test (cart_spec.cy.js)

**Kịch bản 3: Thêm sản phẩm vào giỏ hàng**
- Đăng nhập thành công
- Thêm sản phẩm vào giỏ hàng
- Xác minh badge hiển thị số lượng đúng

**Kịch bản 4: Sắp xếp sản phẩm theo giá**
- Chọn filter "Price (low to high)"
- Xác minh sản phẩm đầu tiên có giá $7.99

**Bài tập bổ sung: Xóa sản phẩm**
- Thêm sản phẩm vào giỏ hàng
- Nhấn nút Remove
- Xác minh giỏ hàng trống

**Bổ sung:**
- Thêm nhiều sản phẩm
- Kiểm tra trang giỏ hàng chi tiết
- Sắp xếp theo tên Z-A

### 💳 Checkout Test (checkout_spec.cy.js)

**Bài tập yêu cầu: Quy trình thanh toán**
- Đăng nhập và thêm sản phẩm
- Vào trang giỏ hàng
- Nhấn Checkout
- Điền thông tin: First Name: `John`, Last Name: `Doe`, Zip Code: `12345`
- Nhấn Continue
- Xác minh chuyển đến `/checkout-step-two.html`

**Bổ sung:**
- Hoàn tất thanh toán và xem thông báo thành công
- Test lỗi khi thiếu thông tin
- Kiểm tra tính tổng tiền
- Hủy thanh toán

## 🎬 Hướng Dẫn Chạy Test

### Chạy test với giao diện (Test Runner)

```bash
npm run test:open
```

Hoặc:

```bash
npx cypress open
```

Sau đó:
1. Chọn "E2E Testing"
2. Chọn trình duyệt (Chrome, Edge, Firefox)
3. Chọn file test để chạy

### Chạy test trong chế độ headless (tự động)

```bash
# Chạy tất cả test cases
npm test

# Hoặc
npx cypress run

# Chạy với Chrome
npm run test:chrome

# Chạy với Firefox
npm run test:firefox

# Chạy và hiển thị trình duyệt
npm run test:headed
```

### Chạy một file test cụ thể

```bash
npx cypress run --spec "cypress/e2e/login_spec.cy.js"
npx cypress run --spec "cypress/e2e/cart_spec.cy.js"
npx cypress run --spec "cypress/e2e/checkout_spec.cy.js"
```

## 📊 Kết Quả Mong Đợi

Khi chạy tất cả test cases, bạn sẽ thấy:

```
┌────────────────────────────────────────────────────────────────────────────┐
│ Tests:        15                                                           │
│ Passing:      15                                                           │
│ Failing:      0                                                            │
│ Pending:      0                                                            │
│ Skipped:      0                                                            │
│ Screenshots:  0                                                            │
│ Video:        true                                                         │
│ Duration:     45 seconds                                                   │
└────────────────────────────────────────────────────────────────────────────┘
```

## 📸 Chụp Màn Hình và Video

Cypress tự động:
- ✅ Chụp màn hình khi test fail (lưu trong `cypress/screenshots/`)
- ✅ Ghi video khi chạy test (lưu trong `cypress/videos/`)

## 📋 Chi Tiết Test Cases

| File Test | Test Cases | Mô Tả |
|-----------|-----------|-------|
| `login_spec.cy.js` | 4 tests | Đăng nhập thành công, thất bại, trường rỗng |
| `cart_spec.cy.js` | 6 tests | Thêm/xóa sản phẩm, sắp xếp, giỏ hàng chi tiết |
| `checkout_spec.cy.js` | 5 tests | Thanh toán, validation, tính tiền, hủy |
| **TỔNG** | **15 tests** | Bao phủ đầy đủ các chức năng chính |

## 🔍 Các Tính Năng Cypress Sử Dụng

- ✅ `cy.visit()` - Truy cập trang web
- ✅ `cy.get()` - Tìm element bằng selector
- ✅ `cy.type()` - Nhập dữ liệu
- ✅ `cy.click()` - Nhấn button
- ✅ `cy.should()` - Kiểm tra assertions
- ✅ `beforeEach()` - Hook chạy trước mỗi test
- ✅ `describe()` và `it()` - Cấu trúc test suite

## 🎓 Kiến Thức Đạt Được

Sau khi hoàn thành bài tập này, bạn sẽ:

1. ✅ Hiểu cách thiết lập và cấu hình Cypress
2. ✅ Viết được các test case E2E cho web app
3. ✅ Sử dụng các selector để tương tác với DOM
4. ✅ Áp dụng assertions để xác minh kết quả
5. ✅ Tổ chức code test có cấu trúc tốt
6. ✅ Debug và xử lý test failures

## 📖 Tài Liệu Tham Khảo

- [Cypress Official Docs](https://docs.cypress.io)
- [Cypress Best Practices](https://docs.cypress.io/guides/references/best-practices)
- [SauceDemo Test Site](https://www.saucedemo.com)
- [Cypress API Reference](https://docs.cypress.io/api/table-of-contents)

## 🐛 Xử Lý Sự Cố

### Test chạy chậm
```bash
# Tăng timeout
cypress run --config defaultCommandTimeout=10000
```

### Không tìm thấy element
- Kiểm tra selector đúng chưa
- Sử dụng `cy.get().should('be.visible')` trước
- Thêm `cy.wait()` nếu cần

### Video/Screenshot không lưu
- Kiểm tra quyền ghi file
- Xem cấu hình trong `cypress.config.js`

## 👨‍💻 Tác Giả

Bài tập thực hành môn Kiểm thử phần mềm - E2E Testing với Cypress

## 📄 License

Dự án này được sử dụng cho mục đích học tập.

---

**Chúc bạn thực hành thành công! 🎉**
