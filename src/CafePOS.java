import java.util.Random;
import java.util.function.*;

public class CafePOS {
    public static void main(String[] args) {
        Supplier<Integer> ticketSupplier = () -> new Random().nextInt(100) + 1;
        Predicate<CoffeeOrder> summerMenuCheck = order -> order.isIced();

        Function<CoffeeOrder, Double> baseCashier = order -> {
            double price = order.getBasePrice();
            if (summerMenuCheck.test(order)) {
                price += 0.75;
            }
            return price;
        };

        UnaryOperator<Double> happyHourDiscount = price -> price * 0.8;

        Consumer<CoffeeOrder> baristaPrinter = order -> {
            String temperatureTag = order.isIced() ? "(ICED)" : "(HOT)";
            System.out.println("------------------------");
            System.out.println("      CAFÉ TICKET       ");
            System.out.println("------------------------");
            System.out.println("Customer: " + order.getCustomerName());
            System.out.println("Drink   : " + order.getDrinkType() + " " + temperatureTag);
            System.out.println("Base    : $" + order.getBasePrice());
            System.out.println("------------------------");
        };

        CoffeeOrder order = new CoffeeOrder("Alice", "Iced Matcha Latte", true, 5.00);

        int ticketNumber = ticketSupplier.get();
        System.out.println("Ticket Number Generated: #" + ticketNumber);

        double finalPrice = baseCashier.andThen(happyHourDiscount).apply(order);
        System.out.printf("Final Price (after Base + Surcharge + Happy Hour Discount): $%.2f%n", finalPrice);

        baristaPrinter.accept(order);
    }
}