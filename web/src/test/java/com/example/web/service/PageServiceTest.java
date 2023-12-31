package com.example.web.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("페이지 기능 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PageService.class)
public class PageServiceTest {

  private final PageService pageService;

  public PageServiceTest(@Autowired PageService pageService) {
    this.pageService = pageService;
  }

  @DisplayName("현재 페이지 번호와 총 페이지 수를 주면, 페이징 바 리스트를 만들어준다.")
  @MethodSource
  @ParameterizedTest(name = "[{index}] 현재 페이지: {0}, 총 페이지: {1} => {2}")
  void givenCurrentPageNumberAndTotalPages_whenPaging_thenReturnPageBarNumbers(
      int currentPageNumber, int totalPages, List<Integer> expected) {
    // Given

    // When
    List<Integer> actual = pageService
        .getPageBarNumbers(currentPageNumber, totalPages);

    // Then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> givenCurrentPageNumberAndTotalPages_whenPaging_thenReturnPageBarNumbers() {
    return Stream.of(
        arguments(0, 10, List.of(0, 1, 2, 3, 4)),
        arguments(1, 10, List.of(0, 1, 2, 3, 4)),
        arguments(2, 10, List.of(0, 1, 2, 3, 4)),
        arguments(3, 10, List.of(1, 2, 3, 4, 5)),
        arguments(4, 10, List.of(2, 3, 4, 5, 6)),
        arguments(5, 10, List.of(3, 4, 5, 6, 7)),
        arguments(6, 10, List.of(4, 5, 6, 7, 8)),
        arguments(7, 10, List.of(5, 6, 7, 8, 9)),
        arguments(8, 10, List.of(6, 7, 8, 9)),
        arguments(9, 10, List.of(7, 8, 9))
    );
  }
}
