package com.example.web.jpa.entity.attend;

import com.example.web.jpa.entity.attend.id.UserAttendId;
import com.example.web.model.enums.AttendType;
import com.example.web.model.enums.converter.AttendTypeConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Entity
@Getter
@Setter
@Table(name = "T_User_Attend")
@IdClass(UserAttendId.class)
public class UserAttend {

  @Id
  @Column(name = "UserIndex")
  private Long userIndex;

  @Id
  @Column(name = "AttendType")
  @Convert(converter = AttendTypeConverter.class)
  private AttendType attendType;

  @Column(name = "AttendCount", nullable = false)
  @Builder.Default
  private Integer attendCount = 0;

  @Column(name = "LastAttendAt", nullable = false)
  @Builder.Default
  private LocalDateTime lastAttendAt = LocalDateTime.now();

  public void increaseAttendCount() {
    this.attendCount += 1;
  }
}
