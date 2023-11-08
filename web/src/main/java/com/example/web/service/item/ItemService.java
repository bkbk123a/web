package com.example.web.service.item;

import com.example.web.dto.item.ItemInfoDto;
import com.example.web.dto.item.UserItemInfoDto;
import com.example.web.jpa.entity.item.Item;
import com.example.web.jpa.entity.item.UserItem;
import com.example.web.jpa.repository.item.ItemRepository;
import com.example.web.jpa.repository.item.UserItemRepository;
import com.example.web.service.ServiceBase;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService extends ServiceBase {

  @PostConstruct
  private void init() {
    List<Item> staticItems = new ArrayList<>();

    staticItems.add(getNewItem("청바지1", 100, 999));
    staticItems.add(getNewItem("청바지2", 200, 999));
    staticItems.add(getNewItem("청바지3", 300, 999));
    staticItems.add(getNewItem("청바지4", 400, 999));
    staticItems.add(getNewItem("상의1", 100, 999));
    staticItems.add(getNewItem("상의2", 200, 999));
    staticItems.add(getNewItem("상의3", 300, 999));
    staticItems.add(getNewItem("상의4", 400, 999));
    staticItems.add(getNewItem("신발1", 100, 999));
    staticItems.add(getNewItem("신발2", 200, 999));
    staticItems.add(getNewItem("신발3", 300, 999));
    staticItems.add(getNewItem("신발4", 400, 999));

    itemRepository.saveAll(staticItems);
  }

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

  private Item getNewItem(String itemName, int price, int quantity) {
    return Item.builder()
        .itemName(itemName)
        .price(price)
        .quantity(quantity)
        .build();
  }
}
