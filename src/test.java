import java.time.Duration;
import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);
// Duration oneDay = Duration.between(today, yesterday); // throws an exception
        System.out.println(Duration.between(today.atStartOfDay(), yesterday.atStartOfDay()).toDays());
    }
}
