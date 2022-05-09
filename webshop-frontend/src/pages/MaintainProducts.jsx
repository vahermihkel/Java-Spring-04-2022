import { useEffect, useState } from "react"; // use algusega funktsioonid on Reacti HOOK-id
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

function MaintainProducts() {
  const [products, setProducts] = useState([]); // useState uuendab HTMLi
    // vasakpoolne muutuja läheb HTMLi
    // parempoolne funktsioon uuendab vasakpoolset muutujat ja sellega seoses toimub ka uuendus
    // useState sulgude sees on vasakpoolse muutuja algväärtus

  useEffect(()=>{ // useEffect ei lase teist korda siia funktsiooni sisse minna
    fetch("http://localhost:8080/products").then(res => res.json()) // response (body+status+headers+time)
    .then(body => setProducts(body)); // teine .then tähistab body võtmist
  },[]); // kandiliste sulgude sees on list muutujatest kelle muutumisel ta ikka teeb uuesti

  return (
    <div>
       <Link to="/admin">
        <Button>Tagasi</Button>
      </Link>
      <table className="table table-hover table-bordered">
          <tr>
            <th>Nimi</th>
            <th>Hind</th>
            <th>Pilt</th>
            <th>Kirjeldus</th>
            <th>Kogus</th>
            <th>Tegevused</th>
          </tr>
        { products.map(element => 
          <tr>
            <td>{element.name}</td>
            <td>{element.price} €</td>
            <td>{element.description}</td>
            <td>{element.stock} tk</td>
            <td><img className="product-img" src={element.imgSrc} alt="" /></td>
            <td>
              <Button variant="danger">-</Button>
              <Button variant="success">+</Button>
              <button>Muuda</button>
              <Button>X</Button>
            </td>
          </tr>) } 
      </table>  
    </div>)
}

export default MaintainProducts;