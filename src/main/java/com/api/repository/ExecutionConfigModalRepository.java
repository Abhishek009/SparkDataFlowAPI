package com.api.repository;

import com.api.model.ExecutionConfigModal;
import com.api.model.FlowMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExecutionConfigModalRepository extends JpaRepository<ExecutionConfigModal, Long> {

    @Query(value = "select FLOOR(RANDOM()*(5000- 1 + 1)) + 5000 as id,leftsql.dataset_id as input_dataset_id, leftsql.data_set_name as input_dataset_name,\n" +
            "leftsql.dataset_type  as input_dataset_type, leftsql.schema_name as input_dataset_schema_name,\n" +
            "leftsql.table_name as input_dataset_table_name, leftsql.source_type as input_source_type,\n" +
            "leftsql.source_format as input_source_format, inputoutput.selected_input_names as compute_dataset_id,\n" +
            "inputoutput.compute_dataset_name, inputoutput.output_dataset_id as output_dataset_id,\n" +
            "inputoutput.output_dataset, inputoutput.output_schema_name,\n" +
            "inputoutput.output_table_name from ( ( (select output_dataset as output_dataset,\n" +
            "id as output_dataset_id, output_schema_name as output_schema_name,output_table_name as output_table_name,\n" +
            "selected_input_names from public.inputoutput where selected_input_names=:nodeId) a \n" +
            "LEFT JOIN  (select  id as output_id,output_dataset_format as output_format,\n" +
            "output_dataset as compute_dataset_name, cast(UNNEST(STRING_TO_ARRAY(selected_input_names,',')) as bigint) as inputs\n" +
            "from public.inputoutput where id=cast(:nodeId as int) ) b on cast(a.selected_input_names as bigint)=b.output_id \n" +
            ") inputoutput INNER JOIN\n" +
            "(select id as dataset_id, data_set_name as data_set_name, dataset_type as dataset_type,\n" +
            "schema_name as schema_name, table_name as table_name, code, source_format,source_type as source_type\n" +
            "from public.input_modal) leftsql on leftsql.dataset_id = inputoutput.inputs )",nativeQuery = true)
    List<ExecutionConfigModal> getConfigMapping(@Param("nodeId") String nodeId);
}
