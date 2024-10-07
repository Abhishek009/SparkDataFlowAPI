select * from public.inputoutput
Select * from public.input_modal

select FLOOR(RANDOM()*(5000- 1 + 1)) + 5000 as id,
        input_modal.id as input_data_id,
        input_modal.data_set_name as input_dataset_name,
		input_modal.dataset_type,
        COALESCE(leftsql.id, 0) as output_dataset_id,
        COALESCE(leftsql.output_dataset, '') as output_dataset_name
        from public.input_modal
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