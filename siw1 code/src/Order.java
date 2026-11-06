import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int orderId;
    private Customer customer;
    private List<OrderItem> items;
    private String status;

    public Order(int orderId, Customer customer) {
        if (orderId <= 0) {
            throw new IllegalArgumentException("Invalid order id");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Order needs a customer");
        }
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "NEW";
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addItem(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid order item");
        }
        for (OrderItem item : items) {
            if (item.getProduct().equals(product)) {
                item.addQuantity(quantity);
                return;
            }
        }
        items.add(new OrderItem(product, quantity));
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total = total + item.getTotal();
        }
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order other = (Order) obj;
        return orderId == other.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
