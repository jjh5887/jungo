package com.jungo.jungocrawling.Account;

import javax.persistence.*;

@Entity
@Table(name = "item_rank")
public class ItemRank {

    @Id @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private long count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
