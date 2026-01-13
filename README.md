# 👁️ Báo Cáo Thực Hành: Kiểm Thử Thị Giác (Visual Testing)

![Status](https://img.shields.io/badge/Status-Completed-success) ![Course](https://img.shields.io/badge/Course-Software%20Testing-blue)

## 1. Tổng quan
Repository này lưu trữ kết quả thực hành bài tập môn **Kiểm thử phần mềm**, tập trung vào kỹ năng kiểm thử giao diện (UI/UX) thông qua nền tảng [CantUnsee](https://cantunsee.space/).

## 2. Mục đích
* **Rèn luyện "mắt nghề nghiệp":** Phát hiện các lỗi giao diện nhỏ nhất (padding, typography, contrast, alignment...).
* **Tư duy thiết kế:** Nâng cao nhận thức về tính nhất quán (consistency) và trải nghiệm người dùng trong phát triển phần mềm.

## 3. Các bước thực hiện
1.  Truy cập nền tảng [CantUnsee](https://cantunsee.space/).
2.  Hoàn thành 3 cấp độ thử thách: **Easy, Medium, Hard**.
3.  Phân tích sự khác biệt giữa hai phiên bản thiết kế.
4.  Ghi nhận kết quả và chụp màn hình minh chứng.

## 4. Kết quả đạt được & Minh chứng
* ✅ **Trạng thái:** Đã hoàn thành toàn bộ các bài test.
* 🏆 **Điểm số:** [Điền điểm của bạn, ví dụ: 7230/8000]

### 📸 Ảnh minh họa kết quả

![Kết quả CantUnsee](bai_tap.png)

> **Nhận xét cá nhân:** Qua bài tập này, em nhận thấy các lỗi về *Alignment (Căn chỉnh)* và *Typography (Kiểu chữ)* là khó phát hiện nhất nếu không quan sát kỹ lưỡng pixel-perfect.

## 5. Thông tin sinh viên
| Thông tin | Chi tiết |
| :--- | :--- |
| **Họ tên** | Dư Văn Sang |
| **MSSV** | BIT230361 |
| **Lớp** | 23IT-SOFT5 |
| **Giảng viên** | Ths. Trương Anh Hoàng |

# 🧪 Bài tập Unit Test: Student Analyzer

![Java](https://img.shields.io/badge/Java-17%2B-orange) ![JUnit](https://img.shields.io/badge/JUnit-5-green) ![Maven](https://img.shields.io/badge/Tool-Maven-red)

## 1. Giới thiệu
Dự án thực hành môn **Kiểm thử phần mềm**, tập trung vào việc viết Unit Test tự động cho các hàm xử lý dữ liệu điểm số học sinh nhằm đảm bảo tính đúng đắn và xử lý ngoại lệ.

## 2. Chức năng chính
Chương trình `StudentAnalyzer` cung cấp các API xử lý:
1.  **`countGoodStudents(List<Double> scores)`**: Đếm số lượng học sinh có điểm `>= 8.0` (Bỏ qua các điểm không hợp lệ < 0 hoặc > 10).
2.  **`calculateAverage(List<Double> scores)`**: Tính trung bình cộng các điểm hợp lệ.

## 3. Cấu trúc dự án
```text
Unit_Test/
├── src/
│   └── StudentAnalyzer.java       # Source code chính (Logic xử lý)
├── test/
│   └── StudentAnalyzerTest.java   # Code kiểm thử (Unit Tests)
├── target/                        # Thư mục build (tự động sinh ra)
└── README.md

## 4. Cách chạy kiểm thử

1.  Mở project trong **VS Code**.

2.  Mở file `test/StudentAnalyzerTest.java`.

3.  Nhấn nút **Run Test** (hình tam giác xanh) bên cạnh tên hàm.

4.  Kết quả (Xanh/Đỏ) sẽ hiện ra ngay lập tức.

Anh minh họa: test.png 
