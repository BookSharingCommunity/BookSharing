import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button'
import { Link } from 'react-router-dom'
import Rating from './Rating'
import axios from 'axios'
import { useContext } from 'react'
import { Store } from '../Store'
import '../App.css'

function Product(props) {
  const { product } = props

  const { state, dispatch: ctxDispatch } = useContext(Store)
  const {
    cart: { cartItems },
  } = state

  const addToCartHandler = async (item) => {
    const existItem = cartItems.find((x) => x._id === product._id)
    const quantity = existItem ? existItem.quantity + 1 : 1
    const { data } = await axios.get(`http://localhost:8080/api/inventory/get/1`)
    if (data.countInStock < quantity) {
      window.alert('Sorry. Product is out of stock')
      return
    }
    ctxDispatch({
      type: 'CART_ADD_ITEM',
      payload: { ...item, quantity },
    })
  }

  return (
    <div className="row row-cols-2 row-cols-md-6 g-4" class='product-container'>

      <Card>
        <Link to={`/product/${product._id}`}>
          <img src={product.picture} className="card-img-top" alt={product.title} />
        </Link>
        <Card.Body>
          <Link to={`/product/${product._id}`}>
            <Card.Title>{product.title}</Card.Title>
          </Link>
          {/* <Rating rating={product.rating} numReviews={product.numReviews} /> */}
          <Card.Text>${product.price}</Card.Text>
          {product.countInStock === 0 ? (
            <Button variant="light" disabled>
              Out of stock
            </Button>
          ) : (
            <Button onClick={() => addToCartHandler(product)}>Add to cart</Button>
          )}
        </Card.Body>
      </Card>
    </div>
  )
}

export default Product
