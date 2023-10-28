package com.example.web.controller;

import com.example.web.dto.item.ItemInfoDto;
import com.example.web.dto.item.UserItemInfoDto;
import com.example.web.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@Slf4j
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/info")
  public ItemInfoDto.Response getItemInfo() {
    return itemService.getItemsInfo();
  }

  @GetMapping("/user-info")
  public UserItemInfoDto.Response getUserItemInfo() {
    return itemService.getUserItemsInfo();
  }
}
