import { TypeWithKey } from "../models/type-with-key";

export const getValidationError = (errorCode:any) => {

    const error: TypeWithKey<string> = {
        ERR_BAD_RESPONSE: "Error en el servidor",
        ERR_NETWORK: "Error de red",
        ERR_BAD_REQUEST: "Error en la peticion"
    };
    
    return error[errorCode];
}