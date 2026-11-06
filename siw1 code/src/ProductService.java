import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    private Store store;
    private Map<Integer, Product> productById;
    private Map<String, List<Product>> productsByCategory;

    public ProductService(Store store) {
        this.store = store;
        this.productById = new HashMap<>();
        this.productsByCategory = new LinkedHashMap<>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product is null");
        }
        if (productById.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product id already exists: " + product.getId());
        }
        store.addProduct(product);
        productById.put(product.getId(), product);
        String category = product.getCategory();
        if (!productsByCategory.containsKey(category)) {
            productsByCategory.put(category, new ArrayList<>());
        }
        productsByCategory.get(category).add(product);
    }

    public Product findById(int id) {
        Product product = productById.get(id);
        if (product == null) {
            throw new IllegalArgumentException("Product not found: " + id);
        }
        return product;
    }

    public void updatePrice(int id, double newPrice) {
        if (newPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        Product product = findById(id);
        product.setPrice(newPrice);
    }

    public void removeProduct(int id) {
        Product product = findById(id);
        store.removeProduct(product);
        productById.remove(id);
        String category = product.getCategory();
        List<Product> group = productsByCategory.get(category);
        group.remove(product);
        if (group.isEmpty()) {
            productsByCategory.remove(category);
        }
    }

    public List<Product> getByCategory(String category) {
        List<Product> group = productsByCategory.get(category);
        if (group == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(group);
    }

    public List<Product> filterByMaxPrice(double maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : store.getProducts()) {
            if (product.getPrice() <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> searchByName(String text) {
        List<Product> result = new ArrayList<>();
        for (Product product : store.getProducts()) {
            if (product.getName().toLowerCase().contains(text.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> getSortedNatural() {
        List<Product> copy = new ArrayList<>(store.getProducts());
        Collections.sort(copy);
        return copy;
    }

    public List<Product> getSorted(Comparator<Product> comparator) {
        List<Product> copy = new ArrayList<>(store.getProducts());
        copy.sort(comparator);
        return copy;
    }

    public double getTotalStockValue() {
        double total = 0;
        for (Product product : store.getProducts()) {
            total = total + product.getPrice() * product.getQuantity();
        }
        return total;
    }

    public Product getMostExpensive() {
        if (store.getProducts().isEmpty()) {
            return null;
        }
        return Collections.max(store.getProducts(), new PriceComparator());
    }

    public int countDiscountable() {
        int count = 0;
        for (Product product : store.getProducts()) {
            if (product instanceof Discountable) {
                count++;
            }
        }
        return count;
    }

    public void printCategoryReport() {
        for (Map.Entry<String, List<Product>> entry : productsByCategory.entrySet()) {
            System.out.println("   " + entry.getKey() + ": " + entry.getValue().size());
        }
    }
}
