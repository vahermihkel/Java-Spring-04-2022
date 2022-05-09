import { useEffect, useState } from "react"; // use algusega funktsioonid on Reacti HOOK-id
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

function MaintainProducts() {
  const baseUrl = "http://localhost:8080";
  const [products, setProducts] = useState([]); // useState uuendab HTMLi
    // vasakpoolne muutuja läheb HTMLi
    // parempoolne funktsioon uuendab vasakpoolset muutujat ja sellega seoses toimub ka uuendus
    // useState sulgude sees on vasakpoolse muutuja algväärtus

  useEffect(()=>{ // useEffect ei lase teist korda siia funktsiooni sisse minna
    fetch(baseUrl + "/products").then(res => res.json()) // response (body+status+headers+time)
    .then(body => setProducts(body)); // teine .then tähistab body võtmist
  },[]); // kandiliste sulgude sees on list muutujatest kelle muutumisel ta ikka teeb uuesti

  function decreaseQuantity(productClicked) {
    fetch(baseUrl + "/decrease-stock", {
      method: "POST",
      body: JSON.stringify(productClicked),
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ3ZWJzaG9wIiwic3ViIjoibUBtLmNvbSIsImV4cCI6MTY1MjEwMjA1Mn0.psdWdOo5LEFarBoCDMrYFRVuvI2YG8W8Vm6YfjW1XSgM0ZJJZwxQv6-SUIV6IIvQkgQ42UL5x_mG2oNwaTDuQg"
      }
    }).then(res => res.json())
    .then(body => setProducts(body));
  }

  function increaseQuantity(productClicked) {
    fetch(baseUrl + "/increase-stock", {
      method: "POST",
      body: JSON.stringify(productClicked),
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ3ZWJzaG9wIiwic3ViIjoibUBtLmNvbSIsImV4cCI6MTY1MjEwMjA1Mn0.psdWdOo5LEFarBoCDMrYFRVuvI2YG8W8Vm6YfjW1XSgM0ZJJZwxQv6-SUIV6IIvQkgQ42UL5x_mG2oNwaTDuQg"
      }
    }).then(res => res.json())
    .then(body => setProducts(body));
  }

  function deleteProduct(productClicked) {
    fetch(baseUrl + "/products/" + productClicked.id, {
      method: "DELETE",
      headers: {
        "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ3ZWJzaG9wIiwic3ViIjoibUBtLmNvbSIsImV4cCI6MTY1MjEwMjA1Mn0.psdWdOo5LEFarBoCDMrYFRVuvI2YG8W8Vm6YfjW1XSgM0ZJJZwxQv6-SUIV6IIvQkgQ42UL5x_mG2oNwaTDuQg"
      }
    }).then(res => res.json())
    .then(body => setProducts(body));
  }

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
              <Button onClick={() => decreaseQuantity(element)} variant="danger">-</Button>
              <Button onClick={() => increaseQuantity(element)} variant="success">+</Button>
              <button>Muuda</button>
              <Button onClick={() => deleteProduct(element)}>X</Button>
            </td>
          </tr>) } 
      </table>  
    </div>)
}

export default MaintainProducts;