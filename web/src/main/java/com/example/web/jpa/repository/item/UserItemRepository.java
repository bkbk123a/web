package com.example.web.jpa.repository.item;

import com.example.web.jpa.entity.item.UserItem;
import com.example.web.jpa.entity.item.id.UserItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserItemRepository extends JpaRepository<UserItem, UserItemId> {

  List<UserItem> findByUserIndex(Long userIndex);
}
