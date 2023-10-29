package com.example.web.service.item;

import com.example.web.dto.item.ItemInfoDto;
import com.example.web.dto.item.UserItemInfoDto;
import com.example.web.jpa.entity.item.UserItem;
import com.example.web.jpa.repository.item.ItemRepository;
import com.example.web.jpa.repository.item.UserItemRepository;
import com.example.web.service.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService extends ServiceBase {

  private final ItemRepository itemRepository;
  private final UserItemRepository userItemRepository;

  public ItemInfoDto.Response getItemsInfo() {

    return ItemInfoDto.Response.builder()
        .items(itemRepository.findAll())
        .build();
  }

  public UserItemInfoDto.Response getUserItemsInfo() {

    List<UserItem> userItems = userItemRepository
        .findByUserIndex(getUserIndex());

    return UserItemInfoDto.Response
        .builder()
        .userItems(userItems)
        .build();
  }
}
