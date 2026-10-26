import org.junit.jupiter.api.BeforeAll;
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

}