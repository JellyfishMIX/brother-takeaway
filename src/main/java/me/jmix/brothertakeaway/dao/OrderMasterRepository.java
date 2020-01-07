package me.jmix.brothertakeaway.dao;

import me.jmix.brothertakeaway.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
    Page<OrderMaster> findByCustomerOpenid(String customerOpenid, Pageable pageable);
}
