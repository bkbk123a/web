package com.example.web.jpa.entity.user.id;

import com.example.web.model.enums.AttendType;
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
public class UserAttendId implements Serializable {

  @Id
  @Column(name = "UserIndex")
  private Long userIndex;

  @Id
  @Column(name = "AttendType")
  private AttendType attendType;
}