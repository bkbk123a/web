package com.example.web.jpa.repository.user;

import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.jpa.entity.attend.id.UserAttendId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAttendRepository extends JpaRepository<UserAttend, UserAttendId> {

  List<UserAttend> findByUserIndex(Long userIndex);
}
