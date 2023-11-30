package com.example.web.jpa.entity.attend;

import com.example.web.model.enums.AttendType;
import com.example.web.model.enums.converter.AttendTypeConverter;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Nationalized;

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
  @Builder.Default
  private String description = "";

  @Column(name = "StartTime", nullable = false)
  private OffsetDateTime startTime;

  @Column(name = "EndTime", nullable = false)
  private OffsetDateTime endTime;
}
