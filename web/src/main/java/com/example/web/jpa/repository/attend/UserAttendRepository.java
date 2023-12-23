package com.example.web.jpa.repository.attend;

import com.example.web.jpa.entity.attend.UserAttend;
import com.example.web.jpa.entity.attend.id.UserAttendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAttendRepository extends JpaRepository<UserAttend, UserAttendId> {

  List<UserAttend> findByUserIndex(Long userIndex);
}
