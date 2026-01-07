# Bài Tập Thực Hành Kiểm Thử Với JUnit

## 📚 Mô Tả Bài Toán

Chương trình phân tích dữ liệu điểm số học sinh với hai chức năng chính:

1. **Đếm số học sinh đạt loại Giỏi** (điểm >= 8.0)
2. **Tính điểm trung bình** của các điểm hợp lệ (từ 0 đến 10)

### Yêu Cầu Kỹ Thuật

- ✅ Điểm hợp lệ: từ 0 đến 10
- ❌ Điểm không hợp lệ (< 0 hoặc > 10): bỏ qua
- 📝 Danh sách rỗng hoặc null: trả về 0
- 🎓 Điểm Giỏi: >= 8.0

## 🗂️ Cấu Trúc Thư Mục

```
Unit_test/
├── src/
│   └── StudentAnalyzer.java       # Mã nguồn chính
├── test/
│   └── StudentAnalyzerTest.java   # Mã kiểm thử đơn vị
├── pom.xml                        # Cấu hình Maven
└── README.md                      # Tài liệu hướng dẫn
```

## 🛠️ Công Nghệ Sử Dụng

- **Java**: 11 hoặc cao hơn
- **JUnit**: 5.10.1 (JUnit Jupiter)
- **Maven**: 3.x để quản lý dependencies và build

## 📦 Yêu Cầu Cài Đặt

### Yêu Cầu Hệ Thống
- Java JDK 11 trở lên
- Maven 3.x
- Git (để quản lý version control)

### Kiểm Tra Cài Đặt

```bash
# Kiểm tra Java
java -version

# Kiểm tra Maven
mvn -version
```

## 🚀 Hướng Dẫn Chạy Chương Trình

### 1. Clone Repository (nếu chưa có)

```bash
git clone <repository-url>
cd Unit_test
```

### 2. Biên Dịch Mã Nguồn

```bash
mvn clean compile
```

### 3. Chạy Kiểm Thử Đơn Vị

```bash
# Chạy tất cả test cases
mvn test

# Chạy test với output chi tiết
mvn test -Dtest=StudentAnalyzerTest

# Chạy một test method cụ thể
mvn test -Dtest=StudentAnalyzerTest#testCountExcellentStudents_MixedValidAndInvalid
```

### 4. Xem Báo Cáo Test

Sau khi chạy `mvn test`, báo cáo sẽ được tạo tại:
```
target/surefire-reports/
```

## 📝 Chi Tiết Test Cases

### Test cho `countExcellentStudents()`

| Test Case | Mô Tả | Kết Quả Mong Đợi |
|-----------|-------|------------------|
| `testCountExcellentStudents_MixedValidAndInvalid` | Danh sách có điểm hợp lệ và không hợp lệ | Đếm đúng số học sinh giỏi |
| `testCountExcellentStudents_AllValid` | Danh sách toàn bộ hợp lệ | Đếm tất cả học sinh >= 8.0 |
| `testCountExcellentStudents_EmptyList` | Danh sách rỗng | Trả về 0 |
| `testCountExcellentStudents_NullList` | Danh sách null | Trả về 0 |
| `testCountExcellentStudents_OnlyZeros` | Chỉ có điểm 0 | Trả về 0 |
| `testCountExcellentStudents_OnlyTens` | Chỉ có điểm 10 | Đếm tất cả |
| `testCountExcellentStudents_ExactlyEight` | Điểm chính xác 8.0 | Điểm 8.0 được tính là giỏi |
| `testCountExcellentStudents_WithNegativeScores` | Có điểm âm | Bỏ qua điểm âm |
| `testCountExcellentStudents_WithScoresAboveTen` | Có điểm > 10 | Bỏ qua điểm > 10 |
| `testCountExcellentStudents_AllInvalid` | Toàn bộ không hợp lệ | Trả về 0 |
| `testCountExcellentStudents_WithNullValues` | Có giá trị null | Bỏ qua null |

### Test cho `calculateValidAverage()`

