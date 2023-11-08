package com.example.web.jpa.repository.item;

import com.example.web.jpa.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
