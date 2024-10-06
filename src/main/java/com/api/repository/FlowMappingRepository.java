package com.api.repository;

import com.api.model.FlowMapping;
import com.api.model.InputModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlowMappingRepository extends JpaRepository<FlowMapping, Long> {

@Query(value = "select FLOOR(RANDOM()*(5000- 1 + 1)) + 5000 as id, \n" +
        "users.id as input_data_id,\n" +
        "users.data_set_name as input_dataset_name,\n" +
        "COALESCE(leftsql.id, 0) as output_dataset_id,\n" +
        "COALESCE(leftsql.output_dataset, '') as output_dataset_name\n" +
        "from public.users\n" +
        "LEFT JOIN\n" +
        "(select \n" +
        " id,\n" +
        " output_dataset_format,\n" +
        " output_dataset,\n" +
        " output_schema_name,\n" +
        "cast(UNNEST(STRING_TO_ARRAY(selected_input_names,',')) as int) as inputs,\n" +
        " output_table_name \n" +
        " from public.inputoutput) leftsql\n" +
        "on users.id=leftsql.inputs;",nativeQuery = true)
    List<FlowMapping> getMapping();
}
