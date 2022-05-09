import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

function AddProduct() {
  return (
    <div>
      <Link to="/admin">
        <Button>Tagasi</Button>
      </Link>   

    </div>)
}

export default AddProduct;