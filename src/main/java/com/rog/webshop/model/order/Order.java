package com.rog.webshop.model.order;

import com.rog.webshop.model.cart.Cart;
import com.rog.webshop.model.product.Product;
import com.rog.webshop.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    private static final long serialVersionUID = -167040897500768764L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Transient
    private Cart cart;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ORDER_PRODUCT",
            joinColumns = {@JoinColumn(name = "ORDER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID")})
    private List<Product> productList = new ArrayList<Product>();

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Order() {
        this.user = new User();
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", cart=" + cart +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (cart != null ? !cart.equals(order.cart) : order.cart != null) return false;
        return user != null ? user.equals(order.user) : order.user == null;

    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
