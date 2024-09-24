package com.api.repository;

import com.api.model.TableInfo;
import com.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableInfoRepository extends JpaRepository<TableInfo, Long> {


}
