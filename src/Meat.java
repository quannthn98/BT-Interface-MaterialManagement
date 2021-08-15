import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Meat extends Material implements Discount {

    private double weight;

    Meat() {
    }

    Meat(String id, String name, LocalDate manufacturingDate, int cost, double weight) {
        super(id, name, manufacturingDate, cost);
        this.weight = weight;

    }

    public double getQuantity() {
        return weight;
    }

    public void setQuantity(double weight){
        this.weight = weight;
    }

    @Override
    public double getAmount() {
        return getQuantity() * getCost();
    }

    @Override
    public LocalDate getExpiryDate() {
        return getManufacturingDate().plusDays(7);
    }

    @Override
    public double getRealMoney() {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), getExpiryDate());
        double discount = 0;
        if (daysLeft >= 3 && daysLeft <= 5) {
            discount = 50;
        } else if (daysLeft >= 3) {
            discount = 30;
        } else {
            discount = 10;
        }
        return discount;
    }

    @Override
    public String toString() {
        return super.toString() + "/kg " +
                ", Amount left:" + weight + " kg" +
                ", Discount: " + getRealMoney() +"%";
    }
}
