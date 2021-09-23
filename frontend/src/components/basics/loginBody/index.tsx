
import { useEffect, useState } from "react";
import api from "../../../services/api";

import '../loginBody/index.css'
import { Link } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.css';

interface ilogin {

    codigo: number,
    nome: string,
    email: string,
    admin: boolean,
}

const LoginBody: React.FC = () => {
    const [inputNome, setInputNome] = useState('');
    const [inputEmail, setInputEmail] = useState('');
    const [inputAdmin, setInputAdmin] = useState(false);
  
    const [Login, setLogin] = useState<ilogin[]>([]);
  


    async function postMsg() {

        const response = api.post('/autenticacao/login/', {
            //codigo_rec: number,

            "nome": inputNome,
            "email": inputEmail,
            "admin": inputAdmin,


        });

        if(inputAdmin == true){
            window.location.href = '/listar';
        }else{
            window.location.reload();
        }

        

    }

  



    return (

        <>

            <body id='bodyLogin'>

                <ul >
                    <div id='divH1'>

                        <h1>Nome: </h1>
                        <h1>Email: </h1>
                        
                        


                    </div>
                    <div id='divInput'>
                        <input type="text" value={inputNome} onChange={e => setInputNome(e.target.value)} />
                        <input type="text" value={inputEmail} onChange={e => setInputEmail(e.target.value)} />
                        
  
             

                    </div>

                </ul>
                <input className="form-check-input" type="checkbox" value="" onChange={e => setInputAdmin(!inputAdmin)} id="flexCheckDefault"/>
     <label className="form-check-label">
    É administrador?
  </label>
            <Link to='/'>
               
                    <button onClick={postMsg}>Cadastrar</button>
                </Link>



            </body>



        </>
    );

}

export default LoginBody;

