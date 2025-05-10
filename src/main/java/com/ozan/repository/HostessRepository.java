package com.ozan.repository;

import com.ozan.entity.Hostess;
import com.ozan.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HostessRepository extends JpaRepository<Hostess, Long> {
    List<Hostess> findAllByIsDeletedOrderByUserFirstName(boolean isDeleted);

    Hostess findByUserTcId(String userTcId);

    Hostess findByUserTcIdAndIsDeleted(String userTcId, boolean isDeleted);

    List<Hostess> findAllByVehicleIsNullAndIsDeletedFalseAndStatusEquals(Status status);
}
