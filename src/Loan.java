import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {

    private final int loanDays = 30;
    private final int maxFine = 200;
    private final int finePerDay = 5;

    private String title;
    private LocalDate LoanDate;
    private LocalDate returnDate;

    public Loan(String title, LocalDate loanDate) {
        this.title = title;
        this.LoanDate = loanDate;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getLoanDate() {
        return LoanDate;
    }

    public LocalDate getDueDate() {
        return LoanDate.plusDays(loanDays);
    }

    public boolean isOverdue(LocalDate today) {
        return today.isAfter(getDueDate());
    }

    public long getDaysOver(LocalDate today) {
        if (!isOverdue(today)) {
            return 0;
        } else {
            return ChronoUnit.DAYS.between(getDueDate(), today);
        }
    }

    public long getFine(LocalDate today) {
        long fine = getDaysOver(today) * finePerDay;
        if (fine > maxFine) {
            return maxFine;
        } else {return fine;}

    }


}
