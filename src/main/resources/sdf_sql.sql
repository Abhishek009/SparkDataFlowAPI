select * from public.inputoutput
Select * from public.input_modal
select * from public.flow_mapping


-- Input of the selected compute node

select
leftsql.dataset_id as input_dataset_id,
leftsql.data_set_name as input_dataset_name,
leftsql.dataset_type  as input_dataset_type,
leftsql.schema_name as input_dataset_schema_name,
leftsql.table_name as input_dataset_table_name,
leftsql.source_type as input_source_type,
leftsql.source_format as input_source_format,
inputoutput.selected_input_names as compute_dataset_id,
inputoutput.compute_dataset_name,
inputoutput.output_dataset_id as output_dataset_id,
inputoutput.output_dataset,
inputoutput.output_schema_name,
inputoutput.output_table_name
from (
(	
(select 	
	output_dataset as output_dataset,
 	id as output_dataset_id,
 	output_schema_name as output_schema_name,
	output_table_name as output_table_name,
	selected_input_names
from public.inputoutput where selected_input_names='165') a 
LEFT JOIN 
(select 
id as output_id,output_dataset_format as output_format,
output_dataset as compute_dataset_name,

cast(UNNEST(STRING_TO_ARRAY(selected_input_names,',')) as bigint) as inputs
from public.inputoutput where id='165' ) b on cast(a.selected_input_names as bigint)=b.output_id 
) inputoutput
INNER JOIN
(select 
 id as dataset_id, data_set_name as data_set_name, dataset_type as dataset_type,
 schema_name as schema_name, table_name as table_name, code, source_format,source_type as source_type
 from public.input_modal) leftsql
 on leftsql.dataset_id = inputoutput.inputs
)




-- Output of the selected compute node
select * from (
SELECT *,cast(UNNEST(STRING_TO_ARRAY(selected_input_names,',')) as bigint) as inputs
FROM public.inputoutput) inputoutput
WHERE inputoutput.inputs = '122';




select FLOOR(RANDOM()*(5000- 1 + 1)) + 5000 as id,
        input_modal.id as input_data_id,
        input_modal.data_set_name as input_dataset_name,
		input_modal.dataset_type,
        COALESCE(leftsql.id, 0) as output_dataset_id,
        COALESCE(leftsql.output_dataset, '') as output_dataset_name
        MNN from public.input_modal
        LEFT JOIN
        (select 
         id,
         output_dataset_format,
         output_dataset,
         output_schema_name,
        cast(UNNEST(STRING_TO_ARRAY(selected_input_names,',')) as int) as inputs,
         output_table_name
         from public.inputoutput) leftsql
        on input_modal.id=leftsql.inputs;
		











