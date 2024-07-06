import { FormControl, Input, InputLabel, Select, Typography } from '@mui/material';
import { ErrorMessage, useField } from 'formik';

export default function CustomSelect({label, ...props}: any) {
    const [field, meta] = useField(props);
    const {name} = props 
    return (
    <>
        <FormControl>
            <InputLabel>{label}</InputLabel>
            <Select 
                {...field}
                {...props}
            />
            <ErrorMessage name={name} >
                {()=> <Typography variant='caption' color='error'>{meta.error}</Typography>}
            </ErrorMessage>
        </FormControl>
    </>
  )
}
