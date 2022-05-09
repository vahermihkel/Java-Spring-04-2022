import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

function MaintainProducts() {
  return (
    <div>
       <Link to="/admin">
        <Button>Tagasi</Button>
      </Link>   
    </div>)
}

export default MaintainProducts;