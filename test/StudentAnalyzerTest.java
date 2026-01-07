import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Lớp kiểm thử đơn vị cho StudentAnalyzer
 * Bao gồm các trường hợp: bình thường, biên, và ngoại lệ
 */
public class StudentAnalyzerTest {
    
    private StudentAnalyzer analyzer;
    
    @BeforeEach
    public void setUp() {
        analyzer = new StudentAnalyzer();
    }
    
    // ========== Test cho countExcellentStudents() ==========
    
    /**
     * Test trường hợp bình thường: danh sách có điểm hợp lệ và không hợp lệ
     */
    @Test
    public void testCountExcellentStudents_MixedValidAndInvalid() {
        // Có 2 học sinh giỏi (9.0 và 8.5), bỏ qua 11.0 và -1.0
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
        assertEquals(2, analyzer.countExcellentStudents(scores),
            "Nên đếm được 2 học sinh giỏi (9.0 và 8.5)");
    }
    
    /**
     * Test trường hợp bình thường: danh sách toàn bộ hợp lệ
     */
    @Test
    public void testCountExcellentStudents_AllValid() {
        List<Double> scores = Arrays.asList(9.5, 8.0, 7.5, 8.5, 6.0);
        assertEquals(3, analyzer.countExcellentStudents(scores),
            "Nên đếm được 3 học sinh giỏi (9.5, 8.0, 8.5)");
    }
    
    /**
     * Test trường hợp biên: danh sách trống
     */
    @Test
    public void testCountExcellentStudents_EmptyList() {
        List<Double> scores = Collections.emptyList();
        assertEquals(0, analyzer.countExcellentStudents(scores),
            "Danh sách trống nên trả về 0");
    }
    
    /**
     * Test trường hợp biên: danh sách null
     */
    @Test
    public void testCountExcellentStudents_NullList() {
        assertEquals(0, analyzer.countExcellentStudents(null),
            "Danh sách null nên trả về 0");
    }
    
    /**
     * Test trường hợp biên: chỉ có điểm 0
     */
    @Test
    public void testCountExcellentStudents_OnlyZeros() {
        List<Double> scores = Arrays.asList(0.0, 0.0, 0.0);
        assertEquals(0, analyzer.countExcellentStudents(scores),
            "Điểm 0 không phải là giỏi, nên trả về 0");
    }
    
    /**
     * Test trường hợp biên: chỉ có điểm 10
     */
    @Test
    public void testCountExcellentStudents_OnlyTens() {
        List<Double> scores = Arrays.asList(10.0, 10.0, 10.0);
        assertEquals(3, analyzer.countExcellentStudents(scores),
            "Điểm 10 là giỏi, nên đếm được 3 học sinh");
    }
    
    /**
     * Test trường hợp biên: điểm chính xác 8.0 (ngưỡng giỏi)
     */
    @Test
    public void testCountExcellentStudents_ExactlyEight() {
        List<Double> scores = Arrays.asList(8.0, 7.9, 8.1);
        assertEquals(2, analyzer.countExcellentStudents(scores),
            "Điểm 8.0 trở lên là giỏi, 7.9 không phải");
    }
    
    /**
     * Test trường hợp ngoại lệ: có điểm âm
     */
    @Test
    public void testCountExcellentStudents_WithNegativeScores() {
        List<Double> scores = Arrays.asList(9.0, -5.0, 8.5, -1.0);
        assertEquals(2, analyzer.countExcellentStudents(scores),
            "Bỏ qua điểm âm, chỉ đếm 9.0 và 8.5");
    }
    
    /**
     * Test trường hợp ngoại lệ: có điểm lớn hơn 10
     */
    @Test
    public void testCountExcellentStudents_WithScoresAboveTen() {
        List<Double> scores = Arrays.asList(9.0, 15.0, 8.5, 100.0);
        assertEquals(2, analyzer.countExcellentStudents(scores),
            "Bỏ qua điểm > 10, chỉ đếm 9.0 và 8.5");
    }
    
    /**
     * Test trường hợp ngoại lệ: toàn bộ điểm không hợp lệ
     */
    @Test
    public void testCountExcellentStudents_AllInvalid() {
        List<Double> scores = Arrays.asList(-1.0, 11.0, -5.0, 20.0);
        assertEquals(0, analyzer.countExcellentStudents(scores),
            "Tất cả điểm không hợp lệ, nên trả về 0");
    }
    
    /**
     * Test trường hợp có null trong danh sách
     */
    @Test
    public void testCountExcellentStudents_WithNullValues() {
        List<Double> scores = Arrays.asList(9.0, null, 8.5, null);
        assertEquals(2, analyzer.countExcellentStudents(scores),
            "Bỏ qua giá trị null, chỉ đếm 9.0 và 8.5");
    }
    
