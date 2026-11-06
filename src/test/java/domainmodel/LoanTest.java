package domainmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {
    private Loan loan;

    @BeforeEach
    void setup(){
        loan = new Loan("iuwfgwugfwui", LocalDate.of(2026, 10, 1));
    }

    @Test
    void dueDateIs30DaysAfterLoanDate() {
        assertEquals(LocalDate.of(2026, 10, 31), loan.getDueDate());
    }

    @Test
    void noFineBeforeDueDate() {
        LocalDate today = LocalDate.of(2026, 10, 15);
        assertFalse(loan.isOverdue(today));
        assertEquals(0, loan.getFine(today));
    }

    @Test
    void fineIs5KrPerDayOverdue() {
        // Arrange
        LocalDate today = LocalDate.of(2026, 11, 10); // 10 dage efter due date

        // Act
        long fine = loan.getFine(today);

        // Assert
        assertTrue(loan.isOverdue(today));
        assertEquals(50, fine);
    }

    @Test
    void fineNeverExceedsMaxFine() {
        LocalDate today = LocalDate.of(2027, 6, 1); // langt over due date
        assertEquals(200, loan.getFine(today));
    }

}