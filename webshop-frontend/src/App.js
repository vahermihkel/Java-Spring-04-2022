import { Container, Nav, Navbar } from 'react-bootstrap';
import { Link, Route, Routes } from 'react-router-dom';
import './App.css';
import AddProduct from './pages/AddProduct';
import Admin from './pages/Admin';
import MaintainProducts from './pages/MaintainProducts';


function App() {
  return (
    <div className="App">
        <Navbar bg="dark" variant="dark">
          <Container>
          <Navbar.Brand as={Link} to="/">Pood</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/admin">Adminstraatori vaatesse</Nav.Link>
            <Nav.Link as={Link} to="/ostukorv">Ostukorvi</Nav.Link>
          </Nav>
          </Container>
        </Navbar>
        <Routes>
          {/* localhost:3000/ --> Avaleht*/}
          <Route path='' exact element={<div>Avaleht</div>} />
          <Route path='ostukorv' exact element={<div>Ostukorv</div>} />
          <Route path='admin' exact element={ <Admin /> } />
          <Route path='admin/lisa-toode' exact element={ <AddProduct /> } />
          <Route path='admin/halda-tooted' exact element={ <MaintainProducts /> } />
          <Route path='admin/muuda-toode' exact element={<div>Toote muutmise leht</div>} />
        </Routes>
    </div>
  );
}

export default App;
