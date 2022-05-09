import { useRef } from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

function AddProduct() {
  const nameRef = useRef();
  const priceRef = useRef();
  const imgSrcRef = useRef();
  const descriptionRef = useRef();
  const stockRef = useRef();
  const activeRef = useRef();
  //document.getElementById("name").value

  function addNewProduct() {
    const newProduct = {
      name: nameRef.current.value,
      price: priceRef.current.value,
      imgSrc: imgSrcRef.current.value,
      description: descriptionRef.current.value,
      stock: stockRef.current.value,
      active: activeRef.current.checked,
    }
    fetch("http://localhost:8080/products",
      {
        method: "POST",
        body: JSON.stringify(newProduct),
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJ3ZWJzaG9wIiwic3ViIjoibUBtLmNvbSIsImV4cCI6MTY1MjEwMjA1Mn0.psdWdOo5LEFarBoCDMrYFRVuvI2YG8W8Vm6YfjW1XSgM0ZJJZwxQv6-SUIV6IIvQkgQ42UL5x_mG2oNwaTDuQg"
        }
      }
    );
  }

  return (
    <div>
      <Link to="/admin">
        <Button>Tagasi</Button>
      </Link>   <br />
      <label>Nimi</label> <br />
      <input ref={nameRef} type="text" /> <br />
      <label>Hind</label> <br />
      <input ref={priceRef} type="number" /> <br />
      <label>Pildi aadress</label> <br />
      <input ref={imgSrcRef} type="text" /> <br />
      <label>Kirjeldus</label> <br />
      <input ref={descriptionRef} type="text" /> <br />
      <label>Laoseis</label> <br />
      <input ref={stockRef} type="number" /> <br />
      <label>Aktiivne</label> <br />
      <input ref={activeRef} type="checkbox" /> <br />
      <Button onClick={addNewProduct} variant="success">Sisesta uus toode</Button>
    </div>)
}

export default AddProduct;