package com.example.web.service.item;

import com.example.web.dto.item.ItemInfoDto;
import com.example.web.dto.item.UserItemInfoDto;
import com.example.web.jpa.repository.item.ItemRepository;
import com.example.web.service.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService extends ServiceBase {

  private final ItemRepository itemRepository;

  public ItemInfoDto.Response getItemsInfo() {

    return ItemInfoDto.Response.builder()
        .items(itemRepository.findAll())
        .build();
  }

  public UserItemInfoDto.Response getUserItemsInfo() {

    return UserItemInfoDto.Response
        .builder()
        .build();
  }
}
