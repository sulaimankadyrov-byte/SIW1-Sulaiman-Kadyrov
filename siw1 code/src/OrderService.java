import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class OrderService {
    private Map<Integer, Customer> customerById;
    private Map<Integer, Order> orderById;
    private Queue<Order> orderQueue;

    public OrderService() {
        this.customerById = new HashMap<>();
        this.orderById = new HashMap<>();
        this.orderQueue = new ArrayDeque<>();
    }

    public void addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is null");
        }
        if (customerById.containsKey(customer.getId())) {
            throw new IllegalArgumentException("Customer id already exists: " + customer.getId());
        }
        customerById.put(customer.getId(), customer);
    }

    public Customer findCustomer(int id) {
        Customer customer = customerById.get(id);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found: " + id);
        }
        return customer;
    }

    public void submitOrder(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order is empty");
        }
        if (orderById.containsKey(order.getOrderId())) {
            throw new IllegalArgumentException("Order id already exists: " + order.getOrderId());
        }
        Customer customer = findCustomer(order.getCustomer().getId());
        customer.addOrder(order);
        orderById.put(order.getOrderId(), order);
        orderQueue.offer(order);
    }

    public Order processNextOrder(Cashier cashier) {
        Order order = orderQueue.poll();
        if (order == null) {
            System.out.println("Queue is empty");
            return null;
        }
        cashier.processOrder(order);
        return order;
    }

    public void cancelOrder(int orderId) {
        Order order = orderById.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found: " + orderId);
        }
        if (!order.getStatus().equals("NEW")) {
            throw new IllegalArgumentException("Order already processed: " + orderId);
        }
        orderQueue.remove(order);
        orderById.remove(orderId);
        order.getCustomer().removeOrder(order);
    }

    public int getQueueSize() {
        return orderQueue.size();
    }

    public double getTotalRevenue() {
        double total = 0;
        for (Order order : orderById.values()) {
            if (order.getStatus().equals("DONE")) {
                total = total + order.getTotal();
            }
        }
        return total;
    }
}