| Test Case | Mô Tả | Kết Quả Mong Đợi |
|-----------|-------|------------------|
| `testCalculateValidAverage_MixedValidAndInvalid` | Danh sách có điểm hợp lệ và không hợp lệ | Tính trung bình chỉ điểm hợp lệ |
| `testCalculateValidAverage_AllValid` | Danh sách toàn bộ hợp lệ | Tính trung bình tất cả |
| `testCalculateValidAverage_EmptyList` | Danh sách rỗng | Trả về 0.0 |
| `testCalculateValidAverage_NullList` | Danh sách null | Trả về 0.0 |
| `testCalculateValidAverage_OnlyZeros` | Chỉ có điểm 0 | Trả về 0.0 |
| `testCalculateValidAverage_OnlyTens` | Chỉ có điểm 10 | Trả về 10.0 |
| `testCalculateValidAverage_BoundaryValues` | Điểm ở ngưỡng 0 và 10 | Trung bình chính xác |
| `testCalculateValidAverage_WithNegativeScores` | Có điểm âm | Bỏ qua điểm âm |
| `testCalculateValidAverage_WithScoresAboveTen` | Có điểm > 10 | Bỏ qua điểm > 10 |
| `testCalculateValidAverage_AllInvalid` | Toàn bộ không hợp lệ | Trả về 0.0 |
| `testCalculateValidAverage_WithNullValues` | Có giá trị null | Bỏ qua null |
| `testCalculateValidAverage_SingleValidScore` | Một điểm hợp lệ | Trả về điểm đó |

## 🔄 Quy Trình Làm Việc Với Git

### 1. Tạo Issues Trên GitHub

Tạo các issues sau trên repository của bạn:

1. **Issue #1**: Viết hàm `countExcellentStudents()`
   - Label: `enhancement`, `feature`
   - Mô tả: Xử lý kiểm tra điểm hợp lệ và đếm học sinh giỏi

2. **Issue #2**: Viết hàm `calculateValidAverage()`
   - Label: `enhancement`, `feature`
   - Mô tả: Tính trung bình các điểm hợp lệ

3. **Issue #3**: Viết test cho 2 hàm trên
   - Label: `testing`
   - Mô tả: Dùng JUnit để kiểm thử đầy đủ

4. **Issue #4**: Viết tài liệu README.md
   - Label: `documentation`
   - Mô tả: Mô tả bài toán, cách chạy chương trình, test

### 2. Commit Với Từ Khóa Liên Kết Issue

```bash
# Khi implement một feature
git add src/StudentAnalyzer.java
git commit -m "feat: implement countExcellentStudents() #1"

git add src/StudentAnalyzer.java
git commit -m "feat: implement calculateValidAverage() #2"

# Khi thêm tests
git add test/StudentAnalyzerTest.java pom.xml
git commit -m "test: add comprehensive unit tests for both methods #3"

# Khi hoàn thành documentation
git add README.md
git commit -m "docs: update README with instructions #4"

# Push lên remote
git push origin main
```

### 3. Từ Khóa Đóng Issue Tự Động

Sử dụng các từ khóa này trong commit message để tự động đóng issue khi merge:

- `fixes #1` hoặc `fix #1`
- `closes #1` hoặc `close #1`
- `resolves #1` hoặc `resolve #1`

Ví dụ:
```bash
git commit -m "feat: implement countExcellentStudents() - fixes #1"
```

## 📊 Kết Quả Mong Đợi

Khi chạy `mvn test`, bạn sẽ thấy output tương tự:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running StudentAnalyzerTest
[INFO] Tests run: 23, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 23, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

## 🎯 Mục Tiêu Học Tập

✅ Biết cách viết kiểm thử tự động bằng JUnit  
✅ Hiểu các loại test case: bình thường, biên, ngoại lệ  
✅ Biết cách khai thác AI để hỗ trợ lập trình  
✅ Thực hành quy trình Git workflow với issues và commits  
✅ Áp dụng TDD (Test-Driven Development) trong thực tế

## 📖 Tài Liệu Tham Khảo

- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Maven Getting Started](https://maven.apache.org/guides/getting-started/)
- [Git Commit Messages Conventions](https://www.conventionalcommits.org/)

## 👨‍💻 Tác Giả

Bài tập thực hành môn Kiểm thử phần mềm

## 📄 License

Dự án này được sử dụng cho mục đích học tập.

---

**Lưu Ý**: Đảm bảo đã cài đặt đầy đủ Java JDK và Maven trước khi chạy chương trình.
