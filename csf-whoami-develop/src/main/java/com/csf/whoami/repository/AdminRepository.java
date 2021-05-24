package com.csf.whoami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.whoami.database.TbAdmin;

@Repository
public interface AdminRepository extends JpaRepository<TbAdmin, Long>, JpaSpecificationExecutor<TbAdmin> {

    @Query(value = "select ad from TbAdmin ad where ad.name = :username and ad.password = AES_ENCRYPT(:password, SHA2(:#{#databasekey}, 512))")
    TbAdmin findByNameAndPassword(@Param("username") String name, @Param("password") String password,
                                  @Param("databasekey") String databasekey);
}
