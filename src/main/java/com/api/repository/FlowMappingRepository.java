package com.api.repository;

import com.api.model.FlowMapping;
import com.api.model.InputModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FlowMappingRepository extends JpaRepository<FlowMapping, Long> {

@Query(value = "select FLOOR(RANDOM()*(5000- 1 + 1)) + 5000 as id,\n" +
        "        input_modal.id as input_data_id,\n" +
        "        input_modal.data_set_name as input_dataset_name,\n" +
        "        input_modal.dataset_type as dataset_type,\n" +
        "        COALESCE(leftsql.id, 0) as output_dataset_id,\n" +
        "        COALESCE(leftsql.output_dataset, '') as output_dataset_name\n" +
        "        from public.input_modal\n" +
        "        LEFT JOIN\n" +
        "        (select \n" +
        "         id,\n" +
        "         output_dataset_format,\n" +
        "         output_dataset,\n" +
        "         output_schema_name,\n" +
        "        cast(UNNEST(STRING_TO_ARRAY(selected_input_names,',')) as int) as inputs,\n" +
        "         output_table_name\n" +
        "         from public.inputoutput) leftsql\n" +
        "        on input_modal.id=leftsql.inputs;",nativeQuery = true)
    List<FlowMapping> getMapping();
}
