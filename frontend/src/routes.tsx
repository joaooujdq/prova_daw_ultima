import { Route, BrowserRouter } from  'react-router-dom';
import Login from 'pages/login';
import Listar from 'pages/listar';




const Routes = () => {
  return (
    <BrowserRouter>
      <Route component={Login} path="/" exact />
      <Route component={Listar} path="/listar" />
    </BrowserRouter>
  );
}
export default Routes;