package com.fmisser.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/2/25.
 *
 */

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {
    List<Device> findByName(String name);
}