    // ========== Test cho calculateValidAverage() ==========
    
    /**
     * Test trường hợp bình thường: danh sách có điểm hợp lệ và không hợp lệ
     */
    @Test
    public void testCalculateValidAverage_MixedValidAndInvalid() {
        // Trung bình của 9.0, 8.5, 7.0 = 24.5 / 3 = 8.166...
        List<Double> scores = Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0);
        assertEquals(8.17, analyzer.calculateValidAverage(scores), 0.01,
            "Trung bình của 9.0, 8.5, 7.0 là 8.17");
    }
    
    /**
     * Test trường hợp bình thường: danh sách toàn bộ hợp lệ
     */
    @Test
    public void testCalculateValidAverage_AllValid() {
        List<Double> scores = Arrays.asList(10.0, 8.0, 6.0);
        assertEquals(8.0, analyzer.calculateValidAverage(scores), 0.01,
            "Trung bình của 10.0, 8.0, 6.0 là 8.0");
    }
    
    /**
     * Test trường hợp biên: danh sách trống
     */
    @Test
    public void testCalculateValidAverage_EmptyList() {
        List<Double> scores = Collections.emptyList();
        assertEquals(0.0, analyzer.calculateValidAverage(scores), 0.01,
            "Danh sách trống nên trả về 0.0");
    }
    
    /**
     * Test trường hợp biên: danh sách null
     */
    @Test
    public void testCalculateValidAverage_NullList() {
        assertEquals(0.0, analyzer.calculateValidAverage(null), 0.01,
            "Danh sách null nên trả về 0.0");
    }
    
    /**
     * Test trường hợp biên: chỉ có điểm 0
     */
    @Test
    public void testCalculateValidAverage_OnlyZeros() {
        List<Double> scores = Arrays.asList(0.0, 0.0, 0.0);
        assertEquals(0.0, analyzer.calculateValidAverage(scores), 0.01,
            "Trung bình của các số 0 là 0.0");
    }
    
    /**
     * Test trường hợp biên: chỉ có điểm 10
     */
    @Test
    public void testCalculateValidAverage_OnlyTens() {
        List<Double> scores = Arrays.asList(10.0, 10.0, 10.0);
        assertEquals(10.0, analyzer.calculateValidAverage(scores), 0.01,
            "Trung bình của các số 10 là 10.0");
    }
    
    /**
     * Test trường hợp biên: điểm ở ngưỡng 0 và 10
     */
    @Test
    public void testCalculateValidAverage_BoundaryValues() {
        List<Double> scores = Arrays.asList(0.0, 10.0);
        assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.01,
            "Trung bình của 0.0 và 10.0 là 5.0");
    }
    
    /**
     * Test trường hợp ngoại lệ: có điểm âm
     */
    @Test
    public void testCalculateValidAverage_WithNegativeScores() {
        List<Double> scores = Arrays.asList(10.0, -5.0, 8.0, -1.0);
        assertEquals(9.0, analyzer.calculateValidAverage(scores), 0.01,
            "Bỏ qua điểm âm, trung bình của 10.0 và 8.0 là 9.0");
    }
    
    /**
     * Test trường hợp ngoại lệ: có điểm lớn hơn 10
     */
    @Test
    public void testCalculateValidAverage_WithScoresAboveTen() {
        List<Double> scores = Arrays.asList(10.0, 15.0, 8.0, 100.0);
        assertEquals(9.0, analyzer.calculateValidAverage(scores), 0.01,
            "Bỏ qua điểm > 10, trung bình của 10.0 và 8.0 là 9.0");
    }
    
    /**
     * Test trường hợp ngoại lệ: toàn bộ điểm không hợp lệ
     */
    @Test
    public void testCalculateValidAverage_AllInvalid() {
        List<Double> scores = Arrays.asList(-1.0, 11.0, -5.0, 20.0);
        assertEquals(0.0, analyzer.calculateValidAverage(scores), 0.01,
            "Tất cả điểm không hợp lệ, nên trả về 0.0");
    }
    
    /**
     * Test trường hợp có null trong danh sách
     */
    @Test
    public void testCalculateValidAverage_WithNullValues() {
        List<Double> scores = Arrays.asList(10.0, null, 8.0, null);
        assertEquals(9.0, analyzer.calculateValidAverage(scores), 0.01,
            "Bỏ qua giá trị null, trung bình của 10.0 và 8.0 là 9.0");
    }
    
    /**
     * Test trường hợp một điểm hợp lệ
     */
    @Test
    public void testCalculateValidAverage_SingleValidScore() {
        List<Double> scores = Arrays.asList(7.5);
        assertEquals(7.5, analyzer.calculateValidAverage(scores), 0.01,
            "Chỉ có một điểm 7.5, trung bình là 7.5");
    }
}
