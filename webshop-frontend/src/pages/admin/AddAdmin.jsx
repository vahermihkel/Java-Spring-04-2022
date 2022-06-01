import { useEffect, useRef, useState } from "react"; // reacti erikood, HOOK
import { Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";

function AddAdmin() {
  const personCodeRef = useRef();
  
  const navigation = useNavigate();
  const [errorMessage, setErrorMessage] = useState("");
  const [isAllOk, setAllOk] = useState(false);

  const authData = JSON.parse(sessionStorage.getItem("authData"));
    const expiration = new Date(authData.expiration);
    let token;
    if (expiration > new Date()) {
      token = authData.token;
    } else {
      sessionStorage.removeItem("authData");
    }

  // ÜHE OTSIMIST
  // useEffect(() => {
  //   fetch("http://localhost:8080/category",{
  //     headers: {
  //       "Authorization": `Bearer ${token}`
  //     }
  //   }).then(res => res.json())
  //   .then(body => setCategories(body));
  // }, [token]);

  // MUUDA TA ADMINIKS
  // function addNewProduct() {
  //   const newProduct = {
  //     name: nameRef.current.value,
  //     price: priceRef.current.value,
  //     imgSrc: imgSrcRef.current.value,
  //     description: descriptionRef.current.value,
  //     stock: stockRef.current.value,
  //     active: activeRef.current.checked,
  //     category: {id: categoryRef.current.value},
  //   }

  //   fetch("http://localhost:8080/products",
  //     {
  //       method: "POST",
  //       body: JSON.stringify(newProduct),
  //       headers: {
  //         "Content-Type": "application/json",
  //         "Authorization": `Bearer ${token}`
  //         // "Authorization": "Bearer " + token
  //       }
  //     }
  //   ).then(res => {
  //     if (res.status === 201) {
  //       setErrorMessage("");
  //       navigation("/admin/halda-tooted")
  //     } else {
  //       console.log(res);
  //       setErrorMessage("Nõutud väljad on täitmata");
  //       throw Error();
  //     }
  //   })
  // }

  function checkIfAllOk() {
    // if (personCodeRef.current.value !== "") {
      setAllOk(personCodeRef.current.value !== "");
    // } else {
    //   setAllOk(false);
    // }
  }

  return (
    <div>
      <Link to="/admin">
        <Button>Tagasi</Button>
      </Link>   <br />
      <div>{errorMessage}</div>
      <label>Isikukood*</label> <br />
      <input onChange={checkIfAllOk} ref={personCodeRef} type="text" /> <br />
      <Button disabled={!isAllOk} variant="success">Otsi kasutaja üles</Button>
    </div>)
}

export default AddAdmin;