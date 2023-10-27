package com.example.web.jpa.entity.attend.id;

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

  private Long userIndex;

  private AttendType attendType;
}