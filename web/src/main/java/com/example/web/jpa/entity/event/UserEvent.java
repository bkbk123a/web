package com.example.web.jpa.entity.event;

import com.example.web.jpa.entity.event.Id.UserEventId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
@Entity
@Table(name = "T_User_Event")
@IdClass(UserEventId.class)
public class UserEvent {

  @Id
  @Column(name = "UserIndex")
  private Long userIndex;

  @Id
  @Column(name = "EventIndex")
  private Integer eventIndex;

  @Column(name = "ClearCount", nullable = false)
  private Integer clearCount;

  @Column(name = "LastClearedAt", nullable = false)
  @Builder.Default
  private LocalDateTime lastClearedAt = LocalDateTime.now();
}
