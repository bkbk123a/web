package com.example.web.service;

import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class PageService {
  // 게시판 메인 화면에서 보이는 바 길이
  private static final int BAR_LENGTH = 5;

  public List<Integer> getPageBarNumbers(int currentPageNumber, int totalPages) {
    int startBarNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
    int endBarNum  = Math.min(startBarNumber + BAR_LENGTH, totalPages);

    return IntStream.range(startBarNumber, endBarNum).boxed().toList();
  }

  public int getCurrentBarLength() {
    return BAR_LENGTH;
  }
}
