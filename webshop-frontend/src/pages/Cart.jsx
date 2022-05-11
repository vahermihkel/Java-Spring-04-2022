import { useState } from "react";

function Cart() {
  const [cartProducts, setCartProducts] = useState(getCartProducts());

  function getCartProducts() {
    if (localStorage.getItem("cartProducts")) {
      return JSON.parse(localStorage.getItem("cartProducts"));
    } else {
      return [];
    }
  }

  // kuvan .map abil välja
  return (<div>Cart</div>)
}

export default Cart;