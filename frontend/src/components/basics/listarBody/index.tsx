
import {  useEffect, useState } from "react";
import api from "../../../services/api";
import '../listarBody/index.css'

interface ilogin {

    codigo: number,
    nome: string,
    email: string,
    admin: boolean,
}



const ListarBody: React.FC = () => {
    const [Login, setLogin] = useState<ilogin[]>([]);


    async function loadMsg() {
        const response = api.get('/autenticacao/login');

        setLogin((await response).data._embedded.loginDTOList);

      
    }


    useEffect(()=>{
        loadMsg()
    },[]);



    return (

        <>
          
          <body id='bodyListar'>
              
    
                <h1>Contas Cadastradas</h1>
                {
                        Login.map(m => (
                            
                            <ul id='listarBody'>
                                <li>{m.codigo}</li>
                                <li>{m.nome}</li>
                                <li>{m.email}</li>
                                <li>{m.admin}</li>
                            </ul>
                        ))

                    }
              
               </body>

        </>
            );

}

            export default ListarBody;

