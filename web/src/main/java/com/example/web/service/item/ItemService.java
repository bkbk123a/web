package com.example.web.service.item;

import com.example.web.dto.item.ItemBuyDto;
import com.example.web.dto.item.ItemInfoDto;
import com.example.web.dto.item.UserItemInfoDto;
import com.example.web.jpa.entity.item.Item;
import com.example.web.jpa.entity.item.UserItem;
import com.example.web.jpa.entity.item.id.UserItemId;
import com.example.web.jpa.entity.user.UserInfo;
import com.example.web.jpa.repository.item.ItemRepository;
import com.example.web.jpa.repository.item.UserItemRepository;
import com.example.web.model.exception.CustomErrorException;
import com.example.web.service.ServiceBase;
import com.example.web.service.user.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
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
  private final UserService userService;

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

  @Transactional
  public ItemBuyDto.Response buyItem(ItemBuyDto.Request request) {
    // 1. dto 생성
    ItemBuyDto.Dto dto = getDto(request);
    // 2. 기획데이터의 남은량 확인
    checkItemCount(dto);
    // 3. 유저가 구매하는데 필요한 돈 확인, 돈 차감
    checkAndMinusUserMoney(dto);
    // 4. 아이템 기획데이터 개수 차감
    minusItemCount(dto);
    // 5. 유저의 아이템 개수 증가
    addUserItem(dto);
    // 6. DB 반영
    saveItemBuy(dto);

    return ItemBuyDto.Response.builder()
        .userMoney(dto.getUserInfo().getMoney())
        .userItem(dto.getUserItem())
        .build();
  }

  private ItemBuyDto.Dto getDto(ItemBuyDto.Request request) {
    // 1. 유저 정보 조회
    UserInfo userInfo = userService.getUserInfoOrElseThrow(getUserIndex());
    // 2. 아이템 기획 데이터 정보 조회
    Item item = getItemOrElseThrow(request.getItemIndex());
    // 3. 유저 아이템 정보 조회
    UserItem userItem = getUserItem(item.getItemIndex(), userInfo.getUserIndex());

    return ItemBuyDto.Dto.builder()
        .userInfo(userInfo)
        .item(item)
        .userItem(userItem)
        .request(request)
        .build();
  }

  private Item getItemOrElseThrow(int itemIndex) {
    return itemRepository.findById(itemIndex)
        .orElseThrow(() -> CustomErrorException.builder().resultValue(10100).build());
  }

  private UserItem getUserItem(int itemIndex, long userIndex) {
    UserItemId userItemId = UserItemId.builder()
        .itemIndex(itemIndex)
        .userIndex(userIndex)
        .build();

    return userItemRepository.findById(userItemId)
        .orElseGet(() -> UserItem.builder()
            .userIndex(userIndex)
            .itemIndex(itemIndex)
            .updatedAt(OffsetDateTime.now())
            .build());
  }

  private void checkItemCount(ItemBuyDto.Dto dto) {
    int needItemCount = dto.getRequest().getItemCount();
    int remainItemCount = dto.getItem().getQuantity();

    if (remainItemCount < needItemCount) {
      throw CustomErrorException.builder().resultValue(10101).build();
    }
  }

  /**
   * 필요한 돈 확인, 돈 차감
   *
   * @param dto
   */
  private void checkAndMinusUserMoney(ItemBuyDto.Dto dto) {
    int itemCount = dto.getRequest().getItemCount();
    int itemPrice = dto.getItem().getPrice();
    long needMoney = itemCount * itemPrice;

    userService.checkEnoughMoney(needMoney, dto.getUserInfo());

    UserInfo userInfo = dto.getUserInfo();
    userInfo.addMoney(-1 * needMoney);
  }

  private void minusItemCount(ItemBuyDto.Dto dto) {
    Item item = dto.getItem();
    item.addItemQuantity(-1 * dto.getRequest().getItemCount());
  }

  private void addUserItem(ItemBuyDto.Dto dto) {
    UserItem userItem = dto.getUserItem();
    userItem.addItemCount(dto.getRequest().getItemCount());
  }

  private void saveItemBuy(ItemBuyDto.Dto dto) {
    userService.saveUserInfo(dto.getUserInfo());
    itemRepository.save(dto.getItem());
    userItemRepository.save(dto.getUserItem());
  }
}
