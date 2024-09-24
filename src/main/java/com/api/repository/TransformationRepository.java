package com.api.repository;

import com.api.model.Transformation;
import com.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransformationRepository extends JpaRepository<Transformation, Long> {


}