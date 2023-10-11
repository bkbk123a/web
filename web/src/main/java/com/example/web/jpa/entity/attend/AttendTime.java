package com.example.web.jpa.entity.attend;

import com.example.web.model.enums.AttendType;
import com.example.web.model.enums.converter.AttendTypeConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
@Entity
@Table(name = "T_STATIC_Attend")
public class AttendTime {

  @Id
  @Column(name = "AttendIndex")
  private Integer attendIndex;

  @Column(name = "AttendType", nullable = false)
  @Convert(converter = AttendTypeConverter.class)
  private AttendType attendType;

  @Column(name = "Description", nullable = false)
  @Nationalized
  private String description;

  @Column(name = "StartTime", nullable = false)
  private LocalDateTime startTime;

  @Column(name = "EndTime", nullable = false)
  private LocalDateTime endTime;
}
