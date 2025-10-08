import java.util.*;

// Lớp cha Product
abstract class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    double getPrice() {
        return price;
    }
}

// Sản phẩm điện tử
class ElectronicProduct extends Product {
    String imei;

    ElectronicProduct(String name, double price, String imei) {
        super(name, price);
        this.imei = imei;
    }
}

// Sản phẩm thực phẩm
class FoodProduct extends Product {
    String expiryDate;

    FoodProduct(String name, double price, String expiryDate) {
        super(name, price);
        this.expiryDate = expiryDate;
    }
}

// Interface cho các phương thức thanh toán
interface PaymentMethod {
    void pay(String customerName, double amount);
}

// Tiền mặt
class CashPayment implements PaymentMethod {
    public void pay(String customerName, double amount) {
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + amount + ". Thanh toán tiền mặt thành công.");
    }
}

// Thẻ tín dụng
class CreditCardPayment implements PaymentMethod {
    public void pay(String customerName, double amount) {
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + amount + ". Thanh toán bằng thẻ tín dụng thành công.");
    }
}

// Ví Momo
class MomoPayment implements PaymentMethod {
    public void pay(String customerName, double amount) {
        System.out.println("Khách hàng: " + customerName + ". Tổng tiền: " + amount + ". Thanh toán qua ví Momo thành công.");
    }
}

// Đơn hàng
class Order {
    String customerName;
    List<Product> products = new ArrayList<>();
    PaymentMethod paymentMethod;

    Order(String customerName) {
        this.customerName = customerName;
    }

    void addProduct(Product p) {
        products.add(p);
    }

    double getTotal() {
        double sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    void setPaymentMethod(PaymentMethod pm) {
        paymentMethod = pm;
    }

    void checkout() {
        double total = getTotal();
        if (paymentMethod != null) {
            paymentMethod.pay(customerName, total);
        } else {
            System.out.println("Chưa chọn phương thức thanh toán!");
        }
    }
}

// Chạy chương trình
public class Main {
    public static void main(String[] args) {
        // Tạo đơn hàng 1
        Order order1 = new Order("Nguyễn Văn A");
        order1.addProduct(new ElectronicProduct("iPhone", 15000000, "123456"));
        order1.addProduct(new FoodProduct("Bánh mì", 20000, "2025-12-31"));
        order1.setPaymentMethod(new CashPayment());
        order1.checkout();

        // Tạo đơn hàng 2
        Order order2 = new Order("Nguyễn Văn B");
        order2.addProduct(new FoodProduct("Sữa", 30000, "2025-11-30"));
        order2.setPaymentMethod(new CreditCardPayment());
        order2.checkout();

        // Tạo đơn hàng 3
        Order order3 = new Order("Trần Thị C");
        order3.addProduct(new ElectronicProduct("Laptop", 20000000, "987654"));
        order3.setPaymentMethod(new MomoPayment());
        order3.checkout();
    }
}
