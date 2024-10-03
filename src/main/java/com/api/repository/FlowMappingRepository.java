package com.api.repository;

import com.api.model.FlowMapping;
import com.api.model.InputModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlowMappingRepository extends JpaRepository<FlowMapping, Long> {

@Query(value = "select FLOOR(RANDOM()*(1000- 1 + 1)) + 1000 as id, users.id as input_data_id,\n" +
        "users.data_set_name as input_dataset_name,\n" +
        "concat('out_',leftsql.id) as output_dataset_id,\n" +
        "leftsql.output_dataset as output_dataset_name\n" +
        "from public.users\n" +
        "LEFT JOIN\n" +
        "(select id,output_dataset_format,output_dataset,output_schema_name,\n" +
        "cast(UNNEST(STRING_TO_ARRAY(selected_input_names,'|')) as int) as inputs,output_table_name from public.inputoutput) leftsql\n" +
        "on users.id=leftsql.inputs;",nativeQuery = true)
    List<FlowMapping> getMapping();
}
