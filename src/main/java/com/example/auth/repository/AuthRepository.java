package com.example.auth.repository;

import com.example.auth.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface AuthRepository extends JpaRepository<Member, Long> {
    Member findByUseId(String userId);
    Member findByMemberNo(long memberNo);
    boolean existsByUserId(String userId);
//    boolean isDuplicated(String userId);
//    void signin(Member member);
//    Member findUserById(String userId);
//    Member findUserByUserSeq(long memberNo);
}
