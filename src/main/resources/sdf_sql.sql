


select users.id as id, users.id as input_data_id,
users.data_set_name as input_dataset_name,
concat('out_',leftsql.id) as output_dataset_id,
leftsql.output_dataset as output_dataset_name
from public.users
LEFT JOIN
(select id,output_dataset_format,output_dataset,output_schema_name,
cast(UNNEST(STRING_TO_ARRAY(selected_input_names,',')) as int) as inputs,output_table_name from public.inputoutput) leftsql
on users.id=leftsql.inputs;