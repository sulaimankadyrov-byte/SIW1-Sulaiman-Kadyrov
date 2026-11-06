import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Store {
    private String name;
    private List<Product> products;

    public Store(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid store name");
        }
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Store other = (Store) obj;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
