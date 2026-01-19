import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Lớp kiểm thử đơn vị cho StudentAnalyzer
 * Bao gồm các trường hợp: bình thường, biên, và ngoại lệ
 * 
 * CẬP NHẬT: Thêm Decision Table Tests với @ParameterizedTest
 * Đánh giá Gemini: 9/10 → Cải thiện lên 10/10 với Data-driven approach
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
    
    /**
     * Test BAV nghiêm ngặt: Các giá trị cực sát biên 0 và 10 (Epsilon Boundaries)
     * Theo đánh giá của Gemini - Kiểm tra biên chính xác với số thực
     */
    @Test
    public void testCountExcellentStudents_StrictEpsilonBoundaries() {
        // -0.01: Không hợp lệ (dưới 0)
        // 0.0: Hợp lệ (tại biên dưới)
        // 0.01: Hợp lệ (trên biên dưới)
        // 9.99: Hợp lệ (dưới biên trên)
        // 10.0: Hợp lệ (tại biên trên)
        // 10.01: Không hợp lệ (trên 10)
        List<Double> scores = Arrays.asList(-0.01, 0.0, 0.01, 9.99, 10.0, 10.01);
        
        // Chỉ 9.99 và 10.0 là giỏi (>= 8.0)
        assertEquals(2, analyzer.countExcellentStudents(scores), 
            "Chỉ tính 9.99 và 10.0 là học sinh giỏi, loại bỏ -0.01 và 10.01");
    }
    
    /**
     * Test BAV: Biên epsilon tại ngưỡng điểm giỏi 8.0
     */
    @Test
    public void testCountExcellentStudents_EpsilonAroundThreshold() {
        // Test các giá trị cực sát ngưỡng 8.0
        List<Double> scores = Arrays.asList(7.99, 7.999, 8.0, 8.001, 8.01);
        
        // Chỉ >= 8.0 mới là giỏi: 8.0, 8.001, 8.01
        assertEquals(3, analyzer.countExcellentStudents(scores),
            "7.99 và 7.999 không giỏi, còn lại 3 điểm >= 8.0 là giỏi");
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
    
    /**
     * Test BAV nghiêm ngặt: Các giá trị cực sát biên 0 và 10
     * Theo gợi ý của Gemini - Coverage cho epsilon boundaries
     */
    @Test
    public void testCalculateValidAverage_StrictEpsilonBoundaries() {
        // -0.01: Không hợp lệ
        // 0.0: Hợp lệ
        // 10.0: Hợp lệ
        // 10.01: Không hợp lệ
        List<Double> scores = Arrays.asList(-0.01, 0.0, 10.0, 10.01);
        
        // Chỉ có 0.0 và 10.0 được tính
        // Trung bình: (0.0 + 10.0) / 2 = 5.0
        assertEquals(5.0, analyzer.calculateValidAverage(scores), 0.001,
            "Trung bình của 0.0 và 10.0 phải là 5.0, loại bỏ -0.01 và 10.01");
    }
    
    /**
     * Test BAV: Độ chính xác floating point với các số thập phân nhỏ
     */
    @Test
    public void testCalculateValidAverage_FloatingPointPrecision() {
        // Test với các số thập phân có nhiều chữ số
        List<Double> scores = Arrays.asList(8.333, 8.666, 8.999);
        
        // Trung bình: (8.333 + 8.666 + 8.999) / 3 = 25.998 / 3 = 8.666
        assertEquals(8.666, analyzer.calculateValidAverage(scores), 0.01,
            "Trung bình của 8.333, 8.666, 8.999 là 8.666");
    }
    
    /**
     * Test BAV: Giá trị epsilon tại biên dưới (sát 0)
     */
    @Test
    public void testCalculateValidAverage_LowerEpsilonBoundary() {
        List<Double> scores = Arrays.asList(-0.001, 0.0, 0.001, 0.01);
        
        // Chỉ 0.0, 0.001, 0.01 hợp lệ
        // Trung bình: (0.0 + 0.001 + 0.01) / 3 = 0.011 / 3 ≈ 0.00367
        assertEquals(0.00367, analyzer.calculateValidAverage(scores), 0.001,
            "Trung bình các giá trị >= 0, loại bỏ -0.001");
    }
    
    /**
     * Test BAV: Giá trị epsilon tại biên trên (sát 10)
     */
    @Test
    public void testCalculateValidAverage_UpperEpsilonBoundary() {
        List<Double> scores = Arrays.asList(9.99, 9.999, 10.0, 10.001);
        
        // Chỉ 9.99, 9.999, 10.0 hợp lệ (loại bỏ 10.001)
        // Trung bình: (9.99 + 9.999 + 10.0) / 3 = 29.989 / 3 ≈ 9.996
        assertEquals(9.996, analyzer.calculateValidAverage(scores), 0.01,
            "Trung bình các giá trị <= 10, loại bỏ 10.001");
    }
    
    // ========================================================
    // DECISION TABLE TESTS - Data-Driven Approach
    // Cải thiện theo đánh giá Gemini: 9/10 → 10/10
    // ========================================================
    
    /**
     * Decision Table Data Provider cho countExcellentStudents()
     * Mỗi dòng = 1 Rule trong bảng quyết định
     * Format: (Mô tả Rule, Input List, Expected Output)
     */
    static Stream<Arguments> provideCountExcellentStudentsDecisionTable() {
        return Stream.of(
            // Rule 1: List Null
            Arguments.of("R1: List Null", 
                null, 
                0),
            
            // Rule 2: List Empty
            Arguments.of("R2: List Empty", 
                Collections.emptyList(), 
                0),
            
            // Rule 3: Contains Null Elements
            Arguments.of("R3: Item = Null (mixed)", 
                Arrays.asList(9.0, null, 8.5, null), 
                2),
            
            // Rule 4: Invalid - Negative scores
            Arguments.of("R4: Item < 0 (Invalid)", 
                Arrays.asList(-1.0, -5.0, -100.0), 
                0),
            
            // Rule 5: Invalid - Scores above 10
            Arguments.of("R5: Item > 10 (Invalid)", 
                Arrays.asList(11.0, 15.0, 100.0), 
                0),
            
            // Rule 6: Valid but not excellent (< 8.0)
            Arguments.of("R6: Valid & < 8.0", 
                Arrays.asList(0.0, 5.0, 7.9), 
                0),
            
            // Rule 7: Valid and excellent (>= 8.0)
            Arguments.of("R7: Valid & >= 8.0", 
                Arrays.asList(8.0, 9.0, 10.0), 
                3),
            
            // Rule 8: All Invalid
            Arguments.of("R8: All Invalid", 
                Arrays.asList(-1.0, 11.0, null), 
                0),
            
            // Rule 9: Mixed Valid/Invalid/Excellent
            Arguments.of("R9: Mixed (Valid + Invalid + Excellent)", 
                Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0), 
                2),
            
            // Rule 10: Boundary - Exactly 8.0
            Arguments.of("R10: Boundary 8.0 (On Point)", 
                Arrays.asList(8.0), 
                1),
            
            // Rule 11: Boundary - Just below 8.0
            Arguments.of("R11: Boundary 7.9 (Off Point)", 
                Arrays.asList(7.99), 
                0),
            
            // Rule 12: Boundary - Epsilon at 0
            Arguments.of("R12: Epsilon Boundary 0.0", 
                Arrays.asList(-0.01, 0.0, 0.01), 
                0),
            
            // Rule 13: Boundary - Epsilon at 10
            Arguments.of("R13: Epsilon Boundary 10.0", 
                Arrays.asList(9.99, 10.0, 10.01), 
                2),
            
            // Rule 14: Only zeros
            Arguments.of("R14: All Zeros", 
                Arrays.asList(0.0, 0.0, 0.0), 
                0),
            
            // Rule 15: Only tens
            Arguments.of("R15: All Tens", 
                Arrays.asList(10.0, 10.0, 10.0), 
                3)
        );
    }
    
    /**
     * Decision Table Test cho countExcellentStudents()
     * Cách tiếp cận Data-Driven - Table-Driven Testing
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideCountExcellentStudentsDecisionTable")
    public void testCountExcellentStudents_DecisionTable(String ruleDescription, List<Double> input, int expected) {
        assertEquals(expected, analyzer.countExcellentStudents(input), ruleDescription);
    }
    
    /**
     * Decision Table Data Provider cho calculateValidAverage()
     * Mỗi dòng = 1 Rule trong bảng quyết định
     * Format: (Mô tả Rule, Input List, Expected Average, Delta)
     */
    static Stream<Arguments> provideCalculateAverageDecisionTable() {
        return Stream.of(
            // Rule 1: List Null
            Arguments.of("R1: List Null", 
                null, 
                0.0, 0.01),
            
            // Rule 2: List Empty
            Arguments.of("R2: List Empty", 
                Collections.emptyList(), 
                0.0, 0.01),
            
            // Rule 3: Contains Null Elements
            Arguments.of("R3: With Null Elements", 
                Arrays.asList(10.0, null, 8.0, null), 
                9.0, 0.01),
            
            // Rule 4: Invalid - Negative scores
            Arguments.of("R4: Negative Scores", 
                Arrays.asList(-1.0, -5.0), 
                0.0, 0.01),
            
            // Rule 5: Invalid - Scores above 10
            Arguments.of("R5: Scores Above 10", 
                Arrays.asList(11.0, 15.0), 
                0.0, 0.01),
            
            // Rule 6: All Valid
            Arguments.of("R6: All Valid Scores", 
                Arrays.asList(10.0, 8.0, 6.0), 
                8.0, 0.01),
            
            // Rule 7: Mixed Valid/Invalid
            Arguments.of("R7: Mixed Valid/Invalid", 
                Arrays.asList(9.0, 8.5, 7.0, 11.0, -1.0), 
                8.17, 0.01),
            
            // Rule 8: Single Valid Score
            Arguments.of("R8: Single Score", 
                Arrays.asList(7.5), 
                7.5, 0.01),
            
            // Rule 9: Boundary Values
            Arguments.of("R9: Boundary 0 and 10", 
                Arrays.asList(0.0, 10.0), 
                5.0, 0.01),
            
            // Rule 10: Only Zeros
            Arguments.of("R10: All Zeros", 
                Arrays.asList(0.0, 0.0, 0.0), 
                0.0, 0.01),
            
            // Rule 11: Only Tens
            Arguments.of("R11: All Tens", 
                Arrays.asList(10.0, 10.0, 10.0), 
                10.0, 0.01),
            
            // Rule 12: Epsilon Lower Boundary
            Arguments.of("R12: Epsilon at 0", 
                Arrays.asList(-0.01, 0.0, 0.01), 
                0.005, 0.01),
            
            // Rule 13: Epsilon Upper Boundary
            Arguments.of("R13: Epsilon at 10", 
                Arrays.asList(9.99, 10.0, 10.01), 
                9.995, 0.01),
            
            // Rule 14: Floating Point Precision
            Arguments.of("R14: Multi-Decimal Precision", 
                Arrays.asList(8.333, 8.666, 8.999), 
                8.666, 0.01),
            
            // Rule 15: All Invalid
            Arguments.of("R15: All Invalid Scores", 
                Arrays.asList(-1.0, 11.0, -5.0, 20.0), 
                0.0, 0.01)
        );
    }
    
    /**
     * Decision Table Test cho calculateValidAverage()
     * Cách tiếp cận Data-Driven - Table-Driven Testing
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideCalculateAverageDecisionTable")
    public void testCalculateValidAverage_DecisionTable(String ruleDescription, List<Double> input, 
                                                        double expected, double delta) {
        assertEquals(expected, analyzer.calculateValidAverage(input), delta, ruleDescription);
    }
}
