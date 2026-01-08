# Báo Cáo Thực Hành: Kiểm Thử Thị Giác (Visual Testing)

## 1. Tổng quan
Repository này lưu trữ kết quả thực hành bài tập môn **Kiểm thử phần mềm**, tập trung vào kỹ năng kiểm thử giao diện (UI) thông qua nền tảng [CantUnsee](https://cantunsee.space/).

## 2. Mục đích
* Rèn luyện "mắt nghề nghiệp" để phát hiện các lỗi giao diện nhỏ nhất (padding, typography, contrast...).
* Nâng cao tư duy về tính nhất quán (consistency) trong thiết kế phần mềm.

## 3. Các bước thực hiện
1. **Thực thi:** Hoàn thành các cấp độ thử thách so sánh giao diện trên CantUnsee.
2. **Ghi nhận:** Chụp lại màn hình kết quả và điểm số cuối cùng.
3. **Lưu trữ:** Upload minh chứng lên GitHub để báo cáo.

## 4. Kết quả đạt được
* Đã hoàn thành toàn bộ các bài test theo yêu cầu của giảng viên.
* Điểm số và tiến độ được thể hiện chi tiết trong thư mục hình ảnh đính kèm (hoặc xem ảnh bên dưới).

## 5. Thông tin sinh viên
* **Họ tên:** Dư Văn Sang
* **MSSV:** BIT230361
* **Lớp:** 23IT-SOFT5
* **Môn:** Kiểm thử phần mềm



# Bài tập Unit Test: Student Analyzer

## 1. Giới thiệu
Dự án thực hành môn **Kiểm thử phần mềm**, tập trung vào việc viết Unit Test tự động cho các hàm xử lý dữ liệu điểm số.

## 2. Chức năng
Chương trình `StudentAnalyzer` gồm 2 tính năng chính:
* **Đếm học sinh giỏi:** Đếm số lượng điểm `>= 8.0` (bỏ qua điểm lỗi).
* **Tính điểm trung bình:** Tính trung bình cộng của các điểm hợp lệ (từ 0 đến 10).

## 3. Công nghệ sử dụng
* Ngôn ngữ: **Java**
* Thư viện kiểm thử: **JUnit 5**
* Quản lý dự án: **Maven**

## 4. Cách chạy kiểm thử
1.  Mở project trong **VS Code**.
2.  Mở file `test/StudentAnalyzerTest.java`.
3.  Nhấn nút **Run Test** (hình tam giác xanh) bên cạnh tên hàm.
4.  Kết quả (Xanh/Đỏ) sẽ hiện ra ngay lập tức.

## 5. Thông tin sinh viên
* **Họ và tên:** Dư Văn Sang
* **Mã sinh viên:** BIT230361
* **Lớp:** 23IT-SOFT5
* **Môn học:** Kiểm thử phần mềm