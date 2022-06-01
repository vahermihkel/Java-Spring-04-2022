import { useEffect, useState } from "react"; // use algusega funktsioonid on Reacti HOOK-id
import { Button } from "react-bootstrap";

function MainPage() {
  const baseUrl = "http://localhost:8080";
  const [products, setProducts] = useState([]); // useState uuendab HTMLi

  useEffect(()=>{ // useEffect ei lase teist korda siia funktsiooni sisse minna
    fetch(baseUrl + "/products").then(res => res.json()) // response (body+status+headers+time)
    .then(body => setProducts(body)); // teine .then tähistab body võtmist
  },[]); // kandiliste sulgude sees on list muutujatest kelle muutumisel ta ikka teeb uuesti

  function addToCart(product) {
    let cartProducts = [];
    if (localStorage.getItem("cartProducts") !== null) {
// JSON.parse võtab jutumärgid (localStorage on koht, kus peab alati olema väärtused string kujul)
      cartProducts = JSON.parse(localStorage.getItem("cartProducts"));
    }
    cartProducts.push(product);
    localStorage.setItem("cartProducts",JSON.stringify(cartProducts));
  }

  return (
  <div>
       { products.filter(element => element.active).map(element => 
          <div>
            <div>{element.name}</div>
            <div>{element.price} €</div>
            <div><img className="product-img" src={element.imgSrc} alt="" /></div>
            <div>
              <Button onClick={() => addToCart(element)}>Lisa ostukorvi</Button>  
            </div>
          </div>) } 
  </div>)
}

export default MainPage;