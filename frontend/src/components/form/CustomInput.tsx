import { FormControl, Input, InputLabel, Typography } from '@mui/material'
import { ErrorMessage, useField } from 'formik'

export default function CustomInput({label, ...props}: any) {
    const [field, meta] = useField(props);
    const {name} = props 
    return (
    <>
        <FormControl>
            <InputLabel>{label}</InputLabel>
            <Input 
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
