import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CrispyFlour extends Material implements Discount{

    private double quantity;

    public CrispyFlour(){}

    public CrispyFlour(String id, String name, LocalDate manufacturingDate, int cost, double quantity) {
        super(id, name, manufacturingDate, cost);
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    @Override
    public double getAmount() {
        return getQuantity() * getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusYears(1);
    }

    @Override
    public double getRealMoney() {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), getExpiryDate());
        double discount = 0;
        if (daysLeft >= 60 && daysLeft <= 120){
            discount = 20;
        } else if (daysLeft <= 60){
            discount = 40;
        } else {
            discount = 5;
        }
        return discount;
    }

    @Override
    public String toString() {
        return
                super.toString() + "/pack" +
                "quantity Left: " + getQuantity() +
                ", Discount: " + getRealMoney() +"%";
    }
}
