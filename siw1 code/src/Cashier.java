import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cashier {
    private int id;
    private String name;
    private List<Order> processedOrders;

    public Cashier(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid cashier id");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid cashier name");
        }
        this.id = id;
        this.name = name;
        this.processedOrders = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProcessedCount() {
        return processedOrders.size();
    }

    public void processOrder(Order order) {
        order.setStatus("DONE");
        processedOrders.add(order);
        System.out.println("Cashier " + name + " processed order #" + order.getOrderId()
                + " for " + order.getCustomer().getName() + ": " + order.getTotal());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cashier other = (Cashier) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
