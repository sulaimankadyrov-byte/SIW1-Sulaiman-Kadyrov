import java.util.List;

public class Main {
    public static void main(String[] args) {
        Store store = new Store("TechShop");
        ProductService productService = new ProductService(store);
        OrderService orderService = new OrderService();

        GamingLaptop g1 = new GamingLaptop(1, "ROG Strix", 1500, 5, "RTX 4060", 144);
        OfficeLaptop o1 = new OfficeLaptop(2, "ThinkBook", 800, 8, 10, true);
        Ultrabook u1 = new Ultrabook(3, "MacBook Air", 1200, 6, 1.2, 13.6);
        GamingLaptop g2 = new GamingLaptop(4, "Legion 5", 1300, 4, "RTX 4050", 120);
        OfficeLaptop o2 = new OfficeLaptop(5, "IdeaPad", 600, 10, 8, false);

        productService.addProduct(u1);
        productService.addProduct(g2);
        productService.addProduct(g1);
        productService.addProduct(o2);
        productService.addProduct(o1);

        System.out.println("--- Lookup by id and by category ---");
        System.out.println("Product 2: " + productService.findById(2).getName());
        printNames("Gaming laptops", productService.getByCategory("Gaming laptop"));

        System.out.println("--- Sorting ---");
        printNames("By id (natural)", productService.getSortedNatural());
        printNames("By name", productService.getSorted(new NameComparator()));
        printNames("By price", productService.getSorted(new PriceComparator()));
        printNames("By category, price", productService.getSorted(new CategoryPriceComparator()));

        System.out.println("--- Filter ---");
        printNames("Price up to 1000", productService.filterByMaxPrice(1000));
        printNames("Name contains 'book'", productService.searchByName("book"));

        System.out.println("--- Orders and queue ---");
        Customer ali = new Customer(1, "Ali");
        Customer bota = new Customer(2, "Bota");
        Cashier cashier = new Cashier(1, "Aida");
        orderService.addCustomer(ali);
        orderService.addCustomer(bota);

        Order order1 = new Order(1, ali);
        order1.addItem(g1, 1);
        order1.addItem(u1, 2);
        Order order2 = new Order(2, bota);
        order2.addItem(o1, 1);
        order2.addItem(o2, 2);
        Order order3 = new Order(3, ali);
        order3.addItem(g2, 1);

        orderService.submitOrder(order1);
        orderService.submitOrder(order2);
        orderService.submitOrder(order3);
        System.out.println("Orders in queue: " + orderService.getQueueSize());

        orderService.cancelOrder(3);
        System.out.println("Order 3 cancelled, queue: " + orderService.getQueueSize());

        while (orderService.getQueueSize() > 0) {
            orderService.processNextOrder(cashier);
        }
        orderService.processNextOrder(cashier);

        System.out.println("--- Remove product and report ---");
        productService.removeProduct(4);
        productService.printCategoryReport();
        System.out.println("Stock value: " + productService.getTotalStockValue());
        Product top = productService.getMostExpensive();
        if (top != null) {
            System.out.println("Most expensive: " + top.getName());
        }
        System.out.println("Discountable products: " + productService.countDiscountable());
        System.out.println("Revenue: " + orderService.getTotalRevenue());
        System.out.println("Ali orders: " + ali.getOrders().size() + " | Aida processed: " + cashier.getProcessedCount());

        System.out.println("--- Error handling ---");
        try {
            productService.findById(99);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            productService.addProduct(g1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
        try {
            orderService.submitOrder(new Order(10, ali));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void printNames(String title, List<Product> products) {
        StringBuilder text = new StringBuilder();
        for (Product product : products) {
            if (text.length() > 0) {
                text.append(", ");
            }
            text.append(product.getName());
        }
        System.out.println(title + ": " + text);
    }
}
