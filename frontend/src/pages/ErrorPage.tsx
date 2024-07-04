import { useRouteError } from "react-router-dom"

export function ErrorPage () {
    const error = useRouteError();

    return(
        <div id="error-page" >
            <h1>Not Found!</h1>
            <p>La pagina a la que intenta acceder no existe!</p>
            <p>
                <i>{error?.statusText || error?.message}</i>
            </p>
        </div>
    );
}