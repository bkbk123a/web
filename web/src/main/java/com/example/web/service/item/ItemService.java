package com.example.web.service.item;

import com.example.web.dto.item.UserItemInfoDto;
import com.example.web.service.ServiceBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService extends ServiceBase {

  public UserItemInfoDto.Response getUserItemInfo() {

    return UserItemInfoDto.Response
        .builder()
        .build();
  }
}
