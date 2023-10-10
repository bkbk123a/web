package com.example.web.jpa.entity.event.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@EqualsAndHashCode
public class UserEventId implements Serializable {

  @Id
  @Column(name = "UserIndex", nullable = false)
  private Integer userIndex;

  @Id
  @Column(name = "EventIndex", nullable = false)
  private Integer eventIndex;
}