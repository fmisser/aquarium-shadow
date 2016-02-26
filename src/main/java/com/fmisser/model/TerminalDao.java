package com.fmisser.model;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2016/2/25.
 *
 */

@Transactional
public interface TerminalDao extends CrudRepository<TerminalModel, Long> {
    List<TerminalModel> findByName(String name);
}
