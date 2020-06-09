package com.jungo.jungocrawling.Account;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Item")
public class Item {

    private Long id;

    @Column(length = 400)
    private String title;

    private int price;

    @Id
    private String address;

    @Column(length = 1800)
    private String img;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return price == item.price &&
                Objects.equals(id, item.id) &&
                Objects.equals(title, item.title) &&
                Objects.equals(address, item.address) &&
                Objects.equals(img, item.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, address, img, price);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
